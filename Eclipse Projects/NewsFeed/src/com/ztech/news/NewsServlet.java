package com.ztech.news;

import java.io.*;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.ztech.bean.NewsItemBean;
import com.ztech.parser.RSStoJSON;

@WebServlet("/NewsServlet")
public class NewsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	RSStoJSON rsstoJSON;

	public NewsServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String newsCategory = request.getParameter("newsCategory");
		rsstoJSON = new RSStoJSON();
		ArrayList<NewsItemBean> newsArrayList = rsstoJSON.getNewsArrayList(newsCategory);
		request.setAttribute("newsCategory",
				newsCategory.substring(0, 1) + newsCategory.substring(1, newsCategory.length()).toLowerCase());
		request.setAttribute("newsArrayList", newsArrayList);
		RequestDispatcher rd = request.getRequestDispatcher("/news.jsp");
		rd.include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
