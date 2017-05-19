package com.coderdream.poi;

import org.junit.Before;
import org.junit.Test;

public class Ex09GettingTheCellContentsTest {


	private String fileFolder;

	@Before
	public void setUp() throws Exception {
		fileFolder = getClass().getResource("../../../").getFile().toString();
	}

	@Test
	public void testIterateBlankCells() {
		String filename = "creatingDifferentTypesOfCells.xlsx";
		String path = fileFolder + filename;
		Ex09GettingTheCellContents example = new Ex09GettingTheCellContents();
		example.gettingTheCellContents(path);
	}

}
