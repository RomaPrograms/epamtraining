<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %>
<h3>Sorry an exception occured!  and <c:out value="message"/></h3>
Exception is: <%= exception %>