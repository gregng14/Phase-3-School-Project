package com.cgi.manager.view;

import com.cgi.manager.MainApp;
import java.io.IOException;
import javafx.fxml.FXML;

/**
 *
 * @author Charlene
 */ 
public class MainViewController implements ViewController {
    
    @FXML
    private MainApp main;
    
    @FXML
    private void goMainScene() throws IOException {
        main.showMainPage();
    }
       
    @FXML
    private void goPackageScene() throws IOException {
        main.showPackageScene();              
    }
    
    @FXML
    private void goAgentScene() throws IOException {
        main.showAgentScene();
    }
    
    @FXML
    private void goCustomerScene() throws IOException {
        main.showCustomerScene();
    }

    @Override
    public void setMainApp(MainApp main) {
        this.main = main;
    }
    

    
}
