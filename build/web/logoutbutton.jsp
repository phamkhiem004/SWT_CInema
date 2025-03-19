<%-- 
    Document   : logout_button
    Created on : Feb 15, 2025, 3:27:46 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Account"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%
    // Lấy thông tin phiên làm việc (session) của người dùng
    HttpSession session = request.getSession();

    // Kiểm tra xem người dùng đã đăng nhập chưa
    Account account = (Account) session.getAttribute("account");
    if (account != null) {
        // Hiển thị nút đăng xuất nếu người dùng đã đăng nhập
%>
    <a href="logout.jsp" class="btn">Đăng Xuất</a>
<%
    }
%>

