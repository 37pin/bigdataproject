package entities;

public class Profile {

    private String email;
    private String name;
    private String surname;
    private String birtday;
    private short gender;

    public Profile(String email, String name, String surname, String birtday, short gender) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.birtday = birtday;
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirtday() {
        return birtday;
    }

    public void setBirtday(String birtday) {
        this.birtday = birtday;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(short gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Profile{" + "email=" + email + ", name=" + name + ", surname=" + surname + ", birtday=" + birtday + ", gender=" + gender + '}';
    }

}
