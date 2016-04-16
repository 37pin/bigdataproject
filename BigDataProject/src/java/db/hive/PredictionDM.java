package db.hive;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PredictionDM {

    public static List<String> getPredictions(String email) {
        List<String> songs = new ArrayList<>();
        try {
            Statement statement = HiveConnection.getStatement();
            String sql = "select idsong from predictions where email = '" + email + "'";
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                songs.add(result.getString(1));
            }
            result.close();
            HiveConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return songs;
    }

}
