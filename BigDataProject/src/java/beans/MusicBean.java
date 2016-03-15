package beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
 
@ManagedBean(name = "musicBean")
@SessionScoped
public class MusicBean implements Serializable {

    private static final String IP = "10.154.101.249";
    
    public String getIP() {
        return IP;
    }
    
    public String musicName() {
        return "Aint_No_Love_-_01_-_Gone_Already.mp3";
    }
    
}
