package finance.servlet;

import java.io.IOException;

import java.util.GregorianCalendar;

import finance.Account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet("/Deposit")
public class Deposit extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Deposit() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String deposit = request.getParameter("deposit");
		Double amount = Double.parseDouble(deposit.replaceAll(",",""));
		Account account = (Account) request.getSession().getAttribute("Account");
		account.makeDeposit("insert description", amount);
		request.getSession().setAttribute("Account", account);
		account = (Account) request.getSession().getAttribute("Account");
		response.setContentType("text/xml");
		response.getWriter().println("<responseFromServer>"+account.getFunds()+"</responseFromServer>");
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
