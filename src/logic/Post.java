/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import helper.NameValue;
import helper.PersianDateTime;
import java.util.*;
import javafx.beans.property.*;

/**
 *
 * @author Ghayour
 */
public class Post extends Message{
    String title;
    ArrayList<Comment> comments = new ArrayList<>();

    public Post(String title, String content, User sender, PersianDateTime sendTime) {
        super(content, sender, sendTime);
        this.title = title;
    }

    Post(String title, String content, User sender) {
        this(title, content, sender, PersianDateTime.now());
    }
            
    @Override
    public NameValue toNameValue() {
        if (myNameValue==null) {
            myNameValue = super.toNameValue();
            myNameValue.put("title", new SimpleStringProperty(""+title) );
            myNameValue.put("commentCount", new SimpleStringProperty(""+comments.size()) );
        } 
        return myNameValue;
    }
    public static NameValue toEmptyNameValue () {
        NameValue res = Message.toEmptyNameValue();
        res.put("title", "");
        res.put("commentCount", "");
        return res;
    }

    // Setter
    
    public void setTitle(String title) {
        this.title = title;
        if (myNameValue != null)
            myNameValue.getStringProperty("title").set(title);
    }

    public void addComment(Comment cmt) {
        comments.add(cmt);
        myNameValue.getStringProperty("commentCount").set(""+comments.size());
    }
    
    // Getter
    public List<Comment> getComments() {
        return comments;
    }

    public String getTitle() {
        return title;
    }

    
    
    

    
    
}
