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
import javafx.application.Platform;
import logic.*;
//import gui.animation.*;

/**
 *
 * @author Ghayour
 */
public class CourseBoardController extends MyController {
    
    @FXML private VBox myCourseBox;
    @FXML private VBox editBox;
    @FXML private HBox boxes;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        super.initialize(url, rb);
    }

    @Override
    public void setGui(GuiController gui) {
        System.out.println("SET GUI");
        super.setGui(gui);

        boxes.getChildren().remove(editBox);
        
        User u = gui.getUi().getUser();
        System.out.println(u.getClass().toString());
        //if (u instanceof Teacher)
            //boxes.getChildren().remove(myCourseBox);
        //else
//            boxes.getChildren().remove(editBox);
            
        //initDialog();
    }
    
    

    @FXML JFXDialog dlg_addCourse;
    @FXML JFXTextField txt_courseName;
    @FXML void plusBtnClicked () {
        showDialog(dlg_addCourse);
    }

    @FXML void btn_addCourse_clicked() {
        Teacher teacher = (Teacher)gui.getUi().getUser();
        String courseName = txt_courseName.getText();
        DataBase db = DataBase.getInstance();
        Course course = new Course(courseName, teacher);
        db.addCourse(course);
        teacher.addCourse(course);
        
        closeDialog();
    }

    
}
