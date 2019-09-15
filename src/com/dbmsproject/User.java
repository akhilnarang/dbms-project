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
    String email;

    User(int id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    User(int id, String username, String password) {
        this(id, username, password, "");
    }

    User(String username, String password) {
        this(-1, username, password);
    }

    User(Map<String, Object> user) {
        this(Integer.parseInt(user.get("id").toString()), user.get("username").toString(), user.get("password").toString(), user.get("email").toString());
    }

    int getId() {
        return this.id;
    }

    void setId(int id) {
        this.id = id;
    }

    @Override
    String getValues() {
        return String.format("values (%s, \'%s\', \'%s\', \'%s\');", id, username, password, email);
    }

    boolean verify(User user) {
        return this.password.equals(Utils.encrypt(user.password));
    }

}
