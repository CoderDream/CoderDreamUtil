package com.coderdream.gensql.bean;

/**
 */
public class MemberParticipate {

	private String staffWorkID;

	private String projectID;
	
	private String projectStartDate;

	private String projectEndDate;
	
	private String monthDate;

	private String participateRate;

	public String getStaffWorkID() {
		return staffWorkID;
	}

	public void setStaffWorkID(String staffWorkID) {
		this.staffWorkID = staffWorkID;
	}

	public String getProjectID() {
		return projectID;
	}

	public void setProjectID(String projectID) {
		this.projectID = projectID;
	}

	public String getProjectStartDate() {
		return projectStartDate;
	}

	public void setProjectStartDate(String projectStartDate) {
		this.projectStartDate = projectStartDate;
	}

	public String getProjectEndDate() {
		return projectEndDate;
	}

	public void setProjectEndDate(String projectEndDate) {
		this.projectEndDate = projectEndDate;
	}

	public String getMonthDate() {
		return monthDate;
	}

	public void setMonthDate(String monthDate) {
		this.monthDate = monthDate;
	}

	public String getParticipateRate() {
		return participateRate;
	}

	public void setParticipateRate(String participateRate) {
		this.participateRate = participateRate;
	}

	@Override
	public String toString() {
		return "MemberParticipate [staffWorkID=" + staffWorkID + ", projectID=" + projectID + ", projectStartDate="
				+ projectStartDate + ", projectEndDate=" + projectEndDate + ", monthDate=" + monthDate
				+ ", participateRate=" + participateRate + "]";
	}
	
}