<%-- 
    Document   : Account
    Created on : Mar 22, 2025, 1:11:58 PM
    Author     : Acer Predator
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account</title>
        <link rel="stylesheet" href="css/admin.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
        <script>
            function sortTable(columnIndex) {
                let table = document.querySelector(".customer-table tbody");
                let rows = Array.from(table.rows);
                let ascending = table.getAttribute("data-sort") !== "asc"; // Toggle sort order

                rows.sort((rowA, rowB) => {
                    let cellA = rowA.cells[columnIndex].innerText.trim().toLowerCase();
                    let cellB = rowB.cells[columnIndex].innerText.trim().toLowerCase();

                    if (cellA < cellB)
                        return ascending ? -1 : 1;
                    if (cellA > cellB)
                        return ascending ? 1 : -1;
                    return 0;
                });

                table.innerHTML = "";
                rows.forEach(row => table.appendChild(row));

                // Update sort order
                table.setAttribute("data-sort", ascending ? "asc" : "desc");

                // Update triangle icon direction
                let header = document.querySelectorAll(".sortable span.sort-icon")[columnIndex - 3];
                header.innerHTML = ascending ? "▲" : "▼";
            }


        </script>
    </head>
    <body>
    <body>
        <div class="container">
            <aside>
                <div class="top">
                    <div class="logo">
                        <h2>C<span class="danger">ABC</h2>
                    </div>
                    <div class="close">
                        <span class="material-symbols-outlined"> close </span>
                    </div>
                </div>

                <div class="sidebar">
                    <a href="admindashboard.jsp">
                        <span class="material-symbols-outlined"> grid_view </span>
                        <h3>Dashboard</h3>
                    </a>

                    <a href="Account.jsp" class="active">
                        <span class="material-symbols-outlined"> person </span>
                        <h3>Account</h3>
                    </a>

                    <a href="#">
                        <span class="material-symbols-outlined"> movie </span>
                        <h3>Movie</h3>
                    </a>

                    <a href="#">
                        <span class="material-symbols-outlined"> other_houses </span>
                        <h3>Theater</h3>
                    </a>

                    <a href="#">
                        <span class="material-symbols-outlined"> meeting_room </span>
                        <h3>Screening room</h3>
                    </a>

                    <a href="combo">
                        <span class="material-symbols-outlined"> attach_money </span>
                        <h3>Combo</h3>
                    </a>

                    <a href="#">
                        <span class="material-symbols-outlined"> app_promo </span>
                        <h3>Promotion</h3>
                    </a>

                    <a href="#">
                        <span class="material-symbols-outlined"> query_stats </span>
                        <h3>Revenue</h3>
                    </a>

                    <a href="#">
                        <span class="material-symbols-outlined"> logout </span>
                        <h3>Log out</h3>
                    </a>
                </div>
            </aside>
            <main>
                <div class="top-bar">
                    <h1>Account List</h1>
                    <div class="search-container">
                        <form action="seachaccount" method="POST">
                            <input type="text" id="searchInput" placeholder="Search customers..." />
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
                                <td class="${a.status == 'Active' ? 'status-active' : 'status-suspended'}">${a.status}</td>
                                <td>
                                    <a href="account?action=update&id=${a.id}" class="btn btn-success">Edit</a>
                                    <a onclick="deleteWarning(${a.id}, '${a.fullname}')" class="btn btn-danger">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </main>
        </div>
    </body>
</html>

