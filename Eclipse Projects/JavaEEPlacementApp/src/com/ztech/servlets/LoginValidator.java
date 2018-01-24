package com.ztech.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ztech.delegates.*;

@WebServlet("/LoginValidator")
public class LoginValidator extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginValidator() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		String deptname = request.getParameter("deptname");
		String password = request.getParameter("password");
		CollegeDelegator cd = new CollegeDelegator();
		int choice = cd.validateCollegeLogin(deptname.toUpperCase(), password);
		HttpSession session = request.getSession();
		RequestDispatcher rd;
		switch (choice) {
		case 1:
			rd = request.getRequestDispatcher("./pages/admin.jsp");
			rd.forward(request, response);
			break;
		case 2:
			rd = request.getRequestDispatcher("./pages/dean.jsp");
			rd.forward(request, response);
			break;
		case 3:
			session.setAttribute("deptname", deptname.toUpperCase());
			rd = request.getRequestDispatcher("./pages/hod.jsp");
			rd.forward(request, response);
			break;
		case -1:
			request.setAttribute("errorMessage", "Enter all the fields");
			rd = request.getRequestDispatcher("./pages/college-login.jsp");
			rd.forward(request, response);
			break;
		default:
			request.setAttribute("errorMessage", "Enter the department and username properly");
			rd = request.getRequestDispatcher("./pages/college-login.jsp");
			rd.forward(request, response);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
