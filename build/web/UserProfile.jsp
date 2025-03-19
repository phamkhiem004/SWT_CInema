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
        <link rel="stylesheet" href="css/profilestyles.css">
    </head>
    <body>

        <jsp:include page="header.jsp" />

        <div class="container">
            <!-- Chỉnh sửa thông tin cá nhân -->
            <div class="profile-container">
                <h1>Thông Tin Cá Nhân</h1>
                <form id="profile-form"action="updateProfile?id=${u.id}&action=profile" method="POST">
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
                <form id="password-form" action="updateProfile?id=${u.id}&action=pass" method="POST">
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

            <!-- 🔍 Bộ lọc thời gian -->
            <div class="filter-container">
                <form action="searchbill?id=${u.id}" method="POST">
                    <label for="start-date">Từ:</label>
                    <input type="date" id="start-date" name="sdate" value="${requestScope.sdate}">

                    <label for="end-date">Đến:</label>
                    <input type="date" id="end-date" name="edate" value="${requestScope.edate}">

                    <button>Lọc</button>
                </form>
            </div>

            <!-- 📋 Bảng lịch sử giao dịch -->
            <table class="transaction-table">
                <thead>
                    <tr>
                        <th>Movie</th>
                        <th>Booking Date</th>
                        <th>Total Price</th>
                        <th>Payment Method</th>
                        <th>Payment Status</th>
                        <th>Detail</th>
                    </tr>
                </thead>
            </table></div>
            <jsp:include page="footer.jsp" />
    <tbody>