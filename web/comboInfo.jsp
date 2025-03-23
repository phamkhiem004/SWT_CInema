<%-- 
    Document   : comboInfo
    Created on : Mar 23, 2025, 3:24:33 PM
    Author     : Acer Predator
--%>

<%@page import="dal.AccountDAO"%>
<%@page import="model.Account"%>
<%@ page import="model.Combo" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thông tin Combo</title>
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

            .combo-banner {
                width: 100%;
                border-radius: 10px;
            }

            .combo-title {
                font-size: 24px;
                font-weight: bold;
                color: #d62020;
                margin-top: 20px;
            }

            .combo-description {
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
            Combo combo = (Combo) request.getAttribute("combos");
            if (combo != null) {
        %>
        <%
            Account account = (Account) session.getAttribute("account");
        %>
        <% if (account != null && ("Admin".equals(account.getRole()) || "Staff".equals(account.getRole()))) { %>
        <a href="StatusComboServlet?id=<%= combo.getCombo_id() %>" 
           class="btn"
           style="background-color: <%= combo.getStatus().equals("Active") ? "red" : "green" %>;"
           onclick="return confirm('Bạn có chắc chắn muốn <%= combo.getStatus().equals("Active") ? "vô hiệu hóa" : "kích hoạt" %> combo này?');">
            <%= combo.getStatus().equals("Active") ? "❌ Vô hiệu hóa" : "✅ Kích hoạt" %>
        </a>
        <% } %>
        <div class="container">
            <h2 class="combo-title"><%= combo.getCombo_name() %></h2>
            <p><strong>Giá:</strong> <%= combo.getPrice() %> VND</p>
            <p><strong>Số lượng:</strong> <%= combo.getQuantity() %></p> <!-- Thêm dòng này -->
            <img src="image/<%= combo.getPoster_url() %>" alt="Combo Banner" class="combo-banner">

            <p class="combo-description">
                <%= combo.getDescription().replace("\n", "<br>") %>
            </p>
        </div>
        <%
            } else {
        %>
        <p class="error">Không có thông tin combo.</p>
        <%
                }
        %>
        <div class="button-container">
            <a href="combos.jsp" class="back-link">Quay về danh sách Combo</a>
        </div>
        <jsp:include page="footer.jsp" />
    </body>
</html>

