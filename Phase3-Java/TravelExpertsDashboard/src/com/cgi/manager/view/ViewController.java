/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cgi.manager.view;

import com.cgi.manager.MainApp;

/**
 *
 * @author Greg Ng
 */
public interface ViewController {
    //This method will allow the injection of the Parent ScreenPane
    public void setMainApp(MainApp  main);
}
