/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ghayour
 */
public class UserInterface {
    private User user=null;
    private DataBase db = DataBase.getInstance();
    
    public UserInterface() {
    }

    public User getUser() {
        return user;
    }
    
    public boolean login(String username, String password) throws Exception{
        User u = db.getUserByUsername(username);
        user = u.checkPassword(password)? u: null;
        return user!=null;
    }

    public boolean register(String userName, String firstName, String lastName, String password, boolean isTeacher) throws Exception {
        if (db.userExists(userName))
            throw new Exception("user exist before");
                    
        if (isTeacher)
            db.addUser(new Teacher(userName, firstName, lastName, password));
        else
            db.addUser(new Student(userName, firstName, lastName, password));
        
        return true;
    }
    
    
    public Course addCourse (String courseName) {
        if (user.getType() != User.TYPE_TEACHER)
            return null;
                
        Course course = new Course(courseName, (Teacher)user);
        db.addCourse(course);
        user.addCourse(course);

        return course;
    }
    
    public boolean removeCourse (int courseId) {
        if (user.getType()==User.TYPE_TEACHER) {
            Course c = db.getCourse().remove(courseId);
            user.getCourses().remove(courseId);
            for (User s: c.getStudents())
                s.getCourses().remove(courseId);
        } else if (user.getType()==User.TYPE_STUDENT) {
            Course c = db.getCourse().get(courseId);
            c.removeStudent(user);
        }
        
        return true;
    }
    
    public boolean addStudentToCourse(String username, int courseId) {
        try {
            return addStudentToCourse(db.getUserByUsername(username), courseId);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public boolean addMeToCourse(int courseId) {
        return addStudentToCourse(user, courseId);
    }
    public boolean addStudentToCourse(User student, int courseId) {
        db.getCourse(courseId).addStudent(student);
        return true;
    }

    public void removeCourseFromUser(int cid, int uid) {
        db.getCourse(cid).removeStudent(
            db.getUser(uid)
        );
    }

    public Post newPost(String title, String content, Course course) {
        Post post = new Post(title, content, user);
        db.addPost(post);
        course.addPost(post);
        return post;
    }
    
}
