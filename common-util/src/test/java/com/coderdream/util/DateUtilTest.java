package com.coderdream.util;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.coderdream.util.DateUtil;

public class DateUtilTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetDateRange_01() {
		String date1 = "2016-01-01";
		String date2 = "2016-06-20";
		int result = DateUtil.getDateRange(date1, date2);
		assertEquals(172, result);
	}

	@Test
	public void testGetDateRange_02() {
		String date1 = "2016-06-21";
		String date2 = "2017-05-19";
		int result = DateUtil.getDateRange(date1, date2);
		assertEquals(333, result);
	}

	@Test
	public void testGetDateRange_03() {
		String date1 = "2017-06-20";
		String date2 = "2017-12-31";
		int result = DateUtil.getDateRange(date1, date2);
		assertEquals(195, result);
	}

	@Test
	public void testGetDateRange_04() {
		String date1 = "2016-01-01";
		String date2 = "2017-12-31";
		int result = DateUtil.getDateRange(date1, date2);
		assertEquals(731, result);
	}

	@Test
	public void testGetDateRange_05() {
		String date1 = "2017-12-31";
		String date2 = "2017-12-31";
		int result = DateUtil.getDateRange(date1, date2);
		assertEquals(1, result);
	}

	@Test
	public void testGetDateRange_06() {
		String date1 = "2017-01-01";
		String date2 = "2017-06-30";
		int result = DateUtil.getDateRange(date1, date2);
		assertEquals(181, result);
	}

	@Test
	public void testGetDateRange_07() {
		String date1 = "2017-01-01";
		String date2 = "2017-02-01";
		int result = DateUtil.getDateRange(date1, date2);
		assertEquals(32, result);
	}

	@Test
	public void testGetMonthBetween_01() {
		String beginDateString1 = "2017-01-01";
		String endDateString1 = "2017-03-05";
		System.out.println(DateUtil.getMonthBetween(beginDateString1, endDateString1));
	}

	@Test
	public void testGetMonthBetween_02() {
		String beginDateString2 = "2016-11-11";
		String endDateString2 = "2017-05-05";
		System.out.println(DateUtil.getMonthBetween(beginDateString2, endDateString2));
	}

	@Test
	public void testGetMonthBetween_03() {
		String beginDateString3 = "2017-02-15";
		String endDateString3 = "2017-10-21";
		System.out.println(DateUtil.getMonthBetween(beginDateString3, endDateString3));
	}

	@Test
	public void testGetMonthBetweenParticipate_01() {
		String beginDateString = "2017-02-05";
		String endDateString = "2017-03-06";
		System.out.println(DateUtil.getMonthBetweenParticipate(beginDateString, endDateString));
	}

	@Test
	public void testGetMonthBetweenParticipate_02() {
		String beginDateString = "2017-02-21";
		String endDateString = "2017-04-05";
		System.out.println(DateUtil.getMonthBetweenParticipate(beginDateString, endDateString));
	}

	@Test
	public void testGetMonthBetweenParticipate_03() {
		String beginDateString = "2017-02-05";
		String endDateString = "2017-02-06";
		System.out.println(DateUtil.getMonthBetweenParticipate(beginDateString, endDateString));
	}

	@Test
	public void testGetMonthBetweenParticipate_04() {
		String beginDateString = "2017-04-21";
		String endDateString = "2017-06-15";
		System.out.println(DateUtil.getMonthBetweenParticipate(beginDateString, endDateString));
	}

	@Test
	public void testGetMonthBetweenParticipate_05() {
		String beginDateString = "2017-01-21";
		String endDateString = "2017-06-15";
		System.out.println(DateUtil.getMonthBetweenParticipate(beginDateString, endDateString));
	}

	@Test
	public void testGetMonthBetweenParticipate_06() {
		String beginDateString = "2017-01-21";
		String endDateString = "2017-01-31";
		System.out.println(DateUtil.getMonthBetweenParticipate(beginDateString, endDateString));
	}

	@Test
	public void testGetMonthBetweenParticipate_07() {
		String beginDateString = "2017-01-21";
		String endDateString = "2017-01-21";
		System.out.println(DateUtil.getMonthBetweenParticipate(beginDateString, endDateString));
	}

	@Test
	public void testGetMonthBetweenParticipate_08() {
		String beginDateString = "2017-01-01";
		String endDateString = "2017-02-28";
		List<Double> result = DateUtil.getMonthBetweenParticipate(beginDateString, endDateString);
		System.out.println(result);
		Double expectValue = new Double(1.0);
		assertEquals(expectValue, result.get(0));
		assertEquals(expectValue, result.get(1));
	}

	@Test
	public void testGetMonthBetweenParticipateWithMonth_01() {
		String beginDateString = "2017-01-01";
		String endDateString = "2017-02-28";
		Map<String, Double> participateMap = DateUtil.getMonthBetweenParticipateWithMonth(beginDateString,
				endDateString);
		for (String month : participateMap.keySet()) {
			Double participateValue = participateMap.get(month);
			System.out.println(month + "\t" + participateValue);
		}
	}

	@Test
	public void testGetMonthBetweenParticipateWithMonth_02() {
		String beginDateString = "2017-01-21";
		String endDateString = "2017-06-15";
		Map<String, Double> participateMap = DateUtil.getMonthBetweenParticipateWithMonth(beginDateString,
				endDateString);
		for (String month : participateMap.keySet()) {
			Double participateValue = participateMap.get(month);
			System.out.println(month + "\t" + participateValue);
		}
	}

	@Test
	public void testGetMonthEnd_01() {
		String dateStr = "2017-01-21";
		String monthEnd = DateUtil.getMonthEnd(dateStr);
		String expectValue = "2017-01-31";
		assertEquals(expectValue, monthEnd);
	}

	@Test
	public void testGetMonthEnd_02() {
		String dateStr = "2017-02-21";
		String monthEnd = DateUtil.getMonthEnd(dateStr);
		String expectValue = "2017-02-28";
		assertEquals(expectValue, monthEnd);
	}

	@Test
	public void testGetMonthEnd_03() {
		String dateStr = "2017-04-21";
		String monthEnd = DateUtil.getMonthEnd(dateStr);
		String expectValue = "2017-04-30";
		assertEquals(expectValue, monthEnd);
	}

	@Test
	public void testGetMonthEnd_04() {
		String dateStr = "2017-12-21";
		String monthEnd = DateUtil.getMonthEnd(dateStr);
		String expectValue = "2017-12-31";
		assertEquals(expectValue, monthEnd);
	}

	@Test
	public void testBeforeDate_01() {
		String beginDateString = "2017-12-21";
		String endDateString = "2017-12-21";
		boolean monthEnd = DateUtil.beforeDate(beginDateString, endDateString);
		boolean expectValue = false;
		assertEquals(expectValue, monthEnd);
	}
	
	@Test
	public void testBeforeDate_02() {
		String beginDateString = "2017-12-22";
		String endDateString = "2017-12-21";
		boolean monthEnd = DateUtil.beforeDate(beginDateString, endDateString);
		boolean expectValue = false;
		assertEquals(expectValue, monthEnd);
	}
	
	@Test
	public void testBeforeDate_03() {
		String beginDateString = "2017-12-21";
		String endDateString = "2017-12-22";
		boolean compareResult = DateUtil.beforeDate(beginDateString, endDateString);
		boolean expectValue = true;
		assertEquals(expectValue, compareResult);
	}
	
	@Test
	public void testBetweenTwoDate_01() {
		String dateString = "2017-12-01";
		String beginDateString = "2017-12-01";
		String endDateString = "2017-12-22";
		boolean compareResult = DateUtil.betweenTwoDate(dateString, beginDateString, endDateString);
		boolean expectValue = true;
		assertEquals(expectValue, compareResult);
	}
	
	@Test
	public void testBetweenTwoDate_02() {
		String dateString = "2017-12-22";
		String beginDateString = "2017-12-01";
		String endDateString = "2017-12-22";
		boolean compareResult = DateUtil.betweenTwoDate(dateString, beginDateString, endDateString);
		boolean expectValue = true;
		assertEquals(expectValue, compareResult);
	}
	
	@Test
	public void testBetweenTwoDate_03() {
		String dateString = "2017-11-30";
		String beginDateString = "2017-12-01";
		String endDateString = "2017-12-22";
		boolean compareResult = DateUtil.betweenTwoDate(dateString, beginDateString, endDateString);
		boolean expectValue = false;
		assertEquals(expectValue, compareResult);
	}
	

}
