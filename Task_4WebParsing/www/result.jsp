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
<table align="center">
    <tr>
        <td colspan="12"><h1>Computer elements</h1></td>
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
        <th>Connection</th>
        <th>Version</th>
        <th>Date of delivery</th>
    </tr>

    <c:set var="peripheralDevice" scope="page" value="PeripheralDevice"/>
    <c:set var="innerDevice" scope="page" value="InnerDevice"/>
    <jsp:useBean id="df" class="java.text.SimpleDateFormat" scope="page"/>
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
                <td><c:out value="${ elem.getConnection().toString()}"/></td>
                <td><c:out value=""/></td>
            </c:if>
            <c:if test="${ elem.getClass().getSimpleName().equals(innerDevice)}">
                <td><c:out value="" /></td>
                <td><c:out value="${ elem.getVersion()}" /></td>
            </c:if>
            <td><c:out value="${ df.format(elem.getDateOfDelivery().getTime())}" /></td>
        </tr>
    </c:forEach>
    <%--<% List<Device> list = (List<Device>) request.getAttribute("res");
        for (Device elem : list) {%>
    <tr>
        <td><%=elem.getName()%></td>
        <td><%=elem.getOrigin()%></td>
        <td><%=elem.getPrice()%></td>
        <%if (elem instanceof PeripheralDevice) {%>
        <td><%="Peripheral"%></td>
        <%} else {%>
        <td><%="Inner"%></td>
        <%}%>
        <td><%=elem.getType().isCooler()%></td>
        <td><%=elem.getType().isCritical()%></td>
        <td><%=elem.getType().getPowerUsage()%></td>
        <td><%=elem.getType().getGroupOfComplects()%></td>
        <td><%=elem.getType().getPort().toString()%></td>
        <td><%=elem.getDateOfDelivery().getCalendarType()%></td>
    </tr>
    <%}%>--%>
</table>
</body>
</html>