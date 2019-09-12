package com.dbmsproject;

import java.util.Map;

/**
 * @author akhil
 */

// This class represents a User
public class User extends DBObject {
    int id;
    String username;
    String password;

    User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    User(String username, String password) {
        this(-1, username, password);
    }

    User(Map<String, Object> user) {
        this.id = Integer.valueOf(user.get("id").toString());
        this.username = user.get("username").toString();
        this.password = user.get("password").toString();
    }

    int getId() {
        return this.id;
    }

    void setId(int id) {
        this.id = id;
    }

    @Override
    String getValues() {
        return String.format("values (%s, %s, %s);", id, username, password);
    }

    boolean verify(User user) {
        return this.password.equals(Utils.encrypt(user.password));
    }
}
