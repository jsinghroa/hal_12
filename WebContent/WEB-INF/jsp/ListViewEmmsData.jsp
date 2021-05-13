<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script type="text/javascript" src="${path}/resources/theme/js/jquery-1.9.1.js"></script>
<script type="text/javascript"
	src="${path}/resources/theme/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript"
		src="${path}/resources/theme/js/jquery.dataTables.js"></script>
<script type="text/javascript"
		src="${path}/resources/theme/js/record_js.js"></script>
<link href="${path}/resources/theme/css/jquery.dataTables.css"
	rel="stylesheet" type="text/css" />
<link href="${path}/resources/theme/css/theme.css"
	rel="stylesheet" type="text/css" />
<link href="${path}/resources/theme/css/home_new.css"
	rel="stylesheet" type="text/css" />

<script type="text/javascript">
$('document').ready(function(){
	
	$('#ListOfRecords').dataTable({
		"pagingType" : "full_numbers",
		"pageLength" : 20,
		"aLengthMenu" : [20,30,40,50],
		"scrollY": "200",
		"scrollX":false, 
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
</script>
<style type="text/css">
table.mdmtables td input[type="text"] {
    width: 149px;
}
</style>
</head>
<body>

<form:form commandName="emmsDataForm" id="formid" method="post"
action="${path}/ListView/onListViewSubmit">
<div class="pageheader">
		<spring:message code="label.listPage.header" />
	</div>
	<div>
	<form:errors path="*" cssClass="error" element="div" id="error"></form:errors>
<table id="ListView" title="List of Records" align="center" class="tabletopmargin10" width="100%" >
			
				<thead>
				<tr>
					<td class="tableheading" width="16%" align="center" id="listView1">
						<a  id="listView" class="ViewLink" style="cursor: pointer;" onclick="LinkSelected('List View')">
						<spring:message code="label.ListView" /></a>
					</td>
					<td class="tableheading" width="16%" align="center">
						<a  id="assetConfig" class="ViewLink" style="cursor: pointer;" onclick="LinkSelected('Asset Config')">
						<spring:message code="label.AssetConfig" /></a>
					</td>
					<td class="tableheading" width="16%" align="center">
						<a  id="installableAsset" class="ViewLink" style="cursor: pointer;" onclick="LinkSelected('Installable Asset')">
						<spring:message code="label.InstallableAsset" /></a>
					</td>
					<td class="tableheading" width="16%" align="center">
						<a  id="Meter" class="ViewLink" style="cursor: pointer;" onclick="LinkSelected('Meter')">
						<spring:message code="label.Meter" /></a>
					</td>
					 <td class="tableheading" width="16%" align="center">
						<a  id="PM" class="ViewLink" style="cursor: pointer;" onclick="LinkSelected('PM')">
						<spring:message code="label.PM" /></a>
					</td>
					
					<td class="tableheading" width="16%" align="center">
						<a  id="FLB" class="ViewLink" style="cursor: pointer;" onclick="LinkSelected('FLB')">
						<spring:message code="label.FLB" /></a>
					</td>					 
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
                <div >Corresponding record details will be deleted. <br/>
                	Are you sure you want to proceed? <br/>
                	
                	<div>
		<div class="allBtn" align="center">
		<div class="inBtn">
		<table>
		<tr>
		<td>
		
			<input type="submit" value="Yes" style="width: 70px ; margin-left: 120px;" class="btn" name="action" id="yes">
			</td>
			<td> 
			<a class="btn" style="height: 23px;margin-left: 20px;" onclick="closeDeleteConfirm()">No</a>
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
	<table id="ListOfRecords" align="center" class="tabletopmargin10" width="100%" >
	<thead>
				<tr>
					<td class="tableheading" width="2%">Select</td>
					<td class="tableheading" width="10%"><spring:message code="label.recordId" /></td>
					<td class="tableheading" width="10%"><spring:message code="label.desc" /></td>
					<td class="tableheading" width="10%"><spring:message code="label.mainAsset" /></td>
					<td class="tableheading" width="10%"><spring:message code="label.mainAssetPart" /></td>
					<td class="tableheading" width="10%"><spring:message code="label.mainAssetSerial" /></td>
					<td class="tableheading" width="10%"><spring:message code="label.location" /></td>
					<td class="tableheading" width="10%"><spring:message code="label.signalOutDate" /></td>
					<td class="tableheading" width="10%"><spring:message code="label.recordStatus" /></td>
					<td class="tableheading" width="5%"><spring:message code="label.delete" /></td>
				</tr>
				</thead>
			<c:forEach var="emmsData" items="${emmsDataFormList}" varStatus="theCount" >
			<tr style ="width:100%;">
			<td>
				<form:radiobutton path="selectedRecordId" value="${emmsData.recordId}" onclick="$('#error').hide();" ></form:radiobutton>
			</td>
			<td>${emmsData.recordId}</td>
			<td>${emmsData.desc}</td>
			<td>${emmsData.mainAsset}</td>
			<td>${emmsData.mainAssetPart}</td>
			<td>${emmsData.mainAssetSerial}</td>
			<td>${emmsData.location}</td>
			<td>${emmsData.signalOutDate}</td>
			<td>${emmsData.recordStatus}</td>
			<td align="center">
					<a  title="Click to Delete Question" style="cursor: pointer;" class="deleteLink" name="${theCount.count}" onclick="showDeleteConfirm('${emmsData.recordId}')">
					 <img src="${path}/theme/images/delete.png" /></a>
				
					</td>
			</tr>
				
			</c:forEach>
				
			</table>	</div>

<input type="hidden" value="" id="linkSelected" name="linkSelected"/>
<input type="submit" style="visibility: hidden;" id="linkSelection" name="action"/>
<input type="hidden" id="deleterecordId" value="0" name="deleterecordId"/> 
</form:form>

</body>
</html>