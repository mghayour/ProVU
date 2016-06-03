/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.helper;

import gui.GuiController;
import helper.NameValue;
import javafx.beans.property.*;
import javafx.collections.ObservableList;
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
    boolean handleClicks;

    public ModelControl() {
    }

    public ModelControl(String fxmlName, boolean handleClicks) {
        this.fxmlName = fxmlName;
        this.handleClicks = handleClicks;
    }
    public ModelControl(String fxmlName) {
        this(fxmlName,true);
    }

    

    public Node generate(final NameValue data) {
        Pane root = null;
        try {
            //root = (Pane)FXMLLoader.load(GuiController.class.getResource(fxmlName));
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlName));
            root = (Pane)loader.load();
            //loader.setClassLoader(null);
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
        
        if(handleClicks)
            root.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    onRootClick(data, event);
                }
            });
        
        return generate(root, data);
    }
    
    public Node generate(Pane root,final NameValue data) {
        final EventHandler<ActionEvent> btnAction = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                onButtonClick(data, event);
            }
        };
             
        ObservableList<Node> childs = root.getChildren();
         
        for(Node n: childs) {
            if (Pane.class.isAssignableFrom(n.getClass())) {
                // if its if : parse it
                if (n instanceof If) {
                    If f = (If)n;
                    if (f.getX()!=null && f.getIs()!=null ){
                        f.setX ( patternBinder(f.getX() , data).getValue() );
                        f.setIs( patternBinder(f.getIs(), data).getValue() );
                    }
                }
                generate( (Pane)n, data);
            } else {                                
                // if its button or lable
                if ( Labeled.class.isAssignableFrom(n.getClass()) ) {
                    Labeled l = (Labeled)n;
                    l.textProperty().bind( patternBinder(l.getText(), data) );
                }
                
                // Onclick action
                if ( handleClicks && Button.class.isAssignableFrom(n.getClass()) ) {
                    Button b = (Button)n;
                    b.setOnAction(btnAction);
                }
            }
        }
        
        // check if statement
        for (int i=0; i<childs.size(); i++) {
            Node n = childs.get(i);
            // if its if !
            if (n instanceof If) {
                If f = (If)n;
                //f.checkStatement(data); // not need, data parsed before !
                f.checkStatement();
            }            
        }

        return root;
    }

    private static StringProperty patternBinder(String pattern, final NameValue data) {
        StringProperty patternResult = new SimpleStringProperty("");

        // extract pattern of text
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

        return patternResult;
    }
    
    // do stuff inside anonymous class
    // We should have data[id] here !        
    // notice: after button action, root action will NOT run !!!
    public void onRootClick  (NameValue data, MouseEvent e) { onRootClick(data); }
    public void onRootClick  (NameValue data) { }

    public void onButtonClick (NameValue data, ActionEvent e) {
        onButtonClick(data, ((Node)e.getSource()).getId() );
        onButtonClick(data);
    }
    public void onButtonClick (NameValue data, String BtnId) { }
    public void onButtonClick (NameValue data) { }
    
}
