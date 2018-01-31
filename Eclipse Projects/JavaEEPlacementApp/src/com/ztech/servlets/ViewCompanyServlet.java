package com.ztech.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.ztech.beans.CompanyDetails;
import com.ztech.delegates.AdminDelegator;

@WebServlet("/ViewCompanyServlet")
public class ViewCompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(ViewCompanyServlet.class.getName());
       
    public ViewCompanyServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminDelegator adminDelegator = new AdminDelegator();
		ArrayList<CompanyDetails> companyArrayList = null;
		try {
			companyArrayList = adminDelegator.displayCompanyDetails();
		} catch (Exception e) {
			logger.log(Level.WARNING, "Error retrieving company details from MySQL.");
		}
		request.setAttribute("companyArrayList", companyArrayList);
		RequestDispatcher rd = request.getRequestDispatcher("./pages/view-company.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
