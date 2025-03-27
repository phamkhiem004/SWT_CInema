package controller;

import dal.AccountDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;

@WebServlet(name = "ResetPasswordController", urlPatterns = {"/reset-password"})
public class ResetPasswordController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("userForgetPassword"); // Lấy account từ session
        String newPassword = req.getParameter("newPassword");
        String confirmPassword = req.getParameter("confirmPassword");

        // Kiểm tra mật khẩu có hợp lệ không (ít nhất 8 ký tự, bao gồm chữ và số)
        if (newPassword == null || confirmPassword == null || !newPassword.equals(confirmPassword)) {
            req.setAttribute("message", "Mật khẩu mới và xác nhận mật khẩu không trùng khớp.");
            req.getRequestDispatcher("reset-password.jsp").forward(req, resp);
            return;
        }

        if (!newPassword.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")) {
            req.setAttribute("message", "Mật khẩu phải có ít nhất 8 ký tự, bao gồm cả chữ và số.");
            req.getRequestDispatcher("reset-password.jsp").forward(req, resp);
            return;
        }

        if (account != null) {
            AccountDAO accountDAO = new AccountDAO();
            boolean isUpdated = accountDAO.updatePassword(account.getEmail(), newPassword);

            if (isUpdated) {
                req.setAttribute("message", "Đổi mật khẩu thành công, hãy đăng nhập lại.");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            } else {
                req.setAttribute("message", "Cập nhật mật khẩu thất bại. Vui lòng thử lại.");
                req.getRequestDispatcher("reset-password.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("message", "Phiên làm việc đã hết hạn. Vui lòng thử lại.");
            req.getRequestDispatcher("forgot-password.jsp").forward(req, resp);
        }
    }
}
