/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cgi.manager.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Greg
 */
public class ConnectionDB {
    private static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE_LOCATION = "jdbc:mysql://verniy:3306/TravelExperts"; 
    private static final String USERNAME = "notyou";
    private static final String PASSWORD = "notyou";
    
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        
        Class.forName(DATABASE_DRIVER);
        Connection conn = DriverManager.getConnection(DATABASE_LOCATION,USERNAME,PASSWORD);
        
        return conn;
    }
}
