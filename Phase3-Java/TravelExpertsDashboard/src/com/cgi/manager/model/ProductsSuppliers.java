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
public class ProductsSuppliers {
    
    private final IntegerProperty productSupplierId = new SimpleIntegerProperty();
    private final IntegerProperty productId = new SimpleIntegerProperty();
    private final IntegerProperty supplierId = new SimpleIntegerProperty();
    private final StringProperty prodName = new SimpleStringProperty();
    private final StringProperty supName = new SimpleStringProperty();
    
    
    public int getProductSupplierId() {
        return productSupplierId.get();
    }

    public void setProductSupplierId(int productSupplierId) {
        this.productSupplierId.set(productSupplierId);
    }
    
    public IntegerProperty productSupplierIdProperty() {
        return productSupplierId;
    }
    
    public int getProductId() {
        return productId.get();
    }

    public void setProductId(int productId) {
        this.productId.set(productId);
    }
    
    public IntegerProperty productIdProperty() {
        return productId;
    }
    
    public int getSupplierId() {
        return supplierId.get();
    }

    public void setSupplierId(int SupplierId) {
        this.supplierId.set(SupplierId);
    }
    
    public IntegerProperty supplierIdProperty() {
        return supplierId;
    }
    
    public String getProdName() {
        return prodName.get();
    }

    public void setProdName(String prodName) {
        this.prodName.set(prodName);
    }

    public StringProperty prodNameProperty() {
        return prodName;
    }
    
    public String getSupName() {
        return supName.get();
    }

    public void setSupName(String supName) {
        this.supName.set(supName);
    }

    public StringProperty supNameProperty() {
        return supName;
    }
}
