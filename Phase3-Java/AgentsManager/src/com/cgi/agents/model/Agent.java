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
 * Last Updated: September 12, 2016
 * Assignment: PROJ207 - Phase Three - Java
 */
package com.cgi.agents.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

// WHY YOU NO UPDATE
/**
 * Contains field data for Agent objects
 * @author 723243
 */
public class Agent {
    
    private final IntegerProperty agentId = new SimpleIntegerProperty();
    private final StringProperty agentFName = new SimpleStringProperty();
    private final StringProperty agentMInitial = new SimpleStringProperty();
    private final StringProperty agentLName = new SimpleStringProperty();
    private final StringProperty agentBusPhone = new SimpleStringProperty();
    private final StringProperty agentEmail = new SimpleStringProperty();
    private final StringProperty agentPosition = new SimpleStringProperty();
    private final IntegerProperty agencyId = new SimpleIntegerProperty();
    
//    public Agent() {
//        // Set default values
//        this.agentId = new SimpleIntegerProperty(0);
//        this.agentFName = new SimpleStringProperty("Blah");
//        this.agentMInitial = new SimpleStringProperty("N.");
//        this.agentLName = new SimpleStringProperty("You");
//        this.agentBusPhone = new SimpleStringProperty("403-123-4567");
//        this.agentEmail = new SimpleStringProperty("Blah.You@te.ca");
//        this.agentPosition = new SimpleStringProperty("Junior Blahface");
//        this.agencyId = new SimpleIntegerProperty(2);
//    }
    
//    public Agent() {
//         Set default values
//        this.agentId = null;
//        this.agentFName = null;
//        this.agentMInitial = null;
//        this.agentLName = null;
//        this.agentBusPhone = null;
//        this.agentEmail = null;
//        this.agentPosition = null;
//        this.agencyId = null;
//    }
    
    public int getAgentId() {
        return agentId.get();
    }

    public void setAgentId(int agentId) {
        this.agentId.set(agentId);
    }
    
    public IntegerProperty agentIdProperty() {
        return agentId;
    }

    public String getAgentFName() {
        return agentFName.get();
    }

    public void setAgentFName(String agentFName) {
        this.agentFName.set(agentFName);
    }
    
    public StringProperty agentFNameProperty() {
        return agentFName;
    }

    public String getAgentMInitial() {
        return agentMInitial.get();
    }

    public void setAgentMInitial(String agentMInitial) {
        this.agentMInitial.set(agentMInitial);
    }
    
    public StringProperty agentMInitialProperty() {
        return agentMInitial;
    }

    public String getAgentLName() {
        return agentLName.get();
    }

    public void setAgentLName(String agentLName) {
        this.agentLName.set(agentLName);
    }
    
    public StringProperty agentLNameProperty() {
        return agentLName;
    }

    public String getAgentBusPhone() {
        return agentBusPhone.get();
    }

    public void setAgentBusPhone(String agentBusPhone) {
        this.agentBusPhone.set(agentBusPhone);
    }

    public StringProperty agentBusPhoneProperty() {
        return agentBusPhone;
    }
    
    public String getAgentEmail() {
        return agentEmail.get();
    }

    public void setAgentEmail(String agentEmail) {
        this.agentEmail.set(agentEmail);
    }
    
    public StringProperty agentEmailProperty() {
        return agentEmail;
    }

    public String getAgentPosition() {
        return agentPosition.get();
    }

    public void setAgentPosition(String agentPosition) {
        this.agentPosition.set(agentPosition);
    }
    
    public StringProperty agentPositionProperty() {
        return agentPosition;
    }

    public int getAgencyId() {
        return agencyId.get();
    }

    public void setAgencyId(int agencyId) {
        this.agencyId.set(agencyId);
    }
    
    public IntegerProperty agencyIdProperty() {
        return agencyId;
    }
    
    @Override
    public String toString() {
        return getAgentId() + " " + getAgentFName() + " " + getAgentLName();
    }
    
    public static Agent cloneAgent(Agent agent) {
        Agent cloningAgent = new Agent();
        
        cloningAgent.setAgentId(agent.getAgentId());
        cloningAgent.setAgentFName(agent.getAgentFName());
        cloningAgent.setAgentMInitial(agent.getAgentMInitial());
        cloningAgent.setAgentLName(agent.getAgentLName());
        cloningAgent.setAgentBusPhone(agent.getAgentBusPhone());
        cloningAgent.setAgentEmail(agent.getAgentEmail());
        cloningAgent.setAgencyId(agent.getAgencyId());
        cloningAgent.setAgentPosition(agent.getAgentPosition());
        
        return cloningAgent;
    }
}
