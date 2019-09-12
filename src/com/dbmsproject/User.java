package src.com.dbmsproject;

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

    int getId() {
        return this.id;
    }

    @Override
    String getValues() {
        return String.format("values (%s, %s, %s);", id, username, password);
    }
}
