<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Company login</title>
<c:set var="req" value="<%=request.getContextPath() %>" />
<link rel="stylesheet" type="text/css" href="${req}/css/login.css">
</head>
<body>
	<fmt:bundle basename="com.ztech.bundles.config" >
		<fmt:message key="COMPANYLOGINHEADER" var="companyLoginHeader" />
		<fmt:message key="COMPANYID" var="companyID" />
		<fmt:message key="COMPANYPASSWORD" var="companyPassword"/>
		<fmt:message key="SIGNIN" var="signIn"/>
	</fmt:bundle>
	<%
		String errorMessage;
		if (request.getAttribute("errorMessage") == null) {
			errorMessage = "";
		} else {
			errorMessage = (String) request.getAttribute("errorMessage");
		}
	%>
	<h1>${companyLoginHeader}</h1>
	<div class="login">
		<img class="logo-side-icon" src="${req}/images/login-logo.jpg">
		<form action="${req}/CompanyLoginServlet" method="POST">
			<label>${companyID } : </label><br /> <br /> 
			<input name="companyid" type="text"><br /> <br /> 
			<label>${companyPassword } : </label><br /> <br />
			<input name="password" type="password"><br /> <br /> 
			<input class="login-button" type="submit" value="${signIn }">
			<p class="error-message"><%=errorMessage%></p>
		</form>
	</div>
</body>
</html>