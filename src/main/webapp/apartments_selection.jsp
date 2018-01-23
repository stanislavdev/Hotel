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
    <form action="/" method="post">
        <input type="hidden" name="command" value="sign_out">
        <button class="btn-floating btn-large waves-effect waves-light red right" type="submit">
            <fmt:message bundle="${msg}" key="exit-button"/>
        </button>
    </form>
    <form action="/hotel/home" method="post">
        <input type="hidden" name="command" value="admin_home_page">
        <button class="btn-floating btn-large waves-effect waves-light blue left" type="submit">
            <img src="/css/ic_home_white_24dp_1x.png">
        </button>
    </form>
</div>
<br><br>
<div class="container">
    <form method="post" action="/hotel/home">
        <input type="hidden" name="command" value="create_bill">
        <table class="bordered">
            <tr>
                <th><fmt:message bundle="${msg}" key="number-of-rooms"/></th>
                <th><fmt:message bundle="${msg}" key="apartment-type"/></th>
                <th><fmt:message bundle="${msg}" key="price-per-day"/></th>
            </tr>
            <c:forEach items="${sessionScope.apartments}" var="item">
                <tr>
                    <td><c:out value="${item.numberOfRooms}"/></td>
                    <td><c:out value="${item.apartmentType}"/></td>
                    <td><c:out value="${item.price}"/></td>
                    <td>
                        <p>
                            <input id="${item.id}" type="radio" name="apartmentId" value="${item.id}"/>
                            <label for="${item.id}"></label>
                        </p>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br><br>
        <button class="btn waves-effect waves-light" type="submit">
            <fmt:message bundle="${msg}" key="admin.buttons.send-bill"/>
        </button>
    </form>
    <form method="post" action="/hotel/home">
        <input type="hidden" name="command" value="reject-order"/>
        <button class="btn waves-effect waves-light red lighten-1" type="submit">
            <fmt:message bundle="${msg}" key="admin.buttons.reject"/>
        </button>
    </form>
</div>
</body>
</html>
