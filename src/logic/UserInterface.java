/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import helper.PersianDateTime;
import java.io.File;
import java.util.List;
import java.util.Scanner;
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

    public void editPost(String title, String content, Post p) {
        p.setContent(content);
        p.setTitle(title);
        p.setSendTime(PersianDateTime.now());
    }

    public Comment newComment(String content, Post post) {
        Comment cmt = new Comment(content, user);
        db.addComment(cmt);
        post.addComment(cmt);
        return cmt;
    }
    
    private void removeComment(Comment cmt, Post post) {
        db.removeComment(cmt);
        post.removeComment(cmt);
    }

    public TamrinPost newTamrin(String title, String content, Course course, PersianDateTime mohlateTahvil) {
        TamrinPost tpost = new TamrinPost(title, content, user, mohlateTahvil);
        db.addPost(tpost);
        course.addPost(tpost);
        return tpost;        
    }

    public Comment addAnswer(File file, TamrinPost post) {
        try {
            
            // reading file
            Scanner scanner = new Scanner(file);
            String fileContents = "";
            while(scanner.hasNextLine())
                fileContents+=scanner.nextLine()+"\n";
            
            // remove last sends by this user
            List<Comment> comments = post.getComments();
            for (int j=0; j<comments.size(); j++) {
                Comment cmt = comments.get(j);
                if (cmt.getSender().equals(user)) {
                    removeComment(cmt, post);
                    j--;
                }
            }
            
            // add new submit
            return newComment(fileContents, post);

        } catch (Exception ex) {
            //System.out.println("ERROR:"+ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }


        
}
