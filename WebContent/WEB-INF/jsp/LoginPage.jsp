<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<meta http-equiv="x-ua-compatible" content="IE=9;" />

<meta http-equiv="Cache-Control" content="no-cache,nostore,must-revalidated"/>
<meta http-equiv="Pragma" content="no-cache"/> 
<meta http-equiv="Pragma" content="no-cache"/> 
<meta http-equiv="expire" content="0"/> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Type-Option" content="text/html; charset=utf-8" />
<meta http-equiv="X-Frame-Option" content="deny"/>
<!-- <link href="/portal/wps/mycontenthandler/!ut/p/digest!7zlrCc7sFpfoL3v5NSzquw/war/EMMSThemeStatic/themes/EMMSThemeStatic/images/favicon.ico" rel="shortcut icon" type="image/x-icon" /> -->

<title>HAL Login</title>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<%-- <link href="${path}/resources/theme/css/jquery.dataTables.css" rel="stylesheet" type="text/css" /> --%>
<link href="${path}/resources/theme/css/login.css" rel="stylesheet" type="text/css" />
<link href="${path}/resources/theme/css/home_new.css" rel="stylesheet" type="text/css" />
<SCRIPT LANGUAGE=JavaScript>
var warningString = "<B>WARNING:</B> To maintain your login session, make sure that your browser is configured to accept Cookies.";
document.cookie = 'acceptsCookies=yes';
if(document.cookie == ''){
document.write(warningString);
}
else{
document.cookie = 'acceptsCookies=yes; expires=Fri, 13-Apr-1970 00:00:00 GMT';
}
</SCRIPT>
<SCRIPT type="text/javascript" language="javascript">
//	window.location="/biometric";
</SCRIPT>
<script src="/js/jquery.js" type="text/javascript"></script>
<script src="/js/jquery-ui.js" type="text/javascript"></script>

