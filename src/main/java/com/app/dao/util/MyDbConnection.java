package com.app.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDbConnection {
	private static Connection connection;

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		// Driver Load
		Class.forName("com.mysql.cj.jdbc.Driver");

		// Open Connection
		String url = "jdbc:mysql://localhost:3306/online_shopping";
		String username = "root";
		String password = "Keerthana123@";
		connection = DriverManager.getConnection(url, username, password);
		// System.out.println("Connection established");
		return connection;
	}

}
