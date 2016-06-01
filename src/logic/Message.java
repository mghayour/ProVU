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
public class Message implements IdNeeded, Modelable, Serializable{
    private int id=0;
    private String content;
    private User sender;
    private PersianDateTime sendTime;

    public Message(String content, User sender, PersianDateTime sendTime) {
        this.content = content;
        this.sender = sender;
        this.sendTime = sendTime;
    }
    
    
    
    @Override
    public void setId(int id) { this.id = id; } // just should use in DB.add !
    
    @Override
    public NameValue toNameValue() {
        NameValue res = new NameValue();
        res.put("id", id);
        res.put("content", content);
        res.put("sender", sender.getName());
        res.put("sendTime", sendTime.toString());
        
        return res;
    }


    
 }
