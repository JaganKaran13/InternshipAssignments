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
<title>Student Portal</title>
<c:set var="req" value="<%=request.getContextPath() %>" />
<base href="${req}/" />
<link rel="stylesheet" href="css/student-portal.css">
<link rel="stylesheet" href="css/portal-header.css">
</head>
<body>
	<fmt:bundle basename="com.ztech.bundles.config" >
		<fmt:message key="STUDENT_HEADER" var="studentHeader" />
		<fmt:message key="HOME_PAGE" var="homePage"/>
		<fmt:message key="REGNO" var="registerNumber"/>
	</fmt:bundle>
	<header class="header">
		<div class="college-image-section"> 
			<img src="images/college-logo.jpg" class="college-logo">
		</div>
	<h1>${studentHeader}</h1>
	</header>
	<div class="icon-links">
		<a href="https://www.facebook.com/SSNInstitution" target="_blank">
			<img src="images/fb-icon.jpg">
		</a> 
		<a href="https://www.youtube.com/user/SSNinstitutions" target="_blank">
			<img src="images/youtube-icon.png">
		</a> 
		<a href="https://twitter.com/ssninstitutions" target="_blank">
			<img src="images/twitter-icon.jpg">
		</a> 
		<a href="https://www.linkedin.com/company/ssn-institutions-chennai-india?trk=top_nav_home" target="_blank">
			<img src="images/linkedin-icon.jpg">
		</a>
	</div>
	<hr />
	<section>
		<form action="StudentRegnoServlet" method="POST">
			<table>
				<tr>
					<td class="right-align"><label for="regno">${registerNumber} : </label></td>
					<td><input type="text" name="regno" id="regno"></td>
				</tr>
			</table>
			<input type="submit" name="applicationSubmit" value="Fetch Applications">
			<br /><br />
			<a href="index.jsp">${homePage }</a>
		</form>
	</section>
</body>
</html>