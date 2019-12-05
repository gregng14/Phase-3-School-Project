package com.cgi.web.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
	private static final String DATABASE_DRIVER="com.mysql.jdbc.Driver";
	private static final String DATABASE_LOCATION="jdbc:mysql://localhost/3306/travelexperts";
	private static final String USERNAME="root";
	private static final String PASSWORD="";
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		
		Class.forName(DATABASE_DRIVER);
		Connection conn = DriverManager.getConnection(DATABASE_LOCATION, USERNAME, PASSWORD);
		
		return conn;
		
	}

}
