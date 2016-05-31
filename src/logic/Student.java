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
public class Student extends User{

    public Student(String firstName, String lastName, String password) {
        super(firstName, lastName, password);
    }

    
    @Override
    int getType() {
        return User.TYPE_STUDENT;
    }

}
