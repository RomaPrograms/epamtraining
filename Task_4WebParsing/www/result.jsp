<%@ page import="by.training.webparsing.entity.Device" %>
<%@ page import="java.util.List" %>
<%@ page import="by.training.webparsing.entity.PeripheralDevice" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>
        Core: forEach
    </title>
    <link rel="stylesheet" type="text/css" href="css/styles.css"/>
</head>
<body>

<%--<a href=${path}>english</a>
<br><a href=${"timeaction?button2=DOM+Parser&lang=be_BY"}>belorussian</a>
<br><a href=${"timeaction?button2=DOM+Parser&lang=ru_RU"}>russian</a>

<c:if test="${lang == null}">
    <c:redirect url="http://localhost:8080/webParsingApp/timeaction?button2=DOM+Parser&lang=en_US"/>
</c:if>--%>

<c:if test="${lang == null}">
    <c:redirect url="http://localhost:8080/webParsingApp/timeaction?button2=DOM+Parser&lang=en_US"/>
</c:if>

<table align="center">
    <tr>
        <td colspan="12"><h1>Computer elements</h1></td>
    </tr>
    <tr>
        <th>${nameOfElemProp}</th>
        <th>${originProp}</th>
        <th>${priceProp}</th>
        <th>${typeProp}</th>
        <th>${powerUsageProp}</th>
        <th>${coolerProp}</th>
        <th>${componentGroupProp}</th>
        <th>${portProp}</th>
        <th>${criticalProp}</th>
        <th>${connectionProp}</th>
        <th>${versionProp}</th>
        <th>${dateOfDeliveryProp}</th>
    </tr>

    <c:set var="peripheralDevice" scope="page" value="PeripheralDevice"/>
    <c:set var="innerDevice" scope="page" value="InnerDevice"/>
    <jsp:useBean id="df" class="java.text.SimpleDateFormat"/>
    <%df.applyPattern("dd MMM yyy");%>
    <c:forEach var="elem" items="${res}" varStatus="status">
        <tr>
            <td><c:out value="${ elem.getName()}" /></td>
            <td><c:out value="${ elem.getOrigin()}" /></td>
            <td><c:out value="${ elem.getPrice()}" /></td>
            <c:if test="${ elem.getClass().getSimpleName().equals(peripheralDevice)}">
                <td><c:out value="${ peripheralDevice}" /></td>
            </c:if>
            <c:if test="${ elem.getClass().getSimpleName().equals(innerDevice)}">
                <td><c:out value="${ innerDevice}" /></td>
            </c:if>
            <td><c:out value="${ elem.getType().isCooler()}" /></td>
            <td><c:out value="${ elem.getType().isCritical()}" /></td>
            <td><c:out value="${ elem.getType().getPowerUsage()}" /></td>
            <td><c:out value="${ elem.getType().getGroupOfComplects()}" /></td>
            <td><c:out value="${ elem.getType().getPort().toString()}" /></td>
            <c:if test="${elem.getClass().getSimpleName().equals(peripheralDevice)}">
                <td><c:out value="${ elem.getConnection().getValue()}"/></td>
                <td><c:out value=""/></td>
            </c:if>
            <c:if test="${ elem.getClass().getSimpleName().equals(innerDevice)}">
                <td><c:out value="" /></td>
                <td><c:out value="${ elem.getVersion()}" /></td>
            </c:if>
            <td><c:out value="${ df.format(elem.getDateOfDelivery().getTime())}" /></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>