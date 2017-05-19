package com.coderdream.gensql.util;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class DateUtilTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetDateRange_01() {
		String date1 = "2016-01-01";
		String date2 = "2016-06-20";
		int result = DateUtil.getDateRange(date1, date2);
		assertEquals(172,result);
	}
	
	@Test
	public void testGetDateRange_02() {
		String date1 = "2016-06-21";
		String date2 = "2017-05-19";
		int result = DateUtil.getDateRange(date1, date2);
		assertEquals(333,result);
	}
	
	@Test
	public void testGetDateRange_03() {
		String date1 = "2017-06-20";
		String date2 = "2017-12-31";
		int result = DateUtil.getDateRange(date1, date2);
		assertEquals(195,result);
	}
	
	@Test
	public void testGetDateRange_04() {
		String date1 = "2016-01-01";
		String date2 = "2017-12-31";
		int result = DateUtil.getDateRange(date1, date2);
		assertEquals(731,result);
	}
}
