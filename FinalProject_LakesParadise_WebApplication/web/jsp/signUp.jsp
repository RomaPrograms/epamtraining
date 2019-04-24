<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>Transparent Login form Design</title>
    <%--
        <link rel="stylesheet" type="text/css" href="../css/style1.css">
    --%>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <style>
        #navbar_button {
            background-color: deepskyblue;
        }

        span {
            background-color: white;
        }

        #navbar_background {
            background-color: white;
        }
        /*body {
            margin: 0;
            padding: 0;
            background: url(../img/myImages/mainPicture.jpg);
            background-size: cover;
            background-position: center;
            font-family: sans-serif;
            background-attachment:fixed;
            !*color: #0b51c5;*!
        }*/
        /*#main_body {
            background-color: white;
        }*/
    </style>
</head>
<body>
<nav id="navbar_background" class="navbar fixed-top scrolling-navbar">
    <div class="container">
        <div class="navbar-header">
            <button id="navbar_button" type="button" class="btn navbar-toggle"
                    data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
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
                <div class="form-group">
                    <input type="password" placeholder="Пароль"
                           class="form-control">
                </div>
                <button type="submit" class="btn btn-primary">Вход</button>
            </form>
        </div>
    </div>
</nav>

<div class="container" id="main_body">
    <%-- <img src="../img/myImages/avatar.png" class="avatar">--%>
    <div class="page-header">
        <h1>Регистрация
            <small>здесь вы можете зарегистрироваться</small>
        </h1>
    </div>
    <form class="form-horizontal" role="form">
        <div class="form-group">
            <label>Логин:</label>
            <input id="sign_up_login" type="text" name="username"
                   placeholder="Введите логин" class="form-control">
        </div>
        <div class="form-group">
            <label>Пароль</label>
            <input id="sign_up_password" type="password" name="password"
                   placeholder="Введите пароль" class="form-control">
        </div>
        <div class="form-group">
            <p>Имя</p>
            <input id="sign_up_name" type="text" name="password"
                   placeholder="Введите имя" class="form-control">
        </div>
        <div class="form-group">
            <label>Фамилия</label>
            <input id="sign_up_surname" type="text" name="password"
                   placeholder="Введите фамилию" class="form-control">
        </div>
        <div class="form-group">
            <label>Телефон</label>
            <input id="sign_up_phoneNumber" type="text" name="password"
                   placeholder="Введите мобильный номер"
                   class="form-control">
        </div>
        <div class="form-group">
            <label>Город</label>
            <input id="sign_up_city" type="text" name="password"
                   placeholder="Введите город" class="form-control">
        </div>
        <input type="submit" name="submit" value="Зарегистрироваться"
               class="btn btn-primary">
        <a href="#">Забыл пароль</a>
    </form>
</div>
</body>
</html>