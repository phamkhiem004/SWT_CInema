<%-- 
    Document   : updateCombo
    Created on : Mar 23, 2025, 8:06:23 PM
    Author     : Acer Predator
--%>

<%@page import="dal.ComboDAO"%>
<%@page import="model.Combo"%>
<%@page import="dal.AccountDAO"%>
<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Account account = (Account) session.getAttribute("account");
    if (account == null || (!"Admin".equals(account.getRole()) && !"Staff".equals(account.getRole()))) {
        response.sendRedirect("home.jsp");
        return;
    }

    String comboIDParam = request.getParameter("id");
    int comboID = (comboIDParam != null) ? Integer.parseInt(comboIDParam) : 0;
    ComboDAO comboDAO = new ComboDAO();
    Combo combo = comboDAO.getComboByID(comboID);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Cập nhật thông tin combo</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                text-align: center;
            }
            .form-container {
                background-color: #fff;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                max-width: 500px;
                margin: 0 auto;
                text-align: left;
            }
            .form-container label {
                display: block;
                margin-top: 10px;
            }
            .form-container input, .form-container textarea, .form-container select {
                width: 100%;
                padding: 8px;
                margin-top: 5px;
                border: 1px solid #ccc;
                border-radius: 5px;
            }
            .form-container button {
                margin-top: 15px;
                padding: 10px;
                background-color: #007bff;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }
            .form-container button:hover {
                background-color: #0056b3;
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <div class="form-container">
            <h2>Cập nhật thông tin combo</h2>
            <form action="UpdateComboServlet" method="get">
                <input type="hidden" name="comboID" value="<%= combo.getCombo_id() %>">

                <label>Tên combo:</label>
                <input type="text" name="comboName" value="<%= combo.getCombo_name() %>" required>

                <label>Mô tả:</label>
                <textarea name="description" required><%= combo.getDescription() %></textarea>

                <label>Giá:</label>
                <input type="number" name="price" step="0.01" value="<%= combo.getPrice() %>" required>

                <label>Số lượng:</label>
                <input type="number" name="quantity" value="<%= combo.getQuantity() %>" required>

                <label>Hình ảnh URL:</label>
                <input type="text" name="posterURL" value="<%= combo.getPoster_url() %>" required>
             

                <button type="submit">Cập nhật</button>
            </form>
        </div>
        <jsp:include page="footer.jsp" />
    </body>
</html>

