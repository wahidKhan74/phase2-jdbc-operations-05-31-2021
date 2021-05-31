package com.mcit.webapp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	public Connection connection;
	
	// create or init connection	
	public DBConnection(String url, String username, String password) throws ClassNotFoundException, SQLException {
		// 1. Register JDBC Driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		// 2. Create a connection
		connection = DriverManager.getConnection(url, username, password);
	}
	
	// get connection
	public Connection getConnection() {
		return connection;
	}
	
	// close connnnnection
	public void closeConnection() throws SQLException {
		if(connection != null) {
			connection.close();
		}		
	}
}
