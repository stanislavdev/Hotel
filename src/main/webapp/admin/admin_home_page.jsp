<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="message" var="msg"/>
<html>
<head>
    <title><fmt:message bundle="${msg}" key="admin.home-page"/></title>
    <link href="/css/materialize/css/materialize.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="card-panel">
   <span class="blue-text text-darken-2">
       <h5><fmt:message bundle="${msg}" key="admin.home-page"/></h5>
   </span>
    <form action="/" method="post">
        <input type="hidden" name="command" value="sign_out">
        <button class="btn-floating btn-large waves-effect waves-light red right" type="submit">
            <fmt:message bundle="${msg}" key="exit-button"/>
        </button>
    </form>
</div>
<br><br>
<div class="container">
    <form method="post" action="/hotel/apartments">
        <input type="hidden" name="command" value="chose_apartment">
        <table id="orders" class="bordered">
            <tr>
                <th><fmt:message bundle="${msg}" key="number-of-rooms"/></th>
                <th><fmt:message bundle="${msg}" key="apartment-type"/></th>
                <th><fmt:message bundle="${msg}" key="dateFrom"/></th>
                <th><fmt:message bundle="${msg}" key="dateTo"/></th>
                <th><fmt:message bundle="${msg}" key="admin.home.user-email"/></th>
                <th></th>
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
                                <p>
                                    <input id="${item.id}" type="radio" name="orderId" value="${item.id}"/>
                                    <label for="${item.id}"></label>
                                </p>
                            </td>
                        </c:when>
                    </c:choose>
                </tr>
            </c:forEach>
        </table>
        <br>
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
        <div class="row">
            <div class="col l7">
                <button class="btn waves-effect waves-light blue darken-2" type="submit">
                    <fmt:message bundle="${msg}" key="admin.show-available-apartments"/>
                </button>
            </div>
            <div class="col l7">
                <c:if test="${requestScope.exception ne null}">
                    <h6 class="left">
                            <span class="flow-text red-text">
                                 <fmt:message bundle="${msg}" key="admin.select-exception"/>
                             </span>
                    </h6>
                </c:if>
            </div>
        </div>
    </form>
</div>
</body>
</html>
