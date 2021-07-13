package com.khauminhduy.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.khauminhduy.model.Data;

public class ReadFileExcel {

	public static XSSFWorkbook open(String path) throws IOException {
		return new XSSFWorkbook(path);
	}

	public static List<Data> getALL(String path) throws IOException {
		List<Data> datas = new ArrayList<>();
		XSSFWorkbook workbook = open(path);
		XSSFSheet sheet = workbook.getSheetAt(0);

		Iterator<Row> rowIterator = sheet.rowIterator();
		rowIterator.forEachRemaining(e -> {
			Data data = new Data();
			if (e.getRowNum() != 0) {
				e.cellIterator().forEachRemaining(cell -> {
					switch (cell.getColumnIndex()) {
					case 0:
						data.setDate(cell.toString());
						break;
					case 1:
						data.setShopId(cell.toString());
						break;
					case 2:
						if(!cell.toString().equals("")) {
							data.setShiftBig((int) Float.parseFloat(cell.toString()));
						} else {
							data.setShiftBig(0);
						}
						break;
					case 3:
						if (!cell.toString().equals("")) {
							data.setShiftSmall((int) Float.parseFloat(cell.toString()));
						} else {
							data.setShiftSmall(0);
						}
						break;
					case 4:
						data.setShiftName(cell.toString());
						break;
					case 5:
						if(!cell.toString().equals("")) {
							data.setHeadCount((int) Float.parseFloat(cell.toString()));
						} else {
							data.setHeadCount(0);
						}
						break;
					case 6:
						if(!cell.toString().equals("")) {
							data.setTimeShiftSmall((int) Float.parseFloat(cell.toString()));
						} else {
							data.setTimeShiftSmall(0);
						}
						break;
					case 7:
						if(!cell.toString().equals("")) {
							data.setTimeShiftBig((int) Float.parseFloat(cell.toString()));
						} else {
							data.setTimeShiftBig(0);
						}
						break;
					case 8:
						data.setJobName(cell.toString());
						break;
					case 9:
						break;
					case 10:
						break;
					case 11:
						if(!cell.toString().equals("")) {
							data.setListWorkers((int) Float.parseFloat(cell.toString()));
						} else {
							data.setListWorkers(0);
						}
						break;
					case 12:
						if(!cell.toString().equals("")) {
							data.setMinutesFinishWork((int) Float.parseFloat(cell.toString()));
						} else {
							data.setMinutesFinishWork(0);
						}
						break;

					}

				});
				datas.add(data);
			}

		});
		return datas;

	}
}
