/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.helper;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 *
 * @author Ghayour
 */
public class If extends Pane {

    
    public StringProperty btn_delete = new SimpleStringProperty("");
    
    public StringProperty btn_deleteProperty() {
        return btn_delete;
    }

    public String getBtn_delete() {
        return btn_delete.getValue();
    }

    public void setBtn_delete(String val) {
        this.btn_delete.set(val);
    }
/*
    public If() {
        System.out.println("Cunstructor !");
//          fill.addListener((obs, oldFill, newFill) -> filled.setFill(Paint.valueOf(newFill)));


    }
*/
    @Override
    public ObservableList<Node> getChildren() {
  /*      System.out.println("     getChild");
        System.out.println("     childs:"+children.size());
        System.out.println("     Pchilds:"+ ((Pane)getParent()).getChildren().size());
*/        
        return children;
        //return super.getChildren(); //To change body of generated methods, choose Tools | Templates.
    }

    
   
  // should have method that call from ModelControl to check statement and do stuff (+statement field)  
    

    
}
