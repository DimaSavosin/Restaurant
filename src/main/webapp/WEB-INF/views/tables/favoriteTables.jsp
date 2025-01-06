<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Избранные столы</title>
    <link rel="stylesheet" href="<c:url value='/style/user/tables/favoriteTable.css' />">
    <link href="https://fonts.googleapis.com/css2?family=Lora:ital,wght@0,400;0,700;1,400;1,700&family=Playfair+Display:ital,wght@0,400;0,700;1,400;1,700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;700&display=swap" rel="stylesheet">
</head>
<body>
<div class="favorites">
    <h1 class="favorites__header">Мои избранные столы</h1>
    <table class="favorites__table">
        <thead class="favorites__table-head">
        <tr class="favorites__table-row">
            <th class="favorites__table-cell">Номер стола</th>
            <th class="favorites__table-cell">Вместимость</th>
            <th class="favorites__table-cell">Локация</th>
            <th class="favorites__table-cell">Действие</th>
        </tr>
        </thead>
        <tbody class="favorites__table-body">
        <c:forEach var="table" items="${favoriteTables}">
            <tr class="favorites__table-row">
                <td class="favorites__table-cell">${table.tableNumber}</td>
                <td class="favorites__table-cell">${table.seatingCapacity}</td>
                <td class="favorites__table-cell">${table.location}</td>
                <td class="favorites__table-cell">
                    <form action="${pageContext.request.contextPath}/favoriteTablesAdd" method="post" class="favorites__form">
                        <input type="hidden" name="action" value="remove">
                        <input type="hidden" name="tableId" value="${table.id}">
                        <button type="submit" class="favorites__button">Удалить из избранного</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="${pageContext.request.contextPath}/mainPage" class="favorites__link">Главное меню</a>
</div>
</body>
</html>
