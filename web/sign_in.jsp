<%@ page import="model.services.impl.AccountServiceImpl" %>
<%@ page import="model.entities.User" %>
<%@ page import="model.dao.FactoryDAO" %>
<%@ page import="model.dao.impl.MySQLFactoryDAO" %>
<%@ page import="model.dao.impl.MySQLUserDAO" %>
<%@ page import="model.dao.UserDAO" %>
<%@ page import="model.entities.Role" %><%--
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
    <form method="post">
        Email: <input type="email" name="email"/>
        Password: <input type="password" name="password">
        <button type="submit">Ok</button>
    </form>
</div>
</body>
</html>
