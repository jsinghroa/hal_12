<%@ page language="java"%>
<%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${path}/resources/theme/css/home_new.css" rel="stylesheet" type="text/css">
<link href="${path}/resources/theme/css/basic.css" rel="stylesheet" type="text/css">

</head>
<body class="scroll">

<div class="wrapper">
<div class="overlay2" style="height: 818px; display: none;"></div>
<jsp:include page="/theme/jsp/basicHeader.jsp"></jsp:include>
<jsp:include page="/theme/jsp/userInfoHeader.jsp"></jsp:include>
<div class="content" role="main">
<div class="breadcurm">
<jsp:include page="/theme/jsp/sideNavigation.jsp"></jsp:include>
</div>
<div class="mainPageDiv">
<jsp:include page="${pageVar}"></jsp:include>
</div>
</div>
<jsp:include page="/theme/jsp/basicFooter.jsp"></jsp:include>

			</div>
			
			
</body>
</html>