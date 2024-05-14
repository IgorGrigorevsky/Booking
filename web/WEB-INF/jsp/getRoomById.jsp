<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10.05.2024
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="addHeaders.jsp" %>

<form action="${pageContext.request.contextPath}/createBooking" method="post">

    <h1> Характеристики комнаты </h1>


    <c:forEach var="room" items="${requestScope.roomById}">

    <span>
        Номер комнаты: ${room.getId()}
        | название отеля: ${requestScope.hotelById.getName()}
        | количество кроватей: ${room.getBeds_count()} | этаж: ${room.getFloor()}
        | наличие завтрака: ${room.getIncluded_breakfast()} | класс комнаты:  ${requestScope.roomClass.getRoomClass()}
        | цена: ${room.getPrice()} руб.
        <br>

    </span>
    </c:forEach>
    <br>

    <a href="http://localhost:8080/getAllRooms">
        <%-- Было - <button type="button">Register</button>--%>
        <button type="button"><fmt:message key="page.login.back"/></button>
    </a>
    <a href="http://localhost:8080/createBooking">
        <%-- Было - <button type="button">Register</button>--%>
        <button type="button"><fmt:message key="page.login.book.button"/></button>
    </a>

</body>
</html>
