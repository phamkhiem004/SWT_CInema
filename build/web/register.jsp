<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        <link rel="stylesheet" href="css/login.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7rxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
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
                            <c:if test="${not empty message}">
                                <div class="alert alert-danger" role="alert">
                                    ${message}
                                </div>
                            </c:if>
                            <form action="register" method="POST">
                                <input type="email" name="email" placeholder="Email" required />
                                <input type="password" name="password" placeholder="Mật khẩu" required />
                                <input type="fullname" name="fullname" placeholder="Họ Và Tên" required />
                                <input type="address" name="address" placeholder="Địa Chỉ" required />
                                <input type="phoneNumber" name="phoneNumber" placeholder="Số Điện Thoại" required />
                                <div class="mb-3">
                                    <label for="dateOfBirth" class="form-label">Ngày Sinh</label>
                                    <input type="date" class="form-control" id="dateOfBirth" name="dateOfBirth" required>
                                </div>
                                <div class="mb-3">
                                    <label for="gender" class="form-label">Giới Tính</label>
                                    <select class="form-select" id="gender" name="gender" required>
                                        <option value="Male">Nam</option>
                                        <option value="Female">Nữ</option>
                                        <option value="Other">Khác</option>
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

        <script src="LoginForm.js"></script>
        <jsp:include page="footer.jsp" />
    </body>
</html>