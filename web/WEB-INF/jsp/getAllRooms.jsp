<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 28.04.2024
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="addHeaders.jsp" %>
<form action="${pageContext.request.contextPath}/getAllRooms2" method="get">
    <label> hotel filter:
        <select name="hotel" id="hotelId">
            <c:forEach var="hotel" items="${requestScope.hotelList}">
                <option value="${hotel.getId()}"> ${hotel.getName()}</option>
            </c:forEach>
        </select>
    </label>
    <br>
    <%--кнопака для отправки формы на сервер по вышеуказанному url--%>
    <button type="submit"><fmt:message key="page.login.submit.button"/></button>
</form>


<h1> Список комнат </h1>

<c:forEach var="room" items="${requestScope.roomsList}">

    <li>
        <br> {Номер комнаты:
        <a href="${pageContext.request.contextPath}/getRoomByIdJSTL?roomId=${room.getId()}">
            <br> ${room.toString()}
        </a>
    </li>
</c:forEach>
</body>
</html>
