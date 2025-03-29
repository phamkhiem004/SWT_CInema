<%-- 
    Document   : user
    Created on : Feb 9, 2025, 1:51:43 PM
    Author     : DUNGVT
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>My Profile - Web Bán Vé Xem Phim</title>
        <style>
            /* Tổng quan */
            body {
                font-family: Arial, sans-serif;
                background-color: #f8f9fa;
                margin: 0;
                padding: 0;
            }

            .container {
                width: 80%;
                margin: 20px auto;
                background: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
            }

            /* Tiêu đề chính */
            h1 {
                text-align: center;
                color: #ff6600;
                font-size: 24px;
                margin-bottom: 20px;
            }

            /* Form chỉnh sửa thông tin */
            .profile-container, .password-container {
                background: #ffffff;
                padding: 20px;
                margin-bottom: 20px;
                border-radius: 8px;
                box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.1);
            }

            label {
                font-weight: bold;
                display: block;
                margin-top: 10px;
                color: #333;
            }

            input, select {
                width: 100%;
                padding: 8px;
                margin-top: 5px;
                border: 1px solid #ddd;
                border-radius: 4px;
            }

            button {
                background-color: #ff6600;
                color: white;
                border: none;
                padding: 10px;
                width: 100%;
                border-radius: 4px;
                cursor: pointer;
                font-size: 16px;
                margin-top: 15px;
            }

            button:hover {
                background-color: #e65c00;
            }

            /* Thông báo */
            small {
                display: block;
                margin-top: 5px;
            }

            small.error-message {
                color: red;
            }

            small.success-message {
                color: green;
            }

            /* 🎟 Lịch sử giao dịch */
            .history-container {
                width: 100%;
                text-align: center;
                padding: 20px;
                background: #ffffff;
                border-radius: 8px;
                box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.1);
            }

            .transaction-table {
                width: 90%;
                margin: 0 auto;
                border-collapse: collapse;
                background: #fff;
                border-radius: 8px;
                overflow: hidden;
            }

            .transaction-table th {
                background-color: #ff6600;
                color: white;
                padding: 12px;
                text-transform: uppercase;
            }

            .transaction-table td {
                padding: 10px;
                border-bottom: 1px solid #ddd;
            }

            .transaction-table tr:hover {
                background-color: #f1f1f1;
            }

            /* Nút hành động */
            .btn {
                display: inline-block;
                padding: 8px 12px;
                text-decoration: none;
                font-size: 14px;
                border-radius: 4px;
                margin: 5px;
            }

            .btn-info {
                background-color: #007bff;
                color: white;
            }

            .btn-info:hover {
                background-color: #0056b3;
            }

            .btn-danger {
                background-color: #dc3545;
                color: white;
            }

            .btn-danger:hover {
                background-color: #b02a37;
            }
            .container {
                display: flex;
                justify-content: space-between;
                gap: 20px;
                max-width: 90%;
                margin: 20px auto;
            }

            /* Mỗi bảng chiếm 50% */
            .profile-container,
            .password-container {
                width: 50%;
                background: #ffffff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.1);
            }

            /* Tiêu đề */
            h2 {
                text-align: center;
                color: #ff6600;
                font-size: 22px;
                margin-bottom: 15px;
            }

            /* Form input */
            input, select {
                width: 100%;
                padding: 8px;
                margin-top: 5px;
                border: 1px solid #ddd;
                border-radius: 4px;
            }

            /* Nút */
            button {
                width: 100%;
                padding: 10px;
                margin-top: 15px;
                background-color: #ff6600;
                color: white;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                font-size: 16px;
            }

            button:hover {
                background-color: #e65c00;
            }

            /* Responsive: Nếu màn hình nhỏ, hiển thị dọc */
            @media (max-width: 768px) {
                .profile-wrapper {
                    flex-direction: column;
                }
                .profile-container, .password-container {
                    width: 100%;
                }
            }


        </style>
    </head>
    <body>

        <jsp:include page="header.jsp" />

        <div class="container">
            <!-- Chỉnh sửa thông tin cá nhân -->
            <div class="profile-container">
                <h1>Thông Tin Cá Nhân</h1>
                <form id="profile-form"action="updateprofile?id=${u.id}&action=profile" method="POST">
                    <label for="full-name">Họ tên:</label>
                    <input type="text" id="full-name" name="fname" value="${u.fullname}" required minlength="3" maxlength="50">
                    <small class="error-message"></small>

                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" value="${u.email}" required readonly>
                    <small class="error-message"></small>

                    <label for="phone">Số điện thoại:</label>
                    <input type="text" id="phone" name="phone" value="${u.phoneNumber}" required pattern="^0[0-9]{9}$">
                    <small class="error-message"></small>

                    <label for="dob">Ngày sinh:</label>
                    <input type="date" id="dob" name="dob" value="${u.dateOfBirth}" required>
                    <small class="error-message"></small>

                    <label for="gender">Giới tính:</label>
                    <select id="gender" name="gender" required>
                        <option value="Male" ${u.gender == "Male" ? "selected" : ""}>Nam</option>
                        <option value="Female" ${u.gender == "Female" ? "selected" : ""}>Nữ</option>
                        <option value="Other" ${u.gender == "Other" ? "selected" : ""}>Khác</option>
                    </select>
                    <small class="error-message"></small>

                    <label for="address">Địa chỉ:</label>
                    <input type="text" id="address" name="address" value="${u.address}" required minlength="5">
                    <small class="error-message"></small>

                    <button type="submit">Cập nhật thông tin</button>
                    <br/>
                    <c:if test="${requestScope.alert_true_info != null}">
                        <small style="color: green">${requestScope.alert_true_info}</small>                            
                    </c:if></form>
            </div>

            <!-- Thay đổi mật khẩu -->
            <div class="password-container">
                <h1>Thay Đổi Mật Khẩu</h1>
                <form id="password-form" action="updateprofile?id=${u.id}&action=pass" method="POST">
                    <label for="current-password">Mật khẩu hiện tại:</label>
                    <input type="password" id="current-password" name="cpass" value="${requestScope.cpass}" required minlength="6">
                    <small class="error-message"></small>

                    <c:if test="${requestScope.alert_false_cpass != null}">
                        <small style="color: red">${requestScope.alert_false_cpass}</small>
                    </c:if>

                    <label for="new-password">Mật khẩu mới:</label>
                    <input type="password" id="new-password" name="npass" value="${requestScope.npass}" required minlength="6">
                    <small class="error-message"></small>

                    <c:if test="${requestScope.alert_false_npass != null}">
                        <small style="color: red">${requestScope.alert_false_npass}</small>
                    </c:if>

                    <label for="confirm-password">Xác nhận mật khẩu mới:</label>
                    <input type="password" id="confirm-password" name="cnpass"  value="${requestScope.cnpass}" required>
                    <small class="error-message"></small>

                    <button type="submit">Thay đổi mật khẩu</button>
                    <br/>
                    <c:if test="${requestScope.alert_true != null}">
                        <small style="color: green">${requestScope.alert_true}</small>                            
                    </c:if>
                </form>
            </div>
        </div>

        <!-- 🎟 Lịch sử giao dịch -->
        <div class="history-container">
            <h1>Lịch Sử Giao Dịch</h1>



            <!-- 📋 Bảng lịch sử giao dịch -->
            <table class="transaction-table">
                <thead>
                    <tr>
                        <th>Tên Phim</th>
                        <th>Ngày Đặt Vé</th>
                        <th>Tổng giá tiền</th>
                        <th>Phương thức thanh toán</th>
                        <th>Trạng thái</th>
                        <th>Hành động</th>
                        <th>Huỷ đơn</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="bill" items="${listB}">
                        <tr>
                            <td>${bill.title}</td> <!-- Thay bằng Movie Name nếu có -->
                            <td>${bill.bookingDate}</td>
                            <td>${bill.totalAmount}</td>
                            <td>${bill.paymentMethod}</td>
                            <td>${bill.paymentStatus}</td>
                            <td>
                                <a href="billdetail?billingID=${bill.billingID}" class="btn btn-info">View</a>
                                <c:if test="${bill.paymentStatus eq 'Pending'}">
                                    <a href="continueBilling?billingID=${bill.billingID}" class="btn btn-info">Continue</a>    
                                </c:if>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${bill.paymentStatus == 'Pending'}">                                      
                                        <a href="declineBill?billingID=${bill.billingID}&action=decline" class="btn btn-danger">Decline</a>
                                    </c:when>
                                    
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <jsp:include page="footer.jsp" />
    <tbody>