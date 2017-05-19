package com.coderdream.poi;

import org.junit.Before;
import org.junit.Test;

public class Ex14CustomColorsTest {


	private String fileFolder;

	@Before
	public void setUp() throws Exception {
		fileFolder = getClass().getResource("../../../").getFile().toString();
	}

	@Test
	public void testIterateBlankCells() {
		String filename = "customColors.xlsx";
		String path = fileFolder + filename;
		Ex14CustomColors example = new Ex14CustomColors();
		example.customColors(path);
	}

}
