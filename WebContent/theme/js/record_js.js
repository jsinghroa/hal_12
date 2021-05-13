function checkExtension(fieldValue) {
	$('#error').hide();
	var file = document.getElementById(fieldValue).value;
	var reg = /(.*?)\.(zip)$/;
	if (!file.match(reg)) {
		document.getElementById("ImportBtn").disabled = true;
		document.getElementById("ImportBtn").setAttribute("style",
				"color: gray");
		alert("Please upload ZIP File only");
	} else {
		document.getElementById("ImportBtn").disabled = false;
		document.getElementById("ImportBtn").removeAttribute("style");

	}
}

function showProgress() {
	/*alert("File Imported");
	*/$("#popo_olay1").css("display", "block");
	$('#popo_dolo1').css("display", "block");
	$('#spinner').show();

}
