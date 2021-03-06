<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
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
	<fmt:bundle basename="com.ztech.bundles.config" >
		<fmt:message key="HODHEADER" var="hodHeader" />
		<fmt:message key="HODSUBHEADER" var="hodSubHeader" />
		<fmt:message key="STUDENTSPLACEDCOUNT" var="studentsPlacedCount"/>
		<fmt:message key="PLACEMENTPERCENTAGE" var="placementPercentage"/>
	</fmt:bundle>
	<header class="header"> 
	<div class="college-image-section"> 
		<img src="images/college-logo.jpg" class="college-logo">
	</div>
	<h1>${hodHeader}</h1>
	</header>
	<div class="icon-links">
		<a href="https://www.facebook.com/SSNInstitution" target="_blank">
		<img src="images/fb-icon.jpg"></a> 
		<a href="https://www.youtube.com/user/SSNinstitutions" target="_blank">
		<img src="images/youtube-icon.png"></a> 
		<a href="https://twitter.com/ssninstitutions" target="_blank">
		<img src="images/twitter-icon.jpg"></a> 
		<a href="https://www.linkedin.com/company/ssn-institutions-chennai-india?trk=top_nav_home" target="_blank">
		<img src="images/linkedin-icon.jpg"></a>
	</div>
	<hr />
	<section>
	<h2>${sessionScope.deptName} ${hodSubHeader}</h2>
	<h2>${studentsPlacedCount} : ${requestScope.studentsPlacedCount}</h2>
	<h2>${placementPercentage} : ${requestScope.placementPercentage}%</h2>
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
		<c:forEach items="${requestScope.studentDepartmentList}" var="students" > 
			<tr>
				<td><c:out value="${students.getRegno() }" /></td>
				<td><c:out value="${students.getName() }" /></td>
				<td><c:out value="${students.getCgpa() }" /></td>
				<td><c:out value="${students.getArrears() }" /></td>
				<td><c:out value="${students.getPlacedStatus() }" /></td>
			</tr>
		</c:forEach>
	</table>
	<a href="LogOutServlet">Log Out</a>
	</section>
</body>
</html>