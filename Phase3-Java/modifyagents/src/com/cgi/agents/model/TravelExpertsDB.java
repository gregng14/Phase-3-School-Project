/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* TravelExpertsDB Class
 * Stores the information for the connection to the MySQL database.
 *
 * Author: Ivan Ng
 * Date Created: August 27, 2016
 * Last Updated: August 27, 2016
 * Assignment: PROJ207 - Phase Three - Java
 */
package com.cgi.agents.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Contains information to the TravelExperts database
 * @author Ivan Ng - 723243
 */
public final class TravelExpertsDB {
    
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/travelexperts", 
                                                      "root", "");
        
        return conn;
    }
}
