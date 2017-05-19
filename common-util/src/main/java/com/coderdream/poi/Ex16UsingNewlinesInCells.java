package com.coderdream.poi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * http://poi.apache.org/spreadsheet/quick-guide.html#Using+newlines+in+cells
 *
 */
public class Ex16UsingNewlinesInCells {

	public void usingNewlinesInCells(String filename) {
		Workbook wb = null;
		try {
			wb = new XSSFWorkbook(); 
			Sheet sheet = wb.createSheet();

		    Row row = sheet.createRow(2);
		    Cell cell = row.createCell(2);
		    cell.setCellValue("Use \n with word wrap on to create a new line");

		    //to enable newlines you need set a cell styles with wrap=true
		    CellStyle cs = wb.createCellStyle();
		    cs.setWrapText(true);
		    cell.setCellStyle(cs);

		    //increase row height to accomodate two lines of text
		    row.setHeightInPoints((2*sheet.getDefaultRowHeightInPoints()));

		    //adjust column width to fit the content
		    sheet.autoSizeColumn((short)2);

			// Write the output to a file
			FileOutputStream fileOut = new FileOutputStream(filename);
			wb.write(fileOut);
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
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
