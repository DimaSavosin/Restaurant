<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User List</title>
    <link href="https://fonts.googleapis.com/css2?family=Lora:ital,wght@0,400;0,700;1,400;1,700&family=Playfair+Display:ital,wght@0,400;0,700;1,400;1,700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value='/style/admin/adminUsers/adminUserList.css' />">
</head>
<body>
<main class="user-list">
    <h1 class="user-list__title">Список пользователей</h1>
    <ul class="user-list__items">
        <c:forEach var="user" items="${userList}">
            <li class="user-list__item">
                <span class="user-list__name">${user.name}</span>
                <span class="user-list__email">(${user.email})</span>
            </li>
        </c:forEach>
    </ul>
</main>
</body>
</html>
