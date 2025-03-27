/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.Billing;
import model.BillingCombo;

/**
 *
 * @author SHD
 */
public class BillingDAO extends DBContext {

    public Billing getBillingById(String billingID) {
        String query = "SELECT * FROM Billing WHERE BillingID = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, billingID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Billing billing = new Billing();
                billing.setBillingID(rs.getString("BillingID"));
                billing.setUserID(rs.getInt("UserID"));
                billing.setShowtimeID(rs.getInt("ShowtimeID"));
                billing.setTotalAmount(rs.getBigDecimal("TotalAmount"));
                billing.setPaymentMethod(rs.getString("PaymentMethod"));
                billing.setPaymentStatus(rs.getString("PaymentStatus"));
                billing.setDiscountID(rs.getInt("DiscountID"));
                billing.setBookingDate(rs.getDate("BookingDate"));
                return billing;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Billing getBillingDetailById(String billingID) {
        String query = "SELECT Billing.BillingID, Title, BookingDate, TotalAmount, PaymentMethod, PaymentStatus, DiscountPercentage, StartTime, Discount.DiscountID, SeatName "
                + "FROM Billing "
                + "JOIN Showtime ON Billing.ShowtimeID = Showtime.ShowtimeID "
                + "LEFT JOIN Discount ON Discount.DiscountID = Billing.DiscountID "
                + "JOIN Movie ON Movie.MovieID = Showtime.MovieID "
                + "JOIN Billing_Seat ON Billing.BillingID = Billing_Seat.BillingID "
                + "WHERE Billing.BillingID = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, billingID);
            ResultSet rs = ps.executeQuery();
            Billing billing = null;
            List<String> seatNames = new ArrayList<>();

            while (rs.next()) {
                if (billing == null) {
                    billing = new Billing();
                    billing.setBillingID(rs.getString("BillingID"));
                    billing.setTitle(rs.getString("Title"));
                    billing.setBookingDate(rs.getDate("BookingDate"));
                    billing.setStartTime(rs.getTimestamp("StartTime").toLocalDateTime());
                    billing.setTotalAmount(rs.getBigDecimal("TotalAmount"));
                    billing.setPaymentMethod(rs.getString("PaymentMethod"));
                    billing.setPaymentStatus(rs.getString("PaymentStatus"));
                    billing.setDiscountPercentage(rs.getDouble("DiscountPercentage"));
                    billing.setDiscountID(rs.getInt("DiscountID"));
                }
                seatNames.add(rs.getString("SeatName"));
            }

            if (billing != null) {
                billing.setSeatNames(seatNames);  // Lưu danh sách ghế vào Billing
            }

            return billing;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

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
    // Lấy tất cả các hoá đơn

    public List<Billing> getAllBillings() {
        List<Billing> billingList = new ArrayList<>();
        String query = "SELECT * FROM billing"; // Cập nhật tên bảng và trường nếu cần
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Billing billing = new Billing(
                        rs.getString("billingID"),
                        rs.getInt("userID"),
                        rs.getInt("showtimeID"),
                        rs.getBigDecimal("totalAmount"),
                        rs.getString("paymentMethod"),
                        rs.getString("paymentStatus"),
                        rs.getInt("discountID"),
                        rs.getDate("bookingDate")
                );
                billingList.add(billing);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return billingList;
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

    public boolean updatePaymentStatus(String billingID, String status) {
        String query = "UPDATE Billing SET PaymentStatus = ? WHERE BillingID like ?";
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, status); // Cập nhật trạng thái
            statement.setString(2, billingID); // Cập nhật billingID

            int rowsAffected = statement.executeUpdate();

            return rowsAffected > 0; // Trả về true nếu cập nhật thành công
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Nếu có lỗi, trả về false
        }
    }

    // Lấy thông tin thanh toán
    public Billing getBillingByID(String billingID) {
        String sql = "SELECT * FROM Billing WHERE BillingID like ?";
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

    // Lấy thông tin thanh toán
    public List<Billing> getBillingByUserID(int userID) {
        List<Billing> billingList = new ArrayList<>();
        String sql = "Select BillingID, Title, BookingDate, TotalAmount, PaymentMethod, PaymentStatus from Billing \n"
                + "Join Showtime On Billing.ShowtimeID = Showtime.ShowtimeID\n"
                + "Join Movie On Movie.MovieID = Showtime.MovieID where UserID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userID);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    billingList.add(new Billing(
                            rs.getString("BillingID"),
                            rs.getBigDecimal("TotalAmount"),
                            rs.getString("PaymentMethod"),
                            rs.getString("PaymentStatus"),
                            rs.getDate("BookingDate"),
                            rs.getString("Title")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return billingList;
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

    public void updateQuantity(int comboID, int Quantity) {
        String sql = "Update Combo set Quantity = (Quantity - ?) where ComboID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, Quantity);
            ps.setInt(2, comboID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
            ps.setString(1, "QR Pay");
            ps.setString(2, billingID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Billing> getAllBillsByText(String search) {
        List<Billing> list = new ArrayList<>();
        String sql = "SELECT * FROM Billing WHERE BillingID LIKE ? OR UserID LIKE ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            String searchText = "%" + search + "%";
            ps.setString(1, searchText);
            ps.setString(2, searchText); // UserID là INT nhưng truyền dưới dạng String để dùng LIKE
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Billing bill = new Billing();
                bill.setBillingID(rs.getString("BillingID"));
                bill.setUserID(rs.getInt("UserID"));
                bill.setShowtimeID(rs.getInt("ShowtimeID"));
                bill.setTotalAmount(rs.getBigDecimal("TotalAmount"));
                bill.setPaymentMethod(rs.getString("PaymentMethod"));
                bill.setPaymentStatus(rs.getString("PaymentStatus"));
                bill.setDiscountID(rs.getInt("DiscountID"));
                bill.setBookingDate(rs.getDate("BookingDate"));

                list.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean declineBill(String billingID) {
        String sql = "UPDATE Billing SET PaymentStatus = 'Cancelled' WHERE BillingID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, billingID);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteBillingById(String billingID) {
        String query = "DELETE FROM Billing WHERE billingID = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, billingID);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
