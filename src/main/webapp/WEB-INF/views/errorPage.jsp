<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Ошибка на сервере</title>
    <style>
        .debug-info {
            display: none;
            background-color: #f9f9f9;
            padding: 10px;
            margin-top: 20px;
            border: 1px solid red;
        }
    </style>
</head>
<body>
<h1>Упс! Что-то пошло не так.</h1>


<c:choose>
    <c:when test="${requestScope['javax.servlet.error.status_code'] == 404}">
        <p>Страница, которую вы ищете, не может быть найдена.</p>
    </c:when>
    <c:when test="${requestScope['javax.servlet.error.status_code'] == 500}">
        <p>Приносим извинения, но при обработке вашего запроса произошла ошибка. Пожалуйста, повторите попытку позже.</p>
    </c:when>
    <c:otherwise>
        <p>Произошла непредвиденная ошибка. Пожалуйста, повторите попытку позже.</p>
    </c:otherwise>
</c:choose>


<button id="show-debug-button">Показать подробности (только для разработчика)</button>


<div id="debug-info" class="debug-info">
    <h2>Детали ошибки:</h2>
    <p><strong>Код ошибки:</strong> ${requestScope['javax.servlet.error.status_code']}</p>
    <p><strong>Сообщение:</strong> ${requestScope['javax.servlet.error.message']}</p>
    <p><strong>URI запроса:</strong> ${requestScope['javax.servlet.error.request_uri']}</p>
    <p><strong>Stack Trace:</strong></p>
    <pre>${requestScope['javax.servlet.error.exception']}</pre>
</div>

<script>

    document.getElementById("show-debug-button").addEventListener("click", function () {
        const debugInfo = document.getElementById("debug-info");
        debugInfo.style.display = debugInfo.style.display === "block" ? "none" : "block";
    });
</script>
</body>
</html>
