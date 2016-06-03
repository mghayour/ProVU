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
public class Course extends IdNeeder implements Modelable {
    String name;
    IdValue<Student> students;
    Teacher teacher;
    IdValue<Post> posts;
    PersianDateTime createdTime;
    
    // List<User/Student> hallet !?

    public Course(String name, Teacher teacher) {
        this.name = name;
        this.teacher = teacher;
        this.id = -1;
        this.students = new IdValue<>();
        this.posts = new IdValue<>();
        this.createdTime = PersianDateTime.now();
    }


    @Override
    public NameValue toNameValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
}
