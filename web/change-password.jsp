<%-- 
    Document   : change-password
    Created on : Mar 4, 2025, 10:50:35 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Thay Đổi Mật Khẩu</title>
    <link rel="stylesheet" href="css/login.css">
</head>
<body class="bg-light">
    <jsp:include page="header.jsp"/>

    <div class="row d-flex justify-content-center m-5">
        <div class="col-5">
            <div class="card shadow">
                <div class="text-center">
                    <h3 class="mb-0 mt-4">Thay Đổi Mật Khẩu</h3>
                </div>
                <form action="change-password" method="POST">
                    <div class="card-body">
                        <div class="row d-flex justify-content-center">
                            <div class="col-12 col-lg-5 pe-0">
                                <div class="form-outline" data-mdb-input-init>
                                    <input type="password" id="newPassword" name="newPassword" class="form-control" required=""/>
                                </div>
                                <p class="m-0 ps-2 text-danger">${message}</p>
                            </div>
                            <div class="col-12 col-lg-5 pe-0">
                                <div class="form-outline" data-mdb-input-init>
                                    <input type="password" id="confirmPassword" name="confirmPassword" class="form-control" required=""/>
                                </div>
                                <p class="m-0 ps-2 text-danger"></p>
                            </div>
                            <div class="col-12 col-lg-3">
                                <button type="submit" class="btn btn-danger" data-mdb-ripple-init>Đổi Mật Khẩu</button>
                            </div>
                        </div>
                    </div>
                    <input type="hidden" name="email" value="<c:out value="${sessionScope.userForgetPassword.email}"/>">
                </form>
            </div>
        </div>
    </div>

    <jsp:include page="footer.jsp"/>
</body>
</html>