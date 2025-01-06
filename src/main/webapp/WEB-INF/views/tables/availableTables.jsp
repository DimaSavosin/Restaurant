<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="UTF-8" %>

<html>
<head>
    <title>Доступные столы</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/user/tables/table.css">
    <link href="https://fonts.googleapis.com/css2?family=Lora:ital,wght@0,400;0,700;1,400;1,700&family=Playfair+Display:ital,wght@0,400;0,700;1,400;1,700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;700&display=swap" rel="stylesheet">
</head>
<body>
<div class="available-tables">
    <h1 class="available-tables__header">Доступные столы</h1>

    <c:if test="${empty availableTables}">
        <p class="available-tables__message">Нет доступных столов на выбранное время.</p>
    </c:if>

    <div class="available-tables__list">
        <c:forEach var="table" items="${availableTables}">
            <div class="table-card">
                <c:if test="${favoriteTableIds.contains(table.id)}">
                    <div class="table-card__favorite">★</div>
                </c:if>
                <h3 class="table-card__number">Стол № ${table.tableNumber}</h3>
                <p class="table-card__info">Вместимость: ${table.seatingCapacity} чел.</p>
                <p class="table-card__info">Локация: ${table.location}</p>
                <form action="${pageContext.request.contextPath}/bookingTable" method="post" class="table-card__form">
                    <input type="hidden" name="tableId" value="${table.id}">
                    <input type="hidden" name="bookingDate" value="${bookingDate}">
                    <input type="hidden" name="bookingTime" value="${bookingTime}">
                    <input type="hidden" name="duration" value="${duration}">
                    <button type="submit" class="table-card__button">Забронировать</button>
                </form>
            </div>
        </c:forEach>
    </div>

</div>
</body>
</html>
