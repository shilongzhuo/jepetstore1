package org.csu.myjpetstore.persistence.impl;

import org.csu.myjpetstore.domain.Account;
import org.csu.myjpetstore.jdbc.CardInfo;
import org.csu.myjpetstore.persistence.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Card_infoDAO {
    public static final String INSERT_USER = "INSERT INTO cart_information(username,cardId,number)VALUES(?,?,?)";
    public static final String SEARCH_CARDID ="SELECT * FROM cart_information WHERE username = \"" + Account.accountUsername + "\"";
    public static final String DELETE_USER = "DELETE FROM cart_information WHERE username = ? and cardId = ?";
    public static final String UPDATE_CARD = "UPDATE cart_information SET username = ?,cardId = ?,number=? WHERE cardId =?";
    public int updateUser(CardInfo cardInfo)
    {
        int result = 0;//1代表成功添加
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CARD);
            System.out.println("cardInfo.getUsername()=="+cardInfo.getUsername());
            preparedStatement.setString(1,cardInfo.getUsername());
            preparedStatement.setString(2,cardInfo.getCardId());
            preparedStatement.setInt(3,cardInfo.getNumber());
            preparedStatement.setString(4,cardInfo.getCardId());
            result = preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public int insertUser(CardInfo cardInfo)
    {
        int result = 0;//1代表成功添加
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);
            System.out.println("cardInfo.getUsername()=="+cardInfo.getUsername());
            preparedStatement.setString(1,cardInfo.getUsername());
            preparedStatement.setString(2,cardInfo.getCardId());
            preparedStatement.setInt(3,cardInfo.getNumber());
            result = preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public List<CardInfo> selectALL()
    {
        List<CardInfo> userList = new ArrayList<>();
        try{
            Connection connection = DBUtil.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SEARCH_CARDID);
            while(resultSet.next())
            {
                CardInfo cardInfo = new CardInfo();
                cardInfo.setUsername(resultSet.getString(1));
                cardInfo.setCardId(resultSet.getString(2));
                cardInfo.setNumber(resultSet.getInt(3));
                userList.add(cardInfo);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(statement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return userList;
    }

    public int deleteUser(CardInfo cardInfo)
    {
        int result = 0;//1代表成功添加
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER);
            preparedStatement.setString(1,cardInfo.getUsername());
            preparedStatement.setString(2,cardInfo.getCardId());
            result = preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
