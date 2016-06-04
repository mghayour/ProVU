/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gui.helper.ModelControl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.geometry.NodeOrientation;

import helper.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.*;
import javafx.scene.layout.Pane;
import logic.DataBase;
import logic.Student;
import logic.Teacher;
import logic.User;
import logic.UserInterface;
import org.joda.time.DateTime;

/**
 * ملاک ما نسخه 6 جاوا
 * @author Ghayour
 */
public class GuiController extends Application {
    
    private UserInterface ui = new UserInterface();
    private Stage stage;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        DataBase db = DataBase.getInstance();
        /***
        db.user.add(
                new Student("mghayour", "مهدی", "غیور", "test")
        );/**
        System.out.println(db.user.toString());
        System.out.println(db.user.size());
*/
        launch(args);
    }
    
    
    
    
    
    
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        
        DataBase db = DataBase.getInstance();
        if (! db.userExists("mnouri"))
            db.addUser(new Teacher("mnouri", "مصطفی", "نوری", "test"));
        
        //ui.login("mghayour", "test");
        ui.login("mnouri", "test");
        //gotoPage("Workspace");
        //gotoPage("Login");
        //Thread.sleep(2000);
        gotoPage("Dashboard");
        //gotoPage("Messageboard");
        //gotoPage("CourseBoard");
        /*
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("MessageBoard.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("CourseBoard.fxml"));


        Parent root = loader.load();
        ((LoginController)loader.getController()).setGui(this);
        //root.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

        Scene scene = new Scene(root);
        //scene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        stage.setScene(scene);
        stage.setMaximized(true);
        //setFullScreen(stage);
        stage.show();
*/
        
    }

    public UserInterface getUi() {
        return ui;
    }

    private Pane loadFxml(String fname) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fname));
        Pane root = (Pane)loader.load();
        MyController controller = (MyController)loader.getController();
        controller.setGui(this);
        
        // parse data
        root = (Pane) new ModelControl("",false).generate(
                root,
                controller.toNameValue()
                );
        
        return root;
    }
    
    void gotoPageSafe(String name) {
        try {
            gotoPage(name);
        } catch (IOException ex) {
            Logger.getLogger(GuiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void gotoPage(String name) throws IOException {
        Pane root = loadFxml(name+".fxml");
        if (!name.equals("Login")) {
            root.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        }

        stage.setMaximized(false);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(true);
        
        /*
        if (!stage.isFullScreen())
            //stage.setMaximized(true);
            stage.setFullScreen(true);
                */
        stage.show();
    }

    void gotoDashBoard() {
        try {
            gotoPage("DashBoard");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    
    

    
}
