package entities.hive;

public class Prediction {
   private String email;
   private String idSong;
   private double probability;
   
   public Prediction(String email, String idSong, double probability) {
       this.email = email;
       this.idSong = idSong;
       this.probability = probability;
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

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }
   
   
}
