<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: stanislav
  Date: 15.01.18
  Time: 13:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Your bills:
<table>
    <tr>
        <th>Price</th>
        <th>Order</th>
        <th>Apartment</th>
        <th>Admin</th>
        <th>Need to be paid</th>
    </tr>
    <tr>
        <c:forEach items="${sessionScope.bills}" var="item">
            <td><c:out value="${item.price}"/></td>
            <td>
                <c:out value="${item.order.dateFrom}"/>
                <br>
                <c:out value="${item.order.dateTo}"/>
            </td>
            <td><c:out value="${item.order.numberOfRooms}"/></td>
            <td><c:out value="${item.admin.email}"/></td>
        </c:forEach>
    </tr>
</table>
</body>
</html>
