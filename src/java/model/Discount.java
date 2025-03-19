/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Acer Predator
 */
public class Discount {

    private int DiscountID;
    private String DiscountName;
    private String DiscountCode;
    private float DiscountPercentage;
    private Date ExpiryDate;
    private Timestamp CreatedAt;
    private Timestamp UpdatedAt;
    private String Status;
    private String ImageURL;
    private String Description;

    public Discount() {
    }

    public Discount(int DiscountID, String DiscountName, String DiscountCode, float DiscountPercentage, Date ExpiryDate, Timestamp CreatedAt, Timestamp UpdatedAt, String ImageURL, String Description) {
        this.DiscountID = DiscountID;
        this.DiscountName = DiscountName;
        this.DiscountCode = DiscountCode;
        this.DiscountPercentage = DiscountPercentage;
        this.ExpiryDate = ExpiryDate;
        this.CreatedAt = CreatedAt;
        this.UpdatedAt = UpdatedAt;
        this.ImageURL = ImageURL;
        this.Description = Description;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public int getDiscountID() {
        return DiscountID;
    }

    public void setDiscountID(int DiscountID) {
        this.DiscountID = DiscountID;
    }

    public String getDiscountCode() {
        return DiscountCode;
    }

    public void setDiscountCode(String DiscountCode) {
        this.DiscountCode = DiscountCode;
    }

    public float getDiscountPercentage() {
        return DiscountPercentage;
    }

    public void setDiscountPercentage(float DiscountPercentage) {
        this.DiscountPercentage = DiscountPercentage;
    }

    public Date getExpiryDate() {
        return ExpiryDate;
    }

    public void setExpiryDate(Date ExpiryDate) {
        this.ExpiryDate = ExpiryDate;
    }

    public Timestamp getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(Timestamp CreatedAt) {
        this.CreatedAt = CreatedAt;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String ImageURL) {
        this.ImageURL = ImageURL;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getDiscountName() {
        return DiscountName;
    }

    public void setDiscountName(String DiscountName) {
        this.DiscountName = DiscountName;
    }

    public Timestamp getUpdatedAt() {
        return UpdatedAt;
    }

    public void setUpdatedAt(Timestamp UpdatedAt) {
        this.UpdatedAt = UpdatedAt;
    }

    @Override
    public String toString() {
        return "Discount{" + "DiscountID=" + DiscountID + ", DiscountName=" + DiscountName + ", DiscountCode=" + DiscountCode + ", DiscountPercentage=" + DiscountPercentage + ", ExpiryDate=" + ExpiryDate + ", CreatedAt=" + CreatedAt + ", UpdatedAt=" + UpdatedAt + ", ImageURL=" + ImageURL + ", Description=" + Description + '}';
    }

}
