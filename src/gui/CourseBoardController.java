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
    
    

    @FXML void plusBtnClicked () {
        showDialog(dialog);
    }

    
    
	@FXML private JFXButton centerButton;

	@FXML private JFXButton topButton;

	@FXML private JFXButton rightButton;

	@FXML private JFXButton bottomButton;

	@FXML private JFXButton leftButton;

	@FXML private JFXButton acceptButton;

	@FXML
	private JFXDialog dialog;

        public void initDialog()  {
            /*
        if(((Pane) context.getRegisteredObject("ContentPane")).getChildren().size() > 0)
                Platform.runLater(()-> ((Pane)((Pane) context.getRegisteredObject("ContentPane")).getChildren().get(0)).getChildren().remove(1));
*/
        centerButton.setOnMouseClicked((e)->{
                dialog.setTransitionType(DialogTransition.CENTER);
                //dialog.show((StackPane) context.getRegisteredObject("ContentPane"));
                dialog.show();
        });

        topButton.setOnMouseClicked((e)->{
                dialog.setTransitionType(DialogTransition.TOP);
                dialog.show();
        });

        rightButton.setOnMouseClicked((e)->{
                dialog.setTransitionType(DialogTransition.RIGHT);
                dialog.show();
        });

        bottomButton.setOnMouseClicked((e)->{
                dialog.setTransitionType(DialogTransition.BOTTOM);
                dialog.show();
        });

        leftButton.setOnMouseClicked((e)->{
                dialog.setTransitionType(DialogTransition.LEFT);
                dialog.show();
        });

        acceptButton.setOnMouseClicked((e)->{
                dialog.close();
        });
}


    
    
}
