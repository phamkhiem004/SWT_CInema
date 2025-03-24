/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.BillingDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;
import model.Billing;
import model.BillingCombo;
import utils.EmailSender;

/**
 *
 * @author SHD
 */
public class CompletePaymentServlet extends HttpServlet {

    private BillingDAO billingDAO = new BillingDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String billingID = request.getParameter("billingID");

        if (billingID == null || billingID.isEmpty()) {
            response.getWriter().write("Billing ID is required.");
            return;
        }

        // Cập nhật trạng thái thanh toán
        billingDAO.updatePaymentStatus(billingID);

        // Lấy thông tin thanh toán
        Billing billing = billingDAO.getBillingByID(billingID);
        if (billing == null) {
            response.getWriter().write("Billing not found.");
            return;
        }

        // Lấy danh sách ghế
        List<String> seatNames = billingDAO.getSeatsByBillingID(billingID);

        // Lấy danh sách combo
        List<BillingCombo> combos = billingDAO.getCombosByBillingID(billingID);

        // Lấy thông tin phòng và phim
        int roomID = billingDAO.getRoomIDByShowtimeID(billing.getShowtimeID());
        String movieName = billingDAO.getMovieNameByShowtimeID(billing.getShowtimeID());

        // Tổng tiền
        BigDecimal totalAmount = billing.getTotalAmount();

        // Gửi email
        String userEmail = billingDAO.getEmailByUserID(billing.getUserID()); // Cần lấy email từ UserDAO
        sendPaymentEmail(userEmail, seatNames, combos, totalAmount, roomID, movieName);

        response.getWriter().write("Payment completed successfully and email sent.");
    }

    private void sendPaymentEmail(String toEmail, List<String> seatNames, List<BillingCombo> combos, BigDecimal totalAmount, int roomID, String movieName) {
        String subject = "Payment Confirmation - Movie Ticket Booking";
        StringBuilder content = new StringBuilder();

        content.append("<h3>Payment Confirmation</h3>")
                .append("<p>Your payment has been successfully completed.</p>")
                .append("<p><strong>Movie:</strong> ").append(movieName).append("</p>")
                .append("<p><strong>Room:</strong> ").append(roomID).append("</p>")
                .append("<p><strong>Total Amount:</strong> $").append(totalAmount).append("</p>")
                .append("<h4>Seats:</h4><ul>");

        for (String seat : seatNames) {
            content.append("<li>").append(seat).append("</li>");
        }
        content.append("</ul>");

        content.append("<h4>Combos:</h4><ul>");
        for (BillingCombo combo : combos) {
            content.append("<li>Combo ID: ").append(combo.getComboID()).append(" - Quantity: ").append(combo.getQuantity()).append("</li>");
        }
        content.append("</ul>");

        EmailSender.sendEmail(toEmail, subject, content.toString());
    }

}
