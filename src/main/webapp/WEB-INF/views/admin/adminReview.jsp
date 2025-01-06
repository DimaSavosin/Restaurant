<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<html>
<head>
    <title>Управление отзывами</title>
    <link href="https://fonts.googleapis.com/css2?family=Lora:wght@400;700&display=swap" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/style/admin/adminReviews/adminReview.css" rel="stylesheet">
</head>
<body>
<div class="admin-reviews">
    <h1 class="admin-reviews__header">Управление отзывами</h1>

    <c:if test="${empty reviews}">
        <p class="admin-reviews__message">Отзывов пока нет.</p>
    </c:if>

    <c:if test="${not empty reviews}">
        <div class="admin-reviews__list">
            <c:forEach var="review" items="${reviews}">
                <div class="admin-reviews__item">
                    <p><strong>Пользователь:</strong> ${review.userName}</p>
                    <p><strong>Оценка:</strong> ${review.rating}</p>
                    <p><strong>Комментарий:</strong> ${review.comment}</p>
                    <p><strong>Дата:</strong> <fmt:formatDate value="${review.createdAt}" pattern="yyyy-MM-dd HH:mm" /></p>
                    <form action="${pageContext.request.contextPath}/admin/reviewsDelete" method="post" class="admin-reviews__form">
                        <input type="hidden" name="reviewId" value="${review.id}">
                        <button type="submit" class="admin-reviews__delete-button">Удалить</button>
                    </form>
                </div>
            </c:forEach>
        </div>
    </c:if>
</div>
</body>
</html>
