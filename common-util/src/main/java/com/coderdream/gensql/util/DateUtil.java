package com.coderdream.gensql.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class DateUtil {

	public static boolean beforeTotal(String beginDateString) {
		Date date = new Date();

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = df.parse(beginDateString);
			// System.out.println(date);
		} catch (ParseException e) {
			System.out.println("Unparseable using" + df);
		}
		Date totalDate = new Date();// 取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);

		Calendar calendarToday = new GregorianCalendar();
		calendarToday.setTime(totalDate);
		calendarToday.add(Calendar.DATE, -1);// 把日期往后增加一天.整数往后推,负数往前移动

		return calendarToday.after(calendar);
	}

	public static boolean compareTwoDate(String oneDateString, String secondDateString) {
		Date dateOne = new Date();
		Date dateTwo = new Date();// 取时间

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			dateOne = df.parse(oneDateString);
			dateTwo = df.parse(secondDateString);
		} catch (ParseException e) {
			System.out.println("Unparseable using" + df);
		}

		Calendar calendarOne = new GregorianCalendar();
		calendarOne.setTime(dateOne);

		Calendar calendarSecond = new GregorianCalendar();
		calendarSecond.setTime(dateTwo);

		return calendarSecond.after(calendarOne);
	}

	public static String getNextDate(String dateString, Integer count) {
		Date dateOne = new Date();

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			dateOne = df.parse(dateString);
		} catch (ParseException e) {
			System.out.println("Unparseable using" + df);
		}

		Calendar calendar = new GregorianCalendar();
		calendar.setTime(dateOne);
		calendar.add(Calendar.DATE, count);

		return df.format(calendar.getTime());
	}

	public static List<String> getMonthBetween(String minDate, String maxDate) {
		List<String> result = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 格式化为年月

		Calendar min = Calendar.getInstance();
		Calendar max = Calendar.getInstance();

		try {
			min.setTime(sdf.parse(minDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

		try {
			max.setTime(sdf.parse(maxDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

		Calendar curr = min;
		while (curr.before(max)) {
			result.add(sdf.format(curr.getTime()));
			curr.add(Calendar.MONTH, 1);
		}

		return result;
	}

	public static int getDateRange(String date1, String date2) {
		Calendar calst = Calendar.getInstance();
		Calendar caled = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 格式化为年月
		try {
			calst.setTime(df.parse(date1));
			caled.setTime(df.parse(date2));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// 设置时间为0时
		calst.set(Calendar.HOUR_OF_DAY, 0);
		calst.set(Calendar.MINUTE, 0);
		calst.set(Calendar.SECOND, 0);
		caled.set(Calendar.HOUR_OF_DAY, 0);
		caled.set(Calendar.MINUTE, 0);
		caled.set(Calendar.SECOND, 0);
		// 得到两个日期相差的天数
		int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst.getTime().getTime() / 1000)) / 3600 / 24;

		return days + 1;
	}

	public static void main(String[] args) {

		System.out.println(DateUtil.getNextDate("2017-05-15", 5));
		// String beginDateString1 = ;
		// String beginDateString2 = "2017-05-16";
		// String beginDateString3 = "2017-05-17";
		// String beginDateString4 = "2017-05-18";
		// String beginDateString5 = "2017-12-31";
		// System.out.println(DateUtil.beforeTotal(beginDateString1));
		// System.out.println(DateUtil.beforeTotal(beginDateString2));
		// System.out.println(DateUtil.beforeTotal(beginDateString3));
		// System.out.println(DateUtil.beforeTotal(beginDateString4));
		// System.out.println(DateUtil.beforeTotal(beginDateString5));

		System.out.println(DateUtil.compareTwoDate("2017-01-01", "2017-01-02"));
		System.out.println(DateUtil.compareTwoDate("2017-01-02", "2017-01-02"));
		System.out.println(DateUtil.compareTwoDate("2017-01-03", "2017-01-02"));

		String beginDateString1 = "2017-01-01";
		String endDateString1 = "2017-03-05";
		String beginDateString2 = "2016-11-11";
		String endDateString2 = "2017-05-05";
		String beginDateString3 = "2017-02-15";
		String endDateString3 = "2017-10-21";
		System.out.println(DateUtil.getMonthBetween(beginDateString1, endDateString1));
		System.out.println(DateUtil.getMonthBetween(beginDateString2, endDateString2));
		System.out.println(DateUtil.getMonthBetween(beginDateString3, endDateString3));
	}
}
