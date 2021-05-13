<%-- <%@page language="java" contentType="text/html; charset=ISO-8859-1"
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
<link href="${path}/resources/theme/css/theme.css"
	rel="stylesheet" type="text/css" />
<link href="${path}/resources/theme/css/home_new.css"
	rel="stylesheet" type="text/css" />
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

$(function()
		{
	$("#datepicker1").datepicker(
			{
				maxDate:0,
				changeMonth:true,
				changeYear:true,
				dateFormat:'yy-mm-dd'
			}		
	);
		});


$(function()
		{
	$("#datepicker2").datepicker(	{
		maxDate:0,
		changeMonth:true,
		changeYear:true,
		dateFormat:'yy-mm-dd'
	}	);
		});
$(function()
		{
	$("#datepicker3").datepicker(	{
		maxDate:0,
		changeMonth:true,
		changeYear:true,
		dateFormat:'yy-mm-dd'
	}	);
		});
$(function()
		{
	$("#datepicker4").datepicker(	{
		maxDate:0,
		changeMonth:true,
		changeYear:true,
		dateFormat:'yy-mm-dd'
	}	);
		});


</script>
</head>

<body>

<form:form method="POST" action="${path}/FLB/saveFlb" commandName="emmsDataForm" style="overflow: auto; height: 500px;">  
<div class="pageheader">
		<spring:message code="label.FLB.header" />
	</div>  
       <div>
      
      <table id="ListView" title="List of Records" align="center" class="tabletopmargin10" width="100%" style="overflow-x: auto;">
			
				<thead>
				<tr>
					<td class="tableheading" width="16%" align="center">
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
	<br></br>
        <table align="center" class="mdmtables" width="100%" height="100px" style="overflow-x: auto;" >
        
         <tr>    
             <td width="16%"><form:label path="recordId"> 
             <spring:message code="label.recordId"/> 
             </form:label><span style="color: red; font-weight: bold; margin-left: -46px;"></span></td>   
             <td width="16%"><form:input path="recordId" value="${emmsDataForm.recordId}"  readonly="true" 
             style="background-color: rgb(216, 216, 216); width: 149px;" /></td>  
             
             <td width="16%"><form:label path="model"> 
             <spring:message code="label.model"/> 
             </form:label><span style="color: red; font-weight: bold; margin-left: -46px;"></span></td>   
             <td width="16%"><form:input path="model" value="${emmsDataForm.model}"  readonly="true" 
             style="background-color: rgb(216, 216, 216); width: 149px;"/></td>  
              
              <td width="16%"><form:label path="recordStatus"> 
             <spring:message code="label.recordStatus"/> 
             </form:label><span style="color: red; font-weight: bold; margin-left: -46px;"></span></td>   
             <td width="16%"><form:input path="recordStatus" value="${emmsDataForm.recordStatus}"  readonly="true"
             style="background-color: rgb(216, 216, 216); width: 149px;" /></td>  
          
         </tr> 
            
         <tr>    
             <td><form:label path="location"> 
             <spring:message code="label.location"/> 
             </form:label><span style="color: red; font-weight: bold; margin-left: -46px;"></span></td>   
             <td><form:input path="location" value="${emmsDataForm.location}"  readonly="true" 
             style="background-color: rgb(216, 216, 216); width: 149px;"/></td>  
         
             <td><form:label path="mainAsset"> 
             <spring:message code="label.mainAsset"/> 
             </form:label><span style="color: red; font-weight: bold; margin-left: -46px;"></span></td>   
             <td><form:input path="mainAsset" value="${emmsDataForm.mainAsset}"  readonly="true"
             style="background-color: rgb(216, 216, 216); width: 149px;" /></td>  
            
             <td><form:label path="assetPmStatus"> 
             <spring:message code="label.InsAssetstatus"/> 
             </form:label><span style="color: red; font-weight: bold; margin-left: -46px;"></span></td>   
             <td><form:input path="assetPmStatus" value="${emmsDataForm.flbStatus}"  readonly="true"
             style="background-color: rgb(216, 216, 216); width: 149px;" /></td>  
         
             
         </tr>         
        
        <tr>    
             <td><form:label path="inductionDate"> 
             <spring:message code="label.inductionDate"/> 
             </form:label><span style="color: red; font-weight: bold; margin-left: -46px;"></span></td> 
             <td><form:input type="text" id="datepicker1" path="inductionDate"  value="${emmsDataForm.inductionDate}" 
             style="background-color: rgb(216, 216, 216); width: 149px;" /></td>  
             
             <td><form:label path="mainAssetPart"> 
             <spring:message code="label.mainAssetPart"/> 
             </form:label><span style="color: red; font-weight: bold; margin-left: -46px;"></span></td>   
             <td><form:input path="mainAssetPart" value="${emmsDataForm.mainAssetPart}"  readonly="true"
             style="background-color: rgb(216, 216, 216); width: 149px;" /></td>  
            
            
             
         
         </tr>  
        
        <tr>    
             <td><form:label path="signalOutDate"> 
             <spring:message code="label.signalOutDate"/> 
             </form:label><span style="color: red; font-weight: bold; margin-left: -46px;"></span></td>   
             <td><form:input path="signalOutDate" id="datepicker2" value="${emmsDataForm.signalOutDate}" 
             style="background-color: rgb(216, 216, 216); width: 149px;"/></td> 
             
             
             <td><form:label path="mainAssetSerial"> 
             <spring:message code="label.mainAssetSerial"/> 
             </form:label><span style="color: red; font-weight: bold; margin-left: -46px;"></span></td>   
             <td><form:input path="mainAssetSerial" value="${emmsDataForm.mainAssetSerial}"  readonly="true"
             style="background-color: rgb(216, 216, 216); width: 149px;" /></td>  
            
              
         </tr>  
               
        </table>
         <br/><br/>         
        </div>
       
      <table id="ListOfRecords" align="center" class="tabletopmargin10" style="width: 802px; overflow-x: hidden;">  
        
        <thead>
        <tr colspan='14'>
        <td class="tableheading" > <spring:message code="label.SortieAR"/> </td>
        </tr>
        <tr>
        <td class="tableheading"><spring:message code="label.SortieNo"/></td>
        <td class="tableheading"><spring:message code="label.SortieDate"/></td>
        <td class="tableheading"><spring:message code="label.ETD"/></td>
        <td class="tableheading"><spring:message code="label.Duration"/></td>
        <td class="tableheading"><spring:message code="label.FlightType"/></td>
        <td class="tableheading"><spring:message code="label.SortieStatus"/></td>
        <td class="tableheading"><spring:message code="label.StatusDate"/></td>
        <td class="tableheading"><spring:message code="label.Changedby"/></td>
        <td class="tableheading"><spring:message code="label.Remarks"/></td>
        <td class="tableheading"><spring:message code="label.Reason"/></td>
        <td class="tableheading"><spring:message code="label.Accept"/></td>
        <td class="tableheading"><spring:message code="label.Reject"/></td>
        <td class="tableheading"><spring:message code="label.ErrorStatus"/></td>
        <td class="tableheading"><spring:message code="label.ErrorDescription"/></td>
         </tr>
        </thead>
         <c:forEach var="FLBForm" varStatus="status" items="${emmsDataForm.flbFormList}">  
         <tr>
        <td>
        <form:input path="flbList[${status.index}].sortieNo"  value="${FLBForm.sortieNo }" style="width:70px; background-color: rgb(216, 216, 216);" readonly="true"/>
        </td>
        <td>
        <form:input id="datepicker3" path="flbList[${status.index}].sortieDate"  value="${FLBForm.sortieDate}" style="width:70px;" />
        </td>
        <td>
        <form:input path="flbList[${status.index}].ETD"  value="${FLBForm.ETD}" style="width:70px;" />
        </td>
        <td>
        <form:input path="flbList[${status.index}].duration"  value="${FLBForm.duration}" style="width:70px;"/>
        </td>
        <td>
        <form:input path="flbList[${status.index}].flightType"  value="${FLBForm.flightType}" style="width:70px;"/>
        </td>
        <td>
        <form:input path="flbList[${status.index}].sortieStatus"  value="${FLBForm.sortieStatus}"  style="width:410px; background-color: rgb(216, 216, 216);" readonly="true"/>
        </td>
        <td>
        <form:input path="flbList[${status.index}].statusDate"  value="${FLBForm.statusDate}" style="width:70px; background-color: rgb(216, 216, 216);" readonly="true" />
        </td>
        <td>
        <form:input path="flbList[${status.index}].changedBy"  value="${FLBForm.changedBy}" style="width:70px; background-color: rgb(216, 216, 216);" readonly="true"/>
        </td>
        <td>
        <form:input path="flbList[${status.index}].remarks"  value="${FLBForm.remarks}" style="width:70px;"/>
        </td>
       ----  <td><form:input class="datepicker3" path="FLBFormList[${status.index}].lastCompiledDate"  value="${FLBForm.lastCompiledDate}" style="width:100px;" />
        </td>
        <td><form:input class="datepicker4" path="FLBFormList[${status.index}].nextDueDate" value="${FLBForm.nextDueDate}" style="width:100px;" />
        </td> ----
         <td>
        <form:input path="flbList[${status.index}].reason"  value="${FLBForm.reason}" style="width:70px; " />
        </td>
         <td>
        <form:input path="flbList[${status.index}].accept"  value="${FLBForm.accept}" style="width:70px;"/>
        </td>
         <td>
        <form:input path="flbList[${status.index}].reject"  value="${FLBForm.reject}" style="width:70px;"/>
        </td>
        
        <td>
        <form:input path="flbList[${status.index}].error"  value="${FLBForm.error}" style="width:70px; background-color: rgb(216, 216, 216);" readonly="true"/>
        </td>
        <td>
        <form:input path="flbList[${status.index}].errorDesc"  value="${FLBForm.errorDesc}" style="width:70px; background-color: rgb(216, 216, 216);" readonly="true"/>
        </td>
        </tr>
        </c:forEach> 
           
           </table>
           
           
           
           <table id="ListOfRecords" align="center" class="tabletopmargin10" style="width: 802px; overflow-x: hidden; position: absolute;">
           <thead>
        <tr colspan='8'>
        <td class="tableheading"> <spring:message code="label.postFlightData"/> </td>
        </tr>
        <tr>
        <td style="font-size: 13px; font-weight: bold !important; line-height: 22px;" ><spring:message code="label.fLBNo"/></td>
        <td style="font-size: 13px; font-weight: bold !important; line-height: 22px;" ><spring:message code="label.flightnumber"/></td>
        <td style="font-size: 13px; font-weight: bold !important; line-height: 22px;" ><spring:message code="label.flightData"/></td>
        <td style="font-size: 13px; font-weight: bold !important; line-height: 22px;" ><spring:message code="label.departureTime"/></td>
        <td style="font-size: 13px; font-weight: bold !important; line-height: 22px;" ><spring:message code="label.arrivalTime"/></td>
        <td style="font-size: 13px; font-weight: bold !important; line-height: 22px;" ><spring:message code="label.flightHours"/></td>
        <td style="font-size: 13px; font-weight: bold !important; line-height: 22px;" ><spring:message code="label.flightType"/></td>
        <td style="font-size: 13px; font-weight: bold !important; line-height: 22px;" ><spring:message code="label.status"/></td>
         </tr>
        </thead>
         <c:forEach var="FLBForm" varStatus="status" items="${emmsDataForm.flbFormList}">  
         <tr>
        <td>
        <form:input path="flbList[${status.index}].flbNo"  value="${FLBForm.flbNo }" style="width:70px; background-color: rgb(216, 216, 216);" readonly="true"/>
        </td>
        <td>
        <form:input path="flbList[${status.index}].flightNumber"  value="${FLBForm.flightNumber}" style="width:70px;" />
        </td>
        <td>
        <form:input path="flbList[${status.index}].flightData"  value="${FLBForm.flightData}" style="width:70px;" />
        </td>
        <td>
        <form:input class="datepicker3" path="flbList[${status.index}].departureTime"  value="${FLBForm.departureTime}" style="width:100px;" />
        </td>
        <td>
        <form:input class="datepicker4" path="flbList[${status.index}].arrivalTime"  value="${FLBForm.arrivalTime}" style="width:100px;" />
        </td>
        <td>
        <form:input path="flbList[${status.index}].flightHours"  value="${FLBForm.flightHours}"  style="width:410px; background-color: rgb(216, 216, 216);" readonly="true"/>
        </td>
        <td>
        <form:input path="flbList[${status.index}].flightType"  value="${FLBForm.flightType}" style="width:70px; background-color: rgb(216, 216, 216);" readonly="true" />
        </td>
        <td>
        <form:input path="flbList[${status.index}].status"  value="${FLBForm.status}" style="width:70px; background-color: rgb(216, 216, 216);" readonly="true"/>
        </td>
        </tr>
        </c:forEach>
        </table> 
           
           
           <table id="ListOfRecords" align="center" class="tabletopmargin10" style="width: 802px; overflow-x: hidden;">
           <thead>
        <tr colspan='8'>
        <td class="tableheading"> <spring:message code="label.meterDetails"/> </td>
        </tr>
        <tr>
        <td style="font-size: 13px; font-weight: bold !important; line-height: 22px;" ><spring:message code="label.meterDetails"/></td>
        <td style="font-size: 13px; font-weight: bold !important; line-height: 22px;" ><spring:message code="label.lcn"/></td>
        <td style="font-size: 13px; font-weight: bold !important; line-height: 22px;" ><spring:message code="label.installedpn"/></td>
        <td style="font-size: 13px; font-weight: bold !important; line-height: 22px;" ><spring:message code="label.installedsn"/></td>
        <td style="font-size: 13px; font-weight: bold !important; line-height: 22px;" ><spring:message code="label.builditem"/></td>
        <td style="font-size: 13px; font-weight: bold !important; line-height: 22px;" ><spring:message code="label.meter"/></td>
        <td style="font-size: 13px; font-weight: bold !important; line-height: 22px;" ><spring:message code="label.description"/></td>
        <td style="font-size: 13px; font-weight: bold !important; line-height: 22px;" ><spring:message code="label.readingType"/></td>
        <td style="font-size: 13px; font-weight: bold !important; line-height: 22px;" ><spring:message code="label.updatedby"/></td>
        <td style="font-size: 13px; font-weight: bold !important; line-height: 22px;" ><spring:message code="label.meterReading"/></td>
        <td style="font-size: 13px; font-weight: bold !important; line-height: 22px;" ><spring:message code="label.error"/></td>
        <td style="font-size: 13px; font-weight: bold !important; line-height: 22px;" ><spring:message code="label.errorDesc"/></td>
         </tr>
        </thead>
        
         <c:forEach var="FLBForm" varStatus="status" items="${emmsDataForm.flbFormList}">  
         <tr>
        <td>
        <form:input path="flbList[${status.index}].meterDetails"  value="${FLBForm.meterDetails }" style="width:70px; background-color: rgb(216, 216, 216);" readonly="true"/>
        </td>
        <td>
        <form:input path="flbList[${status.index}].lcn"  value="${FLBForm.lcn}" style="width:70px;" />
        </td>
        <td>
        <form:input path="flbList[${status.index}].installedpn"  value="${FLBForm.installedpn}" style="width:70px;" />
        </td>
        <td>
        <form:input path="flbList[${status.index}].installedsn"  value="${FLBForm.installedsn}"  style="width:410px; background-color: rgb(216, 216, 216);" readonly="true"/>
        </td>
        <td>
        <form:input path="flbList[${status.index}].builditem"  value="${FLBForm.builditem}" style="width:70px; background-color: rgb(216, 216, 216);" readonly="true" />
        </td>
        <td>
        <form:input path="flbList[${status.index}].meter"  value="${FLBForm.meter}" style="width:70px; background-color: rgb(216, 216, 216);" readonly="true"/>
        </td>
        <td>
        <form:input path="flbList[${status.index}].description"  value="${FLBForm.description}" style="width:70px; background-color: rgb(216, 216, 216);" readonly="true"/>
        </td>
        <td>
        <form:input path="flbList[${status.index}].readingType"  value="${FLBForm.readingType}" style="width:70px; background-color: rgb(216, 216, 216);" readonly="true"/>
        </td>
        <td>
        <form:input path="flbList[${status.index}].updatedby"  value="${FLBForm.updatedby}" style="width:70px; background-color: rgb(216, 216, 216);" readonly="true"/>
        </td>
        <td>
        <form:input path="flbList[${status.index}].meterReading"  value="${FLBForm.meterReading}" style="width:70px; background-color: rgb(216, 216, 216);" readonly="true"/>
        </td>
        <td>
        <form:input path="flbList[${status.index}].error"  value="${FLBForm.error}" style="width:70px; background-color: rgb(216, 216, 216);" readonly="true"/>
        </td>
        <td>
        <form:input path="flbList[${status.index}].errorDesc"  value="${FLBForm.errorDesc}" style="width:70px; background-color: rgb(216, 216, 216);" readonly="true"/>
        </td>
        </tr>
        </c:forEach> 
        </table>  
        <br/><br/>
                  
         <input type="submit" value="Submit" style="width: 70px ; margin-left: 625px;" name="action" class="btn" style="color: gray; "/></td>    
          
     
      
       <input type="hidden" value="" id="linkSelected" name="linkSelected"/>
<input type="submit" style="visibility: hidden;" id="linkSelection" name="action" onclick=""/> 
</form:form>


</body>

</html>--%>