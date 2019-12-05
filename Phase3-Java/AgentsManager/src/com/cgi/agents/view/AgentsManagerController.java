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
 * Last Updated: September 01, 2016
 * Assignment: PROJ207 - Phase Three - Java
 */
package com.cgi.agents.view;

import com.cgi.agents.MainApp;
import com.cgi.agents.model.Agent;
import com.cgi.agents.model.AgentDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author 723243
 */
//public class AgentsManagerController implements Initializable {
public class AgentsManagerController {
    
    @FXML
    private TableView<Agent> agentTable;
    
    @FXML
    private TableColumn<Agent, String> firstNameColumn;
    
    @FXML
    private TableColumn<Agent, String> lastNameColumn;
    
    @FXML
    private Label lblAgentId;
    
    @FXML
    private Label lblFirstName;
    
    @FXML
    private Label lblMidInitial;
    
    @FXML
    private Label lblLastName;
    
    @FXML
    private Label lblBusPhone;
    
    @FXML
    private Label lblEmail;
    
    //@FXML
    //private CheckBox chkActive;
    
    @FXML
    private Label lblPosition;
    
    @FXML
    private Label lblAgency;
    
    @FXML
    private Button btnEdit;
    
    @FXML
    private Button btnNew;
    
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
        System.out.println("I AM CONFUSED");
        //Initialize();
    }
    
    @FXML
    private void initialize() {
        // Initialize table with info
        try {
            firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().agentFNameProperty());
            lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().agentLNameProperty());
            System.out.println("Hellloooo");
        } catch (Exception e) {
            System.out.println("WHY YOU NO LOAD");
        }
        
        // Clear agent details
        showAgentDetails(null);
        
        // Listen for selection change in the table, and show the selected Agent
        agentTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldvalue, newValue) -> showAgentDetails(newValue));
    }
    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        System.out.println("Mmmmmmmmm");
        
        // So somehow putting the Initialize here right before I set the table stuff up works...
        // Aug 31 10:35PM - Ivan
        //Initialize();
        agentTable.setItems(agentData);
    }
    
    private void showAgentDetails(Agent agent) {
        if (agent != null) {
            // Fill the labels with info from the Agent object
            lblAgentId.setText(Integer.toString(agent.getAgentId()));
            lblFirstName.setText(agent.getAgentFName());
            lblMidInitial.setText(agent.getAgentMInitial());
            lblLastName.setText(agent.getAgentLName());
            lblBusPhone.setText(agent.getAgentBusPhone());
            lblEmail.setText(agent.getAgentEmail());
            lblAgency.setText(Integer.toString(agent.getAgencyId()));
            lblPosition.setText(agent.getAgentPosition());
        } else {
            lblAgentId.setText("");
            lblFirstName.setText("");
            lblMidInitial.setText("");
            lblLastName.setText("");
            lblBusPhone.setText("");
            lblEmail.setText("");
            lblAgency.setText("");
            lblPosition.setText("");
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
            //mainApp.showAgentEditDialog(tempAgent);
        } catch (Exception e) {
            System.out.println("handleNewAgent failed");
            e.printStackTrace();
        }
        
        /* Dialog version
        boolean okClicked = mainApp.showAgentEditDialog(tempAgent);
        if (okClicked) {
            try {
                AgentDB.addNewAgent(tempAgent);
                //agentData.add(tempAgent);
                //
                agentData = AgentDB.getAgents();
                agentTable.setItems(agentData);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        } */
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
            
            /* Dialog box version
            boolean okClicked = mainApp.showAgentEditDialog(selectedAgent);
            System.out.println(selectedAgent.toString());
            System.out.println(oldAgent.toString());
            if (okClicked) {
                try {
                    AgentDB.editAgent(selectedAgent, oldAgent);
                    showAgentDetails(selectedAgent);
                } catch (Exception e) {
                    System.out.println("I could not edit agent yo");
                }
            } */
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
    


//    public ObservableList<Agent> getAgentData() {
//        try {
//            agentData = AgentDB.getAgents();
//        } catch (Exception e) {
//            System.out.println("Error in retrieving agents at the start");
//        }
//        return agentData;
//    }
    
//    @FXML
//    private void handleButtonAction(ActionEvent event) {
//        //System.out.println("You clicked me!");
//        //label.setText("Hello World!");
//    }
//    
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//    }    
    
}
