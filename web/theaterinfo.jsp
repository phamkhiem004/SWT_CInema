<%-- 
    Document   : theaterinfo
    Created on : Feb 22, 2025, 2:50:06 PM
    Author     : Acer Predator
--%>
<%@ page import="model.ShowTime" %>
<%@page import="dal.CinemaDAO"%>
<%@ page import="model.Cinema" %>

<%@page import="dal.MovieDAO"%>
<%@page import="model.Movie"%>
<%@page import="dal.AccountDAO"%>
<%@page import="model.Account"%>
<%@ page import="java.util.*, java.time.format.DateTimeFormatter" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thông tin rạp</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                background-color: #f9f6ef;
                padding: 0px;
            }

            h1 {
                color: #333;
                text-align: center;
                font-size: 32px;
                margin-bottom: 10px;
            }

            p {
                text-align: center;
                font-size: 18px;
                color: #555;
            }

            .theater-image {
                display: block;
                margin: 0 auto;
                width: 80%;
                max-width: 1000px;
                border-radius: 10px;
                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
            }

            .movie {
                display: flex;
                justify-content: space-between;
                align-items: flex-start;
                border-bottom: 1px solid #ccc;
                padding-bottom: 20px;
                margin-bottom: 20px;
            }
            .movie-details {
                flex: 1;
                margin-right: 20px;
            }
            .movie-title {
                font-size: 20px;
                font-weight: bold;
                background-color: #f4f4f4;
                padding: 10px;
                border-radius: 4px;
            }
            .screening-times {
                margin-top: 10px;
            }
            .screening-time {
                display: inline-block;
                padding: 5px 10px;
                margin: 5px 5px 0 0;
                background-color: #eef;
                border-radius: 4px;
                color: #333;
                font-weight: bold;
            }
            .screening-header {
                text-align: center;
                font-size: 24px;
                font-weight: bold;
                margin: 20px 0;
                color: #444;
            }
            .movie img {
                width: 150px;
                height: auto;
                border-radius: 8px;
                box-shadow: 0 2px 8px rgba(0,0,0,0.1);
            }
            hr {
                border: 0;
                height: 1px;
                background: #ccc;
            }
            .button-container {
                text-align: center;
                margin-top: 20px;
            }

            .back-link {
                padding: 10px 15px;
                background-color: #007bff;
                color: white;
                text-decoration: none;
                border-radius: 5px;
                font-size: 0.9em;
                transition: background-color 0.3s ease;
            }

            .back-link:hover {
                background-color: #0056b3;
            }

        </style>


    </head>
    <body>
        <jsp:include page="header.jsp" />
        <%
                           Account account = (Account) session.getAttribute("account");
        %>

        <%
            Cinema cinema = (Cinema) request.getAttribute("Cinema");
            Map<Movie, List<ShowTime>> movieShowtimes = (Map<Movie, List<ShowTime>>) request.getAttribute("movieShowtimes");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        %>


        <div class="header-container">
            <h1><%= cinema.getName() %></h1>

            <% if (account != null && ("Admin".equals(account.getRole()) || "Staff".equals(account.getRole()))) { %>
            <a href="ShowTimeServlet" class="back-link">Quản lý giờ chiếu</a>
            <% } %>
        </div>

        <p>Địa chỉ: <%= cinema.getAddress() %></p>
        <img class="theater-image" src="image/<%= cinema.getImageURL() %>" alt="<%= cinema.getName() %>">

        <hr/>
        <h2 class="screening-header">Lịch Chiếu</h2>

        <%
            for (Map.Entry<Movie, List<ShowTime>> entry : movieShowtimes.entrySet()) {
                Movie movie = entry.getKey();
                List<ShowTime> showtimes = entry.getValue();
        %>

        <div class="movie">                                    
            <div class="movie-poster">
                <div class="movie-title"><%= movie.getTitle() %></div>
                <img src="image/<%= movie.getPoster() %>" alt="<%= movie.getTitle() %>">

            </div>


            <div class="screening-details">

                <div class="screening-times">
                    <%                                                                                                         
                        for (ShowTime showtime :showtimes) {
                    %>
                    <span class="screening-time">
                        Giờ Chiếu : <%= showtime.getStartTime().format(formatter) %>

                    </span>

                    <%
                            }
                        
                    %>
                </div>
            </div>



        </div>
        <hr/>

        <%
            }
        %>

        <div class="button-container">
            <a href="theaterfilter" class="back-link">Quay về danh sách rạp</a>        
        </div>
        <jsp:include page="footer.jsp" />
    </body>
</html>
