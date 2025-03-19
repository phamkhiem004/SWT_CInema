/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;
import java.util.Date;

public class Account {

    private int id;
    private String fullname;
    private String email;
    private String phoneNumber;
    private String password;
    private String gender;
    private String address;
    private Date dateOfBirth;
    private String role;
    private String status;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // Constructor
     public Account() {

    }

    public Account(int id, String fullname, String email, String phoneNumber, String password, String gender, String address, Date dateOfBirth, String role, String status, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.gender = gender;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Account(int id, String fullname, String email, String phoneNumber, String password, String gender, Timestamp address, int dateOfBirth, int role, String status, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this .fullname = fullname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.gender = gender;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Account(int id, String email, String password, String fullname, Timestamp createdAt, String status, String role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.createdAt = createdAt;
        this.status = status;
        this.role = role;
    }
 
public Account(String fullname, String email, String password, String phoneNumber,String address,String gender, Date dateOfBirth) {
    this.fullname = fullname;
    this.email = email;
    this.password = password;
    this.phoneNumber = phoneNumber;
    this.address = address;
    this.gender = gender;
    this.dateOfBirth = dateOfBirth;
}
    // Getters and Setters for all the fields
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", fullname=" + fullname + ", email=" + email + ", phoneNumber=" + phoneNumber + ", password=" + password + ", gender=" + gender + ", address=" + address + ", dateOfBirth=" + dateOfBirth + ", role=" + role + ", status=" + status + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }
    
}



