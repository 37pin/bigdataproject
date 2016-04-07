package entities.nosql;

public class Album {
    
    private int idAlbum;
    private String title;
    
    public Album(int idAlbum, String title) {
        this.idAlbum = idAlbum;
        this.title = title;
    }

    public int getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(int idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    @Override
    public String toString() {
        return title;
    }
    
}
