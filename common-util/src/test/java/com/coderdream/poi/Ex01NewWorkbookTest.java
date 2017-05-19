package com.coderdream.poi;

import org.junit.Before;
import org.junit.Test;

public class Ex01NewWorkbookTest {

	//private static final Logger logger = LoggerFactory.getLogger(ExampleEventUserModelTest.class);

	private String fileFolder;
	

	@Before
	public void setUp() throws Exception {
		fileFolder = getClass().getResource("../../../").getFile().toString();
	}

	@Test
	public void testCreateNewWorkbookXls() {
		String filename = "workbook.xls";
		String path = fileFolder + filename;
		Ex01NewWorkbook example = new Ex01NewWorkbook();
		example.createNewWorkbookXls(path);
	}
	
	@Test
	public void testCreateNewWorkbookXlsx() {
		String filename = "workbook.xlsx";
		String path = fileFolder + filename;
		Ex01NewWorkbook example = new Ex01NewWorkbook();
		example.createNewWorkbookXlsx(path);
	}

}
