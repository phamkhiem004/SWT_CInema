<%-- 
    Document   : eventinfo
    Created on : Feb 19, 2025, 2:12:53 PM
    Author     : Acer Predator
--%>
<%@page import="dal.AccountDAO"%>
<%@page import="model.Account"%>
<%@ page import="model.Discount" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thông tin mã khuyến mãi</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f8f5eb;
                margin: 0;
                padding: 0;
                text-align: center;
            }

            .container {
                width: 60%;
                margin: auto;
                background-color: #fff;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            }

            .discount-banner {
                width: 100%;
                border-radius: 10px;
            }

            .discount-title {
                font-size: 24px;
                font-weight: bold;
                color: #d62020;
                margin-top: 20px;
            }

            .discount-description {
                font-size: 16px;
                color: #333;
                text-align: left;
                margin-top: 10px;
            }
            .btn {
                padding: 10px 15px;
                background-color: #007bff;
                color: white;
                text-decoration: none;
                border-radius: 5px;
                font-size: 0.9em;
                transition: background-color 0.3s ease;
                display: inline-block;
                margin-top: 5px;
                margin-bottom: 5px;
            }

            .btn:hover {
                background-color: #0056b3;
            }
            .button-container {
                text-align: center;
                margin-top: 20px;
            }

            .back-link {
                padding: 10px 15px;
                background-color: #007bff;
                color: white;
                text-decoration: none;
                border-radius: 5px;
                font-size: 0.9em;
                transition: background-color 0.3s ease;
            }

            .back-link:hover {
                background-color: #0056b3;
            }

            .error {
                color: red;
                font-weight: bold;
                text-align: center;
                margin: 20px;
            }


        </style>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <%
            Discount discount = (Discount) request.getAttribute("discounts");
            if (discount != null) {
        %>
        <%
            Account account = (Account) session.getAttribute("account");
        %>
        <% if (account != null && ("Admin".equals(account.getRole()) || "Staff".equals(account.getRole()))) { %>
        <a href="StatusDiscountServlet?id=<%= discount.getDiscountID() %>" 
           class="btn"
           style="background-color: <%= discount.getStatus().equals("Active") ? "red" : "green" %>;"
           onclick="return confirm('Bạn có chắc chắn muốn <%= discount.getStatus().equals("Active") ? "vô hiệu hóa" : "kích hoạt" %> mã giảm giá này?');">
            <%= discount.getStatus().equals("Active") ? "❌ Vô hiệu hóa" : "✅ Kích hoạt" %>
        </a>
        <% } %>
        <div class="container">
            <h2 class="discount-title"><%= discount.getDiscountName() %></h2>
            <p><strong>Mã giảm giá:</strong> <%= discount.getDiscountCode() %></p>
            <p><strong>Ngày hết hạn:</strong> <%= discount.getExpiryDate() %></p>

            <img src="image/<%= discount.getImageURL() %>" alt="Discount Banner" class="discount-banner">

            <p class="discount-description">
                <%= discount.getDescription().replace("\n", "<br>") %>
            </p>
        </div>
        <%
            } else {
        %>
        <p class="error">Không có thông tin mã giảm giá.</p>
        <%
                }
            
        %>
        <div class="button-container">
            <a href="discounts.jsp" class="back-link">Quay về khuyến mãi</a>
        </div>
        <jsp:include page="footer.jsp" />
    </body>
</html>
