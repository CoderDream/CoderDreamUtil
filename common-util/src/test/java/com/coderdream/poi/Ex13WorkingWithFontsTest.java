package com.coderdream.poi;

import org.junit.Before;
import org.junit.Test;

public class Ex13WorkingWithFontsTest {


	private String fileFolder;

	@Before
	public void setUp() throws Exception {
		fileFolder = getClass().getResource("../../../").getFile().toString();
	}

	@Test
	public void testIterateBlankCells() {
		String filename = "workingWithFonts.xlsx";
		String path = fileFolder + filename;
		Ex13WorkingWithFonts example = new Ex13WorkingWithFonts();
		example.workingWithFonts(path);
	}

}
