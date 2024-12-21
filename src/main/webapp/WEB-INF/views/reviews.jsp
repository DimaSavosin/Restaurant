<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>Отзывы посетителей</h1>
<c:forEach var="review" items="${reviews}">
    <div style="border: 1px solid #ccc; padding: 10px; margin-bottom: 10px;">
        <p><strong>Пользователь:</strong> ${review.userName}</p>
        <p><strong>Рейтинг:</strong> ${review.rating}</p>
        <p><strong>Комментарий:</strong> ${review.comment}</p>
        <p><strong>Дата:</strong> <fmt:formatDate value="${review.createdAt}" pattern="yyyy-MM-dd HH:mm:ss" /></p>
    </div>
</c:forEach>

<h2>Оставьте отзыв</h2>
<form action="${pageContext.request.contextPath}/reviews" method="post">
    <label for="rating">Рейтинг:</label>
    <select name="rating" id="rating">
        <c:forEach var="i" begin="1" end="5">
            <option value="${i}">${i}</option>
        </c:forEach>
    </select>
    <br>
    <label for="comment">Комментарий:</label>
    <textarea name="comment" id="comment"></textarea>
    <br>
    <button type="submit">Отправить отзыв</button>
</form>

</body>
</html>
