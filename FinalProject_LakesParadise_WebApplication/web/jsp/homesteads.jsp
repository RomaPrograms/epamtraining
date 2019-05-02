<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page errorPage="error.jsp" %>
<html>
<head>
    <title>Transparent Login form Design</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <style>
        #body {
            font-family: sans-serif;
            font-size: 11pt;
            background-image: url(../img/myImages/mainPicture.jpg);
            background-repeat: no-repeat;
            background-size: cover;
            background-attachment: fixed;
        }

        #homestead_catalog, #container{
            background-color: white;
            padding-top: 25px;
            padding-bottom: 25px;
        }

        footer {
            background-color: white;
            padding-top: 5px;
            padding-bottom: 5px;
        }

        #search_row {
            background-color: white;
            padding-top: 10px;
            padding-bottom: 60px;
            border: none;
            border-bottom: 1px solid deepskyblue;
            outline: none;
            height: 40px;
            font-size: 16px;
        }

        #search_button {
            border: none;
            ouline: none;
            height: 40px;
            background: #1c8adb;
            color: #fff;
            font-size: 18px;
            border-radius: 20px;
        }

        #search_button:hover {
            cursor: pointer;
            background: #39dc79;
            color: #000;
        }
    </style>
</head>

<body id="body">
<nav class="navbar fixed-top scrolling-navbar">
    <div class="container" id="container">
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
                        <label class="text-primary">Welcome, <c:out
                                value="${profile.getLogin()}"/></label>
                    </div>
                </c:if>
            </form>
        </div>
    </div>
</nav>

<div id="search_row" class="container">
    <div class="row">
        <div class="col-md-5">
            Имя:
            <input type="text" name="username"
                   placeholder="Введите имя">
        </div>

        <div class="col-md-5">
            Цена:
            <input type="text" name="username"
                   placeholder="Введите цену">
        </div>

        <div class="col-md-2">
            <input id="search_button" type="submit" name="submit" value="Поиск">
        </div>

    </div>
</div>

<div id="homestead_catalog" class="container">
    <c:forEach var="elem" items="${res}" varStatus="status">
        <div class="row">
            <form method="post" action="/home.html">
                <input type="hidden" name="homesteadIdentity"
                       value="${elem.getId()}"/>
                <div class="col-md-4">
                    <img width="300px" height="200px" class="img-rounded"
                         src="../img/myImages/1.1_farmstead.jpg"/>
                </div>
                <div class="col-md-8">
                    <h2><c:out value="${elem.getTitle()}"/></h2>
                    <p><c:out value="${elem.getDescription()}"/></p>
                    <dl>
                        <dt>Цена</dt>
                        <dd>- <c:out value="${elem.getPrice()}"/></dd>
                        <dt>Колличество человек</dt>
                        <dd>- <c:out
                                value="${elem.getPeopleNumber()}"/></dd>
                    </dl>
                    <input type="submit" class="btn btn-default" name="Узнать
                больше&raquo;"/>
                </div>
            </form>
        </div>
        <hr>
    </c:forEach>
</div>
<hr>
<footer>
    <p>&copy; Все права защищены 2019</p>
</footer>
</div>

</body>
</html>