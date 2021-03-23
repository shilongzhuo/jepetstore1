package org.csu.myjpetstore.web.cart;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.csu.myjpetstore.domain.Account;
import org.csu.myjpetstore.domain.Cart;
import org.csu.myjpetstore.domain.Order;
import org.csu.myjpetstore.service.OrderService;

public class CheckoutActionServlet extends HttpServlet {
	
	
	private static final String SIGNON = "/WEB-INF/jsp/account/SignonForm.jsp";
	private static final String VIEW_NewOrderForm = "/WEB-INF/jsp/order/NewOrderForm.jsp";
	private Account account;
	private Cart localCart;
	public static Order order;
	private OrderService orderService;
	
	
	public CheckoutActionServlet() {
		super();
		account = new Account();
		orderService = new OrderService();
		localCart = new Cart();
		order = new Order();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		account = (Account)session.getAttribute("accountBean");
		//System.out.print(account.getEmail());
		//System.out.print(account.getFirstName());
		localCart = (Cart) session.getAttribute("cart");
		if (account == null) {
			request.getRequestDispatcher(SIGNON).forward(request, response);
		}else{
			order.initOrder(account, localCart);
			order.getBillToFirstName();
			session.setAttribute("order", order);
			request.getRequestDispatcher(VIEW_NewOrderForm).forward(request, response);
		}
	}

	public void init() throws ServletException {
		
	}
	

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

}
