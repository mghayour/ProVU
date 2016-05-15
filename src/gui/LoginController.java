/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;



//import javafx.css.StyleableObjectProperty
//import com.fxexperience.javafx.animation.*;
import com.jfoenix.controls.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import static gui.Helper.*;
//import gui.animation.*;

/**
 *
 * @author Ghayour
 */
public class LoginController implements Initializable {
    
    @FXML private Label title;
    @FXML private Pane rooter;
    @FXML private VBox contentPanel;
    // TableView tb = (TableView) scene.lookup("#history");

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //Login.loginController=this;
        addBackground(rooter);
        loadContentPanel("loginContent.fxml");
        title.setText("Login");
/*
        JFXTextField checkBox = new JFXTextField("JFX CheckBox");/*
        JFXButton jfoenixButton = new JFXButton("JFoenix Button");
        JFXButton button = new JFXButton("Raised Button".toUpperCase());
        button.getStyleClass().add("button-raised");
        contentPanel.getChildren().addAll(jfoenixButton,button);
        
        /**/        
  //      contentPanel.getChildren().addAll(checkBox);
    }

    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        //label.setText("Hello World!");
    }

    private String currentContent="";
    private void loadContentPanel(String fxmlName) {
        try {
            System.out.println("Loading...");
            contentPanel.getChildren().clear();
            contentPanel.getChildren().addAll( ((VBox)FXMLLoader.load(getClass().getResource(fxmlName))).getChildren() );
            currentContent=fxmlName;
            System.out.println("Loaded");
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
        
    @FXML
    private void btn_login_click(ActionEvent event) {        
        if (!currentContent.equals("loginContent.fxml")) {
            loadContentPanel("loginContent.fxml");
            title.setText("Login");            
            return;
        }
        
        System.out.println("LOGINACTION");
    }

    @FXML
    private void btn_register_click(ActionEvent event) {
        if (!currentContent.equals("registerContent.fxml")) {
            loadContentPanel("registerContent.fxml");
            title.setText("Register");            
            return;
        }
        
        System.out.println("REGISTERACTION");
    }

    
}
