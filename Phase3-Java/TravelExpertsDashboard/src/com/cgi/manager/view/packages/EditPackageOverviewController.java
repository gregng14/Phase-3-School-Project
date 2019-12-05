/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cgi.manager.view.packages;

import com.cgi.manager.MainApp;
import com.cgi.manager.model.PackageClass;
import com.cgi.manager.model.PackageDB;
import static com.cgi.manager.model.PkgData.pkgData;
import com.cgi.manager.model.ProdData;
import com.cgi.manager.model.ProductsSuppliers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import com.cgi.manager.view.ViewController;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.event.EventType;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.Callback;


/**
 * FXML Controller class
 *
 * @author Greg Ng
 */
public class EditPackageOverviewController implements ViewController{
    @FXML
    private TextField pkgNameTextField;
    @FXML
    private DatePicker pkgStartDateField;
    @FXML
    private DatePicker pkgEndDateField;
    @FXML
    private TextArea pkgDescTextArea;
    @FXML
    private TextField pkgBasePriceTextField;
    @FXML
    private TextField pkgAgencyCommissionTextField;
    
    @FXML
    private TableView<ProductsSuppliers> prodInPkgTableView;
    @FXML
    private TableColumn<ProductsSuppliers, String> prodInPkgNameColumn;
    @FXML
    private TableColumn<ProductsSuppliers, String> supInPkgColumn;
    
    @FXML
    private TableView<String> productTableView;
    @FXML
    private TableColumn<String, String> prodNameColumn;
    @FXML
    private TableView<ProductsSuppliers> supplierTableView;
    @FXML
    private TableColumn<ProductsSuppliers, String> supNameColumn;
    
    private MainApp main;
    private Stage dialogStage;
    private PackageClass selectedPackage;
    private ProdData productList;
    private int edit; // if edit =  1 (new) 2 (edit) 3(copy)

    private ObservableList<ProductsSuppliers> nonPkgProducts = FXCollections.observableArrayList();
    private ObservableList<ProductsSuppliers> packagedProducts = FXCollections.observableArrayList();
    private ObservableList<Integer> pkgProducts = FXCollections.observableArrayList();
    private ObservableList<String> productNames = FXCollections.observableArrayList();
    
    private DecimalFormat priceFormat = new DecimalFormat("######.00");
 
