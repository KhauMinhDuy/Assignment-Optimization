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

	public static List<Data> getAllLines(String path) throws IOException {
		List<Data> datas = new ArrayList<>();
		XSSFWorkbook workbook = open(path);
		XSSFSheet sheet = workbook.getSheetAt(0);

		sheet.rowIterator().forEachRemaining(e -> {
			Data data = new Data();
			if (e.getRowNum() != 0) {
				e.cellIterator().forEachRemaining(cell -> {
					switch (cell.getColumnIndex()) {
					case 0:
						data.setDate(cell.toString());
						break;
					case 1:
						if (!cell.toString().equals("")) {
							data.setShopId((int) Float.parseFloat(cell.toString()));
						} else {
							data.setShopId(null);
						}
						break;
					case 2:
						if (!cell.toString().equals("")) {
							data.setShiftBig((int) Float.parseFloat(cell.toString()));
						} else {
							data.setShiftBig(null);
						}
						break;
					case 3:
						if (!cell.toString().equals("")) {
							data.setShiftSmall((int) Float.parseFloat(cell.toString()));
						} else {
							data.setShiftSmall(null);
						}
						break;
					case 4:
						data.setShiftName(cell.toString());
						break;
					case 5:
						if (!cell.toString().equals("")) {
							data.setHeadCount((int) Float.parseFloat(cell.toString()));
						} else {
							data.setHeadCount(null);
						}
						break;
					case 6:
						if (!cell.toString().equals("")) {
							data.setTimeShiftSmall((int) Float.parseFloat(cell.toString()));
						} else {
							data.setTimeShiftSmall(0);
						}
						break;
					case 7:
						if (!cell.toString().equals("")) {
							data.setTimeShiftBig((int) Float.parseFloat(cell.toString()));
						} else {
							data.setTimeShiftBig(0);
						}
						break;
					case 8:
						data.setJobName(cell.toString());
						break;
					case 9:
						if(!cell.toString().equals("")) {
							data.setTypeWork((int) Float.parseFloat(cell.toString()));
						} else {
							data.setTypeWork(null);
						}
						break;
					case 10:
						if(!cell.toString().equals("")) {
							data.setPropertiesWork((int) Float.parseFloat(cell.toString()));
						} else {
							data.setPropertiesWork(null);
						}
						break;
					case 11:
						if (!cell.toString().equals("")) {
							data.setListWorkers((int) Float.parseFloat(cell.toString()));
						} else {
							data.setListWorkers(0);
						}
						break;
					case 12:
						if (!cell.toString().equals("")) {
							data.setMinutesFinishWork((int) Float.parseFloat(cell.toString()));
						} else {
							data.setMinutesFinishWork(0);
						}
						break;
					case 13:
						if(!cell.toString().equals("")) {
							data.setHourStart((int) Float.parseFloat(cell.toString()));
						} else {
							data.setHourStart(null);
						}
						break;
					case 14:
						if(!cell.toString().equals("")) {
							data.setMinuteStart((int) Float.parseFloat(cell.toString()));
						} else {
							data.setMinuteStart(null);
						}
						break;
					case 15:
						if(!cell.toString().equals("")) {
							data.setHourEnd((int) Float.parseFloat(cell.toString()));
						} else {
							data.setHourEnd(null);
						}
						break;
					case 16:
						if(!cell.toString().equals("")) {
							data.setMinuteEnd((int) Float.parseFloat(cell.toString()));
						} else {
							data.setMinuteEnd(null);
						}
						break;

					}

				});
				datas.add(data);
			}

		});
		workbook.close();
		return datas;

	}
}
