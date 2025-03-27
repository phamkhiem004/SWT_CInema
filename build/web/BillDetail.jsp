<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Billing"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Chi Tiết Hóa Đơn</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                background-color: #f9f6ef;
                padding: 0px;
            }
            .bill-container {
                width: 350px;
                background: white;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
                text-align: center;
                font-family: Arial, sans-serif;
                margin: 30px auto;
                border: 2px dashed #333;
            }

            .bill-header {
                border-bottom: 2px solid #ddd;
                padding-bottom: 10px;
                margin-bottom: 15px;
            }

            .bill-header h2 {
                margin: 0;
                font-size: 18px;
                color: #333;
            }

            .bill-body {
                text-align: left;
            }

            .bill-body table {
                width: 100%;
                border-collapse: collapse;
            }

            .bill-body th {
                text-align: left;
                font-weight: bold;
                padding: 5px 0;
            }

            .bill-body td {
                text-align: right;
                padding: 5px 0;
                font-weight: bold;
            }

            .bill-footer {
                border-top: 2px solid #ddd;
                padding-top: 10px;
                margin-top: 15px;
                font-size: 14px;
                color: #777;
            }

            .btn-primary {
                display: inline-block;
                padding: 8px 15px;
                margin-top: 15px;
                background: #007bff;
                color: white;
                text-decoration: none;
                border-radius: 5px;
                font-weight: bold;
            }

            .btn-primary:hover {
                background: #0056b3;
            }

        </style>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <%
                        Billing bill = (Billing) request.getAttribute("bill");
                        if (bill != null) {
        %>
        <div class="bill-container">
            <div class="bill-header">
                <h2>Chi tiết hóa đơn</h2>
                <p>Ngày: <%= bill.getBookingDate() %></p>
            </div>

            <div class="bill-body">
                <table>
                    <tr><th>Mã Hóa Đơn:</th><td><%= bill.getBillingID() %></td></tr>
                    <tr><th>Tên Suất Chiếu:</th><td><%= bill.getTitle() %></td></tr>
                    <tr><th>Giờ chiếu:</th><td><%= bill.getStartTime() %></td></tr>
                    <tr><th>Tổng Tiền:</th><td><%= bill.getTotalAmount() %> VNĐ</td></tr>
                    <tr><th>Phương Thức Thanh Toán:</th><td><%= bill.getPaymentMethod() %></td></tr>
                    <tr><th>Trạng Thái Thanh Toán:</th><td><%= bill.getPaymentStatus() %></td></tr>
                    <tr><th>Giảm Giá:</th><td><%= bill.getDiscountPercentage() %>%</td></tr>
                    <tr><th>Ghế Đã Đặt:</th>
                        <td>
                            <% for (String seat : bill.getSeatNames()) { %>
                            <span style="padding: 5px; border: 1px solid #ccc; margin: 3px; display: inline-block;">
                                <%= seat %>
                            </span>
                            <% } %>
                        </td>
                    </tr>
                </table>
            </div>

            <div class="bill-footer">
                <%
                    String status = bill.getPaymentStatus();
                    if ("Completed".equalsIgnoreCase(status)) {
                %>
                <p style="color: green; font-weight: bold;">✅ Thanh toán thành công</p>
                <p>Cảm ơn quý khách đã sử dụng dịch vụ!</p>
                <%
                    } else if ("Pending".equalsIgnoreCase(status)) {
                %>
                <p style="color: orange; font-weight: bold;">⏳ Hóa đơn đang chờ xác nhận</p>
                <%
                    } else if ("Cancelled".equalsIgnoreCase(status)) {
                %>
                <p style="color: red; font-weight: bold;">❌ Hóa đơn đã bị hủy bỏ</p>
                <%
                    }
                %>
            </div>
        </div>
        <%}
        %>

        <jsp:include page="footer.jsp" />
    </body>
</html>
