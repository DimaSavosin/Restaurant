<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Бронирование столов</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/user/bookings/bookingForm.css">
    <link href="https://fonts.googleapis.com/css2?family=Lora:ital,wght@0,400;0,700;1,400;1,700&family=Playfair+Display:ital,wght@0,400;0,700;1,400;1,700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;700&display=swap" rel="stylesheet">
</head>
<body>
<div class="booking-form">
    <h1 class="booking-form__header">Форма для бронирования столов</h1>
    <form action="${pageContext.request.contextPath}/availableTables" method="get" class="booking-form__form">
        <div class="booking-form__field">
            <label for="location" class="booking-form__label">Выберите локацию:</label>
            <select id="location" name="location" class="booking-form__select" required>
                <option value="зал">Зал</option>
                <option value="улица">Улица</option>
            </select>
        </div>

        <div class="booking-form__field">
            <label for="bookingDate" class="booking-form__label">Дата бронирования:</label>
            <input type="date" id="bookingDate" name="bookingDate" class="booking-form__input" required>
        </div>

        <div class="booking-form__field">
            <label for="bookingTime" class="booking-form__label">Время бронирования:</label>
            <input type="time" id="bookingTime" name="bookingTime" class="booking-form__input" min="10:00" max="22:00" required>
        </div>

        <div class="booking-form__field">
            <label for="duration" class="booking-form__label">Продолжительность (в часах):</label>
            <select id="duration" name="duration" class="booking-form__select" required>
                <option value="1">1 час</option>
                <option value="2">2 часа</option>
                <option value="3">3 часа</option>
            </select>
        </div>

        <button type="submit" class="booking-form__button">Посмотреть столы</button>
    </form>
    <p class="booking-form__error">${errorMessage}</p>
</div>
</body>
</html>
