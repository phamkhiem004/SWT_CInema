/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author DUNGVT
 */
public class Combo {

    int combo_id;
    double price;
    String combo_name;
    String description;
    String status;
    String poster_url;
    int quantity;

    public Combo() {
    }

    public Combo(int combo_id, double price, String combo_name, String description, String status, int quantity, String poster_url) {
        this.combo_id = combo_id;
        this.price = price;
        this.combo_name = combo_name;
        this.description = description;
        this.status = status;
        this.quantity = quantity;
        this.poster_url = poster_url;
    }

    public Combo(int combo_id, double price, String combo_name, String description, String poster_url, int quantity) {
        this.combo_id = combo_id;
        this.price = price;
        this.combo_name = combo_name;
        this.description = description;
        this.poster_url = poster_url;
        this.quantity = quantity;
    }
    

    public int getCombo_id() {
        return combo_id;
    }

    public void setCombo_id(int combo_id) {
        this.combo_id = combo_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCombo_name() {
        return combo_name;
    }

    public void setCombo_name(String combo_name) {
        this.combo_name = combo_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPoster_url() {
        return poster_url;
    }

    public void setPoster_url(String poster_url) {
        this.poster_url = poster_url;
    }

    @Override
    public String toString() {
        return "Combo{" + "combo_id=" + combo_id + ", price=" + price + ", combo_name=" + combo_name + ", description=" + description + ", status=" + status + ", poster_url=" + poster_url + ", quantity=" + quantity + '}';
    }



}
