package com.coderdream.poi;

import org.junit.Before;
import org.junit.Test;

public class Ex16UsingNewlinesInCellsTest {


	private String fileFolder;

	@Before
	public void setUp() throws Exception {
		fileFolder = getClass().getResource("../../../").getFile().toString();
	}

	@Test
	public void testIterateBlankCells() {
		String filename = "ooxml-newlines.xlsx";
		String path = fileFolder + filename;
		Ex16UsingNewlinesInCells example = new Ex16UsingNewlinesInCells();
		example.usingNewlinesInCells(path);
	}

}
