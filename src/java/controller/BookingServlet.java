/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.BillingSeatDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Acer Predator
 */
@WebServlet(name = "BookingServlet", urlPatterns = {"/booking"})
public class BookingServlet extends HttpServlet {
   
    private static final long serialVersionUID = 1L;
    private BillingSeatDAO billingSeatDAO = new BillingSeatDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int showtimeID = Integer.parseInt(request.getParameter("showtimeID"));

        // Ghế đã đặt bởi tất cả người dùng
        List<String> bookedSeats = billingSeatDAO.getBookedSeats(showtimeID);

        // Ghế tôi đã đặt trong session
        HttpSession session = request.getSession();
        List<String> myBookedSeats = (List<String>) session.getAttribute("selectedSeats");
        if (myBookedSeats == null) {
            myBookedSeats = new ArrayList<>();
        }

        request.setAttribute("bookedSeats", bookedSeats);
        request.setAttribute("myBookedSeats", myBookedSeats);
        request.setAttribute("showtimeID", showtimeID);

        request.getRequestDispatcher("booking.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int showtimeID = Integer.parseInt(request.getParameter("showtimeID"));
        String[] selectedSeats = request.getParameterValues("seat");

        if (selectedSeats == null || selectedSeats.length == 0) {
            request.setAttribute("error", "Vui lòng chọn ít nhất một ghế.");
            doGet(request, response);
            return;
        }

        // Lưu ghế đã đặt vào session
        HttpSession session = request.getSession();
        List<String> myBookedSeats = (List<String>) session.getAttribute("selectedSeats");
        if (myBookedSeats == null) {
            myBookedSeats = new ArrayList<>();
        }

        // Thêm các ghế mới vào danh sách
        for (String seat : selectedSeats) {
            if (!myBookedSeats.contains(seat)) {
                myBookedSeats.add(seat);
            }
        }

        session.setAttribute("selectedSeats", myBookedSeats);

        // Tính tổng tiền
        int totalAmount = 0;
        for (String seat : selectedSeats) {
            char row = seat.charAt(0);
            if ("ABIK".indexOf(row) >= 0) {
                totalAmount += 50;
            } else if ("CDEF".indexOf(row) >= 0) {
                totalAmount += 100;
            } else if (row == 'G') {
                totalAmount += 150;
            }
        }

        session.setAttribute("totalAmount", totalAmount);
        response.sendRedirect("payment.jsp");
    }

}
