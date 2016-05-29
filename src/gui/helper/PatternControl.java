/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.helper;

import com.jfoenix.controls.JFXButton;
import gui.Login;
import java.util.*;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Ghayour
 */
public class PatternControl {
    String fxmlName;
                
    public PatternControl(String fxmlName) {
        this.fxmlName = fxmlName;
    }
    
    public Node generate( final HashMap<String,StringProperty> data ) {
        Pane root = null;
        try {
            root = (Pane)FXMLLoader.load(Login.class.getResource(fxmlName));
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }

        root.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                onRootClick(data, event);
            }
        });
        return generate(root, data);
    }
    
    Node generate(Pane root,final HashMap<String,StringProperty> data) {
        final EventHandler<ActionEvent> btnAction = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                onButtonClick(data, event);
            }
        };
                
        for(Node n: root.getChildren()) {
            if (Pane.class.isAssignableFrom(n.getClass()))
                generate( (Pane)n, data);
            else {
                
                // if its button or lable
                if ( Labeled.class.isAssignableFrom(n.getClass()) ) {
                    Labeled l = (Labeled)n;
                    StringProperty patternResult = new SimpleStringProperty("");
                    
                    // extract pattern of text
                    String pattern = l.getText();
                    pattern=pattern.replace('}', '{');
                    String[] parts = pattern.split("\\{"); // even parts are data keys !

                    // binding pattrn with data
                    boolean itsData=false;
                    for (String s:parts) {
                        StringProperty tmp = new SimpleStringProperty();
                        if(itsData)
                            tmp.bind( patternResult.concat(data.get(s)) );
                        else
                            tmp.bind( patternResult.concat(s) );
                        patternResult = tmp;
                        itsData=!itsData;
                    }
                    
                    l.textProperty().bind(patternResult);
                }
                
                // Onclick action
                if ( Button.class.isAssignableFrom(n.getClass()) ) {
                    Button b = (Button)n;
                    b.setOnAction(btnAction);
                }
                
                
            }
                
        }
        return root;
    }

    
    // do stuff inside anonymous class
    // We should have data[id] here !        
    // notice: after button action, root action will NOT run !!!
    public void onRootClick  (HashMap<String,StringProperty> data, MouseEvent e) { onRootClick(data); }
    public void onRootClick  (HashMap<String,StringProperty> data) { }
    
    public void onButtonClick (HashMap<String,StringProperty> data, ActionEvent e) { onButtonClick(data); }
    public void onButtonClick (HashMap<String,StringProperty> data) { }
    
}
