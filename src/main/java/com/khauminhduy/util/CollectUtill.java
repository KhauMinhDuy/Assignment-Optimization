package com.khauminhduy.util;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.khauminhduy.model.Data;

public class CollectUtill {
	
	private CollectUtill() {}
	
	public static List<Data> getDataByDateAndShopId(List<Data> lists, String date, Integer shopId) {
		return lists.stream()
				.filter(e -> e.getDate().equalsIgnoreCase(date))
				.filter(e -> e.getShopId().equals(shopId))
				.collect(Collectors.toList());
	}
	
	public static List<Integer> toMinuteFinishWork(List<Data> list, String date, int shiftBig, int shiftSmall) {
		return list.stream()
				.filter(e -> e.getDate().equalsIgnoreCase(date))
				.filter(e -> e.getShiftBig() != null ? e.getShiftBig() == shiftBig : false)
				.filter(e -> e.getShiftSmall() != null ? e.getShiftSmall() == shiftSmall : false)
				.map(Data::getMinutesFinishWork)
				.collect(Collectors.toList());
	}

	public static Integer toHeadCounts(List<Data> list, String date, Integer shiftBig) {
		Optional<Integer> headcount = list.stream()
				.filter(e -> e.getDate().equalsIgnoreCase(date))
				.filter(e -> e.getShiftBig() != null ? e.getShiftBig().equals(shiftBig) : false)
				.findFirst()
				.map(Data::getHeadCount);
				
		if(headcount.isPresent()) {
			return headcount.get();
		}
		throw new IllegalArgumentException("khong tim thay headcount");
	}

	public static List<String> toJobNames(List<Data> list, String date, int shiftBig, int shiftSmall) {
		return list.stream()
				.filter(e -> e.getDate().equalsIgnoreCase(date))
				.filter(e -> e.getShiftBig() != null ? e.getShiftBig() == shiftBig : false)
				.filter(e -> e.getShiftSmall() != null ? e.getShiftSmall() == shiftSmall : false)
				.map(Data::getJobName)
				.collect(Collectors.toList());
	}
	
	public static List<Integer> toListWorker(List<Data> list, String date, int shiftBig, int shiftSmall) {
		return list.stream()
				.filter(e -> e.getDate().equalsIgnoreCase(date))
				.filter(e -> e.getShiftBig() != null ? e.getShiftBig() == shiftBig : false)
				.filter(e -> e.getShiftSmall() != null ? e.getShiftSmall() == shiftSmall : false)
				.map(Data::getListWorkers)
				.collect(Collectors.toList());
				
	}
	
	public static Integer timeShiftSmall(List<Data> list, String date, int shiftBig, int shiftSmall) {
		 Optional<Integer> max = list.stream()
				.filter(e -> e.getDate().equalsIgnoreCase(date))
				.filter(e -> e.getShiftBig() != null ? e.getShiftBig() == shiftBig : false)
				.filter(e -> e.getShiftSmall() != null ? e.getShiftSmall() == shiftSmall : false)
				.findFirst()
				.map(Data::getTimeShiftSmall);
		 if(max.isPresent()) return max.get();
		 throw new IllegalArgumentException("khong tim thay thoi gian lam viec ca nho");
	}
	
	public static Integer timeShiftBig(List<Data> list, String date, Integer shiftBig) {
		 Optional<Integer> max = list.stream()
				.filter(e -> e.getDate().equalsIgnoreCase(date))
				.filter(e -> e.getShiftBig() != null ? e.getShiftBig().equals(shiftBig) : false)
				.findFirst()
				.map(Data::getTimeShiftBig);
		 if(max.isPresent()) {
			 return max.get();
		 }
		 throw new IllegalArgumentException("khong tim thay thoi gian lam viec ca lon");
	}
	
	public static Set<String> toDates(List<Data> lists) {
		Set<String> dates = new LinkedHashSet<>();
		lists.stream()
				.map(Data::getDate)
				.forEach(dates::add);
		return dates;
	}
	
