<%@ page import="model.dao.impl.MySQLFactoryDAO" %>
<%@ page import="model.dao.UserDAO" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<% MySQLFactoryDAO mySQLFactoryDAO = new MySQLFactoryDAO();
    UserDAO userDAO = mySQLFactoryDAO.getUserDAO();
%>
<h1>
    <%= userDAO.getAll().get(0).toString()%>
</h1>
</body>
</html>
