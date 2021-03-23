package org.csu.myjpetstore.persistence.impl;

import org.csu.myjpetstore.domain.Item;
import org.csu.myjpetstore.domain.Recode;
import org.csu.myjpetstore.persistence.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecodeDAO {
    public static final String INSERT_USER = "INSERT INTO recode(username,itemId,date,number)VALUES(?,?,?,?)";
    public static final String UPDATE_USER = "UPDATE recode SET date=?,number=? WHERE username = ? and itemId = ?";
    public static final String DELETE_USER = "DELETE FROM recode WHERE itemId = ?";

    public int insertUser(String username, String itemId, Timestamp date, int number) {
        int result = 0;//1代表成功添加
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, itemId);
            preparedStatement.setTimestamp(3, date);
            preparedStatement.setInt(4, number);//1管理员，0普通用户
            result = preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public int updateUser(String username, String itemId, Timestamp date, int number) {
        int result = 0;//1代表成功添加
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER);
            preparedStatement.setTimestamp(1, date);
            preparedStatement.setInt(2, number);
            preparedStatement.setString(3, username);
            preparedStatement.setString(4, itemId);
            result = preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean selectALL(String username, String itemId) {
        boolean select = false;
        String SELECT_USERNAME_ITEMID = "SELECT * FROM recode WHERE username = \"" + username + "\" and itemId = \"" + itemId + "\"";
        try {
            Connection connection = DBUtil.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_USERNAME_ITEMID);
            while (resultSet.next()) {
                System.out.println("I have enter");
                select = true;
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(statement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return select;
    }

    public int selectNumber(String username, String itemId) {
        int select = 0;
        String SELECT_USERNAME_ITEMID = "SELECT * FROM recode WHERE username = \"" + username + "\" and itemId = \"" + itemId + "\"";
        try {
            Connection connection = DBUtil.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_USERNAME_ITEMID);
            while (resultSet.next()) {
                select = resultSet.getInt(4);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(statement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return select;
    }

    public List<Recode> selectByUsername(String username)//通过用户名搜索订单列表
    {
        String SELECT_USERNAME = "SELECT * FROM recode WHERE username = \"" + username + "\"";
        List<Recode> recodeList = new ArrayList<Recode>();
        try {
            Connection connection = DBUtil.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_USERNAME);
            while (resultSet.next()) {
                Recode recode = new Recode();
                recode.setUsername(resultSet.getString(1));
                recode.setItemId(resultSet.getString(2));
                recode.setDate(resultSet.getTimestamp(3));
                recode.setNumber(resultSet.getInt(4));
                recodeList.add(recode);
                int x = recodeList.size();
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(statement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recodeList;
    }
    public void delete(String itemId)
    {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER);
            preparedStatement.setString(1,itemId);
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