	public static Set<Integer> toShopId(List<Data> lists, String date) {
		Set<Integer> shopId = new LinkedHashSet<>();
		lists.stream()
			.filter(e -> e.getDate().equalsIgnoreCase(date))
			.map(Data::getShopId)
			.forEach(shopId::add);
		return shopId;
			
	}
	
	public static Set<Integer> toShiftBigId(List<Data> lists, String date, Integer shopId) {
		Set<Integer> shiftBigId = new LinkedHashSet<>();
		lists.stream()
			.filter(e -> e.getDate().equalsIgnoreCase(date))
			.filter(e -> e.getShopId() != null ? e.getShopId().equals(shopId) : false)
			.map(Data::getShiftBig)
			.forEach(shiftBigId::add);
		return shiftBigId;
	}
	
	public static Set<Integer> toShiftSmallId(List<Data> lists, String date, Integer shopId, Integer shiftBigId) {
		Set<Integer> shiftSmallId = new LinkedHashSet<>();
		lists.stream()
			.filter(e -> e.getDate().equalsIgnoreCase(date))
			.filter(e -> e.getShopId() != null ? e.getShopId().equals(shopId) : false)
			.filter(e -> e.getShiftBig() != null ? e.getShiftBig().equals(shiftBigId) : false)
			.map(Data::getShiftSmall)
			.forEach(shiftSmallId::add);
		return shiftSmallId;
	}
	
	
	public static List<Integer> toTypeWorks(List<Data> lists, String date, Integer shopId, 
			Integer shiftBigId, Integer shiftSmallId) {
		
		return lists.stream()
				.filter(e -> e.getDate().equalsIgnoreCase(date))
				.filter(e -> e.getShopId() != null ? e.getShopId().equals(shopId) : false)
				.filter(e -> e.getShiftBig() != null ? e.getShiftBig().equals(shiftBigId) : false)
				.filter(e -> e.getShiftSmall() != null ? e.getShiftSmall().equals(shiftSmallId) : false)
				.map(Data::getTypeWork)
				.collect(Collectors.toList());
	}
	
	public static List<Data> getDataByTypeWork(List<Data> lists, String date, Integer shopId, Integer shiftBig) {
		return lists.stream()
				.filter(e -> e.getDate().equalsIgnoreCase(date))
				.filter(e -> e.getShopId().equals(shopId))
				.filter(e -> e.getShiftBig() != null ? e.getShiftBig().equals(shiftBig) : false)
				.collect(Collectors.toList());
	}
	
	public static Integer toHourStart(List<Data> lists, String date, Integer shopId, Integer shiftBig, Integer shiftSmall) {
		Optional<Integer> hourStart = lists.stream()
				.filter(e -> e.getDate().equalsIgnoreCase(date))
				.filter(e -> e.getShopId().equals(shopId))
				.filter(e -> e.getShiftBig() != null ? e.getShiftBig().equals(shiftBig) : false)
				.filter(e -> e.getShiftSmall() != null ? e.getShiftSmall().equals(shiftSmall) : false)
				.findFirst()
				.map(Data::getHourStart);
		if(hourStart.isPresent()) {
			return hourStart.get();
		}
		throw new IllegalArgumentException("khong tim thay hour Start");
				
	}
	
	public static Integer toMinuteStart(List<Data> lists, String date, Integer shopId, Integer shiftBig, Integer shiftSmall) {
		Optional<Integer> minuteStart = lists.stream()
				.filter(e -> e.getDate().equalsIgnoreCase(date))
				.filter(e -> e.getShopId().equals(shopId))
				.filter(e -> e.getShiftBig() != null ? e.getShiftBig().equals(shiftBig) : false)
				.filter(e -> e.getShiftSmall() != null ? e.getShiftSmall().equals(shiftSmall) : false)
				.findFirst()
				.map(Data::getMinuteStart);
		if(minuteStart.isPresent()) {
			return minuteStart.get();
		}
		throw new IllegalArgumentException("khong tim thay Minute Start");
				
	}
	
}
