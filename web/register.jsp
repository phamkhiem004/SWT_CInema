<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Register</title>
    <link rel="stylesheet" href="css/login.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7rxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <style>
        .invalid-feedback {
            color: red;
        }
    </style>
</head>
<jsp:include page="header.jsp" />
<body>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6 mt-5">
                <div class="card">
                    <div class="card-header text-center">
                        <h1>Đăng Ký</h1>
                    </div>
                    <div class="card-body">
                        <!-- Hiển thị thông báo lỗi nếu có -->
                        <c:if test="${not empty message}">
                            <div class="alert alert-danger" role="alert">
                                ${message}
                            </div>
                        </c:if>
                        <form action="register" method="POST">
                            <div class="mb-3">
                                <input type="text" name="fullname" class="form-control" placeholder="Họ Và Tên" value="${param.fullname}" required />
                            </div>
                            <div class="mb-3">
                                <input type="email" name="email" class="form-control" placeholder="Email" value="${param.email}" required />
                            </div>
                            <div class="mb-3">
                                <input type="password" name="password" class="form-control" placeholder="Mật khẩu" id="password" value="${param.password}" required 
                                       title="Mật khẩu phải có ít nhất 8 ký tự và bao gồm cả chữ và số" />
                                <small class="form-text text-muted">Mật khẩu phải có ít nhất 8 ký tự và bao gồm cả chữ và số</small>
                            </div>
                            <div class="mb-3">
                                <input type="password" name="confirmPassword" class="form-control" placeholder="Nhập lại mật khẩu" id="confirmPassword" value="${param.confirmPassword}" required />
                                <div id="confirmPasswordFeedback" class="invalid-feedback" style="display: none;">Mật khẩu xác nhận không trùng khớp.</div>
                            </div>
                            <div class="mb-3">
                                <input type="text" name="phoneNumber" class="form-control" placeholder="Số Điện Thoại" value="${param.phoneNumber}" required />
                            </div>
                            <div class="mb-3">
                                <input type="text" name="address" class="form-control" placeholder="Địa Chỉ" value="${param.address}" required />
                            </div>
                            <div class="mb-3">
                                <label for="dateOfBirth" class="form-label">Ngày Sinh</label>
                                <input type="date" class="form-control" id="dateOfBirth" name="dateOfBirth" value="${param.dateOfBirth}" required />
                            </div>
                            <div class="mb-3">
                                <label for="gender" class="form-label">Giới Tính</label>
                                <select class="form-select" id="gender" name="gender" required>
                                    <option value="Male" ${param.gender == 'Male' ? 'selected' : ''}>Nam</option>
                                    <option value="Female" ${param.gender == 'Female' ? 'selected' : ''}>Nữ</option>
                                    <option value="Other" ${param.gender == 'Other' ? 'selected' : ''}>Khác</option>
                                </select>
                            </div>
                            <div class="d-grid">
                                <button type="submit" class="btn btn-primary">Đăng Ký Ngay</button>
                            </div>
                        </form>
                        <br>
                        <div class="text-center">
                            <p>Bạn đã có tài khoản ?</p>
                            <div class="d-grid">
                                <a href="login.jsp" class="btn btn-secondary btn-sm">Đăng Nhập</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>                     
    <script>
        document.addEventListener('DOMContentLoaded', function () {
            // Kiểm tra nếu có thông báo lỗi về mật khẩu
            const errorMessage = "<c:out value='${message}' />";
            if (errorMessage && errorMessage.includes("Mật khẩu xác nhận không trùng khớp")) {
                // Nếu có lỗi, cuộn trang lên phần nhập mật khẩu
                const passwordInput = document.getElementById('password');
                passwordInput.scrollIntoView({ behavior: 'smooth', block: 'center' });
                
                // Hiển thị lỗi cho confirm password
                const confirmPasswordFeedback = document.getElementById('confirmPasswordFeedback');
                confirmPasswordFeedback.style.display = 'block';
            }
        });
    </script>
    <jsp:include page="footer.jsp" />
</body>
</html>
