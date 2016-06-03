/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.io.Serializable;

/**
 *
 * @author Ghayour
 */
public class IdNeeder implements IdNeeded, Serializable {
    protected int id;
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

}
