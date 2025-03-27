package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;
import java.io.IOException;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "VerifyOTPController", urlPatterns = {"/verifyotp"})
public class VerifyOTPController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Chuyển hướng đến trang nhập OTP nếu có yêu cầu GET
        req.getRequestDispatcher("confirm-otp.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy mã OTP người dùng nhập
        String userOTP = req.getParameter("OTP");
        // Lấy mã OTP lưu trong session
        String sessionOTP = (String) req.getSession().getAttribute("OTP");
        
        // Kiểm tra mã OTP người dùng nhập có khớp với mã OTP trong session
        if (userOTP != null && userOTP.equals(sessionOTP)) {
            // Nếu OTP chính xác, chuyển hướng đến trang thay đổi mật khẩu
            Account account = (Account) req.getSession().getAttribute("userForgetPassword");
            req.setAttribute("account", account);
            req.getRequestDispatcher("reset-password.jsp").forward(req, resp);
        } else {
            // Nếu OTP sai, hiển thị thông báo lỗi
            req.setAttribute("message", "Mã OTP không chính xác. Vui lòng thử lại.");
            req.getRequestDispatcher("confirm-otp.jsp").forward(req, resp);
        }
    }
}
