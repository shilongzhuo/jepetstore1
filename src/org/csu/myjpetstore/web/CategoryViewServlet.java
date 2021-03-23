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

import org.csu.myjpetstore.domain.Category;
import org.csu.myjpetstore.domain.Product;
import org.csu.myjpetstore.service.CategoryService;

public class CategoryViewServlet extends HttpServlet {

	private String categoryId;
	
	private CategoryService categoryService;
	
	private Category category;
	private List<Product> productList;
	
	private static final String VIEW_CATEGORY = "/WEB-INF/jsp/catalog/Category.jsp";
	
	public CategoryViewServlet() {
		super();
		category = new Category();
		productList = new ArrayList<Product>();
		categoryService = new CategoryService();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		categoryId = request.getParameter("categoryId");
		
		category = categoryService.getCategory(categoryId);
		productList = categoryService.getProductListByCategory(categoryId);
		
		HttpSession session = request.getSession();
		session.setAttribute("category", category);
		session.setAttribute("productList", productList);
		
		request.getRequestDispatcher(VIEW_CATEGORY).forward(request, response);
	}

	public void init() throws ServletException {

	}
	
	public void destroy() {
		super.destroy(); 
	}
}
