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

<div id="rigth_bar">
    <div class="list_product main_flex">
        <div class="img_product">
            <img width=25% height=25% src="../img/myImages/1.1_farmstead.jpg" alt="">
            <h2>Ourdsfadsfsadfasdf</h2>
            <p>dffffffffffffffffffff</p>
            <p>fddddddddddddddddddddddddd</p>
        </div>
    </div>

    <button id="load_more" type="button">
        LoadMore
    </button>
</div>

</body>
</html>