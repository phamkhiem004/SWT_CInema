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
import model.Account;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ConfirmOTPController", urlPatterns = {"/confirmOTP"})
public class ConfirmOTPController extends HttpServlet {

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
            out.println("<title>Servlet ConfirmOTPController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ConfirmOTPController at " + request.getContextPath() + "</h1>");
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
            // Lấy OTP từ session (do người dùng đã nhận từ email)
        String sessionOTP = (String) request.getSession().getAttribute("OTP");
        
        // Kiểm tra nếu OTP không tồn tại trong session, yêu cầu quay lại trang nhập email
        if (sessionOTP == null) {
            response.sendRedirect("forgotpassword");
            return;
        }
        
        // Forward đến trang confirmOTP.jsp để người dùng nhập OTP và mật khẩu mới
        request.getRequestDispatcher("confirm-otp.jsp").forward(request, response);
    
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         // Lấy OTP người dùng nhập từ form
        String enteredOTP = request.getParameter("otp");
        String sessionOTP = (String) request.getSession().getAttribute("OTP");
        
        // Kiểm tra OTP người dùng nhập có đúng không
        if (enteredOTP != null && enteredOTP.equals(sessionOTP)) {
            // OTP đúng, cho phép người dùng thay đổi mật khẩu
            String newPassword = request.getParameter("newPassword");
            Account account = (Account) request.getSession().getAttribute("userForgetPassword");

            // Cập nhật mật khẩu mới trong database
            AccountDAO dbAccount = new AccountDAO();
            dbAccount.updatePassword(account.getEmail(), newPassword);

            // Thông báo thay đổi mật khẩu thành công và chuyển hướng đến trang đăng nhập
            request.setAttribute("message", "Password changed successfully. Please log in.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            // OTP không đúng, yêu cầu người dùng thử lại
            request.setAttribute("error", "Invalid OTP. Please try again.");
            request.getRequestDispatcher("confirm-otp.jsp").forward(request, response);
        }
    }
    

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
