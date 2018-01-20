<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%=request.getAttribute("newsCategory")%> News</title>
<link rel="stylesheet" href="./css/display-news.css">
</head>
<body>
	<%@ page
		import="org.json.*, org.jsoup.*, org.jsoup.nodes.*, org.jsoup.select.Elements, java.util.*, com.ztech.bean.NewsItemBean"%>
	<div class="navbar">
		<a href="index.jsp">HOME</a> <a
			href="http://localhost:8080/NewsFeed/NewsServlet?newsCategory=WORLD">WORLD</a>
		<a
			href="http://localhost:8080/NewsFeed/NewsServlet?newsCategory=TECHNOLOGY">TECHNOLOGY</a>
		<a
			href="http://localhost:8080/NewsFeed/NewsServlet?newsCategory=SPORTS">SPORTS</a>
		<a
			href="http://localhost:8080/NewsFeed/NewsServlet?newsCategory=ENTERTAINMENT">ENTERTAINMENT</a>
		<a
			href="http://localhost:8080/NewsFeed/NewsServlet?newsCategory=HEALTH">HEALTH</a>
	</div>
	<div class="container">
		<%
			ArrayList<NewsItemBean> newsArrayList = (ArrayList<NewsItemBean>) request.getAttribute("newsArrayList");
			for (int i = 0; i < newsArrayList.size(); i++) {
				NewsItemBean newsItemBean = (NewsItemBean) newsArrayList.get(i);
		%>
		<div class="newsItem col-sm-12">
			<table>
				<tr>
					<td><h2><%=newsItemBean.getTitle() %></h2></td>
				</tr>
				<tr>
					<td class="image"><%=newsItemBean.getImage() %></td>
				</tr>
				<tr>
					<td><a class="more-about"
						href="<%=newsItemBean.getLink() %> target="_blank"">More about this
							story</a></td>
				</tr>
			</table>
		</div>
		<%
			}
		%>
	</div>
</body>
</html>