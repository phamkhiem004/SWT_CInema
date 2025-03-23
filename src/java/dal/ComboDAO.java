/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import java.util.List;
import model.Combo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Acer Predator
 */
public class ComboDAO extends DBContext {

    public List<Combo> getAllCombo() throws SQLException {
        List<Combo> combos = new ArrayList<>();
        String sql = "SELECT * FROM Combo WHERE Status = 'Active'";

        try (PreparedStatement statement = connection.prepareStatement(sql); ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                Combo combo = new Combo();
                combo.setCombo_id(rs.getInt("ComboID"));
                combo.setCombo_name(rs.getString("ComboName"));
                combo.setPoster_url(rs.getString("Poster"));
                combo.setDescription(rs.getString("Description"));
                combo.setStatus(rs.getString("Status"));
                combo.setPrice(rs.getDouble("Cost"));
                combo.setQuantity(rs.getInt("Quantity"));
                combos.add(combo);
            }
        }
        return combos;
    }

    public Combo getComboByID(int comboID) {
        String sql = "SELECT * FROM Combo WHERE ComboID = ? AND Status = 'Active'";
        Combo combo = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, comboID);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    combo = new Combo();
                    combo.setCombo_id(rs.getInt("ComboID"));
                    combo.setCombo_name(rs.getString("ComboName"));
                    combo.setPoster_url(rs.getString("Poster"));
                    combo.setDescription(rs.getString("Description"));
                    combo.setStatus(rs.getString("Status"));
                    combo.setPrice(rs.getDouble("Cost"));
                    combo.setQuantity(rs.getInt("Quantity"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi truy vấn combo theo ID: " + e.getMessage());
            e.printStackTrace();
        }

        return combo;
    }

    public void createCombo(Combo combo) {
        String sql = "INSERT INTO Combo (ComboName, Poster, Description, Status, Cost, Quantity) "
                + "VALUES (?, ?, ?, 'Active', ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, combo.getCombo_name());
            stmt.setString(2, combo.getPoster_url());
            stmt.setString(3, combo.getDescription());
            stmt.setDouble(4, combo.getPrice());
            stmt.setInt(5, combo.getQuantity()); // Thêm quantity vào DB

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Lỗi khi tạo combo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean toggleComboStatus(int comboID) {
        String sql = "UPDATE Combo SET Status = CASE WHEN Status = 'Active' THEN 'Inactive' ELSE 'Active' END WHERE ComboID = ?";

        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, comboID);
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật trạng thái combo: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public void updateCombo(Combo combo) {
        String sql = "UPDATE Combo SET ComboName = ?, Poster = ?, Description = ?, Cost = ?, Quantity = ?, Status = 'Active' WHERE ComboID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, combo.getCombo_name());
            stmt.setString(2, combo.getPoster_url());
            stmt.setString(3, combo.getDescription());
            stmt.setDouble(4, combo.getPrice());
            stmt.setInt(5, combo.getQuantity()); // Cập nhật quantity
            stmt.setInt(6, combo.getCombo_id()); // ID của combo cần cập nhật

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật combo: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
