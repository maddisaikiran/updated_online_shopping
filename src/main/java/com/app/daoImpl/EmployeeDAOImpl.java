package com.app.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.app.Main;
import com.app.businessException.BusinessException;
import com.app.dao.EmployeeDAO;
import com.app.dao.util.MyDbConnection;

public class EmployeeDAOImpl implements EmployeeDAO{
	Logger log = Logger.getLogger(Main.class);

	@Override
	public Boolean checkValidCredentials(String username, String password) throws BusinessException {
		boolean login = false;
		try (Connection connection = MyDbConnection.getConnection()) {
			String sql = "select employeeUsername,employeePassword from employee where employeeUsername=? and employeePassword = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				login = true;
			} else {
				throw new BusinessException("Credentials is not matched with our Database!");
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e.getMessage());
			throw new BusinessException("Internal Problem Occured. Contact sysAdmin!");
		}
		return login;
	}

}
