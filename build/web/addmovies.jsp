<%-- 
    Document   : addmovies
    Created on : Feb 18, 2025, 1:33:38 PM
    Author     : Acer Predator
--%>
<%@page import="dal.AccountDAO"%>
<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Account account = (Account) session.getAttribute("account");

    if (account == null || (!"Admin".equals(account.getRole()) && !"Staff".equals(account.getRole()))) {
    response.sendRedirect("home.jsp");
    return;
}
%>


<%
    String errorMessage = (String) request.getAttribute("errorMessage");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Thêm phim mới</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                margin: 0;

                text-align: center;
            }
            .form-container {
                background-color: #fff;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                max-width: 500px;
                margin: 0 auto;
                text-align: left;
            }
            .form-container label {
                display: block;
                margin-top: 10px;
            }
            .form-container input, .form-container textarea, .form-container select {
                width: 100%;
                padding: 8px;
                margin-top: 5px;
                border: 1px solid #ccc;
                border-radius: 5px;
            }
            .form-container button {
                margin-top: 15px;
                padding: 10px;
                background-color: #007bff;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }
            .form-container button:hover {
                background-color: #0056b3;
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <div class="form-container">
            <h2>Thêm phim mới</h2>
            <p style="color:red;"><%= errorMessage != null ? errorMessage : "" %></p>

            <form action="AddMovieServlet" method="post">
                <label>Tiêu đề:</label>
                <input type="text" name="Title" required>

                <label>Thể loại:</label>
                <input type="text" name="Genre" required>

                <label>Tóm tắt:</label>
                <textarea name="Summary" required></textarea>

                <label>Thời lượng (phút):</label>
                <input type="number" name="Duration" required>

                <label>Ngày phát hành:</label>
                <input type="date" name="ReleaseDate" required>

                <label>Trailer URL:</label>
                <input type="text" name="Trailer">

                <label>Poster:</label>
                <input type="text" name="Poster">

                <label>Trạng thái:</label>
                <select name="TrangThai">
                    <option value="Đang chiếu">Đang chiếu</option>
                    <option value="Sắp chiếu">Sắp chiếu</option>
                </select>

                <button type="submit">Thêm phim</button>
            </form>
        </div>
        <jsp:include page="footer.jsp" />
    </body>
</html>
