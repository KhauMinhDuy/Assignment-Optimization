package com.khauminhduy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import com.khauminhduy.model.Data;
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

}
