<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Chọn Combo</title>
        <!-- Bootstrap 5 -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="styles.css">     </head>
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
                                        <input type="checkbox" class="form-check-input me-2 combo-checkbox" name="comboID" value="${combo.comboID}" onclick="toggleQuantity(this)">
                                        <label class="form-label">Chọn số lượng:</label>
                                        <input type="number" class="form-control combo-quantity" name="quantity" min="1" value="1" disabled>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>

                <div class="card shadow p-4 mt-4">
                    <h3 class="mb-3">Phương thức thanh toán</h3>
                    <input type="text" class="form-control" value="QR Pay" disabled name="paymentMethod">
                </div>

                <div class="card shadow p-4 mt-4">
                    <div style="color: red">${errDiscount}</div>
                    <div style="color: green">${sucDiscount}</div>
                    <h3 class="mb-3">Nhập mã giảm giá</h3>
                    <input type="text" class="form-control" name="discountCode" placeholder="Nhập mã giảm giá...">
                </div>

                <div class="text-center mt-4">
                    <button type="submit" class="btn btn-primary btn-lg">✅ Xác nhận</button>
                    <c:if test="${isApproved == true}">
                        <button type="button" class="btn btn-primary btn-lg"><a href="payment?billingID=${billingID}">Thanh Toán</a></button>
                    </c:if>

                </div>
            </form>
        </div>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <script>
                                            function toggleQuantity(checkbox) {
                                                let quantityInput = checkbox.closest(".card-body").querySelector(".combo-quantity");
                                                quantityInput.disabled = !checkbox.checked;
                                                if (!checkbox.checked) {
                                                    quantityInput.value = 1; // Reset về 1 khi bỏ chọn
                                                }
                                            }
        </script>

    </body>
</html>