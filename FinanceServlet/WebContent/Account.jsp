<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="finance.Account"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel='stylesheet' type='text/css' href='Account.css'/>
<link rel='stylesheet' type='text/css' href='http://code.jquery.com/ui/1.9.1/themes/base/jquery-ui.css'/>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js" type="text/javascript"></script>
<script type='text/javascript' src='Account.js'></script>

<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-45561768-1', '54.225.202.132:8080');
  ga('send', 'pageview');

</script>

</head>


<script type="text/javascript">
var xmlHttpRequest;
if(window.XMLHttpRequest){
	xmlHttpRequest = new XMLHttpRequest();
} else if (window.ActiveXObject){
	xmlHttpRequest = new ActiveXObject("MICROSOFT.XMLHTTP");
}

function sendDepositToServer(){
	xmlHttpRequest.open("POST","Deposit?deposit="+document.getElementById('depositamount').value,true);
	xmlHttpRequest.onreadystatechange = receiveMessageFromServer;
	xmlHttpRequest.send();
	document.getElementById('depositamount').value = "";
}

function sendWithdrawToServer(){
	xmlHttpRequest.open("POST","Withdraw?withdraw="+document.getElementById('withdrawamount').value,true);
	xmlHttpRequest.onreadystatechange = receiveMessageFromServer;
	xmlHttpRequest.send();
	document.getElementById('withdrawamount').value = "";
}

function sendHistoryRequestToServer(){
	xmlHttpRequest.open("GET","History",true);
	xmlHttpRequest.onreadystatechange = receiveHistoryFromServer;
	xmlHttpRequest.send();
}

function sendFilterToServer(){
	
	if(document.getElementById('h1').checked){
		xmlHttpRequest.open("GET","Filter?fromYear="+document.getElementById('fromYear').value
				+"&toYear="+document.getElementById('toYear').value
				+"&act=all",true);
	} else if(document.getElementById('h2').checked){
		xmlHttpRequest.open("GET","Filter?fromYear="+document.getElementById('fromYear').value
				+"&toYear="+document.getElementById('toYear').value
				+"&act=deposit",true);
	} else if(document.getElementById('h3').checked){
		xmlHttpRequest.open("GET","Filter?fromYear="+document.getElementById('fromYear').value
				+"&toYear="+document.getElementById('toYear').value
				+"&act=withdraw",true);
	} else{
		xmlHttpRequest.open("GET","Filter?fromYear="+document.getElementById('fromYear').value
				+"&toYear="+document.getElementById('toYear').value,true);
	}
	
	xmlHttpRequest.onreadystatechange = receiveFilterFromServer;
	xmlHttpRequest.send();
}

function receiveHistoryFromServer(){
	if (xmlHttpRequest.readyState==4 && xmlHttpRequest.status==200){
 		result = "";
 		//temp = Array.prototype.slice.call(mlHttpRequest.responseXML.getElementByTagName("historyFromServer")[0].getElementsByTagName("hist"));
  		for (var i=0;i<xmlHttpRequest.responseXML.getElementsByTagName("historyFromServer")[0].getElementsByTagName("hist").length;i++){
			result += xmlHttpRequest.responseXML.getElementsByTagName("historyFromServer")[0].getElementsByTagName("hist")[i].childNodes[0].data+"<br>";
		} 
		document.getElementById('accounthistory').innerHTML = result;
	}
}

function receiveFilterFromServer(){
	if (xmlHttpRequest.readyState==4 && xmlHttpRequest.status==200){
 		result = "";
 		//temp = Array.prototype.slice.call(mlHttpRequest.responseXML.getElementByTagName("historyFromServer")[0].getElementsByTagName("hist"));
  		for (var i=0;i<xmlHttpRequest.responseXML.getElementsByTagName("historyFromServer")[0].getElementsByTagName("hist").length;i++){
			result += xmlHttpRequest.responseXML.getElementsByTagName("historyFromServer")[0].getElementsByTagName("hist")[i].childNodes[0].data+"<br>";
		} 
		document.getElementById('accounthistory').innerHTML = result;
	}
}

function receiveMessageFromServer(){
	if (xmlHttpRequest.readyState==4 && xmlHttpRequest.status==200){
		//document.getElementById('fundsdisplay').value = 500;
		//System.out.println(xmlHttpRequest.responeXML.getElementsByTagName("responseFromServer")[0].text);
		document.getElementById('fundsdisplay').innerHTML = xmlHttpRequest.responseXML.getElementsByTagName("responseFromServer")[0].childNodes[0].data;
	}
}


</script>


<body>
<%Account a = (Account) request.getSession().getAttribute("Account"); %>
Account Screen
<br>
Welcome to your account (with database!), <%=session.getAttribute("Account Holder") %>. What do you want to do?
<br>
Current funds: <span id="fundsdisplay"><%=a.getFunds() %></span>
<br>

<div id="menu">

	<h3 id="deposit">Make deposit</h3>
	<div id="depositTab">
		<p>How much do you want to deposit?</p>
		<br>
		Amount: <input id="depositamount"/>
		<button type="button" onclick="sendDepositToServer()">Deposit</button>	
	</div>
	
	<h3 id="withdraw">Withdraw funds</h3>
	<div id="withdrawTab">
		<p>How much do you want to withdraw?</p>
		<br>
		Amount: <input id="withdrawamount"/>
		<button type="button" onclick="sendWithdrawToServer()">Withdraw</button>
	</div>
	
	<h3 id="history" onclick="sendHistoryRequestToServer()">Check History</h3>
	<div id="historyTab">
		Filter search from year <input id="fromYear"/> to <input id="toYear"/>
		<br>
		<input type="radio" id="h1" name="act" value="all"/>All History.
		<input type="radio" id="h2" name="act" value="deposit"/>Just Deposit History.
		<input type="radio" id="h3" name="act" value="withdraw"/>Just Withdraw History.
		<br>	
		<button type="button" onclick="sendFilterToServer()">Filter History</button>
		<br>

		<p>Your history:</p><br>
		<span id="accounthistory"></span>
	</div>
	
	<h3 id="logout">Logout</h3>
	<div id="logoutTab">
 		<form method="get" action="AccountServlet">
		<input type="submit" name="action" value="Logout">
		</form>
	</div>	

</div>


<br>
<!-- <form method="get" action="AccountServlet">

<input type="radio" name="act" value="Deposit"/>Deposit
<br>
<input type="radio" name="act" value="Withdraw"/>Withdraw
<br>
<input type="radio" name="act" value="History"/>Check History
<br>
<input type="radio" name="act" value="Logout"/>Logout
<br>
<input type="submit"/>

</form> -->

</body>



</html>