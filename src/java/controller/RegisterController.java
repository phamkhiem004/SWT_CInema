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
    String confirmPassword = request.getParameter("confirmPassword");
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
    
    // Kiểm tra độ dài mật khẩu
    if (password == null || password.length() < 8) {
        request.setAttribute("message", "Mật khẩu phải có ít nhất 8 ký tự.");
        request.getRequestDispatcher("register.jsp").forward(request, response);
        return;
    }

    // Kiểm tra mật khẩu có chứa cả chữ và số
    boolean hasLetter = false;
    boolean hasDigit = false;

    for (char c : password.toCharArray()) {
        if (Character.isLetter(c)) {
            hasLetter = true;
        } else if (Character.isDigit(c)) {
            hasDigit = true;
        }
        
        if (hasLetter && hasDigit) {
            break;
        }
    }

    if (!hasLetter || !hasDigit) {
        request.setAttribute("message", "Mật khẩu phải bao gồm cả chữ và số.");
        request.getRequestDispatcher("register.jsp").forward(request, response);
        return;
    }

    // Kiểm tra mật khẩu xác nhận có trùng khớp không
    if (!password.equals(confirmPassword)) {
        request.setAttribute("message", "Mật khẩu xác nhận không trùng khớp.");
        request.getRequestDispatcher("register.jsp").forward(request, response);
        return;
    }
    
    // Kiểm tra nếu email đã tồn tại trong hệ thống
    AccountDAO accountDAO = new AccountDAO();
    if (accountDAO.isEmailorPhoneNumberExists(email,phoneNumber)) {
        request.setAttribute("message", "Email hoặc số điện thoại đã tồn tại.");
        request.getRequestDispatcher("register.jsp").forward(request, response);
        return;
    }
        
    Account account = new Account(fullname, email, password, phoneNumber, address, gender, dateOfBirth);
    boolean isUserAdded = accountDAO.addAccount(account);
    if (isUserAdded) {
        response.sendRedirect("register-success.jsp"); // Chuyển hướng đến trang thành công
    } else {
        request.setAttribute("message", "Đăng ký không thành công, vui lòng thử lại");
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }
}
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}