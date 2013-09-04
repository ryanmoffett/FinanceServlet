<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="finance.Account" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
Account sessionAccount = (Account) request.getSession().getAttribute("Account");
%>
Transaction successful! Your current balance is <%=sessionAccount.getFunds() %>.
<br>
What do you want to do?
<br>
<br>
<form method="get" action="TransactionOptions">
<input type="radio" name="act" value="MoreTransactions"/>Make more transactions.
<br>
<input type="radio" name="act" value="Logout"/>Logout.
<br>
<input type="submit"/>
</form>

</body>
</html>