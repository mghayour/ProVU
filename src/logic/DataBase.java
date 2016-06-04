/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import helper.IdValue;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ghayour
 */
public class DataBase implements Serializable{
    
    private IdValue<User> user;
    private IdValue<Message> message;
    private IdValue<Course> course;

    private int userAutoId=0;
    private int messageAutoId=0;
    private int courseAutoId=0;
    
    
    private static DataBase instance = null;
    public static DataBase getInstance() {
        if (instance == null)
            loadDB();
        return instance;
    }
    
    
    private DataBase() {
        user = new IdValue<>();
        message = new IdValue<>();
        course = new IdValue<>();
    }
    
    private static void loadDB() {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                try {
                    instance.finalize();
                } catch (Throwable ex) {
                    ex.printStackTrace();
                }
            }
        }));


        // load
        try {
            // Read from disk using FileInputStream
            FileInputStream f_in = new FileInputStream("DataBase.data");

            // Read object using ObjectInputStream
            ObjectInputStream obj_in;
                obj_in = new ObjectInputStream (f_in);

            // Read an object
            Object obj = obj_in.readObject();

            if (obj instanceof DataBase)
                // Cast object to a Vector
                instance = (DataBase) obj;
            else
                throw new Exception("Object is not a DataBase");
        } catch (Exception ex) {
            ex.printStackTrace();
            instance = new DataBase();
        }
        
        
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Saving DataBase ...");
        
        // Write to disk with FileOutputStream
        FileOutputStream f_out = new FileOutputStream("DataBase.data");

        // Write object with ObjectOutputStream
        ObjectOutputStream obj_out = new ObjectOutputStream (f_out);

        // Write object out to disk
        obj_out.writeObject (this);
        
        super.finalize(); //To change body of generated methods, choose Tools | Templates.
    }
    
    

    // Add Objects
    public void addUser(User u) {
        u.setId(userAutoId++);
        user.add(u);
    }
    public void addCourse(Course c) {
        c.setId(courseAutoId++);
        course.add(c);
    }
    
    
    // GetObjects
    public User getUserByUsername(String uname) throws Exception {
        for (User u: user)
            if (u.getUserName().equals(uname))
                return u;
        
        throw new Exception("User["+uname+"] not found");
    }

    public boolean userExists(String uname) {
        for (User u: user)
            if (u.getUserName().equals(uname))
                return true;
        return false;
    }

    public IdValue<Course> getCourse() {
        return course;
    }

    public Course getCourse(int cid) {
        return course.get(cid);
    }

    User getUser(int uid) {
        return user.get(uid);
    }

    
    
    
}
