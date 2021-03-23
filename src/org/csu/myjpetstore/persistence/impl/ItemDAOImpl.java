package org.csu.myjpetstore.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.csu.myjpetstore.domain.Item;
import org.csu.myjpetstore.domain.Product;
import org.csu.myjpetstore.persistence.DBUtil;
import org.csu.myjpetstore.persistence.ItemDAO;


public class ItemDAOImpl implements ItemDAO {

	@Override
	public void updateInventoryQuantity(Map<String, Object> param) {
		
		try {
			Connection connection = DBUtil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(updateInventoryQuantityString);
			String itemId = param.keySet().iterator().next();
			Integer increment = (Integer)param.get(itemId);
			preparedStatement.setInt(1, increment.intValue());
			preparedStatement.setString(2, itemId);
			preparedStatement.executeUpdate();
			DBUtil.closePreparedStatement(preparedStatement);
			DBUtil.closeConnection(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getInventoryQuantity(String itemId) {
		int quantity = -1;
		
		try {
			Connection connection = DBUtil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(getInventoryQuantityString);
			preparedStatement.setString(1, itemId);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				quantity = resultSet.getInt(1);
			}
			
			DBUtil.closeResultSet(resultSet);
			DBUtil.closePreparedStatement(preparedStatement);
			DBUtil.closeConnection(connection);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return quantity;
	}

	@Override
	public List<Item> getItemListByProduct(String productId) {
		List<Item>itemList = new ArrayList<Item>();
		
		try {
			Connection connection = DBUtil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(getItemListByProductString);
			preparedStatement.setString(1, productId);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				Item item = new Item();
				item.setItemId(resultSet.getString(1));
				item.setListPrice(resultSet.getBigDecimal(2));
				item.setUnitCost(resultSet.getBigDecimal(3));
				item.setSupplierId(resultSet.getInt(4));
				item.setProductId(resultSet.getString(5));
				Product product = new Product();
				product.setProductId(resultSet.getString(5));
				product.setName(resultSet.getString(6));
				product.setDescription(resultSet.getString(7));
				product.setCategoryId(resultSet.getString(8));
				item.setProduct(product);
				item.setAttribute1(resultSet.getString(9));
				item.setAttribute1(resultSet.getString(10));
				item.setAttribute1(resultSet.getString(11));
				item.setAttribute1(resultSet.getString(12));
				item.setAttribute1(resultSet.getString(13));
				itemList.add(item);
				}
			DBUtil.closeResultSet(resultSet);
			DBUtil.closePreparedStatement(preparedStatement);
			DBUtil.closeConnection(connection);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return itemList;
	}

	@Override
	public Item getItem(String itemId) {
		Item item = null;
		
		try {
			Connection connection = DBUtil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(getItemString);
			preparedStatement.setString(1, itemId);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				item = new Item();
				item.setItemId(resultSet.getString(1));
				item.setListPrice(resultSet.getBigDecimal(2));
				item.setUnitCost(resultSet.getBigDecimal(3));
				item.setSupplierId(resultSet.getInt(4));
				item.setProductId(resultSet.getString(5));
				Product product = new Product();
				product.setProductId(resultSet.getString(5));
				product.setName(resultSet.getString(6));
				product.setDescription(resultSet.getString(7));
				product.setCategoryId(resultSet.getString(8));
				item.setStatus(resultSet.getString(9));
				item.setAttribute1(resultSet.getString(10));
				item.setAttribute2(resultSet.getString(11));
				item.setAttribute3(resultSet.getString(12));
				item.setAttribute4(resultSet.getString(13));
				item.setAttribute5(resultSet.getString(14));
				item.setQuantity(resultSet.getInt(15));
			}
			DBUtil.closeResultSet(resultSet);
			DBUtil.closePreparedStatement(preparedStatement);
			DBUtil.closeConnection(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return item;
	}
	
}
