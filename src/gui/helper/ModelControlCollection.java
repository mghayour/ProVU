/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.helper;

import helper.*;
import java.util.*;
import java.util.Map.Entry;
import javafx.beans.property.*;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.*;
import logic.Modelable;

/**
 *
 * @author Ghayour
 */
public class ModelControlCollection extends ModelControl{
    
    NameValue defaultData;
    IdValue<NameValue> dataList = new IdValue<NameValue>();
    Pane root=null;
    ObservableList<Node> rootChilds;
    
    public ModelControlCollection(Pane root, String fxmlName, NameValue defaultData) {
        super(fxmlName);
        this.root=root;
        rootChilds = root.getChildren();
        this.defaultData = defaultData;
    }
    public ModelControlCollection(Pane root, String fxmlName) {
        this(root, fxmlName, new NameValue());
    }
    
    public void add(Modelable obj) { //generate data with obj.fields and add it
        add(obj.toNameValue());
    }
    
    public void add(NameValue rowData) {
        if (!rowData.containsKey("id")) {
            //System.out.println("ERROR: data['id'] Not found :"+rowData.toString());
            rowData.put("id",dataList.size()); // AutoIncreament Id
        }
        
        // default data should add to all of rows !
        rowData.putAll(defaultData);
        
            
        
        Node element = generate(rowData);
        rowData.put("element", element);
        rootChilds.add(element);
        
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
