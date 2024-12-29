<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<c:url value='/style/login.css' />">
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
    <p>${errorMessage}</p>
</div>
</body>
</html>
