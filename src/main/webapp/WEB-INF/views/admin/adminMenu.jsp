<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>

<html>
<head>
    <title>Меню для администратора</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/adminMenu.js"></script>
    <style>
        .menu-item {
            display: inline-block;
            width: 30%;
            margin: 10px;
            text-align: center;
            border: 1px solid #ccc;
            padding: 10px;
            border-radius: 5px;
            box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.1);
        }
        .menu-item img {
            max-width: 100%;
            height: auto;
        }
        .delete-btn {
            background-color: red;
            color: white;
            border: none;
            padding: 5px 10px;
            cursor: pointer;
            border-radius: 3px;
        }
    </style>
</head>
<body>
<h1>Меню ресторана (Администратор)</h1>
<div id="menu-container"></div>
<a href="${pageContext.request.contextPath}/admin/addDish">Добавить блюдо</a>
</body>
</html>
