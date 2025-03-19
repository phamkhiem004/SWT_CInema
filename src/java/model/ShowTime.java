/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;
import java.sql.Timestamp;

/**
 *
 * @author Acer Predator
 */
public class ShowTime {

    private int ShowTimeID;
    private int CinemaID;
    private int MovieID;
    private LocalDateTime StartTime;

    public ShowTime() {
    }

    public ShowTime(int MovieID, LocalDateTime StartTime) {
        this.MovieID = MovieID;
        this.StartTime = StartTime;
    }

    public ShowTime(int ShowTimeID, int CinemaID, int MovieID, LocalDateTime StartTime) {
        this.ShowTimeID = ShowTimeID;
        this.CinemaID = CinemaID;
        this.MovieID = MovieID;
        this.StartTime = StartTime;
    }

    public int getShowTimeID() {
        return ShowTimeID;
    }

    public void setShowTimeID(int ShowTimeID) {
        this.ShowTimeID = ShowTimeID;
    }

    public int getCinemaID() {
        return CinemaID;
    }

    public void setCinemaID(int CinemaID) {
        this.CinemaID = CinemaID;
    }

    public int getMovieID() {
        return MovieID;
    }

    public void setMovieID(int MovieID) {
        this.MovieID = MovieID;
    }

    public LocalDateTime getStartTime() {
        return StartTime;
    }

    public void setStartTime(LocalDateTime StartTime) {
        this.StartTime = StartTime;
    }

    @Override
    public String toString() {
        return "ShowTime{" + "ShowTimeID=" + ShowTimeID + ", CinemaID=" + CinemaID + ", MovieID=" + MovieID + ", StartTime=" + StartTime + '}';
    }

}
