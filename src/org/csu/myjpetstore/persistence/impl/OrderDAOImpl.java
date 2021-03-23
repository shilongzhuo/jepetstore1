package org.csu.myjpetstore.persistence.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.csu.myjpetstore.domain.Order;
import org.csu.myjpetstore.persistence.DBUtil;
import org.csu.myjpetstore.persistence.OrderDAO;
import org.csu.myjpetstore.web.cart.CheckoutActionServlet;


public class OrderDAOImpl implements OrderDAO {

	@Override
	public List<Order> getOrdersByUsername(String username) {
		// TODO Auto-generated method stub
		List<Order>orders = new ArrayList<Order>();
		
		try {
			Connection connection = DBUtil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(getOrderByUsernameString);
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				Order order = new Order();
				order.setOrderId(resultSet.getInt(1));
				order.setOrderDate(resultSet.getDate(3));
				order.setShipAddress1(resultSet.getString(4));
				order.setShipAddress2(resultSet.getString(5));
				order.setShipCity(resultSet.getString(6));
				order.setShipState(resultSet.getString(7));
				order.setShipZip(resultSet.getString(8));
				order.setShipCountry(resultSet.getString(9));
				order.setBillAddress1(resultSet.getString(10));
				order.setBillAddress2(resultSet.getString(11));
				order.setBillCity(resultSet.getString(12));
				order.setBillState(resultSet.getString(13));
				order.setBillZip(resultSet.getString(14));
				order.setBillCountry(resultSet.getString(15));
				order.setCourier(resultSet.getString(16));
				order.setTotalPrice(resultSet.getBigDecimal(17));
				order.setBillToFirstName(resultSet.getString(18));
				order.setBillToLastName(resultSet.getString(19));
				order.setShipToFirstName(resultSet.getString(20));
				order.setShipToLastName(resultSet.getString(21));
				order.setCreditCard(resultSet.getString(22));
				orders.add(order);
			}
			DBUtil.closeResultSet(resultSet);
			DBUtil.closePreparedStatement(preparedStatement);
			DBUtil.closeConnection(connection);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return orders;
	}

	@Override
	public Order getOrder(int orderId) {
		// TODO Auto-generated method stub
		Order order = null;
		
		try {
			Connection connection = DBUtil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(getOrderString);
			preparedStatement.setInt(1, orderId);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				order = new Order();
				order.setBillAddress1(resultSet.getString(1));
				order.setBillAddress2(resultSet.getString(2));
				order.setBillCity(resultSet.getString(3));
				order.setBillCountry(resultSet.getString(4));
				order.setBillState(resultSet.getString(5));
				order.setBillToFirstName(resultSet.getString(6));
				order.setBillToLastName(resultSet.getString(7));
				order.setBillZip(resultSet.getString(8));
				order.setShipAddress1(resultSet.getString(9));
				order.setShipAddress2(resultSet.getString(10));
				order.setShipCity(resultSet.getString(11));
				order.setShipCountry(resultSet.getString(12));
				order.setShipState(resultSet.getString(13));
				order.setShipToFirstName(resultSet.getString(14));
				order.setShipToLastName(resultSet.getString(15));
				order.setShipZip(resultSet.getString(16));
				order.setStatus(resultSet.getString(17));
			}
			
			DBUtil.closeResultSet(resultSet);
			DBUtil.closePreparedStatement(preparedStatement);
			DBUtil.closeConnection(connection);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return order;
	}

	@Override
	public void insertOrder(Order order) {
		// TODO Auto-generated method stub
		try {
			Connection connection = DBUtil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(insertOrderString);
			preparedStatement.setInt(1, order.getOrderId());
			preparedStatement.setString(2, order.getUsername());
			preparedStatement.setDate(3, new java.sql.Date(order.getOrderDate().getTime()));
			preparedStatement.setString(4, order.getShipAddress1());
			preparedStatement.setString(5, order.getShipAddress2());
			preparedStatement.setString(6, order.getShipCity());
			preparedStatement.setString(7, order.getShipState());
			preparedStatement.setString(8, order.getShipZip());
			preparedStatement.setString(9, order.getShipCountry());
			preparedStatement.setString(10, order.getBillAddress1());
			preparedStatement.setString(11, order.getBillAddress2());
			preparedStatement.setString(12, order.getBillCity());
			preparedStatement.setString(13, order.getBillState());
			preparedStatement.setString(14, order.getBillZip());
			preparedStatement.setString(15, order.getBillCountry());
			preparedStatement.setString(16, order.getCourier());
			preparedStatement.setBigDecimal(17, order.getTotalPrice());
			preparedStatement.setString(18, order.getBillToFirstName());
			preparedStatement.setString(19, order.getBillToLastName());
			preparedStatement.setString(20, order.getShipToFirstName());
			preparedStatement.setString(21, order.getShipToLastName());
			preparedStatement.setString(22, order.getCreditCard());
			preparedStatement.setString(23, order.getExpiryDate());
			preparedStatement.setString(24, order.getCardType());
			preparedStatement.setString(25, order.getLocale());
			preparedStatement.executeUpdate();
			DBUtil.closePreparedStatement(preparedStatement);
			DBUtil.closeConnection(connection);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void insertOrderStatus(Order order) {
		// TODO Auto-generated method stub
		try {
			Connection connection = DBUtil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(insertOrderStatusString);
			preparedStatement.setInt(1, order.getOrderId());
			preparedStatement.setInt(2, order.getLineItems().size());
			preparedStatement.setDate(3, new java.sql.Date(order.getOrderDate().getTime()));
			preparedStatement.setString(4, order.getStatus());
			preparedStatement.executeUpdate();
			DBUtil.closePreparedStatement(preparedStatement);
			DBUtil.closeConnection(connection);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
