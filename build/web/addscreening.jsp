

<%-- 
    Document   : addScreening
    Created on : Feb 23, 2025, 5:43:42 PM
    Author     : Acer Predator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*, java.util.*, model.*" %>
<%@page import="dal.MovieDAO, dal.CinemaDAO, dal.RoomDAO" %>
<jsp:useBean id="movieDAO" class="dal.MovieDAO" scope="page"/>
<jsp:useBean id="cinemaDAO" class="dal.CinemaDAO" scope="page"/>
<jsp:useBean id="roomDAO" class="dal.RoomDAO" scope="page"/>
<%@page import="dal.AccountDAO"%>
<%@page import="model.Account"%>
<%
    Account account = (Account) session.getAttribute("account");

    if (account == null || (!"Admin".equals(account.getRole()) && !"Staff".equals(account.getRole()))) {
    response.sendRedirect("home.jsp");
    return;
}
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Thêm Suất Chiếu</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f4f4f4;
            }

            .container {
                width: 40%;
                margin: 50px auto;
                background: white;
                padding: 30px;
                border-radius: 10px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            }

            h2 {
                text-align: center;
                color: #333;
                font-size: 24px;
                margin-bottom: 20px;
            }

            label {
                font-weight: bold;
                display: block;
                margin-top: 15px;
                font-size: 16px;
            }

            select, input {
                width: calc(100% - 16px);
                padding: 10px;
                margin-top: 5px;
                border: 1px solid #ccc;
                border-radius: 5px;
                font-size: 14px;
            }

            button {
                width: 100%;
                padding: 12px;
                background-color: #d32f2f; /* Màu đỏ giống header */
                color: white;
                font-size: 16px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                margin-top: 20px;
                transition: background-color 0.3s;
            }

            button:hover {
                background-color: #b71c1c;
            }

            .readonly-input {
                background-color: #e9ecef;
                cursor: not-allowed;
            }

        </style>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <h2>Thêm Suất Chiếu</h2>
        <form action="AddShowtimeServlet" method="post">
            <label for="movie">Chọn Phim:</label>
            <select name="movieID" id="movie" required>
                <option value="">-- Chọn phim --</option>
                <%
                    List<Movie> movies = movieDAO.getNowShowingMovies();
                    for (Movie m : movies) {
                %>
                <option value="<%= m.getMovieID() %>"><%= m.getTitle() %></option>
                <% } %>
            </select>

            <label for="cinema">Chọn Rạp:</label>
            <select name="cinemaID" id="cinema" required onchange="loadRooms(this.value)">
                <option value="">-- Chọn rạp --</option>
                <%
                    List<Cinema> cinemas = cinemaDAO.getAllCinemas();
                    for (Cinema c : cinemas) {
                %>
                <option value="<%= c.getCinemaID() %>"><%= c.getName() %></option>
                <% } %>
            </select>

            <label for="room">Chọn Phòng:</label>
            <select name="roomID" id="room" required>
                <option value="">-- Chọn phòng --</option>
            </select>

            <label for="startTime">Thời gian chiếu:</label>
            <input type="datetime-local" name="startTime" id="startTime" required>
            <label for="status">Trạng thái:</label>
            <input type="text" name="status" id="status" value="Active" readonly class="readonly-input">
            <button type="submit">Thêm Suất Chiếu</button>
        </form>

        <script>
            function loadRooms(cinemaID) {
                fetch('GetRoomServlet?cinemaID=' + cinemaID)
                        .then(response => response.json())
                        .then(data => {
                            let roomSelect = document.getElementById('room');
                            roomSelect.innerHTML = '<option value="">-- Chọn phòng --</option>';
                            data.forEach(room => {
                                let option = document.createElement('option');
                                option.value = room.roomID;
                                option.textContent = room.name + " (Sức chứa: " + room.seatCapacity + ")";
                                roomSelect.appendChild(option);
                            });
                        })
                        .catch(error => console.error('Lỗi:', error));
            }
        </script>

        <script>
            document.querySelector("form").addEventListener("submit", function (event) {
                let startTimeInput = document.getElementById("startTime").value;
                let selectedTime = new Date(startTimeInput);
                let currentTime = new Date();

                if (selectedTime < currentTime) {
                    alert("Thời gian chiếu không thể nhỏ hơn thời gian hiện tại. Vui lòng chọn lại!");
                    event.preventDefault(); // Ngăn form submit
                }
            });
        </script>

        <jsp:include page="footer.jsp" />
    </body>
</html>


