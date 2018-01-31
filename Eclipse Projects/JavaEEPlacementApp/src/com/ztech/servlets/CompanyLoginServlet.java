package com.ztech.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ztech.beans.StudentDetails;
import com.ztech.delegates.CompanyDelegator;

@WebServlet("/CompanyLoginServlet")
public class CompanyLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CompanyLoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CompanyDelegator companyDelegator = new CompanyDelegator();
		String companyid = companyDelegator.getCompanyId(request, response);
		int choice = companyDelegator.validateCompanyLogin(request, response);
		RequestDispatcher rd;
		switch (choice) {
		case -1:
			request.setAttribute("errorMessage", "Enter all the fields");
			break;
		case 0:
			request.setAttribute("errorMessage", "Invalid company ID or password");
			break;
		case 1:
			request.setAttribute("companyid", companyid);
			request.setAttribute("companyName", companyDelegator.getCompanyName(Integer.parseInt(companyid)));
			ArrayList<StudentDetails> studentInterestedList = companyDelegator.getInterestedStudentsList(companyid);
			request.setAttribute("studentInterestedList", studentInterestedList);
			rd = request.getRequestDispatcher("./pages/company.jsp");
			rd.forward(request, response);
			return;
		}
		rd = request.getRequestDispatcher("./pages/company-login.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
