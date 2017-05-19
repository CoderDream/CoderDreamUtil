package com.coderdream.poi;

import org.junit.Before;
import org.junit.Test;

public class Ex07WorkingWithBordersTest {


	private String fileFolder;

	@Before
	public void setUp() throws Exception {
		fileFolder = getClass().getResource("../../../").getFile().toString();
	}

	@Test
	public void testCreatingDifferentTypesOfCells() {
		String filename = "workingWithBorders.xlsx";
		String path = fileFolder + filename;
		Ex07WorkingWithBorders example = new Ex07WorkingWithBorders();
		example.workingWithBorders(path);
	}

}
