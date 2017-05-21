package com.coderdream.baiduyun.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.coderdream.baiduyun.bean.CacheFile;
import com.coderdream.baiduyun.util.DBUtil;

public class CacheFileDao {

	public static List<CacheFile> getTotalCacheFileList() {
		String sql = "select * from cache_file";
		List<CacheFile> cacheFileList = executeSql(sql);

		return cacheFileList;
	}

	public static List<CacheFile> getTotalCacheFileListByFileSize(String filesize) {
		String sql = "select * from cache_file where file_size>" + filesize;
		List<CacheFile> cacheFileList = executeSql(sql);

		return cacheFileList;
	}

	private static List<CacheFile> executeSql(String sql) {
		List<CacheFile> cacheFileList = new ArrayList<CacheFile>();
		Statement st = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			st = con.createStatement();
			// String sql = "select * from cache_file";
			rs = st.executeQuery(sql);
			while (rs.next()) {
				CacheFile cacheFile = new CacheFile();

				String id = rs.getString("id");
				String fid = rs.getString("fid");
				String parentPath = rs.getString("parent_path");
				String serverFilename = rs.getString("server_filename");
				String fileSize = rs.getString("file_size");
				 BigDecimal b1 = new BigDecimal(fileSize);
//				Integer size = Integer.parseInt(fileSize.trim());
				String md5 = rs.getString("md5");
				String isdir = rs.getString("isdir");
				String category = rs.getString("category");
				String serverMtime = rs.getString("server_mtime");
				String localMtime = rs.getString("local_mtime");

				cacheFile.setId(id);
				cacheFile.setFid(fid);
				cacheFile.setParentPath(parentPath);
				cacheFile.setServerFilename(serverFilename);
				cacheFile.setFileSize(b1.divide(new BigDecimal("1000000")) + "MB");
				cacheFile.setMd5(md5);
				cacheFile.setIsdir(isdir);
				cacheFile.setCategory(category);
				cacheFile.setServerMtime(serverMtime);
				cacheFile.setLocalMtime(localMtime);

				cacheFileList.add(cacheFile);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				st.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return cacheFileList;
	}
}
