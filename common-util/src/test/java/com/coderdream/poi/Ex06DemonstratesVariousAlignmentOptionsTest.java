package com.coderdream.poi;

import org.junit.Before;
import org.junit.Test;

public class Ex06DemonstratesVariousAlignmentOptionsTest {


	private String fileFolder;

	@Before
	public void setUp() throws Exception {
		fileFolder = getClass().getResource("../../../").getFile().toString();
	}

	@Test
	public void testCreatingDifferentTypesOfCells() {
		String filename = "xssf-align.xlsx";
		String path = fileFolder + filename;
		Ex06DemonstratesVariousAlignmentOptions example = new Ex06DemonstratesVariousAlignmentOptions();
		example.demonstratesVariousAlignmentOptions(path);
	}

}
