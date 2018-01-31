package com.ztech.delegates;

import java.sql.SQLException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ztech.dao.OthersDAOImpl;

public class CollegeDelegator {

	private static Logger logger = Logger.getLogger(CollegeDelegator.class.getName());

	public int validateCollegeLogin(HttpServletRequest request, HttpServletResponse response) {
		String deptName = request.getParameter("deptname").trim().toUpperCase();
		String password = request.getParameter("password");
		OthersDAOImpl othersDAO;
		CollegeValidator cv;
		try {
			othersDAO = new OthersDAOImpl();
			cv = new CollegeValidator();
			if (deptName.equals("") || password.equals("")) {
				return -1;
			} else if (!cv.validateCollegeLogin(deptName, password)) {
				logger.warning("Validation problem");
				return 0;
			} else {
				String department = othersDAO.validateTeacher(deptName, password);
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
