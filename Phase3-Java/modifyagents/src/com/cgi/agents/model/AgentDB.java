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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 * Contains methods for accessing information in table "agents"
 * @author 723243
 */
public final class AgentDB {
    
    public static Vector<Agent> getAgents() throws ClassNotFoundException, SQLException {
        
        Vector<Agent> agents = new Vector<Agent>();
        
        Connection conn = TravelExpertsDB.getConnection();
        
        try {
            
            Statement stmt = conn.createStatement();
            
            String sql = "SELECT * FROM agents";
            
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
                
                agents.addElement(tempAgent);
            }
            
        } finally {
            conn.close();
        }
        
        return agents;
    }
    
}
