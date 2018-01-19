<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="message" var="msg"/>
<html>
<head>
    <title><fmt:message bundle="${msg}" key="client.bills-page"/></title>
    <link href="/css/materialize/css/materialize.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>
<div class="card-panel">
   <span class="blue-text text-darken-2">
       <h5><fmt:message bundle="${msg}" key="client.bills-page"/></h5>
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
                        <fmt:message bundle="${msg}" key="client.number-of-rooms"/>:
                    </span>
                    <c:out value="${item.order.numberOfRooms}"/>
                    <br>
                    <span class="blue-grey-text darken-4">
                        <fmt:message bundle="${msg}" key="client.apartment-type"/>:
                    </span>
                    <c:out value="${item.order.apartmentType}"/>
                    <br>
                    <span class="blue-grey-text darken-4">
                        <fmt:message bundle="${msg}" key="client.orders-in-progress.dateFrom"/>:
                    </span>
                    <c:out value="${item.order.dateFrom}"/>
                    <br>
                    <span class="blue-grey-text darken-4">
                        <fmt:message bundle="${msg}" key="client.orders-in-progress.dateTo"/>:
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
                            <i class="material-icons">check</i>
                        </c:when>
                        <c:otherwise>
                            <form action="/hotel/bills-payment" method="post">
                                <input type="hidden" name="command" value="billPayment">
                                <button value="${item.id}" class="btn waves-effect waves-light"
                                        type="submit" name="billId">
                                    <fmt:message bundle="${msg}" key="client.pay-button"/>
                                    <i class="material-icons right">payment</i>
                                </button>
                            </form>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
