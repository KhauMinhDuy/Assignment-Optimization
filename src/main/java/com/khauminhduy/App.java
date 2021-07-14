package com.khauminhduy;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
			List<Data> list = ReadFileExcel.getALL(path);
			System.out.println("CA 1 - 11");
			process(list, "31-May-2021", 1, 11);
			System.out.println("=====================================");
			System.out.println("CA 1 - 12");
			process(list, "31-May-2021", 1, 12);
			System.out.println("=====================================");
			System.out.println("CA 1 - 13");
			process(list, "31-May-2021", 1, 13);
			System.out.println("=====================================");
			System.out.println("CA 2 - 14");
			process(list, "31-May-2021", 2, 14);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private static void process(List<Data> list,String date, int shiftBig, int shiftSmall) {
		
		
		List<String> jobs = CollectUtill.toJobNames(list, date, shiftBig, shiftSmall);
		
		Optional<Integer> max = CollectUtill.toWorkers(list, date, shiftBig, shiftSmall);
		
		List<Integer> times = CollectUtill.toTimes(list, date, shiftBig, shiftSmall);
		
		List<Integer> listWorkers = CollectUtill.toListWorker(list, date, shiftBig, shiftSmall);
		
		Integer timeShiftBig = CollectUtill.timeShiftBig(list, date, shiftBig, shiftSmall);
		
		Integer timeShiftSmall = CollectUtill.timeShiftSmall(list, date, shiftBig, shiftSmall);
		
		Integer numWorkers = 0;
		int numTasks = jobs.size();
		
		if(max.isPresent()) {
			numWorkers = max.get();
		}
		
		for(int i = 0; i < listWorkers.size(); i++) {
			if(listWorkers.get(i) > 1 && listWorkers.get(i) < numWorkers) {
				if(times.get(i) >= listWorkers.get(i)) {
//					times.set(i,(int) (times.get(i) / listWorkers.get(i)));
					times.set(i,(int) (times.get(i) / numWorkers));
				}
			}
			if(listWorkers.get(i) >= numWorkers) {
				listWorkers.set(i, numWorkers);
				if(times.get(i) >= listWorkers.get(i)) {
					times.set(i,(int) (times.get(i) / numWorkers));
				}
			}
		}


		String[] workers = {
			"NV1", "NV2", "NV3",	
		};
			
		MPSolver solver = MPSolver.createSolver("SCIP");
		if(solver == null) {
			return;
		}
		
		MPVariable[][] variables = new MPVariable[numWorkers][numTasks];
		for(int i = 0; i < numWorkers; i++) {
			for (int j = 0; j < numTasks; j++) {
				if(listWorkers.get(j) >= numWorkers) {
					variables[i][j] = solver.makeIntVar(1, 1, "");
				} else {
					variables[i][j] = solver.makeIntVar(0, 1, "");
				}
//					variables[i][j] = solver.makeIntVar(0, 1, "");
			}
		}
		
		
		for(int i = 0; i < numWorkers; i++) {
			MPConstraint constraint = solver.makeConstraint(timeShiftSmall/numWorkers, timeShiftSmall, "");
			for(int j = 0; j < numTasks; j++) {
				int time = times.get(j) > timeShiftSmall ? times.get(j) / numWorkers : times.get(j);
				constraint.setCoefficient(variables[i][j], time);
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
				int time = times.get(j) > timeShiftSmall ? times.get(j) / numWorkers : times.get(j);
				objective.setCoefficient(variables[i][j], time);
			}
		}
		
//			objective.setMaximization();
		objective.setMinimization();
		MPSolver.ResultStatus resultStatus = solver.solve();
		if(resultStatus == MPSolver.ResultStatus.OPTIMAL || resultStatus == MPSolver.ResultStatus.FEASIBLE ) {
			System.out.println("Cost: " + objective.value());
			for(int i = 0; i < numWorkers; i++) {
				int s = 0;
				for(int j = 0; j < numTasks; j++) {
					if(variables[i][j].solutionValue() > 0.5) {
						int time = times.get(j) > timeShiftSmall ? times.get(j) / numWorkers : times.get(j);
						s += time;
						System.out.println("Worker " + workers[i] + " assigned to task " + jobs.get(j) + ".  Time = " + time +"p");
					}
				}
				System.out.println(workers[i] + " => " + s);
			}
		} else {
			System.out.println("No solution found");
		}
	}

}
