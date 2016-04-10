package beans;

import db.hive.PredictionDM;
import db.hive.SongDescDM;
import db.nosql.AlbumDM;
import db.nosql.GenreDM;
import db.nosql.LikeDM;
import db.nosql.SongDM;
import java.io.Serializable;
import entities.hive.SongDesc;
import entities.nosql.Like;
import entities.nosql.Song;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "musicBean")
@SessionScoped
public class MusicBean implements Serializable {

    private List<SongDesc> songs;
    private List<SongDesc> recommends;
    private SongDesc currentSong;
    private String query;
    private int[] selectedGenres;
    private boolean checkLike;
    private boolean checkRecommend;
    private String playerStyle;
    public String user;

    public MusicBean() {
        songs = SongDescDM.getAll();
        recommends = new ArrayList<>();
        playerStyle = "none";
        user = "test@test.com";
    }
    
    public void changeLike() {
        if (checkLike) {
            if (LikeDM.get(user, currentSong.getId()) == null) {
                LikeDM.insert(user, currentSong.getId(), 0);
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
                LikeDM.insert(user, currentSong.getId(), 1);
                setCheckLike(true);
                return;
            }
            if (like.getRecommend() == 0) {
                like.setRecommend(1);
                LikeDM.update(like.getEmail(), like.getIdSong(), like.getRecommend());
            }
        } else {
            if (LikeDM.get(user, currentSong.getId()) != null) {
                LikeDM.update(user, currentSong.getId(), 0);
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
        return GenreDM.get(SongDM.get(songDesc.getId()).getIdGenre());
    }
    
    public String getSongAlbum(SongDesc songDesc) {
        if (songDesc == null) return null;
        return AlbumDM.get(SongDM.get(songDesc.getId()).getIdAlbum());
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
        recommend();
    }
    
    private void recommend() {
        recommends.clear();
        Song songInfo = SongDM.get(currentSong.getId());
        
        List<String> artistSongs = SongDM.getSongIdsByArtist(songInfo.getIdArtist());
        List<String> likedSongs = LikeDM.getSongsByEmail(user);
        HashMap<String, Integer> recommendsBySomething = new HashMap<>();
        for (String idSong : artistSongs) {
            if (!likedSongs.contains(idSong) && !idSong.equals(songInfo.getIdSong())) {
                recommendsBySomething.put(idSong, LikeDM.getByIdSong(idSong).size());
            }
        }
        recommendsBySomething = sortHashMapByValues(recommendsBySomething);
        int cnt = 0;
        int artistCnt = 0;
        for (Entry<String, Integer> entry : recommendsBySomething.entrySet()) {
            recommends.add(SongDescDM.get(entry.getKey()));
            if (++artistCnt == 3) {
                break;
            }
        }
        
        List<String> genreSongs = SongDM.getSongIdsByGenre(songInfo.getIdGenre());
        recommendsBySomething.clear();
        for (String idSong : genreSongs) {
            SongDesc sd = new SongDesc(idSong, null, null, null, 0);
            if (!idSong.equals(songInfo.getIdSong()) && !recommends.contains(sd) && !likedSongs.contains(idSong)) {
                recommendsBySomething.put(idSong, LikeDM.getByIdSong(idSong).size());
            }
        }
        recommendsBySomething = sortHashMapByValues(recommendsBySomething);
        int genreCnt = 0;
        for (Entry<String, Integer> entry : recommendsBySomething.entrySet()) {
            recommends.add(SongDescDM.get(entry.getKey()));
            if (++genreCnt == 4) {
                break;
            }
        }
        
        List<String> predictions = PredictionDM.getPredictions(user);
        int predictionCnt = 0;
        for(String idSong : predictions) {
            SongDesc sd = SongDescDM.get(idSong);
            if (!idSong.equals(songInfo.getIdSong()) && !recommends.contains(sd) && !likedSongs.contains(idSong)) {
                recommends.add(sd);
            }
            if (++predictionCnt == 4) {
                break;
            }
        }
        cnt += artistCnt + genreCnt + predictionCnt;
        cnt = 10 - cnt;
    }
    
    private static HashMap sortHashMapByValues(HashMap map) { 
       List list = new LinkedList(map.entrySet());
       Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
               return ((Comparable) ((Map.Entry) (o2)).getValue())
                  .compareTo(((Map.Entry) (o1)).getValue());
            }
       });
       HashMap sortedHashMap = new LinkedHashMap();
       for (Iterator it = list.iterator(); it.hasNext();) {
              Map.Entry entry = (Map.Entry) it.next();
              sortedHashMap.put(entry.getKey(), entry.getValue());
       } 
       return sortedHashMap;
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

    public List<SongDesc> getRecommends() {
        return recommends;
    }

    public void setRecommends(List<SongDesc> recommends) {
        this.recommends = recommends;
    }
    
    public void clear() {
        songs = SongDescDM.getAll();
        recommends = new ArrayList<>();
        playerStyle = "none";
        query = null;
        selectedGenres = null;
        currentSong = null;
    }

}
