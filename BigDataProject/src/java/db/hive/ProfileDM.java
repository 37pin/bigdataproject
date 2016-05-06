package db.hive;

import beans.util.Configuration;
import entities.Profile;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class ProfileDM {

    public static Profile get(String email) {
        try {
            Statement statement = HiveConnection.getStatement();
            String sql = "SELECT name, surname, birthday, gender FROM profiles WHERE email = '" + email + "'";
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                return new Profile(email, result.getString(1), result.getString(2), result.getString(3), (short) result.getInt(4));
            }
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Profile validate(String email, String password) {
        try {
            Statement statement = HiveConnection.getStatement();
            email = Configuration.realEscapeString(email);
            password = Configuration.realEscapeString(password);
            String sql = "SELECT name, surname, birthday, gender FROM profiles WHERE LCASE(TRIM(email)) = '" + email + "' AND pass = '" + password + '\'';
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                return new Profile(email, result.getString(1), result.getString(2), result.getString(3), (short) result.getInt(4));
            }
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
