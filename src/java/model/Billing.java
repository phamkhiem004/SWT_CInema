/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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
    private String Title;
    private double DiscountPercentage;
    private LocalDateTime StartTime;
    private List<String> seatNames;

    public List<String> getSeatNames() {
        return seatNames;
    }

    public void setSeatNames(List<String> seatNames) {
        this.seatNames = seatNames;
    }

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

    public Billing(String billingID, int userID, int showtimeID, BigDecimal totalAmount, String paymentMethod, String paymentStatus, Integer discountID, Date bookingDate, String Title, double DiscountPercentage, LocalDateTime StartTime) {
        this.billingID = billingID;
        this.userID = userID;
        this.showtimeID = showtimeID;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.discountID = discountID;
        this.bookingDate = bookingDate;
        this.Title = Title;
        this.DiscountPercentage = DiscountPercentage;
        this.StartTime = StartTime;
    }

    public Billing(String billingID, BigDecimal totalAmount, String paymentMethod, String paymentStatus, Date bookingDate, String Title) {
        this.billingID = billingID;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.bookingDate = bookingDate;
        this.Title = Title;
    }
    

    public LocalDateTime getStartTime() {
        return StartTime;
    }

    public void setStartTime(LocalDateTime StartTime) {
        this.StartTime = StartTime;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public double getDiscountPercentage() {
        return DiscountPercentage;
    }

    public void setDiscountPercentage(double DiscountPercentage) {
        this.DiscountPercentage = DiscountPercentage;
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
