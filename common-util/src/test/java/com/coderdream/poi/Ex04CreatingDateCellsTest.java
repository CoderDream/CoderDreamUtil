package com.coderdream.poi;

import org.junit.Before;
import org.junit.Test;

public class Ex04CreatingDateCellsTest {


	private String fileFolder;

	@Before
	public void setUp() throws Exception {
		fileFolder = getClass().getResource("../../../").getFile().toString();
	}

	@Test
	public void testCreatingDateCells() {
		String filename = "creatingDateCells.xlsx";
		String path = fileFolder + filename;
		Ex04CreatingDateCells example = new Ex04CreatingDateCells();
		example.creatingDateCells(path);
	}

}
