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

<form action="${pageContext.request.contextPath}/getRoomByIdJSTL" method="post">


    <h1> Характеристики комнаты </h1>

    Номер комнаты: ${requestScope.roomById.getId()}
            | название отеля: ${requestScope.hotelById.getName()}
            | количество кроватей: ${requestScope.roomById.getBeds_count()} | этаж: ${requestScope.roomById.getFloor()}
            | наличие завтрака: ${requestScope.roomById.getIncluded_breakfast()} | класс комнаты:  ${requestScope.roomClass.getRoomClass()}
            | цена: ${requestScope.roomById.getPrice()} руб.


    <a href="http://localhost:8080/getAllRooms">
        <%-- Было - <button type="button">Register</button>--%>
        <button type="button"><fmt:message key="page.login.back"/></button>
    </a>
    <input type="hidden" id="roomId" name="roomId" value="${requestScope.roomById.getId()}">
    <input type="hidden" id="roomId1" name="roomId1" value="${sessionScope.id}">
        <button type="submit"><fmt:message key="page.login.book.button"/></button>
</form>
</body>
</html>
