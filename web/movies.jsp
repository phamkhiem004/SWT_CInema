<%-- 
    Document   : home
    Created on : Jan 13, 2025, 1:53:31 PM
    Author     : ADMIN
--%>
<%@page import="dal.MovieDAO"%>
<%@page import="model.Movie"%>
<%@page import="dal.AccountDAO"%>
<%@page import="model.Account"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Phim Đang Chiếu - CBD Cinemas</title>
    </head>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f8f8f8;
        }
        .movie-list {
            display: flex;
            flex-direction: column;
            align-items: center;
            gap: 20px;
            padding: 20px;
        }
        .movie-row {
            display: flex;
            justify-content: center;
            gap: 20px;
            width: 100%;
            max-width: 1200px;
        }
        .movie {
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            width: 30%; /* Điều chỉnh chiều rộng của mỗi phim */
            text-align: center;
            padding: 0; /* Loại bỏ padding để hình ảnh chiếm toàn bộ khung */
            position: relative; /* Cho phép định vị các nút bên trong */
            overflow: hidden; /* Đảm bảo hình ảnh không tràn ra ngoài */
        }
        .movie:hover {
            transform: scale(1.05);
        }
        .movie img {
            width: 100%;
            height: 500px; /* Đặt chiều cao cố định cho hình ảnh */
            object-fit: cover; /* Đảm bảo hình ảnh không bị méo */
            border-radius: 10px;
            display: block; /* Loại bỏ khoảng trắng dưới hình ảnh */
        }
        .movie .overlay {
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            background: rgba(0, 0, 0, 0.5); /* Lớp phủ màu đen với độ trong suốt */
            display: flex;
            justify-content: center;
            align-items: center;
            opacity: 0; /* Ẩn lớp phủ ban đầu */
            transition: opacity 0.3s ease;
        }
        .movie:hover .overlay {
            opacity: 1; /* Hiển thị lớp phủ khi di chuột qua */
        }
        .movie .btn-container {
            display: flex;
            gap: 10px;
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
        .movie-list h2 {
            margin: 0; /* Loại bỏ margin mặc định của thẻ h2 */
        }

        .add-movie-btn {
            padding: 10px 15px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-size: 0.9em;
            transition: background-color 0.3s ease;
        }

        .add-movie-btn:hover {
            background-color: #0056b3;
        }

    </style>

    <body>
        <!--header.jsp -->
        <jsp:include page="header.jsp" />

        <div class="movie-list">
            <h2>PHIM ĐANG CHIẾU</h2>
            <%
                    Account account = (Account) session.getAttribute("account");
            %>
            <% if (account != null && ("Admin".equals(account.getRole()) || "Staff".equals(account.getRole()))) { %>
            <a href="addmovies.jsp" class="add-movie-btn">Thêm phim</a>
            <% } %>


            <%
                MovieDAO movieDAO = new MovieDAO();
                List<Movie> movies = movieDAO.getNowShowingMovies();
                int count = 0;
                for (Movie movie : movies) {
                    if (count % 3 == 0) {
                        if (count != 0) {
                            out.println("</div>");
                        }
                        out.println("<div class='movie-row'>");
                    }
            %>
            <div class="movie">
                <img src="image/<%= movie.getPoster() %>" alt="<%= movie.getTitle() %>">
                <div class="overlay">
                    <div class="btn-container">
                        <a href="movie?id=<%= movie.getMovieID() %>" class="btn">Thông tin</a>
                        <a href="showtime?id=<%= movie.getMovieID() %>" class="btn">Đặt vé</a>
                    </div>

                </div>
                <h3><%= movie.getTitle() %></h3>
            </div>
            <%
                    count++;
                }
                if (count % 3 != 0) {
                    out.println("</div>");
                }
            %>
        </div>

        <!--footer.jsp -->
        <jsp:include page="footer.jsp" />
    </body>
</html>