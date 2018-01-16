<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="message" var="msg"/>
<html>
<head>
    <title><fmt:message bundle="${msg}" key="sign-in.title"/></title>
    <link href="/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="bgimg-1">
    <div class="caption">
        <h3>
            <fmt:message bundle="${msg}" key="sign-in.title"/>
        </h3>

        <form method="POST" action="/hotel/home">
            <input type="hidden" name="command" value="sign_in">
            <span class="border">
                <label><fmt:message bundle="${msg}" key="email.input"/>
                    <input type="email" name="email" class="inputText"/>
                </label>
            </span>
            <br>
            <span class="border">
                <label><fmt:message bundle="${msg}" key="password.input"/>
                    <input type="password" name="password" class="inputText">
                 </label>
            </span>
            <br>
            <button class="button" type="submit">
                <span>
                    <fmt:message bundle="${msg}" key="button.ok"/>
                </span>
            </button>
        </form>
        <form method="POST" action="/hotel/registration-page">
            <button class="button" type="submit">
                <fmt:message bundle="${msg}" key="registration.button"/>
            </button>
            <input type="hidden" name="command" value="registration_page">
        </form>
    </div>
    <div class="language">
        <form action="/hotel/locale">
            <input type="hidden" name="command" value="change-locale">
            <input type="hidden" name="currentPage" value="/sign_in.jsp">
            <select name="language" onclick="this.form.submit()">
                <option value="ua">ua</option>
                <option value="en">en</option>
            </select>
        </form>
    </div>
</div>
</body>
</html>
