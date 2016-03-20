package entities.nosql;

public class Artist {
    
    private int idArtist;
    private String name;
    
    public Artist(int idArtist, String name) {
        this.idArtist = idArtist;
        this.name = name;
    }

    public int getIdArtist() {
        return idArtist;
    }

    public void setIdArtist(int idArtist) {
        this.idArtist = idArtist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return name;
    }
    
}
