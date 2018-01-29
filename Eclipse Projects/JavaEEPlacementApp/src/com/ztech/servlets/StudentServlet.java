package com.ztech.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ztech.delegates.StudentDelegator;

@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public StudentServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StudentDelegator studentDelegator = new StudentDelegator();
		String submitButton = request.getParameter("applicationSubmit");
		if(submitButton.equals("Apply")) {
			request.setAttribute("responseMessage", studentDelegator.applyForCompany(request, response));
		} else if(submitButton.equals("Decline")) {
			request.setAttribute("responseMessage", studentDelegator.declineForCompany(request, response));
		}
		RequestDispatcher rd = request.getRequestDispatcher("./pages/student.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
