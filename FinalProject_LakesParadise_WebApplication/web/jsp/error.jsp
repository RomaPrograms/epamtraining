<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %>

<c:out value="An exception occured!"/>
<c:out value="${exception}"/>

<c:redirect url="/error.html"/>