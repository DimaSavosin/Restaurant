<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<html>
<head>
    <title>Мой профиль</title>
</head>
<body>
<h1>Мой профиль</h1>
<p>Имя: ${user.name}</p>
<p>Почта: ${user.email}</p>

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
        <th>Действия</th>
    </tr>
    <c:forEach var="booking" items="${bookings}">
        <tr>
            <td>${booking.tableId}</td>
            <td>${booking.bookingDate}</td>
            <td>${booking.bookingTime}</td>
            <td>${booking.status}</td>
            <td>
                <c:if test="${booking.status != 'cancelled'}">
                    <form action="cancelBooking" method="post" style="display:inline;">
                        <input type="hidden" name="bookingId" value="${booking.id}">
                        <button type="submit">Отменить</button>
                    </form>
                </c:if>

                <form action="${pageContext.request.contextPath}/favoriteTables" method="post" style="display:inline;">
                    <input type="hidden" name="tableId" value="${booking.tableId}">
                    <input type="hidden" name="action" value="add">
                    <button type="submit">Добавить в избранное</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<a href="${pageContext.request.contextPath}/favoriteTables">Просмотреть избранные столы</a>

</body>
</html>
