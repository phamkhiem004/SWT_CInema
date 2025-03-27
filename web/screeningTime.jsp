<%-- 
    Document   : screeningTime
    Created on : Mar 26, 2025, 12:29:59 AM
    Author     : Acer Predator
--%>
<%@page import="dal.AccountDAO"%>
<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    Account account = (Account) session.getAttribute("account");
    if (account == null || (!"Admin".equals(account.getRole()) && !"Staff".equals(account.getRole()))) {
        response.sendRedirect("home.jsp");
        return;
    }
    String errorMessage = (String) request.getAttribute("errorMessage");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Screening Time Management</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                background-color: #f9f6ef;
                padding: 0px;
            }
            table {
                width: 100%;
                border-collapse: collapse;
            }
            th, td {
                border: 1px solid #ddd;
                padding: 8px;
                text-align: center;
            }
            th {
                background-color: #4CAF50;
                color: white;
            }
            .deactivate-btn {
                background-color: red;
                color: white;
                border: none;
                padding: 5px 10px;
                cursor: pointer;
            }
            .deactivate-btn:hover {
                background-color: darkred;
            }
            .add-btn {
                background-color: #007BFF;
                color: white;
                border: none;
                padding: 10px 15px;
                cursor: pointer;
                text-decoration: none;
                display: inline-block;
                font-size: 14px;
                margin-left: 20px;
                border-radius: 5px;
            }
            .add-btn:hover {
                background-color: #0056b3;
            }
            .header-container {
                display: flex;
                align-items: center;
                justify-content: space-between;
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <div class="header-container">
            <h2>Screening Time List</h2>
            <a href="addScreening.jsp" class="add-btn"> Thêm Giờ Chiếu</a>
        </div>

        <table>
            <thead>
                <tr>
                    <th>ShowTime ID</th>
                    <th>Movie ID</th>
                    <th>Cinema ID</th>
                    <th>Room ID</th>
                    <th>Start Time</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="showtime" items="${screeningList}">
                    <tr>
                        <td>${showtime.showTimeID}</td>
                        <td>${showtime.movieID}</td>
                        <td>${showtime.cinemaID}</td>
                        <td>${showtime.roomID}</td>
                        <td>${showtime.startTime}</td>
                        <td>${showtime.status}</td>
                        <td>
                            <c:if test="${showtime.status == 'Active'}">
                                <form action="ShowTimeServlet" method="post" 
                                      onsubmit="return confirm('Bạn có muốn bỏ giờ chiếu này không?');">
                                    <input type="hidden" name="showTimeID" value="${showtime.showTimeID}">
                                    <button type="submit" name="action" value="deactivate" class="deactivate-btn">
                                        Deactivate
                                    </button>
                                </form>
                            </c:if>

                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <jsp:include page="footer.jsp" />
    </body>
</html>


