package beans;

import db.hdfs.SongDescDM;
import db.nosql.AlbumDM;
import db.nosql.GenreDM;
import db.nosql.LikeDM;
import db.nosql.SongDM;
import java.io.Serializable;
import entities.hdfs.SongDesc;
import entities.nosql.Like;
import entities.nosql.Song;
import java.util.ArrayList;
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
    private boolean checkLike;
    private boolean checkRecommend;
    private String playerStyle;
    public String user;

    public MusicBean() {
        songs = SongDescDM.getAll();
        playerStyle = "none";
        user = "test@test.com";
    }
    
    public void changeLike() {
        if (checkLike) {
            if (LikeDM.get(user, currentSong.getId()) == null) {
                LikeDM.insert(new Like(user, currentSong.getId(), 0));
            }
        } else {
            LikeDM.drop(user, currentSong.getId());
            setCheckRecommend(false);
        }
    }
    
    public void changeRecommend() {
        if (checkRecommend) {
            Like like = LikeDM.get(user, currentSong.getId());
            if (like == null) {
                LikeDM.insert(new Like(user, currentSong.getId(), 1));
                setCheckLike(true);
                return;
            }
            if (like.getRecommend() == 0) {
                like.setRecommend(1);
                LikeDM.update(like);
            }
        } else {
            if (LikeDM.get(user, currentSong.getId()) != null) {
                LikeDM.update(new Like(user, currentSong.getId(), 0));
            }
        }
    }
    
    public void search() {
        if (query == null) query = "";
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
                    Song song = SongDM.get(sd.getId());
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
    
    public String getSongGenre(SongDesc songDesc) {
        return GenreDM.get(SongDM.get(songDesc.getId()).getIdGenre()).getTitle();
    }
    
    public String getSongAlbum(SongDesc songDesc) {
        if (songDesc == null) return null;
        return AlbumDM.get(SongDM.get(songDesc.getId()).getIdAlbum()).getTitle();
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

    public void setCurrentSong(SongDesc currentSong) {
        this.currentSong = currentSong;
    }

    public int[] getSelectedGenres() {
        return selectedGenres;
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
        setPlayerStyle(currentSong != null ? "inline" : "none");
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public boolean isCheckLike() {
        if (currentSong == null) return false;
        return LikeDM.get(user, currentSong.getId()) != null;
    }

    public void setCheckLike(boolean like) {
        this.checkLike = like;
    }

    public boolean isCheckRecommend() {
        if (currentSong == null) return false;
        Like like = LikeDM.get(user, currentSong.getId());
        if (like == null) return false;
        return like.getRecommend() == 1;
    }

    public void setCheckRecommend(boolean recommend) {
        this.checkRecommend = recommend;
    }

    public String getPlayerStyle() {
        return playerStyle;
    }

    public void setPlayerStyle(String playerStyle) {
        this.playerStyle = playerStyle;
    }

}
