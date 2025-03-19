<%-- 
    Document   : slider
    Created on : Feb 10, 2025, 5:34:56 PM
    Author     : Acer Predator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f8f8f8;
        }
        .slider {
            width: 100%;
            overflow: hidden;
            position: relative;
            border-radius: 10px;
        }
        .slider-container {
            display: flex;
            transition: transform 0.5s ease-in-out;
        }
        .slider img {
            width: 100%;
            height: auto;
            flex-shrink: 0;
        }
        .slider-button {
            position: absolute;
            top: 50%;
            transform: translateY(-50%);
            background-color: rgba(0, 0, 0, 0.5);
            color: white;
            border: none;
            padding: 10px;
            cursor: pointer;
            z-index: 10;
        }
        .slider-button.prev {
            left: 10px;
        }
        .slider-button.next {
            right: 10px;
        }
    </style>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <section class="slider">
            <div class="slider-container">
                <img src="image/banner1.jpg" alt="Banner 1">
                <img src="image/banner2.png" alt="Banner 2">
                <img src="image/banner3.jpg" alt="Banner 3">
            </div>
            <button class="slider-button prev">&#10094;</button>
            <button class="slider-button next">&#10095;</button>
        </section>
        
    </body>
</html>
