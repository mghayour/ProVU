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
import gui.helper.ModelControl;
import gui.helper.ModelControlCollection;
import helper.NameValue;
import java.util.HashMap;
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
public class MessageBoardController extends MyController {
    
    @FXML private Pane rooter;
    @FXML private VBox messageSubjectHolder;
    
    private StringProperty testStr = new SimpleStringProperty("T");
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        super.initialize(url, rb);
        
        
        messageSubjectHolder.getChildren().clear();
        
        ModelControlCollection messages = new ModelControlCollection(messageSubjectHolder, "model/messageItemInMessageBoard.fxml") {
            @Override
            public void onButtonClick (NameValue data) {
                StringProperty number = data.getStringProperty("number");
                int n = data.getInt("number") + 1;
                number.set(""+n);
            }
            
            @Override
            public void onRootClick (NameValue data) {
                StringProperty number = data.getStringProperty("number");
                int n = data.getInt("number") - 1;
                number.set(""+n);
            }
        };
        
        for (int i=0; i<100; i++) {
            NameValue data = new NameValue();
            data.put("id",i);
            data.put("title",new SimpleStringProperty("کوئیز جاوا"));
            data.put("show",new SimpleStringProperty("مشاهده"));
            data.put("number",new SimpleStringProperty(""+i));
            
            messages.add(data);
        }
        for (int i=0; i<100; i+=2)
            messages.remove(i);
                    
        /*
        ObservableList<Node> childs = messageSubjectHolder.getChildren();
        
        childs.clear();
        //ModelControl message = new ModelControl("model/messageItemInMessageBoard.fxml");
        PatternControl message = new PatternControl("model/messageItemInMessageBoard.fxml") {
            @Override
            public void onButtonClick (NameValue data) {
                StringProperty number = data.getStringProperty("number");
                int n = data.getInt("number") + 1;
                number.set(""+n);
            }
            
            @Override
            public void onRootClick (NameValue data) {
                StringProperty number = data.getStringProperty("number");
                int n = data.getInt("number") - 1;
                number.set(""+n);
            }
        };
        
        NameValue data = new NameValue();
        data.put("title",new SimpleStringProperty("کوئیز جاوا"));
        data.put("show",new SimpleStringProperty("مشاهده"));
        data.put("number",new SimpleStringProperty("5"));

        //for (int i=0; i <100; i++)
        Node n = message.generate(data);
        childs.add(n);
        //childs.add(n);
        
        childs.remove(n);
        */
        /*
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
        */
        
    }

    
    @FXML void btn_viewPost_click() {
        testStr.set(testStr.get()+"t");
    }
    @FXML void btn_viewPost_click_pro(int id) {
        testStr.set(testStr.get()+id);
    }
    

    
}
