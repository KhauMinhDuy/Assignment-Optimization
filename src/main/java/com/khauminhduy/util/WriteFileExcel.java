package com.khauminhduy.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.khauminhduy.model.DataOutput;

public class WriteFileExcel {

	private WriteFileExcel() {}
	
	public static void writeAll(List<DataOutput> lists ,String path) throws InvalidFormatException, IOException {
		String shopId = String.valueOf(lists.get(0).getShopId());
		
		try (XSSFWorkbook workbook = new XSSFWorkbook();
				OutputStream outputStream = new FileOutputStream("src/main/resources/dataoutput.xlsx");) {
			
			XSSFSheet sheet = workbook.createSheet(shopId);
			XSSFRow rowTitle = sheet.createRow(0);
			XSSFCell cell1 = rowTitle.createCell(0);
			cell1.setCellValue("Date");
			
			XSSFCell cell2 = rowTitle.createCell(1);
			cell2.setCellValue("Shop Id");
			XSSFCell cell3 = rowTitle.createCell(2);
			cell3.setCellValue("Ca Lon");
			XSSFCell cell4 = rowTitle.createCell(3);
			cell4.setCellValue("Nhan Vien");
			XSSFCell cell5 = rowTitle.createCell(4);
			cell5.setCellValue("IsManager");
			XSSFCell cell6 = rowTitle.createCell(5);
			cell6.setCellValue("IsCashier");
			XSSFCell cell7 = rowTitle.createCell(6);
			cell7.setCellValue("Ca Nho");
			XSSFCell cell8 = rowTitle.createCell(7);
			cell8.setCellValue("Ten CV");
			
			XSSFCell cell9 = rowTitle.createCell(8);
			cell9.setCellValue("Total Minute");
			XSSFCell cell10 = rowTitle.createCell(9);
			cell10.setCellValue("HourStart");
			XSSFCell cell11 = rowTitle.createCell(10);
			cell11.setCellValue("MinuteStart");
			sheet.setColumnWidth(0, 3000);
			sheet.setColumnWidth(1, 3000);
			sheet.setColumnWidth(2, 3000);
			sheet.setColumnWidth(3, 3000);
			sheet.setColumnWidth(4, 3000);
			sheet.setColumnWidth(5, 3000);
			sheet.setColumnWidth(6, 3000);
			sheet.setColumnWidth(7, 30000);
			sheet.setColumnWidth(8, 3000);
			sheet.setColumnWidth(9, 3000);
			sheet.setColumnWidth(10, 3000);
			sheet.setColumnWidth(11, 3000);
			
			
			
			int row = 1;
			for(DataOutput output : lists) {
				XSSFRow rowData = sheet.createRow(row++);
				XSSFCell c1 = rowData.createCell(0);
				c1.setCellValue(output.getDate());
				XSSFCell c2 = rowData.createCell(1);
				c2.setCellValue(output.getShopId());
				XSSFCell c3 = rowData.createCell(2);
				c3.setCellValue(output.getShiftBig());
				XSSFCell c4 = rowData.createCell(3);
				c4.setCellValue(output.getWorkerName());
				XSSFCell c5 = rowData.createCell(4);
				c5.setCellValue(output.isManager());
				XSSFCell c6 = rowData.createCell(5);
				c6.setCellValue(output.isCashier());
				XSSFCell c7 = rowData.createCell(6);
				c7.setCellValue(output.getShiftSmall());
				XSSFCell c8 = rowData.createCell(7);
				c8.setCellValue(output.getJobName());
				XSSFCell c9 = rowData.createCell(8);
				c9.setCellValue(output.getTotalMinute());
				XSSFCell c10 = rowData.createCell(9);
				c10.setCellValue(output.getHourStart());
				XSSFCell c11 = rowData.createCell(10);
				c11.setCellValue(output.getMinuteStart());
			}
			
			
			workbook.write(outputStream);
		}
		
		
	}
	
	
	
}
