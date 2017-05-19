package com.coderdream.poi;

import org.junit.Before;
import org.junit.Test;

public class Ex05CreatingDifferentTypesOfCellsTest {


	private String fileFolder;

	@Before
	public void setUp() throws Exception {
		fileFolder = getClass().getResource("../../../").getFile().toString();
	}

	@Test
	public void testCreatingDifferentTypesOfCells() {
		String filename = "creatingDifferentTypesOfCells.xlsx";
		String path = fileFolder + filename;
		Ex05CreatingDifferentTypesOfCells example = new Ex05CreatingDifferentTypesOfCells();
		example.creatingDifferentTypesOfCells(path);
	}

}
