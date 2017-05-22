package com.coderdream.gensql.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.coderdream.readfolder.util.FileUtil;
import com.coderdream.util.ExcelUtil;

public class GenSqlService {

	/**
	 * 
	 * 
	 * <pre>
	 * INSERT INTO [BJC_Finance].[dbo].[T_Users]
	       ([ID]
	       ,[UserName]
	       ,[UserPWD]
	       ,[CreateDate]
	       ,[DepartID]
	       ,[Email]
	       ,[WorkId]
	       ,[DepartName]
	       ,[IsIntro]
	       ,[UserImage]
	       ,[IsLock])
	 VALUES
	       (<ID, nvarchar(300),>
	       ,<UserName, nvarchar(500),>
	       ,<UserPWD, nvarchar(500),>
	       ,<CreateDate, date,>
	       ,<DepartID, int,>
	       ,<Email, nvarchar(200),>
	       ,<WorkId, varchar(200),>
	       ,<DepartName, nvarchar(200),>
	       ,<IsIntro, int,>
	       ,<UserImage, image,>
	       ,<IsLock, int,>)
	GO
	 * </pre>
	 * 
	 * @param path
	 * @param sheetName
	 * @return
	 */
	public static List<String> genSqlScriptList(String path, String sheetName, Boolean idFlag) {
		List<String> sqlScriptList = new ArrayList<String>();
		StringBuffer stringBuffer = new StringBuffer();
		try {
			List<String[]> arrayList = ExcelUtil.readAllData(path, sheetName);
			int listSize = arrayList.size();
			if (listSize <= 0) {
				return null;
			}
			String[] columnNameArray = arrayList.get(0);
			StringBuffer firstrPart = new StringBuffer("INSERT INTO [dbo].[" + sheetName + "] (");
			if (idFlag) {
				firstrPart.append("[ID],");
			}
			for (int i = 0; i < columnNameArray.length; i++) {
				String string = columnNameArray[i];
				if (i == columnNameArray.length - 1) {
					firstrPart.append(string);
				} else {
					firstrPart.append(string + ",");
				}
			}
			firstrPart.append(") VALUES");
			for (int j = 1; j < listSize; j++) {
				String[] arrayStr = arrayList.get(j);
				StringBuffer secondPart = new StringBuffer("(");
				if (idFlag) {
					secondPart.append("newid(),");
				}
				for (int i = 0; i < arrayStr.length; i++) {
					String string = arrayStr[i];
					// System.out.println("columnName:" + columnNameArray[i] +
					// "\t" + "columnValue:" + string);
					// if (null == string ||
					// "NULL".equalsIgnoreCase(string.trim()) ||
					// "".equals(string.trim())) {
					// 01cd27c0-b9ab-4c11-a3ef-a9ce092baad4

//					if (i < columnNameArray.length
//							&& "01cd27c0-b9ab-4c11-a3ef-a9ce092baad4".equalsIgnoreCase(string.trim())) {
//						System.out.println("####");
//					}

					if (i < columnNameArray.length && (null == string || "NULL".equalsIgnoreCase(string.trim()))) {
						System.out.println("columnName:" + columnNameArray[i] + "\t" + "columnValue:" + string);
						if (i == arrayStr.length - 1) {
							secondPart.append("null");
						} else {
							secondPart.append("null,");
						}
					} else {
						if (i == arrayStr.length - 1) {
							secondPart.append("N'" + string + "'");
						} else {
							secondPart.append("N'" + string + "'" + ",");
						}
					}
				}
				stringBuffer.append(firstrPart);
				stringBuffer.append(secondPart);
				stringBuffer.append(");");
				sqlScriptList.add(stringBuffer.toString());
				stringBuffer = new StringBuffer();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sqlScriptList;
	}

	public static void genSqlScriptFile(String path, String sheetName, Boolean idFlag, String filename,
			String charsetName) {
		List<String> sqlScriptList = GenSqlService.genSqlScriptList(path, sheetName, idFlag);
		FileUtil.write(sqlScriptList, filename, charsetName);
	}

	public static void genSqlScriptFileTotal(String path, Map<String, Boolean> tableNameMap, String filename,
			String charsetName) {
		List<String> totalSqlScriptList = new ArrayList<String>();
		for (String key : tableNameMap.keySet()) {
			List<String> sqlScriptList = GenSqlService.genSqlScriptList(path, key, tableNameMap.get(key));
			totalSqlScriptList.addAll(sqlScriptList);
		}

		FileUtil.write(totalSqlScriptList, filename, charsetName);
	}

}
