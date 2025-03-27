<%-- 
    Document   : booking
    Created on : Feb 21, 2025, 3:30:57 PM
    Author     : Acer Predator
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    List<String> bookedSeats = (List<String>) request.getAttribute("bookedSeats");

    int showtimeID = (Integer) request.getAttribute("showtimeID");
%>

<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Đặt Vé</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <style>
            .seat {
                width: 40px;
                height: 40px;
                display: flex;
                align-items: center;
                justify-content: center;
                border-radius: 5px;
                font-weight: bold;
                cursor: pointer;
                text-align: center;
                user-select: none;
            }
            .standard {
                background-color: #6c757d;
                color: white;
            }  /* Ghế Standard */
            .vip {
                background-color: #ffc107;
            }  /* Ghế VIP */
            .couple {
                background-color: #e83e8c;
                color: white;
            }  /* Ghế Couple */
            .booked {
                background-color: gray;
                cursor: not-allowed;
                opacity: 0.6;
            } /* Ghế đã đặt */
            .my-booked {
                background-color:  #28a745;
                color: white;
                cursor: not-allowed;
                opacity: 0.8;
            }
            .my-choose{
                background-color:  #007bff;
                color: white;
                opacity: 0.8;
            }/* Ghế tôi đặt */
            .seat input {
                display: none;
            }
            .seat:hover {
                filter: brightness(85%);
            }
            .screen {
                background: black;
                color: white;
                padding: 10px;
                text-align: center;
                font-weight: bold;
                border-radius: 5px;
            }
            .legend-box {
                width: 30px;
                height: 30px;
                display: inline-block;
                border-radius: 5px;
                margin-right: 10px;
            }
        </style>
    </head>

    <body>
        <jsp:include page="header.jsp" />
        <div class="container text-center mt-4">
            <h2 class="mb-3">Chọn Ghế</h2>

            <!-- Chú thích các loại ghế -->
            <div class="d-flex justify-content-center align-items-center mb-3">
                <div class="legend-box standard"></div> <span class="me-3">Standard - 50K</span>
                <div class="legend-box vip"></div> <span class="me-3">VIP - 100K</span>
                <div class="legend-box couple"></div> <span class="me-3">Couple - 150K</span>
                <div class="legend-box booked"></div> <span class="me-3">Đã đặt</span>
                <div class="legend-box my-booked"></div> <span class="me-3">Ghế tôi đã đặt trước đó </span>
                <div class="legend-box my-choose"></div> <span>Ghế tôi đang chọn</span>
            </div>

            <!-- Màn hình -->
            <div class="screen mt-3">Màn Hình</div>

            <form action="booking" method="post" class="text-center mt-3">
                <input type="hidden" name="showtimeID" value="${showtimeID}">

                <div class="d-flex flex-column align-items-center">
                    <c:set var="rows" value="A,B,C,D,E,F,G,H,I,K" />
                    <c:forEach var="row" items="${rows.split(',')}">
                        <div class="d-flex align-items-center mb-2">
                            <strong class="me-2">${row}</strong>
                            <c:forEach begin="1" end="10" var="col">
                                <c:set var="seatName" value="${row}${col}" />
                                <c:set var="isBooked" value="${bookedSeats.contains(seatName)}" />
                                <c:set var="isMyBooked" value="${myBookedSeats.contains(seatName)}" />
                                <c:set var="seatClass" value="seat" />

                                <c:choose>
                                    <c:when test="${row eq 'A' or row eq 'B' or row eq 'I' or row eq 'K'}">
                                        <c:set var="seatClass" value="${seatClass} standard" />
                                    </c:when>
                                    <c:when test="${row eq 'C' or row eq 'D' or row eq 'E' or row eq 'F' or row eq 'G'}">
                                        <c:set var="seatClass" value="${seatClass} vip" />
                                    </c:when>
                                    <c:when test="${row eq 'H'}">
                                        <c:set var="seatClass" value="${seatClass} couple" />
                                    </c:when>
                                </c:choose>

                                <c:if test="${isBooked}">
                                    <c:set var="seatClass" value="${seatClass} booked" />
                                </c:if>
                                <c:if test="${isMyBooked}">
                                    <c:set var="seatClass" value="${seatClass} my-booked" />
                                </c:if>

                                <label class="${seatClass} me-2" data-bs-toggle="tooltip" title="Ghế ${seatName}">
                                    <c:if test="${not isBooked and not isMyBooked}">
                                        <input type="checkbox" name="seat" value="${seatName}">
                                    </c:if>
                                    ${col}
                                </label>
                            </c:forEach>
                        </div>
                    </c:forEach>
                </div>
                <c:if test="${isExistPending == false}">
                    <button type="submit" class="btn btn-primary mt-3">Đặt Vé</button>
                </c:if>
                <c:if test="${isExistPending == true}">
                    <div style="padding: 15px; background-color: #ffcccc; border-left: 5px solid #ff4444; color: #cc0000; font-weight: bold; margin: 10px 0; border-radius: 5px;">
                        ⚠️ Bạn không thể đặt thêm ghế vì bạn đã có một đơn hàng chưa hoàn tất. <br> 
                        Vui lòng hoàn tất hoặc hủy đơn trước khi đặt ghế mới.
                    </div>
                </c:if>   

            </form>
        </div>

        <script>
            var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
            var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
                return new bootstrap.Tooltip(tooltipTriggerEl);
            });
        </script>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                let seatElements = document.querySelectorAll(".seat input");

                seatElements.forEach(seat => {
                    seat.addEventListener("change", function () {
                        let parentLabel = this.parentElement;
                        if (this.checked) {
                            parentLabel.classList.add("my-choose");
                        } else {
                            parentLabel.classList.remove("my-choose");
                        }
                    });
                });

                // Khôi phục trạng thái ghế đã chọn từ session
                let selectedSeats = ${myBookedSeats}; // Danh sách ghế tôi đã đặt từ session
                seatElements.forEach(seat => {
                    if (selectedSeats.includes(seat.value)) {
                        seat.checked = true;
                        seat.parentElement.classList.add("my-choose");
                    }
                });
            });
        </script>
    </body>
</html>

