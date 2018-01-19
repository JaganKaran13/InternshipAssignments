package com.ztech.constants;

public class Constants {

	public static String CONNECTION_STRING = "jdbc:mysql://localhost:3306/servletexcercise";
	public static String USERNAME = "root";
	public static String PASSWORD = "zilkeradmin";
	public static String INSERT_STUDENTS = "insert into student values(?,?,?,?,?)";
	public static String GET_STUDENT_ENTRY = "select * from student where regno = ?";
	
}
