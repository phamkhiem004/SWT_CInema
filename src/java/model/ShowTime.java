/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Acer Predator
 */
public class ShowTime {

    private int ShowTimeID;
    private int CinemaID;  
    private int MovieID;
    private LocalDateTime StartTime;
    
    int showtime_id;
    int movie_id;
    int room_id;
    Date showtime;
    

    public ShowTime() {
    }

    public ShowTime(int showtime_id, int movie_id, int room_id, Date showtime) {
        this.showtime_id = showtime_id;
        this.movie_id = movie_id;
        this.room_id = room_id;
        this.showtime = showtime;
    }

    public int getShowtime_id() {
        return showtime_id;
    }

    public void setShowtime_id(int showtime_id) {
        this.showtime_id = showtime_id;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public Date getShowtime() {
        return showtime;
    }

    public void setShowtime(Date showtime) {
        this.showtime = showtime;
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
