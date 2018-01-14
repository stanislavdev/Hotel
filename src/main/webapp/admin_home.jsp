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
        #orders {
            font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        #orders td, #customers th {
            border: 1px solid #ddd;
            padding: 8px;
        }

        #orders tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        #orders tr:hover {
            background-color: #ddd;
        }

        #orders th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: left;
            background-color: #4CAF50;
            color: white;
        }

        .button {
            background-color: #4CAF50; /* Green */
            border: none;
            color: white;
            padding: 15px 32px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
            -webkit-transition-duration: 0.4s; /* Safari */
            transition-duration: 0.4s;
        }

        .button2:hover {
            box-shadow: 0 12px 16px 0 rgba(0, 0, 0, 0.24), 0 17px 50px 0 rgba(0, 0, 0, 0.19);
        }
    </style>
</head>
<body>
<form method="post" action="/hotel/chose-apartment">
    <table id="orders">
        <tr>
            <th>Number of rooms</th>
            <th>Apartment type</th>
            <th>Date from</th>
            <th>Date to</th>
            <th>User</th>
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
                            <input type="radio" name="chosenRadio" value="${item.id}"/>
                        </td>
                    </c:when>
                </c:choose>
            </tr>
        </c:forEach>
    </table>
    <br>
    <input class="button button2" type="submit" value="Show"/>
</form>

<form action="/hotel/sign_out" method="post">
    <button type="submit">Exit</button>
</form>
</body>
</html>
