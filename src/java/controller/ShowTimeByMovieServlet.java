/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CinemaDAO;
import dal.DBContext;
import dal.MovieDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

import model.ShowTime;
import java.sql.Connection;

import model.Cinema;
import model.Movie;

/**
 *
 * @author Acer Predator
 */
@WebServlet(name = "ShowTimeByMovieServlet", urlPatterns = {"/showtime"})
public class ShowTimeByMovieServlet extends HttpServlet {

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
            out.println("<title>Servlet ShowTimeByMovieServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShowTimeByMovieServlet at " + request.getContextPath() + "</h1>");
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
        try {
            int movieId = Integer.parseInt(request.getParameter("id"));

            // Kết nối DB
            DBContext dbContext = new DBContext();
            Connection conn = dbContext.connection;
            CinemaDAO cinemaDAO = new CinemaDAO(conn);
            MovieDAO movieDAO = new MovieDAO(conn);

            // Lấy thông tin phim
            Movie movie = movieDAO.getMoviesByID(movieId);
            if (movie == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy phim!");
                return;
            }
            request.setAttribute("movieTitle", movie.getTitle());

            // Lấy danh sách rạp chiếu phim
            List<Cinema> cinemas = cinemaDAO.getCinemasByMovieId(movieId);
            request.setAttribute("cinemas", cinemas);

            // Lấy danh sách suất chiếu của từng rạp
            for (Cinema cinema : cinemas) {
                List<ShowTime> showTimes = movieDAO.getListShowTime(movieId, cinema.getCinemaID());
                request.setAttribute("showTimes_" + cinema.getCinemaID(), showTimes);
            }

            // Gửi dữ liệu đến trang JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("showtime_list.jsp");
            dispatcher.forward(request, response);

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID phim không hợp lệ!");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi khi lấy dữ liệu lịch chiếu!");
        }
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
