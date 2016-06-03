/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;



//import javafx.css.StyleableObjectProperty
//import com.fxexperience.javafx.animation.*;
import com.jfoenix.controls.*;
import com.jfoenix.controls.JFXDialog.DialogTransition;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.*;

import static gui.Helper.*;
import gui.helper.ModelControlCollection;
import helper.NameValue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.StringProperty;
import logic.*;
//import gui.animation.*;

/**
 *
 * @author Ghayour
 */
public class CourseBoardController extends MyController {
    
    @FXML private VBox vbx_myCourseContent, vbx_allCourseContent;
    
    private ModelControlCollection myCourses = null, allCourses = null;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        super.initialize(url, rb);
    }

    @Override
    public void setGui(GuiController gui) {
        System.out.println("SET GUI");
        super.setGui(gui);
       
        DataBase db = DataBase.getInstance();
        User u = gui.getUi().getUser();
        System.out.println(u.getClass().toString());
        

        myCourses = new ModelControlCollection(vbx_myCourseContent, "model/myCourseInCourseBoard.fxml",
                new NameValue("section","myCourse",   "userType",u.getTypeString() )) {
                    
            @Override
            
            public void onButtonClick (NameValue data, String btnId) {
                
                if (btnId.equals("btn_delete")) {
                    //remove course
                    int id = data.getInt("id");
                    u.getCourses().remove(id);
                    db.getCourse().remove(id);
                    remove(id);
                    allCourses.remove(id);
                }

                if (btnId.equals("btn_addStudent")) {                    
                    // show  addStudent dialog
                    currentCourseId = data.getInt("id");
                    showDialog(dlg_addStudent);
                }
}
        };

        allCourses = new ModelControlCollection(vbx_allCourseContent, "model/myCourseInCourseBoard.fxml",
                new NameValue("section","allCourse",   "userType",u.getTypeString() )) {
                    
            @Override
            public void onButtonClick (NameValue data) {
                //Todo: add to my courses
            }
        };
        
        
        for(Course c: u.getCourses())
            myCourses.add(c);
        
        for(Course c: db.getCourse())
            allCourses.add(c);
        
        
    }
    
    

    
    
    // Add course dialog
    @FXML JFXDialog dlg_addCourse;
    @FXML JFXTextField txt_courseName;
    @FXML void plusBtnClicked () {
        showDialog(dlg_addCourse);
    }

    @FXML void btn_addCourse_clicked() {
        DataBase db = DataBase.getInstance();
        Teacher teacher = (Teacher)gui.getUi().getUser();
        
        String courseName = txt_courseName.getText();
        Course course = new Course(courseName, teacher);
        db.addCourse(course);
        teacher.addCourse(course);
        myCourses.add(course);
        allCourses.add(course);
        
        closeDialog();
    }

    
    
    
    
    // Add Student dialog
    @FXML JFXDialog dlg_addStudent;
    int currentCourseId=0;
    @FXML JFXTextField txt_studentName;
    @FXML void btn_addStudent_clicked() {
        DataBase db = DataBase.getInstance();
        String username = txt_studentName.getText();
        try {
            User student = db.getUserByUsername(username);
            db.getCourse().get(currentCourseId).addStudent(student);
        } catch (Exception ex) {
            Logger.getLogger(CourseBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        closeDialog();
    }
    
    
    
}
