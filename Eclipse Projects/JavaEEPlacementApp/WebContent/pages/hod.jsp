<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>HOD Portal</title>
<link rel="stylesheet" href="css/portal-header.css">
<link rel="stylesheet" href="css/hod-portal.css">
</head>
<body>
	<%@ page
		import="java.util.*, com.ztech.beans.StudentDetails, com.ztech.dao.*"%>
	<%
		if(session == null) {
			response.sendRedirect("pages/college-login.jsp");
		}
	%>
		<header class="header"> <img src="images/college-logo.jpg"
		class="college-logo">
	<h1>HOD Placement Portal</h1>
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
	<%
		String deptname = (String) session.getAttribute("deptname");
		OthersDAO othersDAO = new OthersDAOImpl();
		ArrayList<StudentDetails> studentArrayList = othersDAO.displayDetailsDepartment(deptname);
		StudentDetails studentDetails;
	%>
	<section>
	<h2><%=deptname %> Department Placement Statistics</h2>
	<h2>No. of students placed : <%=othersDAO.noOfStudentsPlaced(deptname) %></h2>
	<h2>Placement Percentage : <%=othersDAO.placementPercentage(deptname) %>%</h2>
	<table>
		<thead>
			<tr>
				<th>Reg. Number</th>
				<th>Name</th>
				<th>CGPA</th>
				<th>Arrears</th>
				<th>Placed Status</th>
			</tr>
		</thead>
		<%
		for (int i = 0; i < studentArrayList.size(); i++) {
			studentDetails = new StudentDetails();
			studentDetails = studentArrayList.get(i);
		%>
		<tr>
			<td><%=studentDetails.getRegno()%></td>
			<td><%=studentDetails.getName()%></td>
			<td><%=studentDetails.getCgpa()%></td>
			<td><%=studentDetails.getArrears()%></td>
			<td><%=studentDetails.getPlacedStatus()%></td>
		</tr>
	<%
		}
	%>
	</table>
	</section>
	<a href="LogOutServlet">Log Out</a>
</body>
</html>