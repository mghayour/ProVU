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
import helper.IdValue;
import helper.NameValue;
import helper.PersianDateTime;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
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
    
    @FXML public TextField txt_tamrin_Day, txt_tamrin_Hour;
    @FXML void btn_addNewPost_click() {
        UserInterface ui = gui.getUi();
        Course c = (Course)currentCourse.get("course");
        String content = hEdit_newPostContent.getHtmlText();
        String title = hEdit_newPostTitle.getText();
        Post post=null;
        if (rad_tamrinPost.isSelected()) {
            long currentTimestamp = PersianDateTime.now().getTimeStamp();
            long tahvilTimestamp = currentTimestamp + Integer.parseInt(txt_tamrin_Hour.getText())*3600000 + Integer.parseInt(txt_tamrin_Day.getText())*86400000;
            tahvilTimestamp+=300000; // 5min
            PersianDateTime mohlateTahvil=new PersianDateTime( tahvilTimestamp );
            post = ui.newTamrin(title, content, c, mohlateTahvil);
        } else {
            post = ui.newPost(title, content, c);
        }
        posts.add(post);
        closeDialog();
    }

    
    // Edit post
    @FXML JFXDialog dlg_editPost;
    @FXML HTMLEditor hEdit_editPostContent; 
    @FXML JFXTextField hEdit_editPostTitle, txt_edit_tamrin_Day, txt_edit_tamrin_Hour; 
    @FXML HBox hbx_edit_mohlateTahvil;
    @FXML void btn_editPostDialog_click() {
        if (currentCourse.containsKey("course")) {
            Post p = (Post)currentPost.get("post");
            hEdit_editPostContent.setHtmlText(p.getContent());
            hEdit_editPostTitle.setText(p.getTitle());
            if (p.getType()==Post.TYPE_TAMRIN_POST) {
                hbx_edit_mohlateTahvil.setVisible(true);
                TamrinPost tp = (TamrinPost)p;
                long tobeTime = tp.getTahvilTime().getTimeStamp() - PersianDateTime.now().getTimeStamp();
                txt_edit_tamrin_Day.setText(""+(tobeTime/86400000l));
                txt_edit_tamrin_Hour.setText(""+((tobeTime%86400000l)/3600000l));
            } else {
                hbx_edit_mohlateTahvil.setVisible(false);
            }
            showDialog(dlg_editPost);
        }
    }
    
    @FXML void btn_saveEditedPost_click() {
        Post p = (Post)currentPost.get("post");
        String content = hEdit_editPostContent.getHtmlText();
        String title = hEdit_editPostTitle.getText();
        gui.getUi().editPost(title, content, p);

        if (p.getType()==Post.TYPE_TAMRIN_POST) {
            long currentTimestamp = PersianDateTime.now().getTimeStamp();
            long tahvilTimestamp = currentTimestamp + Integer.parseInt(txt_edit_tamrin_Hour.getText())*3600000 + Integer.parseInt(txt_edit_tamrin_Day.getText())*86400000;
            tahvilTimestamp+=300000; // 5min
            PersianDateTime mohlateTahvil=new PersianDateTime( tahvilTimestamp );
            TamrinPost tp = (TamrinPost)p;
            tp.setTahvilTime(mohlateTahvil);
        }

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
            Post p = (Post)currentPost.get("post");
            
            
            // Tamrin Answer
            if ( p.getType() == Post.TYPE_TAMRIN_POST ) {
                TamrinPost tp = (TamrinPost)p;
                
                if(tp.getTahvilTime().before(PersianDateTime.now())) {
                    System.out.println("TahvilTime finished.");
                    return;
                }
                
                FileChooser fileChooser = new FileChooser();
                File file = fileChooser.showOpenDialog(gui.getStage());
                System.out.println("selected file : "+file);
                Comment cmt = gui.getUi().addAnswer(file, tp);
                if (cmt!=null) {
                    DataBase db = DataBase.getInstance();
                    Set<Integer> cData = comments.getDataList().keySet();
                    Integer[] currentCId = new Integer[cData.size()];
                    cData.toArray(currentCId);
                    
                    // sync showing comments with post (old answers may be removed)
                    for (Integer id: currentCId)
                        if(!db.containsMessage(id))
                            comments.remove(id);
                    
                    comments.add(cmt);
                }
            }else{
                hEdit_newCommentContent.setHtmlText("<html dir=\"rtl\"><head></head><body contenteditable=\"true\"></body></html>");
                showDialog(dlg_newComment);
            }
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