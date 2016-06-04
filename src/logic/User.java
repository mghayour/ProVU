/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import helper.IdValue;
import helper.NameValue;
import helper.PersianDateTime;
import java.io.Serializable;
import java.util.*;

/**
 *
 * @author Ghayour
 */
public abstract class User extends ObjectFather {
    public static final int TYPE_TEACHER = 1, TYPE_STUDENT = 2;
    public abstract int getType();
    public abstract String getTypeString();

    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private PersianDateTime registerTime;
    protected IdValue<Course> courses;
    
    
    public User(String userName, String firstName, String lastName, String password, PersianDateTime registerTime) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.registerTime = registerTime;
        this.courses = new IdValue<>();
    }
    
    public User(String userName, String firstName, String lastName, String password) {
        this(userName, firstName, lastName, password, PersianDateTime.now());
    }
 
    
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
    public IdValue<Course> getCourses() {
        return courses;
    }
    
    
    public Boolean checkPassword(String pass) {
        return (pass == null ? password == null : pass.equals(password));
    }
    
    @Override
    public NameValue toNameValue() {
        if (myNameValue==null) {
            myNameValue = super.toNameValue();
            myNameValue.put("name", getName());
            myNameValue.put("type", getTypeString());
            myNameValue.put("firstName", getFirstName());
            myNameValue.put("lastName", getLastName());
            myNameValue.put("username", getUserName());
            myNameValue.put("registerTime", registerTime.toString());
        }
        
        return myNameValue;
    }

    @Override
    public String toString() {
        return getName();
    }

    public void addCourse(Course course) {
        courses.put(course.getId(),course);
    }

    void removeCourse(int cid) {
        courses.remove(cid);
    }
    
    
    
}
