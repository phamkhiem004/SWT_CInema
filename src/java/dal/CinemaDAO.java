/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import java.util.List;
import model.Cinema;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import model.Movie;
import model.ShowTime;

/**
 *
 * @author Acer Predator
 */
public class CinemaDAO extends DBContext {

    private Connection conn;

    public CinemaDAO(Connection conn) {
        this.conn = conn;
    }

    public CinemaDAO() {

    }

    public List<Cinema> getAllCinemas() throws SQLException {
        List<Cinema> cinemas = new ArrayList<>();
        String sql = "SELECT * FROM Cinema";

        try (PreparedStatement statement = connection.prepareStatement(sql); ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                Cinema cinema = new Cinema();
                cinema.setCinemaID(rs.getInt("CinemaID"));
                cinema.setName(rs.getString("Name"));
                cinema.setLocation(rs.getString("Location"));
                cinema.setCreatedAt(rs.getTimestamp("CreatedAt"));
                cinema.setUpdatedAt(rs.getTimestamp("UpdatedAt"));
                cinema.setImageURL(rs.getString("ImageURL"));
                cinema.setAddress(rs.getString("Address"));
                cinemas.add(cinema);
            }
        }
        return cinemas;
    }

    public Cinema getCinemaByID(int cinemaID) {
        String sql = "SELECT * FROM Cinema WHERE CinemaID = ?";
        Cinema cinema = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, cinemaID);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    cinema = new Cinema();
                    cinema.setCinemaID(rs.getInt("CinemaID"));
                    cinema.setName(rs.getString("Name"));
                    cinema.setLocation(rs.getString("Location"));
                    cinema.setCreatedAt(rs.getTimestamp("CreatedAt"));
                    cinema.setUpdatedAt(rs.getTimestamp("UpdatedAt"));
                    cinema.setImageURL(rs.getString("ImageURL"));
                    cinema.setAddress(rs.getString("Address"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi truy vấn rạp theo ID: " + e.getMessage());
            e.printStackTrace();
        }

        return cinema;
    }

    public List<Cinema> getCinemasByLocation(String location) throws SQLException {
        List<Cinema> cinemas = new ArrayList<>();
        String sql = "SELECT * FROM Cinema WHERE Location = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, location);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Cinema cinema = new Cinema();
                    cinema.setCinemaID(rs.getInt("CinemaID"));
                    cinema.setName(rs.getString("Name"));
                    cinema.setLocation(rs.getString("Location"));
                    cinema.setCreatedAt(rs.getTimestamp("CreatedAt"));
                    cinema.setUpdatedAt(rs.getTimestamp("UpdatedAt"));
                    cinema.setImageURL(rs.getString("ImageURL"));
                    cinema.setAddress(rs.getString("Address"));
                    cinemas.add(cinema);
                }
            }
        }
        return cinemas;
    }

    // Phương thức chuyển đổi trạng thái Active/Inactive
    public List<String> getAllLocations() throws SQLException {
        List<String> locations = new ArrayList<>();
        String sql = "SELECT DISTINCT Location FROM Cinema";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet rs = statement.executeQuery()) {

                while (rs.next()) {
                    locations.add(rs.getString("Location"));
                }
            }
            return locations;
        }

    }

    public List<Movie> getMoviesByTheaterID(int TheaterID) throws SQLException {
        List<Movie> movies = new ArrayList<>();
        String sql = "Select DISTINCT Movie.MovieID, Title, Movie.Poster from Cinema \n"
                + "Join Room ON Cinema.CinemaID = Room.CinemaID\n"
                + "Join ShowTime ON Room.RoomID = ShowTime.RoomID\n"
                + "Join Movie ON ShowTime.MovieID = Movie.MovieID where Cinema.CinemaID = ? ";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, TheaterID);

            try (ResultSet rs = statement.executeQuery()) {

                while (rs.next()) {
                    Movie movie = new Movie();
                    movie.setMovieID(rs.getInt("MovieID"));
                    movie.setTitle(rs.getString("Title"));
                    movie.setPoster(rs.getString("Poster"));
                    movies.add(movie);
                }
            }

            return movies;
        }

    }

    public ShowTime getScreeningTimeByTheaterID(int cinemaID) {
        String sql = "SELECT * from ShowTime Join Room ON Room.RoomID = ShowTime.RoomID WHERE CinemaID = ?";
        ShowTime theater = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, cinemaID);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    theater = new ShowTime();
                    theater.setCinemaID(rs.getInt("CinemaID"));
                    theater.setMovieID(rs.getInt("MovieID"));
                    theater.setStartTime(rs.getTimestamp("StartTime").toLocalDateTime());
                }
            }
        } catch (SQLException e) {
            // In ra thông báo lỗi để debug
            System.err.println("Lỗi khi truy vấn lịch chiếu theo ID: " + e.getMessage());
            e.printStackTrace();
        }

        return theater;
    }

    public List<Cinema> getCinemasByMovieId(int movieId) {
        List<Cinema> cinemas = new ArrayList<>();
        String sql = "SELECT DISTINCT c.CinemaID, c.Name FROM Cinema c "
                + "JOIN Room r ON c.CinemaID = r.CinemaID "
                + "JOIN Showtime s ON r.RoomID = s.RoomID "
                + "WHERE s.MovieID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, movieId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    cinemas.add(new Cinema(rs.getInt("CinemaID"), rs.getString("Name")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cinemas;
    }

}