<style>
.caps-msg{display:none; font-size:12px; background: url("/images/capserror.png") no-repeat 0 center transparent; padding-left:18px;   color:red; font-family: Arial,Helvetica,sans-serif;"}
</style>
</head>
<script type="text/javascript">
$(function(){
var isShiftPressed = false;
var isCapsOn = null;
$("#password").bind("keydown", function(e)
{
var keyCode = e.keyCode? e.keyCode: e.which;
if(keyCode == 16){
isShiftPressed = true;
} 
});
$("#password").bind("keyup",function(e)
{
var keyCode = e.keyCode? e.keyCode: e.which;
if(keyCode == 16){isShiftPressed = false;}
if(keyCode == 20)
{
if(isCapsOn ==  true){isCapsOn = false;
$("#error").hide(); 
}
else if(isCapsOn == false)
{
isCapsOn = true;
$("#error").show();
}
}
});
 $("#password").bind("keypress",function(e)
{
var keyCode = e.keyCode ? e.keyCode: e.which;
if(keyCode >= 65 && keyCode <= 90 && !isShiftPressed)
{
isCapsOn = true;
$("#error").show(); 
}
else{
$("#error").hide();
}
});
});
</script>


<SCRIPT LANGUAGE=JavaScript>

function setCookie(cname,cvalue,exdays)
{
	var d=new Date();
	d.setTime(d.getTime()+(exdays*24*60*60*1000));
	var expires="expires="+d.toGMTString();
	document.cookie=cname+"="+cvalue+";"+expires;
	}
function getCookie(cname) 
{
	var name=cname + "=";
	var ca=document.cookie.split(';');
	for (var i=0; i<ca.length; i++){
	var c= ca[i]	;
	while (c.charAt(0)==' ') c=c.substring(1);
	if (c.indexOf(name)== 0)
	return c.substring (name.length, c.length);
	}
	
	return "";
	}
function checkCookie()
{
	var user=getCookie("username");
	if (user!=""){	
		alert("Welcome again "+ user);
	} else {
	user=prompt ("Please enter your name :, ");
	if (user!= "" && user!= null) {
		setCookie("username",user,30);
	}
	}
	}

</SCRIPT>

<!-- <SCRIPT type="text/javascript" language="javascript">
function foo()
{
	var message = "%ERROR%";
	var errorCode = "";
	var len= message.length;
	var i=0;
	if (message!="")
	{
		i=message.indexOf(" ");
		errorCode= message.substring(0,i);
		message = message.substring(i+1);
		switch(errorCode)
		{
			case 'HPDIA0233W':
			alert(message);
			break;
			case 'HPDIA0200W':
			alert(message);
			break;
			case 'HPDIA0205W':
			alert(message + " Please Contact HelpDesk.");
			break;
			default: 
			alert(message);
		}
		window.location="http://DEVACMGT/portal/wps/myportal";
		//window.location="http://DEVACMGT/maximosit/";
		
	}
}
</SCRIPT> -->
<SCRIPT type="text/javascript" language="javascript">
function validate()
{
	var fields = [document.getElementById('username'),document.getElementById('password')];
	var err = 0;
	for (i=0;i<fields.length;i++)
	{
		if (fields[i].value == "")
		{
			err++;
		}
	}
	if (err == 0)
	{

		validateTnCs();
		//document.myform.submit();
	}
	else
	{
	alert("Service ID or Password is blank. Please enter the required values.");
	} 
	return false;

	
}
</SCRIPT>
<SCRIPT type="text/javascript" language="javascript">
function validate1()
{
	var fields = [document.getElementById('username')];
	var err = 0;
	for (i=0;i<fields.length;i++)
	{
		if (fields[i].value == "")
		{
			err++;
		}
	}
	if (err == 0)
	{
		a();
	}
	else
	{
	alert(" Service ID is blank. Please enter the required value.");
	return false;
	} 
	
}
</SCRIPT>

<SCRIPT type="text/javascript" language="javascript">
function validateTnCs()
{

$("#popo_olay").css("display","block");
$("#popo_dolo").css("display","block");


$("#subBtn").click(function(){
 document.myform.submit();
});

$("#close").click(function(){
$("#popo_olay").css("display","none");
$("#popo_dolo").css("display","none");
});

$("#subBtn").attr("disabled",false);
$("#mycheckbox").attr("checked",true);

$("#mycheckbox").change(function(){
if(this.checked)
{
$("#subBtn").attr("disabled",false);
}
else
{
$("#subBtn").attr("disabled",true);
}
});

}
function a()
{
var x = document.getElementById("myform");
var b = document.getElementById('password').value;
var txt = "";
txt = txt + x.elements[0].value;
window.location="http://155.0.130.232:9080/itim/self/Login/ChallengeResponse.do?&j_username="+txt+"&j_password=&command=load";
}
</SCRIPT>

<script type="text/javascript">
$(function(){
$('input'.placeholder());
});
</script>
</head>
<body>
<div class="overlay" id="popo_olay" style="display:none;" ></div>
    <div class="topover" id="popo_dolo" style="display:none;">
        <div class="popubox hppopubox">    	
            <div class="popup">
                <a href="#" class="close" id="close">X</a>
			<p>You have logged in to a secure network through Biometric and Password authentications. Whatever sign-offs accorded by you on the portal is equivalent of a digitally signed maintenance document.</p>
			<p>Signing Off on any maintenance document knowing it to be false or without ensuring the accuracy & veracity of Aircraft/Airforce equipment would amount to punishable offence under Sections 53(A) & 63(A) of Airforce Act 1950.</p>
		<input type="checkbox" id="mycheckbox"/>I Agree to the Terms and Conditions<div></div><br/>
<div style=" width:70px; margin:12px auto;" ><input type="button" value="Accept" id="subBtn" style=" border:1px solid #006; border-radius:3px; padding:3px; font-weight: bold; background: none repeat scroll 0 0 #e9f2f9;"/></div>
            </div>
        </div>
    </div>
	<div class="toplogin">
    <div class="login">

        <div class="lg-hadder">
        	<div class="lg-logo"><img src="${path}/resources/theme/images/logo-login.gif" /></div>
            <div class="lg-emms"><img src="${path }/resources/theme/images/logo-login-emms.gif" /></div>
        </div>
        
         <div class="lg-heading">
        	<!--<h1>Welcome to e-MMS Portal</h1>-->
                <!--<h1>maintinance</h1>-->
            <img src="${path }/resources/theme/images/welcom.png" />
        </div>
        <div class="lg-body">
        	<div class="lg-box1">
            	<h2 class="heading" style="padding-bottom:4px;">Login Instructions</h2>
                <ul class="instruction">
                	<li>
                    	<div class="lg-num">1</div>
                    	<div class="lg-text">Please enter service number without check suffix. For e.g. 12345</div>
                    </li>
                	<li>
                    	<div class="lg-num">2</div>
                    	<div class="lg-text">Password should be as per IAF password policy.</div>
                    </li>
                	<li>
                    	<div class="lg-num">3</div>
                    	<div class="lg-text">Please click on 'Forgot Password' to reset your password.</div>
                    </li>
                	<li>
                    	<div class="lg-num">4</div>
                    	<div class="lg-text">Biometric credentials required for authentication. </div>
                    </li>
                </ul>
            </div>
            <div class="lg-box2">
            	<h2 class="heading">Login</h2>
                <form:form action="${path }/authenticateTheUser" method="POST">
                <c:if test="${param.error != null }">
					<i style="color: red;">Sorry! you entered invalid credentials.</i>
					<br><br>
				</c:if>
                <ul>                
                	<li>
                    	<div class="frm-text">Username<span> *</span>:</div>
                    	<div class="frm-input"><input type="text" id="username" name="username" SIZE="10" class="inputbox"  placeholder="Please Enter Username"></div>
                    </li>                
                	<li>
                    	<div class="frm-text">Password<span> *</span>:</div>
                    	<div class="frm-input"><input type="password" id="password" name="password" SIZE="15" AUTOCOMPLETE="off" class="inputbox" placeholder="Please Enter Password"><span id="error" class="caps-msg" >Caps Lock is on</span></div>
					</li>	
					<li>
						<div class="lg-forgot"><input TYPE="HIDDEN" NAME="login-form-type" VALUE="pwd">
					    <input class="btn btn-submit" TYPE="SUBMIT" VALUE="Login"> &nbsp; &nbsp; <!-- <a href="#" onclick= "validate1();">Forgot Password ?</a> --></div>
					</li>
                </ul>
                </form:form>
                <div class="lg-mandatory">*Mandatory</div>
            </div>
        </div>
    </div>
        
        
        
    	<!--Footer Part Start-->
    	 <div class="footer lg-footer">
            <div class="foot-cont">Copyright © All Rights Reserved with Indian Air Force</div>
		<div class="bestview">Site best viewed with screen resolution 1280x768px or higher</div>
         </div>
    	<!--Footer Part End-->
    
    </div>

	
</body>
</html>