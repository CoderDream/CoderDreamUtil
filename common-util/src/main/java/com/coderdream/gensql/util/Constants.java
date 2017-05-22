package com.coderdream.gensql.util;

public class Constants {
	
	public static String TABLE_STRUCTURE_FILE_NAME = "TableStructure.xlsx";
	
	public static String DATA_FILE_NAME = "Data11.xlsx";

	/** 项目统计开始时间 */
	public static String PROJECT_START_DATE = "2017-01-01";

	/** 项目统计结束时间 */
	public static String PROJECT_END_DATE = "2017-06-30";

	/** 项目周期最小天数 */
	public static int PROJECT_PERIOD_MIN = 7;

	/** 项目周期最大天数 */
	public static int PROJECT_PERIOD_MAX = 100;

	/** 项目周期最大天数是项目平均周期的倍数 */
	public static double PROJECT_PERIOD_AVG_TIMES = 2.1;

	/** 空闲随机数最小值 */
	public static int IDEL_RANDOM_MIN = 1;
	
	/** 空闲随机数最大值 */
	public static int IDEL_RANDOM_MAX = 100;

	/** 空闲最小天数 */
	public static int IDEL_RANDOM_DAYS_MIN = 2;
	
	/** 空闲最大天数 */
	public static int IDEL_RANDOM_DAYS_MAX = 7;

	/** BSM评价比率最小值的10倍 */
	public static Integer BSM_RATE_MIN = 8;

	/** BSM评价比率最大值的10倍 */
	public static Integer BSM_RATE_MAX = 15;

	/** BSM单价 */
	public static Integer PDRD_PRICE = 15000;

	/** 奖金 */
	public static Integer PDRD_BONUS = 1000;

	/** 未评价 */
	public static String BSM_STATE_DEFAULT = "1";
	
	/** 已分配 */
	public static String BSM_STATE_NOT_CONFIRM = "2";

	/** 已评价 */
	public static String BSM_STATE_CONFIRM = "3";

}
