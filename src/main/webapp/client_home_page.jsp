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
       <h5><fmt:message bundle="${msg}" key="client.booking-label"/></h5>
   </span>
    <form action="/hotel/home" method="post">
        <input type="hidden" name="command" value="sign_out">
        <button class="btn-floating btn-large waves-effect waves-light red right" type="submit">
            <fmt:message bundle="${msg}" key="exit-button"/>
        </button>
    </form>
</div>
<br>

<div class="container">
    <div class="card-panel hoverable">
        <form method="post" action="/hotel/creating_order">
            <input type="hidden" name="command" value="create_order">
            <input type="hidden" name="currentPage" value="/client_home.jsp">
            <div class="row">
                <div class="col s3">
                    <label for="selectNumber">
                        <fmt:message bundle="${msg}" key="number-of-rooms"/>
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
                        <fmt:message bundle="${msg}" key="apartment-type"/>
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
                <div class="col s8">
                    <c:if test="${requestScope.exception != null}">
                        <h6 class="left">
                            <span class="flow-text red-text">
                                 <fmt:message bundle="${msg}" key="client.wrong-date.exception"/>
                             </span>
                        </h6>
                    </c:if>
                </div>
            </div>
        </form>
    </div>
    <div class="row">
        <div class="col s6">
            <form action="/hotel/bills" method="post">
                <input type="hidden" name="command" value="bills-page">

                <button class="btn waves-effect waves-light blue darken-2" type="submit">
                    <fmt:message bundle="${msg}" key="client.show-bills-button"/>
                </button>
            </form>
        </div>
    </div>

    <div>
        <h4><fmt:message bundle="${msg}" key="client.orders.in-progress"/></h4>
        <table class="bordered">
            <tr>
                <th><fmt:message bundle="${msg}" key="number-of-rooms"/></th>
                <th><fmt:message bundle="${msg}" key="apartment-type"/></th>
                <th><fmt:message bundle="${msg}" key="dateFrom"/></th>
                <th><fmt:message bundle="${msg}" key="dateTo"/></th>
            </tr>
            <c:forEach items="${sessionScope.orders}" var="item">
                <tr>
                    <td><c:out value="${item.numberOfRooms}"/></td>
                    <td><c:out value="${item.apartmentType}"/></td>
                    <td><c:out value="${item.dateFrom}"/></td>
                    <td><c:out value="${item.dateTo}"/></td>
                </tr>
            </c:forEach>
        </table>
        <ul class="pagination">
            <c:forEach begin="1" end="${requestScope.countOfOrders}" varStatus="loop">
                <c:choose>
                    <c:when test="${sessionScope.page eq loop.index}">
                        <li class="active">
                            <a href="/hotel/home/?page=${loop.index}">${loop.index}</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="waves-effect">
                            <a href="/hotel/home/?page=${loop.index}">${loop.index}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </ul>
    </div>
</div>
</body>
</html>
