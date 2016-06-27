/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import helper.NameValue;
import helper.PersianDateTime;
import java.io.Serializable;
import java.util.*;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Ghayour
 */
public class Message extends ObjectFather implements Modelable {
    
    public static final int TYPE_POST=1, TYPE_TAMRIN_POST=2, TYPE_COMMENT=3;
    
    private String content;
    private User sender;
    private PersianDateTime sendTime;

    public Message(String content, User sender, PersianDateTime sendTime) {
        this.content = content;
        this.sender = sender;
        this.sendTime = sendTime;
    }
    
    public Message(String content, User sender) {
        this.content = content;
        this.sender = sender;
        this.sendTime = PersianDateTime.now();
    }
    
    
    
    @Override
    public NameValue toNameValue() {
        if (myNameValue==null) {
            myNameValue = super.toNameValue();
            myNameValue.put("content", content);
            myNameValue.put("sender", sender.getName());
            myNameValue.put("sendTime", new SimpleStringProperty(""+sendTime) );
        }
        return myNameValue;
    }
    public static NameValue toEmptyNameValue () {
        NameValue res = ObjectFather.toEmptyNameValue();
        res.put("content", "");
        res.put("sender", "");
        res.put("sendTime", "");
        return res;
    }

    //Setter
    public void setContent(String content) {
        this.content = content;
        if (myNameValue != null)
            myNameValue.getStringProperty("conrent").set(content);
    }
    
    public void setSendTime(PersianDateTime sendTime) {
        this.sendTime = sendTime;
        if (myNameValue != null)
            myNameValue.getStringProperty("sendTime").set(""+sendTime);        
    }
    
    
    //Getter
    public String getContent() {
        return content;
    }

    public User getSender() {
        return sender;
    }
    

    
 }
