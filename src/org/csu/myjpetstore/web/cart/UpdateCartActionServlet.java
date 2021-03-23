package org.csu.myjpetstore.web.cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.csu.myjpetstore.domain.Account;
import org.csu.myjpetstore.domain.Cart;
import org.csu.myjpetstore.domain.CartItem;
import org.csu.myjpetstore.domain.Item;
import org.csu.myjpetstore.jdbc.CardInfo;
import org.csu.myjpetstore.persistence.impl.Card_infoDAO;

public class UpdateCartActionServlet extends HttpServlet {

	private static final String VIEW_CART="/WEB-INF/jsp/cart/Cart.jsp";
	private Cart localCart;
	private String itemIdString;
	private CartItem cartItem;
	private int localQuatity;
	
	public UpdateCartActionServlet() {
		super();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
//		localCart = (Cart)session.getAttribute("cart");
//		System.out.println(session.getAttribute("cart")+"I love");
//		int[] x = new int[len];
//		System.out.println(request.getParameter("num"));
//		List<Integer> num = new ArrayList<>();
//		num = (List<Integer>) session.getAttribute("num");
//		for(int i = 0; i < localCart.getCartItemList().size();i++){
//			cartItem = localCart.getCartItemList().get(i);
//			itemIdString = cartItem.getItem().getItemId();
//     		localQuatity = Integer.parseInt((String)request.getParameter(itemIdString));
//
//			cartItem.setQuantity(localQuatity);
//			//插入数据库
//			Card_infoDAO card_infoDAO = new Card_infoDAO();
//			CardInfo cardInfo = new CardInfo();
//			cardInfo.setCardId(itemIdString);
//			cardInfo.setUsername(Account.accountUsername);
//			cardInfo.setNumber(localQuatity);
//			card_infoDAO.updateUser(cardInfo);
//		}
		String itemId = (String) request.getParameter("ID");
		try {
			int quantity = Integer.parseInt((String) request.getParameter("quantity"));
			Account.cart1.setQuantityByItemId(itemId, quantity);
			if (quantity < 1) {
				Account.cart1.removeItemById(itemId);
			}
			else
			{
				Card_infoDAO card_infoDAO = new Card_infoDAO();
				CardInfo cardInfo = new CardInfo();
				cardInfo.setCardId(itemId);
				cardInfo.setUsername(Account.accountUsername);
				cardInfo.setNumber(quantity);
				card_infoDAO.updateUser(cardInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.setAttribute("cart", Account.cart1);

		request.getRequestDispatcher(VIEW_CART).forward(request, response);
	}

	public void init() throws ServletException {
		
	}
	
	public void destroy() {
		super.destroy();
	}


}
