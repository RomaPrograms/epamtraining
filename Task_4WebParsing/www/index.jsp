<%@ page import="java.util.Locale" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page info="This jsp page was written by Roman Semizhon."%>
<%@ page language="java"%>
<%@ page isELIgnored="false"%> <%--With help of it we can ignore expression language,
 by defalut it - false--%>
<%@ page errorPage="result.jsp"%> <%--redirect our current page if we got an exception--%>

<%--Also we have an include tag like <%@ include .... %> which we can use if we need to include
some page to our jsp page--%>
<!DOCTYPE html>
<html>
<body>
<jsp:useBean id="title" class="java.lang.String"/>
<%--<%! ResourceManager manager = ResourceManager.INSTANCE;
    Locale locale = new Locale("be","BY");
    manager.changeResource(locale);%>--%>
<%! Locale locale = new Locale("be","BY");
    ResourceBundle resource = ResourceBundle.getBundle("property.text", locale);;
    %>

<h1><%= resource.getString("str1")%></h1>
<form name="Simple" action="timeaction" method="POST">
    <input type="submit" name="button1" value="SAX Parser"/>
    <input type="submit" name="button2" value="DOM Parser"/>
    <input type="submit" name="button3" value="StAX Parser"/>
</form>
</body></html>