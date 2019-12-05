/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* AgencyDB Class
 * Contains methods for obtaining data from table "agencies"
 *
 * Author: Ivan Ng
 * Date Created: August 27, 2016
 * Last Updated: August 27, 2016
 * Assignment: PROJ207 - Phase Three - Java
 */
package com.cgi.manager.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Contains a connection to the Agencies table
 * @author 723243
 */
public final class AgencyDB {
    
    public static ObservableList<Integer> getAgencyIds() throws ClassNotFoundException, SQLException {
        
        ObservableList<Integer> agencies = FXCollections.observableArrayList();
        
        Connection conn = ConnectionDB.getConnection();
        Statement stmt = conn.createStatement();
        String sql = "SELECT AgencyId FROM Agencies";
        
        try {
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                agencies.add(rs.getInt("AgencyId"));
            }
        } finally {
            conn.close();
        }
        return agencies;
    }
}
