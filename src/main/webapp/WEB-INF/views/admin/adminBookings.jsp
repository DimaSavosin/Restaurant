<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="UTF-8" %>

<html>
<head>
    <title>Управление бронированиями</title>
    <link href="https://fonts.googleapis.com/css2?family=Lora:ital,wght@0,400;0,700;1,400;1,700&family=Playfair+Display:ital,wght@0,400;0,700;1,400;1,700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/admin/adminBookings/adminBookings.css">
</head>
<body>
<div class="booking-admin">
    <h1 class="booking-admin__title">Управление бронированиями</h1>
    <table class="booking-admin__table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Имя</th>
            <th>Номер стола</th>
            <th>Дата</th>
            <th>Время</th>
            <th>Статус</th>
            <th>Действия</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="booking" items="${activeBookings}">
            <tr>
                <td>${booking.id}</td>
                <td>${booking.userName}</td>
                <td>${booking.tableId}</td>
                <td>${booking.bookingDate}</td>
                <td>${booking.bookingTime}</td>
                <td>${booking.status}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/admin/bookingsAction" method="post" style="display:inline;">
                        <input type="hidden" name="bookingId" value="${booking.id}">
                        <input type="hidden" name="action" value="confirm">
                        <button type="submit" class="booking-admin__button" ${booking.status == 'confirmed' ? 'disabled' : ''}>Подтвердить</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/admin/bookingsAction" method="post" style="display:inline;">
                        <input type="hidden" name="bookingId" value="${booking.id}">
                        <input type="hidden" name="action" value="cancel">
                        <button type="submit" class="booking-admin__button" ${booking.status == 'cancelled' ? 'disabled' : ''}>Отменить</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="${pageContext.request.contextPath}/admin/bookingHistory" class="booking-admin__link">История бронирования</a>
</div>
</body>
</html>
