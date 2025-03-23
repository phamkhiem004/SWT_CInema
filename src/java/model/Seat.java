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
public class Seat {
    String seat_name;
    String seat_type;
    double seat_price;
    
    private int seatID;
    private String seatType;
    private BigDecimal price;

    public Seat() {
    }

    public Seat(String seat_name, String seat_type, double seat_price) {
        this.seat_name = seat_name;
        this.seat_type = seat_type;
        this.seat_price = seat_price;
    }

    public Seat(int seatID, String seatType, BigDecimal price) {
        this.seatID = seatID;
        this.seatType = seatType;
        this.price = price;
    }
    

    public int getSeatID() {
        return seatID;
    }

    public void setSeatID(int seatID) {
        this.seatID = seatID;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    

    public String getSeat_name() {
        return seat_name;
    }

    public void setSeat_name(String seat_name) {
        this.seat_name = seat_name;
    }


    public String getSeat_type() {
        return seat_type;
    }

    public void setSeat_type(String seat_type) {
        this.seat_type = seat_type;
    }

    public double getSeat_price() {
        return seat_price;
    }

    public void setSeat_price(double seat_price) {
        this.seat_price = seat_price;
    }

    @Override
    public String toString() {
        return "Seat{" + "seat_name=" + seat_name + ", seat_type=" + seat_type + ", seat_price=" + seat_price + '}';
    }

}
