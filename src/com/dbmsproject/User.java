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
    long phone;

    User(int id, String username, String password, String email, long phone) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    User(String username, String password) {
        this(-1, username, password, "", -1);
    }

    User(Map<String, Object> user) {
        this(Integer.parseInt(user.get("id").toString()), user.get("username").toString(), user.get("password").toString(), user.get("email").toString(), Long.parseLong(user.get("phone").toString()));
    }

    @Override
    String getValues() {
        return String.format("values (%s, \'%s\', \'%s\', \'%s\', %s);", id, username, password, email, phone);
    }

    boolean verify(User user) {
        return this.password.equals(Utils.encrypt(user.password));
    }

}
