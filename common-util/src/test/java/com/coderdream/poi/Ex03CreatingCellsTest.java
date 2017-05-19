package com.coderdream.poi;

import org.junit.Before;
import org.junit.Test;

public class Ex03CreatingCellsTest {

	private String fileFolder;

	@Before
	public void setUp() throws Exception {
		fileFolder = getClass().getResource("../../../").getFile().toString();
	}

	@Test
	public void testCreatingCells() {
		String filename = "creatingCells.xlsx";
		String path = fileFolder + filename;
		Ex03CreatingCells example = new Ex03CreatingCells();
		example.creatingCells(path);
	}
}
