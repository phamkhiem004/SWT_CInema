/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Billing;
import model.BillingCombo;

/**
 *
 * @author SHD
 */
public class BillingDAO extends DBContext {

    public String getEmailByUserID(int userID) {
        String sql = "SELECT Email FROM Users WHERE UserID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("Email");
                }
            }
        } catch (SQLException e) {
            
        }
        return null; // Trả về null nếu không tìm thấy
    }

    public void updatePaymentStatus(String billingID) {
        String sql = "UPDATE Billing SET PaymentStatus = 'Completed' WHERE BillingID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, billingID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Lấy thông tin thanh toán
    public Billing getBillingByID(String billingID) {
        String sql = "SELECT * FROM Billing WHERE BillingID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, billingID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Billing(
                            rs.getString("BillingID"),
                            rs.getInt("UserID"),
                            rs.getInt("ShowtimeID"),
                            rs.getBigDecimal("TotalAmount"),
                            rs.getString("PaymentMethod"),
                            rs.getString("PaymentStatus"),
                            rs.getInt("DiscountID"),
                            rs.getDate("BookingDate")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Lấy danh sách ghế đã đặt
    public List<String> getSeatsByBillingID(String billingID) {
        List<String> seats = new ArrayList<>();
        String sql = "SELECT SeatName FROM Billing_Seat WHERE BillingID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, billingID);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    seats.add(rs.getString("SeatName"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seats;
    }

    // Lấy danh sách combo theo billing
    public List<BillingCombo> getCombosByBillingID(String billingID) {
        List<BillingCombo> combos = new ArrayList<>();
        String sql = "SELECT * FROM BillingCombo WHERE BillingID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, billingID);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    combos.add(new BillingCombo(
                            rs.getInt("BillingComboID"),
                            rs.getString("BillingID"),
                            rs.getInt("ComboID"),
                            rs.getInt("Quantity")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return combos;
    }

 
    public String getMovieNameByShowtimeID(int showtimeID) {
        String sql = "SELECT m.Title FROM Showtime s JOIN Movie m ON s.MovieID = m.MovieID WHERE s.ShowtimeID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, showtimeID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("Title"); // Lấy đúng cột "Title" từ bảng Movie
                }
            }
        } catch (SQLException e) {

        }
        return null; // Trả về null thay vì chuỗi rỗng để dễ kiểm tra lỗi
    }

    // Lấy RoomID từ ShowtimeID
    public int getRoomIDByShowtimeID(int showtimeID) {
        String sql = "SELECT RoomID FROM Showtime WHERE ShowtimeID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, showtimeID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("RoomID");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public String generateBillingID() {
        String prefix = "BILL";
        String sql = "SELECT MAX(BillingID) FROM Billing";
        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            if (rs.next() && rs.getString(1) != null) {
                String lastID = rs.getString(1); // Lấy ID lớn nhất hiện tại
                int number = Integer.parseInt(lastID.substring(4)); // Lấy số phần từ "BILLxxx"
                return prefix + String.format("%03d", number + 1); // Tạo ID mới
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prefix + "001"; // Nếu không có bản ghi nào, bắt đầu từ BILL001
    }

    public boolean hasPendingBilling(int userID, int showtimeID) {
        String sql = "SELECT COUNT(*) FROM Billing WHERE UserID = ? AND ShowtimeID = ? AND PaymentStatus = 'Pending'";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userID);
            stmt.setInt(2, showtimeID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    return true; // Có ít nhất một bản ghi Pending
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Không có bản ghi nào
    }

    public String createBilling(int userID, int showtimeID, BigDecimal totalAmount, String paymentMethod, Integer discountID) {
        String billingID = generateBillingID();
        String sql = "INSERT INTO Billing (BillingID, UserID, ShowtimeID, TotalAmount, PaymentMethod, PaymentStatus, DiscountID, BookingDate) "
                + "VALUES (?, ?, ?, ?, ?, 'Pending', ?, GETDATE())";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, billingID);
            stmt.setInt(2, userID);
            stmt.setInt(3, showtimeID);
            stmt.setBigDecimal(4, totalAmount);
            stmt.setString(5, paymentMethod);
            stmt.setObject(6, discountID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return billingID;
    }

    public void addBillingSeat(String billingID, int seatID, String seatName) {
        String sql = "INSERT INTO Billing_Seat (BillingID, SeatID, SeatName) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, billingID);
            stmt.setInt(2, seatID);
            stmt.setString(3, seatName);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getSeatIDByName(String seatName) {
        String sql = "SELECT SeatID FROM Seat WHERE SeatType = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, seatName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("SeatID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Không tìm thấy
    }

    public boolean updateBillingTotalAmountAndDiscount(String billingID, BigDecimal newTotalAmount, Integer discountID) {
        String sql = "UPDATE Billing SET TotalAmount = ?, DiscountID = ? WHERE BillingID = ? AND PaymentStatus = 'Pending'";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setBigDecimal(1, newTotalAmount);
            stmt.setObject(2, discountID); // Nếu discountID là null, vẫn hoạt động
            stmt.setString(3, billingID);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isBillingPending(int userID, int showtimeID) {
        String sql = "SELECT COUNT(*) FROM Billing WHERE UserID = ? AND ShowtimeID = ? AND PaymentStatus = 'Pending'";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userID);
            ps.setInt(2, showtimeID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isBillingPending(String billingID) {
        String sql = "SELECT COUNT(*) FROM Billing WHERE BillingID = ? AND PaymentStatus = 'Pending'";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, billingID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getPendingBillingID(int userID, int showtimeID) {
        String sql = "SELECT BillingID FROM Billing WHERE UserID = ? AND ShowtimeID = ? AND PaymentStatus = 'Pending'";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userID);
            ps.setInt(2, showtimeID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("BillingID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateTotalAmount(String billingID, BigDecimal additionalAmount) {
        String sql = "UPDATE Billing SET TotalAmount = TotalAmount + ? WHERE BillingID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setBigDecimal(1, additionalAmount);
            ps.setString(2, billingID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePaymentMethod(String billingID, String paymentMethod) {
        String sql = "UPDATE Billing SET PaymentMethod = ? WHERE BillingID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, paymentMethod);
            ps.setString(2, billingID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
