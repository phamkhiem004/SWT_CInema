/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.Timestamp;
import java.sql.Date;
/**
 *
 * @author DUNGVT
 */
public class Bill {
     String booking_id;
     String movie_name;
     double discount;
     Date booking_date;
     double totalprice;
     String payment_method;
     String payment_status;

    public Bill() {
    }

    public Bill(String booking_id, String movie_name, double discount, Date booking_date, double totalprice, String payment_method, String payment_status) {
        this.booking_id = booking_id;
        this.movie_name = movie_name;
        this.discount = discount;
        this.booking_date = booking_date;
        this.totalprice = totalprice;
        this.payment_method = payment_method;
        this.payment_status = payment_status;
    }

    public String getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(String booking_id) {
        this.booking_id = booking_id;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Date getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(Date booking_date) {
        this.booking_date = booking_date;
    }

    public double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }

    @Override
    public String toString() {
        return "Bill{" + "booking_id=" + booking_id + ", movie_name=" + movie_name + ", discount=" + discount + ", booking_date=" + booking_date + ", totalprice=" + totalprice + ", payment_method=" + payment_method + ", payment_status=" + payment_status + '}';
    }
     
}
