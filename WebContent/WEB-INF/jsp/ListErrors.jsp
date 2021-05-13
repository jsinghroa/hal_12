<%-- <%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
});

function LinkSelected(linkSelected){
	$("#linkSelected").val(linkSelected);
	$("#linkSelection").click();
}

</script>
</head>

<body>

	<div class="pageheader">
	<spring:message code="label.listViewErrors.header" />
	</div>
	
	<div>
	<form:errors path="*" cssClass="error" element="div" id="error"></form:errors>
	
	<table id="ListView" title="List of Record IDs" align="center" class="tabletopmargin10" width="100%" style=" overflow-x: hidden;">
		
			<thead>
			<tr>
				<td class="tableheading" width="30%" align="center">
					<a  id="listView" class="ViewLink" style="cursor: pointer;" onclick="LinkSelected('List View Record')">
					<spring:message code="label.ListViewRecord" /></a>
				</td>
				<td class="tableheading" width="30%" align="center">
					<a  id="assetConfig" class="ViewLink" style="cursor: pointer;" onclick="LinkSelected('Import XML')">
					<spring:message code="label.ImportXML" /></a>
				</td>
				<td class="tableheading" width="30%" align="center">
					<a  id="errorlist" class="ViewLink" style="cursor: pointer;" onclick="LinkSelected('Error List')">
					<spring:message code="label.listViewErrors" /></a>
				</td>						
			</tr>
			</thead>
	</table>
	
	
	<br></br>
	<div align="center">
	<table id="ListOfRecords" align="center" class="tabletopmargin10" width="70%">
	<thead>
				<tr>
					<td class="tableheading" width="5%">Select</td>
					<td class="tableheading" width="5%"><spring:message code="label.recordId" /></td>
					<td class="tableheading" width="5%"><spring:message code="label.userId" /></td>
					<td class="tableheading" width="5%"><spring:message code="label.mainAsset" /></td>
					<td class="tableheading" width="5%"><spring:message code="label.location" /></td>
					<td class="tableheading" width="5%"><spring:message code="label.status" /></td>
					<td class="tableheading" width="5%"><spring:message code="label.error" /></td>
					<td class="tableheading" width="5%"><spring:message code="label.delete" /></td>
				</tr>
				</thead>
			<c:forEach var="emmsData" items="${recordIdList}" varStatus="theCount" >
			<tr>
			<td width="5%">
			<c:if test="${emmsData.tableOrder == '6'}">
			
				<form:radiobutton path="recordId" disabled="true" value="${emmsData.recordId}" onclick="radioButtonSelected('${emmsData.tableOrder}')" ></form:radiobutton>
			
			</c:if>
			<c:if test="${emmsData.tableOrder != '6'}">
				<form:radiobutton path="recordId" value="${emmsData.recordId}" onclick="radioButtonSelected('${emmsData.tableOrder}')" ></form:radiobutton>
			</c:if>
			</td>
			<td>
				<form:radiobutton path="recordId" value="${emmsData.recordId}" onclick="radioButtonSelected('${emmsData.tableOrder}')" ></form:radiobutton>
			</td>
			<td width="5%">${emmsData.recordId}</td>
			<td width="5%"><security:authentication property="principal.username" /></td>
			<td width="5%">${emmsData.mainAsset}</td>
			<td width="5%">${emmsData.location}</td>
			<td width="5%">${emmsData.recordIdStatus}</td>
			<td width="5%">
				<!-- Bootstrap enabling button goes here -->
				<!-- <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#${emmsData.recordId}">
					Click to view erors
				</button> -->
				<a data-toggle="modal" data-target="#${emmsData.recordId}" onclick="errorTableGenerate(${emmsData.recordId}, ${errorStatusList })" style="color:blue;cursor: pointer;">
					Click to view erors
				</a>
				
				<!-- Bootstrap Modal container starts here -->
				<div class="modal fade" id="${emmsData.recordId}"  role="dialog" aria-labelledby="exampleModalLabel">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLabel"><spring:message code="label.listOfErrors"></spring:message></h5>
								<div class="pageheader">
									<spring:message code="label.listOfErrors" />
								</div>
								<button type="button" class="close" data-dismiss="modal" aria-label="Close" style="margin-top:-18px;">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<!-- <p>${emmsData.recordId}</p> -->
								<table id="ListOfErrors" align="center"  width="100%">
									<thead>
										<tr>
											<td class="tableheading" width="31%"><spring:message code="label.zipName" /></td>
											<td class="tableheading" width="31%"><spring:message code="label.xmlName" /></td>
											<td class="tableheading" width="31%"><spring:message code="label.errors" /></td>
										</tr>
									</thead>
									
									
									<c:forEach var="error" items="${errorStatusList[theCount.index] }" varStatus="count">
										<tr>
											<td width="31%">${error.zipName}</td>
											<td width="31%">${error.xmlName}</td>
											<td width="31%">${error.errors}</td>
										</tr>
									</c:forEach>
								</table>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-primary" data-dismiss="modal" disabled="disable" style="display: inline-block;">Download</button>
								<button type="button" class="btn btn-secondary" data-dismiss="modal" style="display: inline-block;">Close</button>
							</div>
						</div>
					</div>
				</div>
			</td>
			<td align="center" width="5%">
					<a  title="Click to Delete Question" style="cursor: pointer;" class="deleteLink" name="${theCount.count}" onclick="showDeleteConfirm('${emmsData.recordId}')">
					 <img src="${path}/theme/images/delete.png" /></a>
					</td>
			</tr>
			</c:forEach>
			</table>
			</div>
	</div>
<input type="hidden" value="" id="linkSelected" name="linkSelected"/>
<input type="submit" style="visibility: hidden;" id="linkSelection" name="action"/>
<input type="hidden" id="deleterecordId" value="0" name="deleterecordId"/>
<input type="hidden" id="tableOrder" value="0" name="tableOrder"/>
</body>
</html> --%>