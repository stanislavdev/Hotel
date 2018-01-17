<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="message" var="msg"/>
<html>
<head>
    <title><fmt:message bundle="${msg}" key="registration.title"/></title>
    <link href="/css/style.css" rel="stylesheet" type="text/css">
    <link href="/css/materialize/css/materialize.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="card-panel">
     <span class="blue-text text-darken-2">
           <h4><fmt:message bundle="${msg}" key="registration.title"/></h4>
     </span>
</div>
<div class="bgimg-1">
    <div class="caption">
        <br>
        <form action="/hotel/sign-in" method="POST">
            <input type="hidden" name="command" value="registration">
            <span class="border">
            <label><fmt:message bundle="${msg}" key="email.input"/>
                <input class="inputText" name="emailRegistration" type="email"/>
                </label>
                <label><fmt:message bundle="${msg}" key="password.input"/>
                <input class="inputText" name="passwordRegistration" type="password">
            </label>
            </span><br>
            <c:if test="${requestScope.exception != null}">
                <label class="exceptionLabel">
                    <span>
                        <fmt:message bundle="${msg}" key="registration.exception"/>
                    </span>
                </label>
            </c:if>
            <br>
            <button class="button" type="submit">
             <span>
                 <label>
                     <fmt:message bundle="${msg}" key="button.continue"/>
                 </label>
             </span>
            </button>
        </form>
    </div>
</div>
</body>
</html>
