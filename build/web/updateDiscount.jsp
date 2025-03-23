<%-- 
    Document   : updateDiscount
    Created on : Mar 23, 2025, 8:27:42 PM
    Author     : Acer Predator
--%>

<%@page import="dal.DiscountDAO"%>
<%@page import="model.Discount"%>
<%@page import="dal.AccountDAO"%>
<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Account account = (Account) session.getAttribute("account");

    if (account == null || (!"Admin".equals(account.getRole()) && !"Staff".equals(account.getRole()))) {
        response.sendRedirect("home.jsp");
        return;
    }

    String discountIDParam = request.getParameter("id");
    int discountID = (discountIDParam != null) ? Integer.parseInt(discountIDParam) : 0;

    DiscountDAO discountDAO = new DiscountDAO();
    Discount discount = discountDAO.getDiscountByID(discountID);
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Cập nhật thông tin khuyến mãi</title>
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
            <h2>Cập nhật thông tin khuyến mãi</h2>
            <form action="UpdateDiscountServlet" method="post">
                <input type="hidden" name="discountID" value="<%= discount.getDiscountID() %>">

                <label>Tên khuyến mãi:</label>
                <input type="text" name="discountName" value="<%= discount.getDiscountName() %>" required>

                <label>Mã giảm giá:</label>
                <input type="text" name="discountCode" value="<%= discount.getDiscountCode() %>" readonly>


                <label>Phần trăm giảm:</label>
                <input type="number" name="discountPercentage" step="0.01" value="<%= discount.getDiscountPercentage() %>" required>

                <label>Ngày hết hạn:</label>
                <input type="date" name="expiryDate" value="<%= discount.getExpiryDate() %>" required>

                <label>URL hình ảnh:</label>
                <input type="text" name="imageURL" value="<%= discount.getImageURL() %>" required>

                <label>Mô tả:</label>
                <textarea name="description" required><%= discount.getDescription() %></textarea>


                <button type="submit">Cập nhật</button>
            </form>
        </div>
        <jsp:include page="footer.jsp" />
    </body>
</html>

