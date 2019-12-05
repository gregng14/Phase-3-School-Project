/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cgi.manager.model;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Greg Ng
 */
public class ProdData {
    
    private ObservableList<ProductsSuppliers> allPackagedProducts = FXCollections.observableArrayList();
    private ObservableList<ProductsSuppliers> packagedProducts = FXCollections.observableArrayList();
    private ObservableList<ProductsSuppliers> nonPackagedProducts = FXCollections.observableArrayList();
    private ObservableList<Integer> pkgProducts = FXCollections.observableArrayList();
    //private ObservableList
    
    public ProdData(int id) {
        allPackagedProducts = PackageDB.getAllProducts();
        pkgProducts = PackageDB.getProductsByPackageId(id);
        packagedProducts.clear();
        nonPackagedProducts.clear();
        allPackagedProducts.sort((a,b) -> 
                a.getProductSupplierId() < b.getProductSupplierId()? -1: 
                        a.getProductSupplierId() == b.getProductSupplierId() ? 0 : 1);
        pkgProducts.sort((a,b) -> a < b ? -1 : Objects.equals(a, b) ? 0 : 1);
        nonPackagedProducts.addAll(allPackagedProducts);
        
        int i = 0;
        int j = 0;
        int a = allPackagedProducts.size();
        int b = pkgProducts.size();
        ProductsSuppliers x;
        int y;
        while (i<a && j<b)
        {
            x = allPackagedProducts.get(i);
            y = pkgProducts.get(j);
            
            if (x.getProductSupplierId() == y)
            {
                nonPackagedProducts.remove(x);
                packagedProducts.add(x);
//                a--;
                i++;
                j++;
            }
            else if (x.getProductSupplierId() < y)
            {
                i++;
            }
            else
                j++;
            
        }
    }
    
    public ObservableList<ProductsSuppliers> getNonPackagedProducts(){
        return this.nonPackagedProducts;
    }
    public ObservableList<ProductsSuppliers> getPackagedProducts(){
        return this.packagedProducts;
    }
    
    public ObservableList<String> getProductNames(){
        HashSet productNames = new HashSet();
        
        for (ProductsSuppliers name : nonPackagedProducts)
        {
            productNames.add(name.getProdName());
        }
        return FXCollections.observableArrayList(productNames);
    }
}
