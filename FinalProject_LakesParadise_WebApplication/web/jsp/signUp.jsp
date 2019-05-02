<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>Transparent Login form Design</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar fixed-top scrolling-navbar">
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

            <form class="navbar-form navbar-right" action="/changeStatus.html"
                  method="get">
                <c:if test="${profile == null}">
                    <div class="form-group">
                        <input type="text" placeholder="Логин"
                               class="form-control" name="login">
                    </div>
                    <div class="form-group">
                        <input type="password" placeholder="Пароль"
                               class="form-control" name="password">
                    </div>
                    <input type="submit" class="btn btn-primary" value="Вход"/>
                    <c:if test="${logInMessage != null}">
                        <div class="alert alert-danger">
                            <strong>Issue!</strong> <c:out value="${logInMessage}"/>
                        </div>
                    </c:if>
                </c:if>
                <c:if test="${profile != null}">
                    <div class="form-group">
                        <label class="text-primary">Welcome, <c:out value="${profile.getLogin()}"/></label>
                    </div>
                </c:if>
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
            <input id="sign_up_login" type="text" name="login"
                   placeholder="Введите логин" class="form-control">
        </div>
        <div class="form-group">
            <label>Пароль</label>
            <input id="sign_up_password" type="password" name="password"
                   placeholder="Введите пароль" class="form-control">
        </div>
        <div class="form-group">
            <p>Имя</p>
            <input id="sign_up_name" type="text" name="name"
                   placeholder="Введите имя" class="form-control">
        </div>
        <div class="form-group">
            <label>Фамилия</label>
            <input id="sign_up_surname" type="text" name="surname"
                   placeholder="Введите фамилию" class="form-control">
        </div>
        <div class="form-group">
            <label>Телефон</label>
            <input id="sign_up_phoneNumber" type="text" name="phoneNumber"
                   placeholder="Введите мобильный номер"
                   class="form-control">
        </div>
        <div class="form-group">
        <input type="submit" name="submit" value="Зарегистрироваться"
               class="btn btn-primary">
        <a href="#">Забыл пароль</a>
        </div>
        <div clas="form-group">
            <label>${message}</label>
        </div>
    </form>
</div>
</body>
</html>