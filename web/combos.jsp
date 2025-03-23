<%-- 
    Document   : combos
    Created on : Mar 23, 2025, 2:52:45 PM
    Author     : Acer Predator
--%>

<%@page import="dal.ComboDAO"%>
<%@page import="model.Combo"%>
<%@page import="model.Account"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Danh Sách Combo</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f8f8f8;
            }
            .combo-list {
                display: flex;
                flex-direction: column;
                align-items: center;
                padding: 20px;
            }
            .combo-row {
                display: grid;
                grid-template-columns: repeat(4, 1fr);
                gap: 20px;
                width: 100%;
                max-width: 1200px;
                margin-bottom: 20px;
            }
            .combo {
                background-color: #fff;
                border-radius: 10px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
                text-align: center;
                padding: 5px;
                position: relative;
                overflow: hidden;
                transition: transform 0.3s ease-in-out;
            }
            .combo:hover {
                transform: scale(1.05);
            }
            .combo img {
                width: 100%;
                height: 300px;
                object-fit: cover;
                border-radius: 10px;
            }
            .combo .overlay {
                position: absolute;
                top: 0;
                left: 0;
                right: 0;
                bottom: 0;
                background: rgba(0, 0, 0, 0.5);
                display: flex;
                justify-content: center;
                align-items: center;
                opacity: 0;
                transition: opacity 0.3s ease;
            }
            .combo:hover .overlay {
                opacity: 1;
            }
            .btn {
                padding: 8px 12px;
                background-color: #007bff;
                color: white;
                text-decoration: none;
                border-radius: 5px;
                font-size: 0.9em;
                transition: background-color 0.3s ease;
            }
            .btn:hover {
                background-color: #0056b3;
            }
            .add-combo-btn {
                padding: 10px 15px;
                background-color: #007bff;
                color: white;
                text-decoration: none;
                border-radius: 5px;
                font-size: 0.9em;
                transition: background-color 0.3s ease;
                margin-bottom: 5px;
            }
            .add-combo-btn:hover {
                background-color: #0056b3;
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <div class="combo-list">
            <h2>DANH SÁCH COMBO</h2>
            <%
                Account account = (Account) session.getAttribute("account");
            %>
            <% if (account != null && ("Admin".equals(account.getRole()) || "Staff".equals(account.getRole()))) { %>
            <a href="addCombo.jsp" class="add-combo-btn">Thêm Combo</a>
            <% } %>
            <%
                ComboDAO comboDAO = new ComboDAO();
                List<Combo> combos = comboDAO.getAllCombo();
                int count = 0;
                for (Combo combo : combos) {
                    if (count % 4 == 0) {
                        if (count != 0) {
                            out.println("</div>");
                        }
                        out.println("<div class='combo-row'>");
                    }
            %>
            <div class="combo">
                <img src="image/<%= combo.getPoster_url() %>" alt="<%= combo.getCombo_name() %>">
                <div class="overlay">
                    <a href="combo?id=<%= combo.getCombo_id() %>" class="btn">Chi tiết</a>
                </div>
            </div>
            <%
                    count++;
                }
                if (count % 4 != 0) {
                    out.println("</div>");
                }
            %>
        </div>
        <jsp:include page="footer.jsp" />
    </body>
</html>

