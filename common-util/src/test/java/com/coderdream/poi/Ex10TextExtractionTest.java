package com.coderdream.poi;

import org.junit.Before;
import org.junit.Test;

public class Ex10TextExtractionTest {


	private String fileFolder;

	@Before
	public void setUp() throws Exception {
		fileFolder = getClass().getResource("../../../").getFile().toString();
	}

	@Test
	public void testIterateBlankCells() {
		String filename = "creatingDifferentTypesOfCells.xlsx";
		String path = fileFolder + filename;
		Ex10TextExtraction example = new Ex10TextExtraction();
		example.getTextExtraction(path);
	}

}
