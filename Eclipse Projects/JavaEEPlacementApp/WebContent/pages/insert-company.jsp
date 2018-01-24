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
<title>Insert Company Details</title>
<link rel="stylesheet" type="text/css" href="css/admin-navbar.css">
<link rel="stylesheet" type="text/css" href="css/insert-details.css">
</head>
<body>
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
				<td class="right-align"><label for="companyid">Enter
						the company ID : </label></td>
				<td><input type="text" name="companyid" id="companyid"></td>
			</tr>
			<tr>
				<td class="right-align"><label for="company-name">Enter
						the company name : </label></td>
				<td><input type="text" name="companyName" id="company-name"></td>
			</tr>
			<tr>
				<td class="right-align"><label for="arrear-criteria">Enter
						the arrear criteria : </label></td>
				<td><input type="text" name="arrearCriteria"
					id="arrear-criteria"></td>
			</tr>
			<tr>
				<td class="right-align"><label for="cgpa-criteria">Enter
						the CGPA criteria : </label></td>
				<td><input type="text" name="cgpaCriteria" id="cgpa-criteria"></td>
			</tr>
			<tr>
				<td class="right-align"><label for="company-password">Enter
						the Company Password : </label></td>
				<td><input type="password" name="companyPassword"
					id="company-password"></td>
			</tr>
		</table>
		<p class="error-message"><%=responseMessage%></p>
		<input type="submit" value="Insert Details">
	</form>
</body>
</html>