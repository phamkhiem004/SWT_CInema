package controller;

import dal.AccountDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.Account;

@WebServlet(name = "AccountController", urlPatterns = {"/accounts"})
public class AccountController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String search = req.getParameter("search");
        if (search == null || search.isBlank()) {
            search = "";
        }
        AccountDAO dbAccount = new AccountDAO();
        List<Account> accounts = dbAccount.getAllAccountByText(search);

        req.setAttribute("listA", accounts);
        req.getRequestDispatcher("Account.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String id = req.getParameter("id");

        if (id == null || id.isBlank()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid account ID");
            return;
        }

        AccountDAO accountDAO = new AccountDAO();

        boolean success = false;
        if ("block".equals(action)) {
            success = accountDAO.blockAccount(id);
        } else if ("unblock".equals(action)) {
            success = accountDAO.unblockAccount(id);
        }

        if (success) {
            resp.sendRedirect("accounts"); // Reload danh sách
        } else {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update account status");
        }
    }
}
