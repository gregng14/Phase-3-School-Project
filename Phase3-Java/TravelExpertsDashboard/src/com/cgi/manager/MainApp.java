/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cgi.manager;

import com.cgi.manager.model.Agent;
import com.cgi.manager.model.Customer;
import com.cgi.manager.view.ViewController;
import com.cgi.manager.view.agents.AgentEditDialogController;
import com.cgi.manager.view.customers.EditCustomerOverviewController;
import com.cgi.manager.view.packages.EditPackageOverviewController;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Charlene Castillo
 */
public class MainApp extends Application {
    
    private Stage primaryStage;
    private static BorderPane mainLayout;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Travel Experts Manager");
        this.primaryStage.getIcons().add(new Image(MainApp.class.getResourceAsStream("yellowicon_64x64.png")));
        //this.primaryStage.initStyle(StageStyle.UTILITY);
        showMainView();
        showMainPage();
//      showAgentScene();
    }
    
    private void showMainView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/MainView.fxml"));
        mainLayout = (BorderPane) loader.load();        
        
        ViewController controller = loader.getController();
        controller.setMainApp(this);
        Scene scene = new Scene(mainLayout);
        scene.getStylesheets().add(getClass().getResource("view/material-fx-v0_3.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void showMainPage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/MainPage.fxml"));
        AnchorPane mainPage = (AnchorPane) loader.load();
        mainLayout.setCenter(mainPage);
    } 
    
    
    public void showPackageScene() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/packages/PackageOverview.fxml"));
        AnchorPane packageOverview =(AnchorPane) loader.load();
        mainLayout.setCenter(packageOverview);
        ViewController controller = loader.getController();
        controller.setMainApp(this);
    }
    
    public void showEditPackageScene(int edit) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/packages/EditPackageOverview.fxml"));
        AnchorPane packageOverview =(AnchorPane) loader.load();
        mainLayout.setCenter(packageOverview); 
        EditPackageOverviewController controller = loader.getController();
        controller.setMainApp(this);
        controller.setEdit(edit);
    }
    
    public void showAgentScene() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/agents/AgentsManager.fxml"));
        AnchorPane agentOverview =(AnchorPane) loader.load();
        mainLayout.setCenter(agentOverview);     
        ViewController controller = loader.getController();
        controller.setMainApp(this);
    }
    
    public void showEditAgentScene(Agent agent) throws IOException {
        // Load the fxml file and create a new stage for the popup dialog.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/agents/AgentEditDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        mainLayout.setCenter(page);
        // Set the agent into the controller
        AgentEditDialogController controller = loader.getController();
        controller.setMainApp(this);
        controller.setAgent(agent);
        controller.setOldAgent(null);
    }
    
    public void showEditAgentScene(Agent newAgent, Agent oldAgent) throws IOException {
        // Load the fxml file and create a new stage for the popup dialog.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/agents/AgentEditDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        mainLayout.setCenter(page);
        // Set the agent into the controller
        AgentEditDialogController controller = loader.getController();
        controller.setMainApp(this);
        controller.setAgent(newAgent);
        controller.setOldAgent(oldAgent);
    }
    
    public void showCustomerScene() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/customers/CustomerOverview.fxml"));
        AnchorPane customerOverview =(AnchorPane) loader.load();
        mainLayout.setCenter(customerOverview);  
        ViewController controller = loader.getController();
        controller.setMainApp(this);
    }
    
    public void showEditCustomerScene(Customer customer) throws IOException {
        // Load the fxml file and create a new stage for the popup dialog.
        this.showEditCustomerScene(customer,null);
    }
    
    public void showEditCustomerScene(Customer customer, Customer oldCustomer) throws IOException {
        // Load the fxml file and create a new stage for the popup dialog.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/customers/EditCustomerOverview.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        mainLayout.setCenter(page);
        // Set the agent into the controller
        EditCustomerOverviewController controller = loader.getController();
        controller.setMainApp(this);
        controller.setCustomer(customer);
        controller.setOldCustomer(oldCustomer);
    }
    
    public Stage getPrimaryStage(){
        return this.primaryStage;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }


    
}
