<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="message" var="msg"/>
<html>
<head>
    <title><fmt:message bundle="${msg}" key="home.title"/></title>
</head>
<body>
<h2><fmt:message bundle="${msg}" key="client.booking-label"/></h2>
<form method="post" action="/hotel/creating_order">
    <input type="hidden" name="command" value="create_order">
    <label><fmt:message bundle="${msg}" key="client.number-of-rooms"/>
        <select name="numberOfRooms">
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5+">5+</option>
        </select>
    </label>

    <label><fmt:message bundle="${msg}" key="client.apartment-type"/>
        <select name="apartmentType">
            <option value="standart">
                <fmt:message bundle="${msg}" key="client.apartment-type.standard"/>
            </option>
            <option value="business">
                <fmt:message bundle="${msg}" key="client.apartment-type.business"/>
            </option>
            <option value="deluxe">
                <fmt:message bundle="${msg}" key="client.apartment-type.deluxe"/>
            </option>
            <option value="president">
                <fmt:message bundle="${msg}" key="client.apartment-type.president"/>
            </option>
        </select>
    </label>

    <input name="dateFrom" type="date"/>
    <input name="dateTo" type="date"/>

    <button name="submit" type="submit"><fmt:message bundle="${msg}" key="client.confirm-button"/></button>
</form>

<form action="/hotel/home" method="post">
    <input type="hidden" name="command" value="sign_out">
    <button type="submit"><fmt:message bundle="${msg}" key="client.exit-button"/></button>
</form>

<div>
    <h2><fmt:message bundle="${msg}" key="client.history"/></h2>
    <hr>
    <table>
        <tr>
            <th><fmt:message bundle="${msg}" key="client.number-of-rooms"/></th>
            <th><fmt:message bundle="${msg}" key="client.apartment-type"/></th>
            <th><fmt:message bundle="${msg}" key="client.order-status"/></th>
        </tr>
        <c:forEach items="${sessionScope.orders}" var="item">
            <tr>
                <td><c:out value="${item.numberOfRooms}"/></td>
                <td><c:out value="${item.apartmentType}"/></td>
                <td><c:choose>
                    <c:when test="${item.accepted == 1}">
                        <c:out value="accepted"></c:out>
                    </c:when>
                    <c:otherwise>
                        <c:out value="in progress"></c:out>
                    </c:otherwise>
                </c:choose>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br>
</div>

<form action="/hotel/bills" method="post">
    <input type="hidden" name="command" value="bills-page">
    <button type="submit"><fmt:message bundle="${msg}" key="client.show-bills-button"/></button>
</form>
</body>
</html>
