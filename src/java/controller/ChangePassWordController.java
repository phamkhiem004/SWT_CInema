package controller;

import dal.AccountDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;

import java.io.IOException;

@WebServlet(name = "ChangePasswordController", urlPatterns = {"/change-password"})
public class ChangePassWordController extends HttpServlet {

    @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    Account account = (Account) session.getAttribute("userForgetPassword"); // Lấy account từ session
    String newPassword = req.getParameter("newPassword");
    String confirmPassword = req.getParameter("confirmPassword");

    if (account != null && newPassword != null && confirmPassword != null && newPassword.equals(confirmPassword)) {
        AccountDAO accountDAO = new AccountDAO();
        boolean isUpdated = accountDAO.updatePassword(account.getEmail(), newPassword);

        if (isUpdated) {
            req.setAttribute("message", "Đổi mật khẩu thành công, hãy đăng nhập lại.");
            req.getRequestDispatcher("login.jsp").forward(req, resp); // Chuyển tiếp tới trang login.jsp
        } else {
            req.setAttribute("message", "Cập nhật mật khẩu thất bại. Vui lòng thử lại.");
            req.getRequestDispatcher("change-password.jsp").forward(req, resp);
        }
    } else {
        req.setAttribute("message", "Mật khẩu mới và xác nhận mật khẩu không trùng khớp.");
        req.getRequestDispatcher("change-password.jsp").forward(req, resp);
    }
}
}