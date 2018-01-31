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
<title>View Student Details</title>
<c:set var="req" value="<%=request.getContextPath() %>" />
<base href="${req}/" />
<link rel="stylesheet" href="css/admin-navbar.css">
<link rel="stylesheet" href="css/view-details.css">
<link rel="stylesheet" href="css/view-student-input.css">
</head>
<body>
	<div class="navbar">
		<a href="pages/admin.jsp">Home</a>
		<a href="pages/insert-student.jsp">Insert Student</a>
		<a href="#">View Student</a>
		<a href="pages/insert-company.jsp">Insert Company</a>
		<a href="ViewCompanyServlet">View Company</a>
		<a href="index.jsp">Exit</a>
	</div>
	<div class="form">
		<form action="ViewStudentsServlet" method="POST">
			<select name="orderByCriteria">
				<option value="regno">Register No.</option>
				<option value="name">Name</option>
				<option value="deptname">Dept Name</option>
				<option value="arrears">Arrears</option>
				<option value="cgpa">CGPA</option>
			</select>
			<input type="submit" value="Fetch Details">
		</form>
	</div>
	<table>
		<thead>
			<tr>
				<th>Reg No.</th>
				<th>Name</th>
				<th>Dept Name</th>
				<th>Arrears</th>
				<th>CGPA</th>
				<th>Placed</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${requestScope.studentArrayList}" var="students">
		<tr>
			<td><c:out value="${students.getRegno()}" /></td>
			<td><c:out value="${students.getName()}" /></td>
			<td><c:out value="${students.getDeptName()}" /></td>
			<td><c:out value="${students.getArrears()}" /></td>
			<td><c:out value="${students.getCgpa()}" /></td>
			<td><c:out value="${students.getPlacedStatus()}" /></td>
		</tr>	
		</c:forEach>
		</tbody>
	</table>
</body>
</html>