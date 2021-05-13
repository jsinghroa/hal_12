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
<link href="${path}/resources/theme/css/jquery.timepicker.css"
	rel="stylesheet" type="text/css" />
<link href="${path}/resources/theme/css/jquery.timepicker.min.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${path}/resources/theme/js/jquery-1.9.1.js"></script>
<script type="text/javascript"
	src="${path}/resources/theme/js/jquery-1.10.2.min.js"></script>

<script type="text/javascript"
	src="${path}/resources/theme/js/jquery.ui.datepicker.js"></script>

<script type="text/javascript"
	src="${path}/resources/theme/js/jquery.dataTables.js"></script>
<script type="text/javascript"
	src="${path}/resources/theme/js/jquery.timepicker.js"></script>
<script type="text/javascript"
	src="${path}/resources/theme/js/jquery.timepicker.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		$('.listOfRecords').DataTable({
			"pagingType" : "full_numbers",
			"pageLength" : 20,
			"aLengthMenu" : [5,10,15,20],
			"scrollY": "200",
			"scrollX":true,
			"scrollCollapse":true,
			 "order": []
		});
		
	});
	
	// To change sortie status based on the id of column
	function changeSortieStatus(sortieStatusOf, desiredStatus, index) {
		console.log(document.getElementById(sortieStatusOf).value);
		if (document.getElementById(sortieStatusOf).value === "PENDING") {
			$("#" + sortieStatusOf).val(desiredStatus);
			const timeNow = new Date();
			$("#statusDate__" + index).val(convertTime(timeNow));
		}
	}
	
	// To convert date in 'YYYY-MM-DD hh:mm' format
	function convertTime(date) {
		const year = date.getFullYear();
		const month = date.getMonth();
		const day = date.getDate();
		const hours = date.getHours();
		const minutes = date.getMinutes();
		return year + "-" + prefixZero(month) + "-" + prefixZero(day) + " "
				+ prefixZero(hours) + ":" + prefixZero(minutes);
	}

	// To prefix zero in case day/month/hours/minutes < 10
	function prefixZero(num) {
		return num < 10 ? "0" + num : num;
	}

	$(function() {
		$(".timepickerClass").timepicker({
			timeFormat : 'HH:mm',
			interval : 1
		});
	});

	function LinkSelected(linkSelected) {
		$("#linkSelected").val(linkSelected);
		$("#linkSelection").click();
	}

 	$(function() {	
		$(".timepickerClass").timepicker({
			timeFormat : 'HH:mm',
			interval:1
		});
	});
	function updateStatus(status) {
		console.log(status);
		if (status == "ACTIVE") {
			console.log('inside active')
			document.getElementById("postButton").disabled = true;
		}
	}

	$(function() {
		$(".datepicker1").datepicker(

		{
			maxDate : 0,
			changeMonth : true,
			changeYear : true,
			dateFormat : 'dd-M-yy',
			showOn : 'button',
			buttonImageOnly : true,
			buttonImage : '/HAL_12/theme/images/datepicker.jpg'
		});
	});

	$(function() {
		$(".datepicker2").datepicker({
			maxDate : 0,
			changeMonth : true,
			changeYear : true,
			dateFormat : 'dd-M-yy',
			showOn : 'button',
			buttonImageOnly : true,
			buttonImage : '/HAL_12/theme/images/datepicker.jpg'
		});
	});
	
	$(function() {
		$(".datepicker3").datepicker({
			maxDate : 0,
			changeMonth : true,
			changeYear : true,
			dateFormat : 'dd-M-yy',
			showOn : 'button',
			buttonImageOnly : true,
			buttonImage : '/HAL_12/theme/images/datepicker.jpg'
		});
	});
	$(function() {
		$(".datepicker4").datepicker({
			maxDate : 0,
			changeMonth : true,
			changeYear : true,
			dateFormat : 'dd-M-yy',
			showOn : 'button',
			buttonImageOnly : true,
			buttonImage : '/HAL_12/theme/images/datepicker.jpg'
		});
	});

	$(function() {
		$(".datepicker5").datepicker({
			maxDate : 0,
			changeMonth : true,
			changeYear : true,
			dateFormat : 'dd-M-yy',
			showOn : 'button',
			buttonImageOnly : true,
			buttonImage : '/HAL_12/theme/images/datepicker.jpg'

		});
	});
	$(function() {
		$(".datepicker4").datepicker({
			maxDate : 0,
			changeMonth : true,
			changeYear : true,
			dateFormat : 'dd-M-yy',
			showOn : 'button',
			buttonImageOnly : true,
			buttonImage : '/HAL_12/theme/images/datepicker.jpg'
		});
	});
	
	var today = new Date();
	$(function() {
		$(".datepicker7").timepicker({

			timeFormat : 'HH:mm',
			interval : 1,
			minTime : '0:0',
			maxTime : today.getHours() + ":" + today.getMinutes(),
			scrollDefault: 'now'
			 

		});
	});
	
	$(function() {
		$(".datepicker6").timepicker({

			timeFormat : 'HH:mm',
			interval : 1,
			minTime : '0:0',
			maxTime : today.getHours() + ":" + today.getMinutes()

		});
	});
	
		
		
		
	
	function updateTableLength(size) 
	{
	console.log('hhh'+size);
	var table=$('#ListOfRecords').DataTable();
	
	table.page.len(size+1).draw();

	};

	
	
	
	
