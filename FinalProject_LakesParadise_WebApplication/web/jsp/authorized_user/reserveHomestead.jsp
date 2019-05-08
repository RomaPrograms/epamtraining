<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="cng" uri="customtags" %>
<%@ page errorPage="../error.jsp" %>
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
    </style>
</head>

<body id="body">

<ctg:navbar-tag profile="${profile}" language="${locale}" logInMessage="${logInMessage}"/>

<div class="container">
    <c:set var="startRenting" scope="page">
        <fmt:message key="startRenting"/>
    </c:set>

    <c:set var="endRenting" scope="page">
        <fmt:message key="endRenting"/>
    </c:set>

    <c:url value="/authorized_user/reservation.html" var="reservationUrl"/>

    <form method="post" action="${reservationUrl}" id="reg_form">
        <div class="col-md-3 form-group">
            <label for="startDate"><fmt:message key="startRenting"/></label>
            <input type="date" placeholder="${startRenting}"
                   id="startDate" name="startDate"
                   class="form-control">
        </div>

        <div class="col-md-3 form-group">
            <label for="endDate"><fmt:message key="endRenting"/></label>
            <input type="date" placeholder="${endRenting}" id="endDate"
                   class="form-control" name="endDate">
        </div>

        <br/>
        <div class="col-md-3 form-group">
            <button type="submit" class="btn btn-primary"><fmt:message key="rentHomestead"/>
            </button>
        </div>
        <c:if test="${registerErrorMessage != null}">
            <div class="alert alert-danger">
                <strong>Issue!</strong>
                <c:out value="${registerErrorMessage}"/>
            </div>
        </c:if>
        <c:if test="${registerSuccessMessage != null}">
            <div class="alert alert-success">
                <strong>Success!</strong>
                <c:out value="${registerSuccessMessage}"/>
            </div>
        </c:if>
    </form>
</div>

<div class="panel-group container">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title text-center">
                <a data-toggle="collapse" href="#collapse1"><fmt:message key="showDates"/></a>
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
                            <th><fmt:message key="login"/></th>
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

<cng:footer-tag language="${locale}"/>

<script type="text/javascript">
    <jsp:include page="../../js/registration_validation.js"/>
    <jsp:include page="../../js/log_in_validation.js"/>
</script>

</body>
</html>