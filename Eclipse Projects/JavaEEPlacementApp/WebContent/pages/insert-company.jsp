<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert Company Details</title>
<c:set var="req" value="<%=request.getContextPath() %>" />
<base href="${req}/" />
<link rel="stylesheet" type="text/css" href="css/admin-navbar.css">
<link rel="stylesheet" type="text/css" href="css/insert-details.css">
</head>
<body>
	<fmt:bundle basename="com.ztech.bundles.config" >
		<fmt:message key="INSERT_COMPANYID" var="companyID" />
		<fmt:message key="INSERT_COMPANY_NAME" var="companyName" />
		<fmt:message key="INSERT_ARREAR_CRITERIA" var="arrearCriteria"/>
		<fmt:message key="INSERT_CGPA_CRITERIA" var="cgpaCriteria"/>
		<fmt:message key="INSERT_COMPANY_PASSWORD" var="companyPassword"/>
		<fmt:message key="INSERT_DETAILS_BUTTON" var="insertDetailsButton" />
	</fmt:bundle>
	<%
		String responseMessage;
		if (request.getAttribute("responseMessage") == null) {
			responseMessage = "";
		} else {
			responseMessage = (String) request.getAttribute("errorMessage");
		}
	%>
	<div class="navbar">
		<a href="pages/admin.jsp">HOME</a> <a href="pages/insert-student.jsp">Insert
			Student</a> <a href="pages/view-student.jsp">View Student</a> <a
			href="pages/insert-company.jsp">Insert Company</a> <a
			href="pages/view-company.jsp">View Company</a> <a href="index.jsp">Exit</a>
	</div>
	<form action="InsertCompanyServlet" method="POST">
		<table>
			<tr>
				<td class="right-align">
				<label for="companyid">${companyID} : </label></td>
				<td><input type="text" name="companyid" id="companyid"></td>
			</tr>
			<tr>
				<td class="right-align">
				<label for="company-name">${companyName} : </label></td>
				<td><input type="text" name="companyName" id="company-name"></td>
			</tr>
			<tr>
				<td class="right-align">
				<label for="arrear-criteria">${arrearCriteria} : </label></td>
				<td><input type="text" name="arrearCriteria"
					id="arrear-criteria"></td>
			</tr>
			<tr>
				<td class="right-align">
				<label for="cgpa-criteria">${cgpaCriteria} : </label></td>
				<td><input type="text" name="cgpaCriteria" id="cgpa-criteria"></td>
			</tr>
			<tr>
				<td class="right-align">
				<label for="company-password">${companyPassword} : </label></td>
				<td><input type="password" name="companyPassword"
					id="company-password"></td>
			</tr>
		</table>
		<p class="error-message"><%=responseMessage%></p>
		<input type="submit" value="${insertDetailsButton})">
	</form>
</body>
</html>