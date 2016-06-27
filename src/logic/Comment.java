/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import helper.PersianDateTime;

/**
 *
 * @author Ghayour
 */
public class Comment extends Message {

    public Comment(String content, User sender, PersianDateTime sendTime) {
        super(content, sender, sendTime);
    }
    public Comment(String content, User sender) {
        super(content, sender);
    }
    
    
    
}
