/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* MainApp class
 * The main controller of the manager
 *
 * Author: Ivan Ng
 * Date Created: August 27, 2016
 * Last Updated: September 01, 2016
 * Assignment: PROJ207 - Phase Three - Java
 */
package com.cgi.agents;

import com.cgi.agents.model.Agent;
import com.cgi.agents.view.AgentEditDialogController;
import com.cgi.agents.view.AgentsManagerController;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author 723243
 */
public class MainApp extends Application {
    
    private Stage primaryStage;
    private BorderPane rootLayout;
    AnchorPane agentsManager;
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Agents Manager");
        
        initRootLayout();
        
        showAgentsManager();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            // Show the scene containing the root layout
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Shows the agents manager inside the root layout
     */
    public void showAgentsManager() {
        try {
            // Load Agents Manager
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/AgentsManager.fxml"));
            agentsManager = (AnchorPane) loader.load();
            
            // Set the Agents Manager to be the centre of the root layout
            rootLayout.setCenter(agentsManager);
            
            AgentsManagerController controller = loader.getController();
            controller.setMainApp(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Will work on this later
    /**
     * Opens a dialog to edit details for the specified agent
     */
    public boolean showAgentEditDialog(Agent agent) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/AgentEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            
            // Create the dialog stage
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Agent");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            
            // Set the agent into the controller
            AgentEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setAgent(agent);
            
            // Show the diaog and wait until the user closes it
            dialogStage.showAndWait();
            
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public void showEditAgentScene(Agent agent) throws IOException {
        // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/AgentEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            
            rootLayout.setCenter(page);
            // Set the agent into the controller
            AgentEditDialogController controller = loader.getController();
            controller.setMainApp(this);
            controller.setAgent(agent);
            controller.setOldAgent(null);
            //controller.setEdit(edit);
    }
    
    public void showEditAgentScene(Agent newAgent, Agent oldAgent) throws IOException {
        // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/AgentEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            
            rootLayout.setCenter(page);
            // Set the agent into the controller
            AgentEditDialogController controller = loader.getController();
            controller.setMainApp(this);
            controller.setAgent(newAgent);
            controller.setOldAgent(oldAgent);
            //controller.setEdit(edit);
    }
    
    /**
     * Returns the main stage
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
