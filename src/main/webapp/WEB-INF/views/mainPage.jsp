<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Главная страница</title>
    <link rel="stylesheet" href="<c:url value='/style/mainPage.css' />">
    <link href="https://fonts.googleapis.com/css2?family=Lora:ital,wght@0,400;0,700;1,400;1,700&family=Playfair+Display:ital,wght@0,400;0,700;1,400;1,700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;700&display=swap" rel="stylesheet">


</head>
<body>

<header class="navbar">
    <h1>Главная страница</h1>
    <nav class="nav-links">
        <c:if test="${empty sessionScope.userId}">
            <a href="<c:url value='/register'/>">Регистрация</a>
        </c:if>
        <c:if test="${not empty sessionScope.userId}">
            <a href="<c:url value='/profile' />">Мой профиль</a>
        </c:if>
        <a href="<c:url value='/bookingForm' />">Забронировать стол</a>
        <a href="<c:url value='/view-menu' />">Меню</a>
        <a href="<c:url value='/favoriteTables' />">Избранные</a>
        <a href="<c:url value='/reviews' />">Отзывы</a>
    </nav>
</header>

<main>
    <!-- Приветствие -->
    <section class="hero">
        <p>Добро пожаловать в ресторан</p>
        <h1>Gallery</h1>
        <p>Мы предлагаем лучшие блюда, приготовленные с любовью, и уютную атмосферу.</p>
        <a href="${pageContext.request.contextPath}/bookingForm">Забронировать стол</a>
        <a href="${pageContext.request.contextPath}/view-menu">Посмотреть меню</a>
    </section>

    <!-- О нас -->
    <section class="about">
        <h2>О нас</h2>
        <p>Наша миссия — дарить гостям незабываемые вкусовые впечатления. Мы готовим из свежих продуктов и предлагаем блюда европейской и авторской кухни.</p>
        <img src="${pageContext.request.contextPath}/images/interior.png" alt="Интерьер ресторана">
    </section>


    <section class="popular-dishes">
        <h2>Популярные блюда</h2>
        <div class="dishes">
            <div class="dish-card">
                <img src="${pageContext.request.contextPath}/images/borsh.png" alt="Суп дня">
                <h3>Суп дня</h3>
                <p>Легкий и вкусный суп с овощами и специями.</p>
                <p>Цена: 450 ₽</p>
            </div>
            <div class="dish-card">
                <img src="${pageContext.request.contextPath}/images/stake.png" alt="Стейк">
                <h3>Стейк</h3>
                <p>Сочный стейк из мраморной говядины с травами.</p>
                <p>Цена: 1200 ₽</p>
            </div>
        </div>
        <a href="${pageContext.request.contextPath}/view-menu">Посмотреть всё меню</a>
    </section>


    <section class="features">
        <h2>Почему выбирают нас</h2>
        <ul>
            <li>Только свежие продукты</li>
            <li>Авторские блюда</li>
            <li>Простое бронирование</li>
            <li>Уютный интерьер</li>
        </ul>
    </section>


    <section class="reviews">
        <h2>Отзывы наших гостей</h2>
        <blockquote>
            <p>"Отличная еда и приятная атмосфера. Рекомендую!"</p>
            <cite>— Иван П.</cite>
        </blockquote>
        <a href="${pageContext.request.contextPath}/reviews">Читать все отзывы</a>
    </section>


</main>

</body>
</html>
