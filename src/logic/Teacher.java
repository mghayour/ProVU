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
public class Teacher extends User {

    public Teacher(String userName, String firstName, String lastName, String password) {
        super(userName, firstName, lastName, password);
    }


    
    @Override
    public int getType() {
        return User.TYPE_TEACHER;
    }

    @Override
    public String getTypeString() {
        return "Teacher";
    }
    
}
