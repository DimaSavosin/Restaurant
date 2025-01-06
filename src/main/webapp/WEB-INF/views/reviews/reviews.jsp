<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Отзывы</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/user/reviews/reviews.css">
    <link href="https://fonts.googleapis.com/css2?family=Lora:ital,wght@0,400;0,700;1,400;1,700&family=Playfair+Display:ital,wght@0,400;0,700;1,400;1,700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;700&display=swap" rel="stylesheet">
</head>
<body>
<div class="reviews">
    <h1 class="reviews__header">Отзывы посетителей</h1>

    <c:if test="${averageRating > 0}">
        <div class="reviews__average">
            <p class="reviews__average-text"><strong>Средний рейтинг:</strong></p>
            <div class="reviews__stars">
                <c:forEach var="i" begin="1" end="5">
                    <span class="reviews__star ${i <= averageRating ? 'reviews__star--filled' : ''}">&#9733;</span>
                </c:forEach>
                <span class="reviews__average-value">(${averageRating})</span>
            </div>
        </div>
    </c:if>

    <div class="reviews__list">
        <c:forEach var="review" items="${reviews}">
            <div class="reviews__item">
                <p class="reviews__user"><strong>Пользователь:</strong> ${review.userName}</p>
                <p class="reviews__rating"><strong>Рейтинг:</strong> ${review.rating}</p>
                <p class="reviews__comment"><strong>Комментарий:</strong> ${review.comment}</p>
                <p class="reviews__date"><strong>Дата:</strong> <fmt:formatDate value="${review.createdAt}" pattern="yyyy-MM-dd HH:mm" /></p>
            </div>
        </c:forEach>
    </div>

    <h2 class="reviews__form-header">Оставьте отзыв</h2>
    <form action="${pageContext.request.contextPath}/reviewsAdd" method="post" class="reviews__form">
        <label for="rating" class="reviews__form-label">Оценка:</label>
        <select name="rating" id="rating" class="reviews__form-select">
            <c:forEach var="i" begin="1" end="5">
                <option value="${i}">${i}</option>
            </c:forEach>
        </select>
        <br>
        <label for="comment" class="reviews__form-label">Комментарий:</label>
        <textarea name="comment" id="comment" class="reviews__form-textarea" required></textarea>
        <br>
        <button type="submit" class="reviews__form-button">Отправить отзыв</button>
    </form>
</div>
</body>
</html>
