<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Dean Portal</title>
	<c:set var="req" value="<%=request.getContextPath() %>" />
	<script type="text/javascript" src="https://cdn.zingchart.com/zingchart.min.js"></script>
	<link rel="stylesheet" href="${req}/css/portal-header.css">
	<link rel="stylesheet" href="${req}/css/dean-portal.css">
</head>
<body>
	<%@ page
		import="java.util.*, com.ztech.beans.StudentDetails, com.ztech.dao.*"%>
	<%@ page import="com.ztech.delegates.*"%>
	<%
		if (session == null) {
			response.sendRedirect("pages/college-login.jsp");
		}
		OthersDAO othersDAO = new OthersDAOImpl();
	%>
	<fmt:bundle basename="com.ztech.bundles.config" >
		<fmt:message key="DEANHEADER" var="deanHeader" />
		<fmt:message key="STUDENTSPLACEDCOUNT" var="studentsPlacedCount" />
		<fmt:message key="PLACEMENTPERCENTAGE" var="placementPercentage"/>
		<fmt:message key="SIGNIN" var="signin"/>
	</fmt:bundle>
	<header class="header"> <img src="${req}/images/college-logo.jpg"
		class="college-logo">
	<h1>${deanHeader}</h1>
	</header>
	<div class="icon-links">
		<a href="https://www.facebook.com/SSNInstitution" target="_blank"><img
			src="${req}/images/fb-icon.jpg"></a> <a
			href="https://www.youtube.com/user/SSNinstitutions" target="_blank"><img
			src="${req}/images/youtube-icon.png"></a> <a
			href="https://twitter.com/ssninstitutions" target="_blank"><img
			src="${req}/images/twitter-icon.jpg"></a> <a
			href="https://www.linkedin.com/company/ssn-institutions-chennai-india?trk=top_nav_home"
			target="_blank"><img src="${req}/images/linkedin-icon.jpg"></a>
	</div>
	<br />
	<br />
	<hr />
	<section>
	<h2>${studentsPlacedCount} : <%=othersDAO.noOfStudentsPlaced("")%></h2>
	<h2>${placementPercentage} : <%=othersDAO.placementPercentage("")%>%
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
				DeanDelegator deanDelegator = new DeanDelegator();
				ArrayList<Integer> studentsPlacedCountList = deanDelegator.getStudentsPlacedCountList(departmentList);
				for (int i = 0; i < departmentList.size(); i++) {
					String deptName = departmentList.get(i);
					int totalStudentsPlaced = othersDAO.noOfStudentsPlaced(deptName);
					int placementPercentage = othersDAO.placementPercentage(deptName);
					int totalStudents = studentCountList.get(i);
			%>
			<tr>
				<td><%=deptName%></td>
				<td><%=totalStudents%></td>
				<td><%=totalStudentsPlaced%></td>
				<td><%=placementPercentage%></td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>
	<div id="myChart"></div>
	</section>
	<script>
		var departmentData = [<%=deanDelegator.join(departmentList) %>];
		var studentsPlacedCountData = [<%=deanDelegator.join(studentsPlacedCountList) %>];
		var studentCountData = [<%=deanDelegator.join(studentCountList) %>];
		var myChart = {
			  "type": "bar",
			  "title": {
			    "text": "College Placement Statistics!!"
			  },
			  "plot": {
			    "value-box": {
			      "text": "%v"
			    }
			  },
			  "legend": {
				"align": "center",
				"vertical-align": "bottom",
			    "toggle-action": "remove",
			    "draggable": true,
			    "drag-handler": "icon"
			  },
			  "scale-x": {
			    "values": departmentData
			  },
			  "series": [
			    {
			      "values": studentCountData,
			      "text": "Total Students",
			      "palette": 0
			    },
			    {
			      "values": studentsPlacedCountData,
			      "text": "Total Students Placed",
			      "palette": 1
			    }
			  ]
			};
			zingchart.render({
			  id: "myChart",
			  data: myChart,
			  height: "400",
			  width: "50%",
			});
	</script>
</body>
</html>