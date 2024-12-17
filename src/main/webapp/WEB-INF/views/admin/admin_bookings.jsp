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
    <c:forEach var="booking" items="${bookings}">
        <tr>
            <td>${booking.id}</td>
            <td>${booking.userName}</td>
            <td>${booking.tableId}</td>
            <td>${booking.bookingDate}</td>
            <td>${booking.bookingTime}</td>
            <td>${booking.status}</td>
            <td>
                <!-- Кнопка для подтверждения -->
                <form action="${pageContext.request.contextPath}/admin/bookings/confirm" method="post">
                    <input type="hidden" name="bookingId" value="${booking.id}" />
                    <button type="submit" ${booking.status == 'confirmed' ? 'disabled' : ''}>Подтвердить</button>
                </form>

                <!-- Кнопка для отмены -->
                <form action="${pageContext.request.contextPath}/admin/bookings/cancel" method="post" style="display: inline;">
                    <input type="hidden" name="bookingId" value="${booking.id}" />
                    <button type="submit" ${booking.status == 'cancelled' ? 'disabled' : ''}>Отменить</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
