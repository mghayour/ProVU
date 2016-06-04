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
        

        myCourses = new ModelControlCollection(vbx_myCourseContent, "model/myCourseInCourseBoard.fxml",
              toNameValue().combine(new NameValue("section","myCourse"))  ) {
                    
            @Override            
            public void onButtonClick (NameValue data, String btnId) {
                
                if (btnId.equals("btn_delete")) {
                    //remove course
                    int id = data.getInt("id");
                    if (u.getType()==User.TYPE_TEACHER)
                        allCourses.remove(id);
                    myCourses.remove(id);
                    gui.getUi().removeCourse(id);
                }

                if (btnId.equals("btn_addStudent")) {                    
                    // show  addStudent dialog
                    currentCourseId = data.getInt("id");
                    showDialog(dlg_addStudent);
                }
}
        };

        allCourses = new ModelControlCollection(vbx_allCourseContent, "model/myCourseInCourseBoard.fxml",
              toNameValue().combine(new NameValue("section","allCourse"))  ) {
                    
            @Override
            public void onButtonClick (NameValue data) {
                //add to my courses
                int id = data.getInt("id");
                gui.getUi().addMeToCourse(id);
                myCourses.add(db.getCourse().get(id));
            }
        };
        
        
        
        for(Course c: db.getCourse())
            allCourses.add(c);
        
        for(Course c: u.getCourses())
            myCourses.add(c);
        
    }
    
    

    
    
    // Add course dialog
    @FXML JFXDialog dlg_addCourse;
    @FXML JFXTextField txt_courseName;
    @FXML void plusBtnClicked () {
        showDialog(dlg_addCourse);
    }

    @FXML void btn_addCourse_clicked() {
        Course course = gui.getUi().addCourse( txt_courseName.getText() );
        
        myCourses.add(course);
        allCourses.add(course);
        
        closeDialog();
    }

    
    
    
    
    // Add Student dialog
    @FXML JFXDialog dlg_addStudent;
    int currentCourseId=0;
    @FXML JFXTextField txt_studentName;
    @FXML void btn_addStudent_clicked() {
        
        String username = txt_studentName.getText();
        gui.getUi().addStudentToCourse(username, currentCourseId);
        
        closeDialog();
    }
    
    
    
}
