<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="UTF-8" %>

<html>
<head>
    <title>Управление бронированиями</title>
</head>
<body>
<h1>Управление бронированиями</h1>
<table border="1">
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

                <!-- Кнопка подтверждения -->
                <form action="${pageContext.request.contextPath}/admin/bookings/action" method="post" style="display:inline;">
                    <input type="hidden" name="bookingId" value="${booking.id}">
                    <input type="hidden" name="action" value="confirm">
                    <button type="submit" ${booking.status == 'confirmed' ? 'disabled' : ''}>Подтвердить</button>
                </form>
                <!-- Кнопка отмены -->
                <form action="${pageContext.request.contextPath}/admin/bookings/action" method="post" style="display:inline;">
                    <input type="hidden" name="bookingId" value="${booking.id}">
                    <input type="hidden" name="action" value="cancel">
                    <button type="submit" ${booking.status == 'cancelled' ? 'disabled' : ''}>Отменить</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="${pageContext.request.contextPath}/admin/bookingHistory">История бронирования</a>
</body>
</html>
