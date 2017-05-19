package com.coderdream.poi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * http://poi.apache.org/spreadsheet/quick-guide.html#Demonstrates+various+alignment+options
 *
 */
public class Ex06DemonstratesVariousAlignmentOptions {

	public void demonstratesVariousAlignmentOptions(String filename) {
		Workbook wb = new XSSFWorkbook();
		FileOutputStream fileOut = null;
		try {
			Sheet sheet = wb.createSheet("new sheet");

			Row row = sheet.createRow((short) 2);
	        row.setHeightInPoints(30);

	        createCell(wb, row, (short) 0, HorizontalAlignment.CENTER, VerticalAlignment.BOTTOM);
	        createCell(wb, row, (short) 1, HorizontalAlignment.CENTER_SELECTION, VerticalAlignment.BOTTOM);
	        createCell(wb, row, (short) 2, HorizontalAlignment.FILL, VerticalAlignment.CENTER);
	        createCell(wb, row, (short) 3, HorizontalAlignment.GENERAL, VerticalAlignment.CENTER);
	        createCell(wb, row, (short) 4, HorizontalAlignment.JUSTIFY, VerticalAlignment.JUSTIFY);
	        createCell(wb, row, (short) 5, HorizontalAlignment.LEFT, VerticalAlignment.TOP);
	        createCell(wb, row, (short) 6, HorizontalAlignment.RIGHT, VerticalAlignment.TOP);

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
	
	/**
     * Creates a cell and aligns it a certain way.
     *
     * @param wb     the workbook
     * @param row    the row to create the cell in
     * @param column the column number to create the cell in
     * @param halign the horizontal alignment for the cell.
     */
    private static void createCell(Workbook wb, Row row, short column, HorizontalAlignment halign, VerticalAlignment valign) {
        Cell cell = row.createCell(column);
        cell.setCellValue("Align It");
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(halign);
        cellStyle.setVerticalAlignment(valign);
        cell.setCellStyle(cellStyle);
    }
}
