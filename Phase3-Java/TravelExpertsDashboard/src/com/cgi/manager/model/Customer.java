/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cgi.manager.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Greg Ng
 */
public class Customer {
    private final IntegerProperty customerId = new SimpleIntegerProperty();
    private final StringProperty custFirstName = new SimpleStringProperty();
    private final StringProperty custLastName = new SimpleStringProperty();
    private final StringProperty custAddress = new SimpleStringProperty();
    private final StringProperty custCity = new SimpleStringProperty();
    private final StringProperty custProv = new SimpleStringProperty();
    private final StringProperty custPostal = new SimpleStringProperty();
    private final StringProperty custCountry = new SimpleStringProperty();
    private final StringProperty custHomePhone = new SimpleStringProperty();
    private final StringProperty custBusPhone = new SimpleStringProperty();
    private final StringProperty custEmail = new SimpleStringProperty();
    private final IntegerProperty agentId = new SimpleIntegerProperty();

    /**
     * @return the customerId
     */
    public int getCustomerId() {
        return this.customerId.get();
    }
    
    public void setCustomerId(int customerId) {
        this.customerId.set(customerId);
    }

    public IntegerProperty customerIdProperty() {
        return customerId;
    }
    
    /**
     * @return the custFirstName
     */
    public String getCustFirstName() {
        return this.custFirstName.get();
    }

    public void setCustFirstName(String custFirstName) {
        this.custFirstName.set(custFirstName);
    }
    
    public StringProperty custFirstNameProperty() {
        return custFirstName;
    }
    
    /**
     * @return the custLasttName
     */
    public String getCustLastName() {
        return this.custLastName.get();
    }
    
    public void setCustLastName(String custLastName) {
        this.custLastName.set(custLastName);
    }
    
    public StringProperty custLastNameProperty() {
        return custLastName;
    }

    /**
     * @return the custAddress
     */
    public String getCustAddress() {
        return this.custAddress.get();
    }

    public void setCustAddress(String custAddress) {
        this.custAddress.set(custAddress);
    }
    
    public StringProperty getCustAddressProperty() {
        return custAddress;
    }
    
    /**
     * @return the custCity
     */
    public String getCustCity() {
        return this.custCity.get();
    }
    
    public void setCustCity(String custCity) {
        this.custCity.set(custCity);
    }
    
    public StringProperty getCustCityProperty() {
        return custCity;
    }

    /**
     * @return the custProv
     */
    public String getCustProv() {
        return this.custProv.get();
    }
    
    public void setCustProv(String custProv) {
        this.custProv.set(custProv);
    }
    
    public StringProperty getCustProvProperty() {
        return custProv;
    }

    /**
     * @return the custPostal
     */
    public String getCustPostal() {
        return this.custPostal.get();
    }
    
    public void setCustPostal(String custPostal) {
        this.custPostal.set(custPostal);
    }
    
    public StringProperty getCustPostalProperty() {
        return custPostal;
    }

    /**
     * @return the custCountry
     */
    public String getCustCountry() {
        return this.custCountry.get();
    }
    
    public void setCustCountry(String custCountry) {
        this.custCountry.set(custCountry);
    }
    
    public StringProperty getCustCountryProperty() {
        return custCountry;
    }

    /**
     * @return the custHomePhone
     */
    public String getCustHomePhone() {
        return this.custHomePhone.get();
    }

    public void setCustHomePhone(String custHomePhone) {
        this.custHomePhone.set(custHomePhone);
    }
    
    public StringProperty getCustHomePhoneProperty() {
        return custHomePhone;
    }
    
    /**
     * @return the custBusPhone
     */
    public String getCustBusPhone() {
        return this.custBusPhone.get();
    }

    public void setCustBusPhone(String custBusPhone) {
        this.custBusPhone.set(custBusPhone);
    }
    
    public StringProperty getCustBusPhoneProperty() {
        return custBusPhone;
    }
    
    /**
     * @return the custEmail
     */
    public String getCustEmail() {
        return this.custEmail.get();
    }

    public void setCustEmail(String custEmail) {
        this.custEmail.set(custEmail);
    }
    
    public StringProperty getCustEmailProperty() {
        return custEmail;
    }
    
    /**
     * @return the agentId
     */
    public Integer getAgentId() {
        return this.agentId.get();
    }
    
    public void setAgentId(int agentId) {
        this.agentId.set(agentId);
    }
    
    public IntegerProperty getAgentIdProperty() {
        return agentId;
    }
    
    public static Customer cloneCustomer(Customer customer) {
        Customer cloningCustomer = new Customer();
        
        cloningCustomer.setCustomerId(customer.getCustomerId());
        cloningCustomer.setCustFirstName(customer.getCustFirstName());
        cloningCustomer.setCustLastName(customer.getCustLastName());
        cloningCustomer.setCustAddress(customer.getCustAddress());
        cloningCustomer.setCustCity(customer.getCustCity());
        cloningCustomer.setCustProv(customer.getCustProv());
        cloningCustomer.setCustPostal(customer.getCustPostal());
        cloningCustomer.setCustCountry(customer.getCustCountry());
        cloningCustomer.setCustHomePhone(customer.getCustHomePhone());
        cloningCustomer.setCustBusPhone(customer.getCustBusPhone());
        cloningCustomer.setCustEmail(customer.getCustEmail());
        cloningCustomer.setAgentId(customer.getAgentId());
    
        return cloningCustomer;
    }
}
