<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="message" var="msg"/>
<html>
<head>
    <title><fmt:message bundle="${msg}" key="client.bills-page"/></title>
    <link href="/css/materialize/css/materialize.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="card-panel">
   <span class="blue-text text-darken-2">
       <h5><fmt:message bundle="${msg}" key="client.bills-page"/></h5>
   </span>
    <form action="/" method="post">
        <input type="hidden" name="command" value="sign_out">
        <button class="btn-floating btn-large waves-effect waves-light red right" type="submit">
            <fmt:message bundle="${msg}" key="exit-button"/>
        </button>
    </form>
    <form action="/hotel/home" method="post">
        <input type="hidden" name="command" value="client_home_page">
        <button class="btn-floating btn-large waves-effect waves-light blue left" type="submit">
            <img src="/css/ic_home_white_24dp_1x.png">
        </button>
    </form>
</div>
<br><br>
<div class="container">
    <table class="bordered">
        <tr>
            <th><fmt:message bundle="${msg}" key="client.bills.apartment-info"/></th>
            <th><fmt:message bundle="${msg}" key="client.bills.admin-info"/></th>
            <th><fmt:message bundle="${msg}" key="client.bills.price"/></th>
            <th><fmt:message bundle="${msg}" key="client.bills.payment"/></th>
        </tr>
        <c:forEach items="${sessionScope.bills}" var="item">
            <tr>
                <td>
                    <span class="blue-grey-text darken-4">
                        <fmt:message bundle="${msg}" key="number-of-rooms"/>:
                    </span>
                    <c:out value="${item.order.numberOfRooms}"/>
                    <br>
                    <span class="blue-grey-text darken-4">
                        <fmt:message bundle="${msg}" key="apartment-type"/>:
                    </span>
                    <c:out value="${item.order.apartmentType}"/>
                    <br>
                    <span class="blue-grey-text darken-4">
                        <fmt:message bundle="${msg}" key="dateFrom"/>:
                    </span>
                    <c:out value="${item.order.dateFrom}"/>
                    <br>
                    <span class="blue-grey-text darken-4">
                        <fmt:message bundle="${msg}" key="dateTo"/>:
                    </span>
                    <c:out value="${item.order.dateTo}"/>
                </td>
                <td>
                    <c:out value="${item.admin.email}"/>
                </td>
                <td>
                    <c:out value="${item.price}"/>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${item.isPaid == 1}">
                            <p>&#x2713;</p>
                        </c:when>
                        <c:otherwise>
                            <form action="/hotel/bills" method="post">
                                <input type="hidden" name="command" value="billPayment">
                                <button value="${item.id}" class="btn waves-effect waves-light"
                                        type="submit" name="billId">
                                    <fmt:message bundle="${msg}" key="client.pay-button"/>
                                </button>
                            </form>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </table>
    <ul class="pagination">
        <c:forEach begin="1" end="${requestScope.countOfBills}" varStatus="loop">
            <c:choose>
                <c:when test="${sessionScope.page eq loop.index}">
                    <li class="active">
                        <a href="/hotel/bills/?page=${loop.index}">${loop.index}</a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="waves-effect">
                        <a href="/hotel/bills/?page=${loop.index}">${loop.index}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </ul>
</div>
</body>
</html>
