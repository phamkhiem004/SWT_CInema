<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Account" %> <!-- Thêm import cho lớp Account -->

<!DOCTYPE html>
<html lang="en">
    <style>
        header {
            background-color: #d32f2f;
            padding: 15px;
            display: flex;
            align-items: center;
            justify-content: space-between;
        }
        .topbar {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .logo {
            font-size: 24px;
            font-weight: bold;
        }
        .right-links a {
            color: #fff;
            text-decoration: none;
            margin-left: 10px;
        }
        nav ul {
            list-style: none;
            display: flex;
            padding: 10px 0;
        }
        nav ul li {
            margin-right: 20px;
        }
        nav ul li a {
            text-decoration: none;
            color: #fff;
            font-weight: bold;
        }
        .btn {
            padding: 10px 15px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-size: 0.9em;
            transition: background-color 0.3s ease;
        }
        .btn:hover {
            background-color: #0056b3;
        }
    </style>

    <header>
        <div class="topbar">
            <div class="logo">🎬 Cinema</div>
            <div class="right-links">
                <%-- Kiểm tra xem người dùng đã đăng nhập hay chưa --%>
                <%
                    Account account = (Account) session.getAttribute("account");
                %>

                <% if (account != null) { %>
                <%-- Hiển thị nút Đăng xuất --%>
                <span style="color: #fff;"><%= account.getFullname() %></span> 
                <a href="logout" class="btn">Đăng xuất</a>
                <% } else { %>
                <!-- Nếu người dùng chưa đăng nhập, hiển thị nút Đăng nhập và Đăng ký -->
                <a href="login.jsp">Đăng nhập</a> | 
                <a href="register.jsp">Đăng ký</a>
                <% } %>
            </div>
        </div>
        <nav>
            <ul>
                <li><a href="home.jsp">Trang Chủ</a></li>
                <li><a href="movies.jsp">Phim Đang Chiếu</a></li>
                <li><a href="upcomingmovies.jsp">Phim Sắp Chiếu</a></li>
                <li><a href="theaterfilter">Rạp Chiếu</a></li>
                <li><a href="discounts.jsp">Khuyến Mãi</a></li>
                <li><a href="combos.jsp">Combo</a></li>
                <li><a href="viewprofile">Tài Khoản</a></li>

            </ul>
        </nav>
    </header>
</html>
