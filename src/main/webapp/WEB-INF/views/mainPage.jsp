<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Главная страница</title>
    <link rel="stylesheet" href="<c:url value='/style/mainPage.css' />">
</head>
<body>

<header class="navbar">
    <h1>Главная страница</h1>
    <nav class="nav-links">

        <c:if test="${empty sessionScope.userId}">
            <a href="<c:url value='/register'/>">Регистрация</a>
        </c:if>





        <c:if test="${not empty sessionScope.userId}">
            <a href="<c:url value='/profile' />">Мой профиль</a>
        </c:if>

        <a href="<c:url value='/bookingForm' />">Забронировать стол</a>
        <a href="<c:url value='/view-menu' />">Меню</a>
        <a href="<c:url value='/favoriteTables' />" > Избранные </a>
        <a href="<c:url value='/reviews' />">Отзывы</a>
    </nav>
</header>
</body>
</html>
