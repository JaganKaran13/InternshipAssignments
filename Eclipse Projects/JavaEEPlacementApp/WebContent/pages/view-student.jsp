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
<title>View Student Details</title>
<link rel="stylesheet" href="css/admin-navbar.css">
<link rel="stylesheet" href="css/view-details.css">
</head>
<body>
<%@ page import="java.util.*, com.ztech.dao.*, com.ztech.beans.*" %>
	<div class="navbar">
		<a href="pages/admin.jsp">Home</a>
		<a href="pages/insert-student.jsp">Insert Student</a>
		<a href="pages/view-student.jsp">View Student</a>
		<a href="pages/insert-company.jsp">Insert Company</a>
		<a href="pages/view-company.jsp">View Company</a>
		<a href="index.jsp">Exit</a>
	</div>
	<table>
		<thead>
			<tr>
				<th>Register Number</th>
				<th>Name</th>
				<th>Arrears</th>
				<th>CGPA</th>
				<th>Placed Status</th>
			</tr>
		</thead>
		<tbody>
		<%
		ArrayList<StudentDetails> studentArrayList = new ArrayList<StudentDetails>();
		StudentDetails studentDetails;
		AdminDAO adminDAO = new AdminDAOImpl();
		studentArrayList = adminDAO.displayStudentDetails();
		for(int i = 0;i < studentArrayList.size(); i++) {
			studentDetails = studentArrayList.get(i);
		%>
		<tr>
			<td><%=studentDetails.getRegno() %></td>
			<td><%=studentDetails.getName() %></td>
			<td><%=studentDetails.getArrears() %></td>
			<td><%=studentDetails.getCgpa() %></td>
			<td><%=studentDetails.getPlacedStatus() %></td>
		</tr>	
		<%
		}
		%>
		</tbody>
	</table>
</body>
</html>