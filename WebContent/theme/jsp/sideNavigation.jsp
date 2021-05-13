<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link href="${path}/resources/theme/css/home_new.css"
	rel="stylesheet" type="text/css" />
<link href="${path}/resources/theme/css/basic.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript">window.history.forward();</script>
</head>
<body>
	
	<div class="sideNav">
			<ul>
				<li class="first"><a href="${path}/userRegistration/onLoad">New User Registration</a>
				<li class="first"><a href="${path}/importXML/onLoad">Import e-MMS Data</a></li>
				<li class="first"><a href="${path}/ListView/onListView">Integrate e-MMS Data</a></li>
				<li class="first"><a href="${path}/exportXML/onLoad">Export HAL Data</a></li>
			</ul>
	</div>
		
	
</body>
</html>