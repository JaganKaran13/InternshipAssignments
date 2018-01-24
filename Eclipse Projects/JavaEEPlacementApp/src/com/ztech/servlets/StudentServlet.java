package com.ztech.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ztech.dao.OthersDAO;
import com.ztech.dao.OthersDAOImpl;

@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(StudentServlet.class.getName());

	public StudentServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		RequestDispatcher rd;
		PrintWriter out = response.getWriter();
		try {
			int companyid = Integer.parseInt(request.getParameter("companyid"));
			int regno = Integer.parseInt(request.getParameter("regno"));
			OthersDAO othersDAO = new OthersDAOImpl();
			int choice = othersDAO.checkEligibilty(regno, companyid);
			System.out.println("Choice : " + choice);
			switch (choice) {
			case 0:
				request.setAttribute("errorMessage", "You have entered the wrong register number");
				break;
			case 1:
				request.setAttribute("errorMessage", "You are not eligible to sit for this company.");
				break;
			case 2:
				out.println("<script type=\"text/javascript\">");
				out.println("alert('You are eligible to sit for this company.');");
				out.println("location='./pages/student.jsp';");
				out.println("</script>");
				return;
			}
			rd = request.getRequestDispatcher("./pages/student.jsp");
			rd.forward(request, response);
		} catch (NumberFormatException e) {
			request.setAttribute("errorMessage", "Enter the registration number properly");
			logger.warning("The given inputs are not in numbers.");
			rd = request.getRequestDispatcher("./pages/student.jsp");
			rd.forward(request, response);
		} catch (SQLException e) {
			logger.warning("Error retrieving values from MySQL.");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
