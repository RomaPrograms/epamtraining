<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page info="This jsp page was written by Roman Semizhon." %>
<%@ page isELIgnored="false" %>
<%@ page errorPage="result.jsp" %>
<!DOCTYPE html>
<html>
<body>

<a href="timeaction?lang=en_US">english</a> |
<a href="timeaction?lang=be_BY">belorussian</a> |
<a href="timeaction?lang=ru_RU">russian</a>

<c:if test="${lang != null}">
    <h1>${ lang}</h1>
</c:if>

<c:if test="${lang == null}">
    <c:redirect url="http://localhost:8080/webParsingApp/timeaction?lang=en_US"/>
</c:if>

<form name="Simple" action="timeaction" method="GET">
    <input type="submit" name="button1" value="SAX Parser"/>
    <input type="submit" name="button2" value="DOM Parser"/>
    <input type="submit" name="button3" value="StAX Parser"/>
</form>
</body>
</html>