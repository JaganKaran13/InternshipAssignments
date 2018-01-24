package com.ztech.delegates;

import java.util.logging.Logger;

public class CollegeValidator {
	
	private static Logger logger = Logger.getLogger(CollegeValidator.class.getName());
	
	public boolean validateCollegeLogin(String deptname, String password) {
		if(deptname.matches(".*\\d+.*")) {
			logger.warning("Department name contains number");
			return false;
		}
		if(password.length() < 4) {
			logger.warning("Password length is short");
			return false;
		}
		return true;
	}

}
