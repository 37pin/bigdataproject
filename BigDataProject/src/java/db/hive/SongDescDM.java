package db.hive;

import entities.SongDesc;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SongDescDM {

    public static SongDesc get(String idSong) {
        SongDesc songDesc = null;
        if (songDesc == null) {
            try {
                Statement statement = HiveConnection.getStatement();
                String sql = "SELECT namefile, nameartist, title, year FROM songsdesc_hdfs WHERE idsong = '" + idSong + "'";
                ResultSet result = statement.executeQuery(sql);
                while (result.next()) {
                    songDesc = new SongDesc(idSong, result.getString(1), result.getString(2), result.getString(3), result.getInt(4));
                }
                result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return songDesc;
    }

    public static List<SongDesc> multiGet(List<String> idSongs) {
        List<SongDesc> songDescs = new ArrayList<>();
        if (idSongs.isEmpty()) {
            return songDescs;
        }
        List<String> loadedSongs = new ArrayList<>();
        for (String idSong : idSongs) {
            SongDesc songDesc = null;
            if (songDesc == null) {
                loadedSongs.add(idSong);
            } else {
                songDescs.add(songDesc);
            }
        }
        if (!loadedSongs.isEmpty()) {
            try {
                Statement statement = HiveConnection.getStatement();
                StringBuilder sql = new StringBuilder("SELECT * FROM songsdesc_hdfs WHERE");
                for (int i = 0; i < loadedSongs.size(); i++) {
                    if (i != 0) {
                        sql.append(" OR");
                    }
                    sql.append(" idsong = '").append(loadedSongs.get(i)).append('\'');
                }
                ResultSet result = statement.executeQuery(sql.toString());
                while (result.next()) {
                    SongDesc songDesc = new SongDesc(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getInt(5));
                    songDescs.add(songDesc);
                }
                result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return songDescs;
    }

}
