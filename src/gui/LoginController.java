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
import java.util.*;
import java.util.logging.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import static gui.Helper.*;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.util.Duration;
import logic.DataBase;
import logic.User;
//import gui.animation.*;

/**
 *
 * @author Ghayour
 */
public class LoginController extends MyController {
    
    @FXML private Label lbl_error;
    @FXML private Label title;
    @FXML private Pane rooter;
    @FXML private VBox contentPanel;
    @FXML private VBox loginCard;
    
    // inner content elements (fill with Lookup !)
    private JFXTextField username;
    private JFXPasswordField password;
    private JFXTextField firstname;
    private JFXTextField lastname;
    private JFXPasswordField password2;
    private JFXRadioButton rad_student;
    private JFXRadioButton rad_teacher;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addBackground(rooter);
        loadContentPanel("loginContent.fxml");
        title.setText("Login");
    }

    
    
    private String currentContent="";
    private void loadContentPanel(String fxmlName) {
        try {
            System.out.println("Loading...");
            contentPanel.getChildren().clear();
            contentPanel.getChildren().addAll( ((VBox)FXMLLoader.load(getClass().getResource(fxmlName))).getChildren() );
            currentContent=fxmlName;
            System.out.println("Loaded");
            
            username = (JFXTextField)contentPanel.lookup("#username");
            password = (JFXPasswordField)contentPanel.lookup("#password");
            firstname = (JFXTextField)contentPanel.lookup("#firstname");        
            lastname = (JFXTextField)contentPanel.lookup("#lastname");        
            password2 = (JFXPasswordField)contentPanel.lookup("#password2");
            rad_student = (JFXRadioButton)contentPanel.lookup("#rad_student");
            rad_teacher = (JFXRadioButton)contentPanel.lookup("#rad_teacher");
 

        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }

    
    @FXML
    private void btn_login_click(ActionEvent event) {        
        if (!currentContent.equals("loginContent.fxml")) {
            loadContentPanel("loginContent.fxml");
            title.textProperty().bind(new SimpleStringProperty("Login"));
            return;
        }

        boolean sucessLogin = false;
        try {
            if (gui.getUi().login(username.getText(), password.getText())) {
                sucessLogin = true;
                gui.gotoDashBoard();
            } else {
                System.out.println("Username not found");
                lbl_error.setText("Username not found");
            }
        } catch (Exception ex) {
            lbl_error.textProperty().bind(new SimpleStringProperty("Username and Password does not match."));
        }
        lbl_error.textProperty().bind(new SimpleStringProperty(""));
            
    }

    @FXML
    private void btn_register_click(ActionEvent event) {
        if (!currentContent.equals("registerContent.fxml")) {
            loadContentPanel("registerContent.fxml");
            title.textProperty().bind(new SimpleStringProperty("Register"));
            return;
        }

        
        
        try {
            if (gui.getUi().register(username.getText(), firstname.getText(), lastname.getText(), password.getText(), rad_teacher.isSelected()) )
                System.out.println("Success registration");
            else
                System.out.println("registration failed");
        } catch (Exception ex) {
            System.out.println("ERROR Register: "+ex.getMessage());
        }

    }

}
