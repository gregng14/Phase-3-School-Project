/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* AgentsManager Controller
 * Contains Controller methods for the AgentsManager view
 *
 * Author: Ivan Ng
 * Date Created: August 27, 2016
 * Last Updated: October 04, 2016
 * Assignment: PROJ207 - Phase Three - Java
 */
package com.cgi.manager.view.agents;

import com.cgi.manager.MainApp;
import com.cgi.manager.model.Agent;
import com.cgi.manager.model.AgentDB;
import com.cgi.manager.view.ViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 *
 * @author 723243
 */
//public class AgentsManagerController implements Initializable {
public class AgentsManagerController implements ViewController{
    
    @FXML
    private TableView<Agent> agentTable;
    
    @FXML
    private TableColumn<Agent, String> firstNameColumn;
    
    @FXML
    private TableColumn<Agent, String> lastNameColumn;
    
    @FXML
    private TextField tfAgentId;
    
    @FXML
    private TextField tfFirstName;
    
    @FXML
    private TextField tfMidInitial;
    
    @FXML
    private TextField tfLastName;
    
    @FXML
    private TextField tfBusPhone;
    
    @FXML
    private TextField tfEmail;
    
    @FXML
    private TextField tfPosition;
    
    @FXML
    private TextField tfAgency;
    
    private MainApp mainApp;
    
    private ObservableList<Agent> agentData = FXCollections.observableArrayList();
    
    public AgentsManagerController() {
        //Get Agent data initially
        try {
            agentData = AgentDB.getAgents();
        } catch (Exception e) {
            System.out.println("Error in retrieving agents at the start");
            e.printStackTrace();
        }
    }
    
    @FXML
    private void initialize() {
        // Initialize table with info
        try {
            firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().agentFNameProperty());
            lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().agentLNameProperty());
        } catch (Exception e) {
            System.out.println("Initialization has failed.");
        }
        
        // Clear agent details
        showAgentDetails(null);
        
        // Listen for selection change in the table, and show the selected Agent
        agentTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldvalue, newValue) -> showAgentDetails(newValue));
    }
    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        
        agentTable.setItems(agentData);
    }
    
    private void showAgentDetails(Agent agent) {
        if (agent != null) {
            // Fill the labels with info from the Agent object
            tfAgentId.setText(Integer.toString(agent.getAgentId()));
            tfFirstName.setText(agent.getAgentFName());
            tfMidInitial.setText(agent.getAgentMInitial());
            tfLastName.setText(agent.getAgentLName());
            tfBusPhone.setText(agent.getAgentBusPhone());
            tfEmail.setText(agent.getAgentEmail());
            tfAgency.setText(Integer.toString(agent.getAgencyId()));
            tfPosition.setText(agent.getAgentPosition());
        } else {
            tfAgentId.setText("");
            tfFirstName.setText("");
            tfMidInitial.setText("");
            tfLastName.setText("");
            tfBusPhone.setText("");
            tfEmail.setText("");
            tfAgency.setText("");
            tfPosition.setText("");
        }
    }
    
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new agent.
     */
    @FXML
    private void handleNewAgent() {
        Agent tempAgent = new Agent();
        
        try {
            mainApp.showEditAgentScene(tempAgent);
        } catch (Exception e) {
            System.out.println("handleNewAgent() failed");
            e.printStackTrace();
        }
        
    }
    
    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected agent.
     */
    @FXML
    private void handleEditAgent() {
        // For editing
        Agent selectedAgent = agentTable.getSelectionModel().getSelectedItem();
        
        if (selectedAgent != null) {
            try {
                // The information for the agent prior to editing
                Agent oldAgent = Agent.cloneAgent(selectedAgent);
                
                mainApp.showEditAgentScene(selectedAgent, oldAgent);
            } catch (Exception e) {
                System.out.println("handleEditAgent() failed");
                e.printStackTrace();
            }
        } else {
            // Nothing selected
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Agent Selected");
            alert.setContentText("Please select an agent in the table.");

            alert.showAndWait();
        }
    }

}
