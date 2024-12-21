<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>История бронирований</title>
</head>
<body>
<h1>История бронирований</h1>

<table border="1">
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

<a href="${pageContext.request.contextPath}/admin/bookings">Вернуться к управлению бронированиями</a>
</body>
</html>
