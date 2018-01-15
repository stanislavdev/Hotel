<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: stanislav
  Date: 12.01.18
  Time: 20:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:out value="Chose an apartment for ${client.email}"/>
<form method="post" action="/hotel/home">
    <input type="hidden" name="command" value="create_bill">
    <table>
        <tr>
            <th>Number of rooms</th>
            <th>Type</th>
            <th>Price</th>
        </tr>
        <c:forEach items="${sessionScope.apartments}" var="item">
            <tr>
                <td><c:out value="${item.numberOfRooms}"/></td>
                <td><c:out value="${item.apartmentType}"/></td>
                <td><c:out value="${item.price}"/></td>
                <td>
                    <input type="radio" name="chosenApartment" value="${item.id}"/>
                </td>
            </tr>
        </c:forEach>
    </table>
    <input type="submit" value="Send a bill">
</form>
</body>
</html>
