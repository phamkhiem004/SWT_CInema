/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Movie;
import dal.DBContext;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.ShowTime;

public class MovieDAO extends DBContext {

    public List<Movie> getAllMovies() throws SQLException {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT * FROM Movie";

        try (PreparedStatement statement = connection.prepareStatement(sql); ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                Movie movie = new Movie();
                movie.setMovieID(rs.getInt("MovieID"));
                movie.setTitle(rs.getString("Title"));
                movie.setGenre(rs.getString("Genre"));
                movie.setDuration(rs.getInt("Duration"));
                movie.setDescription(rs.getString("Description")); // Sửa lại từ Summary
                movie.setReleaseDate(rs.getDate("ReleaseDate"));
                movie.setTrailer(rs.getString("Trailer"));
                movie.setCreatedAt(rs.getTimestamp("CreatedAt"));
                movie.setUpdatedAt(rs.getTimestamp("UpdatedAt"));
                movie.setPoster(rs.getString("Poster"));
                movie.setTrangThai(rs.getString("TrangThai")); // Thêm TrangThai
                movie.setStatus(rs.getString("Status"));
                movies.add(movie);
            }
        }
        return movies;
    }

    public List<Movie> getHomeMovies() throws SQLException {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT * FROM Movie WHERE TrangThai = N'Đang chiếu' AND Status = 'Active'  ORDER BY MovieID DESC";

        try (PreparedStatement statement = connection.prepareStatement(sql); ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                Movie movie = new Movie();
                movie.setMovieID(rs.getInt("MovieID"));
                movie.setTitle(rs.getString("Title"));
                movie.setGenre(rs.getString("Genre"));
                movie.setDescription(rs.getString("Description"));
                movie.setDuration(rs.getInt("Duration"));
                movie.setReleaseDate(rs.getDate("ReleaseDate"));
                movie.setTrailer(rs.getString("Trailer"));
                movie.setCreatedAt(rs.getTimestamp("CreatedAt"));
                movie.setUpdatedAt(rs.getTimestamp("UpdatedAt"));
                movie.setPoster(rs.getString("Poster"));
                movie.setTrangThai(rs.getString("TrangThai"));
                movie.setStatus(rs.getString("Status"));
                movies.add(movie);
            }
        }
        return movies;
    }

    public List<Movie> getNowShowingMovies() throws SQLException {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT * FROM Movie WHERE TrangThai = N'Đang chiếu' AND Status = 'Active' ";

        try (PreparedStatement statement = connection.prepareStatement(sql); ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                Movie movie = new Movie();
                movie.setMovieID(rs.getInt("MovieID"));
                movie.setTitle(rs.getString("Title"));
                movie.setGenre(rs.getString("Genre"));
                movie.setDescription(rs.getString("Description"));
                movie.setDuration(rs.getInt("Duration"));
                movie.setReleaseDate(rs.getDate("ReleaseDate"));
                movie.setTrailer(rs.getString("Trailer"));
                movie.setCreatedAt(rs.getTimestamp("CreatedAt"));
                movie.setUpdatedAt(rs.getTimestamp("UpdatedAt"));
                movie.setPoster(rs.getString("Poster"));
                movie.setTrangThai(rs.getString("TrangThai"));
                movie.setStatus(rs.getString("Status"));
                movies.add(movie);
            }
        }
        return movies;
    }

    public List<Movie> getComingSoonMovies() throws SQLException {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT * FROM Movie WHERE TrangThai = N'Sắp chiếu' AND Status = 'Active' ";

        try (PreparedStatement statement = connection.prepareStatement(sql); ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                Movie movie = new Movie();
                movie.setMovieID(rs.getInt("MovieID"));
                movie.setTitle(rs.getString("Title"));
                movie.setGenre(rs.getString("Genre"));
                movie.setDescription(rs.getString("Description"));
                movie.setDuration(rs.getInt("Duration"));
                movie.setReleaseDate(rs.getDate("ReleaseDate"));
                movie.setTrailer(rs.getString("Trailer"));
                movie.setCreatedAt(rs.getTimestamp("CreatedAt"));
                movie.setUpdatedAt(rs.getTimestamp("UpdatedAt"));
                movie.setPoster(rs.getString("Poster"));
                movie.setTrangThai(rs.getString("TrangThai"));
                movie.setStatus(rs.getString("Status"));
                movies.add(movie);
            }
        }
        return movies;
    }

    public Movie getMoviesByID(int movieID) {
        String sql = "SELECT * FROM Movie WHERE MovieID = ?";
        Movie movie = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, movieID);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    movie = new Movie();
                    movie.setMovieID(rs.getInt("MovieID"));
                    movie.setTitle(rs.getString("Title"));
                    movie.setGenre(rs.getString("Genre"));
                    movie.setDescription(rs.getString("Description"));
                    movie.setDuration(rs.getInt("Duration"));
                    movie.setReleaseDate(rs.getDate("ReleaseDate"));
                    movie.setTrailer(rs.getString("Trailer"));
                    movie.setCreatedAt(rs.getTimestamp("CreatedAt"));
                    movie.setUpdatedAt(rs.getTimestamp("UpdatedAt"));
                    movie.setPoster(rs.getString("Poster"));
                    movie.setTrangThai(rs.getString("TrangThai"));
                    movie.setStatus(rs.getString("Status"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movie;
    }

    public void createMovie(Movie movie) {
        String sql = "INSERT INTO Movie (Title, Genre, Description, Duration, ReleaseDate, Trailer, CreatedAt, UpdatedAt, Poster, TrangThai, Status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'Active')";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getGenre());
            stmt.setString(3, movie.getDescription());
            stmt.setInt(4, movie.getDuration());
            stmt.setDate(5, new java.sql.Date(movie.getReleaseDate().getTime()));
            stmt.setString(6, movie.getTrailer());
            stmt.setTimestamp(7, (Timestamp) movie.getCreatedAt());
            stmt.setTimestamp(8, (Timestamp) movie.getUpdatedAt());
            stmt.setString(9, movie.getPoster());
            stmt.setString(10, movie.getTrangThai());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateMovie(Movie movie) {
        String sql = "UPDATE Movie SET Title = ?, Genre = ?, Description = ?, Duration = ?, ReleaseDate = ?, Trailer = ?, CreatedAt = ?, UpdatedAt = ?, Poster = ?, TrangThai = ?, Status = 'Active'"
                + "WHERE MovieID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getGenre());
            stmt.setString(3, movie.getDescription());
            stmt.setInt(4, movie.getDuration());
            stmt.setDate(5, new java.sql.Date(movie.getReleaseDate().getTime()));
            stmt.setString(6, movie.getTrailer());
            stmt.setTimestamp(7, (Timestamp) movie.getCreatedAt());
            stmt.setTimestamp(8, (Timestamp) movie.getUpdatedAt());
            stmt.setString(9, movie.getPoster());
            stmt.setString(10, movie.getTrangThai());
            stmt.setInt(11, movie.getMovieID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean deactivateMovie(int movieID) {
        String sql = "UPDATE Movie SET Status = 'Inactive' WHERE MovieID = ? AND Status = 'Active'";

        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, movieID);
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu có dòng bị ảnh hưởng
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<ShowTime> getListShowTime(int MovieID, int CinemaID) throws SQLException {
        List<ShowTime> screening = new ArrayList<>();
        String sql = "Select StartTime from ShowTime join Room ON ShowTime.RoomID = Room.RoomID where MovieID = ? and CinemaID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, MovieID);
            statement.setInt(2, CinemaID);
            try (ResultSet rs = statement.executeQuery()) {

                while (rs.next()) {
                    ShowTime movie = new ShowTime();
                    movie.setStartTime(rs.getTimestamp("StartTime").toLocalDateTime());
                    screening.add(movie);
                }
            }

            return screening;
        }
    }

    public List<Integer> getMovieIDsByTheaterID(int cinemaID) throws SQLException {
        List<Integer> movieIDs = new ArrayList<>();
        String sql = "SELECT DISTINCT Movie.MovieID FROM Movie "
                + "JOIN ShowTime ON Movie.MovieID = ShowTime.MovieID "
                + "JOIN Room ON Room.RoomID = ShowTime.RoomID "
                + "WHERE CinemaID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, cinemaID);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    movieIDs.add(rs.getInt("MovieID"));
                }
            }
        }
        return movieIDs;

    }

}
