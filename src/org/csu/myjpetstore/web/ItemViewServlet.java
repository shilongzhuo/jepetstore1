package org.csu.myjpetstore.web;

import java.applet.Applet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.csu.myjpetstore.domain.Account;
import org.csu.myjpetstore.domain.Item;
import org.csu.myjpetstore.domain.Product;
import org.csu.myjpetstore.jdbc.CardInfo;
import org.csu.myjpetstore.persistence.DBUtil;
import org.csu.myjpetstore.persistence.impl.Card_infoDAO;
import org.csu.myjpetstore.persistence.impl.RecodeDAO;
import org.csu.myjpetstore.service.CategoryService;

public class ItemViewServlet extends HttpServlet {
	
	private String itemId;
	private String productId;
	private CategoryService categoryService;	
	private Item item;
	private Product product;
	private static final String VIEW_ITEM = "/WEB-INF/jsp/catalog/Item.jsp";


	public ItemViewServlet() {
		super();
		categoryService = new CategoryService();
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			itemId = request.getParameter("itemId");
			productId = findproduct(itemId);
			item = categoryService.getItem(itemId);
			product = categoryService.getProduct(productId);
		} catch (Exception e) {
			e.printStackTrace();
		}


		HttpSession session = request.getSession();
		session.setAttribute("item", item);
		session.setAttribute("product",product);
		Date date = new Date();
		Timestamp timeStamp = new Timestamp(date.getTime());
//		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		System.out.println(date.format(t));
		System.out.println(item.getItemId());
		RecodeDAO recodeDAO = new RecodeDAO();

		if(recodeDAO.selectALL(Account.accountUsername,item.getItemId()))
		{
			int number = recodeDAO.selectNumber(Account.accountUsername,item.getItemId()) + 1;
			recodeDAO.updateUser(Account.accountUsername,item.getItemId(),timeStamp,number);
		}
		else
		{
			recodeDAO.insertUser(Account.accountUsername,item.getItemId(),timeStamp,1);
		}
		request.getRequestDispatcher(VIEW_ITEM).forward(request, response);

	}

	public void init() throws ServletException {
		// Put your code here
	}

	public void destroy() {
		super.destroy(); 
	}

	private String findproduct(String itemId) throws Exception {
		String str = "";
		final String FIND_PRODUCTID_BY_ITEMID = "SELECT * FROM ITEM WHERE itemid = ?";
		Connection connection= DBUtil.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(FIND_PRODUCTID_BY_ITEMID);
		preparedStatement.setString(1, itemId);
		ResultSet resultSet = preparedStatement.executeQuery();
		if(resultSet.next()){
			str = resultSet.getString(2);
		}
		DBUtil.closePreparedStatement(preparedStatement);
		DBUtil.closeConnection(connection);
		return str;
	}
}
