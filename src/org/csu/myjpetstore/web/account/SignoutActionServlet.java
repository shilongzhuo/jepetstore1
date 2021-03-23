package org.csu.myjpetstore.web.account;

import org.csu.myjpetstore.domain.Account;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignoutActionServlet extends HttpServlet {

	private static final String MAIN = "/WEB-INF/jsp/catalog/Main.jsp";
	public SignoutActionServlet() {
		super();
	}



	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		session.removeAttribute("accountBean");
		session.removeAttribute("myList");
		session.removeAttribute("authenticated");
		Account.control = false;
		request.getRequestDispatcher(MAIN).forward(request, response);
	}

	public void init() throws ServletException {
		// Put your code here
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
}
