<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="jQtestTwo.css" />
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="submit_to_db.php">
	<div id="container">
		<label for="name">Name</label>
		<input type="text" name="name" id="name" />
		
		<label for="email">Email Address</label>
		<input type="text" name="email" id="email" />
		
		<label for="comments">Comments</label>
		<textarea rows="5" cols="35" name="comments" id="comments"></textarea>
		<br />
		<input type="submit" name="submit" id="submit" value="Go!" />
	</div>
	</form>
</body>
</html>