package com.ztech.beans;

public class CompanyDetails {

	private String name, companyPassword;
	private int companyid, arrearCriteria;
	private float cgpaCriteria;

	// Getters
	public String getName() {
		return name;
	}

	public int getCompanyid() {
		return companyid;
	}

	public int getArrearCriteria() {
		return arrearCriteria;
	}

	public float getCgpaCriteria() {
		return cgpaCriteria;
	}

	public String getCompanyPassword() {
		return companyPassword;
	}
	
	// Setters
	public void setName(String name) {
		this.name = name;
	}

	public void setCompanyid(int companyid) {
		this.companyid = companyid;
	}

	public void setArrearCriteria(int arrearCriteria) {
		this.arrearCriteria = arrearCriteria;
	}

	public void setCgpaCriteria(float cgpaCriteria) {
		this.cgpaCriteria = cgpaCriteria;
	}

	public void setCompanyPassword(String companyPassword) {
		this.companyPassword = companyPassword;
	}

}
