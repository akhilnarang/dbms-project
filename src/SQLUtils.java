import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author akhil
 */

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
    
    String getConnectionURI() {
        return "mysql:jdbc://" + user + ":" + password + "@" + host + ":" + port + "/" + database;
    }
}

public class SQLUtils {
    Connection connection;
    Statement statement;
    JFrame currentFrame;
 
    SQLUtils(JFrame currentFrame) {
        this.currentFrame = currentFrame;
        Properties credentials = new Properties();
        try (FileReader fileReader = new FileReader("../login.properties")) {
            credentials.load(fileReader);
        }
        String username = credentials.getProperty("username", "root");
        String password = credentials.getProperty("password", "student123");
        String database = credentials.getProperty("database", "project");
        String host = credentials.getProperty("hostname", "localhost");
        String port = credentials.getProperty("port", "3306");
        try {
            Database db = new Database(database, host, password, port, user);
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
    
    ResultSet query(String query) {
        try {
            return statement.executeQuery(query);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(currentFrame, "Error occurred while running query: " + query + "\n" + e.getMessage());
        }
        return null;
    }
    
    ResultSet selectQuery(String items, String table, String miscellaneous) {
        return this.query(String.format("select %s from %s %s", items, table, miscellaneous));
    }
    
    ResultSet selectQueryWhere(String items, String table, String whereCondition, String miscellaneous) {
        return this.selectQuery(items, table, String.format("where %s %s", whereCondition, miscellaneous));
    }
    
    int insert(String table, DBObject object) {
        int n = -1;
        String query = String.format("insert into %s %s", table, object.getValues());
        try {
            n = statement.executeUpdate(table);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(currentFrame, "Error occurred while running query: " + query + "\n" + e.getMessage());          
        }
        return n;
    }
    
    int update(String query) {
        int n = -1;
        try {
            n = statement.executeUpdate(query);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(currentFrame, "Error occurred while running query: " + query + "\n" + e.getMessage());
        }
        return n;
    }
    
    void close() {
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(currentFrame, "Error occurred while closing connection to database!\n" + e.getMessage());
        }
    }
}
