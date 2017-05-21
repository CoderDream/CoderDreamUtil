package com.coderdream.baiduyun.service;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.coderdream.baiduyun.bean.CacheFile;

public class CacheFileServiceTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetTotalCacheFileList() {
		List<CacheFile> cacheFileList = CacheFileService.getTotalCacheFileList();
		for (int i = 0; i < 100; i++) {
			System.out.println(cacheFileList.get(i));
		}
	}

	@Test
	public void testGetTotalCacheFileListByFileSize() {
		String filesize = "1000000000";
		List<CacheFile> cacheFileList = CacheFileService.getTotalCacheFileListByFileSize(filesize);
		int size = cacheFileList.size();
		System.out.println(cacheFileList.size());
		if (size > 1000) {
			size = 1000;
		}
		for (int i = 0; i < size; i++) {
			CacheFile cacheFile = cacheFileList.get(i);
			System.out.println(
					cacheFile.getFileSize() + "\t" + cacheFile.getServerFilename() + "\t" + cacheFile.getParentPath());
		}
	}

}
