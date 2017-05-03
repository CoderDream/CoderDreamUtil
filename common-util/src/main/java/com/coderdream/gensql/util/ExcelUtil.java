package com.coderdream.gensql.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.b510.excel.common.Common;
import com.coderdream.gensql.bean.TableStructure;

public class ExcelUtil {

	private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

	/**
	 * read the Excel file
	 * 
	 * @param path
	 *            the path of the Excel file
	 * @return
	 * @throws IOException
	 */
	public static List<TableStructure> readExcel(String path) throws IOException {
		if (path == null || Common.EMPTY.equals(path)) {
			return null;
		} else {
			String postfix = ExcelUtil.getPostfix(path);
			if (!Common.EMPTY.equals(postfix)) {
				if (Common.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
					return readXls(path);
				} else if (Common.OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
					return readXlsx(path);
				}
			} else {
				System.out.println(path + Common.NOT_EXCEL_FILE);
			}
		}
		return null;
	}

	/**
	 * Read the Excel 2010
	 * 
	 * @param path
	 *            the path of the excel file
	 * @return
	 * @throws IOException
	 */
	public static List<TableStructure> readXlsx(String path) throws IOException {
		logger.debug(Common.PROCESSING + path);
		InputStream is = new FileInputStream(path);
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
		TableStructure tableStructure = null;
		List<TableStructure> list = new ArrayList<TableStructure>();
		// Read the Sheet
		for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
			XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
			if (xssfSheet == null) {
				continue;
			}
			// Read the Row
			for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
				XSSFRow xssfRow = xssfSheet.getRow(rowNum);
				if (xssfRow != null) {
					tableStructure = new TableStructure();
					// /** 表名 */
					// String tableName;
					// /** 字段名 */
					// String columnName;
					// /** 类型 */
					// String type;

					/** 表名 */
					XSSFCell tableName = xssfRow.getCell(0);
					/** 字段名 */
					XSSFCell columnName = xssfRow.getCell(2);
					/** 类型 */
					XSSFCell type = xssfRow.getCell(5);
					/** 允许空 */
					XSSFCell nullFlag = xssfRow.getCell(9);

					tableStructure.setTableName(getValue(tableName));
					tableStructure.setColumnName(getValue(columnName));
					tableStructure.setType(getValue(type));
					tableStructure.setNullFlag(getValue(nullFlag));
					list.add(tableStructure);
				}
			}
		}
		xssfWorkbook.close();
		return list;
	}

	/**
	 * Read the Excel 2010
	 * 
	 * @param path
	 *            the path of the excel file
	 * @return
	 * @throws IOException
	 */
	public static List<String[]> readData(String path, String sheetName) throws IOException {
		logger.debug(Common.PROCESSING + path);

		InputStream is = new FileInputStream(path);
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
		List<String[]> arrayList = new ArrayList<String[]>();
		// Read the Sheet
		XSSFSheet xssfSheet = xssfWorkbook.getSheet(sheetName);
		if (xssfSheet == null) {
			xssfWorkbook.close();
			return null;
		}
		// Read the Row
		for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
			XSSFRow xssfRow = xssfSheet.getRow(rowNum);
			if (xssfRow != null) {
				short minColIx = xssfRow.getFirstCellNum();
				short maxColIx = xssfRow.getLastCellNum();
				int size = maxColIx - minColIx;
				String[] strArray = new String[size];

				for (short colIx = minColIx; colIx < maxColIx; colIx++) {
					XSSFCell cell = xssfRow.getCell(colIx);
					if (cell == null) {
						continue;
					}

					strArray[colIx] = getValue(xssfRow.getCell(colIx));
					// ... do something with cell
				}

				arrayList.add(strArray);
			}
		}
		xssfWorkbook.close();
		return arrayList;
	}

	/**
	 * Read the Excel 2003-2007
	 * 
	 * @param path
	 *            the path of the Excel
	 * @return
	 * @throws IOException
	 */
	public static List<TableStructure> readXls(String path) throws IOException {
		System.out.println(Common.PROCESSING + path);
		InputStream is = new FileInputStream(path);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		TableStructure tableStructure = null;
		List<TableStructure> list = new ArrayList<TableStructure>();
		// Read the Sheet
		for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
			if (hssfSheet == null) {
				continue;
			}
			// Read the Row
			for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				if (hssfRow != null) {
					tableStructure = new TableStructure();
					// HSSFCell no = hssfRow.getCell(0);
					// HSSFCell name = hssfRow.getCell(1);
					// HSSFCell age = hssfRow.getCell(2);
					// HSSFCell score = hssfRow.getCell(3);
					// tableStructure.setNo(getValue(no));
					// tableStructure.setName(getValue(name));
					// tableStructure.setAge(getValue(age));
					// tableStructure.setScore(Float.valueOf(getValue(score)));
					list.add(tableStructure);
				}
			}
		}
		hssfWorkbook.close();
		return list;
	}

	@SuppressWarnings({ "static-access", "deprecation" })
	private static String getValue(XSSFCell cell) {
		if (null != cell) {
			if (cell.getCellType() == XSSFCell.CELL_TYPE_BOOLEAN) {
				return String.valueOf(cell.getBooleanCellValue());
			} else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
				String cellValue = "";
				if (HSSFDateUtil.isCellDateFormatted(cell)) { // 判断是日期类型
					SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
					Date dt = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());// 获取成DATE类型
					cellValue = dateformat.format(dt);
				} else {
					DecimalFormat df = new DecimalFormat("0.00");
					cellValue = df.format(cell.getNumericCellValue());
					if (cellValue.indexOf(".") > 0) {
						// 正则表达
						cellValue = cellValue.replaceAll("0+?$", "");// 去掉后面无用的零
						cellValue = cellValue.replaceAll("[.]$", "");// 如小数点后面全是零则去掉小数点
					}
				}

				return cellValue;
			} else {
				return String.valueOf(cell.getStringCellValue().trim());
			}
		} else {
			return "";
		}
	}

	// @SuppressWarnings({ "static-access", "deprecation" })
	// private static String getValue(HSSFCell hssfCell) {
	// if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
	// return String.valueOf(hssfCell.getBooleanCellValue());
	// } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
	// return String.valueOf(hssfCell.getNumericCellValue());
	// } else {
	// return String.valueOf(hssfCell.getStringCellValue());
	// }
	// }

	/**
	 * get postfix of the path
	 * 
	 * @param path
	 * @return
	 */
	public static String getPostfix(String path) {
		if (path == null || Common.EMPTY.equals(path.trim())) {
			return Common.EMPTY;
		}
		if (path.contains(Common.POINT)) {
			return path.substring(path.lastIndexOf(Common.POINT) + 1, path.length());
		}
		return Common.EMPTY;
	}
}
