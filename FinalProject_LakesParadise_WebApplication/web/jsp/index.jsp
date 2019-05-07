<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="cng" uri="customtags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page errorPage="error.jsp" %>
<fmt:setBundle basename="property.text"/>

<html>
<head>
    <meta charset="utf-8">
    <title>Lakes Paradise site</title>

    <style>
        #main_section {
            font-family: sans-serif;
            font-size: 11pt;
            background-image: url(../img/mainPicture.jpg);
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


    <link rel="stylesheet"
          href="/vendors/formvalidation/dist/css/formValidation.min.css">
    <link rel="stylesheet"
          href="//cdn.jsdelivr.net/jquery.bootstrapvalidator/0.5.2/css/bootstrapValidator.min.css"/>
    <link rel="stylesheet"
          href="http://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.2/css/bootstrapValidator.min.css"/>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

    <script type="text/javascript"
            src="http://code.jquery.com/jquery-1.10.2.js"></script>
    <script type="text/javascript"
            src="http://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.0/js/bootstrapValidator.min.js"></script>

</head>

<body>
<nav class="navbar fixed-top scrolling-navbar">

    <c:set var="login" scope="page">
        <fmt:message key="login"/>
    </c:set>

    <c:set var="password" scope="page">
        <fmt:message key="password"/>
    </c:set>

    <c:set var="enter" scope="page">
        <fmt:message key="navbarEnter"/>
    </c:set>

    <div class="container" id="navbar">
        <div class="navbar-header">
            <a class="navbar-brand blue-text"><fmt:message key="siteName"/></a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul id="list" class="nav navbar-nav">
                <li><a href="/menu.html"><fmt:message key="navbarMenu"/></a>
                </li>
                <li><a href="/signUp.html"><fmt:message key="registration"/></a>
                </li>
                <li><a href="/homesteads.html"><fmt:message
                        key="navbarHomesteads"/></a></li>
                <c:if test="${profile != null}">
                    <li><a href="/personalCabinet.html"><fmt:message
                            key="navbarPersonalCabinet"/></a></li>
                </c:if>
                <c:if test="${profile != null && profile.getRole().getIdentity() == 1}">
                    <li><a href="/ownerHomesteads.html"><fmt:message
                            key="navbarOwnerHomesteads"/></a></li>
                </c:if>
                <li class="dropdown">
                    <a class="dropdown-toggle"
                       data-toggle="dropdown"><fmt:message
                            key="navbarLanguage"/>
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="/en_US.html"><fmt:message
                                key="englishLanguage"/></a></li>
                        <li><a href="/be_BY.html"><fmt:message
                                key="belarusianLanguage"/></a></li>
                        <li><a href="/ru_RU.html"><fmt:message
                                key="russianLanguage"/></a></li>
                    </ul>
                </li>
            </ul>

            <form class="navbar-form navbar-right" action="/changeStatus.html"
                  method="post" id="log_in_form">
                <c:if test="${profile == null}">
                    <div class="form-group">
                        <input type="text" placeholder="${login}"
                               class="form-control" name="login">
                    </div>
                    <div class="form-group">
                        <input type="password" placeholder="${password}"
                               class="form-control" name="password">
                    </div>
                    <input type="submit" class="btn btn-primary"
                           value="${enter}">
                    <br/>
                    <div class="form-group">
                        <div id="navbarMessage"></div>
                    </div>
                    <c:if test="${logInMessage != null}">
                        <div class="alert alert-danger">
                            <strong><fmt:message key="navbarIssue"/>!</strong>
                            <c:out value="${logInMessage}"/>
                        </div>
                    </c:if>
                </c:if>
                <c:if test="${profile != null}">
                    <div class="form-group">
                        <label class="text-primary"><fmt:message
                                key="navbarWelcome"/> , <c:out
                                value="${profile.getLogin()}"/></label>
                    </div>
                </c:if>
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
        <p><a href="homesteadsList.jsp" class="btn btn-primary btn-lg">Узнать больше
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

<script type="text/javascript">
    <jsp:include page="../js/log_in_validation.js"/>
</script>

</body>
</html>