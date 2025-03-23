/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Acer Predator
 */
public class BillingSeat {

    private int billingSeatID;
    private String billingID;
    private int seatID;
    private String seatName;

    public BillingSeat() {
    }

    public BillingSeat(int billingSeatID, String billingID, int seatID, String seatName) {
        this.billingSeatID = billingSeatID;
        this.billingID = billingID;
        this.seatID = seatID;
        this.seatName = seatName;
    }

    public int getBillingSeatID() {
        return billingSeatID;
    }

    public void setBillingSeatID(int billingSeatID) {
        this.billingSeatID = billingSeatID;
    }

    public String getBillingID() {
        return billingID;
    }

    public void setBillingID(String billingID) {
        this.billingID = billingID;
    }

    public int getSeatID() {
        return seatID;
    }

    public void setSeatID(int seatID) {
        this.seatID = seatID;
    }

    public String getSeatName() {
        return seatName;
    }

    public void setSeatName(String seatName) {
        this.seatName = seatName;
    }

}
