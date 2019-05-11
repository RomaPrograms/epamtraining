<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="cng" uri="customtags" %>
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

    <style>
        #homesteadButton {
            border: none;
            background-color: white;
        }
    </style>
</head>
<body>

<ctg:navbar-tag profile="${profile}" language="${locale}"
                logInMessage="${logInMessage}"/>


<div class="container">
    <div class="page-header">
        <h1><fmt:message key="personalCabinet"/>
            <small><fmt:message key="personalCabinetInfo"/></small>
        </h1>
    </div>
    <div class="form-group">
        <label><fmt:message key="login"/>: </label>
        ${profile.getLogin()}
    </div>
    <div class="form-group">
        <label><fmt:message key="name"/>: </label>
        ${user.getName()}
    </div>
    <div class="form-group">
        <label><fmt:message key="surname"/>: </label>
        ${user.getSurname()}
    </div>
    <div class="form-group">
        <label><fmt:message key="phoneNumber"/></label>
        ${user.getPhone()}
    </div>

    <div class="panel-group form-group">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title text-center">
                    <a data-toggle="collapse" href="#collapse1"><fmt:message
                            key="showDates"/></a>
                </h4>
            </div>
            <div id="collapse1" class="panel-collapse collapse container">
                <ul class="list-group">
                    <li class="list-group-item">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th><fmt:message key="startRenting"/></th>
                                <th><fmt:message key="endDate"/></th>
                                <th><fmt:message key="homesteads"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <form method="post" action="/homesteadInfo.html">
                                <c:forEach var="order" items="${orders}"
                                           varStatus="status">
                                    <tr>
                                        <td>${order.getStartRentingByPattern()}</td>
                                        <td>${order.getEndRentingByPattern()}</td>
                                        <input type="hidden"
                                               value="${order.getHomestead().getId()}"
                                               name="homesteadIdentity"/>
                                        <td>
                                            <button id="homesteadButton">
                                                    ${order.getHomestead().getTitle()}
                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </form>
                            </tbody>
                        </table>
                    </li>
                </ul>
            </div>
        </div>
    </div>

    <c:set var="update" scope="page">
        <fmt:message key="update"/>
    </c:set>

    <c:set var="delete" scope="page">
        <fmt:message key="deleteAccount"/>
    </c:set>

    <c:url value="/authorized_user/updateUserInfo.html"
           var="updateUserInfoUrl"/>

    <c:url value="/authorized_user/deleteUserAccount.html"
           var="deleteUserAccountUrl"/>

    <div class="form-group form-horizontal">
        <form class="form-horizontal" role="form" method="post"
              action="${updateUserInfoUrl}">
            <input type="submit" class="btn btn-primary"
                   value="${update}">
        </form>
        <form class="form-horizontal" role="form" method="post"
              action="${deleteUserAccountUrl}">
            <input type="submit" class="btn btn-primary"
                   value="${delete}">
        </form>
    </div>
</div>

<cng:footer-tag language="${locale}"/>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript"
        src="http://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.0/js/bootstrapValidator.min.js"></script>

</body>
</html>