package controller;

import dal.AccountDAO;
import dal.EmailHelper;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.regex.Pattern;
import model.Account;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ForgotPasswordController", urlPatterns = {"/forgotpassword"})
public class ForgotPasswordController extends HttpServlet {

    // Pattern để kiểm tra định dạng email
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE
    );

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("forgot-password.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("Email");
        AccountDAO dbAccount = new AccountDAO();
        Account account = dbAccount.findByUsernameOrEmail(email);

        if (account == null) {
            req.setAttribute("message", "Tài khoản không tồn tại");
            req.getRequestDispatcher("forgot-password.jsp").forward(req, resp);
        } else {
            String OTPCode = EmailHelper.generateOTP();
            String bodyEmailOTP = "Mã OTP của bạn là: " + OTPCode;
            req.getSession().setAttribute("OTP", OTPCode);
            req.getSession().setAttribute("userForgetPassword", account);
            req.getSession().setAttribute("op", "change-password");
            EmailHelper.sendEmail(account.getEmail(), EmailHelper.TITLE_PROJECT, bodyEmailOTP);
            resp.sendRedirect("confirmOTP");
        }
    }

    // Kiểm tra định dạng email
    private boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).find();
    }
}
