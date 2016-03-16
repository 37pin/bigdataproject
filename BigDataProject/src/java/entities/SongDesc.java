package entities;

public class SongDesc {
    private String id;
    private String songFile;
    private String title;
    private int year;
    
    public SongDesc(String id, String songFile, String title, int year) {
        this.id = id;
        this.songFile = songFile;
        this.title = title;
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return songFile;
    }

    public void setFileName(String songFile) {
        this.songFile = songFile;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
    @Override
    public String toString() {
        return title;
    }
}
