/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 *
 * @author Ghayour
 */
public abstract class MyController implements Initializable {
    
    protected GuiController gui;
    public void setGui(GuiController gui) {
        this.gui = gui;
    }

}
