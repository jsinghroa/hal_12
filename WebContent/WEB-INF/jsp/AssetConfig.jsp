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
<script type="text/javascript"
	src="${path}/resources/theme/js/config.js"></script>

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
				
				var status1 = document.getElementById("rStatus").value;
				console.log('jsp' + status);
				if (status1 == "Validation Completed") {
					
					document.getElementById("freeze").disabled = false;
					document.getElementById("freeze").setAttribute("style",
							"color:white; width: 70px ;margin-left:-70%");

				}
					
				
				var ele = document.getElementsByTagName('indicator'); 
				var sta= document.getElementsByTagName('status');
		        // Loop through each element.
		        for (i = 0; i < ele.length && sta.length; i++) {
		        	
					console.log('inside for');
					
		            // Check the element type
		            
		            if (ele[i].type == 'text' && ele[i].value == 'I' && sta[i].value == 'Not Validated' ) {
		            	console.log('inside if');
		            	document.getElementsByTagName('indicator').disabled = true;    // Disable the button.
		                return false;
		            }
		            else {
		            	document.getElementsByTagName('indicator').disabled = false;   // Enable the button.
		            }
		        }
				
				
				
				
				
				var freezeFlag = document.getElementById("freezeFlag").value;
				console.log('freeze=' + freezeFlag);
				if (freezeFlag == "true") {
					console.log('freezetrue=' + freezeFlag);
					document.getElementById("validate").disabled = true;
					document.getElementById("save").disabled = true;
					document.getElementById("export").disabled = true;
					document.getElementById("import").disabled = true;
					
				}

				var bulkImportStatus = document
						.getElementById("bulkImportStatus").value;
				console.log('inside import sattus');
				console.log(bulkImportStatus);
				if (bulkImportStatus == "Error") {
					alert('Error While Importing the Data');
				}

			});
			
			
	/* function manageindicator() {
        //var bt = document.getElementById('submit');
        var ele = document.getElementsByTagName('indicator'); 
		var sta= document.getElementsByTagName('status');
        // Loop through each element.
        for (i = 0; i < ele.length && sta.length; i++) {
        	
			console.log('inside for');
			
            // Check the element type
            
            if (ele[i].type == 'text' && ele[i].value == 'I' && sta[i].value == 'Not Validated' ) {
            	console.log('inside if');
                bt.disabled = true;    // Disable the button.
                return false;
            }
            else {
                bt.disabled = false;   // Enable the button.
            }
        }
    } */    

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

		var file = document.getElementById("assetExcelfile").value;
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
	//609200
	//130.230-wasadmin-secure@123
	//155.0.130.230:9062/admin
	$(function() {
		$("#datepicker1")
				.datepicker(
						{
							maxDate : 0,
							changeMonth : true,
							changeYear : true,
							dateFormat : 'dd-M-yy',
							showOn : 'button',
							buttonImageOnly : true,
							buttonImage : '/HAL_12/theme/images/datepicker.jpg',
							disabled : document.getElementById("freezeFlag").value === "true" ? true
									: false
						});
	});

	$(function() {
		$("#datepicker2")
				.datepicker(
						{
							maxDate : 0,
							
							changeMonth : true,
							changeYear : true,
							dateFormat : 'dd-M-yy',
							showOn : 'button',
							buttonImageOnly : true,
							buttonImage : '/HAL_12/theme/images/datepicker.jpg',
							disabled : document.getElementById("freezeFlag").value === "true" ? true
									: false

						});
	});
	$(function() {
		$(".datepicker3")
				.datepicker(
						{
							maxDate : 0,
							changeMonth : true,
							changeYear : true,
							dateFormat : 'dd-M-yy',
							showOn : 'button',
							buttonImageOnly : true,
							buttonImage : '/HAL_12/theme/images/datepicker.jpg',
							disabled : document.getElementById("freezeFlag").value === "true" ? true
									: false

						});
	});
	$(function() {
		$(".datepicker4")
				.datepicker(
						{
							maxDate : 0,
							changeMonth : true,
							changeYear : true,
							dateFormat : 'dd-M-yy',
							showOn : 'button',
							buttonImageOnly : true,
							buttonImage : '/HAL_12/theme/images/datepicker.jpg',
							disabled : document.getElementById("freezeFlag").value === "true" ? true
									: false

						});
	});

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

	<form:form method="POST" commandName="emmsDataForm"
		style="<%-- overflow: auto; --%> overflow-y: auto; overflow-x: hidden; height: 91%;"
		action="${path}/ASSET/saveAsset?${_csrf.parameterName }=${_csrf.token }"
		enctype="multipart/form-data">
		<div class="pageheader">
			<spring:message code="label.asset.header" />
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

			<div class="overlay" id="popo_olay2" style="display: none;"></div>
			<div class="topover" id="popo_dolo2" style="display: none;">
				<div class="popubox hppopubox"
					style="margin: -149px auto !important;">
					<div class="popup">
						<div class="pageheader"
							style="width: 522px; margin-left: -10px; margin-top: -9px; height: 40px;">
							<spring:message code="label.confirmPOPUP.heading" />
						</div>
						<div>
							On saving the record, system will freeze Asset Configuration tab
							before populating data into PM and Meter tab.<br /> Do you wish
							to continue? <br />

							<div>
								<div class="allBtn" align="center">
									<div class="inBtn">
										<table>
											<tr>
												<td><input type="submit" value="Yes" 
													style="width: 70px; margin-left: 120px;" class="btn"
													name="action" id="yes"></td>
												<td><a class="btn"
													style="height: 26px; margin-left: 20px;"
													onclick="closeConfirm()">No</a></td>
											</tr>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>





			<br></br>
			<table align="center" class="mdmtables" width="100%" height="100px"
				style="overflow-x: auto;">

				<tr>
					<td width="16%"><form:label path="recordId">
							<spring:message code="label.recordId" />
						</form:label><span style="color: red; font-weight: bold; margin-left: -46px;"></span></td>
					<td width="16%"><form:input path="recordId"
							class="form-control" value="${emmsDataForm.recordId}"
							readonly="true"
							style="background-color: rgb(216, 216, 216); width: 70%;" /></td>

					<td width="16%"><form:label path="model">
							<spring:message code="label.model" />
						</form:label><span style="color: red; font-weight: bold; margin-left: -46px;"></span></td>
					<td width="16%"><form:input path="model" class="form-control"
							value="${emmsDataForm.model}" readonly="true"
							style="background-color: rgb(216, 216, 216); width: 70%;" /></td>

					<td width="16%"><form:label path="recordStatus">
							<spring:message code="label.recordStatus" />
						</form:label><span style="color: red; font-weight: bold; margin-left: -46px;"></span></td>
					<td><form:input path="recordStatus" id="rStatus"
							class="form-control" value="${emmsDataForm.recordStatus}"
							readonly="true"
							style="background-color: rgb(216, 216, 216); width: 100%; margin-left:-30%" /></td>

					<td><form:input type="hidden" path="freeze" id="freezeFlag"
							class="form-control" value="${emmsDataForm.freeze}"
							readonly="true" /></td>

					<td align="left"><input type="button" value="Freeze"
						disabled="disabled" id="freeze" name="action" class="btn"
						onclick="showConfirm()"
						style="color: gray; width: 70px; margin-left: -70%" /></td>

					<td><form:input type="hidden" path="bulkImportStatus"
							id="bulkImportStatus" class="form-control"
							value="${emmsDataForm.bulkImportStatus}" readonly="true" /></td>

				</tr>

				<tr>
					<td><form:label path="location">
							<spring:message code="label.location" />
						</form:label><span style="color: red; font-weight: bold; margin-left: -46px;"></span></td>
					<td><form:input path="location" class="form-control"
							value="${emmsDataForm.location}" readonly="true"
							style="background-color: rgb(216, 216, 216); width: 70%;" /></td>

					<td><form:label path="mainAsset">
							<spring:message code="label.mainAsset" />
						</form:label><span style="color: red; font-weight: bold; margin-left: -46px;"></span></td>
					<td><form:input path="mainAsset" class="form-control"
							value="${emmsDataForm.mainAsset}" readonly="true"
							style="background-color: rgb(216, 216, 216); width: 70%;" /></td>

					<td width="150px"><form:label path="assetConfigStatus">
							<spring:message code="label.assetConfigStatus" />
						</form:label><span style="color: red; font-weight: bold; margin-left: -46px;"></span></td>
					<td><form:input path="assetConfigStatus" id="configStatus"
							class="form-control" value="${emmsDataForm.assetConfigStatus}"
							readonly="true"
							style="background-color: rgb(216, 216, 216);width: 100%; margin-left:-30%" /></td>


				</tr>

				<tr>
					<td><form:label path="inductionDate">
							<spring:message code="label.inductionDate" />
						</form:label><span style="color: red; font-weight: bold; margin-left: -46px;"></span></td>
					<td class="datepicker2"><form:input type="text"
							id="datepicker1" readonly="true" class="form-control"
							path="inductionDate" value="${emmsDataForm.inductionDate}"
							style="background-color: rgb(216, 216, 216); width: 70%;" /></td>

					<td><form:label path="mainAssetPart">
							<spring:message code="label.mainAssetPart" />
						</form:label><span style="color: red; font-weight: bold; margin-left: -46px;"></span></td>
					<td><form:input path="mainAssetPart" class="form-control"
							value="${emmsDataForm.mainAssetPart}" readonly="true"
							style="background-color: rgb(216, 216, 216); width: 70%;" /></td>




				</tr>

				<tr>
					<td><form:label path="signalOutDate">
							<spring:message code="label.signalOutDate" />
						</form:label><span style="color: red; font-weight: bold; margin-left: -46px;"></span></td>
					<td class="datepicker3"><form:input path="signalOutDate"
							id="datepicker2" readonly="true"
							value="${emmsDataForm.signalOutDate}" class="form-control"
							style="background-color: rgb(216, 216, 216); width: 70%;" /></td>


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
			width="100%" style="overflow-x: hidden;">

			<thead>
				<tr>
					<td class="tableheading"><spring:message
							code="label.indicator" /></td>
					<td class="tableheading"><spring:message code="label.lcn" /></td>
					<td class="tableheading"><spring:message code="label.position" /></td>
					<td class="tableheading"><spring:message
							code="label.buildItem" /></td>
					<td class="tableheading"><spring:message code="label.assetNum" /></td>
					<td class="tableheading"><spring:message code="label.partNum" /></td>
					<td class="tableheading"><spring:message
							code="label.partDescription" /></td>
					<td class="tableheading"><spring:message
							code="label.serialNum" /></td>
					<td class="tableheading"><spring:message
							code="label.installedPN" /></td>
					<td class="tableheading"><spring:message code="label.inLieuPn" /></td>
					<td class="tableheading"><spring:message
							code="label.installedSN" /></td>
					<td class="tableheading"><spring:message
							code="label.conditionCode" /></td>
					<td class="tableheading"><spring:message
							code="label.dateOfManfacturing" /></td>
					<td class="tableheading"><spring:message
							code="label.dateOfReciept" /></td>
					<td class="tableheading"><spring:message
							code="label.errorStatus" /></td>
					<td class="tableheading"><spring:message
							code="label.errorDesc" /></td>
				</tr>
			</thead>
			<c:forEach var="assetConfigForm" varStatus="status"
				items="${emmsDataForm.assetFormList}">
				<tr style="width: 100%;">
					<td><form:input
							path="assetFormList[${status.index}].indicator"
							class="form-control" title="${assetConfigForm.indicator }" 
							id="indicator"
							style="width:70px; background-color: rgb(216, 216, 216); word-wrap:break-word; "
							readonly="true"  /></td>
					<td><form:input path="assetFormList[${status.index}].lcn"
							class="form-control" title="${assetConfigForm.lcn}"
							style="width:140px; background-color: rgb(216, 216, 216); word-wrap:break-word; "
							readonly="true" /></td>
					<td><form:input path="assetFormList[${status.index}].position"
							class="form-control" title="${assetConfigForm.position}"
							style="width:70px; background-color: rgb(216, 216, 216); word-wrap:break-word;"
							readonly="true" /></td>
					<td><form:input
							path="assetFormList[${status.index}].buildItem"
							class="form-control" title="${assetConfigForm.buildItem}"
							style="width:410px; background-color: rgb(216, 216, 216); word-wrap:break-word;"
							readonly="true" /></td>
					<td><form:input path="assetFormList[${status.index}].assetNum"
							class="form-control" title="${assetConfigForm.assetNum}"
							style="width:80px; background-color: rgb(216, 216, 216); word-wrap:break-word;"
							readonly="true" /></td>
					<td><form:input path="assetFormList[${status.index}].partNum"
							class="form-control" title="${assetConfigForm.partNum}"
							style="width:71px; background-color: rgb(216, 216, 216); word-wrap:break-word;"
							readonly="true" /></td>
					<td><form:input
							path="assetFormList[${status.index}].partDescription"
							class="form-control" title="${assetConfigForm.partDescription}"
							style="width:90px; background-color: rgb(216, 216, 216); word-wrap:break-word;"
							readonly="true" /></td>
					<td><form:input
							path="assetFormList[${status.index}].serialNum"
							class="form-control" title="${assetConfigForm.serialNum}"
							style="width:70px; background-color: rgb(216, 216, 216); word-wrap:break-word;"
							readonly="true" /></td>

					<c:set var="bitype" value="false"></c:set>
					<c:if test="${assetConfigForm.biType=='Logical'}">
						<c:set var="bitype" value="true"></c:set>
					</c:if>

					<td>
					<form:select disabled="${emmsDataForm.freeze || (disableIndicator=='1' && assetConfigForm.indicator=='I' && (assetConfigForm.errorStatus=='Validated' || assetConfigForm.errorStatus=='Warning' || assetConfigForm.errorStatus=='Validated With Warning'))}"
							path="assetFormList[${status.index}].installedPN"
							class="form-control" style="width:130px;word-wrap:break-word;"
							id="installledPN">
							<form:option value="" label="- - -Select- - -"></form:option>
							<form:options items="${assetConfigForm.installedPNList}" />
						</form:select>
						</td>

					<td><form:input path="assetFormList[${status.index}].inLieuPn"
							readonly="${emmsDataForm.freeze || (disableIndicator=='1' && assetConfigForm.indicator=='I' && (assetConfigForm.errorStatus=='Validated' || assetConfigForm.errorStatus=='Warning' || assetConfigForm.errorStatus=='Validated With Warning'))}" class="form-control"
							title="${assetConfigForm.inLieuPn}"
							style="width:70px; word-wrap:break-word;" /></td>
					<td><form:input
							path="assetFormList[${status.index}].installedSN"
							readonly="${emmsDataForm.freeze || (disableIndicator=='1' && assetConfigForm.indicator=='I' && (assetConfigForm.errorStatus=='Validated' || assetConfigForm.errorStatus=='Warning' || assetConfigForm.errorStatus=='Validated With Warning'))}" class="form-control"
							title="${assetConfigForm.installedSN}"
							style="width:70px; word-wrap:break-word;" /></td>
					<%-- value="${assetConfigForm.biType=='Logical'?assetConfigForm.serialNum:assetConfigForm.installedSN}" --%>
					<td><form:select disabled="${emmsDataForm.freeze || (disableIndicator=='1' && assetConfigForm.indicator=='I' && (assetConfigForm.errorStatus=='Validated' || assetConfigForm.errorStatus=='Warning' || assetConfigForm.errorStatus=='Validated With Warning'))}"
							path="assetFormList[${status.index}].conditionCode"
							class="form-control" style="width:130px; word-wrap:break-word;"
							id="conditionCode" title="${assetConfigForm.conditionCode}">
							<form:option value="" label="- - -Select- - -"></form:option>
							<form:options items="${assetConfigForm.conditionCodes}" />


							<%-- <c:forEach
							items="${assetConfigForm.installedPNList}"
							var="pnList">
							<form:option value="${pnList}" label="${pnList}"></form:option>
						</c:forEach> --%>

						</form:select></td>
					<td class="datepicker"><form:input  disabled="${disableIndicator=='1' && assetConfigForm.indicator=='I' && (assetConfigForm.errorStatus=='Validated' || assetConfigForm.errorStatus=='Warning' || assetConfigForm.errorStatus=='Validated With Warning')}"
							path="assetFormList[${status.index}].dateOfManfacturing"
							class="form-control datepicker3"
							title="${assetConfigForm.dateOfManfacturing}"
							style="width:117px; word-wrap:break-word;background-color: rgb(255, 255, 255);" /></td>
					<td class="datepicker1"><form:input  disabled="${disableIndicator=='1' && assetConfigForm.indicator=='I' && (assetConfigForm.errorStatus=='Validated' || assetConfigForm.errorStatus=='Warning' || assetConfigForm.errorStatus=='Validated With Warning')}"
							path="assetFormList[${status.index}].dateOfReciept"
							class="form-control datepicker4"
							title="${assetConfigForm.dateOfReciept}"
							style="width:117px; word-wrap:break-word;background-color: rgb(255, 255, 255);" /></td>


					<c:set var="class1" value="defaultstatus"></c:set>
					<c:if test="${assetConfigForm.errorStatus=='Validated'} ">
						<c:set var="class1" value="validated"></c:set>
					</c:if>
					<c:if test="${assetConfigForm.errorStatus=='Not Validated'}">
						<c:set var="class1" value="notvalidated"></c:set>
					</c:if>
					<c:if test="${assetConfigForm.errorStatus=='Warning'}">
						<c:set var="class1" value="Warning"></c:set>
					</c:if>
					<c:if
						test="${assetConfigForm.errorStatus=='Validated With Warning'}">
						<c:set var="class1" value="Warning"></c:set>
					</c:if>


					<td><form:input
							path="assetFormList[${status.index}].errorStatus" id="status"
							title="${assetConfigForm.errorStatus}"
							style="width:110px; word-wrap:break-word;"
							class="${class1} form-control" readonly="true" /></td>

					<!-- 	{assetConfigForm.errorStatus=='Validated'?'validated':assetConfigForm.errorStatus=='Not Validated'?'notvalidated':'defaultstatus'}
					 -->
					<td><form:input
							path="assetFormList[${status.index}].errorDesc"
							class="form-control" title="${assetConfigForm.errorDesc}"
							style="width:110px; background-color: rgb(216, 216, 216); word-wrap:break-word; "
							readonly="true" /></td>
				</tr>
				<%-- value="${assetConfigForm.biType=='Logical'?'Asset is already attached to the Build Item. The field is read-only.':assetConfigForm.errorDesc}" --%>
			
				<c:set var="size" value="${status.index}"></c:set>
				
			</c:forEach>
					
		</table>
		<br />
		<br />

		<table align="center" width="20%" style="overflow-x: hidden;">
			<thead>
				<tr align="center">

					<td align="right"><input type="submit" onclick="updateTableLength(${size})" value="Save" id="save"
						name="action" class="btn" style="width: 70px;" /></td>

					<td><input type="submit" onclick="updateTableLength(${size})" name="action" id="validate"
						value="Validate" class="btn" style="width: 70px;"></td>

					<td><input type="submit" name="action" id="export"
					onclick="success(${size})"
						value="Export" class="btn" style="width: 70px;"></td>







					<td><input type="submit" name="action" id="import"
						disabled="disabled" value="Import" class="btn"
						style="width: 70px;" onclick="showProgress(${size}) "></td>

					<td><form:input type="file" path="assetExcelfile"
							id="assetExcelfile" onchange="checkExtension()" /></td>


					<!-- <td align="left"><input type="submit" value="Submit"
						id="submits" disabled="disabled" name="action" class="btn"
						style="color: gray; width: 70px;" /></td> -->
				</tr>
			</thead>

			<input type="hidden" value="" id="linkSelected" name="linkSelected" />

			<input type="submit" style="visibility: hidden;" id="linkSelection"
				name="action" onclick="" />



		</table>




		<!-- <table>
	<tr align="center" >
			

			<td><input type="submit" value="Submit" id="submits"
				disabled="disabled" name="action" class="btn"
				style="color: gray; width: 70px; " /></td>
		

		
			<td><input type="submit" name="action" id="validate"
				value="Validate" class="btn"
				style="width: 70px;  margin-top: -29px;"></td>
		
		
			<td><input type="submit" value="Save" id="save" name="action"
				class="btn"
				style="width: 70px;  margin-top: -29px;" /></td>
		
		<td><input type="hidden" value="" id="linkSelected" name="linkSelected" /></td>
	    <td>	<input type="submit" style="visibility: hidden;" id="linkSelection"
			name="action" onclick="" /></td>
	</tr>
		</table> -->

	</form:form>


</body>

</html>
			