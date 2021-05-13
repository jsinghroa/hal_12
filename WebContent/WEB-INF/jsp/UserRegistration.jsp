<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<c:set var="path" value="${pageContext.request.contextPath}" />

<link href="${path}/resources/theme/css/jquery.dataTables.css"
	rel="stylesheet" type="text/css" />
<link href="${path}/resources/theme/css/home_new.css" rel="stylesheet"
	type="text/css" />
<link href="${path}/resources/theme/css/bootstrap.css" rel="stylesheet"
	type="text/css" />
<link href="${path}/resources/theme/css/theme.css" rel="stylesheet"
	type="text/css" />

<script type="text/javascript"
	src="${path}/resources/theme/js/jquery-1.9.1.js"></script>
<script type="text/javascript"
	src="${path}/resources/theme/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript"
	src="${path}/resources/theme/js/jquery.dataTables.js"></script>
</head>
<body>
	<form:form modelAttribute="userRegistrationForm" method="POST" action="${path}/userRegistration/onSubmit">
		<div class="pageheader">
			<spring:message code="label.UserRegistration.header" />
		</div>

		<div height="100%">
		<%-- <c:if test="${param.regStatus == 'success' }"> --%>
		<c:if test="${regStatus == 'success' }">
			<i style="margin-left:42%; color: blue;">Registration Completed Successfully!</i>
		</c:if>
		<c:if test="${regStatus == 'failure' }">
			<i style="margin-left:42%; color: red;">Registration Failed!</i>
		</c:if>
		<table align="center" width="30%" height="100%" style="margin-top:10px;overflow-x: auto;">
		<thead>
			 <form:errors path="*" cssClass="error" element="div" id="error"></form:errors>
				
			<tr align="center"><td width="200"><form:label path="label">
					<spring:message code="label.serviceNo" />
				</form:label></td>
				<td width="400"><form:input path="serviceNo" class="form-control" style="width: 200px;" required="required"/></td></tr>
				
			<tr align="center"><td width="200"><form:label path="label">
					<spring:message code="label.firstName" />
				</form:label></td>
				<td width="400"><form:input path="firstName" class="form-control" style="width: 200px;" required="required"/></td></tr>
				
			<tr align="center"><td width="200"><form:label path="label">
					<spring:message code="label.lastName" />
				</form:label></td>
				<td width="400"><form:input path="lastName" class="form-control" style="width: 200px;" required="required"/></td></tr>
				
			<tr align="center"><td width="200"><form:label path="label">
					<spring:message code="label.role" />
				</form:label></td>
				<td width="400" height="35px">
					<form:radiobutton class="form-check-input" path = "role" value = "ROLE_ADMIN" label = "Admin" />
                  	<form:radiobutton class="form-check-input" path = "role" value = "ROLE_EMPLOYEE" label = "Employee" /></td></tr>
				
			<tr align="center"><td width="200"><form:label path="label">
					<spring:message code="label.password" />
				</form:label></td>
				<td width="400"><form:input type="password" path="password" class="form-control" style="width: 200px;" required="required"/></td></tr>
			</thead>
		</table>
		<div>
		<table align="center" width="20%" style="overflow-x: hidden; margin-bottom: 10px; margin-top: 10px;">
			<thead>
				<tr align="center">

					<td align="right"><input type="submit" value="Submit" id="save" name="action" class="btn" style="width: 70px;" /></td>

					<td><input type="submit" name="action" id="validate" value="Reset" disabled="disabled" class="btn" style="width: 70px;"></td>

					<td align="left"><input type="submit" value="Cancel" disabled="disabled" name="action" class="btn" style="color: gray; width: 70px;" /></td>
				</tr>
			</thead>
		</table>
		</div>	
		</div>
		<!-- <input type="hidden" name="regStatus" value="success"/> -->
	</form:form>
</body>
</html>