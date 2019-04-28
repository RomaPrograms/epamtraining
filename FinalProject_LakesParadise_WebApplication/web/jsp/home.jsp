<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page errorPage="error.jsp" %>
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
        #body {
            font-family: sans-serif;
            font-size: 11pt;
            background-image: url(../img/myImages/mainPicture.jpg);
            background-repeat: no-repeat;
            background-size: cover;
            background-attachment: fixed;
        }

        #navbar_button {
            background-color: deepskyblue;
        }

        #navbar_button_span, #navbar_button_span1, #navbar_button_span2 {
            background-color: white;
        }

        #navbar_background {
            background-color: white;
        }

        #homestead_catalog {
            background-color: white;
            padding-top: 25px;
            padding-bottom: 25px;
        }

        footer {
            background-color: white;
            padding-top: 5px;
            padding-bottom: 5px;
        }
    </style>
</head>
<body id="body">
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
                <li><a href="/menu.html">Меню</a></li>
                <li><a href="/signUp.html">Регистрация</a></li>
                <li><a href="/homesteads.html">Агроусадьбы</a></li>
            </ul>

            <form class="navbar-form navbar-right" action="firstAction">
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

<div id="homestead_catalog" class="container">
    <div class="page-header">
        <h1>Дом
            <small>Сюда мы запишем новую информацию о доме</small>
        </h1>
    </div>
    <div class="container" style="width:90%; height:80%;">
        <div id="carouselIndicators" class="carousel slide"
             data-ride="carousel">
            <ol class="carousel-indicators">
                <li class="active" data-target="#carouselIndicators"
                    data-slide-to="0"></li>
                <li data-target="#carouselIndicators" data-slide-to="2"></li>
                <li data-target="#carouselIndicators" data-slide-to="3"></li>
            </ol>
            <div class="carousel-inner">
                <c:forEach var="image" items="${homestead.getImages()}"
                           varStatus="status">

                    <div class="item active">
                        <img src="../img/myImages/3.1_farmstead.jpg"<%--image.getImage().getBinaryStream(1, image.getImage().length())--%>
                             alt="" style="width:100%; height:100%;"/>
                    </div>
                    <div class="item">
                        <img src="../img/myImages/3.1_farmstead.jpg"
                             alt="" style="width:100%; height:100%;"/>
                    </div>
                    <div class="item">
                        <img src="../img/myImages/3.2_farmstead.jpg"
                             alt="" style="width:100%; height:100%;"/>
                    </div>
                </c:forEach>
            </div>
            <a class="left carousel-control" href="#carouselIndicators"
               data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="right carousel-control" href="#carouselIndicators"
               data-slide="next">
                <span class="glyphicon glyphicon-chevron-right"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
    </div>
    <div class="container text-center">
        <h2><c:out value="${homestead.getTitle()}"/></h2>
        <p><c:out value="${homestead.getDescription()}"/></p>
        <dl>
            <dt>Цена</dt>
            <dd>- <c:out value="${homestead.getPrice()}"/></dd>
            <dt>Колличество человек</dt>
            <dd>- <c:out value="${homestead.getPeopleNumber()}"/></dd>
        </dl>
    </div>

    <hr>
    <

    <div class="form-group container">
        <label for="comment">Оставьте комментарий:</label>
        <form action="/review.html">
            <input type="hidden" name="homesteadIdentity"
            value="${homestead.getId()}"/>
            ID: ${homestead.getId()}
            <textarea class="form-control" rows="5" id="comment" name="comment"></textarea>
            <br>
            <p>
                <button type="submit" class="btn btn-primary">Отправить
                    комментарий
                </button>
            </p>
        </form>
    </div>

    <div class="container">
        <div class="panel-group">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" href="#collapse1">Посмотреть
                            все комментарии.</a>
                    </h4>
                </div>
                <div id="collapse1" class="panel-collapse collapse">
                    <c:forEach var="review" items="${homestead.getReviews()}"
                               varStatus="status">
                        <div class="panel-body">
                            <div class="row container text-center">
                                <div class="column-md-3">
                                    <h5>Логин: </h5>
                                </div>
                                <div class="column-md-4">
                                    Логин: ${review.getUserName()}
                                </div>
                            </div>
                            <div class="row container text-center">
                                <div class="column-md-3">
                                    Дата оставленного комментария:
                                </div>
                                <div class="column-md-4">
                                        ${review.getDateOfComment()}
                                </div>
                            </div>
                            <div class="row container text-center">
                                    ${review.getText()}
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>

</div>

<hr>
<footer>
    <p>&copy; Все права защищены 2019</p>
</footer>
</div>

</body>
</html>