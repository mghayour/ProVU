/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.util.HashMap;
import javafx.beans.property.*;
import javafx.scene.control.Labeled;

/**
 *
 * @author Ghayour
 */
public class NameValue extends HashMap<String, Object> {
    
    public StringProperty getStringProperty(String name) {
        Object obj = get(name);
        if ( StringProperty.class.isAssignableFrom(obj.getClass()) )
            return (StringProperty)obj;
        
        return new SimpleStringProperty(obj.toString());
    }
    
    public int getInt(String name) {
        Object obj = get(name);
        int res=0;
        try {
            String str;
            if ( StringProperty.class.isAssignableFrom(obj.getClass()) )
                str = ((StringProperty)obj).get();
            else
                str = obj.toString();
            res = Integer.parseInt(str);
        } catch (java.lang.NumberFormatException e) {
            System.out.println("Cant parse int: "+obj.toString());
        }
        return res;
    }
    
}
