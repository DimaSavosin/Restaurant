<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 21.12.2024
  Time: 18:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/admin/tables" method="post">
  <label for="tableNumber">Номер стола</label>
    <input type="number" id="tableNumber" name="tableNumber" required> <br><br>

    <label for="seatingCapacity">Вместимость</label>
    <input type="number" id="seatingCapacity" name="seatingCapacity" required> <br><br>

    <label for="location">Локация</label>
    <select id="location" name="location" required>
        <option value="зал">Зал</option>
        <option value="улица">Улица</option>
    </select>
    <br><br>
    <button type="submit">Создать</button>
</form>
<p>${errorMessage}</p>
</body>
</html>
