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
  Integer fromYear = (Integer) request.getSession().getAttribute("fromYear");
  Integer toYear = (Integer) request.getSession().getAttribute("toYear");
  String flag = (String) request.getSession().getAttribute("flag");
%>

<form method="get" action="HistoryReturn">
	<input type="submit" value="Return to History"/>
</form>
<br>
Your<%if(flag==null){out.println();}else{if(flag.equals((String)"deposit")){out.println(" deposit");}else{out.println(" withdraw");}} %> history from <%=fromYear %> to <%=toYear %>:
<%
if(flag==null){for (int i=0;i<a.getHistory(fromYear,toYear).size();i++){%> <br>
	<% out.println(a.getHistory(fromYear,toYear).get(i).getWhere()+" "+a.getHistory(fromYear,toYear).get(i).getAmount()
			+" "+a.getHistory(fromYear,toYear).get(i).getMonth()+"/"+a.getHistory(fromYear,toYear).get(i).getDay()+"/"
			+a.getHistory(fromYear,toYear).get(i).getYear());} %>
<%}else{if(flag.equals((String)"deposit")){for (int i=0;i<a.getDepositHistory(fromYear,toYear).size();i++){%><br>
	<% out.println(a.getDepositHistory(fromYear,toYear).get(i).getWhere()+" "+a.getDepositHistory(fromYear,toYear).get(i).getAmount()
			+" "+a.getDepositHistory(fromYear,toYear).get(i).getMonth()+"/"+a.getDepositHistory(fromYear,toYear).get(i).getDay()+"/"
			+a.getDepositHistory(fromYear,toYear).get(i).getYear());} %>
<%}else{for (int i=0;i<a.getCheckHistory(fromYear,toYear).size();i++){ %><br>
	<% out.println(a.getCheckHistory(fromYear,toYear).get(i).getWhere()+" "+a.getCheckHistory(fromYear,toYear).get(i).getAmount()
			+" "+a.getCheckHistory(fromYear,toYear).get(i).getMonth()+"/"+a.getCheckHistory(fromYear,toYear).get(i).getDay()+"/"
			+a.getCheckHistory(fromYear,toYear).get(i).getYear());} %>
<%}} %>
</body>
</html>