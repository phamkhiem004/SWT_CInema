/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dal.BillingDAO;
import jakarta.servlet.RequestDispatcher;
import java.util.List;
import model.Billing;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "BillingServlet", urlPatterns = {"/billing"})
public class BillingServlet extends HttpServlet {

    private BillingDAO billingDAO;

    public BillingServlet() {
        super();
        billingDAO = new BillingDAO();  // Khởi tạo DAO
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy tất cả các hoá đơn từ BillingDAO
        List<Billing> billingList = billingDAO.getAllBillings();

        // Đặt danh sách hoá đơn vào request attribute
        request.setAttribute("billingList", billingList);

        // Chuyển tiếp đến JSP để hiển thị
        RequestDispatcher dispatcher = request.getRequestDispatcher("billing-list.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search = request.getParameter("search");  // Lấy giá trị từ ô tìm kiếm

        List<Billing> billingList;

        if (search != null && !search.trim().isEmpty()) {
            billingList = billingDAO.getAllBillsByText(search);
        } else {
            billingList = billingDAO.getAllBillings(); // Nếu không nhập gì, lấy toàn bộ danh sách
        }

        request.setAttribute("billingList", billingList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("billing-list.jsp");
        dispatcher.forward(request, response);
    }

}
