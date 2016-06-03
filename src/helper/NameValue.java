/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.util.*;
import javafx.beans.property.*;
import javafx.scene.control.Labeled;

/**
 *
 * @author Ghayour
 */
public class NameValue extends HashMap<String, Object> implements Iterable<Map.Entry<String, Object>> {

    public NameValue() {
        super();
    }

    public NameValue(String... data) {
        for(int i=0; i<data.length-1; i+=2)
            put(data[i],data[i+1]);
    }
    
    /*
    public void putAll (Iterable<Entry<String, Object>> objs) {
        for(Entry<String, Object> e: objs)
            put(e);
    }
    */
    
    public void put (Entry<String, Object> obj) {
        put (obj.getKey(), obj.getValue());
    }
    
    public StringProperty getStringProperty(String name) {
        if (!containsKey(name)) {
            System.out.println("ERROR: key["+name+"] not found.  NameValue:"+toString());
            return new SimpleStringProperty("");
        }

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

    public String getString(String name) {
        if (!containsKey(name)) {
            System.out.println("ERROR: key["+name+"] not found.  NameValue:"+toString());
            return "";
        }

        Object obj = get(name);
        
        if (obj instanceof String)
            return (String)obj;
        
        if ( StringProperty.class.isAssignableFrom(obj.getClass()) )
            return ((StringProperty)obj).getValue();
        
        return obj.toString();
    }
    

    @Override
    public Iterator<Entry<String, Object>> iterator() {
        return entrySet().iterator();
    }
    
}
