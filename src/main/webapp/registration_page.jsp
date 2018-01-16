<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="message" var="msg"/>
<html>
<head>
    <title><fmt:message bundle="${msg}" key="registration.title"/></title>
    <link href="/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="bgimg-1">
    <div class="caption">
        <h3><fmt:message bundle="${msg}" key="registration.title"/></h3>
        <br>
        <form action="/hotel/sign-in" method="POST">
            <input type="hidden" name="command" value="registration">
            <span class="border">
            <label><fmt:message bundle="${msg}" key="email.input"/>
                <input class="inputText" name="emailRegistration" type="email">
            </label>
            </span>
            <br>
            <span class="border">
            <label><fmt:message bundle="${msg}" key="password.input"/>
                <input class="inputText" name="passwordRegistration" type="password">
            </label>
            </span><br><br>
            <button class="button" type="submit">
             <span>
                  <fmt:message bundle="${msg}" key="button.continue"/>
             </span>
            </button>
        </form>
    </div>
</div>
</body>
</html>
