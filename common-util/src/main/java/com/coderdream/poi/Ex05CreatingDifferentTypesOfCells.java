package com.coderdream.poi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * http://poi.apache.org/spreadsheet/quick-guide.html#Working+with+different+types+of+cells
 *
 */
public class Ex05CreatingDifferentTypesOfCells {

	public void creatingDifferentTypesOfCells(String filename) {
		Workbook wb = new XSSFWorkbook();
		FileOutputStream fileOut = null;
		try {
			Sheet sheet = wb.createSheet("new sheet");

			Row row = sheet.createRow((short)2);
		    row.createCell(0).setCellValue(1.1);
		    row.createCell(1).setCellValue(new Date());
		    row.createCell(2).setCellValue(Calendar.getInstance());
		    row.createCell(3).setCellValue("a string");
		    row.createCell(4).setCellValue(true);
		    row.createCell(5).setCellType(CellType.ERROR);

			// Write the output to a file
			fileOut = new FileOutputStream(filename);
			wb.write(fileOut);
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				wb.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
