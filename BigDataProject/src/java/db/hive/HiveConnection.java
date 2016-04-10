package db.hive;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class HiveConnection {

    private static Connection connection;
    private static Statement statement;

    public static Statement getStatement(){
        try {
            InitialContext initialContext = new InitialContext();
            DataSource ds = (DataSource) initialContext.lookup("jdbc/hiveDatasource");
            //initialContext.close();
            connection = ds.getConnection();
            statement = connection.createStatement();
            return statement;
        } catch (NamingException | SQLException e) {
             e.printStackTrace();
        }
        return null;
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
