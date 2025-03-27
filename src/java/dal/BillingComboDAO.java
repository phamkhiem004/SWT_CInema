/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.math.BigDecimal;
import java.sql.*;


public class BillingComboDAO extends DBContext {

    public void addComboToBilling(String billingID, int comboID, int quantity, BigDecimal price) {
        String sql = "INSERT INTO BillingCombo (BillingID, ComboID, Quantity) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, billingID);
            ps.setInt(2, comboID);
            ps.setInt(3, quantity);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean hasBillingCombo(String billingID) {
        String query = "SELECT COUNT(*) FROM BillingCombo WHERE BillingID = ?";
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
}
