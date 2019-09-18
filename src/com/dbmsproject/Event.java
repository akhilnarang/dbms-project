package com.dbmsproject;

/**
 * @author akhil
 */

import java.util.Map;

public class Event extends DBObject {
    int id;
    String name;
    String location;

    Event(int id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }


    Event(String username, String password) {
        this(-1, username, password);
    }

    Event(Map<String, Object> event) {
        this(Integer.parseInt(event.get("id").toString()), event.get("name").toString(), event.get("location").toString());
    }

    @Override
    String getValues() {
        return String.format("values (%s, \'%s\', \'%s\');", id, name, location);
    }
}
