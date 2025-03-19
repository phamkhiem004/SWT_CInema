/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author DUNGVT
 */
public class Seat {
    String seat_name;
    String seat_type;
    double seat_price;

    public Seat() {
    }

    public Seat(String seat_name, String seat_type, double seat_price) {
        this.seat_name = seat_name;
        this.seat_type = seat_type;
        this.seat_price = seat_price;
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
