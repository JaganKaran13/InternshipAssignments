package com.ztech.delegates;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ztech.beans.FetchApplicationBean;
import com.ztech.dao.*;

public class StudentDelegator {
	
	private static Logger logger = Logger.getLogger(StudentDelegator.class.getName());
	private OthersDAO othersDAO;

	public String applyForCompany(HttpServletRequest request, HttpServletResponse response) {
		try {
			int companyid = Integer.parseInt(request.getParameter("companyid"));
			int regno = Integer.parseInt(request.getParameter("regno"));
			othersDAO = new OthersDAOImpl();
			String responseMessage = othersDAO.checkEligibilty(regno, companyid);
			if(responseMessage.equals("")) {
				if(othersDAO.insertStudentApplication(regno, companyid, "yes")) {
					return "Congrats. You have applied for this company";
				} else {
					return "You are already placed.";
				}
			}
			return responseMessage;
		} catch (NumberFormatException e) {
			logger.warning("The given inputs are not in numbers.");
			return "Enter a valid register number";
		} catch (SQLException e) {
			logger.warning("Error retrieving values from MySQL.");
		}
		return null;
	}
	
	public String declineForCompany(HttpServletRequest request, HttpServletResponse response) {
		try {
			int companyid = Integer.parseInt(request.getParameter("companyid"));
			int regno = Integer.parseInt(request.getParameter("regno"));
			othersDAO = new OthersDAOImpl();
			if(othersDAO.insertStudentApplication(regno, companyid, "no")) {
				return "You have declined for this company";
			} else {
				return "You are already placed.";
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			logger.warning("The given inputs are not in numbers.");
			return "Enter a valid register number";
		} catch (SQLException e) {
			logger.warning("Error declining for the company in database.");
		}
		return null;
	}
	
	public ArrayList<FetchApplicationBean> getApplicationStatus(HttpServletRequest request, HttpServletResponse response) {
		othersDAO = new OthersDAOImpl();
		int regno = Integer.parseInt(request.getParameter("regno"));
		ArrayList<FetchApplicationBean> applicationStatusList = othersDAO.getApplicationStatus(regno);
		return applicationStatusList;
	}
	
}
