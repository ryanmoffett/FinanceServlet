<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="finance.Account" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%Account a = (Account) request.getSession().getAttribute("Account");
  String flag = (String) request.getSession().getAttribute("flag");
%>

<form method="get" action="HistoryReturn">
	<input type="submit" value="Return to History"/>
</form>
<br>
Your<%if(flag==null){out.println();}else{if(flag.equals((String)"deposit")){out.println(" deposit");}else{out.println(" withdraw");}} %> history:
<%
if(flag==null){for (int i=0;i<a.getHistory().size();i++){%> <br>
	<% out.println(a.getHistory().get(i).getWhere()+" "+a.getHistory().get(i).getAmount()
			+" "+a.getHistory().get(i).getMonth()+"/"+a.getHistory().get(i).getDay()+"/"
			+a.getHistory().get(i).getYear());} %>
<%}else{if(flag.equals((String)"deposit")){for (int i=0;i<a.getDepositHistory().size();i++){%><br>
	<% out.println(a.getDepositHistory().get(i).getWhere()+" "+a.getDepositHistory().get(i).getAmount()
			+" "+a.getDepositHistory().get(i).getMonth()+"/"+a.getDepositHistory().get(i).getDay()+"/"
			+a.getDepositHistory().get(i).getYear());} %>
<%}else{for (int i=0;i<a.getCheckHistory().size();i++){ %><br>
	<% out.println(a.getCheckHistory().get(i).getWhere()+" "+a.getCheckHistory().get(i).getAmount()
			+" "+a.getCheckHistory().get(i).getMonth()+"/"+a.getCheckHistory().get(i).getDay()+"/"
			+a.getCheckHistory().get(i).getYear());} %>
<%}} %>
</body>
</html>