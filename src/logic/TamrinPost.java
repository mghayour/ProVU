/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import helper.NameValue;
import helper.PersianDateTime;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Ghayour
 */
public class TamrinPost extends Post {

    PersianDateTime tahvilTime;

    public TamrinPost(String title, String content, User sender, PersianDateTime sendTime, PersianDateTime tahvilTime) {
        super(title, content, sender, sendTime);
        this.tahvilTime = tahvilTime;
    }
    public TamrinPost(String title, String content, User sender, PersianDateTime tahvilTime) {
        this(title, content, sender, PersianDateTime.now(), tahvilTime);
    }

    
    @Override
    public NameValue toNameValue() {
        if (myNameValue==null) {
            myNameValue = super.toNameValue();
            myNameValue.put("tahvilTime", new SimpleStringProperty(""+tahvilTime) );
            myNameValue.put("type", "tamrinPost" );
        } 
        return myNameValue;
    }
    
    
    public int getType() {
        return TYPE_TAMRIN_POST;
    }

    public PersianDateTime getTahvilTime() {
        return tahvilTime;
    }

    public void setTahvilTime(PersianDateTime tahvilTime) {
        this.tahvilTime = tahvilTime;
        if (myNameValue!=null)
            myNameValue.getStringProperty("tahvilTime").set(""+tahvilTime);
    }
    
    
    
    
}
