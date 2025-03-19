/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;

/**
 *
 * @author Acer Predator
 */
public class Cinema {

    private int CinemaID;
    private String Name;
    private String Location;
    private Timestamp CreatedAt;
    private Timestamp UpdatedAt;
    private String ImageURL;
    private String Address;

    public Cinema() {
    }

    public Cinema(int CinemaID, String Name, String Location, Timestamp CreatedAt, Timestamp UpdatedAt, String ImageURL, String Address) {
        this.CinemaID = CinemaID;
        this.Name = Name;
        this.Location = Location;
        this.CreatedAt = CreatedAt;
        this.UpdatedAt = UpdatedAt;
        this.ImageURL = ImageURL;
        this.Address = Address;
    }

    public int getCinemaID() {
        return CinemaID;
    }

    public void setCinemaID(int CinemaID) {
        this.CinemaID = CinemaID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }



    public Timestamp getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(Timestamp CreatedAt) {
        this.CreatedAt = CreatedAt;
    }

    public Timestamp getUpdatedAt() {
        return UpdatedAt;
    }

    public void setUpdatedAt(Timestamp UpdatedAt) {
        this.UpdatedAt = UpdatedAt;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String ImageURL) {
        this.ImageURL = ImageURL;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    @Override
    public String toString() {
        return "Cinema{" + "CinemaID=" + CinemaID + ", Name=" + Name + ", Location=" + Location + ", CreatedAt=" + CreatedAt + ", UpdatedAt=" + UpdatedAt + ", ImageURL=" + ImageURL + ", Address=" + Address + '}';
    }

    

}
