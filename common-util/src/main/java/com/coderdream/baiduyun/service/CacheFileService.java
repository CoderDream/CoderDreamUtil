package com.coderdream.baiduyun.service;

import java.util.List;

import com.coderdream.baiduyun.bean.CacheFile;
import com.coderdream.baiduyun.dao.CacheFileDao;

public class CacheFileService {

	public static List<CacheFile> getTotalCacheFileList() {
		List<CacheFile> cacheFileList = CacheFileDao.getTotalCacheFileList();
		return cacheFileList;
	}
	
	public static List<CacheFile> getTotalCacheFileListByFileSize(String filesize) {
		List<CacheFile> cacheFileList = CacheFileDao.getTotalCacheFileListByFileSize(filesize);
		return cacheFileList;
	}
	
}
