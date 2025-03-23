<%-- 
    Document   : slider
    Created on : Feb 10, 2025, 5:34:56 PM
    Author     : Acer Predator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Slider JSP</title>
        <link rel="stylesheet" type="text/css" href="css/stylesheet.css">

    </head>
    <body>
        <div id="carouselExample" class="carousel slide" data-bs-ride="carousel">
            <!-- Danh sách ảnh -->
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img src="image/banner1.jpg" class="d-block w-100" alt="Slide 1">
                </div>
                <div class="carousel-item">
                    <img src="image/banner2.png" class="d-block w-100" alt="Slide 2">
                </div>
                <div class="carousel-item">
                    <img src="image/banner3.jpg" class="d-block w-100" alt="Slide 3">
                </div>
            </div>

            <!-- Nút điều hướng -->
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExample" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselExample" data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
            </button>
        </div>

        <!-- Nhúng Bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

    </body>
</html>

