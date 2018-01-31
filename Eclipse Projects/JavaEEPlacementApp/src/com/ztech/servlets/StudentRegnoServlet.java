package com.ztech.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ztech.delegates.StudentDelegator;

@WebServlet("/StudentRegnoServlet")
public class StudentRegnoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public StudentRegnoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudentDelegator studentDelegator = new StudentDelegator();
		request.setAttribute("regno", request.getParameter("regno"));
		request.setAttribute("applicationStatusList", studentDelegator.getApplicationStatus(request, response));
		RequestDispatcher rd = request.getRequestDispatcher("./pages/student.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
