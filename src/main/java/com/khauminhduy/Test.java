package com.khauminhduy;

import com.google.ortools.Loader;
import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPObjective;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;

public class Test {

	public static void main(String[] args) {
		Loader.loadNativeLibraries();

		String[] NV = { "1", "2", "3", "4" };
		int[] ts_CV = { 1, 100, 1, 1, 1, 1, 1 };
		double[] time_CV = { 1, 11, 8, 22, 42, 49, 9 };
		int shiftTime = 90;
		int numNV = NV.length;
		int numCV = time_CV.length;
		for (int i = 0; i < ts_CV.length; i++) {
			if (ts_CV[i] == 100)
				ts_CV[i] = numNV;
		}

		System.out.println("\tBANG NHÂN VIÊN - THOI GIAN CONG VIEC ");
		System.out.print("NV/CV\t");
		for (int i = 0; i < numCV; i++) {
			System.out.print("CV" + i + "\t");
		}
		System.out.println("");
		for (int i = 0; i < numNV; i++) {
			System.out.print(NV[i] + "\t");
			for (int j = 0; j < numCV; j++) {
				System.out.print(time_CV[j] + "\t");
			}
			System.out.println("");
		}

		// Thuật toán giải quyết
		// Create the linear solver with the SCIP backend.
		MPSolver solver = MPSolver.createSolver("SCIP");
		if (solver == null) {
			System.out.println("Could not create solver SCIP");
			return;
		}
		// Variables
		// Tạo mảng 2 chiều có giá tị 0 hoặc 1, 1 nếu NV làm CV đó
		MPVariable[][] x = new MPVariable[numNV][numCV];
		for (int i = 0; i < numNV; ++i) {
			for (int j = 0; j < numCV; ++j) {
				if (ts_CV[j] == numNV)
					x[i][j] = solver.makeIntVar(1, 1, "");
				else
					x[i][j] = solver.makeIntVar(0, 1, "");
			}
		}
		// tạo Constraints : rằng buộc bài toán

		for (int j = 0; j < numCV; ++j) {
			MPConstraint constraint = solver.makeConstraint(ts_CV[j], ts_CV[j], "");
			for (int i = 0; i < numNV; ++i) {
				constraint.setCoefficient(x[i][j], 1);
			}
		}

		for (int i = 0; i < numNV; ++i) {
			MPConstraint constraint = solver.makeConstraint(0, shiftTime, "");
			for (int j = 0; j < numCV; ++j) {
				constraint.setCoefficient(x[i][j], time_CV[j]);
			}
		}
		// Objective _ đối tượng
		// gán mảng chi phí vào mảng đối tượng sau đó thực hiện tính toán
		MPObjective objective = solver.objective();
		for (int i = 0; i < numNV; ++i) {
			for (int j = 0; j < numCV; ++j) {
				objective.setCoefficient(x[i][j], time_CV[j]);
			}
		}
		objective.setMaximization();// set tìm giá trị nhỏ nhất theo thuật toán với mỗi task
		MPSolver.ResultStatus resultStatus = solver.solve(); // kết quả dựa vào các giá trị và rằng buộc + đối tượng
																// cost
		// Check that the problem has a feasible solution.
		if (resultStatus == MPSolver.ResultStatus.OPTIMAL || resultStatus == MPSolver.ResultStatus.FEASIBLE) {
			for (int i = 0; i < numNV; ++i) {
				for (int j = 0; j < numCV; ++j) {
					System.out.print(x[i][j].solutionValue() + "\t");
				}
				System.out.println("");
			}
			System.out.println("Total Time: " + objective.value() + "\n");
			for (int i = 0; i < numNV; ++i) {
				int s = 0;
				for (int j = 0; j < numCV; ++j) {
					if (x[i][j].solutionValue() > 0.5) {
						s += time_CV[j];
						System.out.println("Worker " + i + " assigned to task " + j + ".  Time = " + time_CV[j]);
					}
				}
				System.out.println("Tong : " + s);
			}
		} else {
			System.err.println("No solution found.");
		}
	}

}
