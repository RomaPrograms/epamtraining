<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="by.training.lakes_paradise.db.entity.Role" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="cng" uri="customtags" %>
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
        #body {
            font-family: sans-serif;
            font-size: 11pt;
            background-image: url(../img/myImages/mainPicture.jpg);
            background-repeat: no-repeat;
            background-size: cover;
            background-attachment: fixed;
        }

        #homestead_catalog {
            background-color: white;
            padding-top: 25px;
            padding-bottom: 25px;
        }

        #search_row {
            background-color: white;
            padding-top: 10px;
            padding-bottom: 100px;
            border: none;
            border-bottom: 1px solid deepskyblue;
            outline: none;
            height: 40px;
            font-size: 16px;
        }

    </style>
</head>

<body id="body">

<ctg:navbar-tag profile="${profile}" language="${locale}"
                logInMessage="${logInMessage}"/>

<div id="search_row" class="container">

    <c:set var="enteredName" scope="page">
        <fmt:message key="enterName"/>
    </c:set>

    <c:set var="search" scope="page">
        <fmt:message key="homesteadSearch"/>
    </c:set>

    <c:set var="additionalInformation" scope="page">
        <fmt:message key="homesteadKnowMore"/>
    </c:set>

    <c:set var="deleteHomestead" scope="page">
        <fmt:message key="delete"/>
    </c:set>

    <c:set var="minPrice" scope="page">
        <fmt:message key="maxPrice"/>
    </c:set>

    <c:set var="maxPrice" scope="page">
        <fmt:message key="minPrice"/>
    </c:set>

    <c:url value="/findHomesteadByCategory.html"
           var="findHomesteadByCategoryUrl"/>

    <div class="row">
        <form method="post" action="${findHomesteadByCategoryUrl}">
            <div class="col-md-3 form-group">
                <label for="homesteadName"><fmt:message key="name"/>:</label>
                <input type="text" placeholder="${enteredName}"
                       id="homesteadName" name="homesteadName"
                       class="form-control">
            </div>

            <div class="col-md-2 form-group">
                <label for="minPrice"><fmt:message
                        key="homesteadPrice"/>:</label>
                <input type="text" placeholder="${minPrice}" id="minPrice"
                       class="form-control">
                <input type="text" placeholder="${maxPrice}" id="maxPrice"
                       class="form-control">
            </div>

            <div class="col-md-2 form-group">
                <br/><input type="submit" class="btn btn-primary"
                            value="${search}">
            </div>
        </form>
    </div>
</div>

<div id="homestead_catalog" class="container">

    <c:url value="/homesteadInfo.html" var="homesteadInfoUrl"/>
    <c:url value="/owner/deleteHomestead.html" var="homesteadDeleteUrl"/>

    <c:if test="${res.size() == 0}">
        <div class="alert alert-warning">
            <strong>Warning!</strong> Sorry but we couldn't find any homestead
            by your criteria :(
        </div>
    </c:if>
    <c:if test="${res.size() != 0}">
        <c:forEach var="elem" items="${res}" varStatus="status">
            <div class="row">
                <form method="post" action="${homesteadInfoUrl}">
                    <input type="hidden" name="homesteadIdentity"
                           value="${elem.getId()}"/>
                    <div class="col-md-4">
                        <img width="300px" height="200px" class="img-rounded"
                             src="../img/1.1_farmstead.jpg"/>
                    </div>
                    <div class="col-md-8">
                        <h2><c:out value="${elem.getTitle()}"/></h2>
                        <p><c:out value="${elem.getDescription()}"/></p>
                        <dl>
                            <dt><fmt:message key="homesteadPrice"/></dt>
                            <dd>- <c:out value="${elem.getPrice()}"/></dd>
                            <dt><fmt:message key="homesteadPeopleNumber"/></dt>
                            <dd>- <c:out
                                    value="${elem.getPeopleNumber()}"/></dd>
                        </dl>

                        <input type="submit" class="btn btn-default"
                               value="${additionalInformation}&raquo;"/>

                        <c:if test="${profile.getRole().equals(Role.ADMINISTRATOR)}">
                            <form method="post" action="${homesteadDeleteUrl}">
                                <input type="hidden" name="homesteadIdentity"
                                       value="${elem.getId()}"/>

                                <input type="submit" class="btn btn-default"
                                       value="${deleteHomestead}&raquo;"/>
                            </form>
                        </c:if>
                    </div>
                </form>
            </div>
            <hr>
        </c:forEach>
    </c:if>
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