package dao;

import entities.SongDesc;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class SongDescDao {
    
    public List<SongDesc> getAll() {
        List<SongDesc> allSongDescs = new ArrayList<>();
        try {
            Path pt = new Path("/music/songsdesc.csv");
            Configuration conf = new Configuration();
            FileSystem fs = FileSystem.get(new URI("webhdfs://bigdatalite.localdomain:50070"), conf);
            BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(pt)));
            String line;
            line = br.readLine();
            while (line != null) {
                String[] row = line.split("\\|");
                allSongDescs.add(new SongDesc(row[0], row[1], row[2], Integer.valueOf(row[3])));
                line = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allSongDescs;
    }

}
