package com.coderdream.poi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.ss.extractor.ExcelExtractor;
import org.apache.poi.xssf.extractor.XSSFExcelExtractor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * http://poi.apache.org/spreadsheet/quick-guide.html#Text+Extraction
 *
 */
public class Ex10TextExtraction {

	public void getTextExtraction(String filename) {
		XSSFWorkbook wb = null;
		InputStream inp = null;
		try {
			inp = new FileInputStream(filename);
			wb = new XSSFWorkbook(inp);
			// A text extractor for Excel files.
			// Returns the textual content of the file, suitable for indexing by
			// something like Lucene,
			// but not really intended for display to the user.
			// 用来获得整个excel文件的内容，表示为字符串
			@SuppressWarnings("resource")
			ExcelExtractor extractor = new XSSFExcelExtractor(wb);
			// 字符串所包含的类型，详见API
			extractor.setIncludeSheetNames(true);
			extractor.setFormulasNotResults(false);
			extractor.setIncludeCellComments(true);
			// 获得字符串形式
			String text = extractor.getText();
			System.out.println(text);

			inp.close();
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
