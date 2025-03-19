<%-- 
    Document   : logout
    Created on : Feb 15, 2025, 3:28:14 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%
    // Lấy phiên làm việc (session) hiện tại
    HttpSession session = request.getSession();
    
    // Xóa thông tin tài khoản khỏi phiên làm việc
    session.removeAttribute("account");
    
    // Hủy phiên làm việc để hoàn tất việc đăng xuất
    session.invalidate();
    
    // Chuyển hướng về trang chủ hoặc trang khác sau khi đăng xuất
    response.sendRedirect("home.jsp");
%>

