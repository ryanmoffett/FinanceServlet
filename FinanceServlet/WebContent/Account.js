$(document).ready(function(){
	
	$('#depositTab').hide();
	$('#withdrawTab').hide();
	$('#historyTab').hide();
	$('#logoutTab').hide();
	
	
	$('#deposit').mouseover(function(){
		$(this).css('cursor','pointer');
	});
	$('#deposit').mouseleave(function(){
		$(this).css('cursor','pointer');
	});
	
	$('#withdraw').mouseover(function(){
		$(this).css('cursor','pointer');
	});
	$('#withdraw').mouseleave(function(){
		$(this).css('cursor','pointer');
	});
	
	$('#history').mouseover(function(){
		$(this).css('cursor','pointer');
	});
	$('#history').mouseleave(function(){
		$(this).css('cursor','pointer');
	});
	
	$('#logout').mouseover(function(){
		$(this).css('cursor','pointer');
	});
	$('#logout').mouseleave(function(){
		$(this).css('cursor','pointer');
	});
	
	
	
	$('#deposit').click(function(){
		$('#depositTab').toggle();
	});
	
	$('#withdraw').click(function(){
		$('#withdrawTab').toggle();
	});
	
	$('#history').click(function(){
		$('#historyTab').toggle();
		document.getElementById('fromYear').value="";
		document.getElementById('toYear').value="";
		var radio = document.getElementsByName('act');
		for(var i=0;i<radio.length;i++){
			radio[i].checked=false;
		}
		
	});
	
	$('#logout').click(function(){
		$('#logoutTab').toggle();
	});
	
	
//	var xmlHttpRequest;
//	if(window.XMLHttpRequest){
//		xmlHttpRequest = new XMLHttpRequest();
//	} else if (window.ActiveXObject){
//		xmlHttpRequest = new ActiveXObject("MICROSOFT.XMLHTTP");
//	}
//	
//	function sendDepositToServer(){
//		xmlHttpRequest.open("GET","Deposit?deposit="+document.getElementById('deposit').value,true);
//		xmlHttpRequest.onreadystatechange = receiveMessageFromServer;
//		xmlHttpRequest.send();
//		document.getElementById('deposit').value="";
//	}
//	
//	function receiveMessageFromServer(){
//		if (xmlHttpRequest.readyState==4 && xmlHttpRequest.status==200){
//			document.getElementById('fundsdisplay').value = xmlHttpRequest.responseXML.getElementsByTagName("responseFromServer")[0].text;
//		}
//	}
});