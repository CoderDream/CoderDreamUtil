package com.coderdream.poi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * http://poi.apache.org/spreadsheet/quick-guide.html#Iterate+over+cells%2C+with+control+of+missing+%2F+blank+cells
 *
 */
public class Ex08IterateBlankCells {

	public void iterateBlankCells(String filename) {
		Workbook wb = null;
		FileInputStream fileOut = null;
		try {
			// Write the output to a file
			fileOut = new FileInputStream(filename);
			wb = new XSSFWorkbook(fileOut);

			Sheet sheet = wb.getSheetAt(0);

			// Decide which rows to process
			int rowStart = Math.min(15, sheet.getFirstRowNum());
			int rowEnd = Math.max(1400, sheet.getLastRowNum());

			for (int rowNum = rowStart; rowNum < rowEnd; rowNum++) {
				Row r = sheet.getRow(rowNum);
				if (r == null) {
					// This whole row is empty
					// Handle it as needed
					continue;
				}
				int MY_MINIMUM_COLUMN_COUNT = 2;
				int lastColumn = Math.max(r.getLastCellNum(), MY_MINIMUM_COLUMN_COUNT);

				for (int cn = 0; cn < lastColumn; cn++) {
					Cell c = r.getCell(cn, MissingCellPolicy.RETURN_BLANK_AS_NULL);
					if (c == null) {
						// The spreadsheet is empty in this cell
						System.out.println("c == null");
					} else {
						// Do something useful with the cell's contents
						System.out.println("c != null");
					}
				}
			}

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
