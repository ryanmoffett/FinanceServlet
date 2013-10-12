<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js" type="text/javascript"></script>
	
	<link rel="stylesheet" href="jQtestCSS.css" />
	
	
	<script type="text/javascript">
	$(function() {
		$('a').hover(function(e){ 
		var html = '<div id="info">';
		html += '<h4>Title Will Go Right Here</h4>';
		html += '<img src="BlackLotus.jpg" alt="image" />';
		html += '<p>stuff</p>';
		html += '</div>';
		
		$('#container').append(html).children('#info').hide().fadeIn(400);
		$('#info').css('top',e.pageY-20).css('left',e.pageX+40);
		
		}, function() {
			//mouse out
			$('#info').remove();
		});
		
		$('a').mousemove(function(e){
			$('#info').css('top',e.pageY-20).css('left',e.pageX+40);
		});
		
	});
	
	</script>

</head>
<body>
	<div id="container">
		<a href="#">Some Movie</a>
	</div>
</body>
</html>