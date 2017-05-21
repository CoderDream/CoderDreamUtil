package com.coderdream.baiduyun.bean;

public class CacheFile {

	private String id;
	private String fid;
	private String parentPath;
	private String serverFilename;
	private String fileSize;
	private String md5;
	private String isdir;
	private String category;
	private String serverMtime;
	private String localMtime;
	//private String reserved1;// reserved2 reserved3 reserved4 reserved5
								// reserved6 reserved7 reserved8 reserved9
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public String getParentPath() {
		return parentPath;
	}
	public void setParentPath(String parentPath) {
		this.parentPath = parentPath;
	}
	public String getServerFilename() {
		return serverFilename;
	}
	public void setServerFilename(String serverFilename) {
		this.serverFilename = serverFilename;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public String getIsdir() {
		return isdir;
	}
	public void setIsdir(String isdir) {
		this.isdir = isdir;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getServerMtime() {
		return serverMtime;
	}
	public void setServerMtime(String serverMtime) {
		this.serverMtime = serverMtime;
	}
	public String getLocalMtime() {
		return localMtime;
	}
	public void setLocalMtime(String localMtime) {
		this.localMtime = localMtime;
	}
	@Override
	public String toString() {
		return "CacheFile [id=" + id + ", fid=" + fid + ", parentPath=" + parentPath + ", serverFilename="
				+ serverFilename + ", fileSize=" + fileSize + ", md5=" + md5 + ", isdir=" + isdir + ", category="
				+ category + ", serverMtime=" + serverMtime + ", localMtime=" + localMtime + "]";
	}
	
}
