<%-- 
    Document   : addCombo
    Created on : Mar 23, 2025, 3:39:35 PM
    Author     : Acer Predator
--%>

<%@page import="dal.AccountDAO"%>
<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Account account = (Account) session.getAttribute("account");
    if (account == null || (!"Admin".equals(account.getRole()) && !"Staff".equals(account.getRole()))) {
        response.sendRedirect("home.jsp");
        return;
    }
    String errorMessage = (String) request.getAttribute("errorMessage");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Thêm Combo Mới</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                text-align: center;
                margin: 0;
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
            <h2>Thêm Combo Mới</h2>
            <p style="color:red;"><%= errorMessage != null ? errorMessage : "" %></p>

            <form action="AddComboServlet" method="post">
                <label>Tên Combo:</label>
                <input type="text" name="ComboName" required>

                <label>Hình ảnh:</label>
                <input type="text" name="Poster" required>

                <label>Mô tả:</label>
                <textarea name="Description" required></textarea>

                <label>Giá (VND):</label>
                <input type="number" name="Cost" required>
                
                <label>Số lượng:</label>
                <input type="number" name="Quantity" required>

                <button type="submit">Thêm Combo</button>
            </form>
        </div>
        <jsp:include page="footer.jsp" />
    </body>
</html>

