package org.csu.myjpetstore.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.csu.myjpetstore.domain.Item;
import org.csu.myjpetstore.domain.Product;
import org.csu.myjpetstore.service.CategoryService;

public class ProductViewServlet extends HttpServlet {
	
	private String productId;
	
	private CategoryService categoryService;
	
	private Product product;
	private List<Item>itemList;
	
	private static final String VIEW_PRODUCT = "/WEB-INF/jsp/catalog/Product.jsp";

	public ProductViewServlet() {
		super();
		categoryService = new CategoryService();
		product = new Product();
		itemList = new ArrayList<Item>();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		productId = request.getParameter("productId");
		product = categoryService.getProduct(productId);
		itemList = categoryService.getItemListByProduct(productId);
		
		HttpSession session = request.getSession();
		session.setAttribute("product", product);
		session.setAttribute("itemList", itemList);
		
		request.getRequestDispatcher(VIEW_PRODUCT).forward(request, response);
	}

	public void init() throws ServletException {

	}
	
	public void destroy() {
		super.destroy();
	}
}
