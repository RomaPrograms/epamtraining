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
            <label for="sign_up_login"><fmt:message key="login"/></label>
            <input id="sign_up_login" type="text" name="login"
                   value="${userInfo.getLogin()}"
                   placeholder="${enterLogin}" class="form-control">
        </div>
        <div class="form-group">
            <label for="sign_up_password"><fmt:message key="password"/></label>
            <input id="sign_up_password" type="password" name="password"
                   placeholder="${enterPassword}" class="form-control"
                   value="${userInfo.getPassword()}">
        </div>
        <div class="form-group">
            <label for="sign_up_name"><fmt:message key="name"/></label>
            <input id="sign_up_name" type="text" name="name"
                   value="${userInfo.getName()}"
                   placeholder="${enterName}" class="form-control">
        </div>
        <div class="form-group">
            <label for="sign_up_surname"><fmt:message key="surname"/></label>
            <input id="sign_up_surname" type="text" name="surname"
                   value="${userInfo.getSurname()}"
                   placeholder="${enterSurname}" class="form-control">
        </div>
        <div class="form-group">
            <label for="sign_up_phoneNumber"><fmt:message
                    key="phoneNumber"/></label>
            <input id="sign_up_phoneNumber" type="text" name="phoneNumber"
                   placeholder="${enterPhoneNumber}"
                   value="${userInfo.getPhone()}"
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
            <button type="submit" class="btn btn-primary"><fmt:message
                    key="registration"/></button>
        </div>

    </form>
</div>

<cng:footer-tag language="${locale}"/>

<script type="text/javascript">
    <jsp:include page="../js/sign_up_validation.js"/>
    <jsp:include page="../js/log_in_validation.js"/>
</script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<script type="text/javascript"
        src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script type="text/javascript"
        src="http://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.0/js/bootstrapValidator.min.js"></script>

</body>
</html>