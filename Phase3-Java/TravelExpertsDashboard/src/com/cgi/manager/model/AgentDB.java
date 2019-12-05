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
 * Last Updated: October 04, 2016
 * Assignment: PROJ207 - Phase Three - Java
 */
package com.cgi.manager.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Contains methods for accessing information in table "agents"
 * @author 723243
 */
public final class AgentDB {
    
    public static ObservableList<Agent> getAgents() throws ClassNotFoundException, SQLException {
        
        ObservableList<Agent> agents = FXCollections.observableArrayList();
        
        Connection conn = ConnectionDB.getConnection();
        
        try {
            
            Statement stmt = conn.createStatement();
            
            String sql = "SELECT * FROM Agents";
            
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {

                Agent tempAgent = new Agent();

                tempAgent.setAgentId(rs.getInt("AgentId"));

                tempAgent.setAgentFName(rs.getString("AgtFirstName"));

                tempAgent.setAgentMInitial(rs.getString("AgtMiddleInitial"));

                tempAgent.setAgentLName(rs.getString("AgtLastName"));

                tempAgent.setAgentBusPhone(rs.getString("AgtBusPhone"));

                tempAgent.setAgentEmail(rs.getString("AgtEmail"));

                tempAgent.setAgentPosition(rs.getString("AgtPosition"));

                tempAgent.setAgencyId(rs.getInt("AgencyId"));
                
                agents.add(tempAgent);
            }
            
        } finally {
            conn.close();
        }
        
        return agents;
    }
    
    public static int addNewAgent(Agent agent) throws ClassNotFoundException, SQLException {
        
        int rowschanged = 0;
        Connection conn = ConnectionDB.getConnection();
        
        try {

            String sql = "INSERT INTO Agents (AgtFirstName, AgtMiddleInitial, AgtLastName, AgtBusPhone, AgtEmail, AgtPosition, AgencyId)" +
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
            e.printStackTrace();
            System.out.println("Error in adding new Agent.");
        } finally {
            conn.close();
        }
        return rowschanged;
    }
    
    public static int editAgent(Agent newAgent, Agent oldAgent) throws ClassNotFoundException, SQLException {
        
        int rowschanged = 0;
        Connection conn = ConnectionDB.getConnection();
        
        try {
            
            String sql = "UPDATE Agents " +
                         "SET AgtFirstName=?, AgtMiddleInitial=?, AgtLastName=?, " +
                         "AgtBusPhone=?, AgtEmail=?, AgtPosition=?, AgencyId=? " +
                         "WHERE AgentId=? AND AgtFirstName=? AND (AgtMiddleInitial=? " +
                         "OR (AgtMiddleInitial IS NULL AND ? IS NULL)) " +
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
            stmt.setString(11, oldAgent.getAgentMInitial());
            stmt.setString(12, oldAgent.getAgentLName());
            stmt.setString(13, oldAgent.getAgentBusPhone());
            stmt.setString(14, oldAgent.getAgentEmail());
            stmt.setString(15, oldAgent.getAgentPosition());
            stmt.setInt(16, oldAgent.getAgencyId());
            
            rowschanged = stmt.executeUpdate();
            
            if (rowschanged > 0) {
                System.out.println("Agent edit was successful.");
            } else {
                System.out.println("Agent edit was unsuccessful.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Agent edit has crashed.");
        } finally {
            conn.close();
        }
        return rowschanged;
    }
    
    public static int editAgent(Agent newAgent) throws ClassNotFoundException, SQLException {
        
        int rowschanged = 0;
        Connection conn = ConnectionDB.getConnection();
        
        try {
            
            String sql = "UPDATE Agents " +
                         "SET AgtFirstName=?, AgtMiddleInitial=?, AgtLastName=?, " +
                         "AgtBusPhone=?, AgtEmail=?, AgtPosition=?, AgencyId=? " +
                         "WHERE AgentId=?;";
                         
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, newAgent.getAgentFName());
            stmt.setString(2, newAgent.getAgentMInitial());
            stmt.setString(3, newAgent.getAgentLName());
            stmt.setString(4, newAgent.getAgentBusPhone());
            stmt.setString(5, newAgent.getAgentEmail());
            stmt.setString(6, newAgent.getAgentPosition());
            stmt.setInt(7, newAgent.getAgencyId());
            stmt.setInt(8, newAgent.getAgentId());
            
            rowschanged = stmt.executeUpdate();
            
            if (rowschanged > 0) {
                System.out.println("Agent edit was successful.");
            } else {
                System.out.println("Agent edit was unsuccessful.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Agent edit has crashed.");
        } finally {
            conn.close();
        }
        
        
        return rowschanged;
    }
    
    /**
     * Contains methods for accessing agent id's in table "agents"
     * @author Greg Ng
     */
    public static ObservableList<Integer> getAgentIds() throws ClassNotFoundException, SQLException {
        
        ObservableList<Integer> agentIds = FXCollections.observableArrayList();
        
        Connection conn = ConnectionDB.getConnection();
        
        try {
            
            Statement stmt = conn.createStatement();
            
            String sql = "SELECT AgentId FROM Agents WHERE AgtPosition != 'Inactive'";
            
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()) {
                agentIds.add(rs.getInt("AgentId"));
            }
            
        } finally {
            conn.close();
        }
        
        return agentIds;
    }
}
