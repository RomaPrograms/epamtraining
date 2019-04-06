
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<table align="center">
    <tr>
        <td colspan="9"><h1>Computer elements</h1></td>
    </tr>
    <tr>
        <th>Name of element</th>
        <th>Origin</th>
        <th>Price</th>
        <th>Type</th>
        <th>Power usage</th>
        <th>Cooler</th>
        <th>Component group</th>
        <th>Port</th>
        <th>Critical</th>
        <th>Date of delivery</th>
    </tr>

    <c:forEach var="elem" items="${res}" varStatus="status">
        <tr>
            <td><c:out value="${ elem.getName()}" /></td>
            <td><c:out value="${ elem.getOrigin()}" /></td>
            <td><c:out value="${ elem.getPrice()}" /></td>
            <td><c:out value="${ elem.getType().isCooler()}" /></td>
            <td><c:out value="${ elem.getType().isCritical()}" /></td>
            <td><c:out value="${ elem.getType().getPowerUsage()}" /></td>
            <td><c:out value="${ elem.getType().getGroupOfComplects()}" /></td>
            <td><c:out value="${ elem.getType().getPort().toString()}" /></td>
            <td><c:out value="${ elem.getDateOfDelivery().getCalendarType()}" /></td>
        </tr>
    </c:forEach>
</table>
</body></html>