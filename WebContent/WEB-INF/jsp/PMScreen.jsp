<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

<c:set var="path" value="${pageContext.request.contextPath}" />
<link href="${path}/resources/theme/css/jquery.dataTables.css"
	rel="stylesheet" type="text/css" />
<link href="${path}/resources/theme/css/theme.css" rel="stylesheet"
	type="text/css" />
<link href="${path}/resources/theme/css/home_new.css" rel="stylesheet"
	type="text/css" />
<link href="${path}/resources/theme/css/bootstrap.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript"
	src="${path}/resources/theme/js/jquery-1.9.1.js"></script>
<script type="text/javascript"
	src="${path}/resources/theme/js/jquery-1.10.2.min.js"></script>

<script type="text/javascript"
	src="${path}/resources/theme/js/jquery.ui.datepicker.js"></script>

<script type="text/javascript"
	src="${path}/resources/theme/js/jquery.dataTables.js"></script>
<script type="text/javascript">


$('document').ready(function(){
	
	var table = $('#ListOfRecords').dataTable({
		"pagingType" : "full_numbers",
		"pageLength" : 20,
		"aLengthMenu" : [20,30,40,50],
		"scrollY": "200",
		"scrollX":true, 
		 "order": []
	});
});
	$('document').ready(function() {

		var freezeFlag = document.getElementById("freezeFlag").value;
		console.log('freeze=' + freezeFlag);
		if (freezeFlag == "false") {
			console.log('freezetrue=' + freezeFlag);
			document.getElementById("validate").disabled = true;
			document.getElementById("save").disabled = true;
			document.getElementById("export").disabled = true;
			document.getElementById("import").disabled = true;
			/* document.getElementById("datepicker1").disabled=true;   */
		}
		
		var bulkImportStatus = document
		.getElementById("bulkImportStatus").value;
console.log('inside import sattus');
console.log(bulkImportStatus);
if (bulkImportStatus == "Error") {
	alert('Error While Importing the Data');
}


	});




function success(size)
{
	$("#popo_olay1").css("display", "block");
	$('#popo_dolo1').css("display", "block");
	$('#spinner').show();
	var table=$('#ListOfRecords').DataTable();
	table.page.len(size+1).draw();
	alert('File saved on Desktop');
}
function checkExtension() {

	var file = document.getElementById("pmExcelfile").value;
	var reg = /(.*?)\.(xls|xlsx)$/;
	var freezeFlag = document.getElementById("freezeFlag").value;
	if (!file.match(reg)) {
		console.log('inside')
		document.getElementById("import").disabled = true;
		document.getElementById("import").setAttribute("style",
				"color: gray");
		alert("Please upload Excel File only");
	} else {
		console.log('123');
		
		if (freezeFlag == "false")
			{
			console.log('inside1')
			}else
		document.getElementById("import").disabled = false;

	}

}



function LinkSelected(linkSelected){
	$("#linkSelected").val(linkSelected);
	$("#linkSelection").click();
}


function showProgress(size) {
	$("#popo_olay1").css("display", "block");
	$('#popo_dolo1').css("display", "block");
	$('#spinner').show();

	var table=$('#ListOfRecords').DataTable();
	table.page.len(size+1).draw();

}

function showConfirm() {
	$("#popo_olay2").css("display", "block");
	$("#popo_dolo2").css("display", "block");
	console.log('inside showConfirm')

}

function closeConfirm() {
	$("#popo_olay2").css("display", "none");
	$("#popo_dolo2").css("display", "none");

}

$(function()
		{
	$("#datepicker1").datepicker(
			
	{
		maxDate:0,
		changeMonth:true,
		changeYear:true,
		dateFormat:'dd-M-yy',
		showOn:'button',
		buttonImageOnly : true,
		buttonImage:'/HAL_12/theme/images/datepicker.jpg'
	}
	);
		});


$(function()
		{
	$("#datepicker2").datepicker(
			{
				maxDate:0,
				changeMonth:true,
				changeYear:true,
				dateFormat:'dd-M-yy',
				showOn:'button',
				buttonImageOnly : true,
				buttonImage:'/HAL_12/theme/images/datepicker.jpg'
			});
		});
$(function()
		{
	$(".datepicker3").datepicker(
			{
				maxDate:0,
				changeMonth:true,
				changeYear:true,
				dateFormat:'dd-M-yy',
				showOn:'button',
				buttonImageOnly : true,
				buttonImage:'/HAL_12/theme/images/datepicker.jpg'
			});
		});
$(function()
		{
	$(".datepicker4").datepicker(
			{
				maxDate:0,
				changeMonth:true,
				changeYear:true,
				dateFormat:'dd-M-yy',
				showOn:'button',
				buttonImageOnly : true,
				buttonImage:'/HAL_12/theme/images/datepicker.jpg'
			});
		});
		
