/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Acer Predator
 */
public class Billing {

    private String billingID;
    private int userID;
    private int showtimeID;
    private BigDecimal totalAmount;
    private String paymentMethod;
    private String paymentStatus;
    private Integer discountID;
    private Date bookingDate;

    public Billing() {
    }

    public Billing(String billingID, int userID, int showtimeID, BigDecimal totalAmount,
            String paymentMethod, String paymentStatus, Integer discountID, Date bookingDate) {
        this.billingID = billingID;
        this.userID = userID;
        this.showtimeID = showtimeID;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.discountID = discountID;
        this.bookingDate = bookingDate;
    }

    public String getBillingID() {
        return billingID;
    }

    public void setBillingID(String billingID) {
        this.billingID = billingID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getShowtimeID() {
        return showtimeID;
    }

    public void setShowtimeID(int showtimeID) {
        this.showtimeID = showtimeID;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Integer getDiscountID() {
        return discountID;
    }

    public void setDiscountID(Integer discountID) {
        this.discountID = discountID;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }
}
