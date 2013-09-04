package finance.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/TransactionOptions")
public class TransactionOptions extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public TransactionOptions() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("act");
		if (action.equals((String)"MoreTransactions")){
			response.sendRedirect("Account.jsp");
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
