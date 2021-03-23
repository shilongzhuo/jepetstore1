package org.csu.myjpetstore.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.lang.StringUtils;
import org.csu.myjpetstore.domain.Account;
import org.csu.myjpetstore.persistence.AccountDAO;
import org.csu.myjpetstore.persistence.DBUtil;
import org.csu.myjpetstore.persistence.MailUtils;
import org.csu.myjpetstore.persistence.SHAEncryptUtil;

import javax.swing.plaf.nimbus.State;

public class AccountDAOImpl implements AccountDAO {


	/**
	 * 校验激活码
	 */
	@Override
	public void validateActiveCode(Account account, String code) throws Exception {
		// 验证用户是否存在
//		System.out.println("code=="+code);
//		System.out.println("condition=="+account.getCondition());
//		System.out.println("1");
		if (StringUtils.isEmpty(code)) {
//			System.out.println("激活码为空！");
			throw new Exception("激活码为空！");
		}
		if (account != null) {
			// 验证用户是否已经激活
//			System.out.println("2");
			if (account.getCondition().equals("0")) {
				// 没有激活
//				Long currentTime = new Date().getTime();
				if (true) {
					// 未超过48小时
					// 验证激活码是否正确
					if (SHAEncryptUtil.validateCode(account.getEmail(), code)) {
						// 正确
						account.setCondition("1"); // 1.已经激活
						account.setActcode(null);
//						System.out.println("3");
						updateAccount(account);
					} else {
						throw new Exception("激活码不正确！");
					}
				} else {
					throw new Exception("激活码已过期！");
				}
			} else {
				throw new Exception("邮箱已激活！请直接登录");
			}
		} else {
			// 根据激活码没有查询到该用户，页面跳转
			throw new Exception("该邮箱未注册（邮箱地址不存在）！");
		}

	}

	@Override
	public Account getAccountByEmail(String email_demo) throws Exception {
		Account account = null;
		Connection connection= DBUtil.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(getAccountByEmail);
		preparedStatement.setString(1, email_demo);
		System.out.println("email_demo=="+ email_demo);

		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			account = new Account();
			account.setUsername(resultSet.getString(1));
			account.setEmail(resultSet.getString(2));
			account.setFirstName(resultSet.getString(3));
			account.setLastName(resultSet.getString(4));
			account.setStatus(resultSet.getString(5));
			account.setAddress1(resultSet.getString(6));
			account.setAddress2(resultSet.getString(7));
			account.setCity(resultSet.getString(8));
			account.setState(resultSet.getString(9));
			account.setZip(resultSet.getString(10));
			account.setCountry(resultSet.getString(11));
			account.setPhone(resultSet.getString(12));
			account.setActcode(resultSet.getString(13));
			account.setCondition(resultSet.getString(14));
			/*account.setListOption(resultSet.getBoolean(15));
			account.setBannerOption(resultSet.getBoolean(16));
			account.setBannerName(resultSet.getString(17));*/
		}

		DBUtil.closeResultSet(resultSet);
		DBUtil.closePreparedStatement(preparedStatement);
		DBUtil.closeConnection(connection);