</script>
</head>

<body>

	<form:form method="POST" action="${path}/FLB/saveFlb"
		commandName="emmsDataForm"
		style="overflow-y: auto; overflow-x: hidden; height: 91%;">
		<div class="pageheader">
			<spring:message code="label.FLB.header" />
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

					<td><form:label path="flbValidationStatus">
							<spring:message code="label.flbValidationStatus" />
						</form:label><span style="color: red; font-weight: bold; margin-left: -46px;"></span></td>
					<td><form:input path="flbValidationStatus"
							value="${emmsDataForm.flbValidationStatus}" class="form-control"
							readonly="true"
							style="background-color: rgb(216, 216, 216); width: 90%; margin-left:-30%" /></td>
					<td><form:input type="hidden" path="flbStatus" id="statusFlag"
							class="form-control" value="${emmsDataForm.flbStatus}" /></td>

				</tr>

				<tr>
					<td><form:label path="inductionDate">
							<spring:message code="label.inductionDate" />
						</form:label><span style="color: red; font-weight: bold; margin-left: -46px;"></span></td>
					<td class="datepicker2"><form:input type="text"
							path="inductionDate" class="form-control datepicker1"
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
							value="${emmsDataForm.signalOutDate}"
							class="datepicker2 form-control"
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
			<br /> <br />
		</div>


		<table id="ListOfRecords" class="listOfRecords tabletopmargin10"
			style="width: 100%; overflow-x: hidden;">
			<thead>
				<tr>
					<td class="tableheading"><spring:message code="label.SortieAR" />
					</td>
				</tr>
				<tr>
					<td class="tableheading"><spring:message code="label.SortieNo" /></td>
					<td class="tableheading"><spring:message
							code="label.SortieDate" /></td>
					<td class="tableheading"><spring:message code="label.etdDate" /></td>
					<td class="tableheading"><spring:message code="label.ETD" /></td>
					<td class="tableheading"><spring:message code="label.Duration" /></td>
					<td class="tableheading"><spring:message
							code="label.FlightType" /></td>
					<td class="tableheading"><spring:message
							code="label.SortieStatus" /></td>
					<td class="tableheading"><spring:message
							code="label.StatusDate" /></td>
					<td class="tableheading"><spring:message
							code="label.Changedby" /></td>
					<td class="tableheading"><spring:message code="label.Remarks" /></td>
					<td class="tableheading"><spring:message code="label.Reason" /></td>
					<td class="tableheading"><spring:message code="label.Accept" /></td>
					<td class="tableheading"><spring:message code="label.Reject" /></td>
					<td class="tableheading"><spring:message
							code="label.ErrorStatus" /></td>
					<td class="tableheading"><spring:message
							code="label.ErrorDescription" /></td>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="FLBForm" varStatus="status"
					items="${emmsDataForm.flbSortieArFormList}">
					<tr>
						<td><form:input
								path="flbSortieArFormList[${status.index}].sortieNo"
								class="form-control" title="${FLBForm.sortieNo }"
								style="width:100px; text-align: center; background-color: rgb(216, 216, 216);"
								readonly="true" /></td>
						<td class="datepicker"><form:input
								path="flbSortieArFormList[${status.index}].sortieDate"
								id="sortieDate__${status.index }"
								class="form-control datepicker3" title="${FLBForm.sortieDate}"
								style="width:125px; word-wrap:break-word;background-color: rgb(255, 255, 255);" />
						</td>
						<td class="datepicker"><form:input
								path="flbSortieArFormList[${status.index}].etdDate"
								class="form-control datepicker3" title="${FLBForm.etdDate}"
								style="width:125px;" /></td>
						<td><form:input
								path="flbSortieArFormList[${status.index}].ETD"
								class="form-control timepickerClass" title="${FLBForm.ETD}"
								style="width:120px;" /></td>
						<td><form:input
								path="flbSortieArFormList[${status.index}].duration"
								class="form-control timepickerClass" title="${FLBForm.duration}"
								style="width:120px;" /></td>
						<td><form:input
								path="flbSortieArFormList[${status.index}].flightType"
								class="form-control" title="${FLBForm.flightType}"
								style="width:80px;" /></td>
						<td><form:input id="sortieStatus__${status.index}"
								path="flbSortieArFormList[${status.index}].sortieStatus"
								class="form-control" title="${FLBForm.sortieStatus}"
								style="width:130px; background-color: rgb(216, 216, 216);"
								readonly="true" /></td>
						<td><form:input id="statusDate__${status.index }"
								path="flbSortieArFormList[${status.index}].statusDate"
								class="form-control" title="${FLBForm.statusDate}"
								style="width:140px; background-color: rgb(216, 216, 216);"
								readonly="true" /></td>
						<td><form:input
								path="flbSortieArFormList[${status.index}].changedBy"
								class="form-control" title="${FLBForm.changedBy}"
								style="width:80px; background-color: rgb(216, 216, 216);"
								readonly="true" /></td>
						<td><form:input
								path="flbSortieArFormList[${status.index}].remarks"
								class="form-control" title="${FLBForm.remarks}"
								style="width:100px;" /></td>
						<td><form:input
								path="flbSortieArFormList[${status.index}].reason"
								class="form-control" title="${FLBForm.reason}"
								style="width:120px; " /></td>
						<td><img
							onclick="changeSortieStatus('sortieStatus__${status.index}', 'ACCEPTED', '${status.index }')"
							class="icon__acceptReject" src="${path}/theme/images/accept.gif"
							style="cursor: pointer;" alt="accept" /></td>
						<td><img
							onclick="changeSortieStatus('sortieStatus__${status.index}', 'REJECTED', '${status.index }')"
							class="icon__acceptReject" src="${path}/theme/images/reject.gif"
							style="cursor: pointer;" alt="reject" /></td>

