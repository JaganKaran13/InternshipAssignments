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
	<title>Dean Portal</title>
	<c:set var="req" value="<%=request.getContextPath() %>" />
	<base href="${req}/" />
	<script type="text/javascript" src="https://cdn.zingchart.com/zingchart.min.js"></script>
	<link rel="stylesheet" href="css/portal-header.css">
	<link rel="stylesheet" href="css/dean-portal.css">
</head>
<body>
	<%@ page
		import="java.util.*, com.ztech.beans.StudentDetails, com.ztech.dao.*"%>
	<%@ page import="com.ztech.delegates.*"%>
	<%
		if (session == null) {
			response.sendRedirect("pages/college-login.jsp");
		}
	%>
	<fmt:bundle basename="com.ztech.bundles.config" >
		<fmt:message key="DEANHEADER" var="deanHeader" />
		<fmt:message key="STUDENTSPLACEDCOUNT" var="studentsPlacedCount" />
		<fmt:message key="PLACEMENTPERCENTAGE" var="placementPercentage"/>
		<fmt:message key="SIGNIN" var="signin"/>
	</fmt:bundle>
	<header class="header">
	<div class="college-image-section"> 
		<img src="images/college-logo.jpg" class="college-logo">
	</div>
	<h1>${deanHeader}</h1>
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
	<hr />
	<section>
	<h2>${studentsPlacedCount} : ${requestScope.studentsPlaced }</h2>
	<h2>${placementPercentage} : ${requestScope.placementPercentage }%</h2>
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
			<c:forEach items="${requestScope.deanBeanList}" var="deanDisplayDetails">
				<tr>
					<td><c:out value="${deanDisplayDetails.getDeptName() }" /></td>
					<td><c:out value="${deanDisplayDetails.getStudentCount() }" /></td>
					<td><c:out value="${deanDisplayDetails.getStudentPlacedCount() }" /></td>
					<td><c:out value="${deanDisplayDetails.getPlacedPercentage() }" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="chart-section">
		<div id="placementChart"></div>
	</div>
	<div class="chart-section">
		<div id="compare-chart"></div>
	</div>
	</section>
	<script type="text/javascript" src="js/create-chart.js"></script>
	<script>
	<%
		DeanDelegator deanDelegator = new DeanDelegator();
		ArrayList<String> departmentList = deanDelegator.getDepartmentList();
		ArrayList<Integer> studentsPlacedCountList = deanDelegator.getStudentsPlacedCountList(departmentList);
		ArrayList<Integer> studentCountList = deanDelegator.getTotalStudentsList();
		ArrayList<Integer> byYearStudentCountList = deanDelegator.getStudentCountByYear();
		ArrayList<Integer> byYearStudentPlacedCountList = deanDelegator.getStudentPlacedCountByYear();
	%>
		var departmentData = [<%=deanDelegator.join(departmentList) %>];
		var studentsPlacedCountData = [<%=deanDelegator.join(studentsPlacedCountList) %>];
		var studentCountData = [<%=deanDelegator.join(studentCountList) %>];
		var callFunctionForStatistics = createChart("placementChart", departmentData, studentCountData, studentsPlacedCountData);
		var yearData = [2016, 2017, 2018];
		var studentCountByYearData = [<%=deanDelegator.join(byYearStudentCountList) %>];
		var studentPlacedCountByYearData = [<%=deanDelegator.join(byYearStudentPlacedCountList) %>];
		var callFunctionForComparison = createChart("compare-chart", yearData, studentCountByYearData, studentPlacedCountByYearData);
	</script>
</body>
</html>