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
        body {
            font-family: sans-serif;
            font-size: 11pt;
            background-image: url(../img/myImages/mainPicture.jpg);
            background-repeat: no-repeat;
            background-size: cover;
            background-attachment: fixed;
        }

        #homestead_catalog, #navbar {
            background-color: white;
        }

        footer {
            background-color: white;
            padding-top: 5px;
            padding-bottom: 5px;
        }

    </style>
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
                    <br/>
                    <div class="form-group">
                        <div id="navbarMessage"></div>
                    </div>
                    <input type="submit" class="btn btn-primary"
                           value="${enter}">
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

<div id="homestead_catalog" class="container">

    <c:set var="bookHomestead" scope="page">
        <fmt:message key="rentHomestead"/>
    </c:set>

    <div class="page-header">
        <h1 class="text-center">
            <fmt:message key="welcomeInfo"/>
        </h1>
    </div>
    <div class="container" style="width:90%; height:80%;">
        <div id="myCarousel" class="carousel slide"
             data-ride="carousel">
            <ol class="carousel-indicators">
                <li class="active" data-target="#myCarousel"
                    data-slide-to="0"></li>
                <li data-target="#myCarousel" data-slide-to="1"></li>
                <li data-target="#myCarousel" data-slide-to="2"></li>
            </ol>
            <div class="carousel-inner">
                <%--<c:forEach var="image" items="${homestead.getImages()}"
                           varStatus="status">--%>

                <div class="item active">
                    <img src="../img/3.0_farmstead.jpg"
                         alt="" style="width:100%; height:100%;"/>
                </div>
                <div class="item">
                    <img src="../img/3.1_farmstead.jpg"
                         alt="" style="width:100%; height:100%;"/>
                </div>
                <div class="item">
                    <img src="../img/3.2_farmstead.jpg"
                         alt="" style="width:100%; height:100%;"/>
                </div>
                <%--</c:forEach>--%>
            </div>
            <a class="left carousel-control" href="#myCarousel"
               data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="right carousel-control" href="#myCarousel"
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
            <dt><fmt:message key="homesteadPrice"/></dt>
            <dd>- <c:out value="${homestead.getPrice()}"/></dd>
            <dt><fmt:message key="homesteadPeopleNumber"/></dt>
            <dd>- <c:out value="${homestead.getPeopleNumber()}"/></dd>
        </dl>
    </div>

    <hr>

    <div class="form-group container">
        <form action="/reserveHomestead.html">
            <input type="submit" class="btn btn-default"
                   value="${bookHomestead}"/>
            <c:if test="${registerMessage != null}">
                <div class="alert alert-danger">
                    <strong><fmt:message key="navbarIssue"/>!</strong>
                    <c:out value="${registerMessage}"/>
                </div>
            </c:if>
        </form>
        <label for="comment"><fmt:message key="enterComment"/>:</label>
        <form action="/review.html">
            <textarea class="form-control" rows="5" id="comment"
                      name="comment"></textarea>
            <c:if test="${reviewMessage != null}">
                <div class="alert alert-danger">
                    <strong><fmt:message key="navbarIssue"/>!</strong>
                    <c:out value="${reviewMessage }"/>
                </div>
            </c:if>
            <br>
            <p>
                <button type="submit"
                        class="btn btn-primary">
                    <fmt:message key="sentComment"/>
                </button>
            </p>
        </form>
    </div>

    <div class="container">
        <div class="panel-group">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" href="#collapse1">
                            <fmt:message key="showComments"/></a>
                    </h4>
                </div>
                <div id="collapse1" class="panel-collapse collapse">
                    <c:forEach var="review" items="${homestead.getReviews()}"
                               varStatus="status">
                        <div class="panel-body">
                            <div class="row container text-center">
                                <label><fmt:message key="login"/>: </label>
                                <div>
                                        ${review.getUserName()}
                                </div>
                            </div>
                            <div class="row container text-center">
                                <label>
                                    <fmt:message key="dateOfComment"/>:
                                </label>
                                <div>
                                        ${review.getDateOfCommentByPattern()}
                                </div>
                                <hr>
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

<script type="text/javascript">
    <jsp:include page="../js/log_in_validation.js"/>
</script>

</body>
</html>