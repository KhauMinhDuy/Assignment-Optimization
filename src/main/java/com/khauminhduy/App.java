package com.khauminhduy;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.google.ortools.Loader;
import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPObjective;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import com.khauminhduy.model.Data;
import com.khauminhduy.util.CollectUtill;
import com.khauminhduy.util.ReadFileExcel;

public class App {

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
									process(list, date, shiftBig, shiftSmall);
									System.out.println("\n ================================================================= \n");
								}
							}
						}
					}
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private static void process(List<Data> list,String date, int shiftBig, int shiftSmall) {
		
		
		List<String> jobs = CollectUtill.toJobNames(list, date, shiftBig, shiftSmall);
		
		Optional<Integer> headCount = CollectUtill.toHeadCounts(list, date, shiftBig, shiftSmall);
		
		List<Integer> times = CollectUtill.toMinuteFinishWork(list, date, shiftBig, shiftSmall);
		
		List<Integer> listWorkers = CollectUtill.toListWorker(list, date, shiftBig, shiftSmall);
		
		Integer timeShiftBig = CollectUtill.timeShiftBig(list, date, shiftBig, shiftSmall);
		
		Integer timeShiftSmall = CollectUtill.timeShiftSmall(list, date, shiftBig, shiftSmall);
		
		Integer numWorkers = 0;
		int numTasks = jobs.size();
		
		if(headCount.isPresent()) {
			numWorkers = headCount.get();
		}
		
		for(int i = 0; i < listWorkers.size(); i++) {
			
			if(listWorkers.get(i) > 1 && listWorkers.get(i) <= numWorkers) {
				if(times.get(i) >= listWorkers.get(i)) {
					times.set(i, times.get(i) / listWorkers.get(i));
				}
			}
			
			if(listWorkers.get(i) > numWorkers) {
				listWorkers.set(i, numWorkers);
				if(times.get(i) >= listWorkers.get(i)) {
					times.set(i, times.get(i) / listWorkers.get(i));
				}
			}
		}
		
		for(int i = 0; i < times.size(); i++) {
			if(times.get(i) > timeShiftSmall) {
				times.set(i, times.get(i) / numWorkers);
			}
		}
		
		int max = 0;
		for(int i = 0; i < listWorkers.size(); i++) {
			System.out.println("So NV trong CV: " + listWorkers.get(i) + " | T/g Lam CV: " + times.get(i));
			max += listWorkers.get(i) * times.get(i);
		}
		
		System.out.println("Total Effort: " + max + " | Quy T/g ca nho: " + (timeShiftSmall * numWorkers));
		MPSolver solver = MPSolver.createSolver("GLOP");
		if(solver == null) {
			return;
		}
		
		MPVariable[][] variables = new MPVariable[numWorkers][numTasks];
		for(int i = 0; i < numWorkers; i++) {
			for (int j = 0; j < numTasks; j++) {
				variables[i][j] = solver.makeIntVar(0, 1, "");
			}
		}
		
		
		for(int i = 0; i < numWorkers; i++) {
			MPConstraint constraint = solver.makeConstraint(0, timeShiftSmall, "");
			for(int j = 0; j < numTasks; j++) {
				constraint.setCoefficient(variables[i][j], times.get(j));
			}
		}
		
		
		for(int j = 0; j < numTasks; j++) {
			MPConstraint constraint = solver.makeConstraint(listWorkers.get(j), listWorkers.get(j), "");
			for(int i = 0; i < numWorkers; i++) {
				constraint.setCoefficient(variables[i][j], 1);
			}
		}
		
		MPObjective objective = solver.objective();
		for(int i = 0; i < numWorkers; i++) {
			for(int j = 0; j < numTasks; j++) {
				objective.setCoefficient(variables[i][j], times.get(j));
			}
		}
		
		
//		objective.setMaximization();
		objective.setMinimization();
		MPSolver.ResultStatus resultStatus = solver.solve();
		if(resultStatus == MPSolver.ResultStatus.OPTIMAL || resultStatus == MPSolver.ResultStatus.FEASIBLE ) {
			System.out.println("T/g Ca Lon: " + timeShiftBig);
			System.out.println("T/g Ca Nho: " + timeShiftSmall);
			System.out.println("Cost: " + objective.value());
			for(int i = 0; i < numWorkers; i++) {
				int s = 0;
				for(int j = 0; j < numTasks; j++) {
					if(variables[i][j].solutionValue() > 0.5) {
						s += times.get(j);
						System.out.println("Worker NV" + (i+1) + " assigned to task " + jobs.get(j) + ".  Time = " + times.get(j) +"p");
					}
				}
				System.out.println("NV" + (i+1) + " => " + s);
			}
		} else {
			System.out.println("No solution found");
		}
	}

}
