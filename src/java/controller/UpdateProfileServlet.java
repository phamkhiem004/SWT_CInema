/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.List;
import model.Account;
import model.Bill;

/**
 *
 * @author DUNGVT
 */
@WebServlet(name = "UpdateProfileServlet", urlPatterns = {"/updateprofile"})
public class UpdateProfileServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateProfileServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateProfileServlet at " + request.getContextPath() + "</h1>");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        int id_user = Integer.parseInt(request.getParameter("id"));
        AccountDAO ad = new AccountDAO();
        UserDAO ud = new UserDAO();
        Account user = ad.getUserById(id_user);

        // Chỉnh sửa thông tin người dùng
        if (action.equals("profile")) {

            // Lấy dữ liệu cần cập nhật
            String fname = request.getParameter("fname").trim();
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String address = request.getParameter("address").trim();
            String gender = request.getParameter("gender");
            Date dob = Date.valueOf(request.getParameter("dob"));

            // Thực hiện hành động cập nhật thông tin
            ad.updateAccount(new Account(id_user, fname, email,
                    phone, user.getPassword(),
                    gender, address, dob, user.getRole(), user.getStatus(), user.getCreatedAt(), user.getUpdatedAt()));

            // Gửi lại thông báo và dữ liệu mới được cập nhật
            request.setAttribute("alert_true_info", "Cập nhật thông tin cá nhân thành công!");
            request.setAttribute("u", ad.getUserById(id_user));

            // Gửi lại lịch sử giao dịch
            List<Bill> listBill = ud.getListBillByUserID("", "", id_user);
            request.setAttribute("listB", listBill);
            request.getRequestDispatcher("UserProfile.jsp").forward(request, response);
        } // Thay đổi mật khẩu người dùng
        else if (action.equals("pass")) {
            String cpass = request.getParameter("cpass");

            // Đối chiếu với mật khẩu cũ
            if (cpass.equals(user.getPassword())) {
                String npass = request.getParameter("npass");

                // Tham chiếu mật khẩu mới có khác mật khẩu cũ hay không
                if (npass.equals(cpass)) {
                    request.setAttribute("alert_false_npass",
                            "Mật khẩu mới không được giống mật khẩu cũ! Vui lòng thử lại!");
                } else {
                    // Thực hiện hành đông cập nhật mật khẩu
                    ad.updateAccount(new Account(id_user, user.getFullname(), user.getEmail(),
                            user.getPhoneNumber(), npass, user.getGender(), user.getAddress(),
                            (Date) user.getDateOfBirth(), user.getRole(), user.getStatus(), user.getCreatedAt(),
                            user.getUpdatedAt()));
                    request.setAttribute("alert_true", "Mật khẩu thay đổi thành công!");
                }
            } else {
                request.setAttribute("alert_false_cpass", "Mật khẩu cũ không chính xác! Vui lòng thử lại!");
            }

            // Gửi lại dữ liệu người dùng
            request.setAttribute("u", user);

            // Gửi lại thông tin mật khẩu đã nhập
            request.setAttribute("cpass", request.getParameter("cpass"));
            request.setAttribute("npass", request.getParameter("npass"));
            request.setAttribute("cnpass", request.getParameter("cnpass"));

            // Gửi lại lịch sử giao dịch
            List<Bill> listBill = ud.getListBillByUserID("", "", id_user);
            request.setAttribute("listB", listBill);
            request.getRequestDispatcher("UserProfile.jsp").forward(request, response);
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
