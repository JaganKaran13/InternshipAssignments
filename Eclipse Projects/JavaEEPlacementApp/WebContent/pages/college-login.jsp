<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="req" value="${pageContext.request}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${req.contextPath}/" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>College Login</title>
<link rel="stylesheet" type="text/css" href="css/login.css">
</head>
<body>
	<%
		String errorMessage;
		if (request.getAttribute("errorMessage") == null) {
			errorMessage = "";
		} else {
			errorMessage = (String) request.getAttribute("errorMessage");
		}
	%>
	<h1>Login Page</h1>
	<div class="login">
		<img class="logo-side-icon" src="images/login-logo.jpg">
		<form action="LoginValidator" method="POST">
			<label>Enter your department name : </label><br /> <br /> <input
				name="deptname" type="text"><br /> <br /> <label>Enter
				your password : </label><br /> <br /> <input name="password"
				type="password"><br /> <br /> <input class="login-button"
				type="submit" value="Sign In">
			<p class="error-message"><%=errorMessage%></p>
		</form>
	</div>
</body>
</html>