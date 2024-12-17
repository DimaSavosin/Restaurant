
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>My page</h1>
<p>Name:${user.name}</p>
<p>Email:${user.email}</p>
<h1>Мои бронирования</h1>

<c:if test="${empty bookings}">
    <p>У вас нет активных бронирований.</p>
</c:if>

<table border="1">
    <tr>
        <th>Стол №</th>
        <th>Дата</th>
        <th>Время</th>
        <th>Статус</th>
        <th>Действие</th>
    </tr>
    <c:forEach var="booking" items="${bookings}">
        <tr>
            <td>${booking.tableId}</td>
            <td>${booking.bookingDate}</td>
            <td>${booking.bookingTime}</td>
            <td>${booking.status}</td>
            <td>
                <c:if test="${booking.status != 'canceled'}">
                    <form action="cancelBooking" method="post">
                        <input type="hidden" name="bookingId" value="${booking.id}">
                        <button type="submit">Отменить</button>
                    </form>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>


</body>
</html>
