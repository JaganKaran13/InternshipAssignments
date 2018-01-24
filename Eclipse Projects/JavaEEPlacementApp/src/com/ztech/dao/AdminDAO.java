package com.ztech.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ztech.beans.CompanyDetails;
import com.ztech.beans.StudentDetails;

public interface AdminDAO {
	public boolean insertStudentDetails(StudentDetails studentdet);

	public boolean insertCompanyDetails(CompanyDetails companydet);

	public boolean updateStudentDetails(StudentDetails studentDetails) throws SQLException;

	public boolean updateCompanyDetails(CompanyDetails companyDetails) throws SQLException;
	
	public ArrayList<CompanyDetails> displayCompanyDetails() throws SQLException;

	public ArrayList<StudentDetails> displayStudentDetails() throws SQLException;
	
	public boolean validateCompany(int companyid, String password) throws SQLException;
}
