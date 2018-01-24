package com.ztech.delegates;

import java.sql.SQLException;
import java.util.logging.Logger;

import com.ztech.dao.AdminDAO;
import com.ztech.dao.AdminDAOImpl;
import com.ztech.dao.OthersDAO;
import com.ztech.dao.OthersDAOImpl;

public class CompanyDelegator {
	
	private static Logger logger = Logger.getLogger(CompanyDelegator.class.getName());
			
	public int validateCompanyLogin(String companyid, String password) {
		AdminDAO adminDAO = new AdminDAOImpl();
		try {
			if(password.equals("")) {
				return -1;
			} else if(!adminDAO.validateCompany(Integer.parseInt(companyid), password)) {
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
	
}
