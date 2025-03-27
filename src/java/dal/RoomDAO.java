/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.List;
import model.Room;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author Acer Predator
 */
public class RoomDAO extends DBContext {
    private Connection conn;

    public RoomDAO(Connection conn) {
        this.conn = conn;
    }

    public RoomDAO() {

    }

    public List<Room> getRoomsByCinema(int cinemaID) {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM Room WHERE CinemaID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, cinemaID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Room room = new Room(
                        rs.getInt("RoomID"),
                        rs.getInt("CinemaID"),
                        rs.getString("Name"),
                        rs.getInt("SeatCapacity")
                );
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

}
