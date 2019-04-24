<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>Transparent Login form Design</title>
    <link rel="stylesheet" type="text/css" href="../css/style1.css">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <style>

        #navbar_background
        {
            background-color: white;
        }
    </style>
</head>
<body>
<nav id="navbar_background" class="navbar fixed-top scrolling-navbar">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand blue-text">Озёрный рай</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul id="list" class="nav navbar-nav">
                <li><a href="/menu.html">Меню</a></li>
                <li><a href="/signUp.html">Регистрация</a></li>
                <li><a href="/homesteads.html">Агроусадьбы</a></li>
            </ul>

            <form class="navbar-form navbar-right">
                <div class="form-group">
                    <input type="text" placeholder="Логин" class="form-control">
                </div>
                <div class="form-group"> <%--Для того, чтобы при сжатии файлов мы могли увидеть пробелы между полями--%>
                    <input type="password" placeholder="Пароль"
                           class="form-control">
                </div>
                <button type="submit" class="btn btn-primary">Вход</button>
            </form>
        </div>
    </div>
</nav>

<div class="login-box container">
    <img src="../img/myImages/avatar.png" class="avatar">
    <h1>Регистрация.</h1>
    <form>
        <p>Логин:</p>
        <input id="sign_up_login" type="text" name="username" placeholder="Введите логин">
        <p>Пароль</p>
        <input id="sign_up_password" type="text" name="password" placeholder="Введите пароль">
        <p>Имя</p>
        <input id="sign_up_name" type="text" name="password" placeholder="Введите имя">
        <p>Фамилия</p>
        <input id="sign_up_surname" type="text" name="password" placeholder="Введите фамилию">
        <p>Телефон</p>
        <input id="sign_up_phoneNumber" type="text" name="password" placeholder="Введите мобильный номер">
        <p>Город</p>
        <input id="sign_up_city" type="text" name="password" placeholder="Введите город">
        <input type="submit" name="submit" value="Зарегистрироваться">
        <a href="#">Забыл пароль</a>
    </form>
</div>
</body>
</html>