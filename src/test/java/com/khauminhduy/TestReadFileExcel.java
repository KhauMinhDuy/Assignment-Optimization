package com.khauminhduy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import com.khauminhduy.model.Data;
import com.khauminhduy.util.CollectUtill;
import com.khauminhduy.util.ReadFileExcel;

class TestReadFileExcel {
	
	String path = "src/main/resources/AssignmentData.xlsx";

	@Test
	void testReadFile() {
		try {
			XSSFWorkbook workbook = ReadFileExcel.open(path);
			assertTrue(workbook != null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testGetALL() {
		try {
			List<Data> list = ReadFileExcel.getALL(path);
			assertEquals(593, list.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testParseInt() {
		float parseInt = Float.parseFloat("3.0");
		int x = (int) parseInt;
		assertEquals(3.0, parseInt);
		assertEquals(3, x);
	}
	
	@Test
	void testListDate() {
		try {
			List<Data> lists = ReadFileExcel.getALL(path);
			Set<String> dates = new LinkedHashSet<>();
			lists.stream()
					.map(e -> e.getDate())
					.forEach(dates::add);
			dates.forEach(System.out::println);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testListShopId() {
		try {
			List<Data> lists = ReadFileExcel.getALL(path);
			Set<String> dates = new LinkedHashSet<>();
			lists.stream()
					.map(e -> e.getDate())
					.forEach(dates::add);
			for(String date : dates) {
				Set<Integer> shopId = CollectUtill.toShopId(lists, date);
				for(Integer shop : shopId) {
					System.out.println(date + " => " + shop);
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testListShiftBig() {
		try {
			List<Data> lists = ReadFileExcel.getALL(path);
			Set<String> dates = new LinkedHashSet<>();
			lists.stream()
					.map(e -> e.getDate())
					.forEach(dates::add);
			for(String date : dates) {
				
				Set<Integer> shopId = CollectUtill.toShopId(lists, date);
				
				for(Integer shop : shopId) {
					
					Set<Integer> shiftBigId = CollectUtill.toShiftBigId(lists, date, shop);
					
					for(Integer shiftBig : shiftBigId) {
						
						System.out.println(date + " => " + shop + " => " + shiftBig);
						
					}
					
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testListShiftSmall() {
		try {
			List<Data> lists = ReadFileExcel.getALL(path);
			Set<String> dates = new LinkedHashSet<>();
			lists.stream()
					.map(e -> e.getDate())
					.forEach(dates::add);
			for(String date : dates) {
				Set<Integer> shopId = CollectUtill.toShopId(lists, date);
				for(Integer shop : shopId) {
					
					Set<Integer> shiftBigId = CollectUtill.toShiftBigId(lists, date, shop);
					
					for(Integer shiftBig : shiftBigId) {
						
						Set<Integer> shiftSmallId = CollectUtill.toShiftSmallId(lists, date, shop, shiftBig);
						
						for(Integer shiftSmall : shiftSmallId) {
							System.out.println(date + " => " + shop + " => " + shiftBig + " => " + shiftSmall);
						}
						
					}
					
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
