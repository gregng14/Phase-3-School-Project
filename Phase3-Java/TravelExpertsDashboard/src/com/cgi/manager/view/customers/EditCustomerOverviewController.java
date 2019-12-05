 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* EditCustomer Controller
 * Contains Controller methods for the EditCustomer view
 *
 * Author: Greg Ng
 * Date Created: September 20, 2016
 * Last Updated: October 04, 2016
 * Assignment: PROJ207 - Phase Three - Java
 */

package com.cgi.manager.view.customers;

import com.cgi.manager.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import com.cgi.manager.model.AgentDB;
import com.cgi.manager.model.Customer;
import com.cgi.manager.model.CustomerDB;
import com.cgi.manager.view.ViewController;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ButtonType;



/**
 *
 * @author 723243
 */
public class EditCustomerOverviewController implements ViewController{
    
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
    private ComboBox cboAgentId;
    
    private MainApp main;
    private Customer customer;
    private Customer oldCustomer;
    
    private ObservableList<Integer> listOfAgents = FXCollections.observableArrayList();
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    
    @FXML
    private void initialize() {
        try {
            customer = new Customer();
            listOfAgents = AgentDB.getAgentIds();
            
            cboAgentId.setItems(listOfAgents);
        } catch (Exception e) {
            System.out.println("Failed to load the agency IDs");
        }
        
    }
 
    /**
     * Sets the agent to be edited in the dialog.
     * 
     * @param agent
     */
    public void setCustomer(Customer customer) {
        
        this.customer = customer;
        
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
            if (cboAgentId.getItems().contains(customer.getAgentId())){
                cboAgentId.setValue(customer.getAgentId());
            }
            else{
                cboAgentId.getSelectionModel().selectFirst();
            }
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
            cboAgentId.getSelectionModel().selectFirst();
        }        
    }
    
    public void setOldCustomer(Customer oldCustomer) {
        this.oldCustomer = oldCustomer;
    }
    
    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            customer.setCustFirstName(custFirstNameTextField.getText());
            customer.setCustLastName(custLastNameTextField.getText());
            customer.setCustAddress(custAddressTextField.getText());
            customer.setCustCity(custCityTextField.getText());
            customer.setCustProv(custProvinceTextField.getText());
            customer.setCustPostal(custPostalCodeTextField.getText());
            customer.setCustCountry(custCountryTextField.getText());
            customer.setCustHomePhone(custHomePhoneTextField.getText());
            customer.setCustBusPhone(custBusinessPhoneTextField.getText());
            customer.setCustEmail(custEmailTextField.getText());
            customer.setAgentId((int)cboAgentId.getValue());
            
            try {
                if (oldCustomer == null) {
                    CustomerDB.addNewCustomer(customer);
                } else {
                    CustomerDB.editCustomer(customer, oldCustomer);
                }
            } catch (Exception e) {
                System.out.println("Adding customer to DB in handleOk failed");
                e.printStackTrace();
            }
            try {
                main.showCustomerScene();
            } catch (Exception e) {
                System.out.println("Show customer scene failed");
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        try {
                main.showCustomerScene();
            } catch (Exception e) {
                System.out.println("Show customer scene failed");
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
        if ((custFirstNameTextField.getText() == null || custFirstNameTextField.getText().length() == 0) || (custFirstNameTextField.getText().length() > 25)) {
            errorMessage += "First name has to be between 1 and 25 characters.\n";
        }
        
        if ((custLastNameTextField.getText() == null || custLastNameTextField.getText().length() == 0) || (custLastNameTextField.getText().length() > 25)) {
            errorMessage += "Last name has to be between 1 and 25 characters.\n";
        }
        
        if ((custAddressTextField.getText() == null || custAddressTextField.getText().length() == 0) || (custAddressTextField.getText().length() > 75)) {
            errorMessage += "Address has to be between 1 and 75 characters.\n";
        }
        
        if ((custCityTextField.getText() == null || custCityTextField.getText().length() == 0) || (custCityTextField.getText().length() > 50)) {
            errorMessage += "City has to be between 1 and 50 characters.\n";
        }
        
        if ((custProvinceTextField.getText() == null || custProvinceTextField.getText().length() == 0) || (custProvinceTextField.getText().length() > 2)) {
            errorMessage += "Province has to be a two-letter abbreviation.\n";
        }
        
        if ((custPostalCodeTextField.getText() == null || custPostalCodeTextField.getText().length() == 0) || (custPostalCodeTextField.getText().length() > 7)) {
            errorMessage += "Postal Code has to be between 1 and 7 characters.\n";
        }
        
        if ((custCountryTextField.getText() == null || custCountryTextField.getText().length() == 0) || (custCountryTextField.getText().length() > 25)) {
            errorMessage += "Country has to be between 1 and 25 characters.\n";
        }
        
        if ((custHomePhoneTextField.getText() == null || custHomePhoneTextField.getText().length() == 0) || (custHomePhoneTextField.getText().length() > 20)) {
            errorMessage += "Phone number has to be between 1 and 20 characters long.\n";
        } else {
            phoneNumMatcher = phoneNumPattern.matcher(custHomePhoneTextField.getText());
            if (!phoneNumMatcher.matches()) {
                errorMessage += "Phone number not in the correct format: (111) 111-1111.\n";
            }
        }
        
        if ((custBusinessPhoneTextField.getText() == null || custBusinessPhoneTextField.getText().length() == 0) || (custBusinessPhoneTextField.getText().length() > 20)) {
            errorMessage += "Phone number has to be between 1 and 20 characters long.\n";
        } else {
            phoneNumMatcher = phoneNumPattern.matcher(custBusinessPhoneTextField.getText());
            if (!phoneNumMatcher.matches()) {
                errorMessage += "Phone number not in the correct format: (111) 111-1111.\n";
            }
        }
        
        if ((custEmailTextField.getText() == null || custEmailTextField.getText().length() == 0) || (custEmailTextField.getText().length() > 50)) {
            errorMessage += "Email has to be between 1 and 50 characters.\n";
        } else {
            emailMatcher = emailPattern.matcher(custEmailTextField.getText());
            if (!emailMatcher.matches()) {
                errorMessage += "Email format is not correct. Should be in the format username@domain.domaincode.\n"; 
            }       
        }
        
        if (errorMessage.length() > 0) {
            Alert alert = new Alert(AlertType.ERROR, errorMessage, ButtonType.OK);
            alert.setTitle("Input Error");
            alert.setHeaderText("Please correct invalid fields");
            alert.showAndWait();
            
            return false;
        }
        
        return true;
    }
    
    public void setMainApp(MainApp main) {
        this.main = main;
    }
    
}
