package com.coderdream.poi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * http://poi.apache.org/spreadsheet/quick-guide.html#Fills+and+colors
 *
 */
public class Ex11FillsAndColors {

	public void fillsAndColors(String filename) {
		XSSFWorkbook wb = null;
		FileOutputStream fileOut = null;
		try {
			wb = new XSSFWorkbook();
			Sheet sheet = wb.createSheet("new sheet");

			// Create a row and put some cells in it. Rows are 0 based.
			Row row = sheet.createRow((short) 1);

			// Aqua background
			CellStyle style = wb.createCellStyle();
			style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
			style.setFillPattern(FillPatternType.BIG_SPOTS);
			Cell cell = row.createCell((short) 1);
			cell.setCellValue("X");
			cell.setCellStyle(style);

			// Orange "foreground", foreground being the fill foreground not the
			// font color.
			style = wb.createCellStyle();
			style.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			cell = row.createCell((short) 2);
			cell.setCellValue("X");
			cell.setCellStyle(style);

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
