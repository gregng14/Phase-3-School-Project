/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cgi.manager.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Greg Ng
 */
public class PkgData {
    private ObservableList<PackageName> packageList = FXCollections.observableArrayList();
    private PackageClass selectedPackage;
    
    public static PkgData pkgData = new PkgData();
     
    public PkgData(){
    }
    
    public ObservableList<PackageName> getPackageList(){
        packageList = PackageDB.getPackageName();
        return this.packageList;
    }
    
    public void setSelectedPackage(PackageClass selectedPackage){
        this.selectedPackage = selectedPackage;
    }
    
    public PackageClass getSelectedPackage(){
        return this.selectedPackage;
    }
}
