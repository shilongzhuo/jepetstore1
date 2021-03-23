package org.csu.myjpetstore.web.order;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.csu.myjpetstore.domain.Order;

public class ConfirmShipAddressServlet extends HttpServlet {

	private final static String VIEW_CONFIRMORDER = "/WEB-INF/jsp/order/ConfirmOrder.jsp";
	private Order localOrder;
	
	public ConfirmShipAddressServlet() {
		super();
		localOrder = new Order();
	}

	public void destroy() {
		super.destroy(); 
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		localOrder = (Order) session.getAttribute("order");
		localOrder.setShipToFirstName(request.getParameter("order.shipToFirstName"));
		localOrder.setShipToLastName(request.getParameter("order.shipToLastName"));
		localOrder.setShipAddress1(request.getParameter("order.shipAddress1"));
		localOrder.setShipAddress2(request.getParameter("order.shipAddress2"));
		localOrder.setShipCity(request.getParameter("order.shipCity"));
		localOrder.setShipState(request.getParameter("order.shipState"));
		localOrder.setShipZip(request.getParameter("order.shipZip"));
		localOrder.setShipCountry(request.getParameter("order.shipCountry"));
	
		session.setAttribute("order", localOrder);
		request.getRequestDispatcher(VIEW_CONFIRMORDER).forward(request, response);
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
