package com.coderdream.gensql.service;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.coderdream.gensql.bean.IsbgHumanMap;
import com.coderdream.gensql.bean.IsbgProject;
import com.coderdream.gensql.bean.PdrcBsmDispatch;
import com.coderdream.gensql.bean.PdrcEnpPrize;
import com.coderdream.gensql.bean.PdrcStaffManage;
import com.coderdream.gensql.bean.PdrcTm;
import com.coderdream.gensql.bean.PdrcTmSalary;
import com.coderdream.gensql.bean.PmRmRelation;

public class DataServiceTest {

	// private static final Logger logger =
	// LoggerFactory.getLogger(DataServiceTest.class);

	private String fileFolder;

	private String dataFileName;

	@Before
	public void setUp() throws Exception {
		fileFolder = getClass().getResource("../../../../").getFile().toString();
		dataFileName = "Data10.xlsx";
	}

	@Test
	public void testGetPdrcTmList() {
		String path = fileFolder + dataFileName;
		DataService dataService = new DataService();
		List<PdrcTm> list = dataService.getPdrcTmList(path);
		for (PdrcTm pdrcTm : list) {
			System.out.println(pdrcTm);
		}
	}

	@Test
	public void testGetPdrcStaffManageList() {
		String path = fileFolder + dataFileName;
		DataService dataService = new DataService();
		List<PdrcStaffManage> list = dataService.getPdrcStaffManageList(path);
		for (PdrcStaffManage pdrcStaffManage : list) {
			System.out.println(pdrcStaffManage);
		}
	}

	@Test
	public void testGetIsbgProjectList() {
		String path = fileFolder + dataFileName;
		List<IsbgProject> list = DataService.getIsbgProjectList(path);
		for (IsbgProject isbgProject : list) {
			System.out.println(isbgProject);
		}
	}

	@Test
	public void testGetPdrcStaffManageListMap() {
		String path = fileFolder + dataFileName;
		Map<String, List<PdrcStaffManage>> pdrcStaffManageListMap = DataService.getPdrcStaffManageListMap(path);

		for (String key : pdrcStaffManageListMap.keySet()) {

			List<PdrcStaffManage> pdrcStaffManageList = pdrcStaffManageListMap.get(key);

			System.out.println("TM WorkId\t" + key);
			for (PdrcStaffManage pdrcStaffManage : pdrcStaffManageList) {
				System.out.println("WorkId\t\t" + pdrcStaffManage.getWorkID());
			}
		}
	}

	@Test
	public void testGetPdrcTmSalaryList() {
		String path = fileFolder + dataFileName;
		List<PdrcTmSalary> pdrcTmSalaryList = DataService.getPdrcTmSalaryList(path);
		for (PdrcTmSalary pdrcTmSalary : pdrcTmSalaryList) {
			System.out.println(pdrcTmSalary);
		}
	}

	@Test
	public void testGetPdrcTmSalaryListWithDateRange() {
		String path = fileFolder + dataFileName;
		String startDateString = "2016-01-01";
		String endDateString = "2017-12-31";
		List<PdrcTmSalary> pdrcTmSalaryList = DataService.getPdrcTmSalaryListWithDateRange(path, startDateString,
				endDateString);
		System.out.println("########### size:  " + pdrcTmSalaryList.size());
		for (PdrcTmSalary pdrcTmSalary : pdrcTmSalaryList) {
			System.out.println(pdrcTmSalary);
		}
	}

	@Test
	public void testGetPmRmRelationList() {
		String path = fileFolder + dataFileName;

		List<PmRmRelation> pmRmRelationList = DataService.getPmRmRelationList(path);

		for (PmRmRelation pmRmRelation : pmRmRelationList) {
			String pmWorkID = pmRmRelation.getPmWorkID();
			String rmWorkID = pmRmRelation.getRmWorkID();
			List<String> workIDList = pmRmRelation.getWorkIDList();
			for (String workID : workIDList) {
				System.out.println(pmWorkID + "\t" + rmWorkID + "\t" + workID);
			}
		}
	}

	@Test
	public void testGetIsbgProjectListMap() {
		String path = fileFolder + dataFileName;
		Map<String, List<IsbgProject>> isbgProjectListMap = DataService.getIsbgProjectListMap(path);

		for (String key : isbgProjectListMap.keySet()) {

			List<IsbgProject> isbgProjectList = isbgProjectListMap.get(key);

			System.out.println("projectMgrWorkID\t" + key);
			for (IsbgProject isbgProject : isbgProjectList) {
				// System.out.println("WorkId\t\t" + isbgProject);
				System.out.println(isbgProject);
			}

		}
	}

	@Test
	public void testGetPmWorkIDListMap() {
		String path = fileFolder + dataFileName;
		Map<String, List<String>> pmWorkIDListMap = DataService.getPmWorkIDListMap(path);

		for (String key : pmWorkIDListMap.keySet()) {
			List<String> workIDList = pmWorkIDListMap.get(key);
			for (String workID : workIDList) {
				System.out.println(key + "\t" + workID);
			}
		}
	}

	@Test
	public void testGetIsbgHumanMapListInfo() {
		String path = fileFolder + dataFileName;
		List<IsbgHumanMap> isbgHumanMapList = DataService.getIsbgHumanMapListInfo(path);
		for (IsbgHumanMap isbgHumanMap : isbgHumanMapList) {
			System.out.println(isbgHumanMap);
		}
	}

	@Test
	public void testGetIsbgProjectListInfo() {
		String path = fileFolder + dataFileName;
		List<IsbgProject> totalIsbgProjectList = DataService.getIsbgProjectListInfo(path);
		for (IsbgProject isbgProject : totalIsbgProjectList) {
			System.out.println(isbgProject);
		}
	}

	@Test
	public void testGetIsbgProjectMap() {
		String path = fileFolder + dataFileName;
		Map<String, IsbgProject> isbgProjectMap = DataService.getIsbgProjectMap(path);

		for (String key : isbgProjectMap.keySet()) {
			IsbgProject isbgProject = isbgProjectMap.get(key);
			System.out.println(isbgProject);
		}
	}

	@Test
	public void testGetWorkIDBsmMap() {
		String path = fileFolder + dataFileName;
		Map<String, String> workIDBsmMap = DataService.getWorkIDBsmMap(path);

		for (String key : workIDBsmMap.keySet()) {
			String isbgProject = workIDBsmMap.get(key);
			System.out.println(key + "\t" + isbgProject);
		}
	}

	@Test
	public void testGetPdrcBsmDispatchList() {
		String path = fileFolder + dataFileName;
		List<PdrcBsmDispatch> pdrcBsmDispatchList = DataService.getPdrcBsmDispatchList(path);
		for (PdrcBsmDispatch pdrcBsmDispatch : pdrcBsmDispatchList) {
			System.out.println(pdrcBsmDispatch);
		}
	}

	@Test
	public void testGetPdrcEnpPrizeList() {
		String path = fileFolder + dataFileName;
		List<PdrcEnpPrize> pdrcEnpPrizeList = DataService.getPdrcEnpPrizeList(path);
		for (PdrcEnpPrize pdrcEnpPrize : pdrcEnpPrizeList) {
			System.out.println(pdrcEnpPrize);
		}
	}

}
