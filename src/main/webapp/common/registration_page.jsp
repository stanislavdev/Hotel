<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="message" var="msg"/>
<html>
<head>
    <title><fmt:message bundle="${msg}" key="registration.title"/></title>
    <link href="/css/materialize/css/materialize.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="card-panel">
     <span class="blue-text text-darken-2">
           <h4><fmt:message bundle="${msg}" key="registration.title"/></h4>
     </span>
    <form action="/" method="post">
        <input type="hidden" name="command" value="login_page">
        <button class="btn-floating btn-large waves-effect waves-light blue left" type="submit">
            <img src="/css/ic_home_white_24dp_1x.png">
        </button>
    </form>
</div>
<br><br>
<div class="container">
    <form action="/hotel/home" method="POST">
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
            <h6 class="flow-text red-text">
                <fmt:message bundle="${msg}" key="registration.exception"/>
            </h6>
        </c:if>
        <br>
        <button class="btn waves-effect waves-light blue darken-2" type="submit">
            <fmt:message bundle="${msg}" key="button.continue"/>
        </button>
    </form>
</div>
</body>
</html>
