<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
    <link rel="stylesheet" href="<c:url value='/style/register.css' />">

</head>
<body>

<div class="form-container">
    <form action="/ORIS_war_exploded/register" method="POST" class="form">
        <h2>Registration</h2>
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required><br><br>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br><br>
        <button type="submit">Register</button>
    </form>
    <form action="/ORIS_war_exploded/login" method="GET">
        <div class="button2">
        <button type="submit" class="login-btn">Login</button>
        </div>
    </form>
</div>
<p>${errorMessage}</p>

</body>
</html>
