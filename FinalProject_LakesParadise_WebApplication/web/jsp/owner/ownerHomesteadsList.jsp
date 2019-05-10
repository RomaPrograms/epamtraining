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

    ///////////////////////////////////////
    <script type="text/javascript"
            src="http://code.jquery.com/jquery-1.10.2.js"></script>
    <script type="text/javascript"
            src="http://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.0/js/bootstrapValidator.min.js"></script>

    <style>
        #body {
            font-family: sans-serif;
            font-size: 11pt;
        }

        #homestead_catalog {
            background-color: white;
            padding-top: 25px;
            padding-bottom: 25px;
        }

    </style>
</head>

<body id="body">

<ctg:navbar-tag profile="${profile}" language="${locale}"
                logInMessage="${logInMessage}"/>

<div class="container">

    <c:url value="/owner/addHomestead.html" var="addHomesteadUrl"/>

    <button class="btn btn-default" name="add">
        <a href="${addHomesteadUrl}"><fmt:message key="addHomestead"/></a>
    </button>
</div>

<div id="homestead_catalog" class="container">

    <c:url value="/homesteadInfo.html" var="homesteadInfoUrl"/>
    <c:url value="/owner/deleteHomestead.html" var="deleteHomesteadUrl"/>
    <c:url value="/owner/updateHomestead.html" var="updateHomesteadUrl"/>
    <c:url value="/homesteadInfo.html" var="homesteadInfoUrl"/>
    <c:url value="/owner/addPhoto.html" var="addPhotoUrl"/>

    <c:set var="knowMore" scope="page">
        <fmt:message key="homesteadKnowMore"/>
    </c:set>

    <c:set var="delete" scope="page">
        <fmt:message key="delete"/>
    </c:set>

    <c:set var="update" scope="page">
        <fmt:message key="update"/>
    </c:set>

    <c:forEach var="elem" items="${res}" varStatus="status">
        <div class="row">
            <div class="col-md-4">
                <img width="300px" height="200px" class="img-rounded"
                     src="../../img/1.1_farmstead.jpg"/>
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
                <form method="post" action="${homesteadInfoUrl}">
                    <input type="hidden" name="homesteadIdentity"
                           value="${elem.getId()}"/>
                    <input type="submit" class="btn btn-default" name="show"
                           value="${knowMore}"/>
                </form>
                <form method="post" action="${deleteHomesteadUrl}">
                    <input type="hidden" name="homesteadIdentity"
                           value="${elem.getId()}"/>
                    <input type="submit" class="btn btn-default" name="delete"
                           value="${delete}"/>
                </form>
                <form method="post" action="${updateHomesteadUrl}">
                    <input type="hidden" name="homesteadIdentity"
                           value="${elem.getId()}"/>
                    <input type="submit" class="btn btn-default" name="update"
                           value="${update}"/>
                </form>
                <form method="post" action="${addPhotoUrl}">

                    <input type="hidden" name="homesteadIdentity"
                           value="${elem.getId()}"/>
                    <input type="submit" class="btn btn-default" name="update"
                           value="Добавить фотографию"/>
                    <!-- COMPONENT START -->
                    <input class="btn btn-default btn-choose" name="photo"
                           type="file" value="Choose file"
                           accept=".jpg, .jpeg, .png"/>
                </form>
            </div>
        </div>
        <hr>
    </c:forEach>
</div>

<cng:footer-tag language="${locale}"/>

<script type="text/javascript">
    <jsp:include page="../../js/log_in_validation.js"/>
</script>

</body>
</html>