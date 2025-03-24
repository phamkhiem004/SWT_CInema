/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author SHD
 */
public class BillingCombo {
    private int billingComboID;
    private String billingID;
    private int comboID;
    private int quantity;

    public BillingCombo() {
    }

    public BillingCombo(int billingComboID, String billingID, int comboID, int quantity) {
        this.billingComboID = billingComboID;
        this.billingID = billingID;
        this.comboID = comboID;
        this.quantity = quantity;
    }

    public int getBillingComboID() {
        return billingComboID;
    }

    public void setBillingComboID(int billingComboID) {
        this.billingComboID = billingComboID;
    }

    public String getBillingID() {
        return billingID;
    }

    public void setBillingID(String billingID) {
        this.billingID = billingID;
    }

    public int getComboID() {
        return comboID;
    }

    public void setComboID(int comboID) {
        this.comboID = comboID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
