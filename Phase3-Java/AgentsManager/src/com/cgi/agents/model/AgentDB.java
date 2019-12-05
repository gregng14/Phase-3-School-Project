/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* AgentDB Class
 * Contains methods for accessing information in table "agents"
 *
 * Author: Ivan Ng
 * Date Created: August 27, 2016
 * Last Updated: August 27, 2016
 * Assignment: PROJ207 - Phase Three - Java
 */
package com.cgi.agents.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Contains methods for accessing information in table "agents"
 * @author 723243
 */
public final class AgentDB {
    
    public static ObservableList<Agent> getAgents() throws ClassNotFoundException, SQLException {
        
        ObservableList<Agent> agents = FXCollections.observableArrayList();
        
        Connection conn = TravelExpertsDB.getConnection();
        //System.out.println("Just past the connection");
        
        try {
            
            Statement stmt = conn.createStatement();
            
            String sql = "SELECT * FROM agents";
            
            ResultSet rs = stmt.executeQuery(sql);
            //System.out.println("Right before reading data yo");
            int counter = 1;
            
            while(rs.next()) {
                //System.out.print(counter++);
                Agent tempAgent = new Agent();
                //System.out.print(counter++);
                tempAgent.setAgentId(rs.getInt("AgentId"));
                //System.out.print(counter++);
                tempAgent.setAgentFName(rs.getString("AgtFirstName"));
                //System.out.print(counter++);
                tempAgent.setAgentMInitial(rs.getString("AgtMiddleInitial"));
                //System.out.print(counter++);
                tempAgent.setAgentLName(rs.getString("AgtLastName"));
                //System.out.print(counter++);
                tempAgent.setAgentBusPhone(rs.getString("AgtBusPhone"));
                //System.out.print(counter++);
                tempAgent.setAgentEmail(rs.getString("AgtEmail"));
                //System.out.print(counter++);
                tempAgent.setAgentPosition(rs.getString("AgtPosition"));
                //System.out.print(counter++);
                tempAgent.setAgencyId(rs.getInt("AgencyId"));
                //System.out.print(counter++);
                
                System.out.println(Integer.toString(tempAgent.getAgentId()) + " " + tempAgent.getAgentFName() + " " + tempAgent.getAgentLName());
                agents.add(tempAgent);
            }
            
        } finally {
            conn.close();
        }
        
        return agents;
    }
    
    public static int addNewAgent(Agent agent) throws ClassNotFoundException, SQLException {
        
        int rowschanged = 0;
        Connection conn = TravelExpertsDB.getConnection();
        
        try {

            String sql = "INSERT INTO agents (AgtFirstName, AgtMiddleInitial, AgtLastName, AgtBusPhone, AgtEmail, AgtPosition, AgencyId)" +
                         "VALUES (?, ?, ?, ?, ?, ?, ?);";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, agent.getAgentFName());
            stmt.setString(2, agent.getAgentMInitial());
            stmt.setString(3, agent.getAgentLName());
            stmt.setString(4, agent.getAgentBusPhone());
            stmt.setString(5, agent.getAgentEmail());
            stmt.setString(6, agent.getAgentPosition());
            stmt.setInt(7, agent.getAgencyId());
            
            rowschanged = stmt.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("Error in adding new agent yo");
        } finally {
            conn.close();
        }
        return rowschanged;
    }
    
    public static int editAgent(Agent newAgent, Agent oldAgent) throws ClassNotFoundException, SQLException {
        
        int rowschanged = 0;
        Connection conn = TravelExpertsDB.getConnection();
        
        try {
            
            String sql = "UPDATE agents " +
                         "SET AgtFirstName=?, AgtMiddleInitial=?, AgtLastName=?, " +
                         "AgtBusPhone=?, AgtEmail=?, AgtPosition=?, AgencyId=? " +
                         "WHERE AgentId=? AND AgtFirstName=? AND AgtMiddleInitial=? " +
                         "AND AgtLastName=? AND AgtBusPhone=? AND AgtEmail=? " +
                         "AND AgtPosition=? AND AgencyId=?;";
                         
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, newAgent.getAgentFName());
            stmt.setString(2, newAgent.getAgentMInitial());
            stmt.setString(3, newAgent.getAgentLName());
            stmt.setString(4, newAgent.getAgentBusPhone());
            stmt.setString(5, newAgent.getAgentEmail());
            stmt.setString(6, newAgent.getAgentPosition());
            stmt.setInt(7, newAgent.getAgencyId());
            stmt.setInt(8, oldAgent.getAgentId());
            stmt.setString(9, oldAgent.getAgentFName());
            stmt.setString(10, oldAgent.getAgentMInitial());
            stmt.setString(11, oldAgent.getAgentLName());
            stmt.setString(12, oldAgent.getAgentBusPhone());
            stmt.setString(13, oldAgent.getAgentEmail());
            stmt.setString(14, oldAgent.getAgentPosition());
            stmt.setInt(15, oldAgent.getAgencyId());
            
            rowschanged = stmt.executeUpdate();
            
            if (rowschanged > 0) {
                System.out.println("Update was successful yo");
            } else {
                System.out.println("Update was unsuccessful yo");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Editing agents failed yo");
        } finally {
            conn.close();
        }
        
        
        return rowschanged;
    }
    
    public static int editAgent(Agent newAgent) throws ClassNotFoundException, SQLException {
        
        int rowschanged = 0;
        Connection conn = TravelExpertsDB.getConnection();
        
        try {
            
            String sql = "UPDATE agents " +
                         "SET AgtFirstName=?, AgtMiddleInitial=?, AgtLastName=?, " +
                         "AgtBusPhone=?, AgtEmail=?, AgtPosition=?, AgencyId=? " +
                         "WHERE AgentId=?;";
//                         "AND AgtFirstName=? AND AgtMiddleInitial=? " +
//                         "AND AgtLastName=? AND AgtBusPhone=? AND AgtEmail=? " +
//                         "AND AgtPosition=? AND AgencyId=?;";
                         
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, newAgent.getAgentFName());
            stmt.setString(2, newAgent.getAgentMInitial());
            stmt.setString(3, newAgent.getAgentLName());
            stmt.setString(4, newAgent.getAgentBusPhone());
            stmt.setString(5, newAgent.getAgentEmail());
            stmt.setString(6, newAgent.getAgentPosition());
            stmt.setInt(7, newAgent.getAgencyId());
            stmt.setInt(8, newAgent.getAgentId());
//            stmt.setString(9, oldAgent.getAgentFName());
//            stmt.setString(10, oldAgent.getAgentMInitial());
//            stmt.setString(11, oldAgent.getAgentLName());
//            stmt.setString(12, oldAgent.getAgentBusPhone());
//            stmt.setString(13, oldAgent.getAgentEmail());
//            stmt.setString(14, oldAgent.getAgentPosition());
//            stmt.setInt(15, oldAgent.getAgencyId());
            
            rowschanged = stmt.executeUpdate();
            
            if (rowschanged > 0) {
                System.out.println("Update was successful yo");
            } else {
                System.out.println("Update was unsuccessful yo");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Editing agents failed yo");
        } finally {
            conn.close();
        }
        
        
        return rowschanged;
    }
}
