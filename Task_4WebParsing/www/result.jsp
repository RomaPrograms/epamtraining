<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="property.text"/>

<!DOCTYPE html>
<html>
<head>
    <title>
        Core: forEach
    </title>
    <link rel="stylesheet" type="text/css" href="css/styles.css"/>
</head>
<body>

<form name="Simple" action="timeaction" method="GET">

    <select name="chooser">
        <option value="en_EN">english</option>
        <option value="be_BY">belorussian</option>
        <option value="ru_RU">russian</option>
    </select>

    <input type="submit" value="change" required/>
</form>

<table align="center">
    <tr>
        <td colspan="12"><h1>Computer elements</h1></td>
    </tr>
    <tr>
        <th><fmt:message key="nameOfElemProp"/></th>
        <th><fmt:message key="originProp"/></th>
        <th><fmt:message key="priceProp"/></th>
        <th><fmt:message key="typeProp"/></th>
        <th><fmt:message key="powerUsageProp"/></th>
        <th><fmt:message key="coolerProp"/></th>
        <th><fmt:message key="componentGroupProp"/></th>
        <th><fmt:message key="portProp"/></th>
        <th><fmt:message key="criticalProp"/></th>
        <th><fmt:message key="connectionProp"/></th>
        <th><fmt:message key="versionProp"/></th>
        <th><fmt:message key="dateOfDeliveryProp"/></th>
    </tr>

    <c:set var="peripheralDevice" scope="page" value="PeripheralDevice"/>
    <c:set var="innerDevice" scope="page" value="InnerDevice"/>
    <jsp:useBean id="df" class="java.text.SimpleDateFormat"/>
    <%df.applyPattern("dd MMM yyy");%>
    <c:forEach var="elem" items="${res}" varStatus="status">
        <tr>
            <td><c:out value="${ elem.getName()}"/></td>
            <td><c:out value="${ elem.getOrigin()}"/></td>
            <td><c:out value="${ elem.getPrice()}"/></td>
            <c:if test="${ elem.getClass().getSimpleName()
            .equals(peripheralDevice)}">
                <td><c:out value="${ peripheralDevice}"/></td>
            </c:if>
            <c:if test="${ elem.getClass().getSimpleName()
            .equals(innerDevice)}">
                <td><c:out value="${ innerDevice}"/></td>
            </c:if>
            <td><c:out value="${ elem.getType().isCooler()}"/></td>
            <td><c:out value="${ elem.getType().isCritical()}"/></td>
            <td><c:out value="${ elem.getType().getPowerUsage()}"/></td>
            <td><c:out value="${ elem.getType().getGroupOfComplects()}"/></td>
            <td><c:out value="${ elem.getType().getPort().toString()}"/></td>
            <c:if test="${elem.getClass().getSimpleName()
            .equals(peripheralDevice)}">
                <td><c:out value="${ elem.getConnection().getValue()}"/></td>
                <td><c:out value=""/></td>
            </c:if>
            <c:if test="${ elem.getClass().getSimpleName()
            .equals(innerDevice)}">
                <td><c:out value=""/></td>
                <td><c:out value="${ elem.getVersion()}"/></td>
            </c:if>
            <td><c:out value="${ df.format(elem.getDateOfDelivery()
            .getTime())}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>