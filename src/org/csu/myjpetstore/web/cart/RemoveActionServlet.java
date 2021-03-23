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
import org.csu.myjpetstore.domain.Item;
import org.csu.myjpetstore.jdbc.CardInfo;
import org.csu.myjpetstore.persistence.impl.Card_infoDAO;

public class RemoveActionServlet extends HttpServlet {

	private static final String VIEW_CART="/WEB-INF/jsp/cart/Cart.jsp"; 
	private String itemIdString;
	private Cart cart1;
	public RemoveActionServlet() {
		super();
	}



	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		itemIdString = request.getParameter("cartItem");
		HttpSession session = request.getSession();
		System.out.println("^_^"+itemIdString);
		CardInfo cardInfo = new CardInfo();
		cardInfo.setCardId(itemIdString);
		cardInfo.setUsername(Account.accountUsername);
		Card_infoDAO card_infoDAO = new Card_infoDAO();
		card_infoDAO.deleteUser(cardInfo);
		cart1 = (Cart)session.getAttribute("cart");
		cart1.removeItemById(itemIdString);
		session.setAttribute("cart", cart1);
		request.getRequestDispatcher(VIEW_CART).forward(request, response);
	}

	public void init() throws ServletException {
		
	}
	
	public void destroy() {
		super.destroy(); 
	}

}
