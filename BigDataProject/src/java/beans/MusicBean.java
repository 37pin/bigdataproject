package beans;

import java.io.Serializable;
import entities.SongDesc;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
 
@ManagedBean(name = "musicBean")
@SessionScoped
public class MusicBean implements Serializable {

    private static final String IP = "10.154.101.249";
    private String songFile;
    private DataModel songs = null;
    private List<SongDesc> db = new ArrayList<>(Arrays.asList(new SongDesc("111", "Aint_No_Love_-_01_-_Gone_Already.mp3", "gone", 2016)));

    public MusicBean() {
    }
    
    public String getSongFile() {
        return songFile;
    }

    public void setSongFile(String songFile) {
        this.songFile = songFile;
    }

    public DataModel getSongs() {
        if (songs == null) {
            songs = new ListDataModel(db);
        }
        return songs;
    }

    public void setSongs(DataModel songs) {
        this.songs = songs;
    }
    
    public String getIP() {
        return IP;
    }
    
    public void changeMusic(String songFile) {
        this.songFile = songFile;
    }
    
}
