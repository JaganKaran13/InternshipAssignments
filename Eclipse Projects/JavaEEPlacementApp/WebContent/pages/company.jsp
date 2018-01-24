<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="req" value="${pageContext.request}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${req.contextPath}/" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Company Placement Portal</title>
<link rel="stylesheet" href="css/portal-header.css">
<link rel="stylesheet" href="css/company-portal.css">
</head>
<body>
	<header class="header"> <img src="images/college-logo.jpg"
		class="college-logo">
	<h1>Company Placement Portal</h1>
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
	<% 
		String companyName = (String) request.getAttribute("companyName");
		String responseMessage = "";
		String companyid = (String) request.getAttribute("companyid");
		if(request.getAttribute("responseMessage") == null) {
			responseMessage = "";
		} else {
			responseMessage = (String) request.getAttribute("responseMessage");
		}
	%>
	<h2>Welcome <%=companyName%></h2>
	<form action="CompanyServlet" method="GET">
		<table>
			<tr>
				<td class="right-align"><label for="regno">Enter the
						student register number : </label></td>
				<input type="hidden" name="companyid" value="<%=companyid %>">
				<td><input name="regno" type="text" id="regno" required></td>
			</tr>
		</table>
		<p class="response-message"><%=responseMessage %></p>
		<input type="submit" value="Enter student">
	</form>
	<br />
	<a href="index.jsp">Go to Home Page</a> </section>
	</section>
</body>
</html>