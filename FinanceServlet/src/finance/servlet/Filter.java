package finance.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebServlet("/Filter")
public class Filter extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Filter() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String fromYear = request.getParameter("fromYear");
		String toYear = request.getParameter("toYear");
		String flag = request.getParameter("act");
		HttpSession session = request.getSession();
		if(!fromYear.equals("")&&!toYear.equals("")){
			Integer fYear = Integer.parseInt(fromYear);
			Integer tYear = Integer.parseInt(toYear);
			session.setAttribute("fromYear", fYear);
			session.setAttribute("toYear", tYear);
			session.setAttribute("flag", flag);
			response.sendRedirect("filter.jsp");
		}else{
			session.setAttribute("flag", flag);
			response.sendRedirect("filterNoYear.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
