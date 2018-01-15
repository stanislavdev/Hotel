<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: stanislav
  Date: 08.01.18
  Time: 0:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<h2>Create Your Account</h2>

<form action="/hotel/sign-in" method="POST">
    <input type="hidden" name="command" value="registration">
    Email:<input name="emailRegistration" type="email">
    Password:<input name="passwordRegistration" type="password">
    <button type="submit">Continue</button>
</form>
</body>
</html>
