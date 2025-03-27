package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import dal.AccountDAO;
import java.util.List;
import model.Movie;

@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account acc = (Account) session.getAttribute("Customer");
        if (acc != null) {
            if (acc.getRole().equalsIgnoreCase("Admin")) {
                resp.sendRedirect("accounts");
            } else {
                resp.sendRedirect("home");
            }
        } else {
            req.getRequestDispatcher("login.jsp").forward(req, resp);
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
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        AccountDAO dao = new AccountDAO();
        Account acc = dao.findByEmailAndPassword(email, password);

        if (acc != null) {
            if (acc.getStatus().equals("Active")) {
                if (acc.getRole().equalsIgnoreCase("Admin")) {
                    session.setAttribute("account", acc);
                    response.sendRedirect("accounts");
                } else if (acc.getRole().equalsIgnoreCase("Staff") || acc.getRole().equalsIgnoreCase("Customer")) {
                    session.setAttribute("account", acc);
                    List<Movie> cart = (List<Movie>) session.getAttribute("cart");
                    if (cart != null) {
                        request.setAttribute("cart", cart);
                    }
                    response.sendRedirect("home");
                } else {
                    // Nếu không phải Admin hoặc Customer
                    request.setAttribute("message", "Access denied");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("message", "User is blocked");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("message", "User or Password incorrect!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Login Controller Servlet";
    }
}
