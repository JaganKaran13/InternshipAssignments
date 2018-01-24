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
		try {
			int regno = Integer.parseInt(request.getParameter("regno"));
			String studentName = request.getParameter("studentName");
			String email = request.getParameter("email");
			int arrears = Integer.parseInt(request.getParameter("arrears"));
			float cgpa = Float.parseFloat(request.getParameter("cgpa"));
			String deptName = request.getParameter("deptName");
			AdminDelegator adminDelegator = new AdminDelegator();
			String responseMessage =adminDelegator.insertStudentDetails(regno, studentName, email, arrears,
					cgpa, deptName); 
			if (!responseMessage.equals("")) {
				request.setAttribute("responseMessage", responseMessage);
				rd = request.getRequestDispatcher("./pages/insert-student.jsp");
				rd.forward(request, response);
			} else {
				logger.info("Student details inserted for " + regno);
				request.setAttribute("responseMessage", "The student details are inserted.");
				rd = request.getRequestDispatcher("./pages/insert-student.jsp");
				rd.forward(request, response);
			}
		} catch (NumberFormatException e) {
			request.setAttribute("responseMessage", "Enter the details properly.");
			rd = request.getRequestDispatcher("./pages/insert-student.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
