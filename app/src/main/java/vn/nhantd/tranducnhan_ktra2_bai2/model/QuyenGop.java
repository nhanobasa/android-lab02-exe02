package vn.nhantd.tranducnhan_ktra2_bai2.model;

import java.io.Serializable;

/**
 * Created by Tran Duc Nhan on 2021-05-14
 */
public class QuyenGop implements Serializable {

    private int  id;
    private String name;
    private String city;
    private String date;
    private Double money;

    public QuyenGop(int id, String name, String city, String date, Double money) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.date = date;
        this.money = money;
    }

    public QuyenGop(String name, String city, String date, Double money) {
        this.name = name;
        this.city = city;
        this.date = date;
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "QuyenGop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", date='" + date + '\'' +
                ", money=" + money +
                '}';
    }
}
