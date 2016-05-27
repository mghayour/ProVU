/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;



//import javafx.css.StyleableObjectProperty
//import com.fxexperience.javafx.animation.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import static gui.Helper.*;
//import gui.animation.*;

/**
 *
 * @author Ghayour
 */
public class MessageBoardController implements Initializable {
    
    @FXML private Pane rooter;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addBackground(rooter, 0.3, 1);
    }

    

    
}
