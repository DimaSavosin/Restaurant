<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<html>
<head>
    <title>Мой профиль</title>
    <link rel="stylesheet" href="<c:url value='/style/user/account/profile.css' />">
    <link href="https://fonts.googleapis.com/css2?family=Lora:ital,wght@0,400;0,700;1,400;1,700&family=Playfair+Display:ital,wght@0,400;0,700;1,400;1,700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;700&display=swap" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1 class="profile-header">Мой профиль</h1>
    <p class="profile-info">Имя: ${user.name}</p>
    <p class="profile-info">Почта: ${user.email}</p>

    <h1 class="section-header">Мои бронирования</h1>
    <c:if test="${empty bookings}">
        <p class="no-bookings">У вас нет активных бронирований.</p>
    </c:if>

    <table class="table">
        <tr>
            <th>Стол №</th>
            <th>Дата</th>
            <th>Время</th>
            <th>Продолжительность</th>
            <th>Статус</th>
            <th>Действия</th>
        </tr>
        <c:forEach var="booking" items="${bookings}">
            <tr>
                <td>${booking.tableId}</td>
                <td>${booking.bookingDate}</td>
                <td>${booking.bookingTime}</td>
                <td>${booking.duration}</td>
                <td>${booking.status}</td>
                <td>
                    <c:if test="${booking.status != 'cancelled'}">
                        <form action="cancelBooking" method="post" class="action-form">
                            <input type="hidden" name="bookingId" value="${booking.id}">
                            <button type="submit" class="button">Отменить</button>
                        </form>
                    </c:if>

                    <c:choose>
                        <c:when test="${favoriteTableIds.contains(booking.tableId)}">
                            <span>Уже в избранном</span>
                        </c:when>
                        <c:otherwise>
                            <form action="${pageContext.request.contextPath}/favoriteTablesAdd" method="post" class="action-form">
                                <input type="hidden" name="tableId" value="${booking.tableId}">
                                <input type="hidden" name="action" value="add">
                                <button type="submit" class="button">Добавить в избранное</button>
                            </form>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </table>
    <div class="links">
        <a href="${pageContext.request.contextPath}/favoriteTables" class="link">Просмотреть избранные столы</a>
        <a href="${pageContext.request.contextPath}/logout" class="link">Выйти из аккаунта</a>
    </div>
</div>
${errorMessage}
</body>
</html>
