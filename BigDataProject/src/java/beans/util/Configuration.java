package beans.util;

public class Configuration {
    
    public static final int RECOMMENDS_BY_ARTIST_CNT = 3;
    public static final int RECOMMENDS_BY_GENRE_CNT = 4;
    public static final int MAX_RECOMMENDS = 10;
    public static final int LOAD_MORE_SONGS_CNT = 10;
    public static final String IP = "192.168.206.18";
                                 // "10.154.123.231";
    
    public static String realEscapeString(String string) {
        return string.replace('\\', ' ')
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\'", "\\'")
                .replace("\"", "\\\"")
                .replace((char) 26, ' ')
                .replace((char) 0, ' ');
    }
    
}
