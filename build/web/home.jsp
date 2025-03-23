<%-- 
    Document   : home
    Created on : Jan 13, 2025, 1:53:31 PM
    Author     : ADMIN
--%>
<%@page import="model.Account"%> 
<%@page import="dal.MovieDAO"%>
<%@page import="model.Movie"%>
<%@page import="dal.DiscountDAO"%>
<%@page import="model.Discount" %>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>CBD Cinemas - Trang Chủ</title>
        <style>
            /* Các style CSS */
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f8f8f8;
            }
            .logo img {
                height: 50px;
            }
            nav ul {
                list-style: none;
                margin: 0;
                padding: 0;
                display: flex;
            }
            nav ul li {
                margin: 0 15px;
            }
            nav ul li a {
                color: white;
                text-decoration: none;
                font-weight: bold;
            }
            .cinema-info {
                text-align: center;
                padding: 20px;
                background-image: url('image/cm1.jpg');
                background-size: cover;
                background-position: center;
                background-repeat: no-repeat;
                color: white;
                border-radius: 10px;
                box-shadow: 0 5px 10px rgba(0, 0, 0, 0.1);
                margin: 20px auto;
                width: 80%;
                max-width: 1200px;
                position: relative;
            }
            .cinema-info h2 {
                color: #d32f2f;
            }
            .btn1 {
                display: inline-block;
                background-color: #d32f2f;
                color: white;
                padding: 10px 20px;
                margin-top: 10px;
                border: none;
                cursor: pointer;
                border-radius: 5px;
                text-decoration: none;
            }
            .btn1:hover {
                background-color: #b71c1c;
            }
            .slider img {
                width: 100%;
                height: auto;
            }
            .movie-selection {
                text-align: center;
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
                width: calc(33.33% - 20px);
                text-align: center;
                padding: 15px;
                transition: transform 0.3s ease;
            }
            .movie:hover {
                transform: scale(1.05);
            }
            .movie img {
                width: 100%;
                border-radius: 10px;
                height: 450px
            }
            .movie h3 {
                margin: 10px 0;
                font-size: 1.2em;
            }
            .movie p {
                margin: 5px 0;
                font-size: 0.9em;
                color: #555;
            }

            .events-title {
                text-align: center;
                font-size: 2em;
                margin-bottom: 20px;
            }

            .event-container {
                display: flex;
                justify-content: space-between;
                gap: 20px;
                width: 100%;
                max-width: 1200px;
                margin: 0 auto;
                padding: 20px;
            }

            .event {
                background-color: #f5f5f5;
                border-radius: 10px;
                padding: 15px;
                width: 50%;
                text-align: center;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                transition: transform 0.3s ease;
                text-decoration: none;
                color: black;
            }

            .event:hover {
                transform: scale(1.05);
            }

            .event img {
                width: 100%;
                height: auto;
                border-radius: 10px;
            }

            .event p {
                margin-top: 10px;
                font-weight: bold;
                color: #d32f2f;
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp" />

        <%-- Kiểm tra xem người dùng đã đăng nhập hay chưa --%>
        <%
            // Kiểm tra session đã tồn tại hoặc chưa
            Account account = (Account) session.getAttribute("account"); // Không khai báo lại session, sử dụng session có sẵn
        %>

        <% if (account != null) { %>
            <!-- Hiển thị phần chào mừng người dùng đã đăng nhập -->
            <div class="cinema-info">
                <h2>Chào mừng, <%= account.getFullname() %> đến với CBD Cinema!</h2>
                <p>Trải nghiệm những bộ phim mới nhất với chất lượng âm thanh và hình ảnh tuyệt đỉnh.</p>
                <a href="movies.jsp" class="btn1">Xem Phim Ngay</a>
            </div>

        <% } else { %>
            <!-- Hiển thị phần nếu người dùng chưa đăng nhập -->
            <div class="cinema-info">
                <h2>Chào mừng đến với CBD Cinema!</h2>
                <p>Trải nghiệm những bộ phim mới nhất với chất lượng âm thanh và hình ảnh tuyệt đỉnh.</p>
                <a href="movies.jsp" class="btn1">Xem Phim Ngay</a>
            </div>


        <% } %>

        <section class="slider">
            <jsp:include page="slider.jsp" />
        </section>

        <section class="movie-selection">
            <h2>Phim đang chiếu</h2>
            <div class="movie-list">
                <div class="movie-row">
                    <%
                        MovieDAO movieDAO = new MovieDAO();
                        List<Movie> movies = movieDAO.getHomeMovies();
                        int count = 0;
                        for (Movie movie : movies) {
                            if (count >= 6) {
                                break; 
                            }
                            if (count % 3 == 0 && count != 0 ) {
                                out.println("</div><div class='movie-row'>");
                            }
                    %>
                    <div class="movie">
                        <img src="image/<%= movie.getPoster() %>" alt="<%= movie.getTitle() %>">
                        <h3><%= movie.getTitle() %></h3>
                        <p>Thời lượng: <%= movie.getDuration() %> phút</p>
                        <a href="movie?id=<%= movie.getMovieID() %>" class="btn1">Thông tin</a>
                        <a href="showtime?id=<%= movie.getMovieID() %>" class="btn1">Đặt Vé</a>
                    </div>
                    <%
                            count++;
                        }
                    %>
                </div>
            </div>
        </section>

        <section class="events">
            <h2 class="events-title">Khuyến mãi</h2>
            <div class="event-container">
                <%
            
                    DiscountDAO discountDAO = new DiscountDAO();
                    List<Discount> discounts = discountDAO.getAllDiscounts();
                    int count1 = 0;
                    for (Discount discount : discounts) {
                    if (count1 >= 2) {
                                        break; 
                                    }
            
                %>
                <a href="discount?id=<%= discount.getDiscountID() %>" class="event">
                    <img src="image/<%= discount.getImageURL() %>" alt="Khuyến Mãi <%= discount.getDiscountID() %>">
                </a>
                <%
                    count1++;
                    }
                %>
            </div>
        </section>

        <jsp:include page="footer.jsp" />
    </body>
</html>
