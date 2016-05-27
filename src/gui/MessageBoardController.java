/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;



//import javafx.css.StyleableObjectProperty
//import com.fxexperience.javafx.animation.*;
import com.jfoenix.controls.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import static gui.Helper.*;
import javafx.beans.property.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
//import gui.animation.*;

/**
 *
 * @author Ghayour
 */
public class MessageBoardController implements Initializable {
    
    @FXML private Pane rooter;
    @FXML private VBox messageSubjectHolder;
    
    private StringProperty testStr = new SimpleStringProperty("T");
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addBackground(rooter, 0.3, 1);
        
        ObservableList<Node> childs = messageSubjectHolder.getChildren();
        HBox[] messageSubject = childs.toArray(new HBox[childs.size()]);
        int id=-1;
        for (HBox ms: messageSubject) {
            id++;
            
            Label l = (Label)ms.getChildren().get(0);
            if (l.getText().equals("پروژه سوم"))                    
                l.textProperty().bind(testStr);
            
            Node c = ms.getChildren().get(1);
            JFXButton b = c instanceof JFXButton ? (JFXButton)c : null ;
            final int mid = id;
            if (b!=null)
                b.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        btn_viewPost_click_pro(mid);
                    }
                });
            
        }
        
        
    }

    
    @FXML void btn_viewPost_click() {
        testStr.set(testStr.get()+"t");
    }
    @FXML void btn_viewPost_click_pro(int id) {
        testStr.set(testStr.get()+id);
    }
    

    
}
