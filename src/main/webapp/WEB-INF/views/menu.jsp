<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>


<html>
<head>
    <title>Меню</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/menu.js"></script>
    <style>
        .menu-item {
            display: inline-block;
            width: 30%;
            margin: 10px;
            text-align: center;
        }
        .menu-item img {
            max-width: 100%;
            height: auto;
        }
    </style>
</head>
<body>
<h1>Меню ресторана</h1>
<div id="menu-container"></div>
</body>
</html>
