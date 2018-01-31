package com.ztech.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ztech.Constants.Constants;
import com.ztech.beans.FetchApplicationBean;
import com.ztech.beans.StudentDetails;
import com.ztech.beans.YearDataBean;
import com.ztech.dbutils.DBUtils;

public class OthersDAOImpl implements OthersDAO {

	private final Logger logger = Logger.getLogger(OthersDAOImpl.class.getName());
	private static Connection conn = null;
	private static PreparedStatement pst = null;
	private static ResultSet rs = null, rs1 = null;

	public String checkEligibilty(int regno, int companyid) throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DBUtils.getConnection();
			pst = conn.prepareStatement(Constants.CHECK_ELIGIBILITY);
			pst.setInt(1, regno);
			pst.setInt(2, companyid);
			rs = pst.executeQuery();
			if (!rs.next()) {
				logger.warning("The company ID or registration number is wrong.");
				return "Enter a valid tregister number.";
			}
			if (rs.getFloat(1) < rs.getFloat(3) || rs.getInt(2) > rs.getInt(4)) {
				return "You are not eligible to sit for this company.";
			} else {
				return "";
			}
		} catch (SQLException e) {
			logger.warning("Error connecting with MySQL");
		} catch (ClassNotFoundException e) {
			logger.warning("Class not found for SQL Driver.");
		} finally {
			DBUtils.closeConnection(conn, pst, rs);
		}
		return "";
	}

	public int noOfStudentsPlaced(String deptName) throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DBUtils.getConnection();
			if (deptName.equals("")) {
				pst = (PreparedStatement) conn.prepareStatement(Constants.TOTAL_STUDENTS_PLACED);
				pst.setString(1, "yes");
				rs = pst.executeQuery();
				rs.next();
				return rs.getInt(1);
			} else {
				pst = (PreparedStatement) conn.prepareStatement(Constants.TOTAL_STUDENTS_PLACED_DEPARTMENT);
				pst.setString(1, deptName);
				pst.setString(2, "yes");
				rs = pst.executeQuery();
				rs.next();
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			logger.info("Error connecting it with MySQL");
		} catch (ClassNotFoundException e) {
			logger.warning("Class not found for SQL Driver.");
		} finally {
			DBUtils.closeConnection(conn, pst, rs);
		}
		return -1;
	}

	public int placementPercentage(String deptName) throws SQLException {
		int result, totalCount, placedCount;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DBUtils.getConnection();
			if (deptName.equals("")) {
				pst = (PreparedStatement) conn.prepareStatement(Constants.TOTAL_STUDENTS);
				rs = pst.executeQuery();
				rs.next();
				totalCount = rs.getInt(1);
				pst = (PreparedStatement) conn.prepareStatement(Constants.TOTAL_STUDENTS_PLACED);
				pst.setString(1, "yes");
				rs = pst.executeQuery();
				rs.next();
				placedCount = rs.getInt(1);
				result = (placedCount * 100 / totalCount);
			} else {
				pst = (PreparedStatement) conn.prepareStatement(Constants.TOTAL_STUDENTS_DEPARTMENT);
				pst.setString(1, deptName);
				rs = pst.executeQuery();
				rs.next();
				totalCount = rs.getInt(1);
				pst = (PreparedStatement) conn.prepareStatement(Constants.TOTAL_STUDENTS_PLACED_DEPARTMENT);
				pst.setString(1, deptName);
				pst.setString(2, "yes");
				rs = pst.executeQuery();
				rs.next();
				placedCount = rs.getInt(1);
				result = (placedCount * 100 / totalCount);
			}
			return result;
		} catch (SQLException e) {
			logger.warning("Error retrieving values from MySQL");
		} catch (ClassNotFoundException e) {
			logger.warning("Class not found for SQL Driver.");
		} finally {
			DBUtils.closeConnection(conn, pst, rs);
		}
		return -1;
	}

	public String validateTeacher(String department, String password) throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DBUtils.getConnection();
			pst = (PreparedStatement) conn.prepareStatement(Constants.VALIDATE_TEACHER);
			pst.setString(1, department);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException e) {
			logger.warning("Error connecting it with MySQL");
			return "";
		} catch (ClassNotFoundException e) {
			logger.warning("Class not found for SQL Driver.");
		} finally {
			DBUtils.closeConnection(conn, pst, rs);
		}
		return "";
	}

	public void updateStudentPlacedCount() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DBUtils.getConnection();
			pst = (PreparedStatement) conn.prepareStatement(Constants.GET_DETAILS_CURRENT_YEAR);
			pst.setInt(1, 2018);
			rs = pst.executeQuery();
			rs.next();
			pst = (PreparedStatement) conn.prepareStatement(Constants.UPDATE_DETAILS_CURRENT_YEAR);
			pst.setInt(1, rs.getInt(3) + 1);
			pst.setInt(2, 2018);
			pst.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.WARNING, "Error connecting it with MySQL");
		} catch (ClassNotFoundException e) {
			logger.log(Level.WARNING, "Class not found for SQL Driver.");
		} finally {
			DBUtils.closeConnection(conn, pst, rs);
		} 
	}
	
	public boolean enterStudentsPlaced(int companyid, int regno) throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DBUtils.getConnection();
			pst = (PreparedStatement) conn.prepareStatement(Constants.INSERT_STUDENTS_PLACED);
			pst.setInt(1, companyid);
			pst.setInt(2, regno);
			pst.executeUpdate();
			pst = (PreparedStatement) conn.prepareStatement(Constants.UPDATE_PLACED_STATUS);
			pst.setString(1, "yes");
			pst.setInt(2, regno);
			pst.executeUpdate();
			pst = (PreparedStatement) conn.prepareStatement(Constants.UPDATE_APPLY_STATUS);
			pst.setInt(1, regno);
			pst.setInt(2, companyid);
			pst.setString(3, "placed");
			pst.executeUpdate();
			updateStudentPlacedCount();
			return true;
		} catch (SQLException e) {
			logger.warning("Error connecting it with MySQL");
		} catch (ClassNotFoundException e) {
			logger.warning("Class not found for SQL Driver.");
		} finally {
			DBUtils.closeConnection(conn, pst, rs);
		}
		return false;
	}

	public ArrayList<StudentDetails> displayDetailsDepartment(String deptName) throws SQLException {
		ArrayList<StudentDetails> studentArrayList = new ArrayList<StudentDetails>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			StudentDetails studentDetails;
			conn = DBUtils.getConnection();
			pst = (PreparedStatement) conn.prepareStatement(Constants.DISPLAY_DEPARTMENT_ALL);
			pst.setString(1, deptName);
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
			logger.warning("Error connecting it with MySQL.");
		} catch (ClassNotFoundException e) {
			logger.warning("Class for MySQL driver not found.");
		} finally {
			DBUtils.closeConnection(conn, pst, rs);
		}
		return studentArrayList;
	}
	
	public ArrayList<String> getDepartmentList() throws SQLException {
		ArrayList<String> departmentList = new ArrayList<String>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DBUtils.getConnection();
			pst = (PreparedStatement) conn.prepareStatement(Constants.GET_DEPARTMENT_LIST);
			rs = pst.executeQuery();
			while (rs.next()) {
				departmentList.add(rs.getString(1));
			}
			return departmentList;
		} catch (SQLException e) {
			logger.warning("Error connecting it with MySQL");
		} catch (ClassNotFoundException e) {
			logger.warning("Class not found for SQL Driver.");
		} finally {
			DBUtils.closeConnection(conn, pst, rs);
		}
		return departmentList;
	}
	
	public ArrayList<Integer> getTotalStudentsList() throws SQLException {
		ArrayList<Integer> studentsCountList = new ArrayList<Integer>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DBUtils.getConnection();
			pst = (PreparedStatement) conn.prepareStatement(Constants.GET_STUDENTS_COUNT_LIST);
			rs = pst.executeQuery();
			while (rs.next()) {
				studentsCountList.add(rs.getInt(1));
			}
			return studentsCountList;
		} catch (SQLException e) {
			logger.warning("Error connecting it with MySQL");
		} catch (ClassNotFoundException e) {
			logger.warning("Class not found for SQL Driver.");
		} finally {
			DBUtils.closeConnection(conn, pst, rs);
		}
		return null;
	}

	public String getCompanyName(int companyid) throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DBUtils.getConnection();
			pst = (PreparedStatement) conn.prepareStatement(Constants.GET_COMPANY_NAME);
			pst.setInt(1, companyid);
			rs = pst.executeQuery();
			rs.next();
			String companyName = rs.getString(1);
			return companyName;
		} catch (SQLException e) {
			e.printStackTrace();
			logger.warning("Error connecting it with MySQL");
		} catch (ClassNotFoundException e) {
			logger.warning("Class not found for SQL Driver.");
		} finally {
			DBUtils.closeConnection(conn, pst, rs);
		}
		return null;
	}

	public boolean insertStudentApplication(int regno, int companyid, String applyStatus) throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DBUtils.getConnection();
			pst = (PreparedStatement) conn.prepareStatement(Constants.VERIFY_PLACED_OR_NOT);
			pst.setInt(1, regno);
			rs = pst.executeQuery();
			if(rs.next()) {
				logger.warning("The student is placed.");
				return false;
			}
			pst = (PreparedStatement) conn.prepareStatement(Constants.CHECK_APPLY_STATUS);
			pst.setInt(1, regno);
			pst.setInt(2, companyid);
			rs = pst.executeQuery();
			if(rs.next()) {
				pst = (PreparedStatement) conn.prepareStatement(Constants.UPDATE_APPLY_STATUS);
				pst.setInt(1, regno);
				pst.setInt(2, companyid);
				pst.setString(3, applyStatus);
				pst.executeUpdate();
				return true;
			}
			pst = (PreparedStatement) conn.prepareStatement(Constants.INSERT_APPLY_STATUS);
			pst.setInt(1, regno);
			pst.setInt(2, companyid);
			pst.setString(3, applyStatus);
			pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			logger.warning("Error connecting it with MySQL");
		} catch (ClassNotFoundException e) {
			logger.warning("Class not found for SQL Driver.");
		} finally {
			DBUtils.closeConnection(conn, pst, rs);
		}
		return false;
	}
	
	public ArrayList<StudentDetails> getInterestedStudentsList(int companyid) throws SQLException {
		ArrayList<StudentDetails> studentInterestedList  = new ArrayList<StudentDetails>();
		StudentDetails studentDetails;
		try {
			conn = DBUtils.getConnection();
			pst = (PreparedStatement) conn.prepareStatement(Constants.GET_INTERESTED_STUDENTS);
			pst.setString(1, "no");
			pst.setInt(2, companyid);
			pst.setString(3, "yes");
			rs = pst.executeQuery();
			while(rs.next()) {
				studentDetails = new StudentDetails();
				studentDetails.setRegno(rs.getInt(1));
				studentDetails.setName(rs.getString(2));
				studentDetails.setDeptName(rs.getString(3));
				studentDetails.setCgpa(rs.getFloat(4));
				studentDetails.setArrears(rs.getInt(5));
				studentInterestedList.add(studentDetails);
			}
			return studentInterestedList;
		} catch(SQLException e) {
			logger.log(Level.WARNING, "Error retrieving interested students list from MySQL.");
		} finally {
			DBUtils.closeConnection(conn, pst, rs);
		}
		return null;
	}

	public ArrayList<FetchApplicationBean> getApplicationStatus(int regno) {
		ArrayList<FetchApplicationBean> applicationStatusList = new ArrayList<FetchApplicationBean>();
		FetchApplicationBean fetchApplication;
		int companyid;
		try {
			conn = DBUtils.getConnection();
			pst = (PreparedStatement) conn.prepareStatement(Constants.GET_COMPANY_LIST);
			rs = pst.executeQuery();
			while(rs.next()) {
				companyid = rs.getInt(1);
				pst = (PreparedStatement) conn.prepareStatement(Constants.FETCH_APPLICATIONS);
				pst.setInt(1, regno);
				pst.setInt(2, companyid);
				rs1 = pst.executeQuery();
				fetchApplication = new FetchApplicationBean();
				if(!rs1.next()) {
					fetchApplication.setCompanyName(rs.getString(2));
					fetchApplication.setApplicationStatus("-");
					fetchApplication.setCompanyid(companyid);
				} else {
					fetchApplication.setCompanyName(rs1.getString(1));
					fetchApplication.setCompanyid(companyid);
					if(rs1.getString(2).equals("yes")) {
						fetchApplication.setApplicationStatus("Applied");
					} else if(rs1.getString(2).equals("no")) {
						fetchApplication.setApplicationStatus("Declined");
					} else {
						fetchApplication.setApplicationStatus("Placed");
					}
				}
				applicationStatusList.add(fetchApplication);
			}
			return applicationStatusList;
		} catch(SQLException e) {
			logger.log(Level.WARNING, "Error retrieving interested students list from MySQL.");
		} finally {
			DBUtils.closeConnection(conn, pst, rs);
			DBUtils.closeConnection(null, null, rs1);
		}
		return null;
	}
	
	public ArrayList<YearDataBean> getByYearDetails() {
		ArrayList<YearDataBean> yearBeanList = new ArrayList<YearDataBean>();
		YearDataBean yearDataBean;
		try {
			conn = DBUtils.getConnection();
			pst = (PreparedStatement) conn.prepareStatement(Constants.GET_DETAILS_BY_YEAR);
			rs = pst.executeQuery();
			while (rs.next()) {
				yearDataBean = new YearDataBean();
				yearDataBean.setYear(rs.getInt(1));
				yearDataBean.setStudentCount(rs.getInt(2));
				yearDataBean.setStudentPlaced(rs.getInt(3));
				yearBeanList.add(yearDataBean);
			}
			return yearBeanList;
		} catch(SQLException e) {
			logger.log(Level.WARNING, "Error retrieving previous year details from MySQL.");
		} finally {
			DBUtils.closeConnection(conn, pst, rs);
		}
		return yearBeanList;
	}
}
