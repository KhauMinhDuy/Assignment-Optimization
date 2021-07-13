package com.khauminhduy;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.google.ortools.Loader;
import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPObjective;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import com.khauminhduy.model.Data;
import com.khauminhduy.util.ReadFileExcel;

public class App {

	public static void main(String[] args) {
		
		Loader.loadNativeLibraries();
		
		String path = "src/main/resources/AssignmentData.xlsx";
		
		try {
			
			List<Data> list = ReadFileExcel.getALL(path);
			
			List<String> jobs = toJobs(list);
			
			Optional<Integer> max = toWorker(list);
			
			List<String> times = toTimes(list);
			
			int[] cost = {
				4,1,16,19,27,16,5,1,2,1
			};
			
			int[] ts_cv = {
//				1,2,1,3,2,1,1,3,1,1,
				1,1,1,1,1,1,1,1,1,1
			};
			
			
			Integer numWorkers = 0;
			int numJob = jobs.size();
			
			if(max.isPresent()) {
				numWorkers = max.get();
			}
			
			String[] workers = {
				"NV1", "NV2", "NV3",	
			};
			
			MPSolver solver = MPSolver.createSolver("SCIP");
			if(solver == null) {
				return;
			}
			
			MPVariable[][] variables = new MPVariable[numWorkers][numJob];
			for(int i = 0; i < numWorkers; i++) {
				for (int j = 0; j < numJob; j++) {
					if(ts_cv[j] == numWorkers) {
						variables[i][j] = solver.makeIntVar(1, 1, "");
					} else {
						variables[i][j] = solver.makeIntVar(0, 1, "");
					}
				}
			}
			
			System.out.println("Number of Variables: " + solver.numVariables());
			
			for(int i = 0; i < numWorkers; i++) {
				MPConstraint constraint = solver.makeConstraint(0, 60, "");
				for(int j = 0; j < numJob; j++) {
					constraint.setCoefficient(variables[i][j], cost[j]);
				}
			}
			
			for(int j = 0; j < numJob; j++) {
				MPConstraint constraint = solver.makeConstraint(ts_cv[j], ts_cv[j], "");
				for(int i = 0; i < numWorkers; i++) {
					constraint.setCoefficient(variables[i][j], 1);
				}
			}
			
			System.out.println("Number of Constraints : " + solver.numConstraints());
			
			MPObjective objective = solver.objective();
			for(int i = 0; i < numWorkers; i++) {
				for(int j = 0; j < numJob; j++) {
					objective.setCoefficient(variables[i][j], cost[j] );
				}
			}
			
			
			objective.setMaximization();
			MPSolver.ResultStatus resultStatus = solver.solve();
			if(resultStatus == MPSolver.ResultStatus.OPTIMAL || resultStatus == MPSolver.ResultStatus.FEASIBLE ) {
				System.out.println("Cost: " + objective.value());
				for(int i = 0; i < numWorkers; i++) {
					int s = 0;
					for(int j = 0; j < numJob; j++) {
						if(variables[i][j].solutionValue() > 0.5) {
							s += cost[j];
							System.out.println("Worker " + workers[i] + " assigned to task " + jobs.get(j) + ".  Cost = " + cost[j]);
						}
					}
					System.out.println(workers[i] + " => " + s);
				}
			} else {
				System.out.println("No solution found");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static List<String> toTimes(List<Data> list) {
		List<String> times = list.stream()
				.filter(e -> e.getDate().equalsIgnoreCase("31-May-2021"))
				.filter(e -> e.getShiftBig().equalsIgnoreCase("1.0"))
				.filter(e -> e.getShiftSmall().equalsIgnoreCase("11.0"))
				.map(e -> e.getSoPhutHoanThanh())
				.collect(Collectors.toList());
		return times;
	}

	private static Optional<Integer> toWorker(List<Data> list) {
		Optional<Integer> max = list.stream()
			.filter(e -> e.getDate().equalsIgnoreCase("31-May-2021"))
			.filter(e -> e.getShiftBig().equalsIgnoreCase("1.0"))
			.filter(e -> e.getShiftSmall().equalsIgnoreCase("11.0"))
			.map(e -> e.getHeadCount())
			.map(e -> {
				return Integer.parseInt(e.substring(0, 1));
			})
			.max(Integer::compareTo);
		return max;
	}

	private static List<String> toJobs(List<Data> list) {
		return list.stream()
			.filter(e -> e.getDate().equalsIgnoreCase("31-May-2021"))
			.filter(e -> e.getShiftBig().equalsIgnoreCase("1.0"))
			.filter(e -> e.getShiftSmall().equalsIgnoreCase("11.0"))
			.map(e -> e.getJobName())
			.collect(Collectors.toList());
	}

}
