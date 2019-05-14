<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="cng" uri="customtags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<fmt:setBundle basename="property.text"/>

<html>
<head>
    <meta charset="utf-8">
    <title>Lakes Paradise site</title>

    <style>
        .mainSection {
            font-family: sans-serif;
            font-size: 11pt;
            background-image: url(../img/mainPicture.jpg);
            background-repeat: no-repeat;
            background-size: cover;
            background-attachment: fixed;
        }

        .mainSectionText {
            color: white;
        }

        #text {
            color: #00b0ff;
        }
    </style>

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

</head>
<body>

<ctg:navbar-tag profile="${profile}" language="${locale}"
                logInMessage="${logInMessage}"/>

<c:if test="${incorrectPageMessage != null}">
    <div class="container text-center">
        <div class="alert alert-warning">
            <strong>Warning!</strong> ${incorrectPageMessage}.
        </div>
    </div>
</c:if>

<div class="jumbotron mainSection">
    <div class="container">
        <h1 id="text"><fmt:message key="navbarWelcome"/>!</h1>
        <p class="mainSectionText">
            <fmt:message key="mainPageIntroduction"/>
        </p>
    </div>
</div>

<div class="container">
    <div class="row">
        <div class="col-md-6">
            <h2><fmt:message key="rentCondition"/></h2>
            <p><fmt:message key="mainPageRentConditionInfo"/>
            </p>
        </div>
        <div class="col-md-6">
            <h2><fmt:message key="showHomesteads"/></h2>
            <p><fmt:message key="mainPageShowHomesteadsInfo"/></p>
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