package db.hive;

import entities.hive.SongDesc;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SongDescDM {

    public static final String IP = "192.168.206.17";
    private static List<SongDesc> allSongDescs;
    
    public static List<SongDesc> getAll() {
        if (allSongDescs == null) {
            allSongDescs = new ArrayList<>();
            try {
                Statement statement = HiveConnection.getStatement();
                String tableName = "songsdesc_hdfs";
                String sql = "select * from " + tableName;
                ResultSet result = statement.executeQuery(sql);
                while (result.next()) {
                    String idSong = result.getString(1);
                    String songFile = result.getString(2);
                    String nameArtist = result.getString(3);
                    String title = result.getString(4);
                    int year = result.getInt(5);
                    SongDesc songDesc = new SongDesc(idSong, songFile, nameArtist, title, year);
                    allSongDescs.add(songDesc);
                }
                result.close();
                HiveConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return allSongDescs;
    }
    
    public static SongDesc get(String idSong) {
        SongDesc songDesc = null;
        try {
            Statement statement = HiveConnection.getStatement();
            String tableName = "songsdesc_hdfs";
            String sql = "select namefile, nameartist, title, year from " + tableName + " where idsong = '" + idSong + "'";
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                songDesc = new SongDesc(idSong, result.getString(1), result.getString(2), result.getString(3), result.getInt(4));
            }
            result.close();
            HiveConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return songDesc;
    }

}
