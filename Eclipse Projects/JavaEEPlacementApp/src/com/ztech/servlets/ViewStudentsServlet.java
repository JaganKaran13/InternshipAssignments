package com.ztech.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ztech.delegates.*;
import com.ztech.beans.*;

@WebServlet("/ViewStudentsServlet")
public class ViewStudentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ViewStudentsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminDelegator adminDelegator = new AdminDelegator();
		String orderBy = "regno";
		if(request.getParameter("orderByCriteria") != null) {
			orderBy = request.getParameter("orderByCriteria");
		}
		request.setAttribute("orderBy", orderBy);
		ArrayList<StudentDetails> studentArrayList = null;
		try {
			studentArrayList = adminDelegator.displayStudentDetails(orderBy);
		} catch (SQLException e) {
			
		}
		request.setAttribute("studentArrayList", studentArrayList);
		RequestDispatcher rd = request.getRequestDispatcher("./pages/view-student.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
