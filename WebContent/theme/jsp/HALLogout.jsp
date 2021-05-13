<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script>
window.location.hash="no-back-button";
window.location.hash="Again-No-back-button";
window.onhashchange=function(){window.location.hash="no-back-button";}
</script>
<title>Development EMMS</title>
<%
request.getSession().invalidate();

 %>
 <c:set var="path" value="${pageContext.request.contextPath}"></c:set>
</head>



<body>

<style>
html, body, div, span, applet, object, iframe,
h1, h2, h3, h4, h5, h6, p, blockquote, pre,
a, abbr, acronym, address, big, cite, code,
del, dfn, em, img, ins, kbd, q, s, samp,
small, strike, strong, sub, sup, tt, var,
b, u, i, center,
dl, dt, dd, ol, ul, li,
fieldset, form, label, legend,
table, caption, tbody, tfoot, thead, tr, th, td,
article, aside, canvas, details, embed, 
figure, figcaption, footer, header, hgroup, 
menu, nav, output, ruby, section, summary,
time, mark, audio, video {
	margin: 0;
	padding: 0;
	border: 0;
	font-size: 100%;
	font: inherit;
	vertical-align: baseline;
}
/* HTML5 display-role reset for older browsers */
article, aside, details, figcaption, figure, 
footer, header, hgroup, menu, nav, section {
	display: block;
}
body {
	line-height: 1;
}
ol, ul {
	list-style: none;
}
blockquote, q {
	quotes: none;
}
blockquote:before, blockquote:after,
q:before, q:after {
	content: '';
	content: none;
}
table {
	border-collapse: collapse;
	border-spacing: 0;
}


/*Calender*/
body {
	font-family:Arial, Helvetica, sans-serif;
	font-size:12px;
	line-height:1.2;
	background: #002945;
    *overflow-x:hidden;
	*position: relative;
	overflow-x:hidden;
}
a {
	color: #2673a6;
	text-decoration:none;
}
a:hover {
	color:#2673a6;
	text-decoration:underline;
}
p b
{
	font-weight:bold;
}

.dspnone {
	display:none;
}
/*login page start*/


/*login page start*/
.toplogin
{
  position: fixed;
  top: 50%;
  left: 50%;
  margin-top: -268px;
  margin-left: -522px;
  height:536px;
  width:1044px;
}
.login {
	background:#999 url(../../theme/images/bg-login.jpg) no-repeat;
	width: 1024px;
	float: left;
	overflow: hidden;
	padding: 20px 10px;
	min-height:450px;

}
.lg-hadder {
	width: 1024px;
	float: left;
}
.lg-logo {
	width: 512px;
	float: left;
}
.lg-emms {
	width: 512px;
	float: left;
	text-align:right;
}
.lg-heading {
	width: 1024px;
	float: left;
	text-align:center;
	margin: 28px 0;
}
.lg-heading h1{
	width: 320px;
	text-align:center;
	margin:0 auto;
	background:url(../../theme/images/blue-bg.png);
	font-size: 24px;
	color:#fff;
	padding: 10px;
	font-weight: bold;
	border:2px solid #57a3d8;
	-webkit-border-radius: 5px;  
	-moz-border-radius: 5px;  
	border-radius: 5px;
}
.lg-body {
	width: 1024px;
	float: left;
}
.lg-body .lg-box1, .lg-body .lg-box2 {
	width: 452px;
	float: left;
	background:url(../../theme/images/bg-box.png) repeat;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	padding:20px;
	margin:10px;
}

