<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="cng" uri="customtags" %>
<%@ page errorPage="jsp/error.jsp" %>
<html>
<head>
  <meta charset="utf-8">
  <title>Lakes Paradise site</title>

  <style>
    #main_section {
      font-family: sans-serif;
      font-size: 11pt;
      background-image: url(img/myImages/mainPicture.jpg);
      background-repeat: no-repeat;
      background-size: cover;
      background-attachment: fixed;
    }

    #main_section_text {
      color: white;
    }

    #text {
      color: deepskyblue;
    }
  </style>
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

      <form class="navbar-form navbar-right" action="firstAction" method="get">
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

<div id="main_section" class="jumbotron">
  <div class="container">
    <h1 id="text">Добро пожаловать!</h1>
    <p id="main_section_text">Малое Море – западный мелководный кусочек
      Байкала, ограниченный
      берегом и большим островом Ольхон. Берег вдоль озера изрезан
      бухтами,
      удобными для причаливания суден и яхт. В море – 14 островов. Острова
      – скальные и зеленые, маленькие и большие, на них гнездятся птицы и
      обитают животные. Обрамляющие пролив горы, покрытые тайгой. На
      мелководье вода летом прогревается до 20 градусов. Малое море
      предоставляет полный набор развлечений и впечатлений для отдыха
      летом и зимой. Пляжи и теплая вода, рыбалка на Байкале, общение с
      природой для тех, кто любит покой. Для любителей активного отдыха
      найдутся конные прогулки, велотуризм и альпинистские путешествия.
      Исследователи смогут ознакомиться на экскурсии с древним искусством,
      этническим способом жизни и природными сокровищами Байкала. Зимой
      привлекательна подледная рыбалка на Байкале.</p>
    <p><a href="homesteads.jsp" class="btn btn-primary btn-lg">Узнать больше
      &raquo;</a></p>
  </div>
</div>

<div class="container">
  <div class="row">
    <div class="col-md-6">
      <h2>Условия бронирования</h2>
      <p>Удобная оплата и гарантированное бронирование - наши
        преимущества.
        Ознакомьтесь подробнее</p>
      <p><a href="#" class="btn btn-default">Узнать больше &raquo;</a></p>
    </div>
    <div class="col-md-6">
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

  <cng:footer-tag text="&copy; Все права защищены 2019"/>
</div>

</body>
</html>