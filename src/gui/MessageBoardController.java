/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;



//import javafx.css.StyleableObjectProperty
//import com.fxexperience.javafx.animation.*;
import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

import gui.helper.ModelControlCollection;
import helper.NameValue;
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
    @FXML private VBox messageSubjectHolder, vbx_myCourseContent, vbx_comments;
    ModelControlCollection posts, myCourses, comments;
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
        
        comments = new ModelControlCollection(vbx_comments, "model/CommentsInMessageBoard.fxml",toNameValue()); // edit comment !?
        //comments = new ModelControlCollection(vbx_comments, "model/messageItemInMessageBoard.fxml"); // edit comment !?

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
        
        init_tahvilRadios();
        
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
        
        // show this post comments
        comments.clear();
        for(Comment c: p.getComments())
            comments.add(c);
        
    }
    
    
    @FXML JFXDialog dlg_selectCourse;
    @FXML void btn_selectCourse_click() {
        showDialog(dlg_selectCourse);
    }
    
    
    // new post
    @FXML JFXDialog dlg_newPost;
    @FXML HTMLEditor hEdit_newPostContent; 
    @FXML JFXTextField hEdit_newPostTitle; 
    
    @FXML void btn_newPostDialog_click() {
        if (currentCourse.containsKey("course")) {
            hEdit_newPostContent.setHtmlText("<html dir=\"rtl\"><head></head><body contenteditable=\"true\"></body></html>");
            showDialog(dlg_newPost);
        }
    }
    
    @FXML void btn_addNewPost_click() {
        String content = hEdit_newPostContent.getHtmlText();
        String title = hEdit_newPostTitle.getText();
        Post post = gui.getUi().newPost(title, content, (Course)currentCourse.get("course"));
        posts.add(post);
        closeDialog();
    }

    
    // Edit post
    @FXML JFXDialog dlg_editPost;
    @FXML HTMLEditor hEdit_editPostContent; 
    @FXML JFXTextField hEdit_editPostTitle; 
    
    @FXML void btn_editPostDialog_click() {
        if (currentCourse.containsKey("course")) {
            Post p = (Post)currentPost.get("post");
            hEdit_editPostContent.setHtmlText(p.getContent());
            hEdit_editPostTitle.setText(p.getTitle());
            showDialog(dlg_editPost);
        }
    }
    
    @FXML void btn_saveEditedPost_click() {
        Post p = (Post)currentPost.get("post");
        String content = hEdit_editPostContent.getHtmlText();
        String title = hEdit_editPostTitle.getText();
        gui.getUi().editPost(title, content, p);
        
        // show post details
        currentPost.bind(p.toNameValue());
        
        // show selected post
        web_postContent.getEngine().loadContent(p.getContent());
        web_postContent.setDisable(true);

        // close select dialog
        closeDialog();
        
    }

    
    // Add comment
    @FXML JFXDialog dlg_newComment;
    @FXML HTMLEditor hEdit_newCommentContent; 
    @FXML public void btn_CommentDialog_Clicked() {
        if (currentPost!=null && currentPost.containsKey("post")) {
            hEdit_newCommentContent.setHtmlText("<html dir=\"rtl\"><head></head><body contenteditable=\"true\"></body></html>");
            showDialog(dlg_newComment);
        }
    }
    @FXML public void btn_addNewComment_click() {
        if (currentPost.containsKey("post")) {
            Post post = (Post)currentPost.get("post");
            if (post != null) {
                Comment cmt = gui.getUi().newComment(hEdit_newCommentContent.getHtmlText(), post);
                comments.add(cmt);
                closeDialog();
            }
        }
    }
    
    
    
    
    // Tamrin
    @FXML public HBox hbx_mohlateTahvil, hbx_postType;
    @FXML public JFXRadioButton rad_tamrinPost, rad_post;
    @FXML public void rdo_tamrin_Changed() {
        if(rad_tamrinPost.isSelected())
            hbx_postType.getChildren().add(hbx_mohlateTahvil);
        else
            hbx_postType.getChildren().remove(hbx_mohlateTahvil);
    }
    
    private void init_tahvilRadios() {
        rdo_tamrin_Changed();
        //rad_tamrinPost.addEventHandler(EventType., null);
    }
    
    
            
}