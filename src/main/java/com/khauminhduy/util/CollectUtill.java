package com.khauminhduy.util;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.khauminhduy.model.Data;

public class CollectUtill {
	
	public static List<Integer> toTimes(List<Data> list, String date, int shiftBig, int shiftSmall) {
		return list.stream()
				.filter(e -> e.getDate().equalsIgnoreCase(date))
				.filter(e -> e.getShiftBig() == shiftBig)
				.filter(e -> e.getShiftSmall() == shiftSmall)
				.map(e -> e.getMinutesFinishWork())
				.collect(Collectors.toList());
	}

	public static Optional<Integer> toWorkers(List<Data> list, String date, int shiftBig, int shiftSmall) {
		return list.stream()
				.filter(e -> e.getDate().equalsIgnoreCase(date))
				.filter(e -> e.getShiftBig() == shiftBig)
				.filter(e -> e.getShiftSmall() == shiftSmall)
				.map(e -> e.getHeadCount())
				.max(Integer::compareTo);
	}

	public static List<String> toJobNames(List<Data> list, String date, int shiftBig, int shiftSmall) {
		return list.stream()
				.filter(e -> e.getDate().equalsIgnoreCase(date))
				.filter(e -> e.getShiftBig() == shiftBig)
				.filter(e -> e.getShiftSmall() == shiftSmall)
				.map(e -> e.getJobName())
				.collect(Collectors.toList());
	}
	
	public static List<Integer> toListWorker(List<Data> list, String date, int shiftBig, int shiftSmall) {
		return list.stream()
				.filter(e -> e.getDate().equalsIgnoreCase(date))
				.filter(e -> e.getShiftBig() == shiftBig)
				.filter(e -> e.getShiftSmall() == shiftSmall)
				.map(e -> e.getListWorkers())
				.collect(Collectors.toList());
				
	}
	
}
