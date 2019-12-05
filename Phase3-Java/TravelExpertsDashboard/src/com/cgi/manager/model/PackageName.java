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
public class PackageName {
    private StringProperty pkgName = new SimpleStringProperty();
    private IntegerProperty packageId = new SimpleIntegerProperty();

    /**
     * @return the pkgName
     */
    public final String getPkgName() {
        return pkgName.get();
    }

    /**
     * @param pkgName the pkgName to set
     */
    public final void setPkgName(String pkgName) {
        this.pkgName.set(pkgName);
    }

    public StringProperty pkgNameProperty(){
        return this.pkgName;
    }
    
    /**
     * @return the packageId
     */
    public final int getPackageId() {
        return packageId.get();
    }

    /**
     * @param packageId the packageId to set
     */
    public final void setPackageId(int packageId) {
        this.packageId.set(packageId);
    }
    
    public IntegerProperty packageIdProperty(){
        return this.packageId;
    }
}
