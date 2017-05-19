package com.coderdream.poi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * http://poi.apache.org/spreadsheet/quick-guide.html#Working+with+borders
 *
 */
public class Ex07WorkingWithBorders {

	public void workingWithBorders(String filename) {
		Workbook wb = new XSSFWorkbook();
		FileOutputStream fileOut = null;
		try {
			Sheet sheet = wb.createSheet("new sheet");

			// Create a row and put some cells in it. Rows are 0 based.
		    Row row = sheet.createRow(1);

		    // Create a cell and put a value in it.
		    Cell cell = row.createCell(1);
		    cell.setCellValue(4);

		    // Style the cell with borders all around.
		    CellStyle style = wb.createCellStyle();
		    style.setBorderBottom(BorderStyle.THIN);
		    style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		    style.setBorderLeft(BorderStyle.THIN);
		    style.setLeftBorderColor(IndexedColors.GREEN.getIndex());
		    style.setBorderRight(BorderStyle.THIN);
		    style.setRightBorderColor(IndexedColors.BLUE.getIndex());
		    style.setBorderTop(BorderStyle.MEDIUM_DASHED);
		    style.setTopBorderColor(IndexedColors.BLACK.getIndex());
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
