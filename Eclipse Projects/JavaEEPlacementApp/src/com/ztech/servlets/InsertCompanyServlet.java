package com.ztech.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ztech.delegates.AdminDelegator;

@WebServlet("/InsertCompanyServlet")
public class InsertCompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InsertCompanyServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd;
		AdminDelegator adminDelegator = new AdminDelegator();
		String responseMessage = adminDelegator.insertCompanyDetails(request, response);
		if (!responseMessage.equals("")) {
			request.setAttribute("responseMessage", "The company details are inserted.");
		} else {
			request.setAttribute("responseMessage", responseMessage);
		}
		rd = request.getRequestDispatcher("./pages/insert-company.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
