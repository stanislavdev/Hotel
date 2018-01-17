<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="message" var="msg"/>
<html>
<head>
    <title><fmt:message bundle="${msg}" key="home.title"/></title>
    <link href="/css/materialize/css/materialize.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="card-panel">
   <span class="blue-text text-darken-2">
       <h4><fmt:message bundle="${msg}" key="client.booking-label"/></h4>
   </span>
    <form action="/hotel/home" method="post">
        <input type="hidden" name="command" value="sign_out">
        <button class="btn-floating btn-large waves-effect waves-light red right" type="submit">
            <fmt:message bundle="${msg}" key="client.exit-button"/>
        </button>
    </form>
</div>
<br>

<div class="container">
    <form method="post" action="/hotel/creating_order">
        <input type="hidden" name="command" value="create_order">
        <div class="row">
            <div class="col s3">
                <label for="selectNumber">
                    <fmt:message bundle="${msg}" key="client.number-of-rooms"/>
                </label>
                <select class="browser-default" id="selectNumber" name="numberOfRooms">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5+">5+</option>
                </select>
            </div>
            <div class="col s3">
                <label for="selectType">
                    <fmt:message bundle="${msg}" key="client.apartment-type"/>
                </label>
                <select class="browser-default" id="selectType" name="apartmentType">
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
            </div>
            <div class="col s3">
                <input name="dateFrom" type="date"/>
            </div>
            <div class="col s3">
                <input name="dateTo" type="date"/>
            </div>
        </div>
        <div class="row">
            <div class="col s4">
                <button class="btn waves-effect waves-light blue darken-2" type="submit" name="action">
                    <fmt:message bundle="${msg}" key="client.confirm-button"/>
                </button>
            </div>
        </div>
    </form>


    <div>
        <h3><fmt:message bundle="${msg}" key="client.history"/></h3>
        <table class="bordered">
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
</div>
</body>
</html>
