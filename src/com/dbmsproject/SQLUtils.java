package src.com.dbmsproject;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * @author akhil
 */


// Class to represent a database based on a few properties required to connect to it
class Database {
    String database;
    String host;
    String password;
    String port;
    String user;

    Database(String database, String host, String password, String port, String user) {
        this.database = database;
        this.host = host;
        this.user = user;
        this.password = password;
        this.port = port;
    }

    // Allows us to easily get the new URI if we changed some parameters
    String getConnectionURI() {
        return "mysql:jdbc://" + user + ":" + password + "@" + host + ":" + port + "/" + database;
    }
}

// Class with bunch of helper methods related to basic MySQL queries
public class SQLUtils {
    Connection connection;
    Statement statement;
    JFrame currentFrame;

    SQLUtils(JFrame currentFrame) {
        this.currentFrame = currentFrame;
        // Use the Properties class to load credentials from a file which is ignored by git
        Properties credentials = new Properties();
        try (FileReader fileReader = new FileReader("../../../login.properties")) {
            credentials.load(fileReader);
        } catch (FileNotFoundException e) {
            Utils.showMessage(currentFrame, "Properties file not found!\n" + e.getMessage());
        } catch (IOException e) {
            Utils.showMessage(currentFrame, "IOException occurred!\n" + e.getMessage());
        }
        // Read the all the parameters from the Properties object
        String username = credentials.getProperty("username", "root");
        String password = credentials.getProperty("password", "student123");
        String database = credentials.getProperty("database", "project");
        String host = credentials.getProperty("hostname", "localhost");
        String port = credentials.getProperty("port", "3306");
        try {
            Database db = new Database(database, host, password, port, username);
            Class.forName("java.sql.Driver");
            connection = DriverManager.getConnection(db.getConnectionURI());
            statement = connection.createStatement();
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(currentFrame, "Couldn't find required class!\n" + e.getMessage());
            System.exit(1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(currentFrame, "Couldn't connect to database!\n" + e.getMessage());
            System.exit(1);
        }
    }

    // This method returns a ResultSet based on the select query passed to it
    ResultSet query(String query) {
        try {
            return statement.executeQuery(String.format("%s;", query);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(currentFrame, "Error occurred while running query: " + query + "\n" + e.getMessage());
        }
        return null;
    }

    // This method returns a ResultSet after formulating a query based on the parameters passed to it
    ResultSet selectQuery(String items, String table, String miscellaneous) {
        return this.query(String.format("select %s from %s %s", items, table, miscellaneous));
    }

    // This method returns a ResultSet after formulating a query based on the parameters passed to it
    ResultSet selectQueryWhere(String items, String table, String whereCondition, String miscellaneous) {
        return this.selectQuery(items, table, String.format("where %s %s", whereCondition, miscellaneous));
    }

    // This method inserts values into a table
    int insert(String table, DBObject object) {
        int n = -1;
        // getValues will return a string in the form of values(1, 2, 3, 4) and so on, allowing us to add multiple rows
        String query = String.format("insert into %s %s", table, object.getValues());
        try {
            n = statement.executeUpdate(table);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(currentFrame, "Error occurred while running query: " + query + "\n" + e.getMessage());
        }
        return n;
    }

    // This method updates rows in the table
    int update(String query) {
        int n = -1;
        try {
            n = statement.executeUpdate(query);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(currentFrame, "Error occurred while running query: " + query + "\n" + e.getMessage());
        }
        return n;
    }

    // This method closes the statement and connection objects
    void close() {
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(currentFrame, "Error occurred while closing connection to database!\n" + e.getMessage());
        }
    }
}
