package org.csu.myjpetstore.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.csu.myjpetstore.domain.LineItem;
import org.csu.myjpetstore.persistence.DBUtil;
import org.csu.myjpetstore.persistence.LineItemDAO;


public class LineItemDAOImpl implements LineItemDAO {

	@Override
	public List<LineItem> getLineItemsByOrderId(int orderId) {
		// TODO Auto-generated method stub
		List<LineItem>lineItems = new ArrayList<LineItem>();
		
		try {
			Connection connection = DBUtil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(getLineItemsByOrderIdString);
			preparedStatement.setInt(1, orderId);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				LineItem lineItem = new LineItem();
				lineItem.setOrderId(resultSet.getInt(1));
				lineItem.setLineNumber(resultSet.getInt(2));
				lineItem.setItemId(resultSet.getString(3));
				lineItem.setQuantity(resultSet.getInt(4));
				lineItem.setUnitPrice(resultSet.getBigDecimal(5));
				lineItems.add(lineItem);
			}
			
			DBUtil.closeResultSet(resultSet);
			DBUtil.closePreparedStatement(preparedStatement);
			DBUtil.closeConnection(connection);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return lineItems;
	}

	@Override
	public void insertLineItem(LineItem lineItem) {
		// TODO Auto-generated method stub
		try {
			Connection connection = DBUtil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(insertLineItemString);
			preparedStatement.setInt(1, lineItem.getOrderId());
			preparedStatement.setInt(2, lineItem.getLineNumber());
			preparedStatement.setString(3, lineItem.getItemId());
			preparedStatement.setInt(4, lineItem.getQuantity());
			preparedStatement.setBigDecimal(5, lineItem.getUnitPrice());
			preparedStatement.executeUpdate();
			DBUtil.closePreparedStatement(preparedStatement);
			DBUtil.closeConnection(connection);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
