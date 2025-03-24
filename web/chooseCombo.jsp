<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Chọn Combo</title>
    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="styles.css"> <%-- CSS tùy chỉnh nếu có --%>
</head>
<body>

     <jsp:include page="header.jsp" />

    <div class="container mt-4">
        <h2 class="text-center mb-4">Chọn Combo</h2>

        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>

        <form action="choose-combo" method="post">
            <input type="hidden" name="billingID" value="${billingID}">

            <div class="card shadow p-4">
                <h3 class="mb-3">Danh sách Combo</h3>
                
                <div class="row">
                    <c:forEach var="combo" items="${comboList}">
                        <div class="col-md-6 mb-3">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title">${combo.comboName}</h5>
                                    <p class="card-text">Giá: <strong>${combo.cost} VND</strong></p>
                                    <input type="checkbox" class="form-check-input me-2" name="comboID" value="${combo.comboID}">
                                    <label class="form-label">Chọn số lượng:</label>
                                    <input type="number" class="form-control" name="quantity" min="0" value="0">
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>

            <div class="card shadow p-4 mt-4">
                <h3 class="mb-3">Chọn phương thức thanh toán</h3>
                <select class="form-select" name="paymentMethod">
                    <option value="Cash">💵 Tiền mặt</option>
                    <option value="QR Pay">📱 QR Pay</option>
                </select>
            </div>

            <div class="card shadow p-4 mt-4">
                <h3 class="mb-3">Nhập mã giảm giá</h3>
                <input type="text" class="form-control" name="discountCode" placeholder="Nhập mã giảm giá...">
            </div>

            <div class="text-center mt-4">
                <button type="submit" class="btn btn-primary btn-lg">✅ Xác nhận</button>
            </div>
        </form>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>