		return account;
	}

	@Override
	public Account getAccountByUsername(String username) {
		Account account = null;		
		try {
			Connection connection= DBUtil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(getAccountByUsernameString);
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				account = new Account();
				account.setUsername(resultSet.getString(1));
				account.setEmail(resultSet.getString(2));
				account.setFirstName(resultSet.getString(3));
				account.setLastName(resultSet.getString(4));
				account.setStatus(resultSet.getString(5));
				account.setAddress1(resultSet.getString(6));
				account.setAddress2(resultSet.getString(7));
				account.setCity(resultSet.getString(8));
				account.setState(resultSet.getString(9));
				account.setZip(resultSet.getString(10));
				account.setCountry(resultSet.getString(11));
				account.setPhone(resultSet.getString(12));
				account.setLanguagePreference(resultSet.getString(13));
				account.setFavouriteCategoryId(resultSet.getString(14));
				account.setListOption(resultSet.getBoolean(15));
				account.setBannerOption(resultSet.getBoolean(16));
				account.setBannerName(resultSet.getString(17));
			}
			
			DBUtil.closeResultSet(resultSet);
			DBUtil.closePreparedStatement(preparedStatement);
			DBUtil.closeConnection(connection);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return account;
	}

	@Override
	public Account getAccountByUsernameAndPassword(Account account) {
		// TODO Auto-generated method stub
		Account account2 = null;

		try {
			Connection connection = DBUtil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(getAccountByUsernameAndPasswordString);
			preparedStatement.setString(1, account.getUsername());
			preparedStatement.setString(2, account.getPassword());
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				account2 = new Account();
				account2.setUsername(resultSet.getString(1));
				account2.setEmail(resultSet.getString(2));
				account2.setFirstName(resultSet.getString(3));
				account2.setLastName(resultSet.getString(4));
				account2.setStatus(resultSet.getString(5));
				account2.setAddress1(resultSet.getString(6));
				account2.setAddress2(resultSet.getString(7));
				account2.setCity(resultSet.getString(8));
				account2.setState(resultSet.getString(9));
				account2.setZip(resultSet.getString(10));
				account2.setCountry(resultSet.getString(11));
				account2.setPhone(resultSet.getString(12));
				account2.setLanguagePreference(resultSet.getString(13));
				account2.setFavouriteCategoryId(resultSet.getString(14));
				account2.setListOption(resultSet.getBoolean(15));
				account2.setBannerOption(resultSet.getBoolean(16));
				account2.setBannerName(resultSet.getString(17));
				account2.setActcode(resultSet.getString(18));
				account2.setCondition(resultSet.getString(19));
			}
			
			DBUtil.closeResultSet(resultSet);
			DBUtil.closePreparedStatement(preparedStatement);
			DBUtil.closeConnection(connection);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return account2;
	}

	@Override
	public void insertAccount(Account account) {
		try {
			Connection connection = DBUtil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(insertAccountString);
//			PreparedStatement preparedStatement1 = connection.prepareStatement(insertAccountDemo);
//			preparedStatement1.setInt(1,account.getCondition());
			String s1 = account.getActcode();
			String s2 = account.getActcode();
//			Date s3 = (java.sql.Date) account.getRegisterTime();
//			preparedStatement1.setString(1,account.getActcode());
//			preparedStatement1.setInt(2,account.getCondition());
//			preparedStatement1.setDate(3, (java.sql.Date) account.getRegisterTime());
			System.out.println("##############"+account.getActcode());
			System.out.println("##############"+account.getCondition());
//			System.out.println("##############"+(java.sql.Date) account.getRegisterTime());
//			String insertAccountDemo = "INSERT INTO ACCOUNT VALUES (acc)";
//			Statement statement = connection.createStatement();
//			QueryRunner queryRunner = DBUtil.getQueryRunner();

			preparedStatement.setString(1, account.getEmail());
			preparedStatement.setString(2, account.getFirstName());
			preparedStatement.setString(3, account.getLastName());
			preparedStatement.setString(4, account.getStatus());
			preparedStatement.setString(5, account.getAddress1());
			preparedStatement.setString(6, account.getAddress2());
			preparedStatement.setString(7, account.getCity());
			preparedStatement.setString(8, account.getState());
			preparedStatement.setString(9, account.getZip());
			preparedStatement.setString(10, account.getCountry());
			preparedStatement.setString(11, account.getPhone());
			preparedStatement.setString(12, account.getUsername());
			preparedStatement.setString(13, account.getActcode());
			preparedStatement.setString(14, account.getCondition());
//			preparedStatement.setDate(15, (java.sql.Date) account.getRegisterTime());
//			preparedStatement.setString(13, account.getActcode());
//			preparedStatement.setInt(14, account.getCondition());
//			preparedStatement.setString(15, account.getRegisterTime().toString());
			preparedStatement.executeUpdate();
			System.out.println("Hello insertAccount");
//			preparedStatement1.executeUpdate();



			DBUtil.closePreparedStatement(preparedStatement);
//			DBUtil.closePreparedStatement(preparedStatement1);
			DBUtil.closeConnection(connection);
		} catch (Exception e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void insertProfile(Account account) {
		// TODO Auto-generated method stub
		try {
			Connection connection = DBUtil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(insertProfileString);
			preparedStatement.setString(1, account.getLanguagePreference());
			preparedStatement.setString(2, account.getFavouriteCategoryId());
			preparedStatement.setString(3, account.getUsername());
			preparedStatement.executeUpdate();
			System.out.println("Hello insertProfile");
			DBUtil.closePreparedStatement(preparedStatement);
			DBUtil.closeConnection(connection);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void insertSignon(Account account) {
		// TODO Auto-generated method stub
		try {
			Connection connection = DBUtil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(insertSignonString);
			preparedStatement.setString(1, account.getPassword());
			preparedStatement.setString(2, account.getUsername());
			preparedStatement.executeUpdate();
			System.out.println("Hello insertSignon");
			DBUtil.closePreparedStatement(preparedStatement);
			DBUtil.closeConnection(connection);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateAccount(Account account) {
		// TODO Auto-generated method stub
		try {
			System.out.println("condition=="+account.getCondition());
			System.out.println("4");
			Connection connection = DBUtil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(updateAccountString);
			preparedStatement.setString(1, account.getEmail());
			preparedStatement.setString(2, account.getFirstName());
			preparedStatement.setString(3, account.getLastName());
			preparedStatement.setString(4, account.getStatus());
			preparedStatement.setString(5, account.getAddress1());
			preparedStatement.setString(6, account.getAddress2());
			preparedStatement.setString(7, account.getCity());
			preparedStatement.setString(8, account.getState());
			preparedStatement.setString(9, account.getZip());
			preparedStatement.setString(10, account.getCountry());
			preparedStatement.setString(11, account.getPhone());
			preparedStatement.setString(12,account.getActcode());
			preparedStatement.setString(13,account.getCondition());
			preparedStatement.setString(14, account.getUsername());
			preparedStatement.executeUpdate();
			System.out.println("5");
			DBUtil.closePreparedStatement(preparedStatement);
			DBUtil.closeConnection(connection);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateProfile(Account account) {
		// TODO Auto-generated method stub
		try {
			Connection connection = DBUtil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(updateProfileString);
			preparedStatement.setString(1, account.getLanguagePreference());
			preparedStatement.setString(2, account.getFavouriteCategoryId());
			preparedStatement.setString(3, account.getUsername());
			preparedStatement.executeUpdate();
			DBUtil.closePreparedStatement(preparedStatement);
			DBUtil.closeConnection(connection);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateSignon(Account account) {
		// TODO Auto-generated method stub
		try {

			Connection connection = DBUtil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(updateSignonString);
			preparedStatement.setString(1, account.getPassword());
			preparedStatement.setString(2, account.getUsername());
			preparedStatement.executeUpdate();
			DBUtil.closePreparedStatement(preparedStatement);
			DBUtil.closeConnection(connection);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
