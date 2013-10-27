package finance.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import finance.Account;

@WebServlet("/History")
public class History extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public History() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Account a = (Account) request.getSession().getAttribute("Account");
		String result = "";
		for (int i=0;i<a.getHistory().size();i++){
			result += "<hist>"+a.getHistory().get(i).getTransaction()+" "+a.getHistory().get(i).getAmount()
					+" "+a.getHistory().get(i).getMonth()+"-"+a.getHistory().get(i).getDay()+"-"
					+a.getHistory().get(i).getYear()+"</hist>";
		}

		response.setContentType("text/xml");
		response.getWriter().println("<historyFromServer>"+result+"</historyFromServer>");
	
	}

}
