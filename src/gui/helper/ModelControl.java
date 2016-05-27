/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.helper;

import com.jfoenix.controls.JFXButton;
import java.util.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Ghayour
 */
public class ModelControl {
    String fxmlName;
    
    ModelControl(String fxmlName) throws Exception {
        this.fxmlName = fxmlName;
    }
    
    Node generate(HashMap<String,StringProperty> data) {
        Pane root= null;
        try {
            root = (Pane)FXMLLoader.load(ModelControl.class.getResource(fxmlName));
        } catch(Exception e) {
            e.printStackTrace();
        }
        return generate(root, data);
    }
    
    Node generate(Pane root, HashMap<String,StringProperty> data) {
        for(Node n: root.getChildren()) {
            if (Pane.class.isAssignableFrom(n.getClass()))
                generate( (Pane)n, data);
            else {
                
                // if its button or lable
                if ( Labeled.class.isAssignableFrom(n.getClass()) ) {
                    Labeled l= (Labeled)n;
                    StringProperty patternResult = new SimpleStringProperty();
                    
                    // extract pattern of text
                    String pattern = l.getText();
                    pattern=pattern.replace('}', '{');
                    String[] parts = pattern.split("\\{"); // even parts are data keys !
                    
                    // binding pattrn with data
                    boolean itsData=false;
                    for (String s:parts) {
                        if(itsData)    
                            patternResult.concat(data.get(s));
                        else
                            patternResult.concat(s);

                        itsData=!itsData;
                    }
                }
                
                // Todo: Onclick action
                
            }
                
        }
        return root;
    }
    
}
