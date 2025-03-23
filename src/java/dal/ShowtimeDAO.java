/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Movie;

/**
 *
 * @author Acer Predator
 */
public class ShowtimeDAO extends DBContext {

    public static void main(String[] args) {
        System.out.println(new ShowtimeDAO().getNowShowingMovies());
    }

    public List<Movie> getNowShowingMovies() {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT DISTINCT m.MovieID, m.Title, m.Genre, m.Duration, m.ReleaseDate, "
                + "m.Description, m.Poster, m.Status, m.Trailer, m.CreatedAt "
                + "FROM Movie m "
                + "JOIN Showtime s ON m.MovieID = s.MovieID "
                + "WHERE s.StartTime > GETDATE()"; // Lọc các suất chiếu có StartTime > thời gian hiện tại
        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Movie movie = new Movie(
                        rs.getInt("MovieID"),
                        rs.getString("Title"),
                        rs.getString("Genre"),
                        rs.getString("Description"),
                        rs.getInt("Duration"),
                        rs.getDate("ReleaseDate"),
                        rs.getString("Trailer"),
                        rs.getTimestamp("CreatedAt"),
                        null, // UpdatedAt không cần
                        rs.getString("Poster"),
                        rs.getString("Status")
                );
                movies.add(movie);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movies;
    }
}
