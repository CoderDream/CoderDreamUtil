package com.coderdream.poi;

import org.junit.Before;
import org.junit.Test;

public class Ex11FillsAndColorsTest {


	private String fileFolder;

	@Before
	public void setUp() throws Exception {
		fileFolder = getClass().getResource("../../../").getFile().toString();
	}

	@Test
	public void testIterateBlankCells() {
		String filename = "fillsAndColors.xlsx";
		String path = fileFolder + filename;
		Ex11FillsAndColors example = new Ex11FillsAndColors();
		example.fillsAndColors(path);
	}

}
