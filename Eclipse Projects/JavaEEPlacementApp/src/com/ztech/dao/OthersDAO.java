package com.ztech.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import com.ztech.beans.*;

public interface OthersDAO {
	public boolean enterStudentsPlaced(int companyid, int regno) throws SQLException;

	public String checkEligibilty(int regno, int companyid) throws SQLException;

	public int noOfStudentsPlaced(String deptName) throws SQLException;

	public int placementPercentage(String deptName) throws SQLException;
	
	public ArrayList<StudentDetails> displayDetailsDepartment(String deptName) throws SQLException;

	public String validateTeacher(String department, String password) throws SQLException;

	public ArrayList<String> getDepartmentList() throws SQLException;

	public ArrayList<Integer> getTotalStudentsList() throws SQLException;

	public String getCompanyName(int companyid) throws SQLException;
	
	public boolean insertStudentApplication(int regno, int companyid, String applyStatus) throws SQLException;
	
	public ArrayList<StudentDetails> getInterestedStudentsList(int companyid) throws SQLException;
	
	public ArrayList<FetchApplicationBean> getApplicationStatus(int regno);
	
	public ArrayList<YearDataBean> getByYearDetails();
}
