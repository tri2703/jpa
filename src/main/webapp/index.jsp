<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
            padding-top: 50px;
        }
        h1 {
            color: #333;
        }
        .button-container {
            margin-top: 30px;
        }
        .button {
            display: inline-block;
            background-color: #4CAF50;
            color: white;
            padding: 14px 25px;
            text-align: center;
            text-decoration: none;
            font-size: 16px;
            margin: 10px;
            border-radius: 4px;
        }
        .button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <h1>Welcome to Admin Dashboard</h1>
    
    <div class="button-container">
        <a href="<%= request.getContextPath() %>/admin/categories" class="button">Manage Categories</a>
        <a href="<%= request.getContextPath() %>/admin/videos" class="button">Manage Videos</a>
    </div>
</body>
</html>
