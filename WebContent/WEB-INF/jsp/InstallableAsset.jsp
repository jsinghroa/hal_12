<%@page
	import="org.apache.taglibs.standard.lang.jstl.test.PageContextImpl"%>
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
<link href="${path}/resources/theme/css/bootstrap.css" rel="stylesheet"
	type="text/css" />
<link href="${path}/resources/theme/css/home_new.css" rel="stylesheet"
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

	$('document').ready(
			function() {
				
				var freezeFlag=document.getElementById("freezeFlag").value;
				console.log('freeze='+freezeFlag);
				if(freezeFlag=="true")
					{
					console.log('freezetrue='+freezeFlag);
				document.getElementById("validate").disabled=true;
				document.getElementById("save").disabled=true;
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

		var file = document.getElementById("installableExcelfile").value;
		var reg = /(.*?)\.(xls|xlsx)$/;
		var freezeFlag = document.getElementById("freezeFlag").value;
		if (!file.match(reg)) {
			document.getElementById("import").disabled = true;
			document.getElementById("import").setAttribute("style",
					"color: gray");
			alert("Please upload Excel File only");
		} else {
			
			
			if (freezeFlag == "true")
				{}else
			document.getElementById("import").disabled = false;

		}

	}
	
	
	
	function disableIndicatorRows(){
		document.getElementById('disableIndicator').setAttribute("value","1");
	}
		
	function updateTableLength(size) 
	{
	console.log('Size='+size);
	var table=$('#ListOfRecords').DataTable();
	
	table.page.len(size+1).draw();

	};
	$(function()
			{
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
	$(function(){
		var table = $('#ListOfRecords').dataTable();
		var tableApi = $('#ListOfRecords').DataTable();
		var defaultPageLength = tableApi.page.len();
		var count = table.fnGetData().length;
		tableApi.page.len(count).draw();
		console.log(count);
		var datetimepickerm;
		var datetimepickerr;
		for(i=0 ; i<count ; i++){
			datetimepickerm	= ".manufacturingDateTimePicker"+i;
			datetimepickerr = ".receiptDateTimePicker"+i;
			$(datetimepickerr).datetimepicker({
				format: 'd-M-Y H:i:s',
				step: 1,
				maxDate:0
			});
			
			$(datetimepickerm).datetimepicker({
				format: 'd-M-Y H:i:s',
				step: 1,
				maxDate:0
			});
		}
		tableApi.page.len(defaultPageLength).draw();
		console.log();
	});
	function showDatePickerInductionDate(){
		var freezeFlag = document.getElementById("freezeFlag").value;
		if (freezeFlag == "true") 
			{}
		else{
			$(function(){
			$('.inductionDateTimePicker').datetimepicker('show');
		});
		}
	}
	function showDatePickerSignalOutDate(){
	
		var freezeFlag = document.getElementById("freezeFlag").value;
		
		if (freezeFlag == "true") 
			{}else
				{
				$(function(){
					$('.signalOutDateTimePicker').datetimepicker('show');
				});
				}
	}
	function showDatePickerManufactureDate(index){
		var freezeFlag = document.getElementById("freezeFlag").value;
		console.log("DateTimePicker selected: "+index);
		if (freezeFlag == "true") 
			{}
			else
			{
				$(function(){
			var element = ".manufacturingDateTimePicker"+index;
			$(element).datetimepicker('show');
		});}
	}
	function showDatePickerReceiptDate(index){
		var freezeFlag = document.getElementById("freezeFlag").value;
		console.log("DateTimePicker selected: "+index);
		if (freezeFlag == "true") 
			{}else{
		$(function(){
			var element = ".receiptDateTimePicker"+index;
			$(element).datetimepicker('show');
		});}
	}



	
	
	
</script>
</head>

<body>

	<form:form method="POST"
		action="${path}/Installable/saveInstall?${_csrf.parameterName }=${_csrf.token }"
		commandName="emmsDataForm"
		style="overflow-y: auto; overflow-x: hidden; height: 91%;"
		enctype="multipart/form-data">
		<div class="pageheader">
			<spring:message code="label.installableAsset.header" />
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
					<td><form:input path="recordStatus"
							value="${emmsDataForm.recordStatus}" class="form-control"
							readonly="true"
							style="background-color: rgb(216, 216, 216); width: 80%; margin-left:-30%" /></td>

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
							<spring:message code="label.InsAssetstatus" />
						</form:label><span style="color: red; font-weight: bold; margin-left: -46px;"></span></td>
					<td><form:input path="assetPmStatus"
							value="${emmsDataForm.installableStatus}" class="form-control"
							readonly="true"
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
							<form:input type="text" readonly="true"
							disabled="${emmsDataForm.freeze}"
							 path="inductionDate"
								class="form-control inductionDateTimePicker"
								value="${emmsDataForm.inductionDate}"
								style="background-color: #eeeeee; width: 180px;" />
							<span class="input-group-addon" style="width: 0%;"> <span
								class="glyphicon glyphicon-calendar"
								onclick="showDatePickerInductionDate()"></span>
							</span>
						</div></td>
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
					<td class="form-group date"><div class="input-group date">
							<form:input readonly="true" 
							disabled="${emmsDataForm.freeze}"
							path="signalOutDate"
								value="${emmsDataForm.signalOutDate}"
								class="form-control signalOutDateTimePicker"
								style="background-color: #eeeeee; width: 180px;" />
							<span class="input-group-addon" style="width: 0%;"> <span
								class="glyphicon glyphicon-calendar"
								onclick="showDatePickerSignalOutDate()"></span>
							</span>
						</div></td>

					<td><form:label path="mainAssetSerial">
							<spring:message code="label.mainAssetSerial" />
						</form:label><span style="color: red; font-weight: bold; margin-left: -46px;"></span></td>
					<td><form:input path="mainAssetSerial" class="form-control"
							value="${emmsDataForm.mainAssetSerial}" readonly="true"
							style="background-color: rgb(216, 216, 216); width: 70%;" /></td>


				</tr>

			</table>
			<br /> <br />
		</div>
		<c:set var="size" value="0"></c:set>
		<table id="ListOfRecords" align="center" class="tabletopmargin10"
			style="width: 802px; overflow-x: hidden;">

			<thead>
				<tr>
					<td class="tableheading"><spring:message
							code="label.EnditemPN" /></td>
					<td class="tableheading"><spring:message
							code="label.EnditemSN" /></td>
					<td class="tableheading"><spring:message
							code="label.InstallableModel" /></td>
					<td class="tableheading"><spring:message code="label.LCN" /></td>
					<td class="tableheading"><spring:message code="label.Position" /></td>
					<td class="tableheading"><spring:message
							code="label.Builditem" /></td>
					<td class="tableheading"><spring:message
							code="label.Assertnum" /></td>
					<td class="tableheading"><spring:message code="label.Partnum" /></td>
					<td class="tableheading"><spring:message code="label.PartDesc" /></td>
					<td class="tableheading"><spring:message
							code="label.Serialnum" /></td>
					<td class="tableheading"><spring:message
							code="label.InstallablePN" /></td>
					<td class="tableheading"><spring:message code="label.INlieuPN" /></td>
					<td class="tableheading"><spring:message
							code="label.InstallableSN" /></td>
					<td class="tableheading"><spring:message
							code="label.ConditionCode" /></td>
					<td class="tableheading"><spring:message
							code="label.DateofManufacturing" /></td>
					<td class="tableheading"><spring:message
							code="label.DateofReceipt" /></td>
					<td class="tableheading"><spring:message
							code="label.ErrorStatus" /></td>
					<td class="tableheading"><spring:message
							code="label.ErrorDescription" /></td>
				</tr>
			</thead>
			<c:forEach var="installableForm" varStatus="status"
				items="${emmsDataForm.installableFormList}">
				<tr>
					<td><form:input
							path="installableFormList[${status.index}].enditemPN"
							class="form-control" title="${installableForm.enditemPN }"
							style="width:70px; background-color: rgb(216, 216, 216);"
							readonly="true" /></td>
					<td><form:input
							path="installableFormList[${status.index}].enditemsn"
							class="form-control" title="${installableForm.enditemsn}"
							style="width:140px; background-color: rgb(216, 216, 216);"
							readonly="true" /></td>
					<td><form:input
							path="installableFormList[${status.index}].installableModel"
							class="form-control" title="${installableForm.installableModel}"
							style="width:70px; background-color: rgb(216, 216, 216);"
							readonly="true" /></td>
					<td><form:input
							path="installableFormList[${status.index}].lcn"
							class="form-control" title="${installableForm.lcn}"
							style="width:70px; background-color: rgb(216, 216, 216);"
							readonly="true" /></td>
					<td><form:input
							path="installableFormList[${status.index}].position"
							class="form-control" title="${installableForm.position}"
							style="width:70px; background-color: rgb(216, 216, 216);"
							readonly="true" /></td>
					<td><form:input
							path="installableFormList[${status.index}].builditem"
							class="form-control" title="${installableForm.builditem}"
							style="width:410px; background-color: rgb(216, 216, 216);"
							readonly="true" /></td>
					<td><form:input
							path="installableFormList[${status.index}].assertnum"
							class="form-control" title="${installableForm.assertnum}"
							style="width:80px; background-color: rgb(216, 216, 216);"
							readonly="true" /></td>
					<td><form:input
							path="installableFormList[${status.index}].partnum"
							class="form-control" title="${installableForm.partnum}"
							style="width:71px; background-color: rgb(216, 216, 216);"
							readonly="true" /></td>
					<td><form:input
							path="installableFormList[${status.index}].partDesc"
							class="form-control" title="${installableForm.partDesc}"
							style="width:90px; background-color: rgb(216, 216, 216);"
							readonly="true" /></td>
					<%--  <td><form:input class="datepicker3" path="installableFormList[${status.index}].lastCompiledDate"  value="${installableForm.lastCompiledDate}" style="width:100px;" />
        </td>
        <td><form:input class="datepicker4" path="installableFormList[${status.index}].nextDueDate" value="${installableForm.nextDueDate}" style="width:100px;" />
        </td> --%>
					<td><form:input
							path="installableFormList[${status.index}].serialnum"
							class="form-control" title="${installableForm.serialnum}"
							style="width:70px; background-color: rgb(216, 216, 216);"
							readonly="true" /></td>
					<td><form:select
							path="installableFormList[${status.index}].installablePN"
							onchange="disableIndicatorRows()"
							disabled="${emmsDataForm.freeze}"
							 class="form-control"
							style="width:130px;" id="installledPN">
						
							<form:option value="" label="- - -Select- - -"></form:option>
							<form:options items="${installableForm.installedPNList}" />

						</form:select></td>
					<td><form:input
							path="installableFormList[${status.index}].iNlieuPN"
							readonly="${emmsDataForm.freeze}" class="form-control"
							title="${installableForm.iNlieuPN}" style="width:70px;"
							onchange="disableIndicatorRows()" /></td>

					<td><form:input
							path="installableFormList[${status.index}].installableSN"
							readonly="${emmsDataForm.freeze}" class="form-control"
							title="${installableForm.installableSN}" style="width:70px;"
							onchange="disableIndicatorRows()" /></td>
					<td><form:select
							path="installableFormList[${status.index}].conditionCode"
							disabled="${emmsDataForm.freeze}" class="form-control"
							
							style="width:130px;" id="conditionCode"
							title="${installableForm.conditionCode}">


							<form:option value="" label="- - -Select- - -"></form:option>
							<form:options items="${installableForm.conditionCodes}" />

						</form:select></td>
					<td class="form-group date">
						<div class="input-group date"><form:input
							path="installableFormList[${status.index}].dateofManufacturing"
							readonly="true"
							disabled="${emmsDataForm.freeze}"
							class="form-control manufacturingDateTimePicker${status.index}"
							title="${installableForm.dateofManufacturing}"
							style="width:110px;background-color: rgb(255, 255, 255);" />
							<span class="input-group-addon">
               				<span class="glyphicon glyphicon-calendar" onclick="showDatePickerManufactureDate(${status.index})"></span>
               				</span></div></td>
					<td class="form-group date">
						<div class="input-group date"><form:input
							path="installableFormList[${status.index}].dateofReceipt"
							readonly="true"
							disabled="${emmsDataForm.freeze}"
							class="form-control receiptDateTimePicker${status.index}"
							title="${installableForm.dateofReceipt}"
							style="width:110px;background-color: rgb(255, 255, 255);"
							onchange="disableIndicatorRows()" />
							<span class="input-group-addon">
							<span class="glyphicon glyphicon-calendar" onclick="showDatePickerReceiptDate(${status.index})"></span>
               				</span></div></td>


					<c:set var="class1" value="defaultstatus"></c:set>

					<c:if test="${installableForm.errorStatus=='Validated'}">
						<c:set var="class1" value="validated"></c:set>
					</c:if>
					<c:if test="${installableForm.errorStatus=='Not Validated'}">
						<c:set var="class1" value="notvalidated"></c:set>
					</c:if>
					<c:if test="${installableForm.errorStatus=='Warning'}">
						<c:set var="class1" value="Warning"></c:set>
					</c:if>
					<c:if
						test="${installableForm.errorStatus=='Validated With Warning'}">
						<c:set var="class1" value="Warning"></c:set>
					</c:if>


					<td><form:input
							path="installableFormList[${status.index}].errorStatus"
							id="status" class="${class1} form-control"
							title="${installableForm.errorStatus}"
							style="width:110px; background-color: rgb(216, 216, 216);"
							readonly="true" /></td>
					<td><form:input
							path="installableFormList[${status.index}].errorDescription"
							title="${installableForm.errorDescription}" class="form-control"
							style="width:110px; background-color: rgb(216, 216, 216);"
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
						value="Validate" id="validate" style="width: 70px;" name="action"
						class="btn" /></td>

					<td><input type="submit" name="action" id="export"
						onclick="success(${size})" value="Export" class="btn"
						style="width: 70px;"></td>

					<td><input type="submit" name="action" id="import"
						disabled="disabled" value="Import" class="btn"
						style="width: 70px;" onclick="showProgress(${size}) "></td>

					<td><form:input type="file" path="installableExcelfile"
							id="installableExcelfile" onchange="checkExtension()" /></td>




				</tr>
			</thead>
			<input type="hidden" value="0" id="disableIndicator"
				name="disableIndicator" />
			<input type="hidden" value="" id="linkSelected" name="linkSelected" />
			<input type="submit" style="visibility: hidden;" id="linkSelection"
				name="action" onclick="" />
		</table>

	</form:form>


</body>

</html>