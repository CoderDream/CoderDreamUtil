package com.coderdream.gensql.bean;

import java.util.List;

/**
 */
public class PmRmRelation {

	private String pmWorkID;

	private String rmWorkID;

	private List<String> workIDList;

	public String getPmWorkID() {
		return pmWorkID;
	}

	public void setPmWorkID(String pmWorkID) {
		this.pmWorkID = pmWorkID;
	}

	public String getRmWorkID() {
		return rmWorkID;
	}

	public void setRmWorkID(String rmWorkID) {
		this.rmWorkID = rmWorkID;
	}

	public List<String> getWorkIDList() {
		return workIDList;
	}

	public void setWorkIDList(List<String> workIDList) {
		this.workIDList = workIDList;
	}

}