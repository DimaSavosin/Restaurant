<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Главная страница</title>
    <link rel="stylesheet" href="<c:url value='/style/user/mainPage/mainPage.css' />">
    <link href="https://fonts.googleapis.com/css2?family=Lora:ital,wght@0,400;0,700;1,400;1,700&family=Playfair+Display:ital,wght@0,400;0,700;1,400;1,700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;700&display=swap" rel="stylesheet">
</head>
<body>

<header class="navbar navbar--sticky">
    <h1 class="navbar__title">Главная страница</h1>
    <nav class="navbar__links">
        <c:if test="${empty sessionScope.userId}">
            <a class="navbar__link" href="<c:url value='/login'/>">Войти</a>
        </c:if>
        <c:if test="${not empty sessionScope.userId}">
            <a class="navbar__link" href="<c:url value='/profile' />">Мой профиль</a>
        </c:if>
        <a class="navbar__link" href="<c:url value='/bookingForm' />">Забронировать стол</a>
        <a class="navbar__link" href="<c:url value='/view-menu' />">Меню</a>
        <a class="navbar__link" href="<c:url value='/favoriteTables' />">Избранные</a>
        <a class="navbar__link" href="<c:url value='/reviews' />">Отзывы</a>
    </nav>
</header>

<main class="main-content">

    <section class="hero">
        <p class="hero__intro">Добро пожаловать в ресторан</p>
        <h1 class="hero__title">Gallery</h1>
        <p class="hero__description">Мы предлагаем лучшие блюда, приготовленные с любовью, и уютную атмосферу.</p>
        <a class="hero__button" href="${pageContext.request.contextPath}/bookingForm">Забронировать стол</a>
        <a class="hero__button" href="${pageContext.request.contextPath}/view-menu">Посмотреть меню</a>
    </section>


    <section class="about">
        <h2 class="about__title">О нас</h2>
        <p class="about__description">Наша миссия — дарить гостям незабываемые вкусовые впечатления. Мы готовим из свежих продуктов и предлагаем блюда европейской и авторской кухни.</p>
        <img class="about__image" src="${pageContext.request.contextPath}/images/interior.png" alt="Интерьер ресторана">
    </section>

    <section class="popular-dishes">
        <h2 class="popular-dishes__title">Популярные блюда</h2>
        <div class="popular-dishes__list">
            <div class="dish-card">
                <img class="dish-card__image" src="${pageContext.request.contextPath}/images/borsh.png" alt="Суп дня">
                <h3 class="dish-card__name">Суп дня</h3>
                <p class="dish-card__description">Легкий и вкусный суп с овощами и специями.</p>
                <p class="dish-card__price">Цена: 450 ₽</p>
            </div>
            <div class="dish-card">
                <img class="dish-card__image" src="${pageContext.request.contextPath}/images/stake.png" alt="Стейк">
                <h3 class="dish-card__name">Стейк</h3>
                <p class="dish-card__description">Сочный стейк из мраморной говядины с травами.</p>
                <p class="dish-card__price">Цена: 1200 ₽</p>
            </div>
        </div>
        <a class="popular-dishes__button" href="${pageContext.request.contextPath}/view-menu">Посмотреть всё меню</a>
    </section>

    <section class="features">
        <h2 class="features__title">Почему выбирают нас</h2>
        <ul class="features__list">
            <li class="features__item">Только свежие продукты</li>
            <li class="features__item">Авторские блюда</li>
            <li class="features__item">Простое бронирование</li>
            <li class="features__item">Уютный интерьер</li>
        </ul>
    </section>

    <section class="reviews">
        <h2 class="reviews__title">Отзывы наших гостей</h2>
        <blockquote class="reviews__quote">
            <p class="reviews__text">"Отличная еда и приятная атмосфера. Рекомендую!"</p>
            <cite class="reviews__author">— Иван П.</cite>
        </blockquote>
        <a class="reviews__button" href="${pageContext.request.contextPath}/reviews">Читать все отзывы</a>
    </section>
</main>

</body>
</html>
