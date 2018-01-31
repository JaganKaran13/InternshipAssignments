<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Company Placement Portal</title>
<c:set var="req" value="<%=request.getContextPath() %>" />
<base href="${req}/">
<link rel="stylesheet" href="css/portal-header.css">
<link rel="stylesheet" href="css/company-portal.css">
</head>
<body>
	<fmt:bundle basename="com.ztech.bundles.config" >
		<fmt:message key="COMPANY_HEADER" var="companyHeader" />
		<fmt:message key="STUDENT_REGNO" var="studentRegno" />
		<fmt:message key="ENTER_STUDENT" var="enterStudent"/>
	</fmt:bundle>
	<header class="header"> 
	<div class="college-image-section">
		<img src="images/college-logo.jpg" class="college-logo">
	</div>
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
	<hr />
	<section>
	<h2>Welcome ${requestScope.companyName}</h2>
	<h2>List of Interested Students</h2>
	<form action="CompanyServlet" method="POST">
		<input type="hidden" name="companyid" value="${requestScope.companyid}">
		<table>
			<tr>
				<th></th>
				<th>Register No.</th>
				<th>Name</th>
				<th>Department</th>
				<th>CGPA</th>
				<th>Arrears</th>
			</tr>
			<c:forEach items="${requestScope.studentInterestedList }" var="interestedStudents">
				<tr>
					<td>
						<input type="checkbox" name="studentsPlaced" value="<c:out value="${interestedStudents.getRegno() }" />">
					</td>
					<td><c:out value="${interestedStudents.getRegno() }" /></td>
					<td><c:out value="${interestedStudents.getName() }" /></td>
					<td><c:out value="${interestedStudents.getDeptName() }" /></td>
					<td><c:out value="${interestedStudents.getCgpa() }" /></td>
					<td><c:out value="${interestedStudents.getArrears() }" /></td>
				</tr>
			</c:forEach>
		</table>
		<p class="response-message">${requestScope.responseMessage}</p>
		<input type="submit" value="Submit">
	</form>
	<br />
	<a href="index.jsp">Go to Home Page</a>
	</section>
</body>
</html>