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
import logic.*;
//import gui.animation.*;

/**
 *
 * @author Ghayour
 */
public class MessageBoardController extends MyController {
    
    @FXML private Pane rooter;
    @FXML private VBox messageSubjectHolder;
    ModelControlCollection messages;
    NameValue currentPost, currentCourse;

    @Override
    public NameValue toNameValue() {
        if (myNameValue==null) {
            myNameValue = super.toNameValue();
            
            currentPost= new NameValue();
            currentPost.bind(Post.toEmptyNameValue());
            myNameValue.put("currentPost",currentPost);
            
            currentCourse= new NameValue();
            currentCourse.bind(Course.toEmptyNameValue());
            myNameValue.put("currentCourse",currentCourse);
        }
        return myNameValue;
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        super.initialize(url, rb);
        
       
        messages = new ModelControlCollection(messageSubjectHolder, "model/messageItemInMessageBoard.fxml") {
            @Override
            public void onButtonClick (NameValue data) {
                StringProperty number = data.getStringProperty("number");
                int n = data.getInt("number") + 1;
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

    }

    
    @FXML void btn_viewPost_click() {
    }
    @FXML void btn_viewPost_click_pro(int id) {
    }
    

    
}
