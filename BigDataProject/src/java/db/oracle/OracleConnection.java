package db.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleConnection {

    private static final String DRIVER = "oracle.jdbc.OracleDriver";
    private static Connection connection;
    private static Statement statement;

    public static Statement getStatement(){
        if (statement == null) {
            try {
                if (connection == null) {
                    Class.forName(DRIVER);
                    connection = DriverManager.getConnection("jdbc:oracle:thin:@bigdatalite.localdomain:1521:cdb", "system", "welcome1");
                }
                statement = connection.createStatement();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return statement;
    }
    
    public static String realEscapeString(String string) {
        return string.replace('\\', ' ')
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\'", "\\'")
                .replace("\"", "\\\"")
                .replace((char)26, ' ')
                .replace((char)0, ' ');
    }
    
    public static void close() {
        try {
            if (statement != null) {
                statement.close();
                statement = null;
            }
            if (connection != null) {
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
