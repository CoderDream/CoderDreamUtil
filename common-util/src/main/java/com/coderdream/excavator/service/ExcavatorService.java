package com.coderdream.excavator.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coderdream.excavator.bean.Excavator;
import com.coderdream.util.Constants;
import com.coderdream.util.DateUtil;
import com.coderdream.util.ExcelUtil;
import com.coderdream.util.MathUtil;

public class ExcavatorService {

	private static final Logger logger = LoggerFactory.getLogger(ExcavatorService.class);

	public static List<Excavator> getExcavatorList(String path) {
		logger.debug("getExcavatorList begin");
		List<Excavator> excavatorList = null;
		String sheetName = "Total";
		try {
			List<String[]> arrayList = ExcelUtil.readData(path, sheetName);
			if (null != arrayList && 0 < arrayList.size()) {
				excavatorList = new ArrayList<Excavator>();
				logger.debug("Size: \t" + arrayList.size());
			}
			for (int i = 0; i < arrayList.size(); i++) {
				Excavator excavator = new Excavator();
				String[] arrayStr = arrayList.get(i);

				/** 日期 */
				String workDate = arrayStr[0];
				/** 收入 */
				Double income = arrayStr[1] == null ? new Double(0) : Double.valueOf(arrayStr[1]);
				/** 支出 */
				Double expend = arrayStr[2] == null ? new Double(0) : Double.valueOf(arrayStr[2]);
				/** 类别 */
				String category = arrayStr[3];
				/** 工地 */
				String location = arrayStr[4];
				/** 备注 */
				String comment = arrayStr[5];
				/** 余额 */
				Double balance = arrayStr[6] == null ? new Double(0) : Double.valueOf(arrayStr[6]);
				/** 数量 */
				Double amount = arrayStr[7] == null ? new Double(0) : Double.valueOf(arrayStr[7]);
				/** 单价 */
				Double unitPrice = arrayStr[8] == null ? new Double(0) : Double.valueOf(arrayStr[8]);
				/** 去零 */
				Double fraction = arrayStr[9] == null ? new Double(0) : Double.valueOf(arrayStr[9]);
				/** 毛利率 */
				Double grossProfitRate = arrayStr[10] == null ? new Double(0) : Double.valueOf(arrayStr[10]);
				/** 毛利 */
				Double grossProfit = new Double(0);

				excavator.setWorkDate(workDate);
				excavator.setIncome(income);
				excavator.setExpend(expend);
				excavator.setCategory(category);
				excavator.setLocation(location);
				excavator.setComment(comment);
				excavator.setBalance(balance);
				excavator.setAmount(amount);
				excavator.setUnitPrice(unitPrice);
				excavator.setFraction(fraction);
				excavator.setGrossProfitRate(grossProfitRate);
				excavator.setGrossProfit(grossProfit);
				excavatorList.add(excavator);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return excavatorList;
	}

	public static Double getSumGrossIncome(String path, String beginDateString, String endDateString) {
		Double grossIncome = new Double(0);
		logger.debug("getExcavatorList begin");

		List<Excavator> excavatorList = ExcavatorService.getExcavatorList(path);
		for (Excavator excavator : excavatorList) {
			// System.out.println(excavator);
			String workDate = excavator.getWorkDate();
			// logger.debug(excavator.toString());
			if (DateUtil.betweenTwoDate(workDate, beginDateString, endDateString)) {
				/** 收入 */
				Double income = excavator.getIncome();
				/** 类别 */
				String category = excavator.getCategory();
				if (Constants.CATEGORY_LOAD.equals(category) || Constants.CATEGORY_STAND_BY.equals(category)) {
					grossIncome += income;
				}
			} else {
				// logger.debug(excavator.toString());
			}
		}

		return grossIncome;
	}

	public static Double getSumGasFee(String path, String beginDateString, String endDateString) {
		Double gasFee = new Double(0);
		logger.debug("getSumGasFee begin");

		List<Excavator> excavatorList = ExcavatorService.getExcavatorList(path);
		for (Excavator excavator : excavatorList) {
			// System.out.println(excavator);
			String workDate = excavator.getWorkDate();
			// logger.debug(excavator.toString());
			if (DateUtil.betweenTwoDate(workDate, beginDateString, endDateString)) {
				/** 支出 */
				Double expend = excavator.getExpend();
				/** 类别 */
				String category = excavator.getCategory();
				if (Constants.CATEGORY_OIL_FEE.equals(category)) {
					gasFee += expend;
				}
			} else {
				// logger.debug(excavator.toString());
			}
		}

		return gasFee;
	}

	public static Double getOutputRate(String path, String beginDateString, String endDateString) {
		Double outputRate = new Double(0);
		Double gasFee = ExcavatorService.getSumGasFee(path, beginDateString, endDateString);
		Double grossIncome = ExcavatorService.getSumGrossIncome(path, beginDateString, endDateString);
		outputRate = MathUtil.setScale(grossIncome / gasFee, 2);// TODO
		return outputRate;
	}

	//
	/**
	 * 毛利
	 * 
	 * @param path
	 * @param beginDateString
	 * @param endDateString
	 * @return
	 */
	public static Map<String, Double> getDailyGrossIncome(String path, String beginDateString, String endDateString) {
		Map<String, Double> dailyIncomeMap = new TreeMap<String, Double>();
		logger.debug("getAverageGrossIncome begin");

		List<Excavator> excavatorList = ExcavatorService.getExcavatorList(path);
		for (Excavator excavator : excavatorList) {
			String workDate = excavator.getWorkDate();
			if (DateUtil.betweenTwoDate(workDate, beginDateString, endDateString)) {
				/** 收入 */
				Double income = excavator.getIncome();
				Double oldIncome = dailyIncomeMap.get(workDate);
				if (null == oldIncome) {
					oldIncome = new Double(0);
				}
				/** 类别 */
				String category = excavator.getCategory();
				if (Constants.CATEGORY_LOAD.equals(category) || Constants.CATEGORY_STAND_BY.equals(category)) {
					if (income > 0) {
						oldIncome += income;
						dailyIncomeMap.put(workDate, oldIncome);
					}
				}
			} else {
				// logger.debug(excavator.toString());
			}
		}

		return dailyIncomeMap;
	}

	//
	public static Double getAverageDailyGrossIncome(String path, String beginDateString, String endDateString) {
		Double averageDailyGrossIncome = new Double(0);
		Map<String, Double> dailyIncomeMap = ExcavatorService.getDailyGrossIncome(path, beginDateString, endDateString);
		logger.debug("getAverageGrossIncome begin");
		Double sum = new Double(0);
		int mapSize = dailyIncomeMap.size();
		for (String workDate : dailyIncomeMap.keySet()) {
			Double dailyIncome = dailyIncomeMap.get(workDate);
			logger.debug(workDate + "\t" + dailyIncome);
			sum += dailyIncome;
		}

		averageDailyGrossIncome = MathUtil.setScale(sum / mapSize, 2);
		return averageDailyGrossIncome;
	}

	public static Double getGrossProfit(String path, String beginDateString, String endDateString) {
		Double grossProfit = new Double(0);
		logger.debug("getExcavatorList begin");

		List<Excavator> excavatorList = ExcavatorService.getExcavatorList(path);
		for (Excavator excavator : excavatorList) {
			String workDate = excavator.getWorkDate();
			if (DateUtil.betweenTwoDate(workDate, beginDateString, endDateString)) {
				/** 收入 */
				Double income = excavator.getIncome();
				/** 支出 */
				Double expend = excavator.getExpend();

				/** 类别 */
				String category = excavator.getCategory();
				if (Constants.CATEGORY_OIL_FEE.equals(category)) {
					grossProfit -= expend;
				}

				if (Constants.CATEGORY_LOAD.equals(category) || Constants.CATEGORY_STAND_BY.equals(category)) {
					grossProfit += income;
				}
			} else {
				// logger.debug(excavator.toString());
			}
		}

		return grossProfit;
	}

	public static Double getGrossProfitByLocation(String path, String locationParam) {
		Double grossProfit = new Double(0);
		logger.debug("getExcavatorList begin");

		List<Excavator> excavatorList = ExcavatorService.getExcavatorList(path);
		for (Excavator excavator : excavatorList) {
			/** 工地 */
			String location = excavator.getLocation();
			if (null != location && !"".equals(locationParam) && location.equals(locationParam)) {
				/** 收入 */
				Double income = excavator.getIncome();
				/** 支出 */
				Double expend = excavator.getExpend();

				/** 类别 */
				String category = excavator.getCategory();
				if (Constants.CATEGORY_OIL_FEE.equals(category)) {
					grossProfit -= expend;
				}

				if (Constants.CATEGORY_LOAD.equals(category) || Constants.CATEGORY_STAND_BY.equals(category)) {
					grossProfit += income;
				}
			} else {
				// logger.debug(excavator.toString());
			}
		}

		return grossProfit;
	}

	public static Double getAverageGrossProfitByLocation(String path, String locationParam) {
		Double grossProfit = new Double(0);
		logger.debug("getExcavatorList begin");
		Set<String> workDaySet = new HashSet<String>();
		List<Excavator> excavatorList = ExcavatorService.getExcavatorList(path);
		for (Excavator excavator : excavatorList) {
			/** 日期 */
			String workDate = excavator.getWorkDate();
			/** 工地 */
			String location = excavator.getLocation();
			if (null != location && !"".equals(locationParam) && location.equals(locationParam)) {
				/** 收入 */
				Double income = excavator.getIncome();
				/** 支出 */
				Double expend = excavator.getExpend();

				/** 类别 */
				String category = excavator.getCategory();
				if (Constants.CATEGORY_OIL_FEE.equals(category)) {
					grossProfit -= expend;
				}

				if (Constants.CATEGORY_LOAD.equals(category) || Constants.CATEGORY_STAND_BY.equals(category)) {
					grossProfit += income;
					workDaySet.add(workDate);
				}
			} else {
				// logger.debug(excavator.toString());
			}
		}

		if (workDaySet.size() > 0) {

			return MathUtil.setScale(grossProfit / workDaySet.size(), 2);
		} else {
			return new Double(0);
		}
	}

	public static Double getNetProfit(String path, String beginDateString, String endDateString) {
		Double netProfit = new Double(0);
		logger.debug("getExcavatorList begin");

		List<Excavator> excavatorList = ExcavatorService.getExcavatorList(path);
		for (Excavator excavator : excavatorList) {
			String workDate = excavator.getWorkDate();
			if (DateUtil.betweenTwoDate(workDate, beginDateString, endDateString)) {
				/** 收入 */
				Double income = excavator.getIncome();
				/** 支出 */
				Double expend = excavator.getExpend();

				/** 类别 */
				String category = excavator.getCategory();
				if (Constants.CATEGORY_OIL_FEE.equals(category) || Constants.CATEGORY_SERVICE.equals(category)) {
					netProfit -= expend;
				}

				netProfit += income;
			} else {
				// logger.debug(excavator.toString());
			}
		}

		return netProfit;
	}

}
