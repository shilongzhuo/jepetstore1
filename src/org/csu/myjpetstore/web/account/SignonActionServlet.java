package org.csu.myjpetstore.web.account;

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
import org.csu.myjpetstore.domain.Item;
import org.csu.myjpetstore.domain.Product;
import org.csu.myjpetstore.jdbc.CardInfo;
import org.csu.myjpetstore.persistence.impl.Card_infoDAO;
import org.csu.myjpetstore.persistence.impl.OrderDAOImpl;
import org.csu.myjpetstore.service.AccountService;
import org.csu.myjpetstore.service.CategoryService;

public class SignonActionServlet extends HttpServlet {
	
	private static final String MAIN = "/WEB-INF/jsp/catalog/Main.jsp";
	private static final String SIGNON = "/WEB-INF/jsp/account/SignonForm.jsp";
	private AccountService accountService;
	private CategoryService categoryService;
	private Account account = null;
	private List<Product> myList;
	private boolean authenticated;
	private String username;
	private String password;
	private String input_verification_code;
	private String verification_code;
	public static List<Item> items = new ArrayList<>();
	private Item item = new Item();


	
	public SignonActionServlet() {
		super();
		accountService = new AccountService();
		categoryService = new CategoryService();
		account = new Account();
		myList = new ArrayList<Product>();
		authenticated = false;
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
//		System.out.println("I COME HERE !!!");
		username = request.getParameter("username");
		password = request.getParameter("password");
		input_verification_code = request.getParameter("verifycode");
		verification_code = CheckCodeServlet.checkCode;
		//System.out.println("verifycode=="+verification_code);
//		System.out.println(username);
		Account.accountPassword = password;
		Account.accountUsername = username;
		account = accountService.getAccount(username, password);
		System.out.println("password is" + Account.accountPassword);
//		Cart.cartItemList.add();
//		System.out.println(account.getCondition());
		//读入数据库中的购物车信息
		Card_infoDAO card_infoDAO = new Card_infoDAO();
		List<CardInfo> cardInfo = card_infoDAO.selectALL();
		if(Account.control) {
			for (int i = 0; i < cardInfo.size(); i++) {

				if (Account.cart1.containsItemId(cardInfo.get(i).getCardId())) {
					Account.cart1.incrementQuantityByItemId(cardInfo.get(i).getCardId());
				} else {
					boolean isInStock = categoryService.isItemInStock(cardInfo.get(i).getCardId());
					item = categoryService.getItem(cardInfo.get(i).getCardId());
					Account.cart1.addItem(item, isInStock);
					Account.cart1.setQuantityByItemId(cardInfo.get(i).getCardId(),cardInfo.get(i).getNumber());
				}
			}
			Account.control = false;
		}
		//读入数据库中的订单信息
		Account.MyOrders = new OrderDAOImpl().getOrdersByUsername(Account.accountUsername);
//		System.out.println("账单总价"+Account.MyOrders.get(0).getTotalPrice());
//		System.out.println(Account.cart1.getCartItemList().size());

		if(!verification_code.equals(input_verification_code)){
			request.setAttribute("error1", "Invalid input_verification_code.  Signon failed.");
			request.getRequestDispatcher(SIGNON).forward(request, response);
		}else if (account == null) {
		      request.setAttribute("error2", "Invalid username or password.  Signon failed.");
		      request.getRequestDispatcher(SIGNON).forward(request, response);
		    } else if(account.getCondition().equals("0")) {
			request.setAttribute("error3", "Not activated account.  Signon failed.");
			request.getRequestDispatcher(SIGNON).forward(request, response);
		}else {
		      account.setPassword(null);
		      myList = categoryService.getProductListByCategory(account.getFavouriteCategoryId());
		      authenticated = true;

		      HttpSession s = request.getSession();
		      s.setAttribute("accountBean", account);
		      s.setAttribute("myList", myList);
		      s.setAttribute("authenticated", authenticated);
		      request.getRequestDispatcher(MAIN).forward(request, response);

		    }
	}

	public void init() throws ServletException {
		
	}

}
