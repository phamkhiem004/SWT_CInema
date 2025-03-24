<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Xác nhận OTP</title>
    <link rel="stylesheet" href="css/login.css">
</head>
<body class="bg-light">
    <jsp:include page="header.jsp"/>
    
    <div class="row d-flex justify-content-center m-5">
        <div class="col-5">
            <div class="card shadow">
                <div class="text-center">
                    <h3 class="mb-0 mt-4">Nhập mã OTP</h3>
                </div>
                <div class="text-center">
                    <p class="m-0 text-danger">Nhập mã OTP đã gửi vào email của bạn</p>
                </div>
                <form action="verifyotp" method="POST">
                    <div class="card-body">
                        <div class="row d-flex justify-content-center">
                            <div class="col-12 col-lg-5 pe-0">
                                <div class="form-outline" data-mdb-input-init>
                                    <input type="text" id="OTP" name="OTP" placeholder="Nhập Mã OTP" class="form-control" required=""/>
                                </div>
                                <p class="m-0 ps-2 text-danger">${message}</p>
                            </div>
                            <div class="col-12 col-lg-3">
                                <button type="submit" class="button btn-danger" data-mdb-ripple-init>Xác nhận</button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    
    <jsp:include page="footer.jsp"/>
</body>
</html>
