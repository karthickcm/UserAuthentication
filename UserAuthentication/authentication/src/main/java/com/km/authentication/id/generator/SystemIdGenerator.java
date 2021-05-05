package com.km.authentication.id.generator;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class SystemIdGenerator implements IdentifierGenerator {
	
	/**
	 * @Desc To Generate unique id for Every User with Date time
	 */
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String systemId = simpleDateFormat.format(new Date());
		try {
			systemId = checkSystemId(session, simpleDateFormat, systemId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return systemId; 
	}

	/**
	 * @param session
	 * @param simpleDateFormat
	 * @param systemId
	 * @return
	 * @throws SQLException
	 */
	private String checkSystemId(SharedSessionContractImplementor session, SimpleDateFormat simpleDateFormat,
			String systemId) throws SQLException {
		int count = 0;
		try(PreparedStatement prepareStatement = session.connection().prepareStatement("select count(system_id) from users_details_master where system_id = ?")){
			prepareStatement.setObject(1, systemId);
			try(ResultSet resultSet = prepareStatement.executeQuery()) {
				if(resultSet.next()) {
					count = resultSet.getInt(1);
				}
			}
			
			if(count > 0) {
				systemId = simpleDateFormat.format(new Date()); // Generate new system Id 
				systemId = checkSystemId(session, simpleDateFormat, systemId);
			}
		}
		return systemId;
	}
}