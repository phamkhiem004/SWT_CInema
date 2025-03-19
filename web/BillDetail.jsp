<%-- 
    Document   : BillDetail
    Created on : Feb 28, 2025, 12:55:15 AM
    Author     : DUNGVT
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/billstyles.css">
    </head>
    <body>
        <div class="bill-detail-container">
            <!-- 🎬 Poster + Thông tin phim -->
            <div class="bill-header">
                <img src="${requestScope.poster_url}" class="movie-poster">

                <!-- 📋 Bảng thông tin vé dạng dọc -->
                <table class="bill-table bill-info-table">
                    <tbody>
                        <tr><td><strong>Movie Name:</strong> <span>${requestScope.bill.movie_name}</span></td></tr>
                        <tr><td><strong>Booking Date:</strong> <span>${requestScope.bill.booking_date}</span></td></tr>
                        <tr><td><strong>Showtime:</strong> <span>${requestScope.showtime}</span></td></tr>
                        <tr><td><strong>Cinema:</strong> <span>${requestScope.cinema_name}</span></td></tr>
                        <tr><td><strong>Room Name:</strong> <span>${requestScope.room_name}</span></td></tr>
                        <tr><td><strong>Discount:</strong> <span>${requestScope.bill.discount} %</span></td></tr>
                        <tr><td><strong>Payment Method:</strong> <span>${requestScope.bill.payment_method}</span></td></tr>
                        <tr><td><strong>Payment Status:</strong> <span class="payment-status
                                                                       ${requestScope.bill.payment_status == "Completed" ? "Paid":""}
                                                                       ${requestScope.bill.payment_status == "Pending" ? "Pending":""}
                                                                       ${requestScope.bill.payment_status == "Cancelled" ? "Failed":""}
                                                                       ">${requestScope.bill.payment_status}</span></td></tr>
                    </tbody>
                </table>
            </div>

            <!-- 📋 Bảng thông tin ghế ngồi -->
            <table class="bill-table">
                <thead>
                    <tr>
                        <th>Seat Type</th>
                        <th>Seat Name</th>
                        <th>Seat Price</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.listS}" var="s">
                        <tr>
                            <td>${s.seat_type}</td>
                            <td>${s.seat_name}</td>
                            <td>${s.seat_price} USD</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <!-- 🍿 Bảng thông tin combo -->
            <table class="bill-table">
                <thead>
                    <tr>
                        <th>Combo Name</th>
                        <th>Description</th>
                        <th>Quantity</th>
                        <th>Combo Price</th>
                        <th>Total Combo Price</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.listC}" var="c">
                        <tr>
                            <td>${c.combo_name}</td>
                            <td>${c.description}</td>
                            <td>${c.quantity}</td>
                            <td>${c.price} USD</td>
                            <td>${c.price * c.quantity} USD</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <!-- 🎟 Tổng hóa đơn -->
            <div class="total-bill-container">
                <p><strong>Total Price Before Discount:</strong> <span>${requestScope.totalprice_c} USD</span></p>
                <p><strong>Discount:</strong> <span>- ${requestScope.discount_price} USD</span></p>
                <p class="final-price"><strong>Total Bill Price:</strong> <span>${requestScope.totalprice_f} USD</span></p>
            </div>
        </div>
    </body>
</html>
