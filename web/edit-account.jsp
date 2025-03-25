<%-- 
    Document   : Account
    Created on : Mar 22, 2025, 1:11:58 PM
    Author     : Acer Predator
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account</title>
        <link rel="stylesheet" href="css/admin.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
        <style>
            .form-container {
                background: white;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                width: 100%;
            }

            .form-title {
                text-align: center;
                margin-bottom: 20px;
            }

            .form-group {
                margin-bottom: 15px;
            }

            label {
                display: block;
                margin-bottom: 5px;
                font-weight: bold;
            }

            .form-input, .form-select {
                width: 100%;
                padding: 8px;
                border: 1px solid #ccc;
                border-radius: 4px;
            }

            .form-button {
                width: 100%;
                padding: 10px;
                background: blue;
                color: white;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }

            .form-button:hover {
                background: darkblue;
            }
        </style>
    </head>
    <body>
    <div class="container">
        <jsp:include page="sidebar.jsp" />
        <main>
            <h1>Edit Account</h1>
            <form action="accounts" method="POST">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="id" value="${account.id}">
                
                <label for="username">Name:</label>
                <input type="text" id="username" name="username" value="${account.fullname}" required>
                
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" value="${account.email}" required>
                
                <label for="role">Role:</label>
                <select id="role" name="role">
                    <option value="Admin" ${account.role == 'Admin' ? 'selected' : ''}>Admin</option>
                    <option value="Staff" ${account.role == 'Staff' ? 'selected' : ''}>Staff</option>
                    <option value="Customer" ${account.role == 'Customer' ? 'selected' : ''}>Customer</option>
                </select>
                
                <label for="address">Address:</label>
                <input type="text" id="address" name="address" value="${account.address}">
                
                <label for="phone">Phone:</label>
                <input type="text" id="phone" name="phone" value="${account.phoneNumber}">
                
                <button type="submit" class="btn btn-primary">Update</button>
                <a href="accounts" class="btn btn-secondary">Cancel</a>
            </form>
        </main>
    </div>
</body>
</html>