<c:set var="class1" value="defaultstatus"></c:set>
					<c:if test="${FLBForm.error=='Validated'}">
						<c:set var="class1" value="validated"></c:set>
					</c:if>
					<c:if test="${FLBForm.error=='Not Validated'}">
						<c:set var="class1" value="notvalidated"></c:set>
					</c:if>

						<td><form:input
								path="flbSortieArFormList[${status.index}].error"
								class="${class1 } form-control" title="${FLBForm.error}"
								style="width:70px; background-color: rgb(216, 216, 216);"
								readonly="true" /></td>
						<td><form:input
								path="flbSortieArFormList[${status.index}].errordesc"
								class="form-control" title="${FLBForm.errordesc}"
								style="width:100px; background-color: rgb(216, 216, 216);"
								readonly="true" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<div style="margin-top: 0; margin-bottom: 10vh; margin-right: 10px;">
			<input type="submit" style="float: right;" value="New Row"
				name="addSortieRow" class="btn" />
		</div>

		<c:set var="size" value="0"></c:set>
		<table id="ListOfRecords" class="listOfRecords tabletopmargin10"
			width="100%" style="overflow-x: hidden;">
			<thead>
				<tr>
					<td class="tableheading"><spring:message
							code="label.postFlightData" /></td>
				</tr>
				<tr>
					<td
						style="font-size: 13px; font-weight: bold !important; line-height: 22px;">
						<spring:message code="label.fLBNo" />
					</td>
					<td
						style="font-size: 13px; font-weight: bold !important; line-height: 22px;">
						<spring:message code="label.SortieNo" />
					</td>
					<td
						style="font-size: 13px; font-weight: bold !important; line-height: 22px;">
						<spring:message code="label.flightnumber" />
					</td>
					<td
						style="font-size: 13px; font-weight: bold !important; line-height: 22px;">
						<spring:message code="label.flightDate" />
					</td>
					<td
						style="font-size: 13px; font-weight: bold !important; line-height: 22px;">
						<spring:message code="label.departureTime" />
					</td>
					<td
						style="font-size: 13px; font-weight: bold !important; line-height: 22px;">
						<spring:message code="label.arrivalTime" />
					</td>
					<td
						style="font-size: 13px; font-weight: bold !important; line-height: 22px;">
						<spring:message code="label.flightHours" />
					</td>
					<td
						style="font-size: 13px; font-weight: bold !important; line-height: 22px;">
						<spring:message code="label.flightType" />
					</td>
					<td
						style="font-size: 13px; font-weight: bold !important; line-height: 22px;">
						<spring:message code="label.status" />
					</td>
					<td
						style="font-size: 13px; font-weight: bold !important; line-height: 22px;">
						<spring:message code="label.ErrorStatus" />
					</td>
					<td
						style="font-size: 13px; font-weight: bold !important; line-height: 22px;">
						<spring:message code="label.ErrorDescription" />
					</td>
					

				</tr>
			</thead>
			<tbody>
				<c:forEach var="FLBForm" varStatus="status"
					items="${emmsDataForm.flbPostFlightDataFormList}">
					<tr>
						<td><form:input
								path="flbPostFlightDataFormList[${status.index}].flbNo"
								class="form-control" title="${FLBForm.flbNo }"
								style="width:160px; background-color: rgb(216, 216, 216);"
								readonly="true" /></td>
								
								<td><form:select
								path="flbPostFlightDataFormList[${status.index}].sortieNumber"
								class="form-control status" style="width:150px;"
								title="${FLBForm.sortieNumber}">
								<form:option value="" label="- - -Select- - -"></form:option>
								<form:options items="${FLBForm.listSortieNumber}" />
							</form:select></td>
						<td><form:input
								path="flbPostFlightDataFormList[${status.index}].flightNumber"
								readonly="true" class="form-control"
								title="${FLBForm.flightNumber}"
								style="width:230px; background-color: rgb(216, 216, 216);" /></td>
						<td class="flbDatePicker"><form:input
								path="flbPostFlightDataFormList[${status.index}].flightDate"
								class="form-control datepicker5" title="${FLBForm.flightDate}"
								style="width:165px;" /></td>
						<td class="flbDatePicker1"><form:input
								class="form-control datepicker6" readonly="true"
								path="flbPostFlightDataFormList[${status.index}].departureTime"
								title="${FLBForm.departureTime}" style="width:190px;" /></td>
						<td class="flbDatePicker2"><form:input
								class="form-control datepicker7" readonly="true"
								path="flbPostFlightDataFormList[${status.index}].arrivalTime"
								title="${FLBForm.arrivalTime}" style="width:184px;" /></td>
						<td><form:input
								path="flbPostFlightDataFormList[${status.index}].flightHours"
								class="form-control " title="${FLBForm.flightHours}"
								style="width:420px; background-color: rgb(216, 216, 216);"
								readonly="true" /></td>
						<td><form:select
								path="flbPostFlightDataFormList[${status.index}].flightType"
								class="form-control" style="width:143px;"
								title="${FLBForm.flightType}">
								<form:option value="" label="- - -Select- - -"></form:option>
								<form:options items="${FLBForm.flightTypes}" />
							</form:select></td>

						<td><form:select
								path="flbPostFlightDataFormList[${status.index}].status"
								class="form-control status" style="width:150px;"
								title="${FLBForm.status}">
								<form:option value="" label="- - -Select- - -"></form:option>
								<form:options items="${FLBForm.statuses}" />
							</form:select></td>
							
							<c:set var="class1" value="defaultstatus"></c:set>
					<c:if test="${FLBForm.error=='Validated'}">
						<c:set var="class1" value="validated"></c:set>
					</c:if>
					<c:if test="${FLBForm.error=='Not Validated'}">
						<c:set var="class1" value="notvalidated"></c:set>
					</c:if>
					
							
							
							
							
							
						<td><form:input
								path="flbPostFlightDataFormList[${status.index}].error"
								class="${class1} form-control" title="${FLBForm.error}"
								style="width:70px; background-color: rgb(216, 216, 216);"
								readonly="true" /></td>
						<td><form:input
								path="flbPostFlightDataFormList[${status.index}].errorDesc"
								class="${class1} form-control" title="${FLBForm.errorDesc}"
								style="width:100px; background-color: rgb(216, 216, 216);"
								readonly="true" /></td>

						<script>
							document.addEventListener('DOMContentLoaded',
									function() {
										updateStatus('${FLBForm.status }');
									});
						</script>


					</tr>
					<c:set var="size" value="${status.index}"></c:set>
				</c:forEach>
			</tbody>



		</table>

		<div style="margin-top: 0; margin-bottom: 10vh;">
			<input type="submit" onclick="updateTableLength(${size})"
				value="New Row" id="postButton" name="addPostFlightRow" class="btn"
				style="float: right; margin-right: 10px;" />
		</div>

		<input type="submit" name="action"
			onclick="updateTableLength(${size})" id="validate" value="Save"
			class="btn"
			style="width: 80px; margin-left: auto; margin-right: auto">
		<br />
		<br />


		<table id="ListOfRecords2" class="listOfRecords tabletopmargin10"
			width="100%" style="overflow-x: hidden;">
			<thead>
				<tr colspan='8'>
					<td class="tableheading"><spring:message
							code="label.meterDetails" /></td>
				</tr>
				<tr>
					<td
						style="font-size: 13px; font-weight: bold !important; line-height: 22px;">
						<spring:message code="label.meterDetails" />
					</td>
					<td
						style="font-size: 13px; font-weight: bold !important; line-height: 22px;">
						<spring:message code="label.lcn" />
					</td>
					<td
						style="font-size: 13px; font-weight: bold !important; line-height: 22px;">
						<spring:message code="label.installedpn" />
					</td>
					<td
						style="font-size: 13px; font-weight: bold !important; line-height: 22px;">
						<spring:message code="label.installedsn" />
					</td>
					<td
						style="font-size: 13px; font-weight: bold !important; line-height: 22px;">
						<spring:message code="label.builditem" />
					</td>
					<td
						style="font-size: 13px; font-weight: bold !important; line-height: 22px;">
						<spring:message code="label.meter" />
					</td>
					<td
						style="font-size: 13px; font-weight: bold !important; line-height: 22px;">
						<spring:message code="label.description" />
					</td>
					<td
						style="font-size: 13px; font-weight: bold !important; line-height: 22px;">
						<spring:message code="label.readingType" />
					</td>
					<td
						style="font-size: 13px; font-weight: bold !important; line-height: 22px;">
						<spring:message code="label.updatedby" />
					</td>
					<td
						style="font-size: 13px; font-weight: bold !important; line-height: 22px;">
						<spring:message code="label.meterReading" />
					</td>
					<td
						style="font-size: 13px; font-weight: bold !important; line-height: 22px;">
						<spring:message code="label.error" />
					</td>
					<td
						style="font-size: 13px; font-weight: bold !important; line-height: 22px;">
						<spring:message code="label.errorDesc" />
					</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="FLBForm" varStatus="status"
					items="${emmsDataForm.flbMeterDetailsFormList}">
					<tr>
						<td><form:input
								path="flbMeterDetailsFormList[${status.index}].meterDetails"
								class="form-control" value="${FLBForm.meterDetails }"
								style="width:90px; background-color: rgb(216, 216, 216);"
								readonly="true" /></td>
						<td><form:input
								path="flbMeterDetailsFormList[${status.index}].lcn"
								class="form-control" value="${FLBForm.lcn}" style="width:100px;" /></td>
						<td><form:input
								path="flbMeterDetailsFormList[${status.index}].installedpn"
								class="form-control" value="${FLBForm.installedpn}"
								style="width:100px;" /></td>
						<td><form:input
								path="flbMeterDetailsFormList[${status.index}].installedsn"
								class="form-control" value="${FLBForm.installedsn}"
								style="width:100px; background-color: rgb(216, 216, 216);"
								readonly="true" /></td>
						<td><form:input
								path="flbMeterDetailsFormList[${status.index}].builditem"
								class="form-control" value="${FLBForm.builditem}"
								style="width:140px; background-color: rgb(216, 216, 216);"
								readonly="true" /></td>
						<td><form:input
								path="flbMeterDetailsFormList[${status.index}].meter"
								class="form-control" value="${FLBForm.meter}"
								style="width:100px; background-color: rgb(216, 216, 216);"
								readonly="true" /></td>
						<td><form:input
								path="flbMeterDetailsFormList[${status.index}].description"
								class="form-control" value="${FLBForm.description}"
								style="width:114px; background-color: rgb(216, 216, 216);"
								readonly="true" /></td>
						<td><form:input
								path="flbMeterDetailsFormList[${status.index}].readingType"
								class="form-control" value="${FLBForm.readingType}"
								style="width:90px; background-color: rgb(216, 216, 216);"
								readonly="true" /></td>
						<td><form:input
								path="flbMeterDetailsFormList[${status.index}].updatedby"
								class="form-control" value="${FLBForm.updatedby}"
								style="width:100px; background-color: rgb(216, 216, 216);"
								readonly="true" /></td>
						<td><form:input
								path="flbMeterDetailsFormList[${status.index}].meterReading"
								class="form-control" value="${FLBForm.meterReading}"
								style="width:100px; background-color: rgb(216, 216, 216);"
								readonly="true" /></td>
						<td><form:input
								path="flbMeterDetailsFormList[${status.index}].error"
								class="form-control" value="${FLBForm.error}"
								style="width:100px; background-color: rgb(216, 216, 216);"
								readonly="true" /></td>
						<td><form:input
								path="flbMeterDetailsFormList[${status.index}].errordesc"
								class="form-control" value="${FLBForm.errordesc}"
								style="width:600px; background-color: rgb(216, 216, 216);"
								readonly="true" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<br />
		<br />

		<input type="submit" value="Submit"
			style="width: 70px; margin-left: auto; margin-right: auto"
			name="action" class="btn" style="color: gray; " />



		<input type="hidden" value="" id="linkSelected" name="linkSelected" />
		<input type="submit" style="visibility: hidden;" id="linkSelection"
			name="action" onclick="" />
	</form:form>


	<!-- 
		Script for datepickers
	 -->

	<script type="text/javascript">
		 $(function() {
				$("#etdDatepicker").datepicker({
					minDate : 0,
					changeMonth : true,
					changeYear : true,
					dateFormat : 'dd-M-yy',
					showOn : 'button',
					buttonImageOnly : true,
					buttonImage : '/HAL_12/theme/images/datepicker.jpg'
				});
			});
	 </script>

</body>

</html>