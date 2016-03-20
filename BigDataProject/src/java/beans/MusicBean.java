package beans;

import db.hdfs.SongDescDM;
import db.nosql.GenreDM;
import java.io.Serializable;
import entities.hdfs.SongDesc;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
 
@ManagedBean(name = "musicBean")
@SessionScoped
public class MusicBean implements Serializable {

    private List<SongDesc> songs = null;
    private final SongDescDM songDescDM;
    private SongDesc currentSong;
    private String query;

    public MusicBean() {
        songDescDM = new SongDescDM();
        songs = songDescDM.getAll();
        new GenreDM().getAll();
    }
    
    public void search() {
        songs = songDescDM.search(getQuery());
        changeMusic(currentSong);
    }

    public List<SongDesc> getSongs() {
        return songs;
    }

    public void setSongs(List<SongDesc> songs) {
        this.songs = songs;
    }

    public SongDesc getCurrentSong() {
        return currentSong;
    }

    public void setSongText(SongDesc currentSong) {
        this.currentSong = currentSong;
    }
    
    public String getIP() {
        return SongDescDM.IP;
    }
    
    public void changeMusic(SongDesc songDesc) {
        currentSong = songDesc;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
    
}
