package dal;

import model.Account;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
public class AccountDAO extends DBContext {

    public List<Account> getAllAccount() {
        List<Account> list = new ArrayList<>();
        String sql = "select * from Users";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account account = new Account();
                account.setId(rs.getInt("UserID"));
                account.setFullname(rs.getString("fullname"));
                account.setEmail(rs.getString("email"));
                account.setPhoneNumber(rs.getString("phoneNumber"));
                account.setPassword(rs.getString("password"));
                account.setGender(rs.getString("gender"));
                account.setAddress(rs.getString("address"));
                account.setDateOfBirth(rs.getDate("dateOfBirth"));
                account.setRole(rs.getString("role"));
                account.setStatus(rs.getString("status"));
                account.setCreatedAt(rs.getTimestamp("createdAt"));
                account.setUpdatedAt(rs.getTimestamp("updatedAt"));

                list.add(account);

            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public List<Account> getAllUserByRole(String role) {
        List<Account> list = new ArrayList<>();
        String sql = "select * from Users where Role = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, role);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account account = new Account();
                account.setId(rs.getInt("id"));
                account.setFullname(rs.getString("fullname"));
                account.setEmail(rs.getString("email"));
                account.setPhoneNumber(rs.getString("phoneNumber"));
                account.setPassword(rs.getString("password"));
                account.setGender(rs.getString("gender"));
                account.setAddress(rs.getString("address"));
                account.setDateOfBirth(rs.getDate("dateOfBirth"));
                account.setRole(rs.getString("role"));
                account.setStatus(rs.getString("status"));
                account.setCreatedAt(rs.getTimestamp("createdAt"));
                account.setUpdatedAt(rs.getTimestamp("updatedAt"));

                list.add(account);

            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public Account getUserById(int id) {
    String sql = "SELECT [UserID], [FullName], [Email], [PhoneNumber], [Password], [Gender], [Address], [DateOfBirth], [Role], [Status], [CreatedAt], [UpdatedAt] FROM [dbo].[Users] where UserID = ?";
    try {
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            Account u = new Account();
            u.setId(id);  // Sử dụng UserID thay vì id
            u.setFullname(rs.getString("fullname"));
            u.setEmail(rs.getString("email"));
            u.setPhoneNumber(rs.getString("phoneNumber"));
            u.setPassword(rs.getString("password"));
            u.setGender(rs.getString("gender"));
            u.setAddress(rs.getString("address"));
            u.setDateOfBirth(rs.getDate("dateOfBirth"));
            u.setRole(rs.getString("role"));
            u.setStatus(rs.getString("status"));
            u.setCreatedAt(rs.getTimestamp("createdAt"));
            u.setUpdatedAt(rs.getTimestamp("updatedAt"));

            return u;
        }
    } catch (SQLException e) {
        System.out.println(e);
    }
    return null;
}

     // Phương thức cập nhật mật khẩu mới
    public boolean updatePassword(String email, String newPassword) {
        String sql = "UPDATE Users SET Password = ? WHERE Email = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Mã hóa mật khẩu nếu cần
            // Ví dụ: Mã hóa mật khẩu mới (nếu cần sử dụng hashing)
            // newPassword = hashPassword(newPassword);
            
            stmt.setString(1, newPassword);  // Đặt mật khẩu mới
            stmt.setString(2, email);         // Đặt email người dùng

            int rowsAffected = stmt.executeUpdate();
            
            // Kiểm tra nếu có bản ghi được cập nhật
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    // Lấy tài khoản theo email và mật khẩu
    public Account findByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM [Users] WHERE Email = ? AND Password = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            System.out.println("Executing query: " + sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Account account = new Account();
                account.setId(rs.getInt("UserID"));
                account.setFullname(rs.getString("fullname"));
                account.setPhoneNumber(rs.getString("phoneNumber"));
                account.setGender(rs.getString("gender"));
                account.setRole(rs.getString("role"));
                account.setAddress(rs.getString("address"));
                account.setRole(rs.getString("role"));
                account.setStatus(rs.getString("status"));
                account.setDateOfBirth(rs.getDate("dateOfBirth"));
                account.setCreatedAt(rs.getTimestamp("createdAt"));
                account.setUpdatedAt(rs.getTimestamp("updatedAt"));
                return account;
            }
        } catch (SQLException e) {
             System.out.println("SQL Error: " + e.getMessage());
        }

        return null;
    }
        public Account findByUsernameOrEmail(String email) {
    Account account = null;
    String sql = "SELECT * FROM Users WHERE Email = ?";
    try (Connection conn = getConnection(); 
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            account = new Account();
            account.setId(rs.getInt("UserID"));
            account.setFullname(rs.getString("FullName"));
            account.setEmail(rs.getString("Email"));
            account.setPhoneNumber(rs.getString("PhoneNumber"));
            account.setPassword(rs.getString("Password"));
            account.setGender(rs.getString("Gender"));
            account.setAddress(rs.getString("Address"));
            account.setDateOfBirth(rs.getDate("DateOfBirth"));
            account.setRole(rs.getString("Role"));
            account.setStatus(rs.getString("Status"));
            account.setCreatedAt(rs.getTimestamp("CreatedAt"));
            account.setUpdatedAt(rs.getTimestamp("UpdatedAt"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return account;
}


    public Account getAccountByUserID(int id) {
        Account account = new Account();
        String query = "SELECT * FROM Users WHERE UserID = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    String fullname = rs.getString("fullname");
                    String email = rs.getString("email");
                    String phoneNumber = rs.getString("phoneNumber");
                    String password = rs.getString("password");
                    String gender = rs.getString("gender");
                    Timestamp address = rs.getTimestamp("address");
                    int dateOfBirth = rs.getInt("dateOfBirth");
                    int role = rs.getInt("role");
                    String status = rs.getString("status");
                    Timestamp createdAt = rs.getTimestamp("createdAt");
                    Timestamp updatedAt = rs.getTimestamp("updatedAt");
                    account = new Account(id, fullname, email, phoneNumber, password, gender, address, dateOfBirth, role, status, createdAt, updatedAt);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return account;
    }

     // Cập nhật tài khoản theo ID (cập nhật thông tin người dùng)
public void updateAccount(Account user) {
        String sql = "UPDATE [dbo].[Users]\n"
                + "   SET [FullName] = ?\n"
                + "      ,[Email] = ?\n"
                + "      ,[PhoneNumber] = ?\n"
                + "      ,[Password] = ?\n"
                + "      ,[Gender] = ?\n"
                + "      ,[Address] = ?\n"
                + "      ,[DateOfBirth] = ?\n"
                + "      ,[Role] = ?\n"
                + "      ,[Status] = ?\n"
                + "      ,[CreatedAt] = ?\n"
                + "      ,[UpdatedAt] = ?\n"
                + " WHERE [UserID] = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getFullname());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPhoneNumber());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getGender());
            ps.setString(6, user.getAddress());
            ps.setDate(7, (Date) user.getDateOfBirth());
            ps.setString(8, user.getRole());
            ps.setString(9, user.getStatus());
            ps.setTimestamp(10, (Timestamp)user.getCreatedAt());
            ps.setTimestamp(11, (Timestamp)user.getUpdatedAt());
            ps.setInt(12, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }
    // Kiểm tra sự tồn tại của người dùng theo email
    public boolean isUserAdded(String email) {
        String query = "SELECT COUNT(*) FROM Users WHERE Email = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean isEmailorPhoneNumberExists(String email, String phoneNumber) {
    boolean exists = false;
    String query = "SELECT COUNT(*) FROM Users WHERE Email = ? and PhoneNumber = ?";
    try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, email);
        statement.setString(2, phoneNumber);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            exists = rs.getInt(1) > 0;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return exists;
}
    public List<Account> getAccountToManage(int page, int pageSize) {
        List<Account> accounts = new ArrayList<>();
        int offset = (page - 1) * pageSize;
        try (
                PreparedStatement stmt = connection.prepareStatement("SELECT *\n"
                        + "FROM Users\n"
                        + "ORDER BY UserID\n"
                        + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;")) {
            stmt.setInt(1, offset);
            stmt.setInt(2, pageSize);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Account account = new Account();
                    account.setId(rs.getInt("id"));
                    account.setEmail(rs.getString("email"));
                    account.setPassword(rs.getString("password"));
                    account.setFullname(rs.getString("fullname"));
                    account.setCreatedAt(rs.getTimestamp("created_at"));
                    account.setStatus(rs.getString("status"));
                    account.setRole(rs.getString("role"));
                    accounts.add(account);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    public void updateAccountStatus(int accountId) {
        try (PreparedStatement stmt = connection.prepareStatement("UPDATE Users SET status = (CASE WHEN status = 1 THEN 0 ELSE 1 END) WHERE id = ?")) {
            stmt.setInt(1, accountId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Thêm tài khoản mới vào cơ sở dữ liệu

  public boolean addAccount(Account account) {
    boolean success = false;
    String query = "INSERT INTO Users (FullName, Email, PhoneNumber, Password, Address, Gender, DateOfBirth, Role, Status, CreatedAt, UpdatedAt) " +
                   "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; // Thêm Role, Status, CreatedAt, UpdatedAt

    java.sql.Date sqlDate = null;
    if (account.getDateOfBirth() != null) {
        sqlDate = new java.sql.Date(account.getDateOfBirth().getTime());
    }

    Timestamp currentTime = new Timestamp(System.currentTimeMillis()); // Lấy thời gian hiện tại

    try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, account.getFullname());
        statement.setString(2, account.getEmail());
        statement.setString(3, account.getPhoneNumber());
        statement.setString(4, account.getPassword());
        statement.setString(5, account.getAddress());
        statement.setString(6, account.getGender());

        if (sqlDate != null) {
            statement.setDate(7, sqlDate);
        } else {
            statement.setNull(7, java.sql.Types.DATE);
        }

        // Các trường bổ sung
        statement.setString(8, "Customer"); // Role mặc định là "User"
        statement.setString(9, "Active"); // Status mặc định là "Active"
        statement.setTimestamp(10, currentTime); // Thời gian tạo tài khoản
        statement.setTimestamp(11, currentTime); // Cập nhật thời gian

        int rowsAffected = statement.executeUpdate();
        if (rowsAffected > 0) {
            success = true;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return success;
}

    public static void main(String[] args) {
              AccountDAO dao = new AccountDAO();
        System.out.println(dao.getUserById(1));
}
}
