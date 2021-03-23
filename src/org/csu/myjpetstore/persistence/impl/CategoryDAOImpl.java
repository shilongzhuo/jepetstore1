package org.csu.myjpetstore.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.csu.myjpetstore.domain.Category;
import org.csu.myjpetstore.persistence.CategoryDAO;
import org.csu.myjpetstore.persistence.DBUtil;


public class CategoryDAOImpl implements CategoryDAO {

	@Override
	public List<Category> getCategoryList() {
		List<Category> result = new ArrayList<Category> ();
		try {
			Connection conn = DBUtil.getConnection();
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(getCategoryListSQL);
			
			while(resultSet.next())
			{
				Category category = new Category();
				category.setCategoryId(resultSet.getString(1));
				category.setName(resultSet.getString(2));
				category.setDescription(resultSet.getString(3));
				result.add(category);
			}
			DBUtil.closeResultSet(resultSet);
			DBUtil.closeStatement(statement);
			DBUtil.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Category getCategory(String categoryId) {
		Category category = null;
		try {
			Connection conn = DBUtil.getConnection();
			PreparedStatement pStatement = conn.prepareStatement(getCategorySQL);
			pStatement.setString(1, categoryId);
			ResultSet resultSet = pStatement.executeQuery();
			if(resultSet.next())
			{
				category = new Category();
				category.setCategoryId(resultSet.getString(1));
				category.setName(resultSet.getString(2));
				category.setDescription(resultSet.getString(3));
			}
			DBUtil.closeResultSet(resultSet);
			DBUtil.closePreparedStatement(pStatement);
			DBUtil.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return category;
	}
	
}
