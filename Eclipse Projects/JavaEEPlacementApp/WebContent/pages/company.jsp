<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Company Placement Portal</title>
<c:set var="req" value="<%=request.getContextPath() %>" />
<base href="${req}/">
<link rel="stylesheet" href="css/portal-header.css">
<link rel="stylesheet" href="css/company-portal.css">
</head>
<body>
	<%@page import="com.ztech.delegates.*, com.ztech.beans.StudentDetails, java.util.*" %>
	<% 
		String companyName = (String) request.getAttribute("companyName");
		String companyid = (String) request.getAttribute("companyid");
	%>
	<fmt:bundle basename="com.ztech.bundles.config" >
		<fmt:message key="COMPANY_HEADER" var="companyHeader" />
		<fmt:message key="STUDENT_REGNO" var="studentRegno" />
		<fmt:message key="ENTER_STUDENT" var="enterStudent"/>
	</fmt:bundle>
	<header class="header"> <img src="images/college-logo.jpg"
		class="college-logo">
	<h1>${companyHeader}</h1>
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
	<br />
	<br />
	<hr />
	<section>
	<h2>Welcome <%=companyName%></h2>
	<h2>List of Interested Students</h2>
	<form action="CompanyServlet" method="POST">
		<input type="hidden" name="companyid" value="<%=companyid %>">
		<table>
			<tr>
				<th>Select</th>
				<th>Register No.</th>
				<th>Name</th>
				<th>Department</th>
				<th>CGPA</th>
				<th>Arrears</th>
			</tr>
			<%
				CompanyDelegator companyDelegator = new CompanyDelegator();
				ArrayList<StudentDetails> studentInterestedList = companyDelegator.getInterestedStudentsList(companyid);
				for(int i = 0;i < studentInterestedList.size(); i++) {
					StudentDetails studentDetails = studentInterestedList.get(i);
			%>
			<tr>
				<td><input type="checkbox" name="studentsPlaced" value="<%=studentDetails.getRegno() %>"></td>
				<td><%=studentDetails.getRegno() %></td>
				<td><%=studentDetails.getName() %></td>
				<td><%=studentDetails.getDeptName() %></td>
				<td><%=studentDetails.getCgpa() %></td>
				<td><%=studentDetails.getArrears() %></td>
			</tr>
			<%
				}
			%>
		</table>
		<input type="submit" value="Submit">
	</form>
	<br />
	<a href="index.jsp">Go to Home Page</a>
	</section>
</body>
</html>