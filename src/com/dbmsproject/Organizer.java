package com.dbmsproject;

import java.util.Map;

/**
 * @author akhil
 */

// This class represents a Organizer
public class Organizer extends DBObject {
    int id;
    String username;
    String password;

    Organizer(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }


    Organizer(String username, String password) {
        this(-1, username, password);
    }

    Organizer(Map<String, Object> user) {
        this(Integer.parseInt(user.get("id").toString()), user.get("name").toString(), user.get("password").toString());
    }

    int getId() {
        return this.id;
    }

    void setId(int id) {
        this.id = id;
    }

    @Override
    String getValues() {
        return String.format("values (%s, \'%s\', \'%s\');", id, username, password);
    }

    boolean verify(Organizer user) {
        return this.password.equals(Utils.encrypt(user.password));
    }

}