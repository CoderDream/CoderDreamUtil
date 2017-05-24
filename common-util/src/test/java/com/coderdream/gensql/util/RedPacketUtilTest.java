package com.coderdream.gensql.util;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.coderdream.util.Constants;
import com.coderdream.util.DateUtil;
import com.coderdream.util.RedPacketUtil;

public class RedPacketUtilTest extends RedPacketUtil {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSplitRedPackets() {
		// 总是
		int total = DateUtil.getDateRange(Constants.PROJECT_START_DATE, Constants.PROJECT_END_DATE);
		// 个数
		int count = 20;
		// 最小额度
		int min = Constants.PROJECT_PERIOD_MIN;
		// 最大额度
		int max = Constants.PROJECT_PERIOD_MAX;
		// 最大额度是平均值的倍数
		double time = Constants.PROJECT_PERIOD_AVG_TIMES;

		List<Integer> integerList = RedPacketUtil.splitRedPackets(total, count, min, max, time);
		for (Integer integer : integerList) {
			System.out.println(integer);
		}
	}

	@Test
	public void testGetDateStringList() {
		// 总是
		int total = DateUtil.getDateRange(Constants.PROJECT_START_DATE, Constants.PROJECT_END_DATE);
		// 个数
		int count = 20;
		// 最小额度
		int min = Constants.PROJECT_PERIOD_MIN;
		// 最大额度
		int max = Constants.PROJECT_PERIOD_MAX;
		// 最大额度是平均值的倍数
		double time = Constants.PROJECT_PERIOD_AVG_TIMES;

		List<Integer> integerList = RedPacketUtil.splitRedPackets(total, count, min, max, time);
		List<String> dateStringList = RedPacketUtil.getDateStringList(Constants.PROJECT_START_DATE, integerList);
		for (String dateString : dateStringList) {
			System.out.println(dateString);
		}
	}

}
