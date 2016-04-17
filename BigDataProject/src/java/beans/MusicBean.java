package beans;

import db.hive.HiveConnection;
import db.hive.PredictionDM;
import db.hive.SongDescDM;
import db.nosql.AlbumDM;
import db.nosql.GenreDM;
import db.nosql.LikeDM;
import db.nosql.SongDM;
import db.nosql.Store;
import java.io.Serializable;
import entities.SongDesc;
import entities.Like;
import entities.Profile;
import entities.Song;
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
    private List<SongDesc> recommendsByArtist;
    private List<SongDesc> recommendsByGenre;
    private List<SongDesc> personalRecommends;
    private SongDesc currentSong;
    private String query;
    private int[] selectedGenres;
    private boolean checkLike;
    private boolean checkRecommend;
    private String playerStyle;
    private String recommendStyle;
    private String recommendsByArtistStyle;
    private String recommendsByGenreStyle;
    private String personalRecommendsStyle;
    private Profile user;

    public MusicBean() {
        songs = SongDescDM.getAll();
        recommendsByArtist = new ArrayList<>();
        recommendsByGenre = new ArrayList<>();
        personalRecommends = new ArrayList<>();
        playerStyle = "none";
        recommendStyle = "inline";
        recommendsByArtistStyle = "none";
        recommendsByGenreStyle = "none";
        personalRecommendsStyle = "none";
        user = SessionBean.getUser();
        recommend();
    }

    public void changeLike() {
        String email = user.getEmail();
        if (checkLike) {
            if (LikeDM.get(email, currentSong.getId()) == null) {
                LikeDM.insert(email, currentSong.getId(), 0);
            }
        } else {
            LikeDM.drop(email, currentSong.getId());
            setCheckRecommend(false);
        }
    }

    public void changeRecommend() {
        String email = user.getEmail();
        if (checkRecommend) {
            Like like = LikeDM.get(email, currentSong.getId());
            if (like == null) {
                LikeDM.insert(email, currentSong.getId(), 1);
                setCheckLike(true);
                return;
            }
            if (like.getRecommend() == 0) {
                like.setRecommend(1);
                LikeDM.update(like.getEmail(), like.getIdSong(), like.getRecommend());
            }
        } else if (LikeDM.get(email, currentSong.getId()) != null) {
            LikeDM.update(email, currentSong.getId(), 0);
        }
    }

    public void search() {
        if (query == null) {
            query = "";
        }
        String[] queryWords = query.trim().toLowerCase().split(" ");
        songs = new ArrayList<>();
        List<SongDesc> songDescs = SongDescDM.search(queryWords);
        if (selectedGenres.length > 0) {
            for (SongDesc sd : songDescs) {
                Song song = SongDM.get(sd.getId());
                for (int idGenre : selectedGenres) {
                    if (idGenre == song.getIdGenre()) {
                        songs.add(sd);
                        break;
                    }
                }
            }
        } else {
            songs = songDescs;
        }
    }

    public String getSongGenre(SongDesc songDesc) {
        return GenreDM.get(SongDM.get(songDesc.getId()).getIdGenre());
    }

    public String getSongAlbum(SongDesc songDesc) {
        if (songDesc == null) {
            return null;
        }
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
        playerStyle = currentSong != null ? "inline" : "none";
        recommend();
    }

    private void recommend() {
        List<String> localRecommends = new ArrayList<>();
        List<String> likedSongs = new ArrayList<>();
        HashMap<String, Integer> recommendsByArtistMap = new HashMap<>();
        HashMap<String, Integer> recommendsByGenreMap = new HashMap<>();
        int cnt = 0;
        Song songInfo = null;
        if (currentSong != null) {
            songInfo = SongDM.get(currentSong.getId());

            likedSongs = LikeDM.getSongsByEmail(user.getEmail());
            List<String> artistSongs = SongDM.getSongIdsByArtist(songInfo.getIdArtist());
            for (String idSong : artistSongs) {
                if (!likedSongs.contains(idSong) && !idSong.equals(songInfo.getIdSong())) {
                    recommendsByArtistMap.put(idSong, LikeDM.getLikesCount(idSong));
                }
            }
            recommendsByArtistMap = sortHashMapByValues(recommendsByArtistMap, 3);
            for (Entry<String, Integer> entry : recommendsByArtistMap.entrySet()) {
                localRecommends.add(entry.getKey());
            }

            List<String> genreSongs = SongDM.getSongIdsByGenre(songInfo.getIdGenre());
            for (String idSong : genreSongs) {
                if (!idSong.equals(songInfo.getIdSong()) && !localRecommends.contains(idSong) && !likedSongs.contains(idSong)) {
                    recommendsByGenreMap.put(idSong, LikeDM.getLikesCount(idSong));
                }
            }
            recommendsByGenreMap = sortHashMapByValues(recommendsByGenreMap, 4);
            for (Entry<String, Integer> entry : recommendsByGenreMap.entrySet()) {
                localRecommends.add(entry.getKey());
            }

            cnt += recommendsByArtistMap.size() + recommendsByGenreMap.size();
        }
        cnt = 10 - cnt;
        List<String> predictions = PredictionDM.get(user.getEmail());
        int predictionCnt = 0;
        for (String idSong : predictions) {
            if (!idSong.equals(songInfo == null ? null : songInfo.getIdSong()) && !localRecommends.contains(idSong) && !likedSongs.contains(idSong)) {
                localRecommends.add(idSong);
                if (++predictionCnt == cnt) {
                    break;
                }
            }
        }

        recommendsByArtist.clear();
        recommendsByGenre.clear();
        personalRecommends.clear();
        if (localRecommends.isEmpty()) {
            recommendStyle = "none";
            return;
        }
        recommendStyle = "inline";
        List<SongDesc> songDescList = SongDescDM.multiGet(localRecommends);
        for (SongDesc songDesc : songDescList) {
            if (recommendsByArtistMap.get(songDesc.getId()) != null) {
                recommendsByArtist.add(songDesc);
            } else if (recommendsByGenreMap.get(songDesc.getId()) != null) {
                recommendsByGenre.add(songDesc);
            } else {
                personalRecommends.add(songDesc);
            }
        }
        recommendsByArtistStyle = recommendsByArtist.isEmpty() ? "none" : "inline";
        recommendsByGenreStyle = recommendsByGenre.isEmpty() ? "none" : "inline";
        personalRecommendsStyle = personalRecommends.isEmpty() ? "none" : "inline";
    }

    private static HashMap sortHashMapByValues(HashMap map, int limit) {
        List list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o2)).getValue())
                        .compareTo(((Map.Entry) (o1)).getValue());
            }
        });
        HashMap sortedHashMap = new LinkedHashMap();
        if (limit <= 0) {
            for (Iterator it = list.iterator(); it.hasNext();) {
                Map.Entry entry = (Map.Entry) it.next();
                sortedHashMap.put(entry.getKey(), entry.getValue());
            }
        } else {
            int cnt = 0;
            for (Iterator it = list.iterator(); it.hasNext();) {
                Map.Entry entry = (Map.Entry) it.next();
                sortedHashMap.put(entry.getKey(), entry.getValue());
                if (++cnt == limit) {
                    break;
                }
            }
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
        if (currentSong == null) {
            return false;
        }
        return LikeDM.get(user.getEmail(), currentSong.getId()) != null;
    }

    public void setCheckLike(boolean like) {
        this.checkLike = like;
    }

    public boolean isCheckRecommend() {
        if (currentSong == null) {
            return false;
        }
        Like like = LikeDM.get(user.getEmail(), currentSong.getId());
        if (like == null) {
            return false;
        }
        return like.getRecommend() == 1;
    }

    public void clear() {
        songs = SongDescDM.getAll();
        recommendsByArtist = new ArrayList<>();
        recommendsByGenre = new ArrayList<>();
        personalRecommends = new ArrayList<>();
        playerStyle = "none";
        recommendStyle = "inline";
        recommendsByArtistStyle = "none";
        recommendsByGenreStyle = "none";
        personalRecommendsStyle = "none";
        query = null;
        selectedGenres = null;
        currentSong = null;
        recommend();
        Store.closeStore();
        HiveConnection.close();
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

    public List<SongDesc> getRecommendsByArtist() {
        return recommendsByArtist;
    }

    public void setRecommendsByArtist(List<SongDesc> recommendsByArtist) {
        this.recommendsByArtist = recommendsByArtist;
    }

    public List<SongDesc> getRecommendsByGenre() {
        return recommendsByGenre;
    }

    public void setRecommendsByGenre(List<SongDesc> recommendsByGenre) {
        this.recommendsByGenre = recommendsByGenre;
    }

    public List<SongDesc> getPersonalRecommends() {
        return personalRecommends;
    }

    public void setPersonalRecommends(List<SongDesc> personalRecommends) {
        this.personalRecommends = personalRecommends;
    }

    public String getRecommendStyle() {
        return recommendStyle;
    }

    public void setRecommendStyle(String recommendStyle) {
        this.recommendStyle = recommendStyle;
    }

    public String getRecommendsByArtistStyle() {
        return recommendsByArtistStyle;
    }

    public void setRecommendsByArtistStyle(String recommendsByArtistStyle) {
        this.recommendsByArtistStyle = recommendsByArtistStyle;
    }

    public String getRecommendsByGenreStyle() {
        return recommendsByGenreStyle;
    }

    public void setRecommendsByGenreStyle(String recommendsByGenreStyle) {
        this.recommendsByGenreStyle = recommendsByGenreStyle;
    }

    public String getPersonalRecommendsStyle() {
        return personalRecommendsStyle;
    }

    public void setPersonalRecommendsStyle(String personalRecommendsStyle) {
        this.personalRecommendsStyle = personalRecommendsStyle;
    }

    public Profile getUser() {
        return user;
    }

    public void setUser(Profile user) {
        this.user = user;
    }

}
