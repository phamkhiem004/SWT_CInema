/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ResetPasswordController", urlPatterns = {"/reset-password"})
public class ResetPasswordController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ResetPasswordController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ResetPasswordController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
            req.getRequestDispatcher("reset-password.jsp").forward(req, resp);
        }
    } else {
        req.setAttribute("message", "Mật khẩu mới và xác nhận mật khẩu không trùng khớp.");
        req.getRequestDispatcher("reset-password.jsp").forward(req, resp);
    }
}
}
