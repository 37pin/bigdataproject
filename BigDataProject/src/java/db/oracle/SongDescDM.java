package db.oracle;

import entities.hdfs.SongDesc;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SongDescDM {

    public static final String IP = "192.168.206.17";
    
    public static List<SongDesc> getAll() {
        List<SongDesc> allSongDescs = new ArrayList<>();
        try {
            Statement statement = OracleConnection.getStatement();
            String sql = "SELECT * FROM songsdesc_hive";
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                allSongDescs.add(new SongDesc(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getInt(5)));
            }
            result.close();
            OracleConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allSongDescs;
    }
    
    public static List<SongDesc> search(String[] queryWords) {
        List<SongDesc> result = new ArrayList<>();
        try {
            Statement statement = OracleConnection.getStatement();
            String sql = "SELECT * FROM songsdesc_hdfs";
            for (int i = 0; i < queryWords.length; i++) {
                if (i == 0) {
                    sql += " WHERE";
                } else {
                    sql += " AND";
                }
                String normalizedWord = OracleConnection.realEscapeString(queryWords[i]);
                sql += " (LCASE(title) like '%" + normalizedWord + "%' OR LCASE(nameartist) like '%" + normalizedWord + "%')";
            }
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                result.add(new SongDesc(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5)));
            }
            resultSet.close();
            OracleConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public static SongDesc get(String idSong) {
        try {
            Statement statement = OracleConnection.getStatement();
            String sql = "SELECT namefile, nameartist, title, year FROM songsdesc_hive WHERE idsong = '" + idSong + "'";
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                return new SongDesc(idSong, result.getString(1), result.getString(2), result.getString(3), result.getInt(4));
            }
            result.close();
            OracleConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
