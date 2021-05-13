<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<link href="${path}/resources/theme/css/jquery.dataTables.css" rel="stylesheet" type="text/css" />
<link href="${path}/resources/theme/css/home_new.css" rel="stylesheet" type="text/css" />
<link href="${path}/resources/theme/css/bootstrap.css" rel="stylesheet"	type="text/css" />
<link href="${path}/resources/theme/css/theme.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${path}/resources/theme/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${path}/resources/theme/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="${path}/resources/theme/js/bootstrap.js"></script>
<script type="text/javascript" src="${path}/resources/theme/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${path}/resources/theme/js/jquery.dataTables.js"></script>
<script type="text/javascript">
$('document').ready(function(){
	
	$('#ListOfRecords').dataTable({
		"pagingType" : "full_numbers",
		"pageLength" : 20,
		"aLengthMenu" : [20,30,40,50],
		"scrollY": "200",
		"scrollX":true, 
		"order": []
	});
	$('#ListOfErrors').dataTable({
		"pagingType" : "full_numbers",
		"pageLength" : 5,
		"aLengthMenu" : [20,30,40,50],
		"order": []
	});
});

function LinkSelected(linkSelected){
	$("#linkSelected").val(linkSelected);
	$("#linkSelection").click();
}

function showDeleteConfirm(recordId)
{
	$("#popo_olay2").css("display","block");
	$("#popo_dolo2").css("display","block");
	$('#deleterecordId').val(recordId);
}

function closeDeleteConfirm()
{
	$("#popo_olay2").css("display", "none");
	$("#popo_dolo2").css("display", "none");
	
}
function radioButtonSelected(tableOrder)
{
	$('#tableOrder').val(tableOrder);
}

function errorTableGenerate(recordId, errorStatusList) {
	console.log(errorStatusList);
	console.log(recordId);
}
</script>
</head>
<body>

<form:form commandName="tableStatusForm" id="formid" method="POST" action="${path}/exportXML/export">
<div class="pageheader">
		<spring:message code="label.listViewImportPage.header" />
</div>

<div>
	<form:errors path="*" cssClass="error" element="div" id="error"></form:errors>
	
	
	<br></br>
	<div align="center">
	<table id="ListOfRecords" align="center" class="tabletopmargin10" width="70%">
	<thead>
				<tr>
					<td class="tableheading" width="5%">Select</td>
					<td class="tableheading" width="5%"><spring:message code="label.recordId" /></td>
					
					<td class="tableheading" width="5%"><spring:message code="label.mainAsset" /></td>
					<td class="tableheading" width="5%"><spring:message code="label.location" /></td>
					<td class="tableheading" width="5%"><spring:message code="label.status" /></td>
					<td class="tableheading" width="5%"><spring:message code="label.export" /></td>
					
				</tr>
				</thead>
			<c:forEach var="emmsData" items="${recordIdList}" varStatus="theCount" >
			<tr>
			<td width="5%">
			<form:radiobutton path="recordId" value="${emmsData.recordId}" onclick="radioButtonSelected('${emmsData.tableOrder}')" ></form:radiobutton>
			</td>
			<td width="5%">${emmsData.recordId}</td>
			<td width="5%">${emmsData.mainAsset}</td>
			<td width="5%">${emmsData.location}</td>
			<td width="5%">${emmsData.recordIdStatus}</td>
			<td width="5%">
			<input type="submit" name="action" id="exportData"
						value="Export" class="btn" style="width: 70px;">
			</td>
			
			
			</tr>
			</c:forEach>
			</table>
			</div>
			
	</div>
	
	
<input type="hidden" value="" id="linkSelected" name="linkSelected"/>
<input type="submit" style="visibility: hidden;" id="linkSelection" name="action"/>

</form:form>
</body>
</html>