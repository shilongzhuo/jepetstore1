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
import org.csu.myjpetstore.domain.CartItem;
import org.csu.myjpetstore.domain.Item;
import org.csu.myjpetstore.jdbc.CardInfo;
import org.csu.myjpetstore.persistence.impl.Card_infoDAO;
import org.csu.myjpetstore.service.CategoryService;
import org.csu.myjpetstore.web.account.SignonActionServlet;

public class CartViewServlet extends HttpServlet {

	private static final String VIEW_CART="/WEB-INF/jsp/cart/Cart.jsp";
	private CategoryService categoryService;
	private String itemIdString;
	private Cart cart;
	private CartItem cartItem;
	private Item item;
	public CartViewServlet() {
		super();
		categoryService = new CategoryService();
		cart = new Cart();
	}



	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		itemIdString = request.getParameter("workingItemId");
		String username = request.getParameter("username");
		Card_infoDAO card_infoDAO = new Card_infoDAO();

		System.out.println("##############"+Account.accountUsername);
		System.out.println(itemIdString);

		//更改购物车
		if (Account.cart1.containsItemId(itemIdString)) {
			Account.cart1.incrementQuantityByItemId(itemIdString);
			CardInfo cardInfo = new CardInfo();
			cardInfo.setCardId(itemIdString);
			cardInfo.setUsername(Account.accountUsername);
			cardInfo.setNumber(Account.cart1.getItemNumber(itemIdString));
			card_infoDAO.updateUser(cardInfo);
		}
		else {
			boolean isInStock = categoryService.isItemInStock(itemIdString);
			item = categoryService.getItem(itemIdString);
			Account.cart1.addItem(item, isInStock);
			CardInfo cardInfo = new CardInfo();
			cardInfo.setCardId(itemIdString);
			cardInfo.setUsername(Account.accountUsername);
			cardInfo.setNumber(1);
			card_infoDAO.insertUser(cardInfo);
		}
//		if (cart.containsItemId(itemIdString)) {
//		      cart.incrementQuantityByItemId(itemIdString);
//		      System.out.print("8888888888不该调用");
//		    }
//		else {
//		      boolean isInStock = categoryService.isItemInStock(itemIdString);
//		      item = categoryService.getItem(itemIdString);
//		      cart.addItem(item, isInStock);
//		      System.out.print("*****"+item);
//		    }


		HttpSession session = request.getSession();
		session.setAttribute("cart", Account.cart1);
		request.getRequestDispatcher(VIEW_CART).forward(request, response);
	}

	public void init() throws ServletException {
		
	}
	
	public void destroy() {
		super.destroy(); 
	}

}
