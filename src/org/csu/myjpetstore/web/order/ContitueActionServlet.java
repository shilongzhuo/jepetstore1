package org.csu.myjpetstore.web.order;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.csu.myjpetstore.domain.Order;
import org.csu.myjpetstore.persistence.impl.OrderDAOImpl;

public class ContitueActionServlet extends HttpServlet {

	private final static String VIEW_CONFIRMORDER = "/WEB-INF/jsp/order/ConfirmOrder.jsp";
	private final static String VIEW_SHIPPINGFORM = "/WEB-INF/jsp/order/ShippingForm.jsp";
	private Order localOrder;
	public ContitueActionServlet() {
		super();
		localOrder = new Order();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		localOrder = (Order) session.getAttribute("order");
		localOrder.setCardType(request.getParameter("order.cardType"));
		//System.out.print(localOrder.getCardType());
		localOrder.setCreditCard(request.getParameter("order.creditCard"));
		localOrder.setExpiryDate(request.getParameter("order.expiryDate"));
		localOrder.setBillToFirstName(request.getParameter("order.billToFirstName"));
		localOrder.setBillToLastName(request.getParameter("order.billToLastName"));
		localOrder.setBillAddress1(request.getParameter("order.billAddress1"));
		localOrder.setBillAddress2(request.getParameter("order.billAddress2"));
		localOrder.setBillCity(request.getParameter("order.billCity"));
		localOrder.setBillState(request.getParameter("order.billState"));
		localOrder.setBillZip(request.getParameter("order.billZip"));
		localOrder.setBillCountry(request.getParameter("order.billCountry"));
		session.setAttribute("order", localOrder);

		if (request.getParameter("shippingAddressRequired") == null) {
			request.getRequestDispatcher(VIEW_CONFIRMORDER).forward(request, response);
		}else{
			request.getRequestDispatcher(VIEW_SHIPPINGFORM).forward(request, response);
		}
		
	}

	public void init() throws ServletException {
		
	}

	public void destroy() {
		super.destroy(); 
	}

}
