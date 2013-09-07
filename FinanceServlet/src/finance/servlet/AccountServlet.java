package finance.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/AccountServlet")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AccountServlet() {
        super();
        // TODO Auto-generated constructor stub
        // testing one, two, three...
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("act");
		if (action.equals((String)"Deposit")){
			response.sendRedirect("deposit.jsp");
		}
		if (action.equals((String)"Withdraw")){
			response.sendRedirect("withdraw.jsp");
		}
		if (action.equals((String)"History")){
			response.sendRedirect("history.jsp");
		}
		if (action.equals((String)"Logout")){
			request.getSession().invalidate();
			response.sendRedirect("AccountLogin.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
