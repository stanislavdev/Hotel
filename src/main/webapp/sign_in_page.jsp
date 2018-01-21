<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="message" var="msg"/>
<html>
<head>
    <title><fmt:message bundle="${msg}" key="sign-in.title"/></title>
    <link href="/css/materialize/css/materialize.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="card-panel">
   <span class="blue-text text-darken-2">
       <h5><fmt:message bundle="${msg}" key="sign-in.title"/></h5>
   </span>
    <button class="btn-floating btn-large blue-grey darken-1 right">
        <form action="/hotel/locale">
            <input type="hidden" name="command" value="change-locale">
            <input type="hidden" name="currentPage" value="/sign_in.jsp">
            <select class="browser-default  blue-grey darken-1 right" name="language" onclick="this.form.submit()">
                <option value="ua">ua</option>
                <option value="en">en</option>
            </select>
        </form>
    </button>
</div>
<div class="container">
    <div class="card-panel hoverable">
        <form method="POST" action="/hotel/home">
            <input type="hidden" name="command" value="sign_in">
            <label><fmt:message bundle="${msg}" key="email.input"/>
                <input type="email" name="email"/>
            </label>
            <label><fmt:message bundle="${msg}" key="password.input"/>
                <input type="password" name="password">
            </label>
            <div class="center-align">
                <button class="btn waves-effect waves-light blue darken-2 center" type="submit">
                    <fmt:message bundle="${msg}" key="button.ok"/>
                </button>
            </div>
        </form>
        <div class="center-align">
            <form method="POST" action="/hotel/registration-page">
                <input type="hidden" name="command" value="registration_page">
                <button class="btn waves-effect waves-light blue darken-2 center" type="submit">
                    <fmt:message bundle="${msg}" key="registration.button"/>
                </button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
