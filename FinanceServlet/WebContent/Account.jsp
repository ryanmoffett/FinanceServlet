<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="finance.Account"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%Account a = (Account) request.getSession().getAttribute("Account"); %>
Account Screen
<br>
Welcome to your account, <%=session.getAttribute("Account Holder") %>. What do you want to do?
<br>
Current Funds: <%=a.getFunds() %>
<br>
<form method="get" action="AccountServlet">
<input type="radio" name="act" value="Deposit"/>Deposit
<br>
<input type="radio" name="act" value="Withdraw"/>Withdraw
<br>
<input type="radio" name="act" value="History"/>Check History
<br>
<input type="radio" name="act" value="Logout"/>Logout
<br>
<input type="submit"/>

</form>

</body>
</html>