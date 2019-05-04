<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<fmt:setBundle basename="property.text"/>

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

    <c:set var="login" scope="page">
        <fmt:message key="navbarLogin"/>
    </c:set>

    <c:set var="password" scope="page">
        <fmt:message key="navbarPassword"/>
    </c:set>

    <c:set var="enter" scope="page">
        <fmt:message key="navbarEnter"/>
    </c:set>

    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand blue-text"><fmt:message key="siteName"/></a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul id="list" class="nav navbar-nav">
                <li><a href="/menu.html"><fmt:message key="navbarMenu"/></a></li>
                <li><a href="/signUp.html"><fmt:message key="navbarRegistration"/></a></li>
                <li><a href="/homesteads.html"><fmt:message key="navbarHomesteads"/></a></li>
                <c:if test="${profile != null}">
                    <li><a href="/personalCabinet.html"><fmt:message key="navbarPersonalCabinet"/></a></li>
                </c:if>
                <c:if test="${profile != null && profile.getRole().getIdentity() == 1}">
                    <li><a href="/ownerHomesteads.html"><fmt:message key="navbarOwnerHomesteads"/></a></li>
                </c:if>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown"><fmt:message key="navbarLanguage"/>
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="/en_US.html">Англиийский</a></li>
                        <li><a href="/be_BY.html">Белорусский</a></li>
                        <li><a href="/ru_RU.html">Русский</a></li>
                    </ul>
                </li>
            </ul>

            <form class="navbar-form navbar-right" action="/changeStatus.html"
                  method="post">
                <c:if test="${profile == null}">
                    <div class="form-group">
                        <input type="text" placeholder="${login}"
                               class="form-control" name="login">
                    </div>
                    <div class="form-group">
                        <input type="password" placeholder="${password}"
                               class="form-control" name="password">
                    </div>
                    <input type="submit" class="btn btn-primary" value="${enter}">
                    <c:if test="${logInMessage != null}">
                        <div class="alert alert-danger">
                            <strong><fmt:message key="navbarIssue"/>!</strong> <c:out value="${logInMessage}"/>
                        </div>
                    </c:if>
                </c:if>
                <c:if test="${profile != null}">
                    <div class="form-group">
                        <label class="text-primary"><fmt:message key="navbarWelcome"/> , <c:out
                                value="${profile.getLogin()}"/></label>
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