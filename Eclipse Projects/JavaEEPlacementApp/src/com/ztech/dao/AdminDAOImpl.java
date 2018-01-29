package com.ztech.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Logger;

import com.ztech.Constants.Constants;
import com.ztech.beans.CompanyDetails;
import com.ztech.beans.StudentDetails;
import com.ztech.dbutils.DBUtils;

public class AdminDAOImpl implements AdminDAO {

	private static final Logger logger = Logger.getLogger(AdminDAOImpl.class.getName());
	private static Connection conn = null;
	private static PreparedStatement pst = null;
	private static ResultSet rs = null;

	public boolean insertStudentDetails(StudentDetails studentdet) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DBUtils.getConnection();
			pst = (PreparedStatement) conn.prepareStatement(Constants.CHECK_STUDENT);
			pst.setInt(1, studentdet.getRegno());
			rs = pst.executeQuery();
			if (rs.next()) {
				logger.severe("The student entry is already present");
				return false;
			}
			pst = (PreparedStatement) conn.prepareStatement(Constants.INSERT_STUDENT_DETAILS);
			pst.setString(2, studentdet.getName());
			pst.setString(3, studentdet.getEmail());
			pst.setString(6, studentdet.getPlacedStatus());
			pst.setString(7, studentdet.getDeptName());
			pst.setInt(1, studentdet.getRegno());
			pst.setInt(5, studentdet.getArrears());
			pst.setFloat(4, studentdet.getCgpa());
			pst.setString(8, "Raxit");
			Calendar cal = Calendar.getInstance();
			java.sql.Timestamp timestamp = new java.sql.Timestamp(cal.getTimeInMillis());
			pst.setTimestamp(9, timestamp);
			pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			logger.severe("Problem inserting the values into the database");
		} catch (ClassNotFoundException e) {
			logger.warning("Class for MySQL driver not found.");
		} finally {
			DBUtils.closeConnection(conn, pst, rs);
		}
		return false;
	}

	public boolean insertCompanyDetails(CompanyDetails companydet) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DBUtils.getConnection();
			pst = (PreparedStatement) conn.prepareStatement(Constants.CHECK_COMPANY);
			pst.setInt(1, companydet.getCompanyid());
			rs = pst.executeQuery();
			if (rs.next()) {
				logger.warning("The company details are already present");
				return false;
			}
			pst = (PreparedStatement) conn.prepareStatement(Constants.INSERT_COMPANY_DETAILS);
			pst.setString(2, companydet.getName());
			pst.setInt(1, companydet.getCompanyid());
			pst.setInt(4, companydet.getArrearCriteria());
			pst.setFloat(3, companydet.getCgpaCriteria());
			pst.setString(5, companydet.getCompanyPassword());
			pst.setString(6, "Raxit");
			Calendar cal = Calendar.getInstance();
			java.sql.Timestamp timestamp = new java.sql.Timestamp(cal.getTimeInMillis());
			pst.setTimestamp(7, timestamp);
			pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			logger.severe("Problem inserting the values into the database");
		} catch (ClassNotFoundException e) {
			logger.warning("Class for MySQL driver not found.");
		} finally {
			DBUtils.closeConnection(conn, pst, null);
		}
		return false;
	}

	public ArrayList<CompanyDetails> displayCompanyDetails() throws SQLException {
		try {
			CompanyDetails companyDetails;
			ArrayList<CompanyDetails> companyArrayList = new ArrayList<CompanyDetails>();
			Class.forName("com.mysql.jdbc.Driver");
			conn = DBUtils.getConnection();
			pst = (PreparedStatement) conn.prepareStatement(Constants.DISPLAY_COMPANY_ALL);
			rs = pst.executeQuery();
			while (rs.next()) {
				companyDetails = new CompanyDetails();
				companyDetails.setCompanyid(rs.getInt(1));
				companyDetails.setName(rs.getString(2));
				companyDetails.setCgpaCriteria(rs.getFloat(3));
				companyDetails.setArrearCriteria(rs.getInt(4));
				companyArrayList.add(companyDetails);
			}
			return companyArrayList;
		} catch (SQLException e) {
			logger.warning("Error connecting it with MySQL");
		} catch (ClassNotFoundException e) {
			logger.warning("Class for MySQL driver not found.");
		} finally {
			DBUtils.closeConnection(conn, pst, rs);
		}
		return null;
	}

	public ArrayList<StudentDetails> displayStudentDetails(String orderBy) throws SQLException {
		try {
			ArrayList<StudentDetails> studentArrayList = new ArrayList<StudentDetails>();
			StudentDetails studentDetails;
			Class.forName("com.mysql.jdbc.Driver");
			conn = DBUtils.getConnection();
			pst = (PreparedStatement) conn.prepareStatement(Constants.DISPLAY_STUDENT_ALL + orderBy);
			rs = pst.executeQuery();
			while (rs.next()) {
				studentDetails = new StudentDetails();
				studentDetails.setRegno(rs.getInt(1));
				studentDetails.setName(rs.getString(2));
				studentDetails.setArrears(rs.getInt(5));
				studentDetails.setCgpa(rs.getFloat(4));
				studentDetails.setEmail(rs.getString(3));
				studentDetails.setPlacedStatus(rs.getString(6));
				studentDetails.setDeptName(rs.getString(7));
				studentArrayList.add(studentDetails);
			}
			return studentArrayList;
		} catch (SQLException e) {
			logger.severe("Error connecting it with MySQL");
		} catch (ClassNotFoundException e) {
			logger.warning("Class for MySQL driver not found.");
		} finally {
			DBUtils.closeConnection(conn, pst, rs);
		}
		return null;
	}

	public boolean validateCompany(int companyid, String password) throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DBUtils.getConnection();
			pst = (PreparedStatement) conn.prepareStatement(Constants.VALIDATE_LOGIN);
			pst.setInt(1, companyid);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			logger.severe("Error connecting it with MySQL");
		} catch (ClassNotFoundException e) {
			logger.warning("Class for MySQL driver not found.");
		} finally {
			DBUtils.closeConnection(conn, pst, rs);
		}
		return false;
	}

}
