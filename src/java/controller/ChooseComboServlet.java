/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.BillingComboDAO;
import dal.BillingDAO;
import dal.ComboDAO;
import dal.DiscountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;
import model.Account;
import model.Combo;
import model.Discount;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ChooseComboServlet", urlPatterns = {"/choose-combo"})
public class ChooseComboServlet extends HttpServlet {

    private BillingDAO billingDAO = new BillingDAO();
    private BillingComboDAO billingComboDAO = new BillingComboDAO();
    private DiscountDAO discountDAO = new DiscountDAO();
    private ComboDAO comboDAO = new ComboDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            response.sendRedirect("login");
            return;
        }

        String billingID = request.getParameter("billingID");
        if (!billingDAO.isBillingPending(billingID)) {
            request.setAttribute("error", "Không thể chọn combo vì bạn không có đơn hàng nào đang chờ xử lý.");
        } else {
            request.setAttribute("billingID", billingID);
            List<Combo> comboList = comboDAO.getAllCombos();
            request.setAttribute("comboList", comboList);
        }

        request.getRequestDispatcher("chooseCombo.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            response.sendRedirect("login");
            return;
        }

        String billingID = request.getParameter("billingID");

        String[] comboIDs = request.getParameterValues("comboID");
        String[] quantities = request.getParameterValues("quantity");
        String discountCode = request.getParameter("discountCode");
        String paymentMethod = request.getParameter("paymentMethod");

        Discount discount = discountDAO.getDiscountByCode(discountCode);
        if (discount != null || discountCode.isEmpty()) {
            BigDecimal totalAmount = BigDecimal.ZERO;
            if (comboIDs != null && quantities != null) {
                for (int i = 0; i < comboIDs.length; i++) {
                    int comboID = Integer.parseInt(comboIDs[i]);
                    int quantity = Integer.parseInt(quantities[i]);
                    BigDecimal comboPrice = comboDAO.getComboPrice(comboID);
                    totalAmount = totalAmount.add(comboPrice.multiply(BigDecimal.valueOf(quantity)));
                    billingComboDAO.addComboToBilling(billingID, comboID, quantity, comboPrice);

                }
            }        
            billingDAO.updateTotalAmount(billingID, totalAmount);
            billingDAO.updatePaymentMethod(billingID, paymentMethod);
            if (!discountCode.isEmpty()) {
                discountDAO.applyDiscount(billingID, discount.getDiscountID(), discount.getDiscountPercentage());
                request.setAttribute("sucDiscount", "You got " + discount.getDiscountPercentage() + " % discount");
            }
            request.setAttribute("isApproved", true);
        } else {
            request.setAttribute("errDiscount", "Wrong discount code");
        }

        doGet(request, response);

    }

}


