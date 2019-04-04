<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>
<h1>Выберите тип парсинга: </h1>
<jsp:useBean id="calendar" class="java.util.GregorianCalendar"/> <!--Здесь мы объявляем переменную calendar-->
<form name="Simple" action="timeaction" method="POST">
    <input type="hidden" name="time" value="${calendar.timeInMillis}"/>
    <input type="submit" name="button" value="SAX Parser"/>
    <input type="submit" name="button" value="DOM Parser"/>
    <input type="submit" name="button" value="StAX Parser"/>
</form>
</body></html>