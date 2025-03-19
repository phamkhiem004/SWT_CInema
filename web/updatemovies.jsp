<%-- 
    Document   : updatemovies
    Created on : Feb 18, 2025, 9:28:38 PM
    Author     : Acer Predator
--%>
<%@page import="dal.MovieDAO"%>
<%@page import="model.Movie"%>
<%@page import="dal.AccountDAO"%>
<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Account account = (Account) session.getAttribute("account");

    if (account == null || (!"Admin".equals(account.getRole()) && !"Staff".equals(account.getRole()))) {
    response.sendRedirect("home.jsp");
    return;
}
  String movieIDParam = request.getParameter("id");
    int movieID = (movieIDParam != null) ? Integer.parseInt(movieIDParam) : 0;

    MovieDAO movieDAO = new MovieDAO();
    Movie movie = movieDAO.getMoviesByID(movieID);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Cập nhật thông tin phim</title>
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
            <h2>Cập nhật thông tin phim</h2>
            <form action="UpdateMovieServlet" method="post">
                <input type="hidden" name="movieID" value="<%= movie.getMovieID() %>">
                <label>Tiêu đề:</label>
                <input type="text" name="title" value="<%= movie.getTitle() %>" required>

                <label>Thể loại:</label>
                <input type="text" name="genre" value="<%= movie.getGenre() %>" required>

                <label>Tóm tắt:</label>
                <textarea name="summary" required><%= movie.getDescription() %></textarea>

                <label>Thời lượng (phút):</label>
                <input type="number" name="duration" value="<%= movie.getDuration() %>" required>

                <label>Ngày phát hành:</label>
                <input type="date" name="releaseDate" value="<%= movie.getReleaseDate() %>" required>

                <label>Trailer URL:</label>
                <input type="text" name="trailerURL" value="<%= movie.getTrailer() %>" required>

                <label>Image URL:</label>
                <input type="text" name="imageURL" value="<%= movie.getPoster() %>" required>

                <label>Trạng thái:</label>
                <select name="status">
                    <option value="Đang chiếu" <%= "Đang chiếu".equals(movie.getStatus()) ? "selected" : "" %>>Đang chiếu</option>
                    <option value="Sắp chiếu" <%= "Sắp chiếu".equals(movie.getStatus()) ? "selected" : "" %>>Sắp chiếu</option>
                </select>

                <button type="submit">Cập nhật</button>
            </form>
        </div>
        <jsp:include page="footer.jsp" />
    </body>
</html>

