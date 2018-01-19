package com.ztech.bean;


public class StudentBean {
	
	private String firstname, lastname, deptname, regno;
	private int year;
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public void setRegno(String regno) {
		this.regno = regno;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public String getDeptname() {
		return deptname;
	}
	
	public String getRegno() {
		return regno;
	}
	
	public int getYear() {
		return year;
	}
	
}
