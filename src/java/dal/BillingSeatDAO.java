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

    public boolean hasBillingSeat(String billingID) {
        String query = "SELECT COUNT(*) FROM Billing_Seat WHERE BillingID = ?";
        try (
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, billingID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<String> getBookedSeats(int showtimeID) {
        List<String> bookedSeats = new ArrayList<>();
        String sql = "SELECT SeatName FROM Billing_Seat WHERE BillingID IN "
                + "(SELECT BillingID FROM Billing WHERE ShowtimeID = ? and PaymentStatus like 'Completed')";
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

    public List<String> getBookedSeatsByUser(int showtimeID, int accountID) {
        List<String> myBookedSeats = new ArrayList<>();
        String sql = "SELECT bs.SeatName FROM Billing_Seat bs "
                + "JOIN Billing b ON bs.BillingID = b.BillingID "
                + "WHERE b.ShowtimeID = ? AND b.UserID = ? and b.PaymentStatus like 'Completed'";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, showtimeID);
            stmt.setInt(2, accountID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                myBookedSeats.add(rs.getString("SeatName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return myBookedSeats;
    }

}
