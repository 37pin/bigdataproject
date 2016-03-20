package entities.nosql;

public class Genre {

    private int idGenre;
    private String title;

    public Genre(int idGenre, String title) {
        this.idGenre = idGenre;
        this.title = title;
    }

    public int getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
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
