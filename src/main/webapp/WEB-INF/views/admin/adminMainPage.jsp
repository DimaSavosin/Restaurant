<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Админ-панель</title>
    <link rel="stylesheet" href="<c:url value='/style/admin/adminMainPage/adminMainPage.css' />">
    <link href="https://fonts.googleapis.com/css2?family=Lora:ital,wght@0,400;0,700;1,400;1,700&family=Playfair+Display:wght@400;700&display=swap" rel="stylesheet">
</head>
<body class="admin">
<header class="admin__header">
    <h1 class="admin__title">Добро пожаловать в админ-панель!</h1>
</header>
<nav class="admin__navigation">
    <ul class="admin__menu">
        <li class="admin__menu-item">
            <a class="admin__menu-link" href="${pageContext.request.contextPath}/admin/users">Управление пользователями</a>
        </li>
        <li class="admin__menu-item">
            <a class="admin__menu-link" href="${pageContext.request.contextPath}/admin/bookings">Актуальные бронирования</a>
        </li>
        <li class="admin__menu-item">
            <a class="admin__menu-link" href="${pageContext.request.contextPath}/admin/bookingHistory">История бронирований</a>
        </li>
        <li class="admin__menu-item">
            <a class="admin__menu-link" href="${pageContext.request.contextPath}/admin/menu">Меню</a>
        </li>
        <li class="admin__menu-item">
            <a class="admin__menu-link" href="${pageContext.request.contextPath}/admin/tables">Добавить стол</a>
        </li>
        <li>
            <a class="admin__menu-link" href="${pageContext.request.contextPath}/admin/reviews">Отзывы пользователей</a>
        </li>
    </ul>
</nav>
<footer class="admin__footer">
    <a class="admin__logout" href="${pageContext.request.contextPath}/logout">Выйти из аккаунта</a>
</footer>
</body>

</html>
