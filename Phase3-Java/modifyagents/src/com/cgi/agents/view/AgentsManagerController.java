/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cgi.agents.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

/**
 *
 * @author 723243
 */
public class AgentsManagerController implements Initializable {
    
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
    
    @FXML
    private CheckBox chkActive;
    
    @FXML
    private Label lblPosition;
    
    @FXML
    private Label lblAgency;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        //System.out.println("You clicked me!");
        //label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
