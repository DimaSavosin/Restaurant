<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<html>
<head>
    <title>Добавить блюдо</title>
</head>
<body>
<h1>Добавить новое блюдо</h1>
<form action="${pageContext.request.contextPath}/admin/addDish" method="post" enctype="multipart/form-data">
    <label for="name">Название блюда:</label>
    <input type="text" name="name" id="name" required>
    <br><br>

    <label for="description">Описание:</label>
    <textarea name="description" id="description" required></textarea>
    <br><br>

    <label for="price">Цена (₽):</label>
    <input type="number" name="price" id="price" required>
    <br><br>

    <label for="file">Загрузить изображение:</label>
    <input type="file" name="file" id="file" required>
    <br><br>

    <button type="submit">Добавить блюдо</button>
</form>
<a href="${pageContext.request.contextPath}/admin/menu">Вернутся в меню</a>
<p>${errorMessage}</p>
</body>
</html>
