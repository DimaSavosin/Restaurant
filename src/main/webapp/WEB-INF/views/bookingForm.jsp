<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Бронирование столов</title>
</head>
<body>
<h1>Форма для бронирования столов</h1>
<form action="bookingTable" method="get">

    <label for="location">Выберите локацию:</label>
    <select id="location" name="location" required>
        <option value="зал">Зал</option>
        <option value="улица">Улица</option>
    </select>


    <label for="bookingDate">Дата бронирования:</label>
    <input type="date" id="bookingDate" name="bookingDate" required>


    <label for="bookingTime">Время бронирования:</label>
    <input type = "time" id="bookingTime" name="bookingTime"  min = "10:00" max="22:00" required>


    <label for="duration">Продолжительность (в часах):</label>
    <select id="duration" name="duration" required>
        <option value="1">1 час</option>
        <option value="2">2 часа</option>
        <option value="3">3 часа</option>
    </select>

    <button type="submit">Посмотреть столы</button>
</form>
${errorMessage}
</body>
</html>
