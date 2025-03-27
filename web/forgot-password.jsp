<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Quên Mật Khẩu</title>
    <link rel="stylesheet" href="css/login.css">
    <style>
        .centered-container {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        .custom-form-width {
            width: 400px; /* Điều chỉnh chiều rộng theo ý muốn */
        }

        .form-control {
            padding: 10px;
            font-size: 16px;
        }

        .button {
            padding: 12px 20px;
            font-size: 16px;
        }
         .message-spacing {
        margin-bottom: 20px;
    }
    </style>
</head>
<body class="bg-light">
    <jsp:include page="header.jsp"/>

    <div class="centered-container">
        <div class="custom-form-width">
            <div class="card shadow">
                <div class="text-center">
                    <h1 class="mb-0 mt-4">Quên mật khẩu</h1>
                </div>
                <div class="text-center">
                    <p class="m-0 text-danger message-spacing">Nhập email để đổi mật khẩu</p>
                </div>
                <form action="forgotpassword" method="POST">
                    <div class="card-body">
                        <div class="row d-flex justify-content-center">
                            <div class="col-12 col-lg-5 pe-0">
                                <div class="form-outline" data-mdb-input-init>
                                    <input type="email" id="Email" name="Email" class="form-control" required=""/>
                                </div>
                                <p class="m-0 ps-2 text-danger">${message}</p>
                            </div>
                            <div class="col-12 col-lg-3">
                                <button type="submit" class="button btn-danger" data-mdb-ripple-init>Tìm</button>
                            </div>
                        </div>
                        <div class="d-flex align-items-center justify-content-center pb-4 mt-3">
                            <p class="mb-0 me-2">Bạn vẫn nhớ mật khẩu ?</p>
                            <button type="button" data-mdb-button-init data-mdb-ripple-init onclick="location.href = 'login'" class="button btn-outline-primary">Đăng nhập</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <jsp:include page="footer.jsp"/>
</body>
</html>