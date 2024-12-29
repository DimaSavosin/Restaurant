<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Отзывы</title>
</head>
<body>

<h1>Отзывы посетителей</h1>
<c:if test="${averageRating > 0}">
    <p><strong>Средний рейтинг:</strong></p>
    <div class="stars">
        <c:forEach var="i" begin="1" end="5">
            <span class="${i <= averageRating ? 'filled' : ''}">&#9733;</span>
        </c:forEach>
        <span>(${averageRating})</span>
    </div>
</c:if>


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
    <textarea name="comment" id="comment" required></textarea>
    <br>
    <button type="submit">Отправить отзыв</button>
</form>
<style>
    .stars {
        display: inline-flex;
        align-items: center;
        font-size: 24px;
        color: #ccc; /* Цвет для пустых звезд */
    }

    .stars .filled {
        color: gold; /* Цвет для заполненных звезд */
    }

    .stars span:last-child {
        margin-left: 10px;
        font-size: 18px;
        color: #333; /* Цвет текста */
    }
</style>


</body>
</html>