.lg-body h2 {
	padding-bottom: 28px;
}
.lg-num
{
	float:left;
	color:#fff;
	background:#F60;
	float:left;
	width:30px;
	height:30px;
	-webkit-border-radius: 50px;  
	-moz-border-radius: 50px;  
	border-radius: 50px;
	line-height: 30px;
	text-align: center;
	font-size: 12px;
	border: 2px solid #fff;
}
ul.instruction
{
	list-style:none;
	margin: 9px 0;
}
ul.instruction li
{
	clear:both;
	list-style:none;
	display: block;
	width: 100%;
	overflow: hidden;
	padding-bottom: 3px;
}
.lg-text
{
    padding-left: 14px;
    float: left;
    width: 395px;
    line-height: 35px;
}
.frm-text
{
    float: left;
    width: 158px;
    font-size: 12px;
    line-height: 26px;
}
.frm-input
{
    float: left;  
    width: 283px;  font-size: 15px;
}
.inputbox
{
    height: 31px;
}
.lg-btn
{
    float: left;
    width: 200px;
    text-align: right;
    padding-right: 10px;
}
.lg-forgot
{
float: left;
width: 277px;
line-height: 35px;
font-weight: bold;
padding-left: 161px;
}
.lg-forgot a {
color: #2673a6;
    text-decoration: underline;
}
.lg-body .lg-box2 ul li
{
    clear: both;
    display: block;
    padding-bottom: 15px;
    overflow: hidden;
}
.btn-submit
{
margin: 0 auto;
font-weight: bold;
border: 0;
}
.btn-submit:hover
{
color: #fff;
}
.lg-mandatory
{
	text-align:right;
	color:#f00;
	padding-right:10px;
	font-weight:bold;
}
.lg-footer .foot-cont
{

    color: #C3E1F8;
    font-size: 11px;
    line-height: 20px;
    padding: 0 14px;    
    line-height:4;
	
}
.lg-footer
{
	margin: 0 0px;
}

/*Logout start*/

.lgo-heading {
	width: 1024px;
	float: left;
	text-align:center;
	margin: 128px 0 150px;
}
.lgo-heading h1{
	width: 637px;
	text-align: left;
	margin:0 auto;
	background:#57a3d8;
	font-size: 18px;
	color:#fff;
	padding: 20px 25px;
	font-weight: bold;
	-webkit-border-radius: 5px;  
	-moz-border-radius: 5px;  
	border-radius: 5px;
	line-height: 2;
	font-weight: normal;
}
.lgo-heading h1 a{
    color: #01A1D3;
    background: #fff;
    padding: 1px 5px;
    font-weight: bold;
	border:3px solid #fff;
	-webkit-border-radius: 5px;  
	-moz-border-radius: 5px;  
	border-radius: 5px;
}
.lgo-heading h1 a:hover{
    background-color: #01A1D3;
    color: #fff;
    font-weight: bold;
	text-decoration:none;
}

/*inner page format*/


.btn
{
	
background: #57a3d8 url(../../theme/images/bg-btn-hover.gif) repeat-x;
color:#fff;
padding:5px 10px;
font-size:12px;
border:1px solid #006;
cursor:pointer;
}
/*Edit 23082014*/
.btn:hover
{
color:#e9f2f9;
background: #57a3d8 url(../../theme/images/bg-btn.gif) repeat-x;
}

.bginput, .inputbox {
	height: 24px;
	width: 96%;
	padding:0 2%;
	line-height: 21px;
	border: 1px solid #D5D5D5;
	color: rgb(31, 31, 31);
	background:#fff;
}

.heading, .allpopubox h3
{
color: #2174ad;
font-size: 20px;
font-weight: bold;
}
.even
{
	background:#e9f2f9;
}
#appointment.even, .fullist
{
	width:958px !important;
}
.smallmessage
{
	font-size:12px;
	font-style:italic;
}



 .bestview
{
font-size: 11px;
position: absolute;
right: 8px;
text-align: right;
bottom: 4px;
color:#9FB8BB;
}
.lg-footer .bestview
{
font-size: 11px;
position: absolute;
right: 8px;
text-align: right;
bottom: 15px;
color: #9FB8BB;
}
.footer {
	background: #4e738d;
	width: 1044px;

	text-align: center;
	clear: both;
	position:relative;
}
.bowserbox>.alltopover>.allpopubox>.allpopup>p
{
	background:url(../../theme/images/li-arrow.png) left no-repeat; padding-left:19px; margin-left:15px;
}
@media(max-width:1100px) {
	body
	{
		overflow:scroll;
	}
	.toplogin {
position: static;
top: 0;
left: 0;
margin-top: 0;
margin-left: auto;
height: 536px;
width: 1044px;
margin: 0 auto;
}
}
/*#errorContainer>ul>li
{
	display:none !important;
}
#errorContainer>ul>li:nth-child(1)
{
	display:block !important;
}*/
</style>

    <div class="toplogin">
    <div class="login">

        <div class="lg-hadder">
        	<div class="lg-logo"><img src="../../resources/theme/images/logo-login.gif" /></div>
            <div class="lg-emms"><img src="../../resources/theme/images/emms-logo.png" /></div>
        </div>
        
        <div class="lgo-heading">
        	<h1>You have successfully logged out from HAL.<br /><a href="">Click here</a> to login again</h1>
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