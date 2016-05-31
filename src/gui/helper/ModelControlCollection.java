/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.helper;

import helper.*;
import java.util.*;
import javafx.beans.property.*;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.*;

/**
 *
 * @author Ghayour
 */
public class ModelControlCollection extends ModelControl{
    
    IdValue<NameValue> dataList = new IdValue<NameValue>();
    Pane root=null;
    ObservableList<Node> rootChilds;
    
    public ModelControlCollection(Pane root,String fxmlName) {
        super(fxmlName);
        this.root=root;
        rootChilds = root.getChildren();
    }
    
    // TODO:
    // public void add(Object obj) { generate data with obj.fields and add it
    
    public void add(NameValue rowData) {
        Node element = generate(rowData);
        rowData.put("element", element);
        rootChilds.add(element);
        if (!rowData.containsKey("id"))
            System.out.println("ERROR: data['id'] Not found :"+rowData.toString());
        
        dataList.put(rowData.getInt("id"), rowData);
    }
    
    public void remove(int id) {
        // remove from data list
        NameValue elementData = dataList.remove(id);
        // remove element from parent
        rootChilds.remove((Node)elementData.get("element"));
    }
    
    public NameValue get(int id) {
        return dataList.get(id);
    }
    
}
