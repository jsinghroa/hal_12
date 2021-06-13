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
	
<link href="${path}/resources/theme/css/jquery.datetimepicker.min.css"
	rel="stylesheet" type="text/css" />
<link href="${path}/resources/theme/css/jquery.ui.all.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${path}/resources/theme/js/jquery.datetimepicker.full.js"></script>
<script type="text/javascript"
	src="${path}/resources/theme/js/jquery.datetimepicker.full.min.js"></script>
<script type="text/javascript"
	src="${path}/resources/theme/js/jquery.datetimepicker.min.js"></script>
	
<script type="text/javascript">
	$('document').ready(function() {

		$('#ListOfRecords').dataTable({
			"pagingType" : "full_numbers",
			"pageLength" : 20,
			"aLengthMenu" : [ 20, 30, 40, 50 ],
			"scrollY" : "200",
			"scrollX" : true,
			"order" : []
		});

	});

	$('document').ready(function() {

		var freezeFlag = document.getElementById("freezeFlag").value;
		console.log('freeze=' + freezeFlag);
		if (freezeFlag == "false") {
			console.log('freezetrue=' + freezeFlag);
			document.getElementById("validate").disabled = true;
			
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
		
	}
	function checkExtension() {

		var file = document.getElementById("meterExcelfile").value;
		var reg = /(.*?)\.(xls|xlsx)$/;
		var freezeFlag = document.getElementById("freezeFlag").value;
		if (!file.match(reg)) {
			document.getElementById("import").disabled = true;
			document.getElementById("import").setAttribute("style",
					"color: gray");
			alert("Please upload Excel File only");
		} else {
			
			
			if (freezeFlag == "false")
				{}else
			document.getElementById("import").disabled = false;

		}

	}
	
	
	function LinkSelected(linkSelected) {
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
	
	$(function()
			{
		var freezeFlag = document.getElementById("freezeFlag").value;
		
				$(".inductionDateTimePicker").datetimepicker({
					format: 'd-M-Y H:i:s',
					step: 1,
					maxDate:0
					
				});
				
				$(".signalOutDateTimePicker").datetimepicker({
					format: 'd-M-Y H:i:s',
					step: 1,
					maxDate:0
				});

			});
	
	function showDatePickerInductionDate(){
		var freezeFlag = document.getElementById("freezeFlag").value;
		if (freezeFlag == "true") 
			{
			$(function(){
				$('.inductionDateTimePicker').datetimepicker('show');
			});
			}
		
	}
	function showDatePickerSignalOutDate(){
	
		var freezeFlag = document.getElementById("freezeFlag").value;
		console.log('hello');
		if (freezeFlag == "true") 
			{
			$(function(){
				$('.signalOutDateTimePicker').datetimepicker('show');
			});
			}
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
	
	function updateTableLength(size) 
	{
	console.log('Size='+size);
	var table=$('#ListOfRecords').DataTable();
	
	table.page.len(size+1).draw();

	};

</script>
</head>

<body>

	<form:form method="POST" action="${path}/METER/saveMeter?${_csrf.parameterName }=${_csrf.token }"
		commandName="emmsDataForm"
		style="overflow-y: auto; overflow-x: hidden; height: 91%;"
		enctype="multipart/form-data">
		<div class="pageheader">
			<spring:message code="label.meter.header" />
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
			<table align="center" class="mdmtables" width="100%" height="100px"
				style="overflow-x: auto;">

				<tr>
					<td width="16%"><form:label path="recordId">
							<spring:message code="label.recordId" />
						</form:label><span style="color: red; font-weight: bold; margin-left: -46px;"></span></td>
					<td width="16%"><form:input path="recordId"
							class="form-control" title="${emmsDataForm.recordId}"
							readonly="true"
							style="background-color: rgb(216, 216, 216); width: 70%;" /></td>

					<td width="16%"><form:label path="model">
							<spring:message code="label.model" />
						</form:label><span style="color: red; font-weight: bold; margin-left: -46px;"></span></td>
					<td width="16%"><form:input path="model" class="form-control"
							title="${emmsDataForm.model}" readonly="true"
							style="background-color: rgb(216, 216, 216); width: 70%;" /></td>

					<td width="16%"><form:label path="recordStatus">
							<spring:message code="label.recordStatus" />
						</form:label><span style="color: red; font-weight: bold; margin-left: -46px;"></span></td>
					<td><form:input path="recordStatus" class="form-control"
							title="${emmsDataForm.recordStatus}" readonly="true"
							style="background-color: rgb(216, 216, 216); width: 80%; margin-left:-30%" /></td>

				</tr>

				<tr>
					<td><form:label path="location">
							<spring:message code="label.location" />
						</form:label><span style="color: red; font-weight: bold; margin-left: -46px;"></span></td>
					<td><form:input path="location" class="form-control"
							title="${emmsDataForm.location}" readonly="true"
							style="background-color: rgb(216, 216, 216); width: 70%;" /></td>

					<td><form:label path="mainAsset">
							<spring:message code="label.mainAsset" />
						</form:label><span style="color: red; font-weight: bold; margin-left: -46px;"></span></td>
					<td><form:input path="mainAsset" class="form-control"
							title="${emmsDataForm.mainAsset}" readonly="true"
							style="background-color: rgb(216, 216, 216); width: 70%;" /></td>

					<td><form:label path="assetMeterStatus">
							<spring:message code="label.AssetMeterStatus" />
						</form:label><span style="color: red; font-weight: bold; margin-left: -46px;"></span></td>
					<td><form:input path="assetMeterStatus" class="form-control"
							title="${emmsDataForm.assetMeterStatus}" readonly="true"
							style="background-color: rgb(216, 216, 216); width: 80%; margin-left:-30%" /></td>
					<td><form:input type="hidden" path="freeze" id="freezeFlag"
							class="form-control" value="${emmsDataForm.freeze}"
							readonly="true" /></td>
							<td><form:input type="hidden" path="bulkImportStatus"
							id="bulkImportStatus" class="form-control"
							value="${emmsDataForm.bulkImportStatus}" readonly="true" /></td>
							

				</tr>

				<tr>
					<td><form:label path="inductionDate">
							<spring:message code="label.inductionDate" />
						</form:label><span style="color: red; font-weight: bold; margin-left: -46px;"></span></td>
					<td class="form-group date"><div class="input-group date">
							<form:input 
									readonly="true"
									disabled="${!emmsDataForm.freeze}"
									type="text" path="inductionDate"
								class="form-control inductionDateTimePicker"
								value="${emmsDataForm.inductionDate}"
								style="background-color: #eeeeee; width: 180px;" />
							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-calendar"
								onclick="showDatePickerInductionDate()"></span>
							</span>
						</div></td>

					<td><form:label path="mainAssetPart">
							<spring:message code="label.mainAssetPart" />
						</form:label><span style="color: red; font-weight: bold; margin-left: -46px;"></span></td>
					<td><form:input path="mainAssetPart" class="form-control"
							title="${emmsDataForm.mainAssetPart}" readonly="true"
							style="background-color: rgb(216, 216, 216); width: 70%;" /></td>




				</tr>

				<tr>
					<td><form:label path="signalOutDate">
							<spring:message code="label.signalOutDate" />
						</form:label><span style="color: red; font-weight: bold; margin-left: -46px;"></span></td>
					<td class="form-group date"><div class="input-group date">
							<form:input path="signalOutDate"
							readonly="true"
							disabled="${!emmsDataForm.freeze}"
								value="${emmsDataForm.signalOutDate}"
								class="form-control signalOutDateTimePicker"
								style="background-color: #eeeeee; width: 180px;" />
							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-calendar"
								onclick="showDatePickerSignalOutDate()"></span>
							</span>
						</div></td>


					<td><form:label path="mainAssetSerial">
							<spring:message code="label.mainAssetSerial" />
						</form:label><span style="color: red; font-weight: bold; margin-left: -46px;"></span></td>
					<td><form:input path="mainAssetSerial" class="form-control"
							title="${emmsDataForm.mainAssetSerial}" readonly="true"
							style="background-color: rgb(216, 216, 216); width: 70%;" /></td>


				</tr>

			</table>
			<br /> <br />
		</div>


		<c:set var="size" value="0"></c:set>
		<table id="ListOfRecords" align="center" class="tabletopmargin10"
			width="100%" style="overflow-x: hidden;">

			<thead>
				<tr>
					<td class="tableheading"><spring:message
							code="label.InstalledPN" /></td>
					<td class="tableheading"><spring:message
							code="label.InstalledPartDescription" /></td>
					<td class="tableheading"><spring:message
							code="label.InstalledS/N" /></td>
					<td class="tableheading"><spring:message
							code="label.Metername" /></td>
					<td class="tableheading"><spring:message code="label.UOM" /></td>

					<td class="tableheading"><spring:message
							code="label.ExistingCount" /></td>
					
					<td class="tableheading"><spring:message
							code="label.CurrentCount" /></td>
					<td class="tableheading"><spring:message code="label.Error" /></td>
					<td class="tableheading"><spring:message
							code="label.ErrorDescription" /></td>
				</tr>
			</thead>
			<c:forEach var="meterForm" varStatus="status"
				items="${emmsDataForm.meterFormList}">
				<tr>
					<td><form:input
							path="meterFormList[${status.index}].installedPN"
							title="${meterForm.installedPN }" class="form-control"
							style="width:115px; background-color: rgb(216, 216, 216);"
							readonly="true" /></td>
					<td><form:input
							path="meterFormList[${status.index}].installedPartDescription"
							title="${meterForm.installedPartDescription}"
							class="form-control"
							style="width:200px; background-color: rgb(216, 216, 216);"
							readonly="true" /></td>
					<td><form:input
							path="meterFormList[${status.index}].installedSN"
							title="${meterForm.installedSN}" class="form-control"
							style="width:115px; background-color: rgb(216, 216, 216);"
							readonly="true" /></td>
					<td><form:input
							path="meterFormList[${status.index}].meterName"
							title="${meterForm.meterName}" class="form-control"
							style="width:390px; background-color: rgb(216, 216, 216);"
							readonly="true" /></td>
					<td><form:input path="meterFormList[${status.index}].uom"
							title="${meterForm.uom}" class="form-control"
							style="width:100px; background-color: rgb(216, 216, 216);"
							readonly="true" /></td>


					<td><form:input
							path="meterFormList[${status.index}].existingCountHms"
							readonly="${!emmsDataForm.freeze}"
							title="${meterForm.existingCount}" class="form-control"
							style="width:202px;" /></td>
					
					<td><form:input
							path="meterFormList[${status.index}].currentCountHms"
							readonly="${!emmsDataForm.freeze}"
							title="${meterForm.currentCount}" class="form-control"
							style="width:135px;" /></td>



					<c:set var="class1" value="defaultstatus"></c:set>
					<c:if test="${meterForm.error=='Validated'}">
						<c:set var="class1" value="validated"></c:set>
					</c:if>
					<c:if test="${meterForm.error=='Error'}">
						<c:set var="class1" value="notvalidated"></c:set>
					</c:if>
					<c:if test="${meterForm.error=='Warning'}">
						<c:set var="class1" value="Warning"></c:set>
					</c:if>
					<c:if test="${meterForm.error=='Validated With Warning'}">
						<c:set var="class1" value="Warning"></c:set>
					</c:if>


					<td><form:input path="meterFormList[${status.index}].error"
							title="${meterForm.error}" class="${class1} form-control"
							style="width:79px; background-color: rgb(216, 216, 216);"
							readonly="true" /></td>
					<td><form:input
							path="meterFormList[${status.index}].errorDescription"
							title="${meterForm.errorDescription}" class="form-control"
							style="width:120px; background-color: rgb(216, 216, 216);"
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


					<td><input type="submit" onclick="updateTableLength(${size})"
						name="action" id="validate" value="Validate" class="btn"
						style="width: 70px;"></td>
						
						<td><input type="submit" name="action" id="export"
					onclick="success(${size})"
						value="Export" class="btn" style="width: 70px;"></td>

					<td><input type="submit" name="action" id="import"
						disabled="disabled" value="Import" class="btn"
						style="width: 70px;" onclick="showProgress(${size}) "></td>

					<td><form:input type="file" path="meterExcelfile"
							id="meterExcelfile" onchange="checkExtension()" /></td>
						
						


				</tr>
			</thead>

			<input type="hidden" value="" id="linkSelected" name="linkSelected" />

			<input type="submit" style="visibility: hidden;" id="linkSelection"
				name="action" onclick="" />



		</table>


	</form:form>


</body>

</html>