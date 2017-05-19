package com.coderdream.poi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * http://poi.apache.org/spreadsheet/quick-guide.html#Merging+cells
 *
 */
public class Ex12MergingCells {

	public void mergingCells(String filename) {
		XSSFWorkbook wb = null;
		FileOutputStream fileOut = null;
		try {
			wb = new XSSFWorkbook();
			Sheet sheet = wb.createSheet("new sheet");

			Row row = sheet.createRow((short) 1);
		    Cell cell = row.createCell((short) 1);
		    cell.setCellValue("This is a test of merging");

		    sheet.addMergedRegion(new CellRangeAddress(
		            1, //first row (0-based)
		            1, //last row  (0-based)
		            1, //first column (0-based)
		            2  //last column  (0-based)
		    ));

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
