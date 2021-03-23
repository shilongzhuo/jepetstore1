package org.csu.myjpetstore.web.account;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.csu.myjpetstore.domain.Account;
import org.csu.myjpetstore.domain.Order;
import org.csu.myjpetstore.service.OrderService;

public class SignonFormServlet extends HttpServlet {

	private static final String SIGNON = "/WEB-INF/jsp/account/SignonForm.jsp";

	public SignonFormServlet() {
		super();

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			request.getRequestDispatcher(SIGNON).forward(request, response);
	}

	public void init() throws ServletException {
		// Put your code here
	}
	

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}


}
