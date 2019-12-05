/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cgi.manager.model;

import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;


/**
 * Model class for a PackageClass
 * 
 * @author Greg Ng
 */
public class PackageClass {
    
    private final IntegerProperty packageId = new SimpleIntegerProperty();
    private final StringProperty pkgName = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> pkgStartDate = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> pkgEndDate = new SimpleObjectProperty<>();
    private final StringProperty pkgDesc = new SimpleStringProperty();
    private final FloatProperty pkgBasePrice = new SimpleFloatProperty();
    private final FloatProperty pkgAgencyCommission = new SimpleFloatProperty();
//    private ListProperty<Integer> productSupplierId = new SimpleListProperty<>();

    public final Integer getPackageId() {
        return packageId.get();
    }

    public final void setPackageId(Integer packageId) {
        this.packageId.set(packageId);
    }
    
    public IntegerProperty packageIdProperty() {
        return packageId;
    }
    
    public final String getPkgName() {
        return pkgName.get();
    }

    public final void setPkgName(String pkgName) {
        this.pkgName.set(pkgName);
    }

    public StringProperty pkgNameProperty() {
        return pkgName;
    }
    
    public final LocalDate getPkgStartDate() {
        return pkgStartDate.get();
    }

    public final void setPkgStartDate(LocalDate pkgStartDate) {
        this.pkgStartDate.set(pkgStartDate);
    }

    public ObjectProperty<LocalDate> pkgStartDateProperty() {
        return pkgStartDate;
    }
    
    public final LocalDate getPkgEndDate() {
        return pkgEndDate.get();
    }

    public final void setPkgEndDate(LocalDate pkgEndDate) {
        this.pkgEndDate.set(pkgEndDate);
    }

    public ObjectProperty<LocalDate> pkgEndDateProperty() {
        return pkgEndDate;
    }
    
    public final String getPkgDesc() {
        return pkgDesc.get();
    }

    public final void setPkgDesc(String pkgDesc) {
        this.pkgDesc.set(pkgDesc);
    }

    public StringProperty pkgDescProperty() {
        return pkgDesc;
    }
    
    public final Float getPkgBasePrice() {
        return pkgBasePrice.get();
    }

    public final void setPkgBasePrice(Float pkgBasePrice) {
        this.pkgBasePrice.set(pkgBasePrice);
    }
    
    public FloatProperty pkgBasePriceProperty() {
        return pkgBasePrice;
    }
    
    public final Float getPkgAgencyCommission() {
        return pkgAgencyCommission.get();
    }

    public final void setPkgAgencyCommission(Float pkgAgencyCommission) {
        this.pkgAgencyCommission.set(pkgAgencyCommission);
    }
    
    public FloatProperty pkgAgencyCommissionProperty() {
        return pkgAgencyCommission;
    }

//    public ObservableList<Integer> getProductSupplierId() {
//        return productSupplierId.get();
//    }
//
//    public void setProductSupplierId(ObservableList<Integer> productSupplierId) {
//        this.productSupplierId.set(productSupplierId);
//    }
//
//    public ListProperty<Integer> productSupplierIdProperty() {
//        return this.productSupplierId;
//    }
}
