package entities;

public class Song {

    private String idSong;
    private int idAlbum;
    private int idGenre;
    private int idArtist;

    public Song(String idSong, int idAlbum, int idGenre, int idArtist) {
        this.idSong = idSong;
        this.idAlbum = idAlbum;
        this.idGenre = idGenre;
        this.idArtist = idArtist;
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

    public int getIdArtist() {
        return idArtist;
    }

    public void setIdArtist(int idArtist) {
        this.idArtist = idArtist;
    }

    @Override
    public String toString() {
        return idSong + " " + idAlbum + " " + idGenre + " " + idArtist;
    }
}
