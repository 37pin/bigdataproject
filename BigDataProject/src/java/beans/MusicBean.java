package beans;

import db.hdfs.SongDescDM;
import java.io.Serializable;
import entities.hdfs.SongDesc;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
 
@ManagedBean(name = "musicBean")
@SessionScoped
public class MusicBean implements Serializable {

    private String songFile;
    private List<SongDesc> songs = null;
    private SongDescDM songDescDM;
    private String songText;

    public MusicBean() {
        songDescDM = new SongDescDM();
        songs = songDescDM.getAll();
    }
    
    public String getSongFile() {
        return songFile;
    }

    public void setSongFile(String songFile) {
        this.songFile = songFile;
    }

    public List<SongDesc> getSongs() {
        return songs;
    }

    public void setSongs(List<SongDesc> songs) {
        this.songs = songs;
    }

    public String getSongText() {
        return songText;
    }

    public void setSongText(String songText) {
        this.songText = songText;
    }
    
    public String getIP() {
        return SongDescDM.IP;
    }
    
    public void changeMusic(SongDesc songDesc) {
        songFile = songDesc.getFileName();
        songText = songDesc.getTitle() + ", " + songDesc.getYear();
    }
    
}
