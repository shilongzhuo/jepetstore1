package org.csu.myjpetstore.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.csu.myjpetstore.domain.Product;
import org.csu.myjpetstore.persistence.DBUtil;
import org.csu.myjpetstore.persistence.ProductDAO;


public class ProductDAOImpl implements ProductDAO {

	@Override
	public List<Product> getProductListByCategory(String categoryId) {
		List<Product>products = new ArrayList<>();
		
		try {
			Connection connection = DBUtil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(getProductListByCategoryString);
			preparedStatement.setString(1, categoryId);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				Product product = new Product();
				product.setProductId(resultSet.getString(1));
				product.setName(resultSet.getString(2));
				product.setDescription(resultSet.getString(3));
				product.setCategoryId(resultSet.getString(4));
				products.add(product);
			}
			
			DBUtil.closeResultSet(resultSet);
			DBUtil.closePreparedStatement(preparedStatement);
			DBUtil.closeConnection(connection);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return products;
	}

	@Override
	public Product getProduct(String productId) {
		Product product = null;
		
		try {
			Connection connection = DBUtil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(getProductString);
			preparedStatement.setString(1, productId);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				product = new Product();
				product.setProductId(resultSet.getString(1));
				product.setName(resultSet.getString(2));
				product.setDescription(resultSet.getString(3));
				product.setCategoryId(resultSet.getString(4));
			}
			
			DBUtil.closeResultSet(resultSet);
			DBUtil.closePreparedStatement(preparedStatement);
			DBUtil.closeConnection(connection);
		} catch (Exception e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		}
		
		return product;
	}

	@Override
	public List<Product> searchProductList(String keywords) {
		List<Product> products = new ArrayList<Product>();
		
		try {
			Connection connection = DBUtil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(searchProductListString);
			preparedStatement.setString(1, keywords);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				Product product = new Product();
				product.setProductId(resultSet.getString(1));
				product.setName(resultSet.getString(2));
				product.setDescription(resultSet.getString(3));
				product.setCategoryId(resultSet.getString(4));
				products.add(product);
			}
			DBUtil.closeResultSet(resultSet);
			DBUtil.closePreparedStatement(preparedStatement);
			DBUtil.closeConnection(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return products;
	}

}
