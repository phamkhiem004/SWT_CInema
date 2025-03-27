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
import java.sql.Timestamp;
import java.time.LocalDateTime;
import model.ShowTime;
import java.sql.SQLException;

/**
 *
 * @author Acer Predator
 */
public class ShowtimeDAO extends DBContext {

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

    public void addShowtime(ShowTime showtime) {
        String sql = "INSERT INTO Showtime (MovieID, RoomID,StartTime,Status) VALUES (?, ?,?,?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, showtime.getMovieID());
            stmt.setInt(2, showtime.getRoomID());
            stmt.setTimestamp(3, Timestamp.valueOf(showtime.getStartTime())); // Chuyển Date -> Timestamp
            stmt.setString(4, showtime.getStatus());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deactivateShowTime(int showTimeID) {
        String sql = "UPDATE Showtime SET Status = 'Inactive' WHERE ShowtimeID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, showTimeID);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ShowTime> getAllScreeningTimes() {
        List<ShowTime> list = new ArrayList<>();
        String sql = "SELECT s.ShowtimeID, m.MovieID, c.CinemaID, r.RoomID, s.StartTime, s.Status "
                + "FROM Showtime s "
                + "JOIN Room r ON s.RoomID = r.RoomID "
                + "JOIN Cinema c ON r.CinemaID = c.CinemaID "
                + "JOIN Movie m ON s.MovieID = m.MovieID where s.Status ='Active'";

        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ShowTime screening = new ShowTime();
                screening.setShowTimeID(rs.getInt("ShowtimeID"));
                screening.setMovieID(rs.getInt("MovieID"));
                screening.setCinemaID(rs.getInt("CinemaID"));
                screening.setRoomID(rs.getInt("RoomID"));
                Timestamp timestamp = rs.getTimestamp("StartTime");
                LocalDateTime startTime = timestamp.toLocalDateTime();
                screening.setStartTime(startTime);
                screening.setStatus(rs.getString("Status"));
                list.add(screening);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
