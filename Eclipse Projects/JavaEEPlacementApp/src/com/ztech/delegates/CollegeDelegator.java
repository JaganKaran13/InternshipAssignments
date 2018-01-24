package com.ztech.delegates;

import java.sql.SQLException;
import java.util.logging.Logger;

import com.ztech.dao.OthersDAOImpl;

public class CollegeDelegator {

	private static Logger logger = Logger.getLogger(CollegeDelegator.class.getName());

	public int validateCollegeLogin(String deptname, String password) {
		OthersDAOImpl othersDAO;
		CollegeValidator cv;
		try {
			othersDAO = new OthersDAOImpl();
			cv = new CollegeValidator();
			if (deptname.equals("") || password.equals("")) {
				return -1;
			} else if (!cv.validateCollegeLogin(deptname, password)) {
				logger.warning("Validation problem");
				return 0;
			} else {
				String department = othersDAO.validateTeacher(deptname, password);
				if (department.equals("")) {
					return 0;
				} else if (department.equals("ADMIN")) {
					return 1;
				} else if (department.equals("DEAN")) {
					return 2;
				} else {
					return 3;
				}
			}
		} catch (SQLException e) {
			logger.warning("Error connecting it with MySQL");
		}
		return 0;
	}

}
