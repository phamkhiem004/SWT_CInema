/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Discount;
import dal.DBContext;
import java.security.SecureRandom;
import java.sql.Timestamp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Acer Predator
 */
public class DiscountDAO extends DBContext {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CODE_LENGTH = 6;
    private static final SecureRandom random = new SecureRandom();

    public List<Discount> getAllDiscounts() throws SQLException {
        List<Discount> discounts = new ArrayList<>();
        String sql = "SELECT * FROM Discount WHERE Status = 'Active'";

        try (PreparedStatement statement = connection.prepareStatement(sql); ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                Discount discount = new Discount();
                discount.setDiscountID(rs.getInt("DiscountID"));
                discount.setDiscountName(rs.getString("DiscountName"));
                discount.setDiscountCode(rs.getString("DiscountCode"));
                discount.setDiscountPercentage(rs.getFloat("DiscountPercentage"));
                discount.setExpiryDate(rs.getDate("ExpiryDate"));
                discount.setCreatedAt(rs.getTimestamp("CreatedAt"));
                discount.setUpdatedAt(rs.getTimestamp("UpdatedAt"));
                discount.setStatus(rs.getString("Status"));
                discount.setImageURL(rs.getString("ImageURL"));
                discount.setDescription(rs.getString("Description"));
                discounts.add(discount);
            }
        }
        return discounts;
    }

    public Discount getDiscountByCode(String discountCode) {
        String sql = "SELECT * FROM Discount WHERE DiscountCode = ? AND Status = 'Active' AND ExpiryDate > GETDATE()";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, discountCode);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Discount(
                        rs.getInt("DiscountID"),
                        rs.getString("DiscountName"),
                        rs.getString("DiscountCode"),
                        rs.getFloat("DiscountPercentage"),
                        rs.getDate("ExpiryDate")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Discount getDiscountByID(int discountID) {
        String sql = "SELECT * FROM Discount WHERE DiscountID = ? AND Status = 'Active'";
        Discount discount = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, discountID);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    discount = new Discount();
                    discount.setDiscountID(rs.getInt("DiscountID"));
                    discount.setDiscountName(rs.getString("DiscountName"));
                    discount.setDiscountCode(rs.getString("DiscountCode"));
                    discount.setDiscountPercentage(rs.getFloat("DiscountPercentage"));
                    discount.setExpiryDate(rs.getDate("ExpiryDate"));
                    discount.setCreatedAt(rs.getTimestamp("CreatedAt"));
                    discount.setUpdatedAt(rs.getTimestamp("UpdatedAt"));
                    discount.setStatus(rs.getString("Status"));
                    discount.setImageURL(rs.getString("ImageURL"));
                    discount.setDescription(rs.getString("Description"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi truy vấn mã giảm giá theo ID: " + e.getMessage());
            e.printStackTrace();
        }

        return discount;
    }

    public void createDiscount(Discount discount) {
        String sql = "INSERT INTO Discount (DiscountName, DiscountCode, DiscountPercentage, ExpiryDate, CreatedAt, UpdatedAt, ImageURL, Description, Status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, 'Active')";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, discount.getDiscountName());
            stmt.setString(2, discount.getDiscountCode());
            stmt.setFloat(3, discount.getDiscountPercentage());
            stmt.setDate(4, new java.sql.Date(discount.getExpiryDate().getTime()));
            stmt.setTimestamp(5, discount.getCreatedAt());
            stmt.setTimestamp(6, discount.getUpdatedAt());
            stmt.setString(7, discount.getImageURL());
            stmt.setString(8, discount.getDescription());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDiscount(Discount discount) {
        String sql = "UPDATE Discount SET DiscountName = ?, DiscountCode = ?, DiscountPercentage = ?, ExpiryDate = ?,CreatedAt = ? , UpdatedAt = ?, ImageURL = ?, Description = ?, Status = 'Active' WHERE DiscountID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, discount.getDiscountName());
            stmt.setString(2, discount.getDiscountCode());
            stmt.setFloat(3, discount.getDiscountPercentage());
            stmt.setDate(4, new java.sql.Date(discount.getExpiryDate().getTime()));
            stmt.setTimestamp(5, discount.getCreatedAt());
            stmt.setTimestamp(6, discount.getUpdatedAt()); // Cập nhật thời gian sửa đổi
            stmt.setString(7, discount.getImageURL());
            stmt.setString(8, discount.getDescription());
            stmt.setInt(9, discount.getDiscountID()); // Điều kiện WHERE

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Phương thức chuyển đổi trạng thái Active/Inactive
    public boolean toggleDiscountStatus(int discountID) {
        String sql = "UPDATE Discount SET Status = CASE WHEN Status = 'Active' THEN 'Inactive' ELSE 'Active' END WHERE DiscountID = ?";

        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, discountID);
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String generateUniqueDiscountCode() {
        String code;
        do {
            code = generateRandomCode();
        } while (isDiscountCodeExists(code)); // Kiểm tra nếu đã tồn tại thì tạo lại
        return code;
    }

    private String generateRandomCode() {
        StringBuilder sb = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }
    public void applyDiscount(String billingID, int discountID, float discountPercentage) {
        String sql = "UPDATE Billing SET DiscountID = ?, TotalAmount = TotalAmount * (1 - ? / 100) WHERE BillingID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, discountID);
            ps.setFloat(2, discountPercentage);
            ps.setString(3, billingID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Kiểm tra xem mã giảm giá đã tồn tại trong DB chưa
    private boolean isDiscountCodeExists(String code) {
        String sql = "SELECT COUNT(*) FROM Discount WHERE DiscountCode = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, code);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
