/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gui.helper.ModelControlCollection;
import helper.NameValue;
import java.net.URL;
import java.util.ResourceBundle;
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
            
        };
        
        NameValue val = new NameValue();
        val.put("id", 0);
        val.put("title", "اطلاعیه ها");
        val.put("fxmlName", "MessageBoard");
        menu.add(val);
        
        
        

        mainMenu.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Timeline moveAnimate = new Timeline(
                  new KeyFrame(
                    Duration.seconds(0.2),
                    new KeyValue(
                      mainMenu.layoutXProperty(),
                      0
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
                      -150
                    )
                  )
                );
                moveAnimate.play();                
            }
        });
        
        
    }
    
    
    
}
