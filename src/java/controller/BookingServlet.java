/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.BillingDAO;
import dal.BillingSeatDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import model.Account;

/**
 *
 * @author Acer Predator
 */
@WebServlet(name = "BookingServlet", urlPatterns = {"/booking"})
public class BookingServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private BillingSeatDAO billingSeatDAO = new BillingSeatDAO();
    private BillingDAO billingDAO = new BillingDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            response.sendRedirect("login");
            return;
        }
        int showtimeID = Integer.parseInt(request.getParameter("showtimeID"));
        request.setAttribute("showtimeID", showtimeID);

        List<String> myBookedSeats = billingSeatDAO.getBookedSeatsByUser(showtimeID, account.getId());
        // Ghế đã đặt bởi tất cả người dùng
        List<String> bookedSeats = billingSeatDAO.getBookedSeats(showtimeID);
        request.setAttribute("myBookedSeats", myBookedSeats);
        request.setAttribute("bookedSeats", bookedSeats);
        request.setAttribute("showtimeID", showtimeID);
        request.setAttribute("isExistPending", billingDAO.hasPendingBilling(account.getId(), showtimeID));

        request.getRequestDispatcher("booking.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            response.sendRedirect("login");
            return;
        }

        int showtimeID = Integer.parseInt(request.getParameter("showtimeID"));
        String[] selectedSeats = request.getParameterValues("seat");

        if (selectedSeats == null || selectedSeats.length == 0) {
            request.setAttribute("error", "Vui lòng chọn ít nhất một ghế.");
            doGet(request, response);
            return;
        }

        BigDecimal totalAmount = new BigDecimal(0);
        for (String seat : selectedSeats) {
            char row = seat.charAt(0);
            if ("ABIK".indexOf(row) >= 0) {
                totalAmount = totalAmount.add(new BigDecimal(50));
            } else if ("CDEFG".indexOf(row) >= 0) {
                totalAmount = totalAmount.add(new BigDecimal(100));
            } else if (row == 'H') {
                totalAmount = totalAmount.add(new BigDecimal(150));
            }
        }

        // Lưu vào database
        String billingID = billingDAO.createBilling(account.getId(), showtimeID, totalAmount, null, null);
        if (billingID != null) {
            for (String seat : selectedSeats) {
                char row = seat.charAt(0);
                if ("ABIK".indexOf(row) >= 0) {
                    billingDAO.addBillingSeat(billingID, 1, seat);
                } else if ("CDEFG".indexOf(row) >= 0) {
                    billingDAO.addBillingSeat(billingID, 2, seat);
                } else if (row == 'H') {
                    billingDAO.addBillingSeat(billingID, 3, seat);
                }

            }
        }

        response.sendRedirect("choose-combo?billingID=" + billingID);
    }

}
