/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.MovieDAO;
import dal.CinemaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Movie;
import model.Cinema;
import model.ShowTime;

/**
 *
 * @author Acer Predator
 */
@WebServlet(name = "TheaterInfoServlet", urlPatterns = {"/theater"})
public class TheaterInfoServlet extends HttpServlet {

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
            out.println("<title>Servlet TheaterInfoServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TheaterInfoServlet at " + request.getContextPath() + "</h1>");
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
        int cinemaID = Integer.parseInt(request.getParameter("id"));
        MovieDAO movieDAO = new MovieDAO();
        CinemaDAO cinemaDAO = new CinemaDAO();

        try {
            Cinema cinema = cinemaDAO.getCinemaByID(cinemaID);
            List<Movie> movies = cinemaDAO.getMoviesByTheaterID(cinemaID);
            Map<Movie, List<ShowTime>> movieShowtimes = new HashMap<>();
            for (Movie movie : movies) {
                List<ShowTime> showtimes = movieDAO.getListShowTime(movie.getMovieID(),cinema.getCinemaID());
                movieShowtimes.put(movie, showtimes);
            }

            request.setAttribute("Cinema", cinema);
            request.setAttribute("movieShowtimes", movieShowtimes);
            request.getRequestDispatcher("theaterinfo.jsp").forward(request, response);
        } catch (NumberFormatException e) {
        } catch (SQLException ex) {
            Logger.getLogger(TheaterInfoServlet.class.getName()).log(Level.SEVERE, null, ex);
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
