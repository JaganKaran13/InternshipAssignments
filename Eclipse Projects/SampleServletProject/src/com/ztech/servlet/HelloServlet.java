package com.ztech.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ztech.bean.StudentBean;

@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static Logger logger =  Logger.getLogger(HelloServlet.class.getName());

	public HelloServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();
		StudentDAO sdao = new StudentDAOImpl();
		String regno = request.getParameter("regno").trim();
		String firstname = request.getParameter("firstname").trim();
		String lastname = request.getParameter("lastname").trim();
		String deptname = request.getParameter("deptname").trim();
		String year = request.getParameter("year").trim();
		Validator validator = new Validator();
		String errorString = validator.validateStudentDetails(firstname, lastname, deptname, regno, year);
		if(!errorString.equals("")) {
			out.append("<p style=\"color: red;\">" + errorString + "</p>");
			RequestDispatcher rd = request.getRequestDispatcher("/index.html");
			rd.include(request, response);
			return;
		}
		StudentBean student = new StudentBean();
		try {
			student.setFirstname(firstname);
			student.setLastname(lastname);
			student.setDeptname(deptname);
			student.setRegno(regno);
			student.setYear(Integer.parseInt(year));
		} catch(NumberFormatException e) {
			logger.warning("Enter number input for year.");
			out.append("<p style=\"color: red;\">Enter number input for year.</p>");
			RequestDispatcher rd = request.getRequestDispatcher("/index.html");
			rd.include(request, response);
			return;
		}
		if (sdao.insertStudentDetails(student)) {
			logger.info("The details are inserted in the database.");
			RequestDispatcher rd = request.getRequestDispatcher("index.html");
			rd.include(request, response);
		} else {
			out.append("<p style=\"color: red;\">The register number is already present.</p>");
			RequestDispatcher rd = request.getRequestDispatcher("index.html");
			rd.include(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
