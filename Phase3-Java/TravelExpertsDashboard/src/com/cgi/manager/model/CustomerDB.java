package com.cgi.manager.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Greg Ng
 * Last updated October 04, 2016
 */
public class CustomerDB {
    public static ObservableList<Customer> getCustomers() {
        
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        
        Connection conn = null;
        
        try {
            conn = ConnectionDB.getConnection();
            Statement stmt = conn.createStatement();
            
            String sql = "SELECT * FROM Customers";
            
            ResultSet rs = stmt.executeQuery(sql);

            int counter = 1;
            
            while(rs.next()) {

                Customer tempCustomer = new Customer();

                tempCustomer.setCustomerId(rs.getInt("CustomerId"));

                tempCustomer.setCustFirstName(rs.getString("CustFirstName"));

                tempCustomer.setCustLastName(rs.getString("CustLastName"));

                tempCustomer.setCustAddress(rs.getString("CustAddress"));

                tempCustomer.setCustCity(rs.getString("CustCity"));

                tempCustomer.setCustProv(rs.getString("CustProv"));

                tempCustomer.setCustPostal(rs.getString("CustPostal"));
                
                tempCustomer.setCustCountry(rs.getString("CustCountry"));
                
                tempCustomer.setCustHomePhone(rs.getString("CustHomePhone"));
                
                tempCustomer.setCustBusPhone(rs.getString("CustBusPhone"));
                
                tempCustomer.setCustEmail(rs.getString("CustEmail"));

                tempCustomer.setAgentId(rs.getInt("AgentId"));

                customers.add(tempCustomer);
            }
            
        } 
        catch(ClassNotFoundException | SQLException e){
            System.out.println("Error in getting customer");
        }
        finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Error in closing database");
            }
        }
        
        return customers;
    }
    
    public static ObservableList<Customer> getCustomersWithInactiveAgent() {
        
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        
        Connection conn = null;
        
        try {
            conn = ConnectionDB.getConnection();
            Statement stmt = conn.createStatement();
            
            String sql = "SELECT c.* FROM Customers as c, Agents as a "
                    + "WHERE a.AgentId = c.AgentId "
                    + "AND a.AgtPosition = 'Inactive'";
            
            ResultSet rs = stmt.executeQuery(sql);

            int counter = 1;
            
            while(rs.next()) {

                Customer tempCustomer = new Customer();

                tempCustomer.setCustomerId(rs.getInt("CustomerId"));

                tempCustomer.setCustFirstName(rs.getString("CustFirstName"));

                tempCustomer.setCustLastName(rs.getString("CustLastName"));

                tempCustomer.setCustAddress(rs.getString("CustAddress"));

                tempCustomer.setCustCity(rs.getString("CustCity"));

                tempCustomer.setCustProv(rs.getString("CustProv"));

                tempCustomer.setCustPostal(rs.getString("CustPostal"));
                
                tempCustomer.setCustCountry(rs.getString("CustCountry"));
                
                tempCustomer.setCustHomePhone(rs.getString("CustHomePhone"));
                
                tempCustomer.setCustBusPhone(rs.getString("CustBusPhone"));
                
                tempCustomer.setCustEmail(rs.getString("CustEmail"));

                tempCustomer.setAgentId(rs.getInt("AgentId"));

                customers.add(tempCustomer);
            }
            
        } 
        catch(ClassNotFoundException | SQLException e){
            System.out.println("Error in getting customer");
        }
        finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Error in closing database");
            }
        }
        
        return customers;
    }
    
    public static int addNewCustomer(Customer customer){
        
        int rowschanged = 0;
        Connection conn = null;
        try {   
            
            conn = ConnectionDB.getConnection();
            
            // Test code to get max customerid
            String maxSql = "SELECT MAX(CustomerId) AS maxId FROM Customers";
            Statement maxStmt = conn.createStatement();
            ResultSet maxRs = maxStmt.executeQuery(maxSql);
            
            int maxId = 0;
            if (maxRs.next()) {
                maxId = maxRs.getInt("maxId") + 1;
            }
            
            String sql = "INSERT INTO Customers (CustFirstName, CustLastName, CustAddress, CustCity, CustProv, CustPostal, CustCountry, CustHomePhone, CustBusPhone, CustEmail, AgentId, UserId, Password)" +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, customer.getCustFirstName());
            stmt.setString(2, customer.getCustLastName());
            stmt.setString(3, customer.getCustAddress());
            stmt.setString(4, customer.getCustCity());
            stmt.setString(5, customer.getCustProv());
            stmt.setString(6, customer.getCustPostal());
            stmt.setString(7, customer.getCustCountry());
            stmt.setString(8, customer.getCustHomePhone());
            stmt.setString(9, customer.getCustBusPhone());
            stmt.setString(10, customer.getCustEmail());
            stmt.setInt(11, customer.getAgentId());
            stmt.setString(12, "user" + maxId);
            stmt.setString(13, "pass" + maxId);
            
            rowschanged = stmt.executeUpdate();
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error in adding new customer");
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Error in closing database");
            }
        }
        return rowschanged;
    }
    
    public static int editCustomer(Customer newCustomer, Customer oldCustomer){
        
        int rowschanged = 0;
        Connection conn = null;
        
        try {
            conn = ConnectionDB.getConnection();
            String sql = "UPDATE Customers " 
                    + "SET CustFirstName=?, "
                    + "CustLastName=?, "
                    + "CustAddress=?, " 
                    + "CustCity=?, "
                    + "CustProv=?, "
                    + "CustPostal=?, "
                    + "CustCountry=?, " 
                    + "CustHomePhone=?, "
                    + "CustBusPhone=?, "
                    + "CustEmail=?, "
                    + "AgentId=? "
                    + "WHERE CustomerId=? "
                    + "AND CustFirstName=? "
                    + "AND CustLastName=? " 
                    + "AND CustAddress=? "
                    + "AND CustCity=? "
                    + "AND CustProv=? " 
                    + "AND CustPostal=? "
                    + "AND CustCountry=? "
                    + "AND CustHomePhone=? "
                    + "AND CustBusPhone=? "
                    + "AND CustEmail=? "
                    + "AND AgentId=?;";
                         
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, newCustomer.getCustFirstName());
            stmt.setString(2, newCustomer.getCustLastName());
            stmt.setString(3, newCustomer.getCustAddress());
            stmt.setString(4, newCustomer.getCustCity());
            stmt.setString(5, newCustomer.getCustProv());
            stmt.setString(6, newCustomer.getCustPostal());
            stmt.setString(7, newCustomer.getCustCountry());
            stmt.setString(8, newCustomer.getCustHomePhone());
            stmt.setString(9, newCustomer.getCustBusPhone());
            stmt.setString(10, newCustomer.getCustEmail());
            stmt.setInt(11, newCustomer.getAgentId());
            stmt.setInt(12, oldCustomer.getCustomerId());
            stmt.setString(13, oldCustomer.getCustFirstName());
            stmt.setString(14, oldCustomer.getCustLastName());
            stmt.setString(15, oldCustomer.getCustAddress());
            stmt.setString(16, oldCustomer.getCustCity());
            stmt.setString(17, oldCustomer.getCustProv());
            stmt.setString(18, oldCustomer.getCustPostal());
            stmt.setString(19, oldCustomer.getCustCountry());
            stmt.setString(20, oldCustomer.getCustHomePhone());
            stmt.setString(21, oldCustomer.getCustBusPhone());
            stmt.setString(22, oldCustomer.getCustEmail());
            stmt.setInt(23, oldCustomer.getAgentId());
            
            rowschanged = stmt.executeUpdate();
            
            if (rowschanged > 0) {
                System.out.println("Update was successful");
            } else {
                System.out.println("Update was unsuccessful");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Error in editing customers");
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Error in closing database");
            }
        }
        return rowschanged;
    }
    
