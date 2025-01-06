<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>История бронирований</title>
    <link href="https://fonts.googleapis.com/css2?family=Lora:ital,wght@0,400;0,700;1,400;1,700&family=Playfair+Display:ital,wght@0,400;0,700;1,400;1,700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/admin/adminBookings/adminHistoryBookings.css">
</head>
<body>
<div class="history-bookings">
    <h1 class="history-bookings__title">История бронирований</h1>
    <table class="history-bookings__table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Имя пользователя</th>
            <th>Номер стола</th>
            <th>Дата</th>
            <th>Время</th>
            <th>Статус</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="booking" items="${historicalBookings}">
            <tr>
                <td>${booking.id}</td>
                <td>${booking.userName}</td>
                <td>${booking.tableId}</td>
                <td>${booking.bookingDate}</td>
                <td>${booking.bookingTime}</td>
                <td>${booking.status}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="${pageContext.request.contextPath}/admin/bookings" class="history-bookings__link">Вернуться к управлению бронированиями</a>
</div>
</body>
</html>
