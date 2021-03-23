package org.csu.myjpetstore.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBUtil {
	private static String driverString = "com.mysql.jdbc.Driver";
	private static String connectionString =
			"jdbc:mysql:///demo";
	private static String username = "root";
	private static String password = "1234";
	public static Connection getConnection() throws Exception {
		Connection connection = null;
		try {
			Class.forName(driverString);
			connection = DriverManager.getConnection(connectionString,username,password);
		} catch (Exception e) {
			throw e;
		}
		return connection;
	}
	public static void closeStatement(Statement statement) throws Exception {
		statement.close();
	}
	public static void closePreparedStatement(PreparedStatement pStatement)
			throws Exception {
		pStatement.close();
	}
	public static void closeResultSet(ResultSet resultSet) throws Exception {
		resultSet.close();
	}
	public static void closeConnection(Connection connection) throws Exception {
		connection.close();
	}
	public static void main(String[] args) {
		try {
			Connection connection = getConnection();
			System.out.print(connection);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
