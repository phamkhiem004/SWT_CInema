<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
    <style>
        .success-message {
            color: green;
            font-size: 16px;
            margin-bottom: 15px;
            text-align: center;
        }
    </style>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/login.css">
        <title>Đăng nhập</title>
    </head>
    <!-- Header -->
    <jsp:include page="header.jsp" />
    <body>

        <!-- Nội dung đăng nhập -->
        <div class="container">
            <div class="form-container sign-in-container">
                <!-- Form đăng nhập -->
                <form action="login" method="post">
                    <h1>Đăng nhập</h1>

                    <!-- Hiển thị thông báo nếu có -->
                    <c:if test="${not empty message}">
                        <div class="success-message">${message}</div>
                    </c:if>

                    <input type="email" name="email" placeholder="Email" required />
                    <input type="password" name="password" placeholder="Mật khẩu" required />

                    <button type="submit">Đăng nhập</button> <br>  <%-- Thêm <br> để xuống dòng --%>
                    <a href="forgot-password.jsp" class="forgot-password">Quên mật khẩu?</a> <%-- Thêm class "forgot-password" --%>
                </form>

                <!-- Nút Sign In nếu người dùng chưa có tài khoản -->
                <div class="text-center">
                    <p>Chưa có tài khoản? <a href="register.jsp" class="sign-in-button">Đăng ký</a></p>
                </div>
            </div>
        </div>

        <!-- Script JS (nếu có) -->
        <script src="LoginForm.js"></script>
    </body>
    <!-- Footer -->
    <jsp:include page="footer.jsp" />
</html>