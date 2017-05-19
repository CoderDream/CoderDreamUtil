package com.coderdream.gensql.service;

import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.b510.excel.common.Common;
import com.coderdream.gensql.bean.IsbgHumanMap;
import com.coderdream.gensql.bean.IsbgHumanMapComparator;
import com.coderdream.gensql.bean.IsbgProject;
import com.coderdream.gensql.bean.PdrcBsmDispatch;
import com.coderdream.gensql.bean.PdrcBsmDispatchComparator;
import com.coderdream.gensql.bean.PdrcEnpPrize;
import com.coderdream.gensql.bean.PdrcEnpPrizeComparator;
import com.coderdream.gensql.bean.PdrcStaffManage;
import com.coderdream.gensql.bean.PdrcTm;
import com.coderdream.gensql.bean.PdrcTmSalary;
import com.coderdream.gensql.bean.PdrcTmSalaryComparator;
import com.coderdream.gensql.bean.PmRmRelation;
import com.coderdream.gensql.util.DateUtil;
import com.coderdream.gensql.util.ExcelUtil;
import com.coderdream.gensql.util.MathUtil;
import com.coderdream.gensql.util.RedPacketUtil;

public class DataService {

	private static final Logger logger = LoggerFactory.getLogger(DataService.class);

	public List<PdrcTm> getPdrcTmList(String path) {
		List<PdrcTm> list = null;
		logger.debug(Common.PROCESSING + path);
		String sheetName = "PDRC_TM";
		try {
			List<String[]> arrayList = ExcelUtil.readAllData(path, sheetName);
			if (null != arrayList && 1 < arrayList.size()) {
				list = new ArrayList<PdrcTm>();
			}
			for (int i = 1; i < arrayList.size(); i++) {
				PdrcTm rdrcTm = new PdrcTm();
				String[] arrayStr = arrayList.get(i);
				String workID = arrayStr[0];
				rdrcTm.setWorkID(workID);
				String rmWorkID = arrayStr[1];
				rdrcTm.setRmWorkID(rmWorkID);

				list.add(rdrcTm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<PdrcStaffManage> getPdrcStaffManageList(String path) {
		List<PdrcStaffManage> list = null;
		logger.debug(Common.PROCESSING + path);
		String sheetName = "PDRC_StaffManage";
		try {
			List<String[]> arrayList = ExcelUtil.readData(path, sheetName);
			if (null != arrayList && 0 < arrayList.size()) {
				list = new ArrayList<PdrcStaffManage>();
				logger.debug("Size: \t" + arrayList.size());
			}
			for (int i = 0; i < arrayList.size(); i++) {
				PdrcStaffManage pdrcStaffManage = new PdrcStaffManage();
				String[] arrayStr = arrayList.get(i);
				String workID = arrayStr[0];
				pdrcStaffManage.setWorkID(workID);
				String tmWorkID = arrayStr[2];
				pdrcStaffManage.setTmWorkID(tmWorkID);
				String normalMam = arrayStr[4];
				String salary = arrayStr[8];
				pdrcStaffManage.setNormalMam(normalMam);
				pdrcStaffManage.setSalary(salary);

				list.add(pdrcStaffManage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public static List<PmRmRelation> getPmRmRelationList(String path) {
		List<PmRmRelation> pmRmRelationList = null;
		List<String> pmList = null;

		logger.debug(Common.PROCESSING + path);
		String sheetName = "PDRC_PM";
		try {
			List<String[]> arrayList = ExcelUtil.readData(path, sheetName);
			if (null != arrayList && 0 < arrayList.size()) {
				logger.debug("Size: \t" + arrayList.size());
				pmList = new ArrayList<String>();

				for (int i = 0; i < arrayList.size(); i++) {
					String[] arrayStr = arrayList.get(i);
					String workID = arrayStr[0];
					pmList.add(workID);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		Map<String, List<PdrcStaffManage>> pdrcStaffManageListMap = getPdrcStaffManageListMap(path);

		if (pdrcStaffManageListMap.size() > 0) {
			pmRmRelationList = new ArrayList<PmRmRelation>();
		}
		int index = 0;
		PmRmRelation pmRmRelation = null;
		List<String> workIDList = null;
		for (String key : pdrcStaffManageListMap.keySet()) {

			pmRmRelation = new PmRmRelation();
			List<PdrcStaffManage> pdrcStaffManageList = pdrcStaffManageListMap.get(key);
			String pmWorkID = pmList.get(index++);
			pmRmRelation.setPmWorkID(pmWorkID);
			pmRmRelation.setRmWorkID(key);

			// System.out.println("RM WorkId\t" + key);
			workIDList = new ArrayList<String>();
			// workIDList.add(key);
			for (PdrcStaffManage pdrcStaffManage : pdrcStaffManageList) {
				// System.out.println("WorkId\t\t" +
				// pdrcStaffManage.getWorkID());
				workIDList.add(pdrcStaffManage.getWorkID());
				pmRmRelation.setWorkIDList(workIDList);
			}

			pmRmRelationList.add(pmRmRelation);
		}

		return pmRmRelationList;
	}

	public static Map<String, List<String>> getPmWorkIDListMap(String path) {
		Map<String, List<String>> pmWorkIDListMap = new HashMap<String, List<String>>();
		List<PmRmRelation> pmRmRelationList = DataService.getPmRmRelationList(path);

		for (PmRmRelation pmRmRelation : pmRmRelationList) {
			String pmWorkID = pmRmRelation.getPmWorkID();
			List<String> workIDList = pmRmRelation.getWorkIDList();
			pmWorkIDListMap.put(pmWorkID, workIDList);
		}

		return pmWorkIDListMap;
	}

	public static Map<String, List<PdrcStaffManage>> getPdrcStaffManageListMap(String path) {
		List<PdrcStaffManage> pdrcStaffManageList = null;
		logger.debug(Common.PROCESSING + path);
		String sheetName = "PDRC_StaffManage";
		Map<String, List<PdrcStaffManage>> pdrcStaffManageListMap = new HashMap<String, List<PdrcStaffManage>>();
		try {
			List<String[]> arrayList = ExcelUtil.readAllData(path, sheetName);
			if (null != arrayList && 1 < arrayList.size()) {

				for (int i = 1; i < arrayList.size(); i++) {

					PdrcStaffManage pdrcStaffManage = new PdrcStaffManage();
					String[] arrayStr = arrayList.get(i);
					String tmWorkID = arrayStr[0];
					pdrcStaffManage.setTmWorkID(tmWorkID);
					String workID = arrayStr[2];
					pdrcStaffManage.setWorkID(workID);// TODO
					String normalMam = arrayStr[4];
					String salary = arrayStr[8];
					pdrcStaffManage.setNormalMam(normalMam);
					pdrcStaffManage.setSalary(salary);

					pdrcStaffManageList = pdrcStaffManageListMap.get(tmWorkID);

					if (null == pdrcStaffManageList) {
						pdrcStaffManageList = new ArrayList<PdrcStaffManage>();
					}

					pdrcStaffManageList.add(pdrcStaffManage);
					pdrcStaffManageListMap.put(tmWorkID, pdrcStaffManageList);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pdrcStaffManageListMap;
	}

	public static List<PdrcTmSalary> getPdrcTmSalaryList(String path) {
		List<PdrcTmSalary> pdrcTmSalaryList = new ArrayList<PdrcTmSalary>();
		List<PdrcStaffManage> pdrcStaffManageList = null;
		logger.debug(Common.PROCESSING + path);
		Map<String, List<PdrcStaffManage>> pdrcStaffManageListMap = DataService.getPdrcStaffManageListMap(path);
		PdrcTmSalary pdrcTmSalary = null;
		for (String key : pdrcStaffManageListMap.keySet()) {

			pdrcStaffManageList = pdrcStaffManageListMap.get(key);
			pdrcTmSalary = new PdrcTmSalary();
			pdrcTmSalary.setTmWorkID(key);

			Integer totalSalaryValue = 0;
			int index = 1;
			for (PdrcStaffManage pdrcStaffManage : pdrcStaffManageList) {
				String salary = pdrcStaffManage.getSalary();
				totalSalaryValue += Integer.parseInt(salary);
				index++;
			}
			Integer averageSalaryValue = totalSalaryValue / index;
			pdrcTmSalary.setTotalSalary(totalSalaryValue.toString());
			pdrcTmSalary.setAverageSalary(averageSalaryValue.toString());
			pdrcTmSalaryList.add(pdrcTmSalary);
		}

		return pdrcTmSalaryList;
	}

	public static List<PdrcTmSalary> getPdrcTmSalaryListWithDateRange(String path, String startDateString,
			String endDateString) {
		List<PdrcTmSalary> pdrcTmSalaryList = new ArrayList<PdrcTmSalary>();

		List<String> monthList = DateUtil.getMonthBetween(startDateString, endDateString);

		List<PdrcTmSalary> basePdrcTmSalaryList = DataService.getPdrcTmSalaryList(path);
		PdrcTmSalary newPdrcTmSalary = null;
		try {
			for (String monthDate : monthList) {
				for (PdrcTmSalary pdrcTmSalary : basePdrcTmSalaryList) {
					newPdrcTmSalary = new PdrcTmSalary();
					BeanUtils.copyProperties(newPdrcTmSalary, pdrcTmSalary);
					newPdrcTmSalary.setMonthDate(monthDate);
					pdrcTmSalaryList.add(newPdrcTmSalary);
				}
			}
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		Collections.sort(pdrcTmSalaryList, new PdrcTmSalaryComparator());

		return pdrcTmSalaryList;
	}

	public static Map<String, String> getWorkIDBsmMap(String path) {
		Map<String, String> workIDBsmMap = new HashMap<String, String>();
		logger.debug(Common.PROCESSING + path);
		String sheetName = "PDRC_StaffManage";
		try {
			List<String[]> arrayList = ExcelUtil.readAllData(path, sheetName);
			if (null != arrayList && 1 < arrayList.size()) {
				for (int i = 1; i < arrayList.size(); i++) {
					String[] arrayStr = arrayList.get(i);
					String workID = arrayStr[2];
					String bsm = arrayStr[4];
					workIDBsmMap.put(workID, bsm);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return workIDBsmMap;
	}

	public List<PdrcStaffManage> getPdrcStaffManageListByRmWorkId(String path, String RmWorkId) {
		Map<String, List<PdrcStaffManage>> pdrcStaffManageListMap = getPdrcStaffManageListMap(path);
		List<PdrcStaffManage> pdrcStaffManageList = null;
		for (String key : pdrcStaffManageListMap.keySet()) {
			if (RmWorkId.equalsIgnoreCase(key)) {
				pdrcStaffManageList = pdrcStaffManageListMap.get(key);
			}
		}

		return pdrcStaffManageList;
	}

	public static List<IsbgProject> getIsbgProjectList(String path) {
		List<IsbgProject> isbgProjectList = null;
		logger.debug(Common.PROCESSING + path);
		String sheetName = "ISBG_Project";
		try {
			List<String[]> arrayList = ExcelUtil.readData(path, sheetName);
			if (null != arrayList && 0 < arrayList.size()) {
				isbgProjectList = new ArrayList<IsbgProject>();
				logger.debug("Size: \t" + arrayList.size());
			}
			for (int i = 0; i < arrayList.size(); i++) {
				IsbgProject isbgProject = new IsbgProject();
				String[] arrayStr = arrayList.get(i);

				String projectId = arrayStr[0];
				String projectNo = arrayStr[1];
				String projectName = arrayStr[2];
				String projectMgrWorkID = arrayStr[3];
				String projectMgrName = arrayStr[4];

				isbgProject.setProjectId(projectId);
				isbgProject.setProjectNo(projectNo);
				isbgProject.setProjectName(projectName);
				isbgProject.setProjectMgrWorkID(projectMgrWorkID);
				isbgProject.setProjectMgrName(projectMgrName);
				isbgProjectList.add(isbgProject);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isbgProjectList;
	}

	public static Map<String, List<IsbgProject>> getIsbgProjectListMap(String path) {

		Map<String, List<IsbgProject>> isbgProjectListMap = new HashMap<String, List<IsbgProject>>();

		List<IsbgProject> isbgProjectList = getIsbgProjectList(path);
		for (IsbgProject isbgProject : isbgProjectList) {
			String projectMgrWorkID = isbgProject.getProjectMgrWorkID();
			List<IsbgProject> list = isbgProjectListMap.get(projectMgrWorkID);
			if (null == list) {
				list = new ArrayList<IsbgProject>();
			}
			list.add(isbgProject);

			isbgProjectListMap.put(isbgProject.getProjectMgrWorkID(), list);
		}

		return isbgProjectListMap;
	}

	public static List<IsbgProject> getIsbgProjectListInfo(String path) {
		List<IsbgProject> totalIsbgProjectList = new ArrayList<IsbgProject>();
		Map<String, List<IsbgProject>> isbgProjectListMap = getIsbgProjectListMap(path);

		for (String key : isbgProjectListMap.keySet()) {

			List<IsbgProject> isbgProjectList = isbgProjectListMap.get(key);

			// System.out.println("projectMgrWorkID\t" + key);

			String beginDateString = "2016-01-01";
			// 总是
			int total = 730;
			// 个数
			int count = isbgProjectList.size();
			// 最小额度
			int min = 15;
			// 最大额度
			int max = 150;
			// 最大额度是平均值的倍数
			double time = 6.1;

			List<Integer> integerList = RedPacketUtil.splitRedPackets(total, count, min, max, time);
			List<String> dateStringList = RedPacketUtil.getDateStringList(beginDateString, integerList);
			for (int i = 0; i < count; i++) {
				IsbgProject isbgProject = isbgProjectList.get(i);
				// System.out.println("WorkId\t\t" + isbgProject);
				// System.out.println(isbgProject);

				// Map<String, List<PdrcStaffManage>> pdrcStaffManageListMap =
				// getPdrcStaffManageListByRmWorkId(path, RmWorkId);

				String projectStartDateTime = dateStringList.get(i * 2);
				String projectEndDateTime = dateStringList.get(i * 2 + 1);
				isbgProject.setProjectStartDateTime(projectStartDateTime);
				isbgProject.setProjectEndDateTime(projectEndDateTime);

				Integer pdrc = integerList.get(i) * 15000;
				isbgProject.setPdrc(pdrc.toString());

				String isFinish = DateUtil.beforeTotal(projectEndDateTime) ? "true" : "false";
				isbgProject.setIsFinish(isFinish);

				totalIsbgProjectList.add(isbgProject);
			}
		}

		return totalIsbgProjectList;
	}

	public static Map<String, IsbgProject> getIsbgProjectMap(String path) {
		Map<String, IsbgProject> isbgProjectMap = new HashMap<String, IsbgProject>();

		List<IsbgProject> totalIsbgProjectList = getIsbgProjectListInfo(path);
		for (IsbgProject isbgProject : totalIsbgProjectList) {
			isbgProjectMap.put(isbgProject.getProjectId(), isbgProject);
		}

		return isbgProjectMap;
	}

	public static List<IsbgHumanMap> getIsbgHumanMapListInfo(String path) {
		List<IsbgHumanMap> isbgHumanMapList = new ArrayList<IsbgHumanMap>();
		Map<String, List<String>> pmWorkIDListMap = DataService.getPmWorkIDListMap(path);

		Map<String, IsbgProject> isbgProjectMap = DataService.getIsbgProjectMap(path);
		IsbgHumanMap isbgHumanMap = null;
		for (String key : isbgProjectMap.keySet()) {
			IsbgProject isbgProject = isbgProjectMap.get(key);
			// System.out.println(isbgProject);

			String staffName = "NULL";

			String projectID = isbgProject.getProjectId();
			String pmWorkID = isbgProject.getProjectMgrWorkID();

			/** 项目开始时间 */
			String inProDate = isbgProject.getProjectStartDateTime();

			/** 项目预计结束时间 */
			String predictOutProDate = isbgProject.getProjectEndDateTime();

			/** 项目结束时间（如果项目预计结束时间在今天和今天之后，则为上项目，否则为下项目 */
			String inProState = "上项目";

			/** 项目结束时间（如果在今天和今天之后，则为NULL */
			String outProDate = "NULL";

			if (DateUtil.beforeTotal(predictOutProDate)) {
				outProDate = predictOutProDate;
				inProState = "下项目 ";
			}

			/** 默认为true */
			String isPay = "true";

			List<String> workIDList = pmWorkIDListMap.get(pmWorkID);
			for (String workID : workIDList) {
				isbgHumanMap = new IsbgHumanMap();

				isbgHumanMap.setStaffWorkID(workID);
				isbgHumanMap.setStaffName(staffName);
				isbgHumanMap.setProjectID(projectID);
				isbgHumanMap.setInProDate(inProDate);
				isbgHumanMap.setInProState(inProState);
				isbgHumanMap.setOutProDate(outProDate);
				isbgHumanMap.setPredictOutProDate(predictOutProDate);
				isbgHumanMap.setIsPay(isPay);

				// TODO
				int randomNumber = MathUtil.getRandomByRange(1, 1000);
				// 如果随机数是4、5、6，就让他晚入项目这么多天
				if (3 < randomNumber && randomNumber < 7) {
					isbgHumanMap.setInProDate(DateUtil.getNextDate(inProDate, randomNumber));
					System.out.println("############### " + workID + "\t" + inProDate + "\t" + randomNumber);
				}

				isbgHumanMapList.add(isbgHumanMap);
			}
		}

		Collections.sort(isbgHumanMapList, new IsbgHumanMapComparator());

		return isbgHumanMapList;
	}

	public static List<PdrcBsmDispatch> getPdrcBsmDispatchList(String path) {
		List<PdrcBsmDispatch> pdrcBsmDispatchList = new ArrayList<PdrcBsmDispatch>();
		List<IsbgHumanMap> isbgHumanMapList = DataService.getIsbgHumanMapListInfo(path);
		Map<String, String> workIDBsmMap = DataService.getWorkIDBsmMap(path);
		PdrcBsmDispatch pdrcBsmDispatch = null;
		for (IsbgHumanMap isbgHumanMap : isbgHumanMapList) {

			String staffWorkID = isbgHumanMap.getStaffWorkID();

			String projectID = isbgHumanMap.getProjectID();
			String inProDate = isbgHumanMap.getInProDate();

			String predictOutProDate = isbgHumanMap.getPredictOutProDate();

			String bsmState = "1";
			String bsm = "0";

			List<String> dateList = DateUtil.getMonthBetween(inProDate, predictOutProDate);
			for (String monthStr : dateList) {
				pdrcBsmDispatch = new PdrcBsmDispatch();

				pdrcBsmDispatch.setProjectID(projectID);
				pdrcBsmDispatch.setStaffWorkID(staffWorkID);

				String dispatchMonth = monthStr;
				String confrimTime = "NULL";

				if (DateUtil.beforeTotal(predictOutProDate)) {
					confrimTime = predictOutProDate;

					// 已评价
					bsmState = "3";

					int randomNumber = MathUtil.getRandomByRange(8, 15);
					double dRandomNumber = randomNumber * 0.1;
					String baseBsm = workIDBsmMap.get(staffWorkID);

					Double dBsm = dRandomNumber * Double.parseDouble(baseBsm);

					if ("0.0".equals(dBsm.toString())) {
						System.out.println("#####");
					}

					bsm = MathUtil.setScale(dBsm, 2).toString();
				}

				pdrcBsmDispatch.setDispatchMonth(dispatchMonth);
				pdrcBsmDispatch.setConfrimTime(confrimTime);

				pdrcBsmDispatch.setBsmState(bsmState);
				pdrcBsmDispatch.setBsm(bsm);

				pdrcBsmDispatchList.add(pdrcBsmDispatch);
			}
		}
		
		Collections.sort(pdrcBsmDispatchList, new PdrcBsmDispatchComparator());

		return pdrcBsmDispatchList;
	}

	public static List<PdrcEnpPrize> getPdrcEnpPrizeList(String path) {
		List<PdrcEnpPrize> pdrcEnpPrizeList = new ArrayList<PdrcEnpPrize>();
		List<PdrcBsmDispatch> pdrcBsmDispatchList = DataService.getPdrcBsmDispatchList(path);
		Map<String, List<String>> bsmMap = new HashMap<String, List<String>>();
		List<String> bsmList = null;
		for (PdrcBsmDispatch pdrcBsmDispatch : pdrcBsmDispatchList) {

			String staffWorkID = pdrcBsmDispatch.getStaffWorkID();

			String dispatchMonth = pdrcBsmDispatch.getDispatchMonth();

			String bsmState = pdrcBsmDispatch.getBsmState();
			String bsm = pdrcBsmDispatch.getBsm();
			String key = staffWorkID + ":" + dispatchMonth;

			bsmList = bsmMap.get(key);
			if (null == bsmList || 1 > bsmList.size()) {
				bsmList = new ArrayList<>();
			}
			if ("3".equals(bsmState)) {
				bsmList.add(bsm);
				bsmMap.put(key, bsmList);
			}

			// System.out.println(pdrcBsmDispatch);
		}

		PdrcEnpPrize pdrcEnpPrize = null;
		for (String key : bsmMap.keySet()) {
			pdrcEnpPrize = new PdrcEnpPrize();
			List<String> tempBsmList = bsmMap.get(key);

			String[] arr = key.split(":");
			pdrcEnpPrize.setWorkID(arr[0]);
			pdrcEnpPrize.setMonthDate(arr[1]);
			Double p = MathUtil.setScale(MathUtil.sumNumberList(tempBsmList), 2);
			// System.out.println("P:\t" + p);
			p *= 1000;
			// System.out.println("P:\t" + p);
			DecimalFormat df = new DecimalFormat("#");
			pdrcEnpPrize.setPrize(df.format(p));

			pdrcEnpPrizeList.add(pdrcEnpPrize);
		}

		Collections.sort(pdrcEnpPrizeList, new PdrcEnpPrizeComparator());
		return pdrcEnpPrizeList;
	}

}
