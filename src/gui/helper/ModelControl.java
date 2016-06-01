/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.helper;

import gui.GuiController;
import helper.NameValue;
import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 *
 * @author Ghayour
 */
public class ModelControl {
    String fxmlName;
                
    public ModelControl(String fxmlName) {
        this.fxmlName = fxmlName;
    }
    
    public Node generate( final NameValue data ) {
        Pane root = null;
        try {
            root = (Pane)FXMLLoader.load(GuiController.class.getResource(fxmlName));
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
    
    private Node generate(Pane root,final NameValue data) {
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
                            tmp.bind( patternResult.concat(data.getStringProperty(s)) );
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
    public void onRootClick  (NameValue data, MouseEvent e) { onRootClick(data); }
    public void onRootClick  (NameValue data) { }
    
    public void onButtonClick (NameValue data, ActionEvent e) { onButtonClick(data); }
    public void onButtonClick (NameValue data) { }
    
}
