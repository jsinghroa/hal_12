<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<div class="nav">

<!-- <div class="navigation"></div>
	</div> -->
		<div class="info">
<div class="welocome">
<b>
Welcome:
</b>
<c:out value="nnn" /> <c:out value="nnn" /> <c:out value="nnn" />
<%-- <c:out value='<%= request.getHeader("title").replaceAll("%20"," ") %>' /> <c:out value='<%= request.getHeader("givenname").replaceAll("%20"," ") %>' /> <c:out value='<%= request.getHeader("sn").replaceAll("%20"," ") %>' /> --%>
</div>


<div class="log-search">
<div class="logout">
<%-- <a id="logoutlink" href="${path}/theme/jsp/HALLogout.jsp" title="Logout">Logout</a> --%>
<%-- <a id="logoutlink" href="${path}/HALLogout" title="Logout">Logout</a> --%>
<form:form action="${path}/logout" method="POST">
		<input type="submit" value="logout" />
	</form:form>
</div>
</div>
</div>
</body>
</html>