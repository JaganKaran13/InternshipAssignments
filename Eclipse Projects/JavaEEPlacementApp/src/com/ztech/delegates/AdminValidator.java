package com.ztech.delegates;

import java.util.regex.Pattern;

import com.ztech.beans.CompanyDetails;
import com.ztech.beans.StudentDetails;

public class AdminValidator {
	
	private String companyName, companyPassword, studentName, deptName, email;
	private int arrearsCriteria, companyid, regno, arrears;
	private float cgpaCriteria, cgpa;
	public static final Pattern VALID_EMAIL_REGEX = Pattern.compile("^[a-z]+\\.{0,1}[_a-z0-9%+-]+@[a-z]+\\.[a-z]{2,3}$");

	public String validateCompanyDetails(CompanyDetails companyDetails) {
		companyid = companyDetails.getCompanyid();
		companyName = companyDetails.getName();
		arrearsCriteria = companyDetails.getArrearCriteria();
		cgpaCriteria = companyDetails.getCgpaCriteria();
		companyPassword = companyDetails.getCompanyPassword();
		if(companyPassword.length() == 0 || companyName.length() == 0) {
			return "Enter all the fields";
		} else if(companyPassword.length() < 5) {
			return "Password length is short";
		} else if(companyName.length() > 30) {
			return "Company name length is too big";
		} else if(arrearsCriteria < 0) {
			return "The arrear criteria should be a positive number";
		}
		return "";
	}

	public String validateStudentDetails(StudentDetails studentDetails) {
		studentName = studentDetails.getName();
		deptName = studentDetails.getDeptName();
		email = studentDetails.getEmail();
		regno = studentDetails.getRegno();
		arrears = studentDetails.getArrears();
		cgpa = studentDetails.getCgpa();
		if(deptName.matches(".*\\d+.*")) {
			return "The department name	contains numbers.";
		} else if(studentName.matches(".*\\d+.*")) {
			return "The student name contains numbers.";
		} else if(!VALID_EMAIL_REGEX.matcher(email).find()) {
			return "Invalid email address";
		} else if(cgpa < 0 || cgpa > 10) {
			return "CGPA should be in the range of 1 to 10";
		} else if(arrears < 0) {
			return "Arrears should be a positive number";
		}
		return "";
	}

}
