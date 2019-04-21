<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        #navbar_button {
            background-color: deepskyblue;
        }

        #navbar_button_span, #navbar_button_span1, #navbar_button_span2 {
            background-color: white;
        }

        #navbar_background {
            background-color: white;
        }

        #dddd {
            background-color: white;
            padding-top: 25px;
            padding-bottom: 25px;
        }

        footer {
            background-color: white;
            padding-top: 5px;
            padding-bottom: 5px;
        }
        #findData {
            background-color: white;
            padding-top: 10px;
            padding-bottom: 60px;
            border: none;
            border-bottom: 1px solid deepskyblue;
            outline: none;
            height: 40px;
            font-size: 16px;
        }
        #search_button
        {
            border: none;
            ouline: none;
            height: 40px;
            background: #1c8adb;
            color: #fff;
            font-size: 18px;
            border-radius: 20px;
        }

        #search_button:hover
        {
            cursor: pointer;
            background: #39dc79;
            color: #000;
        }
    </style>
</head>
<body>
<nav id="navbar_background" class="navbar fixed-top scrolling-navbar">
    <div class="container">
        <div class="navbar-header">
            <button id="navbar_button" type="button" class="btn navbar-toggle"
                    data-toggle="collapse" data-target="#myNavbar">
                <span id="navbar_button_span" class="icon-bar"></span>
                <span id="navbar_button_span1" class="icon-bar"></span>
                <span id="navbar_button_span2" class="icon-bar"></span>
            </button>
            <a class="navbar-brand blue-text">Озёрный рай</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul id="list" class="nav navbar-nav">
                <li><a href="../index.jsp">Меню</a></li>
                <li><a href="RegistrationForm.jsp">Регистрация</a></li>
                <li><a href="homesteads.jsp">Агроусадьбы</a></li>
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
<div id="findData" class="container">
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
</div>
<div id="dddd" class="container">
    <div class="row">
        <div class="col-md-4">
            <img width="300px" height="200px" class="img-rounded"
                 src="../img/myImages/1.1_farmstead.jpg"/>
        </div>
        <div class="col-md-8">
            <h2>Дом №1</h2>
            <p>Этот домик невероятно классный, мы уверены, что он вам понравится.</p>
            <dl>
                <dt>Цена</dt>
                <dd>- 2000</dd>
                <dt>Колличество человек</dt>
                <dd>- 8</dd>
            </dl>
            <p><a href="Contacts.jsp" class="btn btn-default">Узнать больше
                &raquo;</a></p>
        </div>
    </div>
    <hr>
    <div class="row">
        <div class="col-md-4">
            <img width="300px" height="200px" class="img-rounded"
                 src="../img/myImages/2.0_farmstead.jpg"/>
        </div>
        <div class="col-md-8">
            <h2>Просмотреть дома</h2>
            <p>На нашем сайте собрано огромное колличество агроусадьб. По поводу
                каждой
                агроусадьбы вы сможете написать хозяину лично. Более того, вы
                можете выбирать
                агроусадьбы по предложенным критериям. Мы не сомневаемся, что вы
                сможете найти агроусадьбу подходящую всем вашим категориям.</p>
            <p><a href="Contacts.jsp" class="btn btn-default">Узнать больше
                &raquo;</a></p>
        </div>
    </div>
    <hr>
    <div class="row">
        <div class="col-md-4">
            <img width="300px" height="200px" class="img-rounded"
                 src="../img/myImages/3.0_farmstead.jpg"/>
        </div>
        <div class="col-md-8">
            <h2>Просмотреть дома</h2>
            <p>На нашем сайте собрано огромное колличество агроусадьб. По поводу
                каждой
                агроусадьбы вы сможете написать хозяину лично. Более того, вы
                можете выбирать
                агроусадьбы по предложенным критериям. Мы не сомневаемся, что вы
                сможете найти агроусадьбу подходящую всем вашим категориям.</p>
            <p><a href="Contacts.jsp" class="btn btn-default">Узнать больше
                &raquo;</a></p>
        </div>
    </div>
    <hr>
    <div class="row">
        <div class="col-md-4">
            <img width="300px" height="200px" class="img-rounded"
                 src="../img/myImages/4.0_farmstead.jpg"/>
        </div>
        <div class="col-md-8">
            <h2>Просмотреть дома</h2>
            <p>На нашем сайте собрано огромное колличество агроусадьб. По поводу
                каждой
                агроусадьбы вы сможете написать хозяину лично. Более того, вы
                можете выбирать
                агроусадьбы по предложенным критериям. Мы не сомневаемся, что вы
                сможете найти агроусадьбу подходящую всем вашим категориям.</p>
            <p><a href="Contacts.jsp" class="btn btn-default">Узнать больше
                &raquo;</a></p>
        </div>
    </div>

    <hr>
    <footer>
        <p>&copy; Все права защищены 2019</p>
    </footer>
</div>

</body>
</html>