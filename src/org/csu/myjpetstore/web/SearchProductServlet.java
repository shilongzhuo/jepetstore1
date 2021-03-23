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

import org.csu.myjpetstore.domain.Product;
import org.csu.myjpetstore.service.CategoryService;

public class SearchProductServlet extends HttpServlet {

	private String keyword;
	private CategoryService categoryService;
	private List<Product>productList;
	private static final String PRODUCT_SEARCH = "/WEB-INF/jsp/catalog/SearchProducts.jsp";

	public SearchProductServlet() {
		super();
		categoryService = new CategoryService();
		productList = new ArrayList<Product>();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		keyword = request.getParameter("keyword");
		System.out.println("keyword=="+keyword);
		productList = categoryService.searchProductList(keyword);

		HttpSession session = request.getSession();
		session.setAttribute("productList", productList);

		request.getRequestDispatcher(PRODUCT_SEARCH).forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void init() throws ServletException {
		// Put your code here
	}


	public void destroy() {
		super.destroy();
	}


}
