<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script type="text/javascript"
		src="${path}/resources/theme/js/jquery-1.9.1.js"></script>
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
<link href="${path}/resources/theme/css/alert.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript">
$('document').ready(function(){
	var order=document.getElementById("tableOrder").value;
	var order=document.getElementById("tableOrder").value;
	 if(order==6){
		 document.getElementById("asset1").disabled = true;
	 }
});
function LinkSelected(linkSelected){
	$("#linkSelected").val(linkSelected);
	$("#linkSelection").click();
}
</script>
</head>
<body>
<form:form commandName="tableStatusForm" id="formid" method="POST" action="${path}/importXML/onSubmit?${_csrf.parameterName }=${_csrf.token }" enctype="multipart/form-data" >
<div class="pageheader">
		<spring:message code="label.importPage.header" />
	</div>
	<div>
	<form:errors path="*" cssClass="error" element="div" id="error"></form:errors>
	
	<table id="ListView" title="List of Record IDs" align="center" class="tabletopmargin10" width="100%" style="overflow-x: hidden;">
			
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
					</td> --%>						
				</tr>
				</thead>
	</table>
	<br></br>
	
		<div class="al">
	<%-- <c:if test="${count != null }">
		<c:if test="${count != 4 }">

			<h1 style= "text-align: center; font-size:12px; color: red; "> Count of XML files is not 4!</h1>

		</c:if>

	</c:if> --%>
	<c:if test="${xmlFileCheck == false }">
		
			<h1 style= "text-align: center; font-size:12px; color: red; ">Files doesn't start with <b> IAF_HAL </b>,Import files with proper nomenclature</h1>
		
		</c:if>	
		
		
	<c:if test="${success == true }">
		
			<h1 style= "text-align: center; font-size:12px; color: green; ">All files successfully imported</h1>
		
		</c:if>
	</div>
		

		
<%-- <div class="al">
<c:choose>

	<c:when test="${count != null }">
		<c:when test="${count != 4 }">

			<h1 style= "text-align: center; font-size:12px; color: red; "> Count of XML files is not 4!</h1>

		</c:when>
		<c:otherwise>
			<c:when xmlFileCheck != >
		</c:otherwise>
		</c:when>
		
	</c:when>
	</c:choose>
		</div>--%>

	
<table class="mdmtables"  align ="center" width="750px">
<tr>

<td>
<form:label path="recordId" style="width: 100%;">
	<spring:message code="label.recordId"/></form:label>
</td>
<td>
	<spring:message code="tootTip.recordId" var="toolTipId"/>
<form:input path="recordId" readonly="true"
		style="background-color: rgb(216, 216, 216); margin-right:170px;"
		id="recordId" title="${toolTipId}"></form:input>
		<form:hidden path="tableOrder" id="tableOrder"/>
		</td>
</tr>

</table>
	<div>
	<p style="font-weight: bold;  margin-left: 16px;"><spring:message code="label.Import"/>
			<form:input type="file"  path="zip1" id="asset1" onchange="checkExtension('asset1')" multiple="multiple"
			 style="color: gray; margin-left:35px;"/></p>
	</div>
	
  <table align="center">
			<tr>
				<td><input type="submit" name="action"
					 id="ImportBtn" value="Import" class="button" disabled="disabled"
					 style="color: gray; margin-left:13px; margin-top:20px;" onclick="showProgress() "></td>
			</tr>
		</table> 
 	
		
</div>
<input type="hidden" value="" id="linkSelected" name="linkSelected"/>
<input type="submit" style="visibility: hidden;" id="linkSelection" name="action" onclick=""/>
</form:form>
<div class="overlay" id="popo_olay1" style="display:none;" ></div>
   	 	<div class="topover" id="popo_dolo1" style="display:none;">
        	<div class="popubox hppopubox">    	
                <img src="${path}/resources/theme/images/AjaxLoading.gif" alt="Loading" id="spinner"/>
        	</div>
    	</div>
</body>
</html>