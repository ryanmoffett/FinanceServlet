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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String deposit = request.getParameter("deposit");
		Double amount = Double.parseDouble(deposit.replaceAll(",",""));
		Account account = (Account) request.getSession().getAttribute("Account");
		GregorianCalendar date = new GregorianCalendar();
		account.makeDeposit("Deposit", amount, date.get(GregorianCalendar.YEAR), date.get(GregorianCalendar.MONTH), date.get(GregorianCalendar.DAY_OF_MONTH));
		request.getSession().setAttribute("Account", account);
		response.sendRedirect("transactionComplete.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
