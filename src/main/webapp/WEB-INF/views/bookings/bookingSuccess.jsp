<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Бронирование успешно создано</title>
    <link rel="stylesheet" href="<c:url value='/style/user/bookings/bookingSuccess.css' />">
    <link href="https://fonts.googleapis.com/css2?family=Lora:ital,wght@0,400;0,700;1,400;1,700&family=Playfair+Display:ital,wght@0,400;0,700;1,400;1,700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;700&display=swap" rel="stylesheet">
</head>
<body>
<div class="confirmation">
    <h2 class="confirmation__header">Ваше бронирование успешно создано!</h2>
    <p class="confirmation__message">Вы можете посмотреть свои бронирования в личном кабинете.</p>
    <a href="<c:url value='/mainPage' />" class="confirmation__link">Вернуться в главное меню</a>
</div>
</body>
</html>
