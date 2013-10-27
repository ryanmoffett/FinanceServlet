package finance.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import finance.Account;

//@WebServlet("/Filter")
public class Filter extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Filter() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Account a = (Account) request.getSession().getAttribute("Account");
		String fromYear = request.getParameter("fromYear");
		String toYear = request.getParameter("toYear");
		String flag = request.getParameter("act");
		String result = "";
		
		if(!fromYear.equals("")&&!toYear.equals("")){
			Integer fYear = Integer.parseInt(fromYear);
			Integer tYear = Integer.parseInt(toYear);
			
			if(flag==null||flag.equals((String)"all")){
				for(int i=0;i<a.getHistory(fYear, tYear).size();i++){
					result += "<hist>"+a.getHistory(fYear, tYear).get(i).getTransaction()+" "+a.getHistory(fYear, tYear).get(i).getAmount()
							+" "+a.getHistory(fYear, tYear).get(i).getMonth()+"-"+a.getHistory(fYear, tYear).get(i).getDay()+"-"
							+a.getHistory(fYear, tYear).get(i).getYear()+"</hist>";
				}
			} else{
				if(flag.equals((String)"deposit")){
					for(int i=0;i<a.getDepositHistory(fYear,tYear).size();i++){
						result += "<hist>"+a.getDepositHistory(fYear, tYear).get(i).getTransaction()+" "+a.getDepositHistory(fYear, tYear).get(i).getAmount()
								+" "+a.getDepositHistory(fYear, tYear).get(i).getMonth()+"-"+a.getDepositHistory(fYear, tYear).get(i).getDay()+"-"
								+a.getDepositHistory(fYear, tYear).get(i).getYear()+"</hist>";
					}
				} else{
					for(int i=0;i<a.getCheckHistory(fYear,tYear).size();i++){
						result += "<hist>"+a.getCheckHistory(fYear, tYear).get(i).getTransaction()+" "+a.getCheckHistory(fYear, tYear).get(i).getAmount()
								+" "+a.getCheckHistory(fYear, tYear).get(i).getMonth()+"-"+a.getCheckHistory(fYear, tYear).get(i).getDay()+"-"
								+a.getCheckHistory(fYear, tYear).get(i).getYear()+"</hist>";
					}
				}
			}
			
			response.setContentType("text/xml");
			response.getWriter().println("<historyFromServer>"+result+"</historyFromServer>");
			
		}else{
			
			if(flag==null||flag.equals((String)"all")){
				for(int i=0;i<a.getHistory().size();i++){
					result += "<hist>"+a.getHistory().get(i).getTransaction()+" "+a.getHistory().get(i).getAmount()
							+" "+a.getHistory().get(i).getMonth()+"-"+a.getHistory().get(i).getDay()+"-"
							+a.getHistory().get(i).getYear()+"</hist>";
				}
			} else{
				if(flag.equals((String)"deposit")){
					for(int i=0;i<a.getDepositHistory().size();i++){
						result += "<hist>"+a.getDepositHistory().get(i).getTransaction()+" "+a.getDepositHistory().get(i).getAmount()
								+" "+a.getDepositHistory().get(i).getMonth()+"-"+a.getDepositHistory().get(i).getDay()+"-"
								+a.getDepositHistory().get(i).getYear()+"</hist>";
					}
				} else{
					for(int i=0;i<a.getCheckHistory().size();i++){
						result += "<hist>"+a.getCheckHistory().get(i).getTransaction()+" "+a.getCheckHistory().get(i).getAmount()
								+" "+a.getCheckHistory().get(i).getMonth()+"-"+a.getCheckHistory().get(i).getDay()+"-"
								+a.getCheckHistory().get(i).getYear()+"</hist>";
					}
				}
			}
			
			response.setContentType("text/xml");
			response.getWriter().println("<historyFromServer>"+result+"</historyFromServer>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}


