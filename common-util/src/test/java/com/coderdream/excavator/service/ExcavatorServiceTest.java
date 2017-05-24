package com.coderdream.excavator.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coderdream.excavator.bean.Excavator;
import com.coderdream.util.Constants;

public class ExcavatorServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(ExcavatorServiceTest.class);

	private String fileFolder;

	private String dataFileName;
	
	private List<String> locationList;

	@Before
	public void setUp() throws Exception {
		fileFolder = getClass().getResource("../../../../").getFile().toString();
		dataFileName = Constants.EXCAVATOR_FILE_NAME;
		locationList = new ArrayList<String>();
		locationList.add(Constants.LOCATION_ONE);
		locationList.add(Constants.LOCATION_TWO);
		locationList.add(Constants.LOCATION_THREE);
	}

	@Test
	public void testGetExcavatorList() {
		String path = fileFolder + dataFileName;
		List<Excavator> excavatorList = ExcavatorService.getExcavatorList(path);
		for (Excavator excavator : excavatorList) {
			System.out.println(excavator);
			logger.debug(excavator.toString());
		}
	}

	@Test
	public void testGetGrossIncome_01() {
		String path = fileFolder + dataFileName;
		String beginDateString = "2017-04-01";
		String endDateString = "2017-04-30";
		Double grossIncome = ExcavatorService.getSumGrossIncome(path, beginDateString, endDateString);
		logger.debug("grossIncome\t" + grossIncome);
	}

	@Test
	public void testGetGrossIncome_02() {
		String path = fileFolder + dataFileName;
		String beginDateString = "2017-05-01";
		String endDateString = "2017-05-31";
		Double grossIncome = ExcavatorService.getSumGrossIncome(path, beginDateString, endDateString);
		logger.debug("grossIncome\t" + grossIncome);
	}
	
	@Test
	public void testGetGrossIncome_03() {
		String path = fileFolder + dataFileName;
		String beginDateString = "2017-03-01";
		String endDateString = "2017-05-31";
		Double grossIncome = ExcavatorService.getSumGrossIncome(path, beginDateString, endDateString);
		logger.debug("grossIncome\t" + grossIncome);
	}
	
	@Test
	public void testGetGrossIncome_04() {
		String path = fileFolder + dataFileName;
		String beginDateString = "2017-03-01";
		String endDateString = "2017-04-01";
		Double grossIncome = ExcavatorService.getSumGrossIncome(path, beginDateString, endDateString);
		Double expectValue = new Double(21089);
		assertEquals(expectValue, grossIncome);
		logger.debug("grossIncome\t" + grossIncome);
	}
	
	@Test
	public void testGetOutputRate_01() {
		String path = fileFolder + dataFileName;
		String beginDateString = "2017-03-01";
		String endDateString = "2017-04-01";
		Double outputRate = ExcavatorService.getOutputRate(path, beginDateString, endDateString);
		
		Double expectValue = new Double(2.44);
		assertEquals(expectValue, outputRate);
		logger.debug("grossIncome\t" + outputRate);
	}
	
	@Test
	public void testGetOutputRate_02() {
		String path = fileFolder + dataFileName;
		String beginDateString = "2017-04-02";
		String endDateString = "2017-05-14";
		Double outputRate = ExcavatorService.getOutputRate(path, beginDateString, endDateString);
		Double expectValue = new Double(2.65);
		assertEquals(expectValue, outputRate);
		logger.debug("grossIncome\t" + outputRate);
	}
	
	@Test
	public void testGetOutputRate_03() {
		String path = fileFolder + dataFileName;
		String beginDateString = "2017-04-02";
		String endDateString = "2017-05-20";
		Double outputRate = ExcavatorService.getOutputRate(path, beginDateString, endDateString);
		Double expectValue = new Double(2.45);
		assertEquals(expectValue, outputRate);
		logger.debug("grossIncome\t" + outputRate);
	}
	
	@Test
	public void testGasFee_01() {
		String path = fileFolder + dataFileName;
		String beginDateString = "2017-03-01";
		String endDateString = "2017-04-01";
		Double gasFee = ExcavatorService.getSumGasFee(path, beginDateString, endDateString);
		logger.debug("gasFee\t" + gasFee);
	}
	
	@Test
	public void testGasFee_02() {
		String path = fileFolder + dataFileName;
		String beginDateString = "2017-04-02";
		String endDateString = "2017-05-20";
		Double gasFee = ExcavatorService.getSumGasFee(path, beginDateString, endDateString);
		logger.debug("gasFee\t" + gasFee);
	}
	
	@Test
	public void testGetGrossProfit_01() {
		String path = fileFolder + dataFileName;
		String beginDateString = "2017-04-01";
		String endDateString = "2017-04-30";
		Double grossProfit = ExcavatorService.getGrossProfit(path, beginDateString, endDateString);
		logger.debug("grossProfit\t" + grossProfit);
	}

	@Test
	public void testGetGrossProfit_02() {
		String path = fileFolder + dataFileName;
		String beginDateString = "2017-05-01";
		String endDateString = "2017-05-31";
		Double grossProfit = ExcavatorService.getGrossProfit(path, beginDateString, endDateString);
		logger.debug("grossProfit\t" + grossProfit);
	}
	
	@Test
	public void testGetGrossProfitByLocation_01() {
		String path = fileFolder + dataFileName;
		String location = Constants.LOCATION_ONE;
		Double grossProfit = ExcavatorService.getGrossProfitByLocation(path, location);
		logger.debug(location+ "grossProfit\t" + grossProfit);
	}
	
	@Test
	public void testGetGrossProfitByLocation_02() {
		String path = fileFolder + dataFileName;
		String location = Constants.LOCATION_TWO;
		Double grossProfit = ExcavatorService.getGrossProfitByLocation(path, location);
		logger.debug(location+ "grossProfit\t" + grossProfit);
	}
	
	@Test
	public void testGetGrossProfitByLocation_03() {
		String path = fileFolder + dataFileName;
		String location = Constants.LOCATION_THREE;
		Double grossProfit = ExcavatorService.getGrossProfitByLocation(path, location);
		logger.debug(location+ "grossProfit\t" + grossProfit);
	}
	
	@Test
	public void testGetGrossProfitByLocation_04() {
		String path = fileFolder + dataFileName;
		for (String location : locationList) {
			Double grossProfit = ExcavatorService.getGrossProfitByLocation(path, location);
			logger.debug(location+ "grossProfit\t" + grossProfit);
		}
	}
	
	@Test
	public void testGetAverageGrossProfitByLocation_01() {
		String path = fileFolder + dataFileName;
		String location = Constants.LOCATION_ONE;
		Double grossProfit = ExcavatorService.getAverageGrossProfitByLocation(path, location);
		logger.debug(location+ "grossProfit\t" + grossProfit);
	}
	
	@Test
	public void testGetAverageGrossProfitByLocation_02() {
		String path = fileFolder + dataFileName;
		String location = Constants.LOCATION_TWO;
		Double grossProfit = ExcavatorService.getAverageGrossProfitByLocation(path, location);
		logger.debug(location+ "grossProfit\t" + grossProfit);
	}
	
	@Test
	public void testGetAverageGrossProfitByLocation_03() {
		String path = fileFolder + dataFileName;
		String location = Constants.LOCATION_THREE;
		Double grossProfit = ExcavatorService.getAverageGrossProfitByLocation(path, location);
		logger.debug(location+ "grossProfit\t" + grossProfit);
	}
	
	@Test
	public void testGetAverageGrossProfitByLocation_04() {
		String path = fileFolder + dataFileName;
		for (String location : locationList) {
			Double grossProfit = ExcavatorService.getAverageGrossProfitByLocation(path, location);
			logger.debug(location+ "grossProfit\t" + grossProfit);
		}
	}

	@Test
	public void testGetNetProfit_01() {
		String path = fileFolder + dataFileName;
		String beginDateString = "2017-04-01";
		String endDateString = "2017-04-30";
		Double netProfit = ExcavatorService.getNetProfit(path, beginDateString, endDateString);
		logger.debug("netProfit\t" + netProfit);
	}
	
	@Test
	public void testGetNetProfit_02() {
		String path = fileFolder + dataFileName;
		String beginDateString = "2017-05-01";
		String endDateString = "2017-05-31";
		Double netProfit = ExcavatorService.getNetProfit(path, beginDateString, endDateString);
		logger.debug("netProfit\t" + netProfit);
	}

	/**
	 * Net:12378
	 * Salary: 10000
	 * Interest: 350000*0.02=7000
	 * 
	 */
	@Test
	public void testGetNetProfit_03() {
		String path = fileFolder + dataFileName;
		String beginDateString = "2017-04-01";
		String endDateString = "2017-05-31";
		Double netProfit = ExcavatorService.getNetProfit(path, beginDateString, endDateString);
		logger.debug("netProfit\t" + netProfit/2);
	}

	@Test
	public void testGetDailyGrossIncome_01() {
		String path = fileFolder + dataFileName;
		String beginDateString = "2017-04-01";
		String endDateString = "2017-05-31";
		Map<String, Double> dailyIncomeMap = ExcavatorService.getDailyGrossIncome(path, beginDateString, endDateString);

		logger.debug("dailyIncomeMap size\t" + dailyIncomeMap.size());
		for (String workDate : dailyIncomeMap.keySet()) {
			Double dailyIncome = dailyIncomeMap.get(workDate);
			logger.debug(workDate + "\t" + dailyIncome);
		}
	}

	@Test
	public void testGetAverageDailyGrossIncome_01() {
		String path = fileFolder + dataFileName;
		String beginDateString = "2017-04-01";
		String endDateString = "2017-04-30";
		Double averageDailyGrossIncome = ExcavatorService.getAverageDailyGrossIncome(path, beginDateString,
				endDateString);

		logger.debug("averageDailyGrossIncome\t" + averageDailyGrossIncome);
	}

	@Test
	public void testGetAverageDailyGrossIncome_02() {
		String path = fileFolder + dataFileName;
		String beginDateString = "2017-05-01";
		String endDateString = "2017-05-31";
		Double averageDailyGrossIncome = ExcavatorService.getAverageDailyGrossIncome(path, beginDateString,
				endDateString);

		logger.debug("averageDailyGrossIncome\t" + averageDailyGrossIncome);
	}

	@Test
	public void testGetAverageDailyGrossIncome_03() {
		String path = fileFolder + dataFileName;
		String beginDateString = "2017-04-01";
		String endDateString = "2017-05-31";
		Double averageDailyGrossIncome = ExcavatorService.getAverageDailyGrossIncome(path, beginDateString,
				endDateString);

		logger.debug("averageDailyGrossIncome\t" + averageDailyGrossIncome);
	}

}
