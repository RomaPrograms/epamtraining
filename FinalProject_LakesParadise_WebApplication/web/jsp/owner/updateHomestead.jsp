<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="cng" uri="customtags" %>
<%@ page import="by.training.lakes_paradise.db.entity.Role" %>
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

<c:if test="${!profile.getRole().equals(Role.OWNER)}">
    <jsp:forward page="/jsp/error.jsp"/>
</c:if>

<ctg:navbar-tag profile="${profile}" language="${locale}"
                logInMessage="${logInMessage}"/>

<div class="container" id="main_body">
    <c:set var="enterHomesteadName" scope="page">
        <fmt:message key="enterName"/>
    </c:set>

    <c:set var="enterDescription" scope="page">
        <fmt:message key="enterDescription"/>
    </c:set>

    <c:set var="enterPrice" scope="page">
        <fmt:message key="enterPrice"/>
    </c:set>

    <c:set var="enterPeopleNumber" scope="page">
        <fmt:message key="enterPeopleNumber"/>
    </c:set>

    <div class="page-header">
        <h1>
            <fmt:message key="updateInfo"/>
        </h1>
    </div>
    <form class="form-horizontal" role="form" id="homestead_reg_form"
          method="post">
        <input type="hidden" name="homesteadIdentity"
               value="${updatedHomestead.getId()}"/>

        <div class="form-group">
            <label for="name"><fmt:message key="homesteadName"/></label>
            <input id="name" type="text" name="name"
                   value="${updatedHomestead.getTitle()}"
                   placeholder="${enterHomesteadName}" class="form-control">
        </div>
        <div class="form-group">
            <label for="description"><fmt:message
                    key="homesteadDescription"/></label>
            <textarea class="form-control" rows="5" id="description"
                      name="description"
                      placeholder="${enterDescription}">${updatedHomestead.getDescription()}</textarea>
        </div>
        <div class="form-group">
            <label for="price"><fmt:message
                    key="homesteadPrice"/></label>
            <input id="price" type="text" name="price"
                   value="${updatedHomestead.getPrice()}"
                   placeholder="${enterPrice}" class="form-control">
        </div>
        <div class="form-group">
            <label for="peopleNumber"><fmt:message
                    key="homesteadPeopleNumber"/></label>
            <input id="peopleNumber" type="text" name="peopleNumber"
                   placeholder="${enterPeopleNumber}" class="form-control"
                   value="${updatedHomestead.getPeopleNumber()}">
        </div>

        <c:if test="${successMessage != null}">
            <div class="alert alert-success form-group">
                <strong>Success!</strong> <c:out value="${successMessage}"/>
            </div>
        </c:if>

        <div class="form-group">
            <button type="submit" class="btn btn-primary">Update</button>
        </div>

    </form>
</div>

<cng:footer-tag language="${locale}"/>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
    <jsp:include page="../../js/log_in_validation.js"/>
    <jsp:include page="../../js/homestead_validation.js"/>
</script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<script type="text/javascript"
        src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script type="text/javascript"
        src="http://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.0/js/bootstrapValidator.min.js"></script>
</body>
</html>