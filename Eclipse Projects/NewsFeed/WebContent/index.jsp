<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page</title>
<link rel="stylesheet" href="./css/home.css">
</head>
<body>
	<div class="navbar">
		<a href="http://localhost:8080/NewsFeed/NewsServlet?newsCategory=WORLD">WORLD</a>
		<a href="http://localhost:8080/NewsFeed/NewsServlet?newsCategory=TECHNOLOGY">TECHNOLOGY</a>
		<a href="http://localhost:8080/NewsFeed/NewsServlet?newsCategory=SPORTS">SPORTS</a>
		<a href="http://localhost:8080/NewsFeed/NewsServlet?newsCategory=ENTERTAINMENT">ENTERTAINMENT</a>
		<a href="http://localhost:8080/NewsFeed/NewsServlet?newsCategory=HEALTH">HEALTH</a>  
	</div>
	<div class="image">
		<img src="./images/google-news.jpg" alt="Google News logo" align="middle">
	</div>
	<h1>Welcome...</h1>
	<h2>Fetch the contents of Google News from the navigation bar</h2>
</body>
</html>