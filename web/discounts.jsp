<%-- 
    Document   : events
    Created on : Feb 16, 2025, 3:19:36 PM
    Author     : Acer Predator
--%>
<%@page import="dal.AccountDAO"%>
<%@page import="model.Account"%>
<%@page import="dal.DiscountDAO"%>
<%@page import="model.Discount"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Khuyến Mãi</title>
    </head>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f8f8f8;
        }
        .discount-list {
            display: flex;
            flex-direction: column;
            align-items: center;
            padding: 20px;
        }

        .discount-row {
            display: grid;
            grid-template-columns: repeat(4, 1fr); /* 4 discount trên mỗi hàng */
            gap: 20px; /* Giảm khoảng cách giữa các discount */
            width: 100%;
            max-width: 1200px;
            margin-bottom: 20px; /* Thêm khoảng cách giữa các dòng */
        }


        .discount {
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            text-align: center;
            padding: 5px;
            position: relative;
            overflow: hidden;
            transition: transform 0.3s ease-in-out;
        }

        .discount:hover {
            transform: scale(1.05);
        }

        .discount img {
            width: 100%;
            height: 300px; /* Điều chỉnh chiều cao hợp lý */
            object-fit: cover;
            border-radius: 10px;
        }

        .discount .overlay {
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

        .discount:hover .overlay {
            opacity: 1;
        }

        .discount .btn-container {
            display: flex;
            gap: 5px;
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


        .discount-list h2 {
            margin: 0;
        }

        .add-discount-btn {
            padding: 10px 15px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-size: 0.9em;
            transition: background-color 0.3s ease;
            margin-bottom: 5px;
        }

        .add-discount-btn:hover {
            background-color: #0056b3;
        }

    </style>
    <body>

        <!--header.jsp -->
        <jsp:include page="header.jsp" />

        <div class="discount-list">
            <h2>KHUYẾN MÃI</h2>
            <%
                    Account account = (Account) session.getAttribute("account");
            %>
            <% if (account != null && ("Admin".equals(account.getRole()) || "Staff".equals(account.getRole()))) { %>
            <a href="AddDiscountServlet" class="add-discount-btn">Thêm khuyến mãi</a>
            <% } %>


            <%
                DiscountDAO discountDAO = new DiscountDAO();
                List<Discount> discounts = discountDAO.getAllDiscounts();
                int count = 0;
                for (Discount discount : discounts) {
                    if (count % 4 == 0) {
                        if (count != 0) {
                            out.println("</div>");
                        }
                        out.println("<div class='discount-row'>");
                    }
            %>
            <div class="discount">
                <img src="image/<%= discount.getImageURL() %>" alt="<%= discount.getDiscountCode() %>">
                <div class="overlay">
                    <div class="btn-container">
                        <a href="discount?id=<%= discount.getDiscountID() %>" class="btn">Thông tin</a>

                    </div>

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

        <!--footer.jsp -->
        <jsp:include page="footer.jsp" />
    </body>
</html>


