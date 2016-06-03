/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXDialog;
import static gui.Helper.addBackground;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import logic.*;

/**
 *
 * @author Ghayour
 */
public abstract class MyController implements Initializable {
    
    @FXML protected StackPane backpane;
    @FXML protected Pane rooter;
    protected SideMenuController sideMenuController = null;
    
    protected GuiController gui;
    public void setGui(GuiController gui) {
        this.gui = gui;
        if (sideMenuController!=null)
            sideMenuController.setGui(gui);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        addBackground(rooter, 0.3, 1);
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SideMenu.fxml"));
            rooter.getChildren().add( (Pane)loader.load() );
            sideMenuController = (SideMenuController)loader.getController();
            System.out.println("Side menu added");
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    // dialog functions
    private JFXDialog currentDialog=null;
    protected void showDialog(JFXDialog dialog) {
        currentDialog=dialog;
        dialog.show(backpane);
    }
    
    @FXML
    protected void closeDialog() {
        currentDialog.close();
    }

}
