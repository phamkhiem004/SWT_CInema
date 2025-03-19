<%-- 
    Document   : theaters
    Created on : Feb 21, 2025, 4:07:01 PM
    Author     : Acer Predator
--%>
<%@page import="dal.CinemaDAO"%>
<%@page import="model.Cinema"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Danh sách rạp chiếu</title>
    </head>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f8f8f8;
        }
        .theater-list {
            display: flex;
            flex-direction: column;
            align-items: center;
            padding: 20px;
        }

        .theater-row {
            display: grid;
            grid-template-columns: repeat(2, 1fr); /* 4 discount trên mỗi hàng */
            gap: 20px; /* Giảm khoảng cách giữa các discount */
            width: 100%;
            max-width: 1200px;
            margin-bottom: 20px; /* Thêm khoảng cách giữa các dòng */
        }


        .theater {
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            text-align: center;
            padding: 5px;
            position: relative;
            overflow: hidden;
            transition: transform 0.3s ease-in-out;
        }

        .theater:hover {
            transform: scale(1.05);
        }

        .theater img {
            width: 100%;
            height: 300px; /* Điều chỉnh chiều cao hợp lý */
            object-fit: cover;
            border-radius: 10px;
        }

        .theater .overlay {
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            background: rgba(0, 0, 0, 0.5);
            display: flex;
            justify-content: center;
            align-items: center;
            opacity: 0;
            transition: opacity 0.3s ease;
        }

        .theater:hover .overlay {
            opacity: 1;
        }

        .theater .btn-container {
            display: flex;
            gap: 5px;
        }

        .btn {
            padding: 8px 12px;
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


        .theater-list h2 {
            margin: 0;
        }

        /* Form wrapper */
        .filter-form {
            display: flex;
            align-items: center;          
            gap: 10px;
            margin: 20px auto;
            padding: 15px 20px;
            background-color: #f9f9f9;
            border-radius: 10px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
            max-width: 500px;
        }

        /* Label styling */
        .filter-form label {
            font-size: 16px;
            font-weight: 500;
            color: #333;
        }

        /* Dropdown (Select) styling */
        .filter-form select {
            padding: 8px 12px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 6px;
            outline: none;
            transition: border-color 0.3s ease;
        }

        .filter-form select:focus {
            border-color: #007BFF;
        }

        /* Button styling */
        .filter-form .btn {
            background-color: #007BFF;
            color: white;
            border: none;
            padding: 10px 16px;
            font-size: 16px;
            border-radius: 6px;
            cursor: pointer;
            transition: background-color 0.3s ease, box-shadow 0.3s ease;
        }

        .filter-form .btn:hover {
            background-color: #0056b3;
            box-shadow: 0 4px 10px rgba(0, 123, 255, 0.3);
        }
        



    </style>

    <body>

        <!--header.jsp -->
        <jsp:include page="header.jsp" />

        <div class="theater-list">
            <h2>Rạp chiếu</h2>
            <!-- Filter Form -->
            <form action="theaterfilter" method="get" class="filter-form">
                <label for="location">Chọn địa điểm:</label>
                <select name="location" id="location">
                    <option value="">Tất cả</option>
                    <% 
                        List<String> locations = (List<String>) request.getAttribute("location");      
                        CinemaDAO cinemaDAO = new CinemaDAO();
                        locations = cinemaDAO.getAllLocations();
    
                        String selectedLocation = (String) request.getAttribute("selectedLocation");
                        if (locations != null) {
                        for (String loc : locations) {
                            String selected = loc.equals(selectedLocation) ? "selected" : "";
                    %>
                    <option value="<%= loc %>" <%= selected %>><%= loc %></option>
                    <% }
                    }%>
                </select>
                <button type="submit" class="btn">Lọc</button>
            </form>




            <%
                
                List<Cinema> cinemas = (List<Cinema>) request.getAttribute("cinema");
                int count = 0;
                if (cinemas != null && !cinemas.isEmpty()) {
                for (Cinema cinema : cinemas) {
                    if (count % 2 == 0) {
                        if (count != 0) {
                            out.println("</div>");
                        }
                        out.println("<div class='theater-row'>");
                    }
            %>
            <div class="theater">
                <img src="image/<%= cinema.getImageURL() %>" alt="<%= cinema.getName() %>">
                <div class="overlay">
                    <div class="btn-container">
                        <a href="theater?id=<%= cinema.getCinemaID() %>" class="btn">Thông tin</a>

                    </div>


                </div>
                <h3><%= cinema.getName() %></h3>
            </div>
            <%
                    count++;
                }
                if (count % 2 != 0) {
                    out.println("</div>");
                }
            } else {
            %>
            <p>Không có rạp chiếu nào được tìm thấy.</p>
            <% 
                }
            %>
        </div>

        <!--footer.jsp -->
        <jsp:include page="footer.jsp" />
    </body>
</html>
