/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.helper;

import helper.NameValue;
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

    String contains=null;

    String x=null;
    String is=null;

    
    // Setter
    public void setContains(String contains) {
        this.contains = contains;
    }
    public void setX(String x) {
        this.x = x;
    }
    public void setIs(String is) {
        this.is = is;
    }
    
    // Getter
    public String getContains() {
        return contains;
    }
    public String getX() {
        return x;
    }
    public String getIs() {
        return is;
    }
    
    
    //should have method that call from ModelControl to check statement and do stuff (+statement field)  
    public void checkStatement() {
        checkStatement(null);
    }
    public void checkStatement(NameValue data) {
        boolean ok = true;
        if (contains!=null)
            ok = ok && data.containsKey(contains);
        
        if (x!=null && is!=null) {
            if (data!=null)
                ok = ok && data.containsKey(x) && data.getString(x).equals(is);
            else
                ok = ok && x.equals(is);
        }
        
        statement(ok);
    }
    
    protected void statement(boolean ok) {
        Pane father = (Pane)getParent();
        ObservableList<Node> brothers = father.getChildren();
        int myid=brothers.indexOf(this);
        ObservableList<Node> childs = getChildren();

        if (ok) {
            while ( childs.size()>0 )
                brothers.add(myid, childs.get( childs.size()-1 ) );
            brothers.remove(this);
        } 
        childs.clear();
    }
    
   
}
