package com.ztech.dbutils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBUtils {

	private static final Logger logger = Logger.getLogger(DBUtils.class.getName());
	public static Connection conn;
	
	public static Connection getConnection() {
		try {
			Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/placement");
            conn = ds.getConnection();
		} catch (SQLException e) {
			logger.warning("Error connecting with SQL Driver");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void closeConnection(Connection conn, PreparedStatement pst, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pst != null) {
				pst.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			logger.warning("Error closing the connection variables");
		}
	}

}
