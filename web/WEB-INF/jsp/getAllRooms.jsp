<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 28.04.2024
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="addHeaders.jsp"%>
<h1> Список комнат </h1>

<c:forEach var="room" items="${requestScope.roomsList}">

    <li>
        <br> {Номер комнаты:
        <a href="${pageContext.request.contextPath}/getRoomByIdJSTL?roomId=${room.getId()}">
            <br> ${room.toString()}
<%--            <br> ${room.getId()} | количество кроватей: ${room.getBeds_count()} | этаж: ${room.getFloor()} | | цена: ${room.getPrice()} руб.--%>


<%--    <c:forEach var="hotel" items="${requestScope.hotelList}">--%>

<%--    <c:if test= "${room.getHotel_id()} == ${requestScope.hotelList.getId()}">--%>
<%--        название отеля: ${hotel.getName()} |--%>
<%--    </c:if>--%>

<%--    </c:forEach>--%>

<%--    | количество кроватей: ${room.getBeds_count()} | этаж: ${room.getFloor()}--%>
<%--&lt;%&ndash;    | наличие завтрака: ${room.getIncluded_breakfast()} | класс комнаты:  ${requestScope.roomClass.getRoomClass()}&ndash;%&gt;--%>
<%--    | цена: ${room.getPrice()} руб.--%>
<%--    <br>--%>

        </a>
    </li>
</c:forEach>
</body>
</html>
