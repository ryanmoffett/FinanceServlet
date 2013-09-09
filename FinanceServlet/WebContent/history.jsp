<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="finance.Account" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%Account a = (Account) request.getSession().getAttribute("Account"); %>
<form method="get" action="Filter">
	Filter search from year <input name="fromYear"/> to <input name="toYear"/>
	<input type="submit"/>
	<br>
	<input type="radio" name="act" value="deposit"/>Just Deposit History.
	<input type="radio" name="act" value="withdraw"/>Just Withdraw History.
</form>
<br>
<form method="get" action="Return">
	<input type="submit" value="Return to Account Screen"/>
</form>
<br>
Current Funds: <%=a.getFunds() %>
<br>
Your history:
<%
for (int i=0;i<a.getHistory().size();i++){ %><br>
	<% out.println(a.getHistory().get(i).getTransaction()+" "+a.getHistory().get(i).getAmount()
			+" "+a.getHistory().get(i).getMonth()+"/"+a.getHistory().get(i).getDay()+"/"
			+a.getHistory().get(i).getYear()); %>
<%}%>
</body>
</html>