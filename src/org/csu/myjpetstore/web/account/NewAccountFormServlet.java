package org.csu.myjpetstore.web.account;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NewAccountFormServlet extends HttpServlet {

	private static final String VIEW_NEWACCOUNTFORM="/WEB-INF/jsp/account/NewAccountForm.jsp";
	public NewAccountFormServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(VIEW_NEWACCOUNTFORM).forward(request, response);
	}

	public void init() throws ServletException {
		// Put your code here
	}


	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

}
