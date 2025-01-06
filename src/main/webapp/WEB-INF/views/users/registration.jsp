<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
    <link rel="stylesheet" href="<c:url value='/style/user/account/register.css' />">
    <link href="https://fonts.googleapis.com/css2?family=Lora:ital,wght@0,400;0,700;1,400;1,700&family=Playfair+Display:ital,wght@0,400;0,700;1,400;1,700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;700&display=swap" rel="stylesheet">

</head>
<body>

<div class="form-container">
    <form action="${pageContext.request.contextPath}/register" method="POST" class="form">
        <h2>Регистрация</h2>
        <label for="name">Имя:</label>
        <input type="text" id="name" name="name" required>

        <label for="email">Почта:</label>
        <input type="email" id="email" name="email" required>

        <label for="password">Пароль:</label>
        <input type="password" id="password" name="password" minlength="4" required>

        <button type="submit">Зарегистрироваться</button>
    </form>
    <p class="error-message">${errorMessage}</p>


</div>


</body>
</html>
