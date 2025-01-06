
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Меню для администратора</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/admin/adminMenu/adminMenu.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/adminMenu.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Lora:ital,wght@0,400;0,700;1,400;1,700&family=Playfair+Display:wght@0,400;0,700&display=swap" rel="stylesheet">
</head>
<body>
<main class="menu-admin">
    <h1 class="menu-admin__title">Меню ресторана (Администратор)</h1>
    <div id="menu-container" class="menu-admin__container"></div>
    <a href="${pageContext.request.contextPath}/admin/addDish" class="menu-admin__add-button">Добавить блюдо</a>
</main>
</body>
</html>
