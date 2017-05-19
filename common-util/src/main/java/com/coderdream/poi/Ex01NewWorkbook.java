package com.coderdream.poi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * http://poi.apache.org/spreadsheet/quick-guide.html#NewWorkbook
 * 
 * http://blog.csdn.net/limitstorm/article/details/6192177
 *
 */
public class Ex01NewWorkbook {

	public void createNewWorkbookXls(String filename) {
		Workbook wb = new HSSFWorkbook();
		FileOutputStream fileOut;
		try {
			// fileOut = new FileOutputStream("workbook.xls");
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

	public void createNewWorkbookXlsx(String filename) {
		Workbook wb = new XSSFWorkbook();
		FileOutputStream fileOut;
		try {
			// fileOut = new FileOutputStream("workbook.xlsx");
		    fileOut = new FileOutputStream(filename);
		    wb.write(fileOut);
		    fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != wb) {
					wb.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
