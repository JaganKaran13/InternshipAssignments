package com.ztech.servlet;

import java.util.logging.Logger;

public class Validator {

	private static Logger logger = Logger.getLogger(Validator.class.getName());

	public String validateStudentDetails(String firstname, String lastname, String deptname, String regno, String year) {
		if (firstname.length() == 0 || lastname.length() == 0 || deptname.length() == 0 || regno.length() == 0) {
			logger.warning("There are empty input fields");
			return "Enter all the fields.";
		}
		if (firstname.matches(".*\\d+.*")) {
			logger.warning("The first name is containing numbers.");
			return "The first name should not contain numbers";
		}
		if (lastname.matches(".*\\d+.*")) {
			logger.warning("The last name is containing numbers.");
			return "The last name should not contain numbers";
		}
		if (regno.matches(".*[a-zA-Z]+.*")) {
			logger.warning("The register number is containing alphabets.");
			return "The register number should not contain alphabets";
		}
		if (deptname.matches(".*\\d+.*")) {
			logger.warning("The department name is containing numbers.");
			return "The department name should not contain numbers";
		}
		if (Integer.parseInt(year) < 1 || Integer.parseInt(year) > 4) {
			logger.warning("Wrong input for year.");
			return "Year should be in the range of 1 to 4";
		}
		return "";
	}

}
