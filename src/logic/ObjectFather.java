/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import helper.NameValue;
import java.io.Serializable;

/**
 *
 * @author Ghayour
 */
public class ObjectFather implements IdNeeded, Serializable, Modelable {
    protected int id;
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    
    protected transient NameValue myNameValue = null;
    @Override
    public NameValue toNameValue() {
        NameValue res = new NameValue();
        res.put("id", id);
        return res;
    }

}
