<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 17.05.2024
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>

    <%@ include file="addHeaders.jsp" %>
    <h1> Список комнат данного отеля </h1>

    <c:forEach var="room" items="${requestScope.roomListById}">

        <li>
            <br> {Номер комнаты:
            <a href="${pageContext.request.contextPath}/getRoomByIdJSTL?roomId=${room.getId()}">
                <br> ${room.toString()}
            </a>
        </li>
    </c:forEach>
</head>
<body>
</body>
</html>
