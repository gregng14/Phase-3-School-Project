/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cgi.manager.view.packages;

import com.cgi.manager.MainApp;
import com.cgi.manager.model.PackageClass;
import com.cgi.manager.model.PackageDB;
import com.cgi.manager.model.PackageName;
import static com.cgi.manager.model.PkgData.pkgData;
import com.cgi.manager.model.ProdData;
import com.cgi.manager.model.ProductsSuppliers;
import com.cgi.manager.util.DateUtil;
import com.cgi.manager.view.ViewController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Optional;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Greg Ng
 */
public class PackageOverviewController implements Initializable, ViewController {

    @FXML
    private TableView<PackageName> packageTable;
    @FXML
    private TableColumn<PackageName, String> pkgNameColumn;
    @FXML
    private TableView<ProductsSuppliers> productTable;
    @FXML
    private TableColumn<ProductsSuppliers, String> prodNameColumn;
    @FXML
    private TableColumn<ProductsSuppliers, String> supNameColumn;
    
    @FXML
    private TextField pkgNameJFXTextField;
    @FXML
    private TextField pkgStartDateJFXTextField;
    @FXML
    private TextField pkgEndDateJFXTextField;
    @FXML
    private TextArea pkgDescTextArea;
    @FXML
    private TextField pkgBasePriceJFXTextField;
    @FXML
    private TextField pkgAgencyCommissionJFXTextField;
    
    // Reference to the main application.
    private MainApp main;
    private ProdData productList;
    private PackageClass selectedPackage;
    
    private ObservableList<PackageName> packageList = FXCollections.observableArrayList();
    private ObservableList<ProductsSuppliers> nonPkgProducts = FXCollections.observableArrayList();
    private ObservableList<ProductsSuppliers> packagedProducts = FXCollections.observableArrayList();
    private ObservableList<Integer> pkgProducts = FXCollections.observableArrayList();
    
    public PackageOverviewController() {
        // TODO use packagedb class to call in information
        packageList = pkgData.getPackageList();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
         
        pkgNameColumn.setCellValueFactory(cellData -> cellData.getValue().pkgNameProperty());
        prodNameColumn.setCellValueFactory(cellData -> cellData.getValue().prodNameProperty());
        supNameColumn.setCellValueFactory(cellData -> cellData.getValue().supNameProperty());

        // Clear person details.
        showPackageDetails(null);
        
        // Listen for selection changes and show the package details when changed.
        packageTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPackageDetails(newValue.getPackageId()));
    }
    
    @Override
    public void setMainApp(MainApp main) {
        this.main = main;

//         Add observable list data to the table
        SortedList<PackageName> sortedPackageList = new SortedList<>(packageList,(x, y) -> x.getPkgName().compareToIgnoreCase(y.getPkgName()));

        packageTable.setItems(sortedPackageList);
    
    }
    
    private void showPackageDetails(Integer id) {
        selectedPackage = new PackageClass();
        DecimalFormat priceFormat = new DecimalFormat("$###,###.00");
        
        if (id != null) {
            selectedPackage = PackageDB.getPackageById(id); 

            // Fill the labels with info fr om the PackageClass object.
            pkgNameJFXTextField.setText(selectedPackage.getPkgName());
            pkgStartDateJFXTextField.setText(DateUtil.format(selectedPackage.getPkgStartDate()));
            pkgEndDateJFXTextField.setText(DateUtil.format(selectedPackage.getPkgEndDate()));
            pkgDescTextArea.setText(selectedPackage.getPkgDesc());
            pkgBasePriceJFXTextField.setText(priceFormat.format(selectedPackage.getPkgBasePrice()));
            pkgAgencyCommissionJFXTextField.setText(priceFormat.format(selectedPackage.getPkgAgencyCommission()));

            productList = new ProdData(id);
            nonPkgProducts = productList.getNonPackagedProducts();
            packagedProducts = productList.getPackagedProducts();

            SortedList<ProductsSuppliers> sortedPackagedProducts = new SortedList<>(packagedProducts,(x, y) -> x.getProdName().compareToIgnoreCase(y.getProdName()));
//            sortedPackagedProducts.comparatorProperty().bind(productTable.comparatorProperty());
            productTable.setItems(sortedPackagedProducts);
        } 
        else {
            // id is null, remove all the text.
            pkgNameJFXTextField.setText("");
            pkgStartDateJFXTextField.setText("");
            pkgEndDateJFXTextField.setText("");
            pkgDescTextArea.setText("");
            pkgBasePriceJFXTextField.setText("");
            pkgAgencyCommissionJFXTextField.setText("");

        }
    }
    
    @FXML
    private void handleDeletePackage() {
        PackageName selectedDeletePackage = packageTable.getSelectionModel().getSelectedItem();
        if (selectedDeletePackage != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("Confirm Delete?");
            alert.setHeaderText(selectedDeletePackage.getPkgName() + " will be deleted?");
            alert.setContentText("Are you sure this is what you want to do?");

            Optional<ButtonType> result = alert.showAndWait();
            
            if (result.get() == ButtonType.OK){
                PackageDB.deletePackage(selectedDeletePackage.getPackageId());
                // need to refresh screen
                packageList.remove(selectedDeletePackage);
            }
        }
        else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Package Selected");
            alert.setContentText("Please select a package in the table.");

            alert.showAndWait();
        }
    }
    
    @FXML
    private void handleNewPackage() {
        PackageClass tempPackage = new PackageClass();
        tempPackage.setPkgStartDate(LocalDate.now());
        tempPackage.setPkgEndDate(LocalDate.now().plusDays(1));
        try{
            pkgData.setSelectedPackage(tempPackage);
            main.showEditPackageScene(1);
        }
        catch(IOException e){
            System.out.println("Edit Package scene cannot be shown.");
        }
         
    }

    @FXML
    private void handleEditPackage() {
        PackageName selectedEditPackage = packageTable.getSelectionModel().getSelectedItem();
        if (selectedEditPackage != null && selectedEditPackage.getPackageId() == selectedPackage.getPackageId()) {
            try{
                pkgData.setSelectedPackage(selectedPackage);
                main.showEditPackageScene(2);
            }
            catch(IOException e){
                System.out.println("Edit Package scene cannot be shown.");
            }
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Package Selected");
            alert.setContentText("Please select a package in the table.");

            alert.showAndWait();
        }
    }
    
    @FXML
    private void handleCopyPackage() {
        PackageName selectedEditPackage = packageTable.getSelectionModel().getSelectedItem();
        if (selectedEditPackage != null && selectedEditPackage.getPackageId() == selectedPackage.getPackageId()) {
            try{
                selectedPackage.setPkgName(selectedPackage.getPkgName().toString() + " copy"); 
                pkgData.setSelectedPackage(selectedPackage);
                main.showEditPackageScene(3);
            }
            catch(IOException e){
                System.out.println("Edit Package scene cannot be shown.");
            }
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Package Selected");
            alert.setContentText("Please select a package in the table.");

            alert.showAndWait();
        }
    }

}
