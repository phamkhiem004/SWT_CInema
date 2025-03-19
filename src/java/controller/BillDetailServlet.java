/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import model.Account;
import model.Bill;
import model.Combo;
import model.Seat;

/**
 *
 * @author DUNGVT
 */
@WebServlet(name = "BillDetailServlet", urlPatterns = {"/billdetail"})
public class BillDetailServlet extends HttpServlet {

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
            out.println("<title>Servlet BillDetailServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BillDetailServlet at " + request.getContextPath() + "</h1>");
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
                String bill_id = request.getParameter("bill_id");
        
        int user_id = 0;
        
        HttpSession session = request.getSession();
        Account acc = (Account)session.getAttribute("account");
        if(acc == null){
            response.sendRedirect("login");
        }
        else{
            user_id = acc.getId();
        }       
        
        UserDAO ud = new UserDAO(); 
        
        // Get data for bill details
        Bill bill = ud.getBillByBillidAndUserId(bill_id, user_id);
        String poster_url = ud.getMoviePosterURLByName(bill.getMovie_name());
        Timestamp showtime = ud.getShowtimeByBillid(bill_id);
        String showtime_s = new SimpleDateFormat("yyyy/MM/dd HH:mm").format(showtime);
        String room_name = ud.getRoomNameByBillid(bill_id);
        String cinema_name = ud.getCinemaNameByBillid(bill_id);
        List<Seat> listS = ud.getListSeatByBillid(bill_id);
        List<Combo> listC = ud.getListComboByBillid(bill_id);
        
        double totalprice_c = 0;
        for (Seat s : listS) {
            totalprice_c += s.getSeat_price();
        }
        for (Combo c : listC) {
            totalprice_c += (c.getPrice() * c.getQuantity());
        }
        
        // Pull data to jsp
        request.setAttribute("bill", bill);
        request.setAttribute("poster_url", poster_url);
        request.setAttribute("showtime", showtime_s);
        request.setAttribute("room_name", room_name);
        request.setAttribute("cinema_name", cinema_name);
        request.setAttribute("listS", listS);
        request.setAttribute("listC", listC);
        request.setAttribute("totalprice_c", totalprice_c);
        request.setAttribute("discount_price", totalprice_c * bill.getDiscount()/100);
        request.setAttribute("totalprice_f", totalprice_c - (totalprice_c * bill.getDiscount()/100));
        
        request.getRequestDispatcher("BillDetail.jsp").forward(request, response);
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
        processRequest(request, response);
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
