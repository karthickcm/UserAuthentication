package com.km.authentication.id.generator;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class TokenGenerator implements IdentifierGenerator {
	
	
	/**
	 * Desc : To generate Random UUID for Token Generate
	 */
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		String token = UUID.randomUUID().toString();
		try {
			token = isTokenExists(session, token);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return token; 
	}

	/**
	 * To Check Token is there in user auth details table
	 * @param session
	 * @param token
	 * @return
	 * @throws SQLException
	 */
	private String isTokenExists(SharedSessionContractImplementor session, String token) throws SQLException {
		int count = 0;
		try(PreparedStatement prepareStatement = session.connection().prepareStatement("select count(system_id) from user_auth_details where token = ?")){
			prepareStatement.setObject(1, token);
			try(ResultSet resultSet = prepareStatement.executeQuery()){
				if(resultSet.next()) {
					count = resultSet.getInt(1);
				}
			}
			if(count > 0) {
				token = UUID.randomUUID().toString();
				token = isTokenExists(session, token);
			}
		}
		return token;
	}
}