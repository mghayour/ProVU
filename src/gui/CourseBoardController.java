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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import logic.*;
//import gui.animation.*;

/**
 *
 * @author Ghayour
 */
public class CourseBoardController extends MyController {
    
    @FXML private VBox vbx_myCourseContent, vbx_allCourseContent, vbx_CourseStudentContents;
    
    private ModelControlCollection myCourses, allCourses, courseStudents;

    
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
              toNameValue().combine(new NameValue("section","myCourse", "page","CourseBoard"))  ) {
                    
            @Override            
            public void onButtonClick (NameValue data, String btnId) {

                if (btnId.equals("btn_delete")) {
                    //remove my course (student)
                    int id = data.getInt("id");
                    myCourses.remove(id);
                    gui.getUi().removeCourse(id);
                }

                if (btnId.equals("btn_showCourse")) {        
                    // show course students (teacher)
                    currentCourseId = data.getInt("id");
                    currentCourseName.set(data.getString("name"));
                    showCourseContent();
                }
}
        };

        allCourses = new ModelControlCollection(vbx_allCourseContent, "model/myCourseInCourseBoard.fxml",
              toNameValue().combine(new NameValue("section","allCourse", "page","CourseBoard"))  ) {
                    
            @Override
            public void onButtonClick (NameValue data) {
                //add to my courses
                int id = data.getInt("id");
                gui.getUi().addMeToCourse(id);
                myCourses.add(db.getCourse(id));
            }
        };

        
        if (u.getType()==User.TYPE_TEACHER)
        courseStudents = new ModelControlCollection(vbx_CourseStudentContents, "model/StudentInCourseBoard.fxml",
              toNameValue().combine(new NameValue("section","courseStudents"))  ) {
                    
            @Override
            public void onButtonClick (NameValue data) {
                // remove student from course
                int uid = data.getInt("id");
                gui.getUi().removeCourseFromUser(currentCourseId, uid);
                remove(uid);
            }
        };
        
        
        
        for(Course c: db.getCourse())
            allCourses.add(c);
        
        for(Course c: u.getCourses())
            myCourses.add(c);
        
    }

    
    StringProperty currentCourseName = new SimpleStringProperty("");
    @Override
    public NameValue toNameValue() {
        if (myNameValue==null) {
            myNameValue = super.toNameValue();
            myNameValue.put("currentCourseName",currentCourseName);
        }
        return myNameValue;
    }
    
    

    
    
    @FXML void btn_plusStudentClicked() {
        if (currentCourseId>=0)
            showDialog(dlg_addStudent);        
    }
    
    @FXML void btn_removeCourseClicked() {
        //remove course
        int id=currentCourseId;
        User u = gui.getUi().getUser();
        if (u.getType()==User.TYPE_TEACHER)
            allCourses.remove(id);
        myCourses.remove(id);
        courseStudents.clear();
        currentCourseId=-1;
        currentCourseName.set("");
        gui.getUi().removeCourse(id);
    }

    void showCourseContent() {
        DataBase db = DataBase.getInstance();
        Course c = db.getCourse(currentCourseId);
        courseStudents.clear();
        for (User u: c.getStudents())
            courseStudents.add(u);
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
    int currentCourseId=-1;
    @FXML JFXTextField txt_studentName;
    @FXML void btn_addStudent_clicked() {
        try {
            DataBase db = DataBase.getInstance();
            String username = txt_studentName.getText();
            User u = db.getUserByUsername(username);
            gui.getUi().addStudentToCourse(u, currentCourseId);
            courseStudents.add(u);
            
            closeDialog();
        } catch (Exception ex) {
            Logger.getLogger(CourseBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
