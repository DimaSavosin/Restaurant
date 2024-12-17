<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="UTF-8" %>

<html>
<head>
    <title>Доступные столы</title>
</head>
<body>
<h1>Доступные столы</h1>

<c:if test="${empty availableTables}">
    <p>Нет доступных столов на выбранное время.</p>
</c:if>

<c:forEach var="table" items="${availableTables}">
    <form action="bookingTable" method="post">
        <p>Стол № ${table.tableNumber}, вместимость: ${table.seatingCapacity}, локация: ${table.location}</p>
        <input type="hidden" name="tableId" value="${table.id}">
        <input type="hidden" name="bookingDate" value="${bookingDate}">
        <input type="hidden" name="bookingTime" value="${bookingTime}">
        <input type="hidden" name="duration" value="${duration}">
        <button type="submit">Забронировать</button>
    </form>
</c:forEach>


</body>
</html>
