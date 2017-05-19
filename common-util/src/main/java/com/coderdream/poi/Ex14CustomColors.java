package com.coderdream.poi;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * http://poi.apache.org/spreadsheet/quick-guide.html#Custom+colors
 *
 */
public class Ex14CustomColors {

	public void customColors(String filename) {
		XSSFWorkbook wb = null;
		FileOutputStream fileOut = null;
		try {
			wb = new XSSFWorkbook();
			XSSFSheet sheet = wb.createSheet("new sheet");

			// Create a row and put some cells in it. Rows are 0 based.
		    //Row row = sheet.createRow(1);

		    XSSFRow row = sheet.createRow(0);
		    XSSFCell cell = row.createCell( 0);
		    cell.setCellValue("custom XSSF colors");

		    XSSFCellStyle style = wb.createCellStyle();
		    style.setFillForegroundColor(new XSSFColor(new java.awt.Color(128, 0, 128)));
		    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		    
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
