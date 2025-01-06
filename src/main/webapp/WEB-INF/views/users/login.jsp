<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<c:url value='/style/user/account/login.css' />">
    <link href="https://fonts.googleapis.com/css2?family=Lora:ital,wght@0,400;0,700;1,400;1,700&family=Playfair+Display:ital,wght@0,400;0,700;1,400;1,700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;700&display=swap" rel="stylesheet">
</head>
<body>
<div class="form-container">
    <h2>Вход</h2>
    <form action="${pageContext.request.contextPath}/login" method="POST" class="form">
        <label for="email">Почта:</label>
        <input type="email" id="email" name="email" required><br><br>
        <label for="password">Пароль:</label>
        <input type="password" id="password" name="password" required><br><br>
        <button type="submit">Войти</button>
    </form>

    <p>У вас нет аккаунта? <a href="${pageContext.request.contextPath}/register">Зарегистрироваться</a></p>

    <p class="error-message">${errorMessage}</p>
</div>
</body>
</html>
