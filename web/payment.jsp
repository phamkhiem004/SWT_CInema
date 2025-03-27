<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Thanh toán</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .card {
                max-width: 500px;
                margin: auto;
            }
            .qr-container {
                display: flex;
                justify-content: center;
                margin-top: 20px;
            }
            .qr-container img {
                border: 5px solid #f8f9fa;
                padding: 10px;
                border-radius: 10px;
                background: #fff;
            }
            .payment-code {
                font-size: 1.2rem;
                font-weight: bold;
                color: #dc3545;
            }
            /* CSS cho popup */
            .modal {
                display: none;
                position: fixed;
                z-index: 1000;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                background-color: rgba(0,0,0,0.4);
            }
            .modal-content {
                background-color: white;
                margin: 15% auto;
                padding: 20px;
                border-radius: 10px;
                width: 50%;
                text-align: center;
                box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.3);
            }
            .modal-content h4 {
                margin-bottom: 15px;
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp" />

        <div class="container mt-5">
            <h2 class="text-center mb-4">Thông tin thanh toán</h2>

            <div class="card shadow p-4">
                <h4>Người dùng: ${user.fullname}</h4>
                <p><strong>Billing ID:</strong> ${billing.billingID}</p>
                <p><strong>Showtime ID:</strong> ${billing.showtimeID}</p>
                <p><strong>Tổng tiền sau giảm giá:</strong> <span class="text-danger">${discountedTotal} VNĐ</span></p>
                <p><strong>Nội dung chuyển khoản:</strong> <span class="payment-code">${paymentContent}</span></p>
            </div>

            <div class="qr-container">
                <img src="./image/qr.jpg" alt="QR Code">
            </div>

            <div class="text-center mt-4">
                <button class="btn btn-primary" onclick="showPopup()">Xác Nhận Thanh Toán</button>
            </div>
        </div>

        <!-- Popup -->
        <div id="paymentPopup" class="modal">
            <div class="modal-content">
                <h4>Thanh toán chờ được xác nhận</h4>
                <p>Vui lòng chờ hệ thống xác nhận giao dịch của bạn.</p>
                <button class="btn btn-secondary" onclick="closePopup()">Quay lại trang chủ</button>
            </div>
        </div>

        <script>
            function showPopup() {
                document.getElementById("paymentPopup").style.display = "block";
            }

            function closePopup() {
                document.getElementById("paymentPopup").style.display = "none";
                window.location.href = "home"; // Quay lại trang chủ
            }
        </script>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>