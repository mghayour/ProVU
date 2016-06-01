/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.*;

/**
 *
 * @author Ghayour
 */
public class WorkspaceController extends MyController {

    @FXML Pane mainPanel;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //loadMainPanel("Dashboard.fxml");
        loadMainPanel("MessageBoard.fxml");
    }
    
    
    private String currentContent="";
    private void loadMainPanel(String fxmlName) {
        try {
            System.out.println("Loading...");
            mainPanel.getChildren().clear();
            //mainPanel.getChildren().addAll( ((BorderPane)FXMLLoader.load(getClass().getResource(fxmlName))).getChildren() );
            mainPanel.getChildren().add( ((BorderPane)FXMLLoader.load(getClass().getResource(fxmlName))) );
            currentContent=fxmlName;
            System.out.println("Loaded");            
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }

    
}
