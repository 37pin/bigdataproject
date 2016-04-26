package db.oracle;

import db.hive.HiveConnection;
import entities.SongDesc;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OracleSongDescDM {
    
    public static List<SongDesc> getMostLiked(String[] queryWords, int[] selectedGenres, int offset) {
        List<SongDesc> mostLikedSongDescs = new ArrayList<>();
        try {
            String sql = "SELECT s.*, DECODE(c.cnt, null, 0, c.cnt) cnt FROM (SELECT idsong, COUNT(idsong) cnt FROM likes_hive GROUP BY idsong) c, songsdesc_hive s, songs_hive d WHERE d.idsong = s.idsong AND c.idsong(+)=s.idsong ";
            int genresCnt = selectedGenres.length;
            if (genresCnt > 0) {
                sql += "AND (";
                for (int i = 0; i < genresCnt; i++) {
                    if (i != 0) {
                        sql += " OR ";
                    }
                    sql += "idgenre = " + selectedGenres[i];
                }
                sql += ") ";
            }
            for (int i = 0; i < queryWords.length; i++) {
                String normalizedWord = HiveConnection.realEscapeString(queryWords[i]);
                sql += "AND (LOWER(title) LIKE '%" + normalizedWord + "%' OR LOWER(nameartist) LIKE '%" + normalizedWord + "%') ";
            }
            sql += "ORDER BY cnt DESC OFFSET " + offset + " ROWS FETCH NEXT 10 ROWS ONLY";
            PreparedStatement statement = OracleConnection.getConnection().prepareStatement(sql);
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                mostLikedSongDescs.add(new SongDesc(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getInt(5)));
            }
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mostLikedSongDescs;
    }
    
    public static List<SongDesc> getMostRecommended(String[] queryWords, int[] selectedGenres, int offset) {
        List<SongDesc> mostRecommendSongDescs = new ArrayList<>();
        try {
            String sql = "SELECT s.*, DECODE(c.cnt, null, 0, c.cnt) cnt FROM (SELECT idsong, SUM(recommend) cnt FROM likes_hive GROUP BY idsong) c, songsdesc_hive s, songs_hive d WHERE d.idsong = s.idsong AND c.idsong(+)=s.idsong ";
            int genresCnt = selectedGenres.length;
            if (genresCnt > 0) {
                sql += "AND (";
                for (int i = 0; i < genresCnt; i++) {
                    if (i != 0) {
                        sql += " OR ";
                    }
                    sql += "idgenre = " + selectedGenres[i];
                }
                sql += ") ";
            }
            for (int i = 0; i < queryWords.length; i++) {
                String normalizedWord = HiveConnection.realEscapeString(queryWords[i]);
                sql += "AND (LOWER(title) LIKE '%" + normalizedWord + "%' OR LOWER(nameartist) LIKE '%" + normalizedWord + "%') ";
            }
            sql += "ORDER BY cnt DESC OFFSET " + offset + " ROWS FETCH NEXT 10 ROWS ONLY";
            PreparedStatement statement = OracleConnection.getConnection().prepareStatement(sql);
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                mostRecommendSongDescs.add(new SongDesc(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getInt(5)));
            }
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mostRecommendSongDescs;
    }

}
