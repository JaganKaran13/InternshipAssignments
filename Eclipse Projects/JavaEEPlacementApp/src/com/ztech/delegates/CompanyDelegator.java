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
import com.ztech.singleton.StudentsPlaced;

public class CompanyDelegator {

	private static Logger logger = Logger.getLogger(CompanyDelegator.class.getName());
	private OthersDAO othersDAO;
	private StudentsPlaced studentsPlaced = StudentsPlaced.getInstance();

	public int validateCompanyLogin(HttpServletRequest request, HttpServletResponse response) {
		String companyid = request.getParameter("companyid");
		String password = request.getParameter("password");
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
		othersDAO = new OthersDAOImpl();
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
		othersDAO = new OthersDAOImpl();
		try {
			studentInterestedList = othersDAO.getInterestedStudentsList(Integer.parseInt(companyid));
			return studentInterestedList;
		} catch (SQLException e) {
			logger.warning("Error connecting it with MySQL");
		}
		return null;
	}

	public String insertStudentsPlaced(HttpServletRequest request, HttpServletResponse response) {
		int companyid = Integer.parseInt(request.getParameter("companyid"));
		String[] studentsPlacedList = request.getParameterValues("studentsPlaced");
		othersDAO = new OthersDAOImpl();
		if (studentsPlacedList == null) {
			return "Select the students who got placed";
		}
		for (int i = 0; i < studentsPlacedList.length; i++) {
			try {
				if (StudentsPlaced.isStudentPlaced(Integer.parseInt(studentsPlacedList[i]))) {
					return "The student selected with register number " + studentsPlacedList[i] +
							" is already placed";
				} else {
					studentsPlaced.insertStudentPlaced(Integer.parseInt(studentsPlacedList[i]));
					othersDAO.enterStudentsPlaced(companyid, Integer.parseInt(studentsPlacedList[i]));
				}
			} catch (NumberFormatException e) {
				logger.log(Level.WARNING, "The register number is not in numbers");
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Error entering the data in the database.");
			}
		}
		return "The students selected are placed in your company";
	}

	public String getCompanyId(HttpServletRequest request, HttpServletResponse response) {
		return request.getParameter("companyid");
	}
}
