package com.coderdream.poi;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * http://poi.apache.org/spreadsheet/quick-guide.html#Working+with+fonts
 *
 */
public class Ex13WorkingWithFonts {

	public void workingWithFonts(String filename) {
		XSSFWorkbook wb = null;
		FileOutputStream fileOut = null;
		try {
			wb = new XSSFWorkbook();
			Sheet sheet = wb.createSheet("new sheet");

			// Create a row and put some cells in it. Rows are 0 based.
		    Row row = sheet.createRow(1);

		    // Create a new font and alter it.
		    Font font = wb.createFont();
		    font.setFontHeightInPoints((short)24);
		    font.setFontName("Courier New");
		    font.setItalic(true);
		    font.setStrikeout(true);

		    // Fonts are set into a style so create a new one to use.
		    CellStyle style = wb.createCellStyle();
		    style.setFont(font);

		    // Create a cell and put a value in it.
		    Cell cell = row.createCell(1);
		    cell.setCellValue("This is a test of fonts");
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
