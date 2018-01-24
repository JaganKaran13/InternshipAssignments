<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${req.contextPath}/" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert Student Details</title>
<link rel="stylesheet" type="text/css" href="css/admin-navbar.css">
<link rel="stylesheet" type="text/css" href="css/insert-details.css">
</head>
<body>
	<%
		String responseMessage;
		if(request.getAttribute("responseMessage") == null) {
			responseMessage = "";
		} else {
			responseMessage = (String) request.getAttribute("responseMessage");
		}
	%>
	<div class="navbar">
		<a href="pages/admin.jsp">HOME</a>
		<a href="pages/insert-student.jsp">Insert Student</a>
		<a href="pages/view-student.jsp">View Student</a>
		<a href="pages/insert-company.jsp">Insert Company</a>
		<a href="pages/view-company.jsp">View Company</a>
		<a href="index.jsp">Exit</a>
	</div>
	<form action="InsertStudentServlet" method="POST">
	<table>
		<tr>
			<td class="right-align"><label for="companyid">Enter the Register number : </label></td>
			<td><input type="text" name="regno" id="companyid" required></td>
		</tr>
		<tr>
			<td class="right-align"><label for="company-name">Enter the student name : </label></td>
			<td><input type="text" name="studentName" id="company-name" required></td>
		</tr>
		<tr>
			<td class="right-align"><label for="email">Enter the email : </label></td>
			<td><input type="email" name="email" id="email" required></td>
		</tr>
		<tr>
			<td class="right-align"><label for="deptName">Enter the department name : </label></td>
			<td><input type="text" name="deptName" id="deptName" required></td>
		</tr>
		<tr>
			<td class="right-align"><label for="arrear-criteria">Enter the number of arrears : </label></td>
			<td><input type="text" name="arrears" id="arrear-criteria" required></td>
		</tr>
		<tr>
			<td class="right-align"	><label for="cgpa-criteria">Enter the CGPA : </label></td>
			<td><input type="text" name="cgpa" id="cgpa-criteria" required></td>
		</tr>
	</table>
		<p class="error-message"><%=responseMessage %></p>
		<input type="submit" value="Insert">
	</form>
</body>
</html>