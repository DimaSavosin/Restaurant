<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Избранные столы</title>
</head>
<body>
<h1>Мои избранные столы</h1>
<table border="1">
    <thead>
    <tr>
        <th>Номер стола</th>
        <th>Вместимость</th>
        <th>Локация</th>
        <th>Действие</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="table" items="${favoriteTables}">
        <tr>
            <td>${table.tableNumber}</td>
            <td>${table.seatingCapacity}</td>
            <td>${table.location}</td>
            <td>

                <form action="${pageContext.request.contextPath}/favoriteTables" method="post">
                    <input type="hidden" name="action" value="remove">
                    <input type="hidden" name="tableId" value="${table.id}">
                    <button type="submit">Удалить из избранного</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="${pageContext.request.contextPath}/mainPage">Главное меню</a>
</body>
</html>
