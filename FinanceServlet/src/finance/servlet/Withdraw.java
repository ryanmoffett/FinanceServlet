package finance.servlet;

import java.io.IOException;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import finance.Account;

//@WebServlet("/Withdraw")
public class Withdraw extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Withdraw() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String withdraw = request.getParameter("withdraw");
		Double amount = Double.parseDouble(withdraw.replaceAll(",",""));
		Account account = (Account) request.getSession().getAttribute("Account");
		account.writeCheck("insert description", amount);
		request.getSession().setAttribute("Account", account);
		account = (Account) request.getSession().getAttribute("Account");
		response.setContentType("text/xml");
		response.getWriter().println("<responseFromServer>"+account.getFunds()+"</responseFromServer>");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
