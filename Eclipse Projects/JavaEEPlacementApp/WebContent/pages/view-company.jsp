<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>View Company Details</title>
<c:set var="req" value="<%=request.getContextPath() %>" />
<base href="${req}/" />
<link rel="stylesheet" href="css/admin-navbar.css">
<link rel="stylesheet" href="css/view-details.css">
</head>
<body>
	<%@ page import="java.util.ArrayList, com.ztech.beans.CompanyDetails, com.ztech.dao.*" %>
	<div class="navbar">
		<a href="pages/admin.jsp">HOME</a> 
		<a href="pages/insert-student.jsp">Insert Student</a>
		<a href="ViewStudentsServlet">View Student</a>
		<a href="pages/insert-company.jsp">Insert Company</a>
		<a href="#">View Company</a>
		<a href="index.jsp">Exit</a>
	</div>
	<table>
		<thead>
			<tr>
				<th>Company ID</th>
				<th>Name</th>
				<th>Arrear Criteria</th>
				<th>CGPA Criteria</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${requestScope.companyArrayList}" var="companies" >
			<tr>
				<td><c:out value="${companies.getCompanyid() }" /></td>
				<td><c:out value="${companies.getName() }" /></td>
				<td><c:out value="${companies.getArrearCriteria() }" /></td>
				<td><c:out value="${companies.getCgpaCriteria() }" /></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>