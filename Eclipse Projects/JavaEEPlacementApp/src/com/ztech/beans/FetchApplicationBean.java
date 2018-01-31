package com.ztech.beans;

public class FetchApplicationBean {

	private String applicationStatus;
	private String companyName;
	private int companyid;
	
	public int getCompanyid() {
		return companyid;
	}
	
	public void setCompanyid(int companyid) {
		this.companyid = companyid;
	}

	public String getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
}
