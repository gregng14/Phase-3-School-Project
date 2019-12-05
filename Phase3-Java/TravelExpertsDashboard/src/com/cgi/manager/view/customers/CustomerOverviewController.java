/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* CustomerOverview Controller
 * Contains Controller methods for the CustomerOverview view
 *
 * Author: Greg Ng
 * Date Created: September 20, 2016
 * Last Updated: September 20, 2016
 * Assignment: PROJ207 - Phase Three - Java
 */
package com.cgi.manager.view.customers;

import com.cgi.manager.MainApp;
import com.cgi.manager.model.Customer;
import com.cgi.manager.model.CustomerDB;
import com.cgi.manager.view.ViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 *
 * @author 723243
 */
//public class CustomerOverviewController implements Initializable {
public class CustomerOverviewController implements ViewController{
    //CustFirstName, CustLastName, CustAddress, CustCity, CustProv, CustPostal, CustCountry, CustHomePhone, CustBusPhone, CustEmail, AgentId
    @FXML
    private TableView<Customer> customerTable;
    @FXML
    private TableColumn<Customer, String> firstNameColumn;
    @FXML
    private TableColumn<Customer, String> lastNameColumn;
    
    @FXML
    private TextField custIdTextField;
    @FXML
    private TextField custFirstNameTextField;
    @FXML
    private TextField custLastNameTextField;
    @FXML
    private TextField custAddressTextField;
    @FXML
    private TextField custCityTextField;
    @FXML
    private TextField custProvinceTextField;
    @FXML
    private TextField custPostalCodeTextField;
    @FXML
    private TextField custCountryTextField;
    @FXML
    private TextField custHomePhoneTextField;
    @FXML
    private TextField custBusinessPhoneTextField;
    @FXML
    private TextField custEmailTextField;
    @FXML
    private TextField custAgentIdTextField;
    
    @FXML
    private CheckBox chkActive;
    
    // Reference to the main application.
    private MainApp mainApp;
    
    private ObservableList<Customer> customerData = FXCollections.observableArrayList();
    
    public CustomerOverviewController() {
        //Get Agent data initially
        try {
            customerData = CustomerDB.getCustomers();
        } catch (Exception e) {
            System.out.println("Error in retrieving customers list");
            e.printStackTrace();
        }
    }
    
    @FXML
    private void initialize() {
        // Initialize table with info

        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().custFirstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().custLastNameProperty());

        
        // Clear agent details
        showCustomerDetails(null);
        
        // Listen for selection change in the table, and show the selected Customer
        customerTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldvalue, newValue) -> showCustomerDetails(newValue));
        
        // Listen for selection change in the check box,  and show Customers with active or inactive agents
        chkActive.selectedProperty().setValue(Boolean.FALSE);
        chkActive.selectedProperty().addListener((observable,oldvalue,value)-> getCustomers(value));
    }
    
    private void getCustomers(boolean value){
        try {
            if(!value){
                customerData = CustomerDB.getCustomers();
            }
            else{
                customerData = CustomerDB.getCustomersWithInactiveAgent();
            }
            customerTable.setItems(customerData);
        } catch (Exception e) {
            System.out.println("Error in retrieving customers list");
            e.printStackTrace();
        }
    }
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        customerTable.setItems(customerData);
    }
    
    private void showCustomerDetails(Customer customer) {
        if (customer != null) {
            // Fill the labels with info from the Customer object
            custIdTextField.setText(Integer.toString(customer.getCustomerId()));
            custFirstNameTextField.setText(customer.getCustFirstName());
            custLastNameTextField.setText(customer.getCustLastName());
            custAddressTextField.setText(customer.getCustAddress());
            custCityTextField.setText(customer.getCustCity());
            custProvinceTextField.setText(customer.getCustProv());
            custPostalCodeTextField.setText(customer.getCustPostal());
            custCountryTextField.setText(customer.getCustCountry());
            custHomePhoneTextField.setText(customer.getCustHomePhone());
            custBusinessPhoneTextField.setText(customer.getCustBusPhone());
            custEmailTextField.setText(customer.getCustEmail());
            custAgentIdTextField.setText(Integer.toString(customer.getAgentId()));
        } else {
            custIdTextField.setText("");
            custFirstNameTextField.setText("");
            custLastNameTextField.setText("");
            custAddressTextField.setText("");
            custCityTextField.setText("");
            custProvinceTextField.setText("");
            custPostalCodeTextField.setText("");
            custCountryTextField.setText("");
            custHomePhoneTextField.setText("");
            custBusinessPhoneTextField.setText("");
            custEmailTextField.setText("");
            custAgentIdTextField.setText("");
        }
    }
    
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new agent.
     */
    @FXML
    private void handleNewCustomer() {
        Customer tempCustomer = new Customer();
        
        try {
            mainApp.showEditCustomerScene(tempCustomer);
        } catch (Exception e) {
            System.out.println("Error in creating new customer");
            e.printStackTrace();
        }
    }
    
    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected agent.
     */
    @FXML
    private void handleEditCustomer() {
        // For editing
        Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        
        if (selectedCustomer != null) {
            try {
                // The information for the customer prior to editing
                Customer oldCustomer = Customer.cloneCustomer(selectedCustomer);
                
                mainApp.showEditCustomerScene(selectedCustomer, oldCustomer);
            } catch (Exception e) {
                System.out.println("handleEditCustomer() failed");
                e.printStackTrace();
            }
            
        } else {
        // Nothing selected
        Alert alert = new Alert(AlertType.WARNING);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("No Selection");
        alert.setHeaderText("No Customer Selected");
        alert.setContentText("Please select an customer in the table.");
        
        alert.showAndWait();
        }
    }   
    
}
