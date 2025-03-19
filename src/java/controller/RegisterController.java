package controller;

import dal.AccountDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.Account;

@WebServlet(name = "RegisterController", urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        String gender = request.getParameter("gender");
        String dateOfBirthString = request.getParameter("dateOfBirth");

        Date dateOfBirth = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            if (dateOfBirthString != null && !dateOfBirthString.isEmpty()) {
                dateOfBirth = dateFormat.parse(dateOfBirthString);
            }
        } catch (Exception e) {
            e.printStackTrace(); // In lỗi ra console để debug
            request.setAttribute("message", "Định dạng ngày không hợp lệ."); // Gửi thông báo lỗi về trang register.jsp
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return; // Dừng xử lý nếu có lỗi ngày tháng
        }

        Account account = new Account(fullname, email, password, phoneNumber, address, gender, dateOfBirth);

        AccountDAO accountDAO = new AccountDAO();

        boolean isUserAdded = accountDAO.addAccount(account);

        if (isUserAdded) {
            response.sendRedirect("register-success.jsp"); // Chuyển hướng đến trang thành công
        } else {
            request.setAttribute("message", "Đăng ký không thành công. Xin vui lòng thử lại. "); // Thông báo lỗi chi tiết hơn
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}