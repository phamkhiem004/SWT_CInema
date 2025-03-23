/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Acer Predator
 */
public class BillingSeatDAO extends DBContext {

    public List<String> getBookedSeats(int showtimeID) {
        List<String> bookedSeats = new ArrayList<>();
        String sql = "SELECT SeatName FROM Billing_Seat WHERE BillingID IN "
                + "(SELECT BillingID FROM Billing WHERE ShowtimeID = ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, showtimeID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                bookedSeats.add(rs.getString("SeatName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookedSeats;
    }

}
