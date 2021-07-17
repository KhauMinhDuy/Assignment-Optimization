package com.khauminhduy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.google.ortools.Loader;
import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPObjective;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import com.khauminhduy.model.Data;
import com.khauminhduy.model.DataOutput;
import com.khauminhduy.util.CollectUtill;
import com.khauminhduy.util.ReadFileExcel;
import com.khauminhduy.util.WriteFileExcel;

public class App {
	
	private static List<DataOutput> listOutput = new ArrayList<>();

	public static void main(String[] args) {
		
		Loader.loadNativeLibraries();
		
		String path = "src/main/resources/AssignmentData.xlsx";
		
		try {
			
			List<Data> list = ReadFileExcel.getAllLines(path);
			
			Set<String> dates = CollectUtill.toDates(list);
			
			for(String date : dates) {
				
				Set<Integer> shopId = CollectUtill.toShopId(list, date);
				
				for(Integer shopid : shopId) {
					
					Set<Integer> shiftBigId = CollectUtill.toShiftBigId(list, date, shopid);
					
					for(Integer shiftBig : shiftBigId) {
						
						if(shiftBig != null) {
							
							Set<Integer> shiftSmallId = CollectUtill.toShiftSmallId(list, date, shopid, shiftBig);
							
							for(Integer shiftSmall : shiftSmallId) {
								
								if(shiftSmall != null) {
									
									System.out.println("Date: " + date + " - Shop: " + shopid + " - Ca Lon: " + shiftBig + " - Ca Nho: " + shiftSmall);
									process(list, date, shopid, shiftBig, shiftSmall);
									System.out.println("\n ================================================================= \n");
									
								}
							}
						}
					}
				}
			}
			
			exportData(listOutput);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private static void exportData(List<DataOutput> listOutput2) {
		try {
			WriteFileExcel.writeAll(listOutput2, "src/main/resources/dataoutput.xlsx");
			System.out.println("File output: src/main/resources/dataoutput.xlsx");
		} catch (InvalidFormatException | IOException e) {
			e.printStackTrace();
		} 
	}

	private static void process(List<Data> list,String date, Integer shopId, int shiftBig, int shiftSmall) {
		
		List<String> jobs = CollectUtill.toJobNames(list, date, shiftBig, shiftSmall);
		
		Integer headCount = CollectUtill.toHeadCounts(list, date, shiftBig);
		
		List<Integer> timeWorks = CollectUtill.toMinuteFinishWork(list, date, shiftBig, shiftSmall);
		
		List<Integer> listWorker = CollectUtill.toListWorker(list, date, shiftBig, shiftSmall);
		
		Integer timeShiftBig = CollectUtill.timeShiftBig(list, date, shiftBig);
		
		Integer timeShiftSmall = CollectUtill.timeShiftSmall(list, date, shiftBig, shiftSmall);
		
		List<Integer> typeWorks = CollectUtill.toTypeWorks(list, date, shopId, shiftBig, shiftSmall);
		
		Integer hourStart = CollectUtill.toHourStart(list, date, shopId, shiftBig, shiftSmall);
		Integer minuteStart = CollectUtill.toMinuteStart(list, date, shopId, shiftBig, shiftSmall);
		
//		Integer numWorkers = headCount;
		int numTasks = jobs.size();
		
		for(int i = 0; i < listWorker.size(); i++) {
			
			if(listWorker.get(i) > 1 && 
					listWorker.get(i) <= headCount && 
					timeWorks.get(i) >= listWorker.get(i)) {
				
				timeWorks.set(i, timeWorks.get(i) / listWorker.get(i));
				
			}
			
			if(listWorker.get(i) > headCount) {
				listWorker.set(i, headCount);
				if(timeWorks.get(i) >= listWorker.get(i)) {
					timeWorks.set(i, timeWorks.get(i) / listWorker.get(i));
				}
			}
			
		}
		
		for(int i = 0; i < timeWorks.size(); i++) {
			if(timeWorks.get(i) > timeShiftSmall) {
				timeWorks.set(i, timeWorks.get(i) / headCount);
			}
		}
		
		int max = 0;
		for(int i = 0; i < listWorker.size(); i++) {
			System.out.println("So NV trong CV: " + listWorker.get(i) + " | T/g Lam CV: " + timeWorks.get(i) +
					" | Loai CV: " + typeWorks.get(i));
			max += listWorker.get(i) * timeWorks.get(i);
		}
		
		System.out.println("Total Effort: " + max + " | Quy T/g ca nho: " + (timeShiftSmall * headCount));
		
		
		MPSolver solver = MPSolver.createSolver("GLOP");
		if(solver == null) {
			return;
		}
		
		MPVariable[][] variables = new MPVariable[headCount][numTasks];
		for(int i = 0; i < headCount; i++) {
			for (int j = 0; j < numTasks; j++) {
				variables[i][j] = solver.makeIntVar(0, 1, "");
			}
		}
		
		
		for(int i = 0; i < headCount; i++) {
			MPConstraint constraint = solver.makeConstraint(0, timeShiftSmall, "");
			for(int j = 0; j < numTasks; j++) {
				constraint.setCoefficient(variables[i][j], timeWorks.get(j));
			}
		}
		
		
		for(int j = 0; j < numTasks; j++) {
			MPConstraint constraint = solver.makeConstraint(listWorker.get(j), listWorker.get(j), "");
			for(int i = 0; i < headCount; i++) {
				constraint.setCoefficient(variables[i][j], 1);
			}
		}
		
		MPObjective objective = solver.objective();
		for(int i = 0; i < headCount; i++) {
			for(int j = 0; j < numTasks; j++) {
				objective.setCoefficient(variables[i][j], timeWorks.get(j));
			}
		}
		
		
		objective.setMinimization();
		MPSolver.ResultStatus resultStatus = solver.solve();
		if(resultStatus == MPSolver.ResultStatus.OPTIMAL || resultStatus == MPSolver.ResultStatus.FEASIBLE ) {
			
			System.out.println("T/g Ca Lon: " + timeShiftBig);
			System.out.println("T/g Ca Nho: " + timeShiftSmall);
			System.out.println("Cost: " + objective.value());
			for(int i = 0; i < headCount; i++) {
				int s = 0;
				for(int j = 0; j < numTasks; j++) {
					if(variables[i][j].solutionValue() > 0.5) {
						s += timeWorks.get(j);
						System.out.println("Worker NV" + (i+1) + " assigned to task " 
										+ jobs.get(j) + ".  Time = " + timeWorks.get(j) +"p");
						listOutput.add(
								new DataOutput(date, shopId, shiftBig, "NV" + (i+1) ,	
											false, false, shiftSmall, jobs.get(j), 
											timeWorks.get(j),hourStart, minuteStart)
								);
					}
				}
				System.out.println("NV" + (i+1) + " => " + s);
			}
		} else {
			System.out.println("No solution found");
		}
		
		
	}

}
