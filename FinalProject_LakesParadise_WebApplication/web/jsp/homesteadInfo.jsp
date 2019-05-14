<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="cng" uri="customtags" %>
<%@ page import="by.training.lakes_paradise.db.entity.Role" %>
<fmt:setBundle basename="property.text"/>

<html>
<head>
    <title>Transparent Login form Design</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="/vendors/formvalidation/dist/css/formValidation.min.css">
    <link rel="stylesheet"
          href="//cdn.jsdelivr.net/jquery.bootstrapvalidator/0.5.2/css/bootstrapValidator.min.css"/>
    <link rel="stylesheet"
          href="http://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.2/css/bootstrapValidator.min.css"/>

    <style>

        #homestead_catalog {
            background-color: white;
        }

    </style>
</head>
<body>

<ctg:navbar-tag profile="${profile}" language="${locale}"
                logInMessage="${logInMessage}"/>

<div id="homestead_catalog" class="container">

    <c:set var="bookHomestead" scope="page">
        <fmt:message key="rentHomestead"/>
    </c:set>

    <c:set var="isFirstPhoto" scope="session" value="${true}"/>

    <div class="page-header">
        <h1 class="text-center">
            <fmt:message key="welcomeInfo"/>
        </h1>
    </div>
    <div class="container" style="width:90%; height:80%;">
        <div id="myCarousel" class="carousel slide"
             data-ride="carousel">
            <div class="carousel-inner">
                <c:forEach var="image" items="${homestead.getImages()}"
                           varStatus="status">
                    <c:if test="${!isFirstPhoto}">
                        <div class="item">
                            <img src="${image.getPathToImage()}"
                                 alt="" style="width:100%; height:100%;"/>
                        </div>
                    </c:if>
                    <c:if test="${isFirstPhoto}">
                        <div class="item active">
                            <img src="${image.getPathToImage()}"
                                 alt="" style="width:100%; height:100%;"/>
                        </div>
                        <c:set var="isFirstPhoto" scope="session"
                               value="${false}"/>
                    </c:if>
                </c:forEach>
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

    <div class="container">
        <div class="row">
            <div class="col-md-6 text-center">
                <h2><c:out value="${homestead.getTitle()}"/></h2>
                <p><c:out value="${homestead.getDescription()}"/></p>
                <dl>
                    <dt><fmt:message key="homesteadPrice"/></dt>
                    <dd>- <c:out value="${homestead.getPrice()}"/></dd>
                    <dt><fmt:message key="homesteadPeopleNumber"/></dt>
                    <dd>- <c:out value="${homestead.getPeopleNumber()}"/></dd>
                </dl>
            </div>

            <div class="col-md-6 text-center">
                <h2><fmt:message key="homesteadOwner"/></h2>
                <dl>
                    <dt><fmt:message key="name"/></dt>
                    <dd>- <c:out
                            value="${homestead.getOwner().getName()}"/></dd>
                    <dt><fmt:message key="surname"/></dt>
                    <dd>- <c:out
                            value="${homestead.getOwner().getSurname()}"/></dd>
                    <dt><fmt:message key="phoneNumber"/></dt>
                    <dd>- <c:out
                            value="${homestead.getOwner().getPhone()}"/></dd>
                </dl>
            </div>
        </div>
    </div>

    <hr>
    <c:if test="${profile == null}">
        <div class="alert alert-info container text-center">
            <strong>Info!</strong> If you want to sent a review or rent a
            homestead
            you should log in.
        </div>
    </c:if>

    <c:if test="${profile != null && profile.getRole().equals(Role.USER)}">
        <div class="form-group container">

            <c:url value="/authorized_user/reservationInfo.html"
                   var="reservationInfoUrl"/>
            <form action="${reservationInfoUrl}">
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

            <c:url value="/authorized_user/homesteadReview.html"
                   var="homesteadReviewUrl"/>

            <form action="${homesteadReviewUrl}">
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
    </c:if>

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

                            <div class="row container">
                                <div class="col-md-1">
                                    <c:if test="${profile.getRole().equals(Role.ADMINISTRATOR)}">

                                        <c:set var="deleteReview" scope="page">
                                            <fmt:message key="delete"/>
                                        </c:set>

                                        <c:url value="/admin/deleteReview.html"
                                               var="deleteReviewUrl"/>

                                        <form method="post"
                                              action="${deleteReviewUrl}"
                                              class="container text-left">
                                            <input type="hidden"
                                                   value="${review.getId()}"
                                                   name="reviewIdentity"/>
                                            <input type="submit"
                                                   class="btn btn-primary"
                                                   value="${deleteReview}"/>
                                        </form>
                                    </c:if>
                                </div>

                                <div class="col-md-10 text-center">
                                    <label><fmt:message key="login"/>: </label>
                                    <div>
                                            ${review.getUserName()}
                                    </div>
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

<cng:footer-tag language="${locale}"/>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
    <jsp:include page="../js/log_in_validation.js"/>
</script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<script type="text/javascript"
        src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script type="text/javascript"
        src="http://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.0/js/bootstrapValidator.min.js"></script>
</body>
</html>