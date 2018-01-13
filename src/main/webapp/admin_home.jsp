<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: stanislav
  Date: 03.01.18
  Time: 22:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        .orderTable {
            position: -webkit-sticky;
            position: sticky;
            left: 0;
        }
    </style>
</head>
<body>
<form method="post" action="/hotel/chose-apartment">
    <table class="orderTable">
        <tr>
            <th>Number of rooms</th>
            <th>Apartment type</th>
            <th>Date from</th>
            <th>Date to</th>
            <th>User</th>
        </tr>
        <c:forEach items="${sessionScope.orders}" var="item">
            <tr>
                <c:choose>
                    <c:when test="${item.accepted == 0}">
                        <td><c:out value="${item.numberOfRooms}"/></td>
                        <td><c:out value="${item.apartmentType}"/></td>
                        <td><c:out value="${item.dateFrom}"/></td>
                        <td><c:out value="${item.dateTo}"/></td>
                        <td><c:out value="${item.client.email}"/></td>
                        <td>
                            <input type="radio" name="chosenRadio" value="${item.id}"/>
                        </td>
                    </c:when>
                </c:choose>
            </tr>
        </c:forEach>
    </table>
    <input type="submit" value="Show"/>
</form>

<form action="/hotel/sign_out" method="post">
    <button type="submit">Exit</button>
</form>
</body>
</html>
