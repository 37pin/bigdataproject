package entities;

public class Like {

    private String email;
    private String idSong;
    private int recommend;

    public Like(String email, String idSong, int recommend) {
        this.email = email;
        this.idSong = idSong;
        this.recommend = recommend;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdSong() {
        return idSong;
    }

    public void setIdSong(String idSong) {
        this.idSong = idSong;
    }

    public int getRecommend() {
        return recommend;
    }

    public void setRecommend(int recommend) {
        this.recommend = recommend;
    }

    @Override
    public String toString() {
        return getEmail() + (getRecommend() == 1 ? " recommend " : " not recommend ") + getIdSong();
    }

}
