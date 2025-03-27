<%-- 
    Document   : sidebar
    Created on : Mar 24, 2025, 10:03:52 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <aside>
            <div class="top">
                <div class="logo">
                    <h2><span class="danger">CBDCinema</span></h2>
                </div>
                <div class="close">
                    <span class="material-symbols-outlined"> close </span>
                </div>
            </div>
            <div class="sidebar">
                  

                <a href="accounts" class="link">
                    <span class="material-symbols-outlined"> person </span>
                    <h3>Account</h3>
                </a>
                <a href="billing" class="link">
                    <span class="material-symbols-outlined"> receipt_long </span>
                    <h3>Billing</h3>
                </a>
                <a href="home.jsp" class="link">
                    <span class="material-symbols-outlined"> home </span>
                    <h3>Vào Trang chủ</h3>
                </a>
                <a href="logout" class="link">
                    <span class="material-symbols-outlined"> logout </span>
                    <h3>Log out</h3>
                </a>
            </div>
        </aside>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                let currentPath = window.location.pathname;
                let links = document.getElementsByClassName("link");

                Array.from(links).forEach(link => {
                    if (currentPath.includes(link.getAttribute("href"))) {
                        link.classList.add("active");
                    } else {
                        link.classList.remove("active");
                    }
                });
            });
        </script>
    </body>
</html>

