package beans;

import db.hive.HiveConnection;
import db.hive.ProfileDM;
import entities.Profile;
import java.io.Serializable;
import java.security.MessageDigest;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private String email;
    private String password;
    private String message;

    public String validate() {
        String trueEmail = null;
        if (email != null) {
            trueEmail = email.trim().toLowerCase();
        }
        if (trueEmail != null && trueEmail.length() > 0 && password != null && password.length() > 0) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-512");
                md.update(password.getBytes("UTF-8"));
                byte[] digest = md.digest();
                // password = DatatypeConverter.printHexBinary(digest);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Profile user = ProfileDM.validate(trueEmail, password);
            if (user != null) {
                HttpSession session = SessionBean.getSession();
                session.setAttribute("user", user);
                return "/main";
            }
        }
        message = "Incorrect email or password";
        return "/index";
    }

    public String logout() {
        HttpSession session = SessionBean.getSession();
        session.invalidate();
        return "/index";
    }

    public void clear() {
        email = null;
        password = null;
        message = null;
        HiveConnection.close();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
