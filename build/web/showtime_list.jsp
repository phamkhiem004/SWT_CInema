<%-- 
    Document   : showtime_list
    Created on : Mar 23, 2025, 3:13:19 PM
    Author     : Acer Predator
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, model.Cinema, model.ShowTime" %>
<%@page import="dal.AccountDAO"%>
<%@page import="model.Account"%>
<%
    // Kiểm tra nếu chưa đăng nhập thì chuyển hướng về trang login.jsp
    Account account = (Account) session.getAttribute("account");

    if (account == null ) {
    response.sendRedirect("login.jsp");
    return;
}
%>
<%
    String movieTitle = (String) request.getAttribute("movieTitle"); // Lấy từ request
    if (movieTitle == null) {
        movieTitle = (String) session.getAttribute("movieTitle"); // Thử lấy từ session nếu có
    }
%>
<html>
    <head>
        <title>Danh sách suất chiếu</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f8f8f8;
            }
            .container {
                width: 80%;
                max-width: 800px;
                background: white;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                margin-top: 20px;
            }
            h2 {
                text-align: center;
                color: #2c3e50;
            }
            .cinema-box {
                margin-bottom: 20px;
                padding: 15px;
                border-radius: 8px;
                background: #ecf0f1;
            }
            .cinema-name {
                font-size: 18px;
                font-weight: bold;
                color: #34495e;
                margin-bottom: 10px;
            }
            .showtime-container {
                display: flex;
                flex-wrap: wrap;
                gap: 10px;
            }
            .showtime-btn {
                padding: 8px 15px;
                background: #2c3e50;
                color: white;
                border: none;
                cursor: pointer;
                border-radius: 5px;
                font-size: 14px;
                transition: 0.3s;
            }
            .showtime-btn:hover {
                background: #3498db;
            }
            .no-cinema {
                text-align: center;
                color: red;
                font-size: 20px;
                font-weight: bold;
                margin-top: 20px;
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <h2>Danh sách rạp & suất chiếu</h2>
        <%
            List<Cinema> cinemas = (List<Cinema>) request.getAttribute("cinemas");
            if (cinemas != null && !cinemas.isEmpty()) {
                for (Cinema cinema : cinemas) {
                    out.println("<div class='cinema-box'>");
                    out.println("<h3>" + cinema.getName() + "</h3>");

                    List<ShowTime> showTimes = (List<ShowTime>) request.getAttribute("showTimes_" + cinema.getCinemaID());
                    if (showTimes != null && !showTimes.isEmpty()) {
                        for (ShowTime show : showTimes) {
                            out.println("<span class='showtime-btn'>" + show.getStartTime().toString().replace("T", " ") + "</span>");%>
                            <button class="btn btn-success"><a href="booking?showtimeID=<%= show.getShowTimeID() %>">Đặt Vé</a></button>
                        <%}
                    } else {
                        out.println("<p class='no-cinema'>Không có suất chiếu nào.</p>");
                    }
                    out.println("</div>");
                }
            } else {
                out.println("<p class='no-cinema'>Không tìm thấy rạp nào đang chiếu phim này.</p>");
            }
        %>
        <jsp:include page="footer.jsp" />
    </body>
</html>
