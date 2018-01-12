<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: stanislav
  Date: 01.01.18
  Time: 22:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <h3>Sign in</h3>
    <form method="POST" action="/hotel/sign_in">
        Email: <input type="email" name="email"/>
        Password: <input type="password" name="password">
        <button type="submit">Ok</button>
    </form>

    <form method="POST" action="/hotel/registration_page">
        <button type="submit">Registration</button>
    </form>
</div>
</body>
</html>
