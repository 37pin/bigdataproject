package dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class SongDescDao {

    public static final String IP = "10.154.101.249";
    
    public String loadData() {
        String ans = "test: ";
        try {
            Path pt = new Path("/music/songsdesc.csv");
            Configuration conf = new Configuration();
            FileSystem fs = FileSystem.get(new URI("webhdfs://" + IP + ":8020"), conf);
            BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(pt)));
            String line;
            line = br.readLine();
            while (line != null) {
                ans += line;
                line = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ans;
    }

}
