package beans;

import db.hdfs.SongDescDM;
import db.nosql.GenreDM;
import db.nosql.SongDM;
import java.io.Serializable;
import entities.hdfs.SongDesc;
import entities.nosql.Song;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "musicBean")
@SessionScoped
public class MusicBean implements Serializable {

    private List<SongDesc> songs;
    private SongDesc currentSong;
    private String query;
    private int[] selectedGenres;

    public MusicBean() {
        songs = SongDescDM.getAll();
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

    public int[] getSelectedGenres() {
        return selectedGenres;
    }

    public void search() {
        if (query == null) return;
        String[] queryWords = query.trim().toLowerCase().split(" ");
        songs = new ArrayList<>();
        List<SongDesc> songDescs = SongDescDM.getAll();
        for (SongDesc sd : songDescs) {
            boolean isExist = true;
            for (String word : queryWords) {
                if (!sd.getTitle().trim().toLowerCase().contains(word) && !sd.getNameArtist().trim().toLowerCase().contains(word)) {
                    isExist = false;
                    break;
                }
            }
            if (isExist) {
                if (selectedGenres.length > 0) {
                    Song song = SongDM.getById(sd.getId());
                    for (int idGenre : selectedGenres) {
                        if (idGenre == song.getIdGenre()) {
                            songs.add(sd);
                            break;
                        }
                    }
                } else {
                    songs.add(sd);
                }
            }
        }
    }

    public void setSelectedGenres(int[] selectedGenres) {
        this.selectedGenres = selectedGenres;
    }

    public Map<String, Object> getGenres() {
        return GenreDM.getAll();
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
