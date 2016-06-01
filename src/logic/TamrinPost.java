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
public class TamrinPost extends Post {

    PersianDateTime tahvilTime;

    public TamrinPost(String title, String content, User sender, PersianDateTime sendTime, PersianDateTime tahvilTime) {
        super(title, content, sender, sendTime);
        this.tahvilTime = tahvilTime;
    }

    
    
}
