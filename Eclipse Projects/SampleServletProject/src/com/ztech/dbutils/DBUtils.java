package com.ztech.dbutils;

import java.util.logging.Logger;

import com.ztech.constants.Constants;

import java.sql.*;

public class DBUtils {

	Connection conn;
	PreparedStatement pst ;
	private static Logger logger =  Logger.getLogger(DBUtils.class.getName());
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(Constants.CONNECTION_STRING, Constants.USERNAME, Constants.PASSWORD);
	}
	
	public static void closeConnection(Connection conn, PreparedStatement pst, ResultSet rs)  {
		try {
			if(conn != null) {
				conn.close();
			}
			if(pst != null) {
				pst.close();
			}
			if(rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			logger.warning("Error closing the connection");
		}
	}
	
}
