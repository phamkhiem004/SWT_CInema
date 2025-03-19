/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
import java.sql.Timestamp;

/**
 *
 * @author Acer Predator
 */
public class Movie {

    private int MovieID;
    private String Title;
    private String Genre;
    private int Duration;
    private String Description;
    private String Poster;
    private String Trailer;
    private Date ReleaseDate;
    private String TrangThai;
    private Timestamp CreatedAt;
    private Timestamp UpdatedAt;
    private String Status;

    public Movie() {
    }

    public Movie(int MovieID, String Title, String Poster) {
        this.MovieID = MovieID;
        this.Title = Title;
        this.Poster = Poster;
    }

    public Movie(int MovieID, String Title, String Genre, int Duration, String Description, String Poster, String Trailer, Date ReleaseDate, String TrangThai, Timestamp CreatedAt, Timestamp UpdatedAt, String Status) {
        this.MovieID = MovieID;
        this.Title = Title;
        this.Genre = Genre;
        this.Duration = Duration;
        this.Description = Description;
        this.Poster = Poster;
        this.Trailer = Trailer;
        this.ReleaseDate = ReleaseDate;
        this.TrangThai = TrangThai;
        this.CreatedAt = CreatedAt;
        this.UpdatedAt = UpdatedAt;
        this.Status = Status;
    }

    public Movie(int MovieID, String Title, String Genre, int Duration, String Description, String Poster, String Trailer, Date ReleaseDate, String TrangThai, Timestamp CreatedAt, Timestamp UpdatedAt) {
        this.MovieID = MovieID;
        this.Title = Title;
        this.Genre = Genre;
        this.Duration = Duration;
        this.Description = Description;
        this.Poster = Poster;
        this.Trailer = Trailer;
        this.ReleaseDate = ReleaseDate;
        this.TrangThai = TrangThai;
        this.CreatedAt = CreatedAt;
        this.UpdatedAt = UpdatedAt;
    }
    

    public int getMovieID() {
        return MovieID;
    }

    public void setMovieID(int MovieID) {
        this.MovieID = MovieID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String Genre) {
        this.Genre = Genre;
    }

    public int getDuration() {
        return Duration;
    }

    public void setDuration(int Duration) {
        this.Duration = Duration;
    }

    public Date getReleaseDate() {
        return ReleaseDate;
    }

    public void setReleaseDate(Date ReleaseDate) {
        this.ReleaseDate = ReleaseDate;
    }

    public String getTrailer() {
        return Trailer;
    }

    public void setTrailer(String Trailer) {
        this.Trailer = Trailer;
    }

    public Date getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(Date CreatedAt) {
        this.CreatedAt = (Timestamp) CreatedAt;
    }

    public Date getUpdatedAt() {
        return UpdatedAt;
    }

    public void setUpdatedAt(Date UpdatedAt) {
        this.UpdatedAt = (Timestamp) UpdatedAt;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String Poster) {
        this.Poster = Poster;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }
    

    @Override
    public String toString() {
        return "Movie{" + "MovieID=" + MovieID + ", Title=" + Title + ", Genre=" + Genre + ", Duration=" + Duration + ", Description=" + Description + ", Poster=" + Poster + ", Trailer=" + Trailer + ", ReleaseDate=" + ReleaseDate + ", TrangThai=" + TrangThai + ", CreatedAt=" + CreatedAt + ", UpdatedAt=" + UpdatedAt + ", Status=" + Status + '}';
    }

}
