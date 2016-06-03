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
        

        myCourses = new ModelControlCollection(vbx_myCourseContent, "model/myCourseInCourseBoard.fxml") {
            @Override
            public void onButtonClick (NameValue data) {
                int id = data.getInt("id");
                u.getCourses().remove(id);
                remove(id);
                allCourses.remove(id);
            }
        };

        allCourses = new ModelControlCollection(vbx_allCourseContent, "model/myCourseInCourseBoard.fxml") {
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

    
}