    public void PackageEditOverviewController(){ 
    }
    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() {

        try{
        prodInPkgNameColumn.setCellValueFactory(cellData -> cellData.getValue().prodNameProperty());
        supInPkgColumn.setCellValueFactory(cellData -> cellData.getValue().supNameProperty());
        prodNameColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        supNameColumn.setCellValueFactory(cellData -> cellData.getValue().supNameProperty());
        
        final Callback<DatePicker, DateCell> dayCellFactory = 
        new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item.isBefore(
                                pkgStartDateField.getValue().plusDays(1))
                            ) {
                                setDisable(true);
                        }
                    }
                };
            }
        };
        

        
        pkgStartDateField.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                pkgEndDateField.setValue(newValue.plusDays(1));
            }
        }); 
        pkgEndDateField.setDayCellFactory(dayCellFactory);
        }
        catch(Exception e){
            System.out.println("Stupid thing cant display columns");
        }
    }    
    
    public void setPackageData(PackageClass packageData) {
        this.selectedPackage = packageData;

        pkgNameTextField.setText(packageData.getPkgName());
        pkgStartDateField.setValue(packageData.getPkgStartDate());
        pkgEndDateField.setValue(packageData.getPkgEndDate());
        pkgDescTextArea.setText(packageData.getPkgDesc());
        pkgBasePriceTextField.setText(priceFormat.format(packageData.getPkgBasePrice()));
        pkgAgencyCommissionTextField.setText(priceFormat.format(packageData.getPkgAgencyCommission()));
    }
    
    @FXML
    private void handleAdd() {
        ProductsSuppliers selectedProduct = supplierTableView.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            System.out.println(packagedProducts.add(selectedProduct));
            nonPkgProducts.remove(selectedProduct);
        }
        else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Product Selected");
            alert.setContentText("Please select a product in the table.");

            alert.showAndWait();
        }
    }
    
    @FXML
    private void handleRemove() {
        ProductsSuppliers selectedProduct = prodInPkgTableView.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            System.out.println(packagedProducts.remove(selectedProduct));
            nonPkgProducts.add(selectedProduct);
        }
        else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Product Selected");
            alert.setContentText("Please select a product in the table.");

            alert.showAndWait();
        }
    }
    
    @FXML
    private void handleOk() {        
        if (isInputValid()) {
            selectedPackage.setPkgName(pkgNameTextField.getText());
            selectedPackage.setPkgStartDate(pkgStartDateField.getValue());
            selectedPackage.setPkgEndDate(pkgEndDateField.getValue());
            selectedPackage.setPkgDesc(pkgDescTextArea.getText());
            selectedPackage.setPkgBasePrice(Float.valueOf(pkgBasePriceTextField.getText()));
            selectedPackage.setPkgAgencyCommission(Float.valueOf(pkgAgencyCommissionTextField.getText()));
            
            // decide if edit new or copy
            switch (edit){
                case 1:{
                    PackageDB.insertPackage(selectedPackage,packagedProducts);
                    break;
                }
                case 2:{
                    PackageDB.updatePackage(selectedPackage,packagedProducts);
                    break;
                }
                case 3:{
                    PackageDB.insertPackage(selectedPackage,packagedProducts);
                    break;
                }
            }
                    
            try{
                main.showPackageScene();
            }
            // switch to main screen    
            catch(IOException e){
                System.out.println("Problem showing package scene!!");
            }
        }
    }

    @FXML
    private void handleCancel() {
        // switch to main screen
        try{
            main.showPackageScene();
        }
        // switch to main screen    
        catch(IOException e){
            System.out.println("Problem showing package scene!!");
        }
    }

    private void activeButtons(Boolean bool){
//        addButton.setDisable(bool);
//        removeButton.setDisable(!bool);
    }
    
    private boolean isInputValid() {
        String errorMessage = "";
        Float p = null, c = null;
        
        if ((pkgNameTextField.getText() == null || pkgNameTextField.getText().length() == 0) || pkgNameTextField.getText().length() > 50) {
            errorMessage += "Package name has to be between 1 and 50 characters!\n"; 
        }
        if ((pkgDescTextArea.getText() == null || pkgDescTextArea.getText().length() == 0) || pkgDescTextArea.getText().length() > 50) {
            errorMessage += "Package description has to be between 1 and 50 characters!\n"; 
        }

        if ((pkgBasePriceTextField.getText() == null || pkgBasePriceTextField.getText().length() == 0) || pkgBasePriceTextField.getText().length() > 24) {
            errorMessage += "Not a valid base price!\n"; 
        } else {
            
            try {
                p = Float.parseFloat(pkgBasePriceTextField.getText());
                if (p<0){
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                errorMessage += "No valid base price (must be a positive number)!\n"; 
            }
        }
        
        if ((pkgAgencyCommissionTextField.getText() == null || pkgAgencyCommissionTextField.getText().length() == 0) || pkgAgencyCommissionTextField.getText().length() > 24){
            errorMessage += "Not a valid commission!\n"; 
        } else {
           
            try {
                c = Float.parseFloat(pkgAgencyCommissionTextField.getText());
                if (c<0){
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                errorMessage += "No valid commission (must be a postive number)!\n"; 
            }
        }
        
        
        if (p != null && c != null){
            if (c > p){
                errorMessage += "Commission cannot be greater than base price!\n";
            }
        }
        
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
    
    @Override
    public void setMainApp(MainApp main) {
        selectedPackage = pkgData.getSelectedPackage();
        
        productList = new ProdData(selectedPackage.getPackageId());
        nonPkgProducts = productList.getNonPackagedProducts();
        packagedProducts = productList.getPackagedProducts();
        productNames = productList.getProductNames();
        SortedList<ProductsSuppliers> sortedPackagedProducts = new SortedList<>(packagedProducts,(x, y) -> (x.getProdName()+x.getSupName()).compareToIgnoreCase(y.getProdName()+y.getSupName()));

        prodInPkgTableView.setItems(sortedPackagedProducts);
        
        SortedList<String> sortedProductNames = new SortedList<>(productNames,(x, y) -> (x).compareToIgnoreCase(y));

        productTableView.setItems(sortedProductNames);
        
        FilteredList<ProductsSuppliers> filteredNonPkgProducts = new FilteredList<>(nonPkgProducts, p -> false);
        
        SortedList<ProductsSuppliers> sortedNonPkgProducts = new SortedList<>(filteredNonPkgProducts,(x, y) -> (x.getSupName()).compareToIgnoreCase(y.getSupName()));

        supplierTableView.setItems(sortedNonPkgProducts);
        
        productTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filteredNonPkgProducts.setPredicate(product -> {
                String lowerCaseFilter = newValue.toLowerCase();

                if (product.getProdName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches product name.
                } 
                
                return false; // Does not match.
            });
            }
        });

        setPackageData(selectedPackage);
        
        this.main = main;
    }
    
    public void setEdit(int edit) {
        this.edit = edit;
    }
}
