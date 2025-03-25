package controller;

import dal.AccountDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BlockUserServlet", urlPatterns = {"/blockUser"})
public class BlockUserController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String action = req.getParameter("action"); // "block" hoặc "unblock"

        if (id == null || id.isBlank() || action == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid request");
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
            resp.sendRedirect("accounts"); // Quay lại danh sách tài khoản
        } else {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update account status");
        }
    }
}
