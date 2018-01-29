package com.ztech.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ztech.beans.CompanyDetails;
import com.ztech.beans.StudentDetails;

public interface AdminDAO {
	public boolean insertStudentDetails(StudentDetails studentdet);

	public boolean insertCompanyDetails(CompanyDetails companydet);
	
	public ArrayList<CompanyDetails> displayCompanyDetails() throws SQLException;

	public ArrayList<StudentDetails> displayStudentDetails(String orderBy) throws SQLException;
	
	public boolean validateCompany(int companyid, String password) throws SQLException;
}
