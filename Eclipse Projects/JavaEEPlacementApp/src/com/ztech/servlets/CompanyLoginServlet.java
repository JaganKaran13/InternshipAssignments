package com.ztech.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ztech.delegates.CompanyDelegator;

@WebServlet("/CompanyLoginServlet")
public class CompanyLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CompanyLoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String companyid = request.getParameter("companyid");
		String password = request.getParameter("password");
		CompanyDelegator companyDelegator = new CompanyDelegator();
		int choice = companyDelegator.validateCompanyLogin(companyid, password);
		RequestDispatcher rd;
		switch (choice) {
		case -1:
			request.setAttribute("errorMessage", "Enter all the fields");
			rd = request.getRequestDispatcher("./pages/company-login.jsp");
			rd.forward(request, response);
			break;
		case 0:
			request.setAttribute("errorMessage", "Invalid company ID or password");
			rd = request.getRequestDispatcher("./pages/company-login.jsp");
			rd.forward(request, response);
			break;
		case 1:
			request.setAttribute("companyid", companyid);
			request.setAttribute("companyName", companyDelegator.getCompanyName(Integer.parseInt(companyid)));
			rd = request.getRequestDispatcher("./pages/company.jsp");
			rd.forward(request, response);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
