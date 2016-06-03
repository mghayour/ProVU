/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gui.helper.ModelControlCollection;
import helper.NameValue;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.util.Duration;

/**
 *
 * @author Ghayour
 */
public class SideMenuController extends MyController {

    @FXML Pane mainMenu;
    @FXML VBox menu_buttons;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Im fine");
        
        ModelControlCollection menu = new ModelControlCollection(menu_buttons,"model/buttonInSideMenu.fxml") {
            @Override
            public void onButtonClick(NameValue data) {
                gui.gotoPageSafe(data.getString("fxmlName"));
            }
        };

        addDataToMenu(menu, "DashBoard", "داشبورد");
        addDataToMenu(menu, "CourseBoard", "درس");
        addDataToMenu(menu, "MessageBoard", "اطلاعیه");

        setMainMenuInOutAnimation();
        
    }
    
    private void addDataToMenu(ModelControlCollection menu, String fxmName, String title) {
        NameValue val = new NameValue();
        val.put("title", title);
        val.put("fxmlName", fxmName);
        menu.add(val);        
    }
    
    @FXML void btn_logout_click() {
        if (gui==null)
            System.out.println("Toooooo Bad :|");
        else
            gui.gotoPageSafe("Login");
    }
    
    private void setMainMenuInOutAnimation() {
        mainMenu.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Timeline moveAnimate = new Timeline(
                  new KeyFrame(
                    Duration.seconds(0.2),
                    new KeyValue(
                      mainMenu.layoutXProperty(),
                      -12
                    )
                  )
                );
                moveAnimate.play();                
            }
        });

        mainMenu.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Timeline moveAnimate = new Timeline(
                  new KeyFrame(
                    Duration.seconds(0.2),
                    new KeyValue(
                      mainMenu.layoutXProperty(),
                      -230
                    )
                  )
                );
                moveAnimate.play();                
            }
        });        
    }
    
}
