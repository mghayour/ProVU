/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.geometry.NodeOrientation;

import helper.*;
import logic.DataBase;
import logic.Student;
import org.joda.time.DateTime;

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
        
        DataBase db = DataBase.getInstance();
        /**
        db.user.add(
                new Student("مهدی", "غیور", "test")
        );/**/
        System.out.println(db.user.toString());
        System.out.println(db.user.size());

        launch(args);
    }
    
    
    
    
    
    
    @Override
    public void start(Stage stage) throws Exception {
        
        //Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("MessageBoard.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("CourseBoard.fxml"));
        
        root.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        Scene scene = new Scene(root);
        //scene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        stage.setScene(scene);
        stage.setMaximized(true);
        //setFullScreen(stage);
        stage.show();

    }


    
}
