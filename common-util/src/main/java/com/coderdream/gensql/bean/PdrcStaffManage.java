package com.coderdream.gensql.bean;

/**
 */
public class PdrcStaffManage {

	private String workID;
	
	private String tmWorkID;
	
	private String normalMam;
	
	private String salary;

		
	public String getWorkID() {
		return workID;
	}
	public void setWorkID(String workID) {
		this.workID = workID;
	}
	public String getTmWorkID() {
		return tmWorkID;
	}
	public void setTmWorkID(String rmWorkID) {
		this.tmWorkID = rmWorkID;
	}
	public String getNormalMam() {
		return normalMam;
	}
	public void setNormalMam(String normalMam) {
		this.normalMam = normalMam;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "PdrcStaffManage [workID=" + workID + ", tmWorkID=" + tmWorkID + ", normalMam=" + normalMam + ", salary="
				+ salary + "]";
	}
	
}