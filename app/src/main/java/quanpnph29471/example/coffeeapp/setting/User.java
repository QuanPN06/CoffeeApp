package quanpnph29471.example.coffeeapp.setting;

public class User {

    String user_name,user_pass,user_role,user_img;


    public User() {
    }

    public User(String user_name) {
        this.user_name = user_name;
    }

    public User(String user_name, String user_pass, String user_role, String user_img) {
        this.user_name = user_name;
        this.user_pass = user_pass;
        this.user_role = user_role;
        this.user_img = user_img;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_pass() {
        return user_pass;
    }

    public void setUser_pass(String user_pass) {
        this.user_pass = user_pass;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }

    public String getUser_img() {
        return user_img;
    }

    public void setUser_img(String user_img) {
        this.user_img = user_img;
    }
}
