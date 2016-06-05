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
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;
import logic.*;
//import gui.animation.*;

/**
 *
 * @author Ghayour
 */
public class MessageBoardController extends MyController {
    
    @FXML private Pane rooter;
    @FXML private VBox messageSubjectHolder, vbx_myCourseContent;
    ModelControlCollection posts, myCourses;
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
    public void setGui(GuiController gui) {
        super.setGui(gui);

        DataBase db = DataBase.getInstance();
        User u = gui.getUi().getUser();
        
        posts = new ModelControlCollection(messageSubjectHolder, "model/messageItemInMessageBoard.fxml") {
            @Override
            public void onButtonClick (NameValue data) {
                selectPost(data.getInt("id"));
            }
        };

        myCourses = new ModelControlCollection(vbx_myCourseContent, "model/myCourseInCourseBoard.fxml",
                        toNameValue().combine(new NameValue("section","myCourse", "page","MessageBoard"))  ) {
            @Override            
            public void onButtonClick (NameValue data, String btnId) {
                if (btnId.equals("btn_selectCourse")) {        
                    // select course
                    selectCourse(data.getInt("id"));
                }
            }
        };
        
        for(Course c: u.getCourses())
            myCourses.add(c);
        
        // auto ask for select course
        btn_selectCourse_click();
    }

    
    void selectCourse(int cid) {
        DataBase db = DataBase.getInstance();
        Course c = db.getCourse(cid);

        // show course details
        currentCourse.bind(c.toNameValue());
        currentCourse.put("course", c);
        
        // show selected course
        posts.clear();
        for(Post p: c.getPosts())
            posts.add(p);

        // close select dialog
        closeDialog();        
    }
    
    @FXML WebView web_postContent;
    void selectPost(int pid) {
        DataBase db = DataBase.getInstance();
        Post p = db.getPost(pid);

        // show post details
        currentPost.bind(p.toNameValue());
        currentPost.put("post", p);
        
        // show selected post
        web_postContent.getEngine().loadContent(p.getContent());
        web_postContent.setDisable(true);

        // close select dialog
        closeDialog();        
    }
    
    
    @FXML JFXDialog dlg_selectCourse;
    @FXML void btn_selectCourse_click() {
        showDialog(dlg_selectCourse);
    }
    
    @FXML JFXDialog dlg_newPost;
    @FXML void btn_newPostDialog_click() {
        if (currentCourse.containsKey("course"))
            showDialog(dlg_newPost);
    }
    
    @FXML HTMLEditor hEdit_newPostContent; 
    @FXML JFXTextField hEdit_newPostTitle; 
    @FXML void btn_addNewPost_click() {
        String content = hEdit_newPostContent.getHtmlText();
        String title = hEdit_newPostTitle.getText();
        Post post = gui.getUi().newPost(title, content, (Course)currentCourse.get("course"));
        posts.add(post);
        closeDialog();
    }

    
}
