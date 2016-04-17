package db.hive;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PredictionDM {

    public static List<String> get(String email) {
        List<String> songs = new ArrayList<>();
        try {
            Statement statement = HiveConnection.getStatement();
            String sql = "SELECT idsong FROM predictions WHERE email = '" + email + "'";
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                songs.add(result.getString(1));
            }
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return songs;
    }

}
