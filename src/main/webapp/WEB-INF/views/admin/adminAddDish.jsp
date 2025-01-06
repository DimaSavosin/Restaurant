<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<html>
<head>
    <title>Добавить блюдо</title>
    <link href="https://fonts.googleapis.com/css2?family=Lora:ital,wght@0,400;0,700;1,400;1,700&family=Playfair+Display:ital,wght@0,400;0,700;1,400;1,700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/admin/adminMenu/adminAddDish.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" defer></script>
    <script src="${pageContext.request.contextPath}/js/adminAddDish.js" defer></script>

</head>
<body class="page">
<div class="add-dish">
    <h1 class="add-dish__header">Добавить новое блюдо</h1>
    <form id="addDishForm" enctype="multipart/form-data" class="add-dish__form">
        <label for="name" class="add-dish__label">Название блюда:</label>
        <input type="text" name="name" id="name" class="add-dish__input" required>

        <label for="description" class="add-dish__label">Описание:</label>
        <textarea name="description" id="description" class="add-dish__textarea" required></textarea>

        <label for="price" class="add-dish__label">Цена (₽):</label>
        <input type="number" name="price" id="price" class="add-dish__input" required>

        <label for="file" class="add-dish__label">Загрузить изображение:</label>
        <input type="file" name="file" id="file" class="add-dish__file-input" required>

        <button type="submit" class="add-dish__button">Добавить блюдо</button>
    </form>
    <p id="addDishStatus" class="add-dish__status-message"></p>
    <a href="${pageContext.request.contextPath}/admin/menu" class="add-dish__link">Вернуться в меню</a>
</div>


</body>
</html>
