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

    <link rel="stylesheet" type="text/css" href="/css/styles.css">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="/vendors/formvalidation/dist/css/formValidation.min.css">
    <link rel="stylesheet"
          href="//cdn.jsdelivr.net/jquery.bootstrapvalidator/0.5.2/css/bootstrapValidator.min.css"/>
    <link rel="stylesheet"
          href="http://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.2/css/bootstrapValidator.min.css"/>
    <link href="/css/dataTables.bootstrap.min.css" rel="stylesheet"/>
</head>

<ctg:navbar-tag profile="${profile}" language="${locale}"
                logInMessage="${logInMessage}"/>

<div id="search_row" class="container">

    <c:set var="enterLoginLabel" scope="page">
        <fmt:message key="enterLogin"/>
    </c:set>

    <c:set var="deleteUser" scope="page">
        <fmt:message key="delete"/>
    </c:set>

    <c:set var="findUser" scope="page">
        <fmt:message key="findUser"/>
    </c:set>

    <c:url value="/admin/findUserByLogin.html"
           var="findUserUrl"/>

    <div class="row">
        <div class="col-md-6">
            <form method="post" action="${findUserUrl}">
                <div class="col-md-12">
                    <label><fmt:message key="login"/>:</label>
                    <div class="row">
                        <div class="col-md-6">
                            <input type="text" placeholder="${enterLoginLabel}"
                                   id="userLogin" name="userLogin"
                                   class="form-control" value="${userLogin}">
                        </div>

                        <div class="col-md-6">
                            <input type="submit" class="btn btn-primary"
                                   value="${findUser}">
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="container catalog">
    <hr/>
    <c:url value="/admin/deleteUser.html" var="userDeleteUrl"/>

    <c:if test="${res == null || res.size() == 0}">
        <div class="alert alert-warning">
            <strong>Warning!</strong> Sorry but we couldn't find any user
            by your criteria :(
        </div>
    </c:if>
    <c:if test="${res.size() != 0}">
        <c:forEach var="elem" items="${res}" varStatus="status">
            <div class="row">
                <input type="hidden" name="homesteadIdentity"
                       value="${elem.getId()}"/>
                <div class="col-md-4">
                    <img width="300px" height="200px" class="img-rounded"
                         src="../img/avatar.png"/>
                </div>
                <div class="col-md-8">
                    <c:if test="${elem.getRole().equals(Role.OWNER)}">
                        <h2><fmt:message key="homesteadOwner"/></h2>
                    </c:if>
                    <c:if test="${elem.getRole().equals(Role.USER)}">
                        <h2><fmt:message key="user"/></h2>
                    </c:if>
                    <dl>
                        <dt><fmt:message key="login"/></dt>
                        <dd>- <c:out value="${elem.getLogin()}"/></dd>
                        <dt><fmt:message key="name"/></dt>
                        <dd>- <c:out value="${elem.getName()}"/></dd>
                        <dt><fmt:message key="surname"/></dt>
                        <dd>- <c:out value="${elem.getSurname()}"/></dd>
                        <dt><fmt:message key="phoneNumber"/></dt>
                        <dd>- <c:out
                                value="${elem.getPhone()}"/></dd>
                    </dl>

                    <form method="post" action="${userDeleteUrl}">
                        <input type="hidden" name="userIdentity"
                               value="${elem.getId()}"/>

                        <input type="submit" class="btn btn-default"
                               value="${deleteUser}&raquo;"/>
                    </form>
                </div>
                </form>
            </div>
            <hr>
        </c:forEach>
    </c:if>
</div>

<cng:footer-tag language="${locale}"/>

<script src="/js/jquery.js"></script>
<script src="/js/jquery.dataTables.min.js"></script>
    <script src="/js/dataTables.bootstrap.min.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
    <jsp:include page="/js/log_in_validation.js"/>
</script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<script type="text/javascript"
        src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script type="text/javascript"
        src="http://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.0/js/bootstrapValidator.min.js"></script>
</body>
</html>