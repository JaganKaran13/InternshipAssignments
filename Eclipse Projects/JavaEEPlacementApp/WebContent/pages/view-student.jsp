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
</head>
<body>
<%@ page import="java.util.*, com.ztech.dao.*, com.ztech.beans.*, com.ztech.delegates.AdminDelegator" %>
	<%
		String orderBy = "";
		if(request.getAttribute("orderBy") == null) {
			orderBy = "regno";
		} else {
			orderBy = (String) request.getAttribute("orderBy");
		}
	%>
	<div class="navbar">
		<a href="pages/admin.jsp">Home</a>
		<a href="pages/insert-student.jsp">Insert Student</a>
		<a href="pages/view-student.jsp">View Student</a>
		<a href="pages/insert-company.jsp">Insert Company</a>
		<a href="pages/view-company.jsp">View Company</a>
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
				<th>Register No.</th>
				<th>Name</th>
				<th>Dept Name</th>
				<th>Arrears</th>
				<th>CGPA</th>
				<th>Placed Status</th>
			</tr>
		</thead>
		<tbody>
		<%
			AdminDelegator adminDelegator = new AdminDelegator();
			ArrayList<StudentDetails> studentArrayList = adminDelegator.displayStudentDetails(orderBy);
			for(int i = 0;i < studentArrayList.size(); i++) {
		%>
		<tr>
			<td><%=studentArrayList.get(i).getRegno() %></td>
			<td><%=studentArrayList.get(i).getName() %></td>
			<td><%=studentArrayList.get(i).getDeptName() %></td>
			<td><%=studentArrayList.get(i).getArrears() %></td>
			<td><%=studentArrayList.get(i).getCgpa() %></td>
			<td><%=studentArrayList.get(i).getPlacedStatus() %></td>
		</tr>	
		<%
			}
		%>
		</tbody>
	</table>
</body>
</html>