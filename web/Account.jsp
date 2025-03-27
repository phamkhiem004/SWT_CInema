<%-- 
    Document   : Account
    Created on : Mar 22, 2025, 1:11:58 PM
    Author     : Acer Predator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="dal.AccountDAO"%>
<%@page import="model.Account"%>
<%
    Account account = (Account) session.getAttribute("account");

    if (account == null || (!"Admin".equals(account.getRole()))) {
    response.sendRedirect("home.jsp");
    return;
}
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account</title>
        <link rel="stylesheet" href="css/admin.css">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.0.18/dist/sweetalert2.all.min.js"></script>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
        <script>
            function sortTable(columnIndex) {
                let table = document.querySelector(".customer-table tbody");
                let rows = Array.from(table.rows);
                let ascending = table.getAttribute("data-sort") !== "asc";

                rows.sort((rowA, rowB) => {
                    let cellA = rowA.cells[columnIndex].innerText.trim().toLowerCase();
                    let cellB = rowB.cells[columnIndex].innerText.trim().toLowerCase();
                    return ascending ? cellA.localeCompare(cellB) : cellB.localeCompare(cellA);
                });

                table.innerHTML = "";
                rows.forEach(row => table.appendChild(row));
                table.setAttribute("data-sort", ascending ? "asc" : "desc");

                let header = document.querySelectorAll(".sortable span.sort-icon")[columnIndex - 3];
                header.innerHTML = ascending ? "▲" : "▼";
            }

            function confirmBlock(link) {
                event.preventDefault();
                Swal.fire({
                    title: "Are you sure?",
                    text: "Do you want to block this account?",
                    icon: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#d33",
                    cancelButtonColor: "#3085d6",
                    confirmButtonText: "Yes, block it!"
                }).then((result) => {
                    if (result.isConfirmed) {
                        window.location.href = link.href;
                    }
                });
            }

            function confirmUnblock(link) {
                event.preventDefault();
                Swal.fire({
                    title: "Are you sure?",
                    text: "Do you want to unblock this account?",
                    icon: "info",
                    showCancelButton: true,
                    confirmButtonColor: "#28a745",
                    cancelButtonColor: "#3085d6",
                    confirmButtonText: "Yes, unblock it!"
                }).then((result) => {
                    if (result.isConfirmed) {
                        window.location.href = link.href;
                    }
                });
            }
        </script>
        <style>
            table {
                width: 100%;
                border-collapse: collapse;
            }
            th, td {
                padding: 8px;
                text-align: left;
                border-bottom: 1px solid #ddd;
            }
            .status-active {
                color: green;
                font-weight: bold;
            }
            .status-blocked {
                color: red;
                font-weight: bold;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <jsp:include page="sidebar.jsp" />
            <main>
                <div class="top-bar">
                    <h1>Account List</h1>
                    <div class="search-container">
                        <form action="accounts" method="GET">
                            <input type="text" id="searchInput" placeholder="Search customers..." name="search"/>
                            <button class="btn-search" type="submit">Search</button>
                        </form>
                    </div>
                </div>



                <table class="customer-table">
                    <thead>
                        <tr>
                            <th>Account Id</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th class="sortable" onclick="sortTable(3)">
                                Role <span class="sort-icon">▲▼</span>
                            </th>
                            <th>Address</th>
                            <th>Phone</th>
                            <th class="sortable" onclick="sortTable(6)">
                                Status <span class="sort-icon">▲▼</span>
                            </th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.listA}" var="a">
                            <tr>
                                <td>${a.id}</td>
                                <td>${a.fullname}</td>
                                <td>${a.email}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${a.role == 'Admin'}">Admin</c:when>
                                        <c:when test="${a.role == 'Staff'}">Staff</c:when>
                                        <c:otherwise>Customer</c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${a.address}</td>
                                <td>${a.phoneNumber}</td>
                                <td class="${a.status == 'Active' ? 'status-active' : 'status-blocked'}">${a.status}</td>
                                <td>
                                    <a href="editAccount.jsp?id=${a.id}" class="btn btn-success btn-sm">Edit</a>

                                    <form action="blockUser" method="post" style="display:inline;">
                                        <input type="hidden" name="id" value="${a.id}">
                                        <c:choose>
                                            <c:when test="${a.status == 'Active'}">
                                                <!-- Nút BLOCK -->
                                                <input type="hidden" name="action" value="block">
                                                <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to block this user?')">Block</button>
                                            </c:when>
                                            <c:when test="${a.status == 'Suspend'}">
                                                <!-- Nút UNBLOCK -->
                                                <input type="hidden" name="action" value="unblock">
                                                <button type="submit" class="btn btn-success btn-sm" onclick="return confirm('Are you sure you want to unblock this user?')">Unblock</button>
                                            </c:when>   
                                        </c:choose>
                                    </form>
                                </td>

                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </main>
        </div>
    </body>
</html>
