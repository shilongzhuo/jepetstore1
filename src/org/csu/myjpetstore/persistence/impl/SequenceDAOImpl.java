package org.csu.myjpetstore.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.csu.myjpetstore.domain.Sequence;
import org.csu.myjpetstore.persistence.DBUtil;
import org.csu.myjpetstore.persistence.SequenceDAO;


public class SequenceDAOImpl implements SequenceDAO {

	@Override
	public Sequence getSequence(Sequence sequence) {
		// TODO Auto-generated method stub
		Sequence sequence2 = null;
		
		try {
			Connection connection = DBUtil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(getSequenceString);
			preparedStatement.setString(1, sequence.getName());
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				sequence2 = new Sequence();
				sequence2.setName(resultSet.getString(1));
				sequence2.setNextId(resultSet.getInt(2));
			}
			DBUtil.closeConnection(connection);
			DBUtil.closePreparedStatement(preparedStatement);
			DBUtil.closeResultSet(resultSet);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sequence2;
	}

	@Override
	public void updateSequence(Sequence sequence) {
		// TODO Auto-generated method stub
		try {
			Connection connection = DBUtil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(updateSequenceString);
			preparedStatement.setInt(1, sequence.getNextId());
			preparedStatement.setString(2, sequence.getName());
			preparedStatement.executeUpdate();
			DBUtil.closePreparedStatement(preparedStatement);
			DBUtil.closeConnection(connection);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
