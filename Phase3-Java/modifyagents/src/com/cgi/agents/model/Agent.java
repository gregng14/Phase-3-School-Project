/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* Agent Class
 * Contains fields for storing information from the table "agents"
 *
 * Author: Ivan Ng
 * Date Created: August 27, 2016
 * Last Updated: August 27, 2016
 * Assignment: PROJ207 - Phase Three - Java
 */
package com.cgi.agents.model;

/**
 * Contains field data for Agent objects
 * @author 723243
 */
public class Agent {
    
    private int agentId;
    private String agentFName;
    private String agentMInitial;
    private String agentLName;
    private String agentBusPhone;
    private String agentEmail;
    private String agentPosition;
    private int agencyId;
    
    public Agent() {
        // Set default values
    }
    
    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public String getAgentFName() {
        return agentFName;
    }

    public void setAgentFName(String agentFName) {
        this.agentFName = agentFName;
    }

    public String getAgentMInitial() {
        return agentMInitial;
    }

    public void setAgentMInitial(String agentMInitial) {
        this.agentMInitial = agentMInitial;
    }

    public String getAgentLName() {
        return agentLName;
    }

    public void setAgentLName(String agentLName) {
        this.agentLName = agentLName;
    }

    public String getAgentBusPhone() {
        return agentBusPhone;
    }

    public void setAgentBusPhone(String agentBusPhone) {
        this.agentBusPhone = agentBusPhone;
    }

    public String getAgentEmail() {
        return agentEmail;
    }

    public void setAgentEmail(String agentEmail) {
        this.agentEmail = agentEmail;
    }

    public String getAgentPosition() {
        return agentPosition;
    }

    public void setAgentPosition(String agentPosition) {
        this.agentPosition = agentPosition;
    }

    public int getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(int agencyId) {
        this.agencyId = agencyId;
    }
}
