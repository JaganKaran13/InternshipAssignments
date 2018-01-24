package com.ztech.servlets;

import java.io.IOException;
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
import com.ztech.delegates.CompanyDelegator;

@WebServlet("/CompanyServlet")
public class CompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(CompanyServlet.class.getName());

    public CompanyServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String responseMessage = "";
		response.setContentType("text/html");
		RequestDispatcher rd;
		int companyid = 0;
		CompanyDelegator companyDelegator = new CompanyDelegator();
		try {
			companyid = Integer.parseInt(request.getParameter("companyid"));
			int regno = Integer.parseInt(request.getParameter("regno"));
			OthersDAO othersDAO = new OthersDAOImpl();
			responseMessage = othersDAO.enterStudentsPlaced(companyid, regno);
			request.setAttribute("companyid", request.getParameter("companyid"));
			request.setAttribute("responseMessage", responseMessage);
			request.setAttribute("companyName", companyDelegator.getCompanyName(companyid));
		} catch(NumberFormatException e) {
			request.setAttribute("companyid", request.getParameter("companyid"));
			request.setAttribute("responseMessage", "Enter the register number properly.");
			request.setAttribute("companyName", companyDelegator.getCompanyName(companyid));
			rd = request.getRequestDispatcher("./pages/company.jsp");
			rd.forward(request, response);
		} catch (SQLException e) {
			logger.warning("Error connecting it with MySQL.");
		}
		rd = request.getRequestDispatcher("./pages/company.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
