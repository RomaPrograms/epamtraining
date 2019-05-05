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

    <link rel="stylesheet"
          href="/vendors/formvalidation/dist/css/formValidation.min.css">
    <link rel="stylesheet"
          href="//cdn.jsdelivr.net/jquery.bootstrapvalidator/0.5.2/css/bootstrapValidator.min.css"/>
    <link rel="stylesheet"
          href="http://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.2/css/bootstrapValidator.min.css"/>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script type="text/javascript"
            src="http://code.jquery.com/jquery-1.10.2.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
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

    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand blue-text"><fmt:message key="siteName"/></a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul id="list" class="nav navbar-nav">
                <li><a href="/menu.html"><fmt:message key="navbarMenu"/></a></li>
                <li><a href="/signUp.html"><fmt:message key="registration"/></a></li>
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
                        <li><a href="/en_US.html"><fmt:message key="englishLanguage"/></a></li>
                        <li><a href="/be_BY.html"><fmt:message key="belarusianLanguage"/></a></li>
                        <li><a href="/ru_RU.html"><fmt:message key="russianLanguage"/></a></li>
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
                    <input type="submit" class="btn btn-primary" value="${enter}">
                    <br/><div class="form-group">
                        <div id="navbarMessage"></div>
                    </div>
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
        <c:set var="enterPassword" scope="page">
            <fmt:message key="enterPassword"/>
        </c:set>

        <c:set var="enterLogin" scope="page">
            <fmt:message key="enterLogin"/>
        </c:set>

        <c:set var="enterName" scope="page">
            <fmt:message key="enterName"/>
        </c:set>

        <c:set var="enterSurname" scope="page">
            <fmt:message key="enterSurname"/>
        </c:set>

        <c:set var="enterPhoneNumber" scope="page">
            <fmt:message key="enterPhoneNumber"/>
        </c:set>

    <div class="page-header">
        <h1><fmt:message key="registration"/>
            <small><fmt:message key="registrationInfo"/></small>
        </h1>
    </div>
    <form class="form-horizontal" role="form" id="reg_form" method="post">
        <div class="form-group">
            <label><fmt:message key="login"/></label>
            <input id="sign_up_login" type="text" name="login" pattern="^[A-z]*"
                   placeholder="${enterLogin}" class="form-control">
            <div id="messages"></div>
        </div>
        <div class="form-group">
            <label for="sign_up_password"><fmt:message key="password"/></label>
            <input id="sign_up_password" type="password" name="password"
                   placeholder="${enterPassword}" class="form-control" value="${userInfo.getPassword()}">
        </div>
        <div class="form-group">
            <label><fmt:message key="name"/></label>
            <input id="sign_up_name" type="text" name="name" value="${userInfo.getName()}"
                   placeholder="${enterName}" class="form-control">
        </div>
        <div class="form-group">
            <label for="sign_up_surname"><fmt:message key="surname"/></label>
            <input id="sign_up_surname" type="text" name="surname" value="${userInfo.getSurname()}"
                   placeholder="${enterSurname}" class="form-control">
        </div>
        <div class="form-group">
            <label for="sign_up_phoneNumber"><fmt:message key="phoneNumber"/></label>
            <input id="sign_up_phoneNumber" type="text" name="phoneNumber"
                   placeholder="${enterPhoneNumber}" value="${userInfo.getPhone()}"
                   class="form-control">
        </div>

        <c:if test="${successMessage != null}">
        <div class="alert alert-success form-group">
            <strong>Success!</strong> <c:out value="${successMessage}"/>
        </div>
        </c:if>
        <c:if test="${errorMessage != null}">
            <div class="alert alert-warning form-group">
                <strong>Warning!</strong> <c:out value="${errorMessage}"/>
            </div>
        </c:if>

        <div class="form-group">
        <button type="submit" class="btn btn-primary"><fmt:message key="registration"/></button>
        </div>

    </form>
</div>

<script type="text/javascript">
    <jsp:include page="../js/sign_up_validation.js"/>
    <jsp:include page="../js/log_in_validation.js"/>
</script>

</body>
</html>