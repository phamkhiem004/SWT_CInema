<%-- 
    Document   : addevents
    Created on : Feb 19, 2025, 1:45:36 PM
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
%>


<%
    String errorMessage = (String) request.getAttribute("errorMessage");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thêm mã giảm giá</title>
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
            .form-container input, .form-container textarea{
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
            <h2>Thêm mã giảm giá mới</h2>
            <p style="color:red;"><%= errorMessage != null ? errorMessage : "" %></p>

            <form action="AddDiscountServlet" method="post">
                <label>Mã Giảm Giá:</label>
                <input type="text" name="DiscountCode" value="${requestScope.generatedCode}" readonly>

                <label>Tên Giảm Giá:</label>
                <input type="text" name="DiscountName" required>

                <label>Phần trăm (%):</label>
                <input type="number" name="DiscountPercentage" required step="0.01" min="0" max="100">

                <label>Ngày kết thúc:</label>
                <input type="date" name="ExpiryDate" required>

                <label>Ảnh URL:</label>
                <input type="text" name="ImageURL">

                <label>Mô tả:</label>
                <textarea name="Description" required></textarea>

                <button type="submit">Thêm giảm giá</button>
            </form>
        </div>
        <jsp:include page="footer.jsp" />
    </body>
</html>
