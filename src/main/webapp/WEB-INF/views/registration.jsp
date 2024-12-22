<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
    <link rel="stylesheet" href="<c:url value='/style/register.css' />">

</head>
<body>

<div class="form-container">
    <form action="${pageContext.request.contextPath}/register" method="POST" class="form">
        <h2>Регистрация</h2>
        <label for="name">Имя:</label>
        <input type="text" id="name" name="name" required><br><br>
        <label for="email">Почта:</label>
        <input type="email" id="email" name="email" required><br><br>
        <label for="password">Пароль:</label>
        <input type="password" id="password" name="password" minlength="4"  required><br><br>
        <button type="submit">Зарегестрироваться</button>
    </form>
    <form action="${pageContext.request.contextPath}/login" method="GET">
        <div class="button2">
        <button type="submit" class="login-btn">Войти</button>
        </div>
    </form>
</div>
<p>${errorMessage}</p>

</body>
</html>
