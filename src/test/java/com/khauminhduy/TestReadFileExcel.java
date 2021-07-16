package com.khauminhduy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

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
			assertNotNull(workbook);
			
			int numberOfSheets = workbook.getNumberOfSheets();
			assertEquals(1, numberOfSheets);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testGetALL() {
		try {
			List<Data> list = ReadFileExcel.getAllLines(path);
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
			List<Data> lists = ReadFileExcel.getAllLines(path);
			Set<String> dates = CollectUtill.toDates(lists);
			dates.forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testListShopId() {
		try {
			List<Data> lists = ReadFileExcel.getAllLines(path);
			Set<String> dates = CollectUtill.toDates(lists);
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
			List<Data> lists = ReadFileExcel.getAllLines(path);
			Set<String> dates = CollectUtill.toDates(lists);
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
			List<Data> lists = ReadFileExcel.getAllLines(path);
			Set<String> dates = CollectUtill.toDates(lists);
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
	
	@Test
	void testListTypeWork() {
		try {
			List<Data> lists = ReadFileExcel.getAllLines(path);
			Set<String> dates = CollectUtill.toDates(lists);
			for(String date : dates) {
				Set<Integer> shopId = CollectUtill.toShopId(lists, date);
				for(Integer shop : shopId) {
					
					Set<Integer> shiftBigId = CollectUtill.toShiftBigId(lists, date, shop);
					
					for(Integer shiftBig : shiftBigId) {
						
						Set<Integer> shiftSmallId = CollectUtill.toShiftSmallId(lists, date, shop, shiftBig);
						
						for(Integer shiftSmall : shiftSmallId) {
							
							List<Integer> typeWorks = CollectUtill.toTypeWorks(lists, date, shop, shiftBig, shiftSmall);
							
							for(Integer typeWork : typeWorks) {
								System.out.println(date + " => " + shop + " => " + shiftBig + " => " 
										+ shiftSmall + " => " + typeWork);
							}
							
						}
						
					}
					
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testGetDataByDateAndShopID() {
		
		try {
			List<Data> lists = ReadFileExcel.getAllLines(path);
			
			List<Data> dataByDateAndShopId = CollectUtill.getDataByDateAndShopId(lists, "31-May-2021", 6581);
			dataByDateAndShopId.stream()
				.forEach(System.out::println);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testSortTypeWork() {
		
		try {
			List<Data> lists = ReadFileExcel.getAllLines(path);
			
			List<Data> dataByDateAndShopId = CollectUtill.getDataByTypeWork(lists, "31-May-2021", 6581, 1);
			dataByDateAndShopId.stream()
				.filter(e -> e.getShiftSmall() != null ? e.getShiftSmall().equals(11) : false)
				.sorted(Comparator.comparing(Data::getTypeWork))
				.forEach(e -> {
					System.out.println(e.getDate() + " " + e.getShopId() + " " + 
							e.getShiftBig() + " " + e.getShiftSmall() + " " +
							e.getTypeWork());
				});
			
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testFindTypeWork() {
		
		try {
			List<Data> lists = ReadFileExcel.getAllLines(path);
			
			List<Data> dataByDateAndShopId = CollectUtill.getDataByTypeWork(lists, "31-May-2021", 6581, 1);
			
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
