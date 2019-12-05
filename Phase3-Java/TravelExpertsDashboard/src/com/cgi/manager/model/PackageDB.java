/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cgi.manager.model;

import com.cgi.manager.util.DateUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



/**
 *
 * @author Greg Ng
 */
public class PackageDB {

    private static final String INSERT_PRODUCTS_TO_PACKAGES = "INSERT INTO Packages_Products_Suppliers "
                                            +"(PackageId,ProductSupplierId) "
                                            + "VALUES ( ?, ?)";
    private static final String DELETE_PACKAGES = "DELETE FROM Packages WHERE PackageId = ?";
    private static final String DELETE_PACKAGES_PRODUCTS_SUPPLIERS = "DELETE FROM Packages_Products_Suppliers WHERE PackageId = ?";
    private static final String GET_NEW_PACKAGE_ID = "SELECT PackageId FROM Packages ORDER BY PackageId DESC LIMIT 1";

    public static ObservableList<PackageName> getPackageName()
    {
        ObservableList<PackageName> packageNames = FXCollections.observableArrayList();
        PackageName packageName;
        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();
            String query = "SELECT PackageId, PkgName FROM Packages";
            PreparedStatement stmt = conn.prepareStatement(query);
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next())
            {
                packageName = new PackageName();
                packageName.setPackageId(rs.getInt(1));
                packageName.setPkgName(rs.getString(2));
                packageNames.add(packageName);
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PackageDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try{
                if(conn!=null)
                    conn.close();
            }
            catch(SQLException ex){
            Logger.getLogger("Could not close Connection", ex.getMessage());
            }
        }
        return packageNames;
    }
        
    public static PackageClass getPackageById(int id) {
        PackageClass pack = null;
        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();
            String query = "SELECT * FROM Packages WHERE PackageId = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next())
            {
                pack = new PackageClass();
                pack.setPackageId(id);
                pack.setPkgName(rs.getString(2));
                pack.setPkgStartDate(rs.getDate(3).toLocalDate());
                pack.setPkgEndDate( rs.getDate(4).toLocalDate());
                pack.setPkgDesc(rs.getString(5));
                pack.setPkgBasePrice(rs.getFloat(6));
                pack.setPkgAgencyCommission(rs.getFloat(7));
            }
            else
                pack = new PackageClass();
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PackageDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PackageDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try{
                if(conn!=null)
                    conn.close();
            }
            catch(SQLException ex){
            Logger.getLogger("Could not close Connection", ex.getMessage());
            }
        }
        return pack;
    }
    
    public static ObservableList<Integer> getProductsByPackageId(int id) {
        Connection conn = null;
        ObservableList<Integer> packageProducts = FXCollections.observableArrayList();
        try {
            conn = ConnectionDB.getConnection();
            
            String query = "SELECT ProductSupplierId "
                    + "FROM Packages_Products_Suppliers "
                    + "WHERE PackageId= ?";
            
            
            
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next())
            {
                packageProducts.add(rs.getInt(1));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PackageDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PackageDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try{
                if(conn!=null)
                    conn.close();
            }
            catch(SQLException ex){
            Logger.getLogger("Could not close Connection", ex.getMessage());
            }
        }
        return packageProducts;
    }
    
    public static ObservableList<ProductsSuppliers> getAllProducts() {
        Connection conn = null;
        ObservableList<ProductsSuppliers> packageProducts = FXCollections.observableArrayList();
        try {
            conn = ConnectionDB.getConnection();
            
            String query = "SELECT ps.ProductSupplierId, "
                    + "ps.ProductId, "
                    + "ps.SupplierId, "
                    + "ProdName, "
                    + "SupName "
                    + "FROM "
                    + "Products_Suppliers as ps, "
                    + "Products as p, "
                    + "Suppliers as s "
                    + "WHERE ps.ProductId=p.ProductId "
                    + "AND ps.SupplierId=s.SupplierId ";

            ProductsSuppliers packageProduct;
            
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next())
            {
                packageProduct = new ProductsSuppliers();
                packageProduct.setProductSupplierId(rs.getInt(1));
                packageProduct.setProductId(rs.getInt(2));
                packageProduct.setSupplierId(rs.getInt(3));
                packageProduct.setProdName(rs.getString(4));
                packageProduct.setSupName(rs.getString(5));
                packageProducts.add(packageProduct);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PackageDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PackageDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try{
                if(conn!=null)
                    conn.close();
            }
            catch(SQLException ex){
            Logger.getLogger("Could not close Connection", ex.getMessage());
            }
        }
        return packageProducts;
    }
    
    public static void updatePackage(PackageClass pkg,ObservableList<ProductsSuppliers> products) 
    {
        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();
            String sql = "UPDATE Packages "
                    + "SET PkgName = ?, "
                    + "PkgStartDate = ?, "
                    + "PkgEndDate = ?, "
                    + "PkgDesc = ?, "
                    + "PkgBasePrice = ?, "
                    + "PkgAgencyCommission = ? "
                    + "WHERE PackageId = ?";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,pkg.getPkgName());
            stmt.setString(2,DateUtil.format(pkg.getPkgStartDate()));
            stmt.setString(3,DateUtil.format(pkg.getPkgEndDate()));
            stmt.setString(4,pkg.getPkgDesc());
            stmt.setFloat(5,pkg.getPkgBasePrice());
            stmt.setFloat(6,pkg.getPkgAgencyCommission());
            stmt.setInt(7, pkg.getPackageId());
            
            int numRows;
            conn.setAutoCommit(false);
            numRows = stmt.executeUpdate();
            if (numRows == 0)
            {
                System.out.println("update failed");
            }
            else
            {
                System.out.println("updated " + numRows + " row(s)");
            }
            
            PackageDB.insertProductsInPackage(conn,pkg.getPackageId(),products);
            conn.commit();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PackageDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PackageDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try{
                if(conn!=null)
                    conn.close();
            }
            catch(SQLException ex){
            Logger.getLogger("Could not close Connection", ex.getMessage());
            }
        }
    }
    
    public static void insertPackage(PackageClass pkg, ObservableList<ProductsSuppliers> products) 
    {
        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();
            String sql = "INSERT INTO Packages "
                    + "( PkgName, PkgStartDate, PkgEndDate, "
                    + "PkgDesc, PkgBasePrice, PkgAgencyCommission) "
                    + "VALUES (?,?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,pkg.getPkgName());
            stmt.setString(2,DateUtil.format(pkg.getPkgStartDate()));
            stmt.setString(3,DateUtil.format(pkg.getPkgEndDate()));
            stmt.setString(4,pkg.getPkgDesc());
            stmt.setFloat(5,pkg.getPkgBasePrice());
            stmt.setFloat(6,pkg.getPkgAgencyCommission());
            
            int numRows;
            conn.setAutoCommit(false);
            numRows = stmt.executeUpdate();
            if (numRows == 0)
            {
                System.out.println("update failed");
            }
            else
            {
                System.out.println("updated " + numRows + " row(s)");
            }
            int id = PackageDB.getNewPackageId(conn);
            PackageDB.insertProductsInPackage(conn,id,products);
            conn.commit();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PackageDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PackageDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try{
                if(conn!=null)
                    conn.close();
            }
            catch(SQLException ex){
            Logger.getLogger("Could not close Connection", ex.getMessage());
            }
        }
    }

    private static int getNewPackageId(Connection conn)
            throws ClassNotFoundException, SQLException
    {
        PreparedStatement stmt = conn.prepareStatement(GET_NEW_PACKAGE_ID);
        ResultSet rs = stmt.executeQuery();
            int id = 0;
            if(rs.next())
            {
                id = rs.getInt(1);
            }
            if(id==0)
            {
                System.out.println("System failed to retrieve the new package id.");
            }
        return id;
    }
    
    private static void insertProductsInPackage(Connection conn,int id, ObservableList<ProductsSuppliers> products)
            throws ClassNotFoundException, SQLException
    {
        PreparedStatement stmt = conn.prepareStatement(DELETE_PACKAGES_PRODUCTS_SUPPLIERS);
        stmt.setInt(1,id);
        System.out.println(stmt.executeUpdate());
        stmt = conn.prepareStatement(INSERT_PRODUCTS_TO_PACKAGES);
        
        int numPackagesProductsSuppliersRows = 0;
        
        for(ProductsSuppliers product : products){
            stmt.setInt(1,id);
            stmt.setInt(2,product.getProductSupplierId());
            numPackagesProductsSuppliersRows += stmt.executeUpdate();
        }
        System.out.println("updated " + numPackagesProductsSuppliersRows + " rows(s)");
    }
    
    public static void deletePackage(int id){
        Connection conn = null;
        try{
            conn = ConnectionDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(DELETE_PACKAGES);
            stmt.setInt(1, id);
            
            int numRows;
            conn.setAutoCommit(false);
            numRows = stmt.executeUpdate();
            if (numRows == 0)
            {
                System.out.println("delete package failed");
            }
            else
            {
                System.out.println("deleted " + numRows + " row(s)");
            }
            
            stmt = conn.prepareStatement(DELETE_PACKAGES_PRODUCTS_SUPPLIERS);
            stmt.setInt(1, id);
            
            numRows = stmt.executeUpdate();

            System.out.println("delete " + numRows + " row(s)");
            conn.commit();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PackageDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PackageDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try{
                if(conn!=null)
                    conn.close();
            }
            catch(SQLException ex){
            Logger.getLogger("Could not close Connection", ex.getMessage());
            }
        }
    }
}

//SELECT ps.ProductSupplierId, ps.ProductId, ps.SupplierId, ProdName, SupName FROM Products_Suppliers as ps, Products as p, Suppliers as s WHERE ps.ProductId=p.ProductId AND ps.SupplierId=s.SupplierId ;