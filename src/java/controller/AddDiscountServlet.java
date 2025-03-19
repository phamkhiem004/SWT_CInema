/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DiscountDAO;
import dal.MovieDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;
import model.Discount;
import model.Movie;
import utils.DateTimeUtils;

/**
 *
 * @author Acer Predator
 */
@WebServlet(name = "AddDiscountServlet", urlPatterns = {"/AddDiscountServlet"})
public class AddDiscountServlet extends HttpServlet {

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
            out.println("<title>Servlet AddEventServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddEventServlet at " + request.getContextPath() + "</h1>");
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
        DiscountDAO discountDAO = new DiscountDAO();

        // Sinh mã giảm giá ngẫu nhiên
        String generatedCode = discountDAO.generateUniqueDiscountCode();
        System.out.println("Generated Discount Code: " + generatedCode);
        request.setAttribute("generatedCode", generatedCode);

        // Gửi mã này đến trang JSP
        request.setAttribute("generatedCode", generatedCode);

        // Chuyển hướng đến trang add_discount.jsp
        request.getRequestDispatcher("adddiscount.jsp").forward(request, response);
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
        DiscountDAO discountDAO = new DiscountDAO();

        String name = request.getParameter("DiscountName"); // Thêm DiscountName
        String code = request.getParameter("DiscountCode");
        float percent = Float.parseFloat(request.getParameter("DiscountPercentage"));
        Date expiryDate = DateTimeUtils.stringToDate(request.getParameter("ExpiryDate"));
        String imageURL = request.getParameter("ImageURL");
        String description = request.getParameter("Description");

        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

        // Tạo đối tượng Discount phù hợp với constructor mới
        Discount discount = new Discount(0, name, code, percent, expiryDate, currentTimestamp, currentTimestamp, imageURL, description);

        // Thêm vào DB
        discountDAO.createDiscount(discount);

        // Chuyển hướng về trang danh sách mã giảm giá
        response.sendRedirect("discounts.jsp");
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
