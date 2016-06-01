/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

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
}
