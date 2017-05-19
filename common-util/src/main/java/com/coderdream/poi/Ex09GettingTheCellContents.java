package com.coderdream.poi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * http://poi.apache.org/spreadsheet/quick-guide.html#Getting+the+cell+contents
 *
 */
public class Ex09GettingTheCellContents {

	public void gettingTheCellContents(String filename) {
		Workbook wb = null;
//		FileInputStream fileIn = null;
		try {
			File file = new File(filename);

			// 给该文件加锁
			// RandomAccessFile fis = new RandomAccessFile(file, "rw");
			// FileChannel fcin = fis.getChannel();
			// FileLock flin = null;
			// while (true) {
			// try {
			// flin = fcin.tryLock();
			// break;
			// } catch (Exception e) {
			// System.out.println("有其他线程正在操作该文件，当前线程休眠1000毫秒");
			// try {
			// Thread.sleep(1000);
			// } catch (InterruptedException e1) {
			// // TODO Auto-generated catch block
			// e1.printStackTrace();
			// }
			// }
			// }

			// File file = new File(filePath);
			// RandomAccessFile raf = new RandomAccessFile(file, "rw");
			// FileChannel fc = raf.getChannel();
			// FileLock fl = fc.tryLock();
			//
			// if (fl.isValid()) {
			// System.out.println("Get the lock successed!");
			//
			// // 测试读线程
			// //ReadFile rf = new ReadFile(file);
			// //rf.start();
			// System.out.println("###################");
			//
			// // 测试写线程
			// // WriteFile wf = new WriteFile(file,"This is a test！----幻影");
			// // wf.start();
			//
			// // Write the output to a file
			//// fileIn = new FileInputStream(raf);
			//
			//
			//
			// }

			try {
				wb = new XSSFWorkbook(file);
			} catch (InvalidFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			DataFormatter formatter = new DataFormatter();
			Sheet sheet1 = wb.getSheetAt(0);
			for (Row row : sheet1) {
				for (Cell cell : row) {
					CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
					System.out.print(cellRef.formatAsString());
					System.out.print(" - ");

					// get the text that appears in the cell by getting the cell
					// value and applying any data formats (Date, 0.00, 1.23e9,
					// $1.23, etc)
					String text = formatter.formatCellValue(cell);
					System.out.println(text);

					// Alternatively, get the value and format it yourself
					switch (cell.getCellTypeEnum()) {
					case STRING:
						System.out.println("StringCellValue\t" + cell.getRichStringCellValue().getString());
						break;
					case NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							System.out.println("DateCellValue\t" + cell.getDateCellValue());
						} else {
							System.out.println("NumericCellValue\t" + cell.getNumericCellValue());
						}
						break;
					case BOOLEAN:
						System.out.println("BooleanCellValue\t" + cell.getBooleanCellValue());
						break;
					case FORMULA:
						CellType cy = cell.getCachedFormulaResultTypeEnum();
						System.out.println("cy: " + cy);
						if (CellType.NUMERIC == cy) {
							System.out.println("FormulaCellValue\t" + String.valueOf(cell.getNumericCellValue()));
						}

						if (CellType.STRING == cy) {
							String res = cell.getStringCellValue();
							System.out.println("FormulaCellValue\t" + res);
						}

						System.out.println("FormulaCellValue\t" + cell.getCellFormula());
						break;
					case BLANK:
						System.out.println();
						break;
					default:
						System.out.println();
					}
				}
			}

			// fl.release();
			// System.out.println("release the lock!");
			// raf.close();

			// fileIn.close();
			wb.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
