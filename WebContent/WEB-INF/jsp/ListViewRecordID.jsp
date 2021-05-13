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

<form:form commandName="tableStatusForm" id="formid" method="POST" action="${path}/importXML/onSubmit">
<div class="pageheader">
		<spring:message code="label.listViewImportPage.header" />
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
					<%-- <td class="tableheading" width="30%" align="center">
						<a  id="errorlist" class="ViewLink" style="cursor: pointer;" onclick="LinkSelected('Error List')">
						<spring:message code="label.listViewErrors" /></a>
					</td>	 --%>					
				</tr>
				</thead>
	</table>
	
	<div class="overlay" id="popo_olay2" style="display:none;" ></div>
    <div class="topover" id="popo_dolo2" style="display:none;">
        <div class="popubox hppopubox" style="margin:-149px auto !important;">    	
            <div class="popup">
                <div class="pageheader"
  					 style="width: 522px; margin-left: -10px; margin-top: -9px; height: 20px;">
					<spring:message code="label.confirmPOPUP.heading" />
				</div>
                <div>Record once deleted cannot be used in future. <br/>
                	Are you sure you want to delete this Record? <br/>
                	
                	<div>
		<div class="allBtn" align="center">
		<div class="inBtn">
		<table>
		<tr>
		<td>
		
			<input type="submit" value="Yes" style="width: 70px ; margin-left: 120px;" class="btn" name="action" id="yes">
			</td>
			<td> 
			<a class="btn" style="height: 23px; margin-left: 20px;" onclick="closeDeleteConfirm()">No</a>
			</td>
			</tr>
			</table>
			</div> </div>
		</div>
                	
                  </div>
		
            </div>
        </div>
    </div>
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
			<%-- <td>
				<form:radiobutton path="recordId" value="${emmsData.recordId}" onclick="radioButtonSelected('${emmsData.tableOrder}')" ></form:radiobutton>
			</td> --%>
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
								<%-- <div class="pageheader">
									<spring:message code="label.listOfErrors" />
								</div> --%>
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
</form:form>
</body>
</html>