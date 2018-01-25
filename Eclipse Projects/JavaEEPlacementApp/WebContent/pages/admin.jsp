<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<c:set var="req" value="${pageContext.request}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${req.contextPath}/" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administrator Portal</title>
<link rel="stylesheet" href="css/admin-navbar.css">
</head>
<body>
	<fmt:bundle basename="com.ztech.bundles.config" >
		<fmt:message key="HELLO_ADMIN" var="helloAdmin" />
	</fmt:bundle>
	<%
		if(session == null) {
			response.sendRedirect("pages/college-login.jsp");
		}
	%>
	<div class="navbar">
		<a href="pages/insert-student.jsp">Insert Student</a> <a
			href="pages/view-student.jsp">View Student</a> <a
			href="pages/insert-company.jsp">Insert Company</a> <a
			href="pages/view-company.jsp">View Company</a> 
		<a href="LogOutServlet" name="logout">Log Out</a>
	</div>
	<h1>${helloAdmin}</h1>
</body>
</html>