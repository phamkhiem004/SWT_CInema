/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;

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
    private int comboID;
    private String comboName;
    private String poster;
    private String descriptionD;
    private String statusD;
    private BigDecimal cost;
    public Combo() {
    }
        public Combo(int comboID, String comboName, BigDecimal cost) {
        this.comboID = comboID;
        this.comboName = comboName;
        this.cost = cost;
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

    public int getComboID() {
        return comboID;
    }

    public void setComboID(int comboID) {
        this.comboID = comboID;
    }

    public String getComboName() {
        return comboName;
    }

    public void setComboName(String comboName) {
        this.comboName = comboName;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getDescriptionD() {
        return descriptionD;
    }

    public void setDescriptionD(String descriptionD) {
        this.descriptionD = descriptionD;
    }

    public String getStatusD() {
        return statusD;
    }

    public void setStatusD(String statusD) {
        this.statusD = statusD;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Combo{" + "combo_id=" + combo_id + ", price=" + price + ", combo_name=" + combo_name + ", description=" + description + ", status=" + status + ", poster_url=" + poster_url + ", quantity=" + quantity + '}';
    }



}
