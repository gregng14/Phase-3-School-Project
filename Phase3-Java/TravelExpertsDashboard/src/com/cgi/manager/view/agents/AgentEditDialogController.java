/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* AgentEdit Controller
 * Contains Controller methods for the AgentEdit view
 *
 * Author: Ivan Ng
 * Date Created: September 01, 2016
 * Last Updated: September 12, 2016
 * Assignment: PROJ207 - Phase Three - Java
 */

package com.cgi.manager.view.agents;

import com.cgi.manager.MainApp;
import com.cgi.manager.model.AgencyDB;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.cgi.manager.model.Agent;
import com.cgi.manager.model.AgentDB;
import com.cgi.manager.view.ViewController;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ButtonType;



/**
 *
 * @author 723243
 */
public class AgentEditDialogController implements ViewController{
    
    @FXML
    private TextField tfFirstName;
    
    @FXML
    private TextField tfMidInitial;
    
    @FXML
    private TextField tfLastName;
    
    @FXML
    private TextField tfPhone;
    
    @FXML
    private TextField tfEmail;
    
    @FXML
    private ComboBox cboAgency;
    
    @FXML
    private TextField tfPosition;
    
    @FXML
    private CheckBox chkActive;
    
    private MainApp main;
    private Stage dialogStage;
    private Agent agent;
    private Agent oldAgent;
    private boolean okClicked = false;
    private int edit; // Same logic as Greg's --> 1 = New, 2 = Edit
    
    private ObservableList<Integer> listOfAgencies = FXCollections.observableArrayList();
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    
    @FXML
    private void initialize() {
        // Do nothing
        // Get the agency IDs and then populate the combo box with the integers
        try {
            agent = new Agent();
            listOfAgencies = AgencyDB.getAgencyIds();
            
            cboAgency.setItems(listOfAgencies);
        } catch (Exception e) {
            System.out.println("Failed to load the agency IDs");
        }
        
        chkActive.setSelected(true);
        chkActive.setOnAction((event) -> {
            boolean selected = chkActive.isSelected();
            if (selected) {
                tfPosition.setDisable(false);
            } else {
                tfPosition.setDisable(true);
            }
        });
    }
    
    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    /**
     * Sets the agent to be edited in the dialog.
     * 
     * @param agent
     */
    public void setAgent(Agent agent) {
        
        this.agent = agent;
        System.out.println(agent.getAgentId());
        if (agent != null && agent.getAgentId() > 0) {
            tfFirstName.setText(agent.getAgentFName());
            tfMidInitial.setText(agent.getAgentMInitial());
            tfLastName.setText(agent.getAgentLName());
            tfPhone.setText(agent.getAgentBusPhone());
            tfEmail.setText(agent.getAgentEmail());
            cboAgency.setValue(agent.getAgencyId());
            tfPosition.setText(agent.getAgentPosition());
            
            if (agent.getAgentPosition().equals("Inactive")) {
                chkActive.setSelected(false);
                tfPosition.setDisable(true);
            } else {
                chkActive.setSelected(true);
                tfPosition.setDisable(false);
            }
        } else {
            tfFirstName.setText("");
            tfMidInitial.setText("");
            tfLastName.setText("");
            tfPhone.setText("");
            tfEmail.setText("");
            cboAgency.getSelectionModel().selectFirst();
            tfPosition.setText("");
            chkActive.setSelected(true);
        }
    }
    
    public void setOldAgent(Agent oldAgent) {
        this.oldAgent = oldAgent;
    }
    
    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }
    
    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            agent.setAgentFName(tfFirstName.getText());
            agent.setAgentMInitial(tfMidInitial.getText());
            agent.setAgentLName(tfLastName.getText());
            agent.setAgentBusPhone(tfPhone.getText());
            agent.setAgentEmail(tfEmail.getText());
            agent.setAgencyId(Integer.parseInt(cboAgency.getSelectionModel().getSelectedItem().toString()));
            if (chkActive.isSelected()) {
                agent.setAgentPosition(tfPosition.getText());
            } else {
                agent.setAgentPosition("Inactive");
            }
            
            try {
                if (oldAgent == null) {
                    AgentDB.addNewAgent(agent);
                } else {
                    AgentDB.editAgent(agent, oldAgent);
                }
            } catch (Exception e) {
                System.out.println("Adding agent to DB in handleOk failed");
                e.printStackTrace();
            }
            try {
                main.showAgentScene();

            } catch (Exception e) {
                System.out.println("Show Agent scene failed");
                e.printStackTrace();
            }
            
            // Do agent update here with old agent and new
            //okClicked = true;
            //dialogStage.close();
        }
    }
    
    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        try {
                main.showAgentScene();

            } catch (Exception e) {
                System.out.println("Show Agent scene failed");
                e.printStackTrace();
            }
    }
    
    /**
     * Validates the user input in the fields
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        
        String errorMessage = "";
        
        // Email regex provided by emailregex.com using the RFC5322 standard
        String emailPatternString = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        
        // Phone number regex provided by Ravi Thapliyal on Stack Overflow:
        // http://stackoverflow.com/questions/16699007/regular-expression-to-match-standard-10-digit-phone-number
        String phoneNumString = "^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$";
        
        Pattern emailPattern = Pattern.compile(emailPatternString, Pattern.CASE_INSENSITIVE);
        Matcher emailMatcher;
        
        Pattern phoneNumPattern = Pattern.compile(phoneNumString);
        Matcher phoneNumMatcher;
        
        // Validation for no length or over database size limit
        if ((tfFirstName.getText() == null || tfFirstName.getText().length() == 0) || (tfFirstName.getText().length() > 20)) {
            errorMessage += "First name has to be between 1 and 20 characters.\n";
        }
        
        if (tfMidInitial.getText() != null && tfMidInitial.getText().length() > 5) {
            errorMessage += "Middle Initial has to be between 0 and 5 characters.\n";
        }
        
        if ((tfLastName.getText() == null || tfLastName.getText().length() == 0) || (tfLastName.getText().length() > 20)) {
            errorMessage += "Last name has to be between 1 and 20 characters.\n";
        }
        
        phoneNumMatcher = phoneNumPattern.matcher(tfPhone.getText());
        
        if ((tfPhone.getText() == null || tfPhone.getText().length() == 0) || (tfPhone.getText().length() > 20)) {
            errorMessage += "Phone number has to be between 1 and 20 characters long.\n";
        } else {
            if (!phoneNumMatcher.matches()) {
                errorMessage += "Phone number not in the correct format: (111) 111-1111.\n";
            }
        }
        
        emailMatcher = emailPattern.matcher(tfEmail.getText());
        
        if ((tfEmail.getText() == null || tfEmail.getText().length() == 0) || (tfEmail.getText().length() > 50)) {
            errorMessage += "Email has to be between 1 and 50 characters.\n";
        } else {
            if (!emailMatcher.matches()) {
                errorMessage += "Email format is not correct. Should be in the format username@domain.domaincode.\n"; 
            }       
        }
        
        if (chkActive.isSelected() && ((tfPosition.getText() == null || tfPosition.getText().length() == 0) || (tfPosition.getText().length() > 20))) {
            errorMessage += "Position has to be between 1 and 20 characters.\n";
        }
        
        if (errorMessage.length() > 0) {
            Alert alert = new Alert(AlertType.ERROR, errorMessage, ButtonType.OK);
            alert.setTitle("Input Error");
            
            alert.showAndWait();
            
            return false;
        }
        
        return true;
    }
    
    public void setMainApp(MainApp main) {
        this.main = main;
    }
    
}
