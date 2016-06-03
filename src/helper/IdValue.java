/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.util.*;

/**
 *
 * @author Ghayour
 */
public class IdValue<T> extends HashMap<Integer, T> implements Iterable<T> { // need update handling for more featurs !?
    
    void put (int id, T obj) {
        super.put( (Integer)id, obj);
    }
    T get (int id) {
        return super.get( (Integer)id );
    }
    T remove(int id) {
        return super.remove( (Integer)id );
    }
    
    
    public Iterator<T> iterator() {
        return values().iterator();
    }
    
}