function updateTableLength(size) 
{
	console.log('Size='+size);
	var table=$('#ListOfRecords').DataTable();
	
	table.page.len(size+1).draw();

};

</script>
</head>

<body>

	<form:form id="ListViewForm" method="POST" 
	action="${path}/PM/savePm?${_csrf.parameterName }=${_csrf.token }"
		commandName="emmsDataForm"
		style="overflow-y: auto; overflow-x: hidden; height: 91%;"
		enctype="multipart/form-data">
		<div class="pageheader">
			<spring:message code="label.pm.header" />
		</div>
		<div>

			<table id="ListView" title="List of Records" align="center"
				class="tabletopmargin10" width="100%" style="overflow-x: auto;">

				<thead>
					<tr>
						<td class="tableheading" width="16%" align="center"><a
							id="listView" class="ViewLink" style="cursor: pointer;"
							onclick="LinkSelected('List View')"> <spring:message
									code="label.ListView" /></a></td>
						<td class="tableheading" width="16%" align="center"><a
							id="assetConfig" class="ViewLink" style="cursor: pointer;"
							onclick="LinkSelected('Asset Config')"> <spring:message
									code="label.AssetConfig" /></a></td>
						<td class="tableheading" width="16%" align="center"><a
							id="installableAsset" class="ViewLink" style="cursor: pointer;"
							onclick="LinkSelected('Installable Asset')"> <spring:message
									code="label.InstallableAsset" /></a></td>
						<td class="tableheading" width="16%" align="center"><a
							id="Meter" class="ViewLink" style="cursor: pointer;"
							onclick="LinkSelected('Meter')"> <spring:message
									code="label.Meter" /></a></td>
						<td class="tableheading" width="16%" align="center"><a
							id="PM" class="ViewLink" style="cursor: pointer;"
							onclick="LinkSelected('PM')"> <spring:message code="label.PM" /></a>
						</td>

						<td class="tableheading" width="16%" align="center"><a
							id="FLB" class="ViewLink" style="cursor: pointer;"
							onclick="LinkSelected('FLB')"> <spring:message
									code="label.FLB" /></a></td>
					</tr>
				</thead>
			</table>
			<br></br>
			<table id="commonTable" align="center" class="mdmtables" width="100%"
				height="100px" style="overflow-x: auto;">

				<tr>
					<td width="16%"><form:label path="recordId">
							<spring:message code="label.recordId" />
						</form:label><span style="color: red; font-weight: bold; margin-left: -46px;"></span></td>
					<td width="16%"><form:input path="recordId"
							value="${emmsDataForm.recordId}" class="form-control"
							readonly="true"
							style="background-color: rgb(216, 216, 216); width: 70%;" /></td>

					<td width="16%"><form:label path="model">
							<spring:message code="label.model" />
						</form:label><span style="color: red; font-weight: bold; margin-left: -46px;"></span></td>
					<td width="16%"><form:input path="model"
							value="${emmsDataForm.model}" class="form-control"
							readonly="true"
							style="background-color: rgb(216, 216, 216); width: 70%;" /></td>

					<td width="16%"><form:label path="recordStatus">
							<spring:message code="label.recordStatus" />
						</form:label><span style="color: red; font-weight: bold; margin-left: -46px;"></span></td>
					<td width="16%"><form:input path="recordStatus"
							value="${emmsDataForm.recordStatus}" class="form-control"
							readonly="true"
							style="background-color: rgb(216, 216, 216); width: 90%; margin-left:-30%" /></td>

				</tr>

				<tr>
					<td><form:label path="location">
							<spring:message code="label.location" />
						</form:label><span style="color: red; font-weight: bold; margin-left: -46px;"></span></td>
					<td><form:input path="location"
							value="${emmsDataForm.location}" class="form-control"
							readonly="true"
							style="background-color: rgb(216, 216, 216); width: 70%;" /></td>

					<td><form:label path="mainAsset">
							<spring:message code="label.mainAsset" />
						</form:label><span style="color: red; font-weight: bold; margin-left: -46px;"></span></td>
					<td><form:input path="mainAsset"
							value="${emmsDataForm.mainAsset}" class="form-control"
							readonly="true"
							style="background-color: rgb(216, 216, 216); width: 70%;" /></td>

					<td><form:label path="assetPmStatus">
							<spring:message code="label.assetPmStatus" />
						</form:label><span style="color: red; font-weight: bold; margin-left: -46px;"></span></td>
					<td><form:input path="assetPmStatus"
							value="${emmsDataForm.assetPmStatus}" class="form-control"
							readonly="true"
							style="background-color: rgb(216, 216, 216); width: 90%; margin-left:-30%" /></td>

					<td><form:input type="hidden" path="freeze" id="freezeFlag"
							class="form-control" value="${emmsDataForm.freeze}" /></td>

					<td><form:input type="hidden" path="bulkImportStatus"
							id="bulkImportStatus" class="form-control"
							value="${emmsDataForm.bulkImportStatus}" readonly="true" /></td>
							
				</tr>

				<tr>
					<td><form:label path="inductionDate">
							<spring:message code="label.inductionDate" />
						</form:label><span style="color: red; font-weight: bold; margin-left: -46px;"></span></td>
					<td class="datepicker2"><form:input type="text"
							id="datepicker1" path="inductionDate" class="form-control"
							value="${emmsDataForm.inductionDate}"
							style="background-color: rgb(216, 216, 216); width: 70%;" /></td>

					<td><form:label path="mainAssetPart">
							<spring:message code="label.mainAssetPart" />
						</form:label><span style="color: red; font-weight: bold; margin-left: -46px;"></span></td>
					<td><form:input path="mainAssetPart"
							value="${emmsDataForm.mainAssetPart}" class="form-control"
							readonly="true"
							style="background-color: rgb(216, 216, 216); width: 70%;" /></td>




				</tr>

				<tr>
					<td><form:label path="signalOutDate">
							<spring:message code="label.signalOutDate" />
						</form:label><span style="color: red; font-weight: bold; margin-left: -46px;"></span></td>
					<td class="datepicker3"><form:input path="signalOutDate"
							id="datepicker2" value="${emmsDataForm.signalOutDate}"
							class="form-control"
							style="background-color: rgb(216, 216, 216); width: 70%;" /></td>


					<td><form:label path="mainAssetSerial">
							<spring:message code="label.mainAssetSerial" />
						</form:label><span style="color: red; font-weight: bold; margin-left: -46px;"></span></td>
					<td><form:input path="mainAssetSerial"
							value="${emmsDataForm.mainAssetSerial}" readonly="true"
							class="form-control"
							style="background-color: rgb(216, 216, 216); width: 70%;" /></td>


				</tr>

			</table>
			<br />
			<br />
		</div>
		<c:set var="size" value="0"></c:set>
		<table id="ListOfRecords" align="center" class="tabletopmargin10"
			width="100%" style="overflow-x: hidden;">

			<thead>
				<tr>
					<td class="tableheading"><spring:message
							code="label.installedPN" /></td>
					<td class="tableheading"><spring:message
							code="label.installedPartDesc" /></td>
					<td class="tableheading"><spring:message
							code="label.installSerialNum" /></td>
					<td class="tableheading"><spring:message code="label.workType" /></td>
					<td class="tableheading"><spring:message code="label.mpmNum" /></td>
					<td class="tableheading"><spring:message
							code="label.mpmDescription" /></td>
					<td class="tableheading"><spring:message
							code="label.meterName" /></td>
					<td class="tableheading"><spring:message
							code="label.frequencyIteration" /></td>
					<td class="tableheading"><spring:message
							code="label.frequencyUnit" /></td>
					<td class="tableheading"><spring:message
							code="label.lastCompiledDate" /></td>
					<td class="tableheading"><spring:message
							code="label.nextDueDate" /></td>
					<td class="tableheading"><spring:message
							code="label.lastCompiledValue" /></td>
					<td class="tableheading"><spring:message
							code="label.nextDueValue" /></td>
					<td class="tableheading"><spring:message
							code="label.errorStatus" /></td>
					<td class="tableheading"><spring:message
							code="label.errorDesc" /></td>
				</tr>
			</thead>
			<c:forEach var="pmForm" varStatus="status"
				items="${emmsDataForm.pmDetailFormList}">
				<tr>
					<td><form:input
							path="pmDetailFormList[${status.index}].installedPN"
							class="form-control" value="${pmForm.installedPN }"
							style="width:77px; background-color: rgb(216, 216, 216);"
							readonly="true" /></td>
					<td><form:input
							path="pmDetailFormList[${status.index}].installedPartDesc"
							class="form-control" value="${pmForm.installedPartDesc}"
							style="width:149px; background-color: rgb(216, 216, 216);"
							readonly="true" /></td>
					<td><form:input
							path="pmDetailFormList[${status.index}].installSerialNum"
							class="form-control" value="${pmForm.installSerialNum}"
							style="width:79px; background-color: rgb(216, 216, 216);"
							readonly="true" /></td>
					<td><form:input
							path="pmDetailFormList[${status.index}].workType"
							class="form-control" value="${pmForm.workType}"
							style="width:70px; background-color: rgb(216, 216, 216);"
							readonly="true" /></td>
					<td><form:input
							path="pmDetailFormList[${status.index}].mpmNum"
							class="form-control" value="${pmForm.mpmNum}"
							style="width:70px; background-color: rgb(216, 216, 216);"
							readonly="true" /></td>
					<td><form:input
							path="pmDetailFormList[${status.index}].mpmDescription"
							class="form-control" value="${pmForm.mpmDescription}"
							style="width:71px; background-color: rgb(216, 216, 216);"
							readonly="true" /></td>
					<td><form:input
							path="pmDetailFormList[${status.index}].meterName"
							class="form-control" value="${pmForm.meterName}"
							style="width:130px; background-color: rgb(216, 216, 216);"
							readonly="true" /></td>
					<td><form:input
							path="pmDetailFormList[${status.index}].frequencyIteration"
							class="form-control" value="${pmForm.frequencyIteration}"
							style="width:83px; background-color: rgb(216, 216, 216);"
							readonly="true" /></td>
					<td><form:input
							path="pmDetailFormList[${status.index}].frequencyUnit"
							class="form-control" value="${pmForm.frequencyUnit}"
							style="width:90px; background-color: rgb(216, 216, 216);"
							readonly="true" /></td>
					<td class="datepicker4"><form:input readonly="true"
							path="pmDetailFormList[${status.index}].lastCompiledDate"
							class="form-control datepicker3"
							value="${pmForm.lastCompiledDate}" style="width:104px;"
							required="required" /></td>
					<td class="datepicker6"><form:input readonly="true"
							path="pmDetailFormList[${status.index}].nextDueDate"
							class="form-control datepicker4" value="${pmForm.nextDueDate}"
							style="width:101px;" required="required" /></td>
					<td><form:input
							path="pmDetailFormList[${status.index}].lastCompiledValue"
							readonly="${!emmsDataForm.freeze}" class="form-control"
							value="${pmForm.lastCompiledValue}" style="width:85px;" /></td>
					<td><form:input
							path="pmDetailFormList[${status.index}].nextDueValue"
							readonly="${!emmsDataForm.freeze}" class="form-control"
							value="${pmForm.nextDueValue}" style="width:77px;" /></td>

					<c:set var="class1" value="defaultstatus"></c:set>

					<c:if test="${pmForm.errorStatus=='Validated'}">
						<c:set var="class1" value="validated"></c:set>
					</c:if>
					<c:if test="${pmForm.errorStatus=='Not Validated'}">
						<c:set var="class1" value="notvalidated"></c:set>
					</c:if>
					<c:if test="${pmForm.errorStatus=='Warning'}">
						<c:set var="class1" value="Warning"></c:set>
					</c:if>
					<c:if test="${pmForm.errorStatus=='Validated With Warning'}">
						<c:set var="class1" value="Warning"></c:set>
					</c:if>

					<td><form:input
							path="pmDetailFormList[${status.index}].errorStatus"
							title="${pmForm.errorStatus }" class="${class1} form-control"
							value="${pmForm.errorStatus}"
							style="width:74px; background-color: rgb(216, 216, 216);"
							readonly="true" /></td>
					<td><form:input
							path="pmDetailFormList[${status.index}].errorDesc"
							title="${pmForm.errorDesc }" class="form-control"
							value="${pmForm.errorDesc}"
							style="width:90px; background-color: rgb(216, 216, 216);"
							readonly="true" /></td>
				</tr>
				<c:set var="size" value="${status.index}"></c:set>
			</c:forEach>

		</table>
		<br />
		<br />

		<table align="center" width="20%" style="overflow-x: hidden;">
			<thead>
				<tr align="center">

					<td align="right"><input type="submit"
						onclick="updateTableLength(${size})" value="Save" id="save"
						name="action" class="btn" style="width: 70px;" /></td>

					<td><input type="submit" onclick="updateTableLength(${size})"
						name="action" id="validate" value="Validate" class="btn"
						style="width: 70px;"></td>


					<td><input type="submit" name="action" id="export"
					onclick="success(${size})"
						value="Export" class="btn" style="width: 70px;"></td>

					<td><input type="submit" name="action" id="import"
						disabled="disabled" value="Import" class="btn"
						style="width: 70px;" onclick="showProgress(${size}) "></td>

					<td><form:input type="file" path="pmExcelfile"
							id="pmExcelfile" onchange="checkExtension()" /></td>
						
					<!-- <td align="left"><input type="submit" value="Submit"
						id="submits" disabled="disabled" name="action" class="btn"
						style="color: gray; width: 70px;" /></td> -->
				</tr>
			</thead>

			<input type="hidden" value="" id="linkSelected" name="linkSelected" />

			<input type="submit" style="visibility: hidden;" id="linkSelection"
				name="action" onclick="" />



		</table>
	</form:form>


</body>

</html>