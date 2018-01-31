package com.ztech.servlets;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ztech.delegates.AdminDelegator;

@WebServlet("/InsertStudentServlet")
public class InsertStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(InsertStudentServlet.class.getName());

	public InsertStudentServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd;
		AdminDelegator adminDelegator = new AdminDelegator();
		String responseMessage = adminDelegator.insertStudentDetails(request, response); 
		if (!responseMessage.equals("")) {
			request.setAttribute("responseMessage", responseMessage);
			rd = request.getRequestDispatcher("./pages/insert-student.jsp");
			rd.forward(request, response);
		} else {
			logger.info("Student details inserted");
			request.setAttribute("responseMessage", "The student details are inserted.");
			rd = request.getRequestDispatcher("./pages/insert-student.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
