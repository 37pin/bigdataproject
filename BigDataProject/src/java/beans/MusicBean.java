package beans;

import dao.SongDescDao;
import java.io.Serializable;
import entities.SongDesc;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
 
@ManagedBean(name = "musicBean")
@SessionScoped
public class MusicBean implements Serializable {

    private String songFile = "null";
    private List<SongDesc> songs = null;
    private SongDescDao songDescDao;
    private String test;

    public MusicBean() {
        songDescDao = new SongDescDao();
        test = songDescDao.loadData();
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

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
    
    public String getIP() {
        return SongDescDao.IP;
    }
    
    public void changeMusic(String songFile) {
        this.songFile = songFile;
    }
    
}
