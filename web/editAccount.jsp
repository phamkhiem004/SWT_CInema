<%@page import="model.Account"%>
<%@page import="dal.AccountDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int userId = Integer.parseInt(request.getParameter("id"));
    AccountDAO dao = new AccountDAO();
    Account user = dao.getAccountById(userId);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Account</title>
    <link rel="stylesheet" href="css/admin.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <style>
        body {
            background-color: #f5f7fa;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        .edit-container {
            max-width: 600px;
            margin: 50px auto;
            background: #ffffff;
            padding: 30px 40px;
            border-radius: 12px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            margin-bottom: 30px;
            color: #333;
        }

        label {
            font-weight: 500;
        }

        .form-control[readonly] {
            background-color: #e9ecef;
        }

        .btn {
            width: 100px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .btn-group {
            display: flex;
            justify-content: space-between;
            margin-top: 30px;
        }
    </style>
</head>
<body>

<div class="edit-container">
    <h2>Edit Account</h2>
    <form action="editAccount" method="post">
        <input type="hidden" name="id" value="<%= user.getId() %>"/>

        <div class="form-group">
            <label>Full Name:</label>
            <input type="text" class="form-control" value="<%= user.getFullname() %>" readonly>
        </div>

        <div class="form-group">
            <label>Email:</label>
            <input type="email" class="form-control" value="<%= user.getEmail() %>" readonly>
        </div>

        <div class="form-group">
            <label>Phone:</label>
            <input type="text" class="form-control" value="<%= user.getPhoneNumber() %>" readonly>
        </div>

        <div class="form-group">
            <label>Gender:</label>
            <input type="text" class="form-control" value="<%= user.getGender() %>" readonly>
        </div>

        <div class="form-group">
            <label>Address:</label>
            <input type="text" class="form-control" value="<%= user.getAddress() %>" readonly>
        </div>

        <div class="form-group">
            <label>Date of Birth:</label>
            <input type="date" class="form-control" value="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(user.getDateOfBirth()) %>" readonly>
        </div>

        <div class="form-group">
            <label>Status:</label>
            <input type="text" class="form-control" value="<%= user.getStatus() %>" readonly>
        </div>

        <div class="form-group">
            <label>Role:</label>
            <select name="role" class="form-select">
                <option value="Customer" <%= user.getRole().equals("Customer") ? "selected" : "" %>>Customer</option>
                <option value="Staff" <%= user.getRole().equals("Staff") ? "selected" : "" %>>Staff</option>
                <option value="Admin" <%= user.getRole().equals("Admin") ? "selected" : "" %>>Admin</option>
            </select>
        </div>

        <div class="btn-group">
            <button type="submit" class="btn btn-primary">Save</button>
            <a href="accounts" class="btn btn-secondary">Cancel</a>
        </div>
    </form>
</div>

</body>
</html>
