<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="req" value="${pageContext.request}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${req.contextPath}/" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Portal</title>
<link rel="stylesheet" href="css/student-portal.css">
<link rel="stylesheet" href="css/portal-header.css">
</head>
<body>
	<fmt:bundle basename="com.ztech.bundles.config" >
		<fmt:message key="STUDENT_HEADER" var="studentHeader" />
		<fmt:message key="COMPANY_NAME" var="companyName" />
		<fmt:message key="REGNO" var="regno"/>
		<fmt:message key="HOME_PAGE" var="homePage"/>
		<fmt:message key="CHECK_ELIGIBILITY" var="checkEligibility"/>
	</fmt:bundle>
	<%
		String errorMessage;
		if(request.getAttribute("errorMessage") == null) {
			errorMessage = "";
		} else {
			errorMessage = (String) request.getAttribute("errorMessage");
		}
	%>
	<%@ page
		import="java.util.ArrayList, com.ztech.beans.CompanyDetails, com.ztech.dao.*"%>
	<header class="header"> <img src="images/college-logo.jpg"
		class="college-logo">
	<h1>${studentHeader}</h1>
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
	<form action="StudentServlet" method="GET">
		<table>
			<tr>
				<td class="right-align"><label>${companyName} : </label></td>
				<td><select name="companyid">
						<%
							OthersDAO othersDAO = new OthersDAOImpl();
							ArrayList<CompanyDetails> companyArrayList = othersDAO.getCompanyList();
							for (int i = 0; i < companyArrayList.size(); i++) {
								CompanyDetails companyDetails = companyArrayList.get(i);
						%>
						<option value="<%=companyDetails.getCompanyid()%>"><%=companyDetails.getName()%></option>
						<%
							}
						%>
				</select></td>
			</tr>
			<tr>
				<td class="right-align"><label for="regno">${regno} : </label></td>
				<td><input type="text" name="regno" id="regno" required></td>
			</tr>
		</table>
		<input type="submit" value="${checkEligibility}">
	</form>
	<br />
	<a href="index.jsp">${homePage}</a>
	<p class="errorMessage"><%=errorMessage %></p>
	</section>
</body>
</html>