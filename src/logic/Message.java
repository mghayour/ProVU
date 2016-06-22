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

/**
 *
 * @author Ghayour
 */
public class Message extends ObjectFather implements Modelable {
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
            myNameValue.put("sendTime", sendTime.toString());
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

    
    //Getter
    public String getContent() {
        return content;
    }
    

    
 }
