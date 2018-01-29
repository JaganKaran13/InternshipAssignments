<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Company login</title>
<c:set var="req" value="<%=request.getContextPath() %>" />
<base href="${req}/" />
<link rel="stylesheet" type="text/css" href="css/login.css">
</head>
<body>
	<fmt:bundle basename="com.ztech.bundles.config" >
		<fmt:message key="COMPANYLOGINHEADER" var="companyLoginHeader" />
		<fmt:message key="COMPANYID" var="companyID" />
		<fmt:message key="COMPANYPASSWORD" var="companyPassword"/>
		<fmt:message key="SIGNIN" var="signIn"/>
	</fmt:bundle>
	<h1>${companyLoginHeader}</h1>
	<div class="login">
		<div class="logo-side-icon">
			<img src="images/login-logo.jpg" alt="placement-icon">
		</div>
		<div class="form">
			<form action="CompanyLoginServlet" method="POST">
				<label>${companyID } : </label><br /> <br />
				<input name="companyid" type="text"><br /> <br />
				<label>${companyPassword } : </label><br /> <br />
				<input name="password" type="password"><br /> <br />
				<p class="error-message">${requestScope.errorMessage}</p>
				<div class="sign-in-button">
					<input class="login-button" type="submit" value="${signIn}">
				</div>
			</form>
		</div>
	</div>
</body>
</html>