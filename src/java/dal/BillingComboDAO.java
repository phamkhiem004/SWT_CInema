/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import java.math.BigDecimal;
import java.sql.*;
/**
 *
 * @author SHD
 */
public class BillingComboDAO extends DBContext{
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
    
}
