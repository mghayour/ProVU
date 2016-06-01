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
public class Answer extends Comment {
    String fileName;

    public Answer(String fileName, String content, User sender, PersianDateTime sendTime) {
        super(content, sender, sendTime);
        this.fileName = fileName;
    }
    
    
}
