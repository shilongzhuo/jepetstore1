package org.csu.myjpetstore.service;

import java.util.List;

import org.csu.myjpetstore.domain.Category;
import org.csu.myjpetstore.domain.Item;
import org.csu.myjpetstore.domain.Product;
import org.csu.myjpetstore.persistence.CategoryDAO;
import org.csu.myjpetstore.persistence.ItemDAO;
import org.csu.myjpetstore.persistence.ProductDAO;
import org.csu.myjpetstore.persistence.impl.CategoryDAOImpl;
import org.csu.myjpetstore.persistence.impl.ItemDAOImpl;
import org.csu.myjpetstore.persistence.impl.ProductDAOImpl;


public class CategoryService {
	private CategoryDAO categoryDAO;
	private ProductDAO productDAO;
	private ItemDAO itemDAO;

	public CategoryService() {
		categoryDAO = new CategoryDAOImpl();
		productDAO = new ProductDAOImpl();
		itemDAO = new ItemDAOImpl();
	}

	public List<Category> getCategoryList() {
		return categoryDAO.getCategoryList();
	}

	public Category getCategory(String categoryId) {
		return categoryDAO.getCategory(categoryId);
	}

	public Product getProduct(String productId) {
		return productDAO.getProduct(productId);
	}

	public List<Product> getProductListByCategory(String categoryId) {
		return productDAO.getProductListByCategory(categoryId);
	}

	public List<Product> searchProductList(String keyword) {
		return productDAO.searchProductList("%" + keyword.toLowerCase() + "%");
	}
	public List<Item> getItemListByProduct(String productId) {
		return itemDAO.getItemListByProduct(productId);
	}

	public Item getItem(String itemId) {
		return itemDAO.getItem(itemId);
	}

	public boolean isItemInStock(String itemId) {
		return itemDAO.getInventoryQuantity(itemId) > 0;
	}
}
