package com.ztech.delegates;

import com.ztech.beans.*;
import com.ztech.dao.AdminDAO;
import com.ztech.dao.AdminDAOImpl;

public class AdminDelegator {
	
	AdminValidator adminValidator = new AdminValidator();
	AdminDAO adminDAO;

	public String insertCompanyDetails(int companyid, String companyName, int arrearCriteria,
			float cgpaCriteria, String companyPassword) {
		CompanyDetails companyDetails = new CompanyDetails();
		companyDetails.setCompanyid(companyid);
		companyDetails.setName(companyName);
		companyDetails.setArrearCriteria(arrearCriteria);
		companyDetails.setCgpaCriteria(cgpaCriteria);
		companyDetails.setCompanyPassword(companyPassword);
		if(!adminValidator.validateCompanyDetails(companyDetails).equals("")) {
			return adminValidator.validateCompanyDetails(companyDetails);
		}
		adminDAO = new AdminDAOImpl();
		if(adminDAO.insertCompanyDetails(companyDetails)) {
			return "";
		}else {
			return "The company details are already present";
		}
	}

	public String insertStudentDetails(int regno, String studentName, String email, int arrears, float cgpa,
			String deptName) {
		StudentDetails studentDetails = new StudentDetails();
		studentDetails.setRegno(regno);
		studentDetails.setName(studentName);
		studentDetails.setEmail(email);
		studentDetails.setArrears(arrears);
		studentDetails.setCgpa(cgpa);
		studentDetails.setDeptName(deptName);
		studentDetails.setPlacedStatus("no");
		adminDAO = new AdminDAOImpl();
		if(!adminValidator.validateStudentDetails(studentDetails).equals("")) {
			return adminValidator.validateStudentDetails(studentDetails);
		}
		if(adminDAO.insertStudentDetails(studentDetails)) {
			return "";
		} else {
			return "The student details is already present.";
		}	
	}

}
