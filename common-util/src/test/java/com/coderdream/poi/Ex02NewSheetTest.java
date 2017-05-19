package com.coderdream.poi;

import org.junit.Before;
import org.junit.Test;

public class Ex02NewSheetTest {

	private String fileFolder;

	@Before
	public void setUp() throws Exception {
		fileFolder = getClass().getResource("../../../").getFile().toString();
	}

	@Test
	public void testCreateNewSheet() {
		String filename = "workbook.xlsx";
		String path = fileFolder + filename;
		Ex02NewSheet example = new Ex02NewSheet();
		example.createNewSheet(path);
	}

}
