package com.coderdream.poi;

import org.junit.Before;
import org.junit.Test;

public class Ex12MergingCellsTest {


	private String fileFolder;

	@Before
	public void setUp() throws Exception {
		fileFolder = getClass().getResource("../../../").getFile().toString();
	}

	@Test
	public void testIterateBlankCells() {
		String filename = "mergingCells.xlsx";
		String path = fileFolder + filename;
		Ex12MergingCells example = new Ex12MergingCells();
		example.mergingCells(path);
	}

}
