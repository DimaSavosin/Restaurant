
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://fonts.googleapis.com/css2?family=Lora:ital,wght@0,400;0,700;1,400;1,700&family=Playfair+Display:ital,wght@0,400;0,700;1,400;1,700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/admin/adminTables/adminAddTable.css">
</head>
<body class="body">
<div class="add-table">
    <h1 class="add-table__title">Добавить новый стол</h1>
    <form action="${pageContext.request.contextPath}/admin/tables" method="post" class="add-table__form">
        <label for="tableNumber" class="add-table__label">Номер стола</label>
        <input type="number" id="tableNumber" name="tableNumber" class="add-table__input" required>

        <label for="seatingCapacity" class="add-table__label">Вместимость</label>
        <input type="number" id="seatingCapacity" name="seatingCapacity" class="add-table__input" required>

        <label for="location" class="add-table__label">Локация</label>
        <select id="location" name="location" class="add-table__select" required>
            <option value="зал">Зал</option>
            <option value="улица">Улица</option>
        </select>

        <button type="submit" class="add-table__button">Создать</button>
    </form>
    <p class="add-table__error">${errorMessage}</p>
</div>
</body>


</html>
