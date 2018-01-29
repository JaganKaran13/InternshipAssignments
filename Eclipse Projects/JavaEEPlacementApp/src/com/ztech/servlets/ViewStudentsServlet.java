package com.ztech.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ViewStudentsServlet")
public class ViewStudentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ViewStudentsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderBy = request.getParameter("orderByCriteria");
		request.setAttribute("orderBy", orderBy);
		RequestDispatcher rd = request.getRequestDispatcher("./pages/view-student.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
