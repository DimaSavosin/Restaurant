<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Админ-панель</title>
</head>
<body>
<h1>Добро пожаловать в админ-панель!</h1>
<nav>
    <ul>
        <li><a href="<c:url value='/admin/users' />">Управление пользователями</a></li>
        <li><a href="<c:url value='/admin/bookings' />">Управление бронированиями</a></li>
    </ul>
</nav>
</body>
</html>