//    public static int editCustomer(Customer newCustomer) {
//        
//        int rowschanged = 0;
//        Connection conn = null;
////CustFirstName, CustLastName, CustAddress, CustCity, CustProv, CustPostal, CustCountry, CustHomePhone, CustBusPhone, CustEmail, AgentId
//        try {
//            conn = TravelExpertsDB.getConnection();
//            String sql = "UPDATE agents " +
//                         "SET AgtFirstName=?, AgtMiddleInitial=?, AgtLastName=?, " +
//                         "AgtBusPhone=?, AgtEmail=?, AgtPosition=?, AgencyId=? " +
//                         "WHERE AgentId=?;";
////                         "AND AgtFirstName=? AND AgtMiddleInitial=? " +
////                         "AND AgtLastName=? AND AgtBusPhone=? AND AgtEmail=? " +
////                         "AND AgtPosition=? AND AgencyId=?;";
//                         
//            PreparedStatement stmt = conn.prepareStatement(sql);
//            
//            stmt.setString(1, newAgent.getAgentFName());
//            stmt.setString(2, newAgent.getAgentMInitial());
//            stmt.setString(3, newAgent.getAgentLName());
//            stmt.setString(4, newAgent.getAgentBusPhone());
//            stmt.setString(5, newAgent.getAgentEmail());
//            stmt.setString(6, newAgent.getAgentPosition());
//            stmt.setInt(7, newAgent.getAgencyId());
//            stmt.setInt(8, newAgent.getAgentId());
////            stmt.setString(9, oldAgent.getAgentFName());
////            stmt.setString(10, oldAgent.getAgentMInitial());
////            stmt.setString(11, oldAgent.getAgentLName());
////            stmt.setString(12, oldAgent.getAgentBusPhone());
////            stmt.setString(13, oldAgent.getAgentEmail());
////            stmt.setString(14, oldAgent.getAgentPosition());
////            stmt.setInt(15, oldAgent.getAgencyId());
//            
//            rowschanged = stmt.executeUpdate();
//            
//            if (rowschanged > 0) {
//                System.out.println("Update was successful yo");
//            } else {
//                System.out.println("Update was unsuccessful yo");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Editing agents failed yo");
//        } finally {
//            try {
//                conn.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(CustomerDB.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        
//        
//        return rowschanged;
//    }
}
