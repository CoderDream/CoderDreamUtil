package com.b510.excel.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.b510.excel.common.Common;
import com.b510.excel.vo.Student;
import com.coderdream.readfolder.bean.FileInfo;

public class ExcelUtil {

	private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

	public void writeExcel(List<Student> list, String path) throws Exception {
		if (list == null) {
			return;
		} else if (path == null || Common.EMPTY.equals(path)) {
			return;
		} else {
			String postfix = Util.getPostfix(path);
			if (!Common.EMPTY.equals(postfix)) {
				if (Common.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
					writeXls(list, path);
				} else if (Common.OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
					writeXlsx(list, path);
				}
			} else {
				System.out.println(path + Common.NOT_EXCEL_FILE);
			}
		}
	}

	/**
	 * read the Excel file
	 * 
	 * @param path
	 *            the path of the Excel file
	 * @return
	 * @throws IOException
	 */
	public List<Student> readExcel(String path) throws IOException {
		if (path == null || Common.EMPTY.equals(path)) {
			return null;
		} else {
			String postfix = Util.getPostfix(path);
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
	public List<Student> readXlsx(String path) throws IOException {
		System.out.println(Common.PROCESSING + path);
		InputStream is = new FileInputStream(path);
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
		Student student = null;
		List<Student> list = new ArrayList<Student>();
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
					student = new Student();
					XSSFCell no = xssfRow.getCell(0);
					XSSFCell name = xssfRow.getCell(1);
					XSSFCell age = xssfRow.getCell(2);
					XSSFCell score = xssfRow.getCell(3);
					student.setNo(getValue(no));
					student.setName(getValue(name));
					student.setAge(getValue(age));
					student.setScore(Float.valueOf(getValue(score)));
					list.add(student);
				}
			}
		}
		xssfWorkbook.close();
		return list;
	}

	/**
	 * Read the Excel 2003-2007
	 * 
	 * @param path
	 *            the path of the Excel
	 * @return
	 * @throws IOException
	 */
	public List<Student> readXls(String path) throws IOException {
		System.out.println(Common.PROCESSING + path);
		InputStream is = new FileInputStream(path);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		Student student = null;
		List<Student> list = new ArrayList<Student>();
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
					student = new Student();
					HSSFCell no = hssfRow.getCell(0);
					HSSFCell name = hssfRow.getCell(1);
					HSSFCell age = hssfRow.getCell(2);
					HSSFCell score = hssfRow.getCell(3);
					student.setNo(getValue(no));
					student.setName(getValue(name));
					student.setAge(getValue(age));
					student.setScore(Float.valueOf(getValue(score)));
					list.add(student);
				}
			}
		}
		hssfWorkbook.close();
		return list;
	}

	@SuppressWarnings({ "static-access", "deprecation" })
	private String getValue(XSSFCell xssfRow) {
		if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
			return String.valueOf(xssfRow.getBooleanCellValue());
		} else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
			return String.valueOf(xssfRow.getNumericCellValue());
		} else {
			return String.valueOf(xssfRow.getStringCellValue());
		}
	}

	@SuppressWarnings({ "static-access", "deprecation" })
	private String getValue(HSSFCell hssfCell) {
		if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(hssfCell.getBooleanCellValue());
		} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
			return String.valueOf(hssfCell.getNumericCellValue());
		} else {
			return String.valueOf(hssfCell.getStringCellValue());
		}
	}

	public void writeXls(List<Student> list, String path) throws Exception {
		if (list == null) {
			return;
		}
		int countColumnNum = list.size();
		HSSFWorkbook book = new HSSFWorkbook();
		HSSFSheet sheet = book.createSheet("studentSheet");
		// option at first row.
		HSSFRow firstRow = sheet.createRow(0);
		HSSFCell[] firstCells = new HSSFCell[countColumnNum];
		String[] options = { "no", "name", "age", "score" };
		for (int j = 0; j < options.length; j++) {
			System.out.println(j);
			firstCells[j] = firstRow.createCell(j);
			firstCells[j].setCellValue(new HSSFRichTextString(options[j]));
		}
		//
		for (int i = 0; i < countColumnNum; i++) {
			HSSFRow row = sheet.createRow(i + 1);
			Student student = list.get(i);
			for (int column = 0; column < options.length; column++) {
				HSSFCell no = row.createCell(0);
				HSSFCell name = row.createCell(1);
				HSSFCell age = row.createCell(2);
				HSSFCell score = row.createCell(3);
				no.setCellValue(student.getNo());
				name.setCellValue(student.getName());
				age.setCellValue(student.getAge());
				score.setCellValue(student.getScore());
			}
		}
		File file = new File(path);
		OutputStream os = new FileOutputStream(file);
		System.out.println(Common.WRITE_DATA + path);
		book.write(os);
		book.close();
		os.close();
	}

	public void writeXlsx(List<Student> list, String path) throws Exception {
		if (list == null) {
			return;
		}
		// XSSFWorkbook
		int countColumnNum = list.size();
		XSSFWorkbook book = new XSSFWorkbook();
		XSSFSheet sheet = book.createSheet("studentSheet");
		// option at first row.
		XSSFRow firstRow = sheet.createRow(0);
		XSSFCell[] firstCells = new XSSFCell[countColumnNum];
		String[] options = { "no", "name", "age", "score" };
		for (int j = 0; j < options.length; j++) {
			firstCells[j] = firstRow.createCell(j);
			firstCells[j].setCellValue(new XSSFRichTextString(options[j]));
		}
		//
		for (int i = 0; i < countColumnNum; i++) {
			XSSFRow row = sheet.createRow(i + 1);
			Student student = list.get(i);
			for (int column = 0; column < options.length; column++) {
				XSSFCell no = row.createCell(0);
				XSSFCell name = row.createCell(1);
				XSSFCell age = row.createCell(2);
				XSSFCell score = row.createCell(3);
				no.setCellValue(student.getNo());
				name.setCellValue(student.getName());
				age.setCellValue(student.getAge());
				score.setCellValue(student.getScore());
			}
		}
		File file = new File(path);
		OutputStream os = new FileOutputStream(file);
		System.out.println(Common.WRITE_DATA + path);
		book.write(os);
		book.close();
		os.close();
	}

	public static void writeXlsxWithFileInfoList(List<FileInfo> list, String path) {
		logger.info("writeXlsxWithFileInfoList begin");
		if (list == null) {
			return;
		}
		// XSSFWorkbook
		int countColumnNum = list.size();
		XSSFWorkbook book = new XSSFWorkbook();
		XSSFSheet sheet = book.createSheet("studentSheet");
		// option at first row.
		XSSFRow firstRow = sheet.createRow(0);

		String[] options = { "level1", "level2", "level3", "level4", "level5", "name", "filesize", "createDateStr",
				"updateDateStr", "deleteFlag" };
		XSSFCell[] firstCells = new XSSFCell[options.length];

		for (int j = 0; j < options.length; j++) {
			firstCells[j] = firstRow.createCell(j);
			firstCells[j].setCellValue(new XSSFRichTextString(options[j]));
		}
		//
		for (int i = 0; i < countColumnNum; i++) {

			// logger.info("writeXlsxWithFileInfoList countColumnNum: " +
			// countColumnNum);
			XSSFRow row = sheet.createRow(i + 1);
			FileInfo fileInfo = list.get(i);
			for (int column = 0; column < options.length; column++) {
				// logger.info("writeXlsxWithFileInfoList options.length: " +
				// options.length);
				XSSFCell level1 = row.createCell(0);
				XSSFCell level2 = row.createCell(1);
				XSSFCell level3 = row.createCell(2);
				XSSFCell level4 = row.createCell(3);
				XSSFCell level5 = row.createCell(4);
				XSSFCell name = row.createCell(5);
				XSSFCell filesize = row.createCell(6);
				XSSFCell createDateStr = row.createCell(7);
				XSSFCell updateDateStr = row.createCell(8);
				XSSFCell deleteFlag = row.createCell(9);
				level1.setCellValue(fileInfo.getLevel1());
				level2.setCellValue(fileInfo.getLevel2());
				level3.setCellValue(fileInfo.getLevel3());
				level4.setCellValue(fileInfo.getLevel4());
				level5.setCellValue(fileInfo.getLevel5());

				name.setCellValue(fileInfo.getName());
				filesize.setCellValue(fileInfo.getFilesize());
				createDateStr.setCellValue(fileInfo.getCreateDateStr());
				updateDateStr.setCellValue(fileInfo.getUpdateDateStr());
				deleteFlag.setCellValue(fileInfo.getDeleteFlag());
			}
		}
		File file = new File(path);
		OutputStream os;
		try {
			os = new FileOutputStream(file);

			System.out.println(Common.WRITE_DATA + path);
			book.write(os);
			book.close();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		logger.info("writeXlsxWithFileInfoList end");
	}
	
	public static void writeXlsxWithFileNameList(List<String> list, String path) {
		logger.info("writeXlsxWithFileNameList begin");
		if (list == null) {
			return;
		}
		// XSSFWorkbook
		int countColumnNum = list.size();
		XSSFWorkbook book = new XSSFWorkbook();
		XSSFSheet sheet = book.createSheet("studentSheet");
		// option at first row.
		XSSFRow firstRow = sheet.createRow(0);

		String[] options = { "level1", "level2", "level3", "level4", "level5", "name", "filesize", "createDateStr",
				"updateDateStr", "deleteFlag" };
		XSSFCell[] firstCells = new XSSFCell[options.length];

		for (int j = 0; j < options.length; j++) {
			firstCells[j] = firstRow.createCell(j);
			firstCells[j].setCellValue(new XSSFRichTextString(options[j]));
		}
		//
		for (int i = 0; i < countColumnNum; i++) {

			// logger.info("writeXlsxWithFileInfoList countColumnNum: " +
			// countColumnNum);
			XSSFRow row = sheet.createRow(i + 1);
			String filename = list.get(i);
			for (int column = 0; column < options.length; column++) {
				// logger.info("writeXlsxWithFileInfoList options.length: " +
				// options.length);
				
				String[] nameArray = filename.split("\\\\");
				int length = nameArray.length;
				
				XSSFCell level1 = row.createCell(0);
				XSSFCell level2 = row.createCell(1);
				XSSFCell level3 = row.createCell(2);
				XSSFCell level4 = row.createCell(3);
				XSSFCell level5 = row.createCell(4);
				XSSFCell name = row.createCell(5);
				XSSFCell deleteFlag = row.createCell(9);
				deleteFlag.setCellValue("false");
				
				switch (length) {
				case 0:
					break;
				case 1:
					name.setCellValue(nameArray[0]);
					break;
				case 2:
					level1.setCellValue(nameArray[0]);
					name.setCellValue(nameArray[1]);
					break;
				case 3:
					level1.setCellValue(nameArray[0]);
					level2.setCellValue(nameArray[1]);
					name.setCellValue(nameArray[2]);
					break;
				case 4:
					level1.setCellValue(nameArray[0]);
					level2.setCellValue(nameArray[1]);
					level3.setCellValue(nameArray[2]);
					name.setCellValue(nameArray[3]);
					break;
				case 5:
					level1.setCellValue(nameArray[0]);
					level2.setCellValue(nameArray[1]);
					level3.setCellValue(nameArray[2]);
					level4.setCellValue(nameArray[3]);
					name.setCellValue(nameArray[4]);
					break;
				case 6:
					level1.setCellValue(nameArray[0]);
					level2.setCellValue(nameArray[1]);
					level3.setCellValue(nameArray[2]);
					level4.setCellValue(nameArray[3]);
					level5.setCellValue(nameArray[4]);
					name.setCellValue(nameArray[5]);
					break;

				default:
					break;
				}
				if(null== name.getStringCellValue()|| "".equals(name.getStringCellValue().trim())) {
					logger.error("ERROR");
				}
				
			}
		}
		File file = new File(path);
		OutputStream os;
		try {
			os = new FileOutputStream(file);

			System.out.println(Common.WRITE_DATA + path);
			book.write(os);
			book.close();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		logger.info("writeXlsxWithFileNameList end");
	}
}
