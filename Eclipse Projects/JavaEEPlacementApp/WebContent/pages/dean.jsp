<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dean Portal</title>
<link rel="stylesheet" href="css/portal-header.css">
<link rel="stylesheet" href="css/dean-portal.css">
</head>
<body>
	<%@ page
		import="java.util.*, com.ztech.beans.StudentDetails, com.ztech.dao.*"%>
	<%
		if(session == null) {
			response.sendRedirect("pages/college-login.jsp");
		}
		OthersDAO othersDAO = new OthersDAOImpl();
	%>
	<header class="header"> <img src="images/college-logo.jpg"
		class="college-logo">
	<h1>College Placement Statistics</h1>
	</header>
	<div class="icon-links">
		<a href="https://www.facebook.com/SSNInstitution" target="_blank"><img
			src="images/fb-icon.jpg"></a> <a
			href="https://www.youtube.com/user/SSNinstitutions" target="_blank"><img
			src="images/youtube-icon.png"></a> <a
			href="https://twitter.com/ssninstitutions" target="_blank"><img
			src="images/twitter-icon.jpg"></a> <a
			href="https://www.linkedin.com/company/ssn-institutions-chennai-india?trk=top_nav_home"
			target="_blank"><img src="images/linkedin-icon.jpg"></a>
	</div>
	<br />
	<br />
	<hr />
	<section>
		<h2>
			No. of students placed :
			<%=othersDAO.noOfStudentsPlaced("")%></h2>
		<h2>
			Placement Percentage :
			<%=othersDAO.placementPercentage("")%>%
	</h2>
	<table>
		<thead>
			<tr>
				<th>Department</th>
				<th>Total Students</th>
				<th>Total Placed</th>
				<th>Placement %</th>
			</tr>
		</thead>
		<tbody>
			<%
				ArrayList<String> departmentList = othersDAO.getDepartmentList();
				ArrayList<Integer> studentCountList = othersDAO.getTotalStudentsList();
				for(int i = 0;i < departmentList.size(); i++) {
					String deptName = departmentList.get(i);
					int totalStudentsPlaced = othersDAO.noOfStudentsPlaced(deptName);
					int placementPercentage = othersDAO.placementPercentage(deptName);
					int totalStudents = studentCountList.get(i);
			%>
			<tr>
				<td><%=deptName %></td>
				<td><%=totalStudents %></td>
				<td><%=totalStudentsPlaced %></td>
				<td><%=placementPercentage %></td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>
	</section>
</body>
</html>