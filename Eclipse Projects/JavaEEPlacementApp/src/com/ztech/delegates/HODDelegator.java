package com.ztech.delegates;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ztech.beans.StudentDetails;
import com.ztech.dao.OthersDAO;
import com.ztech.dao.OthersDAOImpl;

public class HODDelegator {
	
	private static Logger logger = Logger.getLogger(HODDelegator.class.getName());
	OthersDAO othersDAO;
	
	public int noOfStudentsPlaced(String deptName) {
		othersDAO = new OthersDAOImpl();
		try {
			return othersDAO.noOfStudentsPlaced(deptName);
		} catch (SQLException e) {
			logger.log(Level.WARNING, "Error retrieving data from MySQL.");
		}
		return 0;
	}
	
	public int placementPercentage(String deptName) {
		othersDAO = new OthersDAOImpl();
		try {
			return othersDAO.placementPercentage(deptName);
		} catch (SQLException e) {
			logger.log(Level.WARNING, "Error retrieving data from MySQL.");
		}
		return 0;
	}

	public ArrayList<StudentDetails> displayDetailsDepartment(String deptName) {
		ArrayList<StudentDetails> studentArrayList = new ArrayList<StudentDetails>();
		othersDAO = new OthersDAOImpl();
		try {
			studentArrayList = othersDAO.displayDetailsDepartment(deptName);
		} catch (SQLException e) {
			logger.log(Level.WARNING, "Error retrieving data from MySQL.");
		}
		return studentArrayList;
	}
	
}
