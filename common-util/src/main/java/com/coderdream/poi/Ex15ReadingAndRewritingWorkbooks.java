package com.coderdream.poi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * http://poi.apache.org/spreadsheet/quick-guide.html#Reading+and+Rewriting+Workbooks
 *
 */
public class Ex15ReadingAndRewritingWorkbooks {

	public void readingAndRewritingWorkbooks(String filename) {
		Workbook wb = null;
		InputStream inp = null;
		try {
			inp = new FileInputStream(filename);
			wb = WorkbookFactory.create(inp);

			Sheet sheet = wb.getSheetAt(0);
			Row row = sheet.getRow(2);
			Cell cell = row.getCell(3);
			if (cell == null)
				cell = row.createCell(3);
			cell.setCellType(CellType.STRING);
			cell.setCellValue("a test");

			// Write the output to a file
			FileOutputStream fileOut = new FileOutputStream(filename);
			wb.write(fileOut);
			fileOut.close();
			inp.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
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
