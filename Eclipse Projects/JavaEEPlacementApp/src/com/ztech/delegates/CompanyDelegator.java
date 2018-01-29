package com.ztech.delegates;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ztech.beans.StudentDetails;
import com.ztech.dao.AdminDAO;
import com.ztech.dao.AdminDAOImpl;
import com.ztech.dao.OthersDAO;
import com.ztech.dao.OthersDAOImpl;

public class CompanyDelegator {

	private static Logger logger = Logger.getLogger(CompanyDelegator.class.getName());

	public int validateCompanyLogin(String companyid, String password) {
		AdminDAO adminDAO = new AdminDAOImpl();
		try {
			if (password.equals("")) {
				return -1;
			} else if (!adminDAO.validateCompany(Integer.parseInt(companyid), password)) {
				return 0;
			} else {
				return 1;
			}
		} catch (NumberFormatException e) {
			logger.warning("The company ID is not in numbers.");
			return 0;
		} catch (SQLException e) {
			logger.warning("Error connecting it with MySQL.");
		}
		return 0;
	}

	public String getCompanyName(int companyid) {
		OthersDAO othersDAO = new OthersDAOImpl();
		String companyName = "";
		try {
			companyName = othersDAO.getCompanyName(companyid);
		} catch (SQLException e) {
			logger.warning("Error connecting it with MySQL");
		}
		return companyName;
	}

	public ArrayList<StudentDetails> getInterestedStudentsList(String companyid) {
		ArrayList<StudentDetails> studentInterestedList = new ArrayList<StudentDetails>();
		OthersDAO othersDAO = new OthersDAOImpl();
		try {
			studentInterestedList = othersDAO.getInterestedStudentsList(Integer.parseInt(companyid));
			return studentInterestedList;
		} catch (SQLException e) {
			logger.warning("Error connecting it with MySQL");
		}
		return null;
	}
	
	public void insertStudentsPlaced(HttpServletRequest request, HttpServletResponse response) {
		int companyid = Integer.parseInt(request.getParameter("companyid"));
		String[] studentsPlacedList = request.getParameterValues("studentsPlaced");
		OthersDAO othersDAO = new OthersDAOImpl();
		for(int i = 0; i < studentsPlacedList.length; i++) {
			try {
				othersDAO.enterStudentsPlaced(companyid, Integer.parseInt(studentsPlacedList[i]));
			} catch (NumberFormatException e) {
				logger.log(Level.WARNING, "The register number is not in numbers");
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Error entering the data in the database.");
			}
		}
	}

}
