package com.ztech.delegates;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ztech.beans.*;
import com.ztech.dao.AdminDAO;
import com.ztech.dao.AdminDAOImpl;

public class AdminDelegator {
	
	AdminValidator adminValidator = new AdminValidator();
	AdminDAO adminDAO;

	public String insertCompanyDetails(HttpServletRequest request, HttpServletResponse response) {
		CompanyDetails companyDetails = new CompanyDetails();
		try {
			String companyName = request.getParameter("companyName");
			if (companyName.equals("")) {
				return "Enter all the fields";
			}
			int companyid = Integer.parseInt(request.getParameter("companyid"));
			int arrearCriteria = Integer.parseInt(request.getParameter("arrearCriteria"));
			float cgpaCriteria = Float.parseFloat(request.getParameter("cgpaCriteria"));
			String companyPassword = request.getParameter("companyPassword");
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
		catch (NumberFormatException e) {
			return "Enter the details properly.";
		}
	}

	public String insertStudentDetails(HttpServletRequest request, HttpServletResponse response) {
		StudentDetails studentDetails = new StudentDetails();
		try {
			int regno = Integer.parseInt(request.getParameter("regno"));
			String studentName = request.getParameter("studentName");
			String email = request.getParameter("email");
			int arrears = Integer.parseInt(request.getParameter("arrears"));
			float cgpa = Float.parseFloat(request.getParameter("cgpa"));
			String deptName = request.getParameter("deptName");
			studentDetails.setRegno(regno);
			studentDetails.setName(studentName);
			studentDetails.setEmail(email);
			studentDetails.setArrears(arrears);
			studentDetails.setCgpa(cgpa);
			studentDetails.setDeptName(deptName);
			studentDetails.setPlacedStatus("no");
		} catch(NumberFormatException e) {
			return "Enter the inputs properly";
		}
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
	
	public ArrayList<StudentDetails> displayStudentDetails(String orderBy) throws SQLException {
		ArrayList<StudentDetails> studentArrayList = new ArrayList<StudentDetails>();
		adminDAO = new AdminDAOImpl();
		studentArrayList = adminDAO.displayStudentDetails(orderBy);
		return studentArrayList;
	}
	
	public ArrayList<CompanyDetails> displayCompanyDetails() throws SQLException {
		ArrayList<CompanyDetails> companyArrayList = new ArrayList<CompanyDetails>();
		adminDAO = new AdminDAOImpl();
		companyArrayList = adminDAO.displayCompanyDetails();
		return companyArrayList;
	}

	public String getPlacedInCompanyName(int regno) {
		String companyName;
		adminDAO = new AdminDAOImpl();
		companyName = adminDAO.getPlacedInCompanyName(regno);
		return companyName;
	}
	
}
