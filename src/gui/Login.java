/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.*;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import javafx.css.StyleableObjectProperty;
        
import static helper.Helper.*;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Screen;

import com.jfoenix.controls.*;
import java.lang.reflect.InvocationTargetException;

import static gui.Helper.*;

/**
 * ملاک ما نسخه 6 جاوا
 * @author Ghayour
 */
public class Login extends Application {
    
    public static LoginController loginController;
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
    
    
    
    
    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource(/**/"Dashboard.fxml"/**"Login.fxml"/**/));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        setFullScreen(stage);
        stage.show();

    }


    
}
