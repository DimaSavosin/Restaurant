<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>

<html>
<head>
    <title>Меню ресторана</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/user/menu/menu.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/menu.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Lora:ital,wght@0,400;0,700;1,400;1,700&family=Playfair+Display:ital,wght@0,400;0,700;1,400;1,700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;700&display=swap" rel="stylesheet">
</head>
<body>
<div class="menu">
    <h1 class="menu__header">Меню ресторана</h1>
    <div id="menu-container" class="menu__container"></div>
</div>
</body>
</html>
