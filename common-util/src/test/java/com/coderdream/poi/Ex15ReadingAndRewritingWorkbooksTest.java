package com.coderdream.poi;

import org.junit.Before;
import org.junit.Test;

public class Ex15ReadingAndRewritingWorkbooksTest {


	private String fileFolder;

	@Before
	public void setUp() throws Exception {
		fileFolder = getClass().getResource("../../../").getFile().toString();
	}

	@Test
	public void testIterateBlankCells() {
		String filename = "readingAndRewritingWorkbooks.xlsx";
		String path = fileFolder + filename;
		Ex15ReadingAndRewritingWorkbooks example = new Ex15ReadingAndRewritingWorkbooks();
		example.readingAndRewritingWorkbooks(path);
	}

}
