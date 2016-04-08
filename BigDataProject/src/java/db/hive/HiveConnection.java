package db.hive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class HiveConnection {

    private static final String DRIVER = "org.apache.hive.jdbc.HiveDriver";
    private static Connection connection;
    private static Statement statement;

    public static Statement getStatement(){
        if (statement == null) {
            try {
                if (connection == null) {
                    Class.forName(DRIVER);
                    connection = DriverManager.getConnection("jdbc:hive2://bigdatalite.localdomain:10000/", "oracle", "welcome1");
                    System.out.println("connect!");
                }
                statement = connection.createStatement();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return statement;
    }
    
    public static void close() {
        try {
            statement.close();
            connection.close();
            connection = null;
            statement = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
