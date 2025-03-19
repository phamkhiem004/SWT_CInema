/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Bill;
import java.sql.Timestamp;
import model.Combo;
import model.Seat;

/**
 *
 * @author DUNGVT
 */
public class UserDAO {

    DBContext db = new DBContext();
    protected Connection connection = db.connection;

    public static void main(String[] args) {
        UserDAO u = new UserDAO();

        System.out.println(u.getDiscountPercentageByBillid("BILL001"));
        System.out.println(u.getMovieNameByShowtimeId(1));
        for (Bill arg : u.getListBillByUserID("", "", 9)) {
            System.out.println(arg);
        }
        System.out.println(u.getBillByBillidAndUserId("BILL001", 1));
        System.out.println(u.getMoviePosterURLByName("Inception"));
        System.out.println(u.getShowtimeByBillid("BILL001"));
        System.out.println(u.getRoomNameByBillid("BILL001"));
        System.out.println(u.getCinemaNameByBillid("BILL001"));
        for (Seat arg : u.getListSeatByBillid("BILL001")) {
            System.out.println(arg);
        }
        for (Combo arg : u.getListComboByBillid("BILL001")) {
            System.out.println(arg);
        }
    }

    // Get list bill by search time
    public List<Bill> getListBillByUserID(String start_date, String end_date, int id_user) {
        List<Bill> list = new ArrayList<>();
        String sql = "SELECT [BillingID]\n"
                + "      ,[ShowID]\n"
                + "      ,[TotalAmount]\n"
                + "      ,[PaymentMethod]\n"
                + "      ,[PaymentStatus]\n"
                + "      ,[BookingDate]\n"
                + "  FROM [SWP_MovieManagement].[dbo].[Billing] WHERE [UserID] = ?";
        if (start_date != "" && end_date == "") {
            sql += " AND [BookingDate] >= ? ";
        } else if (start_date == "" && end_date != "") {
            sql += " AND [BookingDate] <= ? ";
        } else if (start_date != "" && end_date != "") {
            sql += " AND [BookingDate] between ? and ? ";
        }
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id_user);
            if (start_date != "" && end_date == "") {
                ps.setString(2, start_date);
            } else if (start_date == "" && end_date != "") {
                ps.setString(2, end_date);
            } else if (start_date != "" && end_date != "") {
                ps.setString(2, start_date);
                ps.setString(3, end_date);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Bill(rs.getString(1), getMovieNameByShowtimeId(rs.getInt(2)),
                        getDiscountPercentageByBillid(rs.getString(1)), rs.getDate(6),
                        rs.getDouble(3), rs.getString(4), rs.getString(5)));
            }
        } catch (SQLException e) {
        }
        return list;
    }

    // Get discount percentage by bill id
    public double getDiscountPercentageByBillid(String Bill_id) {
        String sql = "SELECT [DiscountPercentage]\n"
                + "FROM [SWP_MovieManagement].[dbo].[Discounts] INNER JOIN [SWP_MovieManagement].[dbo].[Billing]\n"
                + "ON [Discounts].DiscountID = [Billing].DiscountID WHERE [Billing].BillingID = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, Bill_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
        }
        return 0;
    }

    // Get movie name by showtime id
    public String getMovieNameByShowtimeId(int showtime_id) {
        String sql = "SELECT [Title]\n"
                + "  FROM [SWP_MovieManagement].[dbo].[Movies] INNER JOIN [SWP_MovieManagement].[dbo].[Shows]\n"
                + "  ON [Movies].MovieID = [ShowID].MovieID WHERE [Shows].ShowID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, showtime_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
        }
        return null;
    }

    //Get bill by bill id and user id
    public Bill getBillByBillidAndUserId(String bill_id, int user_id) {
        String sql = "SELECT [BillingID]\n"
                + "      ,[ShowID]\n"
                + "      ,[TotalAmount]\n"
                + "      ,[PaymentMethod]\n"
                + "      ,[PaymentStatus]\n"
                + "      ,[BookingDate]\n"
                + "  FROM [SWP_MovieManagement].[dbo].[Billing] WHERE [UserID] = ? AND [BillingID] = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, user_id);
            ps.setString(2, bill_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Bill(rs.getString(1), getMovieNameByShowtimeId(rs.getInt(2)),
                        getDiscountPercentageByBillid(rs.getString(1)), rs.getDate(6),
                        rs.getDouble(3), rs.getString(4), rs.getString(5));
            }
        } catch (SQLException e) {
        }
        return null;
    }

    // Get movie poster url by name
    public String getMoviePosterURLByName(String movie_name) {
        String sql = "SELECT [ImageURL]\n"
                + "  FROM [SWP_MovieManagement].[dbo].[Movies]\n"
                + "  WHERE [Title] = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, movie_name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
        }
        return null;
    }

    //Get showtime by bill id
    public Timestamp getShowtimeByBillid(String bill_id) {
        String sql = "SELECT [ShowTime]\n"
                + "  FROM [SWP_MovieManagement].[dbo].[Shows] INNER JOIN [SWP_MovieManagement].[dbo].[Billing]\n"
                + "  ON [Shows].ShowID = [Billing].ShowID WHERE [Billing].BillingID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, bill_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getTimestamp(1);
            }
        } catch (SQLException e) {
        }
        return null;
    }

    //Get room name by bill id
    public String getRoomNameByBillid(String bill_id) {
        String sql = "SELECT [Name]\n"
                + "  FROM [SWP_MovieManagement].[dbo].[Room] INNER JOIN [SWP_MovieManagement].[dbo].[Shows]\n"
                + "  ON [Room].RoomID = [Shows].RoomID WHERE [Shows].ShowID = \n"
                + "  (SELECT [ShowID] FROM [SWP_MovieManagement].[dbo].[Billing] WHERE BillingID = ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, bill_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
        }
        return null;
    }

    //Get cinema name by bill id
    public String getCinemaNameByBillid(String bill_id) {
        String sql = "SELECT [Theaters].[Name] FROM [SWP_MovieManagement].[dbo].[Room] INNER JOIN [SWP_MovieManagement].[dbo].[Theaters]\n"
                + "ON [Room].TheaterID = [Theaters].TheaterID WHERE [Room].RoomID = \n"
                + "(SELECT [Room].RoomID FROM [SWP_MovieManagement].[dbo].[Room] INNER JOIN [SWP_MovieManagement].[dbo].[Shows]\n"
                + "ON [Room].RoomID = [Shows].RoomID WHERE [Shows].ShowID = \n"
                + "(SELECT [ShowID] FROM [SWP_MovieManagement].[dbo].[Billing] WHERE BillingID = ?))";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, bill_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
        }
        return null;
    }

    // Get list seat by bill id
    public List<Seat> getListSeatByBillid(String bill_id) {
        List<Seat> list = new ArrayList<>();
        String sql = "SELECT [Billing_Seat].SeatName, [Seat].SeatType,[Seat].Price\n"
                + "  FROM [SWP_MovieManagement].[dbo].[Billing_Seat] INNER JOIN [SWP_MovieManagement].[dbo].[Seat]\n"
                + "  ON [Billing_Seat].SeatID = [Seat].SeatID WHERE [Billing_Seat].BillingID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, bill_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Seat(rs.getString(1), rs.getString(2), rs.getDouble(3)));
            }
        } catch (SQLException e) {
        }
        return list;
    }

    //Get list combo by bill id
    public List<Combo> getListComboByBillid(String bill_id) {
        List<Combo> list = new ArrayList<>();
        String sql = "SELECT [Combo].[ComboID],[Combo].Cost\n"
                + " ,[Combo].ComboName,[Combo].Description,[Combo].Status\n"
                + "  ,[Combo].[Poster],[BillingCombo].[Quantity]\n"
                + "  FROM [SWP_MovieManagement].[dbo].[BillingCombo] INNER JOIN [SWP_MovieManagement].[dbo].[Combo]\n"
                + "  ON [BillingCombo].ComboID = [Combo].ComboID WHERE [BillingCombo].BillingID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, bill_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Combo(rs.getInt(1), rs.getDouble(2), rs.getString(3), 
                        rs.getString(4), rs.getString(5), rs.getInt(7), rs.getString(6)));
            }
        } catch (SQLException e) {
        }
        return list;
    }
}
