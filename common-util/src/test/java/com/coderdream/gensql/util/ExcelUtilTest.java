package com.coderdream.gensql.util;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.b510.excel.common.Common;
import com.coderdream.gensql.bean.TableStructure;
import com.coderdream.util.Constants;
import com.coderdream.util.ExcelUtil;

public class ExcelUtilTest {

	private static final Logger logger = LoggerFactory.getLogger(ExcelUtilTest.class);

	private String fileFolder;

	private String dataFileName;

	@Before
	public void setUp() throws Exception {
		fileFolder = getClass().getResource("../../../../").getFile().toString();
		dataFileName = Constants.DATA_FILE_NAME;
	}

	@Test
	public void testReadExcel() {
		logger.debug(Common.PROCESSING + fileFolder);
		String fileName = "TableStructure.xlsx";
		try {
			List<TableStructure> list = ExcelUtil.readXlsx(fileFolder + fileName);
			for (TableStructure tableStructure : list) {
				System.out.println(tableStructure.getTableName() + "\t" + tableStructure.getColumnName() + "\t"
						+ tableStructure.getType() + "\t");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testReadData_01() {
		logger.debug(Common.PROCESSING + fileFolder);
		String sheetName = "T_Users";
		String path = fileFolder + dataFileName;
		try {
			List<String[]> arrayList = ExcelUtil.readAllData(path, sheetName);
			for (String[] arrayStr : arrayList) {
				for (int i = 0; i < arrayStr.length; i++) {
					String string = arrayStr[i];
					System.out.print(string + "\t");
				}
				// System.out.println();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testReadData_02() {
		logger.debug(Common.PROCESSING + fileFolder);
		dataFileName = "Data7.xlsx";
		String sheetName = "PDRC_StaffManage";
		String path = fileFolder + dataFileName;
		try {
			List<String[]> arrayList = ExcelUtil.readAllData(path, sheetName);
			for (String[] arrayStr : arrayList) {
				for (int i = 0; i < arrayStr.length; i++) {
					String string = arrayStr[i];
					System.out.print(string + "\t");
				}
				// System.out.println();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testReadData_03() {
		logger.debug(Common.PROCESSING + fileFolder);
		String sheetName = "PDRC_TM_SALARY";
		String path = fileFolder + dataFileName;
		try {
			List<String[]> arrayList = ExcelUtil.readAllData(path, sheetName);
			for (String[] arrayStr : arrayList) {
				for (int i = 0; i < arrayStr.length; i++) {
					String string = arrayStr[i];
					System.out.print(string + "\t");
				}
				// System.out.println();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testReadData_04() {
		logger.debug(Common.PROCESSING + fileFolder);
		String sheetName = "ISBG_HumanMap";
		String path = fileFolder + dataFileName;
		try {
			List<String[]> arrayList = ExcelUtil.readAllData(path, sheetName);
			for (String[] arrayStr : arrayList) {
				for (int i = 0; i < arrayStr.length; i++) {
					String string = arrayStr[i];
					System.out.print("arrayStr[" + i + "]: " + string + "\t");
				}
				// System.out.println();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testReadData_05() {
		logger.debug(Common.PROCESSING + fileFolder);
		String sheetName = "PDRC_TM";
		String path = fileFolder + dataFileName;
		try {
			List<String[]> arrayList = ExcelUtil.readAllData(path, sheetName);
			for (String[] arrayStr : arrayList) {
				for (int i = 0; i < arrayStr.length; i++) {
					String string = arrayStr[i];
					System.out.print("arrayStr[" + i + "]: " + string + "\t");
				}
				System.out.println();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testReadingAndRewritingIsbgProjectInfo_01() {
		logger.debug(Common.PROCESSING + fileFolder);
		String path = fileFolder + dataFileName;
		ExcelUtil.readingAndRewritingIsbgProjectInfo(path);
	}

}
