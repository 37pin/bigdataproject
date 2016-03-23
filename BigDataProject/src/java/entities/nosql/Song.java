package entities.nosql;

public class Song {
    
    private String idSong;
    private int idAlbum;
    private int idGenre;
    
    public Song(String idSong, int idAlbum, int idGenre) {
        this.idSong = idSong;
        this.idAlbum = idAlbum;
        this.idGenre = idGenre;
    }

    public String getIdSong() {
        return idSong;
    }

    public void setIdSong(String idSong) {
        this.idSong = idSong;
    }

    public int getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(int idAlbum) {
        this.idAlbum = idAlbum;
    }

    public int getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
    }
    
}
