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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Ghayour
 */
public class Course extends ObjectFather {
    String name;
    IdValue<User> students;
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
        if (myNameValue==null) {
            myNameValue = super.toNameValue();
            myNameValue.put("name", name);
            myNameValue.put("teacherName", teacher.getName() );
            myNameValue.put("studentCount", new SimpleStringProperty(""+students.size()) );
            myNameValue.put("createdDate", createdTime.toDateString() );
            myNameValue.put("createdTime", createdTime.toString() );
        }
        return myNameValue;
    }

    public void addStudent(User user) {
        students.add(user);
        user.addCourse(this);
        ((StringProperty)myNameValue.get("studentCount")).set(""+students.size());
    }

    void removeStudent(User user) {
        students.remove(user.id);
        user.removeCourse(this.id);
        ((StringProperty)myNameValue.get("studentCount")).set(""+students.size());        
    }

    public IdValue<User> getStudents() {
        return students;
    }



    
    
    
    
}
