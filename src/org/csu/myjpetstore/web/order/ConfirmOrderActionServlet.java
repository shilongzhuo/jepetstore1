package org.csu.myjpetstore.web.order;

import org.csu.myjpetstore.domain.Account;
import org.csu.myjpetstore.domain.Cart;
import org.csu.myjpetstore.domain.Order;
import org.csu.myjpetstore.persistence.impl.OrderDAOImpl;
import org.csu.myjpetstore.service.OrderService;
import org.csu.myjpetstore.web.cart.CheckoutActionServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ConfirmOrderActionServlet extends HttpServlet {

	private final static String VIEW_ORDER = "/WEB-INF/jsp/order/ViewOrder.jsp";
	public ConfirmOrderActionServlet() {
		super();
	}

	private Account account;
	private Cart localCart;
	public static Order order;
	private OrderService orderService;
	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		account = new Account();
		orderService = new OrderService();
		localCart = new Cart();
		order = new Order();
		HttpSession session = request.getSession();
		account = (Account)session.getAttribute("accountBean");
		//System.out.print(account.getEmail());
		//System.out.print(account.getFirstName());
		localCart = (Cart) session.getAttribute("cart");
		System.out.println(order+"MMMM");
		System.out.println(account+"????");
		order.initOrder(account, localCart);
		order.getBillToFirstName();
		session.setAttribute("order", order);
		OrderDAOImpl orderDAOImpl = new OrderDAOImpl();
		System.out.println(new java.sql.Date(order.getOrderDate().getTime()));
		orderDAOImpl.insertOrder(order);
		orderDAOImpl.insertOrderStatus(order);
		Account.MyOrders = new OrderDAOImpl().getOrdersByUsername(Account.accountUsername);
		System.out.println(order.getOrderId());
		request.getRequestDispatcher(VIEW_ORDER).forward(request, response);

	}

	public void init() throws ServletException {
		
	}

}
