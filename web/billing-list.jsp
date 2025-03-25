<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Billing List</title>
    <link rel="stylesheet" href="css/admin.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
</head>
<body>
    <div class="container">
        <jsp:include page="sidebar.jsp" />
        <main>
            <div class="top-bar">
                <h1>Billing List</h1>
                <div class="search-container">
                    <form action="billing-list" method="GET">
                        <input type="text" id="searchInput" placeholder="Search billing..." name="search"/>
                        <button class="btn-search" type="submit">Search</button>
                    </form>
                </div>
            </div>
            <table class="billing-table">
                <thead>
                    <tr>
                        <th>Billing ID</th>
                        <th>User ID</th>
                        <th>Showtime ID</th>
                        <th>Total Amount</th>
                        <th>Payment Method</th>
                        <th>Payment Status</th>
                        <th>Discount ID</th>
                        <th>Booking Date</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.billingList}" var="billing">
                        <tr>
                            <td>${billing.billingID}</td>
                            <td>${billing.userID}</td>
                            <td>${billing.showtimeID}</td>
                            <td>${billing.totalAmount}</td>
                            <td>${billing.paymentMethod}</td>
                            <td>${billing.paymentStatus}</td>
                            <td>${billing.discountID != null ? billing.discountID : 'N/A'}</td>
                            <td>${billing.bookingDate}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${billing.paymentStatus == 'Pending'}">
                                        <a href="updatePaymentStatus?billingID=${billing.billingID}&action=approve" class="btn btn-success">Approve</a>
                                        <a href="updatePaymentStatus?billingID=${billing.billingID}&action=decline" class="btn btn-danger">Decline</a>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="status-processed">${billing.paymentStatus}</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </main>
    </div>
</body>
</html>
