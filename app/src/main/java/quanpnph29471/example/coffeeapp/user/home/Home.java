package quanpnph29471.example.coffeeapp.user.home;

import java.io.Serializable;

public class Home implements Serializable {
    private int id;
    private String img;
    private String name;
    private String des;
    private int price;

    public Home() {
    }

    public Home(int id, String img, String name, String des, int price) {
        this.id = id;
        this.img = img;
        this.name = name;
        this.des = des;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
