<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.training.webparsing.propertymanager.ResourceManager" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page info="This jsp page was written by Roman Semizhon."%>
<%@ page language="java"%>
<%@ page isELIgnored="false"%> <%--With help of it we can ignore expression
 language, by defalut it - false--%>
<%@ page errorPage="result.jsp"%> <%--redirect our current page if we got an
 exception--%>

<%--Also we have an include tag like <%@ include .... %> which we can use if we
 want to include some page to our jsp page--%>
<!DOCTYPE html>
<html>
<body>

<%ResourceManager manager = ResourceManager.INSTANCE;%>

<h1><%= manager.getString("str1")%></h1>
<form name="Simple" action="timeaction" method="GET">
    <input type="submit" name="button1" value="SAX Parser"/>
    <input type="submit" name="button2" value="DOM Parser"/>
    <input type="submit" name="button3" value="StAX Parser"/>
</form>
</body></html>