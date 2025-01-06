<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Ошибка на сервере</title>
    <link rel="stylesheet" href="<c:url value='/style/errors/error.css' />">
    <link href="https://fonts.googleapis.com/css2?family=Lora:ital,wght@0,400;0,700;1,400;1,700&family=Playfair+Display:ital,wght@0,400;0,700;1,400;1,700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;700&display=swap" rel="stylesheet">
</head>
<body>
<div class="error-page">
    <div class="error-page__status">Ошибка: ${requestScope['javax.servlet.error.status_code']}</div>
    <h1 class="error-page__header">Упс! Что-то пошло не так.</h1>

    <div class="error-page__message">
        <c:choose>
            <c:when test="${requestScope['javax.servlet.error.status_code'] == 404}">
                <p class="error-page__text">Страница, которую вы ищете, не может быть найдена.</p>
            </c:when>
            <c:when test="${requestScope['javax.servlet.error.status_code'] == 500}">
                <p class="error-page__text">Приносим извинения, но при обработке вашего запроса произошла ошибка. Пожалуйста, повторите попытку позже.</p>
            </c:when>
            <c:otherwise>
                <p class="error-page__text">Произошла непредвиденная ошибка. Пожалуйста, повторите попытку позже.</p>
            </c:otherwise>
        </c:choose>
    </div>

    <a href="<c:url value='/mainPage' />" class="error-page__link">Вернуться на главную</a>
</div>
</body>
</html>
