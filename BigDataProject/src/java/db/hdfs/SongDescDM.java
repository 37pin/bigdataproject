package db.hdfs;

import entities.hdfs.SongDesc;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class SongDescDM {
    
    public static final String IP = "192.168.206.17";
    private static HashMap<String, SongDesc> allSongDescsMap;
    private static List<SongDesc> allSongDescs;
    
    public static List<SongDesc> getAll() {
        if (allSongDescs == null) {
            allSongDescs = new ArrayList<>();
            allSongDescsMap = new HashMap<>();
            try {
                Path pt = new Path("/music/songsdesc/songsdesc.csv");
                Configuration conf = new Configuration();
                FileSystem fs = FileSystem.get(new URI("webhdfs://bigdatalite.localdomain:50070"), conf);
                BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(pt)));
                String line;
                line = br.readLine();
                while (line != null) {
                    String[] row = line.split("\\|");
                    String idSong = row[0];
                    SongDesc songDesc = new SongDesc(idSong, row[1], row[2], row[3], Integer.valueOf(row[4]));
                    allSongDescsMap.put(idSong, songDesc);
                    allSongDescs.add(songDesc);
                    line = br.readLine();
                }
                br.close();
                fs.close();
                } catch (Exception e) {
                    e.printStackTrace();
            }
        }
        return allSongDescs;
    }
    
    public static SongDesc get(String idSong) {
        getAll();
        return allSongDescsMap.get(idSong);
    }

}