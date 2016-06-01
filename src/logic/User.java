/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import helper.NameValue;
import helper.PersianDateTime;
import java.io.Serializable;

/**
 *
 * @author Ghayour
 */
public abstract class User implements IdNeeded, Modelable, Serializable{
    public static final int TYPE_TEACHER = 1, TYPE_STUDENT = 2;
    abstract int getType();

    private int id=0;
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private PersianDateTime registerTime;

    
    public User(String userName, String firstName, String lastName, String password, PersianDateTime registerTime) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.registerTime = registerTime;
    }
    
    public User(String userName, String firstName, String lastName, String password) {
        this(userName, firstName, lastName, password, PersianDateTime.now());
    }

 
    // Setter
    @Override
    public void setId(int id) { this.id = id; } // just should use in DB.add !
    
    //Getter
    public int getId() {
        return id;
    }
    public String getUserName() {
        return userName;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public PersianDateTime getRegisterTime() {
        return registerTime;
    }
    public String getName() {
        return firstName.substring(0, 1)+". "+lastName;
    }

    public Boolean checkPassword(String pass) {
        return (pass == null ? password == null : pass.equals(password));
    }
    
    @Override
    public NameValue toNameValue() {
        NameValue res = new NameValue();
        res.put("id", id);
        res.put("name", getName());
        res.put("registerTime", registerTime.toString());
        
        return res;
    }

    @Override
    public String toString() {
        return getName();
    }
    
    
    
}
