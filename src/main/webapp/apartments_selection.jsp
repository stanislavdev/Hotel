<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="message" var="msg"/>
<html>
<head>
    <title>Title</title>
    <link href="/css/materialize/css/materialize.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="card-panel">
   <span class="blue-text text-darken-2">
       <h5><fmt:message bundle="${msg}" key="admin.apartments-choice"/></h5>
   </span>
    <form action="/hotel/home" method="post">
        <input type="hidden" name="command" value="sign_out">
        <button class="btn-floating btn-large waves-effect waves-light red right" type="submit">
            <fmt:message bundle="${msg}" key="exit-button"/>
        </button>
    </form>
</div>
<br><br>
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
