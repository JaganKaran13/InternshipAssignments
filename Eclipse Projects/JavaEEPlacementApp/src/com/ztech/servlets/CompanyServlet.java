package com.ztech.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ztech.delegates.CompanyDelegator;

@WebServlet("/CompanyServlet")
public class CompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CompanyServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CompanyDelegator companyDelegator = new CompanyDelegator();
		int companyid = Integer.parseInt(request.getParameter("companyid"));
		request.setAttribute("responseMessage", companyDelegator.insertStudentsPlaced(request, response));
		request.setAttribute("companyid", request.getParameter("companyid"));
		request.setAttribute("companyName", companyDelegator.getCompanyName(companyid));
		RequestDispatcher rd = request.getRequestDispatcher("./pages/company.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
