<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page errorPage="error.jsp" %>
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

    <script type="text/javascript"
            src="http://code.jquery.com/jquery-1.10.2.js"></script>
    <script type="text/javascript"
            src="http://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.0/js/bootstrapValidator.min.js"></script>

    <style>
        #body {
            font-family: sans-serif;
            font-size: 11pt;
            background-image: url(../img/myImages/mainPicture.jpg);
            background-repeat: no-repeat;
            background-size: cover;
            background-attachment: fixed;
        }

        #navbar {
            background-color: white;
        }

        footer {
            background-color: white;
            padding-top: 5px;
            padding-bottom: 5px;
        }

    </style>
</head>

<body id="body">
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

<div class="container">
    <form method="post" action="/reservation.html" id="reg_form">
        <div class="col-md-3 form-group">
            <label for="startDate">Начало аренды:</label>
            <input type="date" placeholder="Начало аренды"
                   id="startDate" name="startDate"
                   class="form-control">
        </div>

        <div class="col-md-3 form-group">
            <label for="endDate">Конец аренды:</label>
            <input type="date" placeholder="Конец аренды" id="endDate"
                   class="form-control" name="endDate">
        </div>

        <br/>
        <div class="col-md-3 form-group">
            <button type="submit" class="btn btn-primary">Забронировать
            </button>
        </div>
        <c:if test="${message != null}">
            <div class="alert alert-danger">
                <strong><fmt:message key="navbarIssue"/>!</strong>
                <c:out value="${message}"/>
            </div>
        </c:if>
    </form>
</div>

<div class="panel-group container">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title text-center">
                <a data-toggle="collapse" href="#collapse1">Show reserved
                    dates</a>
            </h4>
        </div>
        <div id="collapse1" class="panel-collapse collapse container">
            <ul class="list-group">
                <li class="list-group-item">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>StartDate</th>
                            <th>EndDate</th>
                            <th>Login</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="elem" items="${res}" varStatus="status">
                            <tr>
                                <td>${elem.getStartRentingByPattern()}</td>
                                <td>${elem.getEndRentingByPattern()}</td>
                                <td>${elem.getUser().getLogin()}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </li>
            </ul>
        </div>
    </div>
</div>

<hr>
<footer class="text-center">
    <p>&copy; Все права защищены 2019</p>
</footer>

<script type="text/javascript">
    <jsp:include page="../js/registration_validation.js"/>
    <jsp:include page="../js/log_in_validation.js"/>
</script>

</body>
</html>