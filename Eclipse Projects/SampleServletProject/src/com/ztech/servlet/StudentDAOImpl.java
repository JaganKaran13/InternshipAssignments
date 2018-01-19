package com.ztech.servlet;

import java.sql.*;
import java.util.logging.Logger;

import com.ztech.bean.StudentBean;
import com.ztech.constants.Constants;
import com.ztech.dbutils.DBUtils;

public class StudentDAOImpl implements StudentDAO{
	
	private Connection conn;
	private PreparedStatement pst;
	private ResultSet rs;
	private static Logger logger = Logger.getLogger(StudentDAOImpl.class.getName());

	public boolean insertStudentDetails(StudentBean student) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DBUtils.getConnection();
			pst = (PreparedStatement) conn.prepareStatement(Constants.GET_STUDENT_ENTRY);
			pst.setString(1, student.getRegno());
			rs = pst.executeQuery();
			if(rs.next()) {
				logger.warning("The student entry is already present.");
				return false;
			}
			pst = (PreparedStatement) conn.prepareStatement(Constants.INSERT_STUDENTS);
			pst.setString(1, student.getFirstname());
			pst.setString(2, student.getLastname());
			pst.setString(3, student.getDeptname());
			pst.setInt(4, student.getYear());
			pst.setString(5, student.getRegno());
			pst.execute();
			return true;
		} catch (ClassNotFoundException ex) {
			logger.warning("Class not found.");
		} catch (SQLException e) {
			logger.warning("Error with SQL query.");
		} finally {
			DBUtils.closeConnection(conn, pst, rs);
		}
		return false;
	}

}
