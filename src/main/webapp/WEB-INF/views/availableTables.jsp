<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="UTF-8" %>

<html>
<head>
    <title>Доступные столы</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/table.css">
</head>
<body>
<h1>Доступные столы</h1>

<c:if test="${empty availableTables}">
    <p>Нет доступных столов на выбранное время.</p>
</c:if>

<div class="table-container">
    <c:forEach var="table" items="${availableTables}">
        <div class="table-card">
            <!-- Звездочка, если стол избранный -->
            <c:if test="${favoriteTableIds.contains(table.id)}">
                <div class="favorite-star">★</div>
            </c:if>
            <h3>Стол № ${table.tableNumber}</h3>
            <p><i class="fas fa-chair"></i> Вместимость: ${table.seatingCapacity} чел.</p>
            <p><i class="fas fa-map-marker-alt"></i> Локация: ${table.location}</p>
            <form action="bookingTable" method="post">
                <input type="hidden" name="tableId" value="${table.id}">
                <input type="hidden" name="bookingDate" value="${bookingDate}">
                <input type="hidden" name="bookingTime" value="${bookingTime}">
                <input type="hidden" name="duration" value="${duration}">
                <button type="submit" class="book-btn">Забронировать</button>
            </form>
        </div>
    </c:forEach>
</div>
${errorMessage}
</body>
</html>
