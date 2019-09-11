package src.com.dbmsproject;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author akhil
 */
public class User extends DBObject {
    int id;
    String username;
    String password;
    
    User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
    
    int getId() {
        return this.id;
    }
    
    @Override
    String getValues() {
        return String.format("values (%s, %s, %s);", id, username, password);
    }
}
