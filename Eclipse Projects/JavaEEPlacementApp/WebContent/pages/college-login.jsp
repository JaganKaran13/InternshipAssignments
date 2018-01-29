<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@page import="java.util.ResourceBundle" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>College Login</title>
<c:set var="req" value="<%=request.getContextPath() %>" />
<base href="${req}/" />
<c:set var="req" value="<%=request.getContextPath() %>" />
<link rel="stylesheet" type="text/css" href="${req}/css/login.css">
</head>
<body>
	<fmt:bundle basename="com.ztech.bundles.config" >
		<fmt:message key="DEPTNAME" var="deptName" />
		<fmt:message key="PASSWORD" var="password" />
		<fmt:message key="LOGIN" var="login"/>
		<fmt:message key="SIGNIN" var="signin"/>
	</fmt:bundle>
	<h1>${login}</h1>
	<div class="login">
		<div class="logo-side-icon">
			<img src="images/login-logo.jpg" alt="placement-icon">
		</div>
		<div class="form">
			<form action="LoginValidator" method="POST">
				<label>${deptName}</label><br /> <br /> 
				<input name="deptname" type="text"><br /> <br /> 
				<label>${password}</label><br /> <br /> 
				<input name="password" type="password"><br /> <br /> 
				<p class="error-message">${requestScope.errorMessage}</p>
				<div class="sign-in-button">
					<input class="login-button" type="submit" value="${signin}">
				</div>
			</form>
		</div>
	</div>
</body>
</html